package org.exist.indexing.range;

import com.ibm.icu.text.Collator;
import org.apache.logging.log4j.LogManager;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.collation.ICUCollationAttributeFactory;
import org.apache.lucene.util.AttributeFactory;
import org.exist.util.Collations;
import org.exist.util.DatabaseConfigurationException;
import org.apache.logging.log4j.Logger;
import org.exist.xquery.XPathException;
import org.w3c.dom.Element;

import java.io.Reader;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.invoke.MethodType.methodType;

/**
 * Lucene analyzer used by the range index. Based on {@link KeywordTokenizer}, it allows additional
 * filters to be added to the pipeline through the collection.xconf configuration. A collation may be
 * specified as well.
 */
public class RangeIndexAnalyzer extends Analyzer {

    private final static Logger LOG = LogManager.getLogger(RangeIndexAnalyzer.class);

    private static class FilterConfig {
        Function<TokenStream, TokenStream> constructor;

        FilterConfig(Element config) throws DatabaseConfigurationException {
            final String className = config.getAttribute("class");
            if (className == null) {
                throw new DatabaseConfigurationException("No class specified for filter");
            }
            try {
                final Class clazz = Class.forName(className);
                if (!TokenFilter.class.isAssignableFrom(clazz)) {
                    throw new DatabaseConfigurationException("Filter " + className + " is not a subclass of " +
                        TokenFilter.class.getName());
                }
                final MethodHandles.Lookup lookup = MethodHandles.lookup();
                final MethodHandle methodHandle = lookup.findConstructor(clazz, methodType(void.class, TokenStream.class));

                this.constructor = (Function<TokenStream, TokenStream>)
                        LambdaMetafactory.metafactory(
                                lookup, "apply", methodType(Function.class),
                                methodHandle.type().erase(), methodHandle, methodHandle.type()).getTarget().invokeExact();
            } catch (final Throwable e) {
                throw new DatabaseConfigurationException("Filter not found: " + className, e);
            }
        }
    }

    private List<FilterConfig> filterConfigs = new ArrayList<>();
    private Collator collator = null;

    public RangeIndexAnalyzer() {
    }

    public void addFilter(Element filter) throws DatabaseConfigurationException {
        filterConfigs.add(new FilterConfig(filter));
    }

    public void addCollation(String uri) throws DatabaseConfigurationException {
        try {
            collator = Collations.getCollationFromURI(uri);
        } catch (XPathException e) {
            throw new DatabaseConfigurationException(e.getMessage(), e);
        }
    }

    @Override
    protected TokenStreamComponents createComponents(final String fieldName, final Reader reader) {
        AttributeFactory factory = AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY;
        if (collator != null) {
            factory = new ICUCollationAttributeFactory(collator);
        }
        final Tokenizer src = new KeywordTokenizer(factory, reader, 256);
        TokenStream tok = src;
        for (final FilterConfig filter: filterConfigs) {
            tok = filter.constructor.apply(tok);
        }
        return new TokenStreamComponents(src, tok);
    }
}
