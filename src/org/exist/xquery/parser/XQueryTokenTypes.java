// $ANTLR 2.7.7 (2006-11-01): "XQuery.g" -> "XQueryParser.java"$

	package org.exist.xquery.parser;

	import antlr.debug.misc.*;
	import java.io.StringReader;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Iterator;
	import java.util.Stack;
	import org.exist.storage.BrokerPool;
	import org.exist.storage.DBBroker;
	import org.exist.storage.analysis.Tokenizer;
	import org.exist.EXistException;
	import org.exist.dom.DocumentSet;
	import org.exist.dom.DocumentImpl;
	import org.exist.dom.QName;
	import org.exist.security.PermissionDeniedException;
	import org.exist.xquery.*;
	import org.exist.xquery.value.*;
	import org.exist.xquery.functions.fn.*;

public interface XQueryTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int QNAME = 4;
	int PREDICATE = 5;
	int FLWOR = 6;
	int PARENTHESIZED = 7;
	int ABSOLUTE_SLASH = 8;
	int ABSOLUTE_DSLASH = 9;
	int WILDCARD = 10;
	int PREFIX_WILDCARD = 11;
	int FUNCTION = 12;
	int UNARY_MINUS = 13;
	int UNARY_PLUS = 14;
	int XPOINTER = 15;
	int XPOINTER_ID = 16;
	int VARIABLE_REF = 17;
	int VARIABLE_BINDING = 18;
	int ELEMENT = 19;
	int ATTRIBUTE = 20;
	int ATTRIBUTE_CONTENT = 21;
	int TEXT = 22;
	int VERSION_DECL = 23;
	int NAMESPACE_DECL = 24;
	int DEF_NAMESPACE_DECL = 25;
	int DEF_COLLATION_DECL = 26;
	int DEF_FUNCTION_NS_DECL = 27;
	int ANNOT_DECL = 28;
	int GLOBAL_VAR = 29;
	int FUNCTION_DECL = 30;
	int PROLOG = 31;
	int OPTION = 32;
	int ATOMIC_TYPE = 33;
	int MODULE = 34;
	int ORDER_BY = 35;
	int GROUP_BY = 36;
	int POSITIONAL_VAR = 37;
	int CATCH_ERROR_CODE = 38;
	int CATCH_ERROR_DESC = 39;
	int CATCH_ERROR_VAL = 40;
	int MODULE_DECL = 41;
	int MODULE_IMPORT = 42;
	int SCHEMA_IMPORT = 43;
	int ATTRIBUTE_TEST = 44;
	int COMP_ELEM_CONSTRUCTOR = 45;
	int COMP_ATTR_CONSTRUCTOR = 46;
	int COMP_TEXT_CONSTRUCTOR = 47;
	int COMP_COMMENT_CONSTRUCTOR = 48;
	int COMP_PI_CONSTRUCTOR = 49;
	int COMP_NS_CONSTRUCTOR = 50;
	int COMP_DOC_CONSTRUCTOR = 51;
	int PRAGMA = 52;
	int GTEQ = 53;
	int SEQUENCE = 54;
	int LITERAL_xpointer = 55;
	int LPAREN = 56;
	int RPAREN = 57;
	int NCNAME = 58;
	int LITERAL_xquery = 59;
	int LITERAL_version = 60;
	int SEMICOLON = 61;
	int LITERAL_module = 62;
	int LITERAL_namespace = 63;
	int EQ = 64;
	int STRING_LITERAL = 65;
	int LITERAL_declare = 66;
	int LITERAL_default = 67;
	// "boundary-space" = 68
	int LITERAL_ordering = 69;
	int LITERAL_construction = 70;
	// "base-uri" = 71
	// "copy-namespaces" = 72
	int LITERAL_option = 73;
	int LITERAL_function = 74;
	int LITERAL_variable = 75;
	int MOD = 76;
	int LITERAL_import = 77;
	int LITERAL_encoding = 78;
	int LITERAL_collation = 79;
	int LITERAL_element = 80;
	int LITERAL_order = 81;
	int LITERAL_empty = 82;
	int LITERAL_greatest = 83;
	int LITERAL_least = 84;
	int LITERAL_preserve = 85;
	int LITERAL_strip = 86;
	int LITERAL_ordered = 87;
	int LITERAL_unordered = 88;
	int COMMA = 89;
	// "no-preserve" = 90
	int LITERAL_inherit = 91;
	// "no-inherit" = 92
	int DOLLAR = 93;
	int LCURLY = 94;
	int RCURLY = 95;
	int COLON = 96;
	int LITERAL_external = 97;
	int LITERAL_schema = 98;
	// ":" = 99
	int LITERAL_as = 100;
	int LITERAL_at = 101;
	// "empty-sequence" = 102
	int QUESTION = 103;
	int STAR = 104;
	int PLUS = 105;
	int LITERAL_item = 106;
	int LITERAL_for = 107;
	int LITERAL_let = 108;
	int LITERAL_try = 109;
	int LITERAL_some = 110;
	int LITERAL_every = 111;
	int LITERAL_if = 112;
	int LITERAL_switch = 113;
	int LITERAL_typeswitch = 114;
	int LITERAL_update = 115;
	int LITERAL_replace = 116;
	int LITERAL_value = 117;
	int LITERAL_insert = 118;
	int LITERAL_delete = 119;
	int LITERAL_rename = 120;
	int LITERAL_with = 121;
	int LITERAL_into = 122;
	int LITERAL_preceding = 123;
	int LITERAL_following = 124;
	int LITERAL_catch = 125;
	int UNION = 126;
	int LITERAL_where = 127;
	int LITERAL_return = 128;
	int LITERAL_in = 129;
	int LITERAL_by = 130;
	int LITERAL_stable = 131;
	int LITERAL_ascending = 132;
	int LITERAL_descending = 133;
	int LITERAL_group = 134;
	int LITERAL_satisfies = 135;
	int LITERAL_case = 136;
	int LITERAL_then = 137;
	int LITERAL_else = 138;
	int LITERAL_or = 139;
	int LITERAL_and = 140;
	int LITERAL_instance = 141;
	int LITERAL_of = 142;
	int LITERAL_treat = 143;
	int LITERAL_castable = 144;
	int LITERAL_cast = 145;
	int BEFORE = 146;
	int AFTER = 147;
	int LITERAL_eq = 148;
	int LITERAL_ne = 149;
	int LITERAL_lt = 150;
	int LITERAL_le = 151;
	int LITERAL_gt = 152;
	int LITERAL_ge = 153;
	int GT = 154;
	int NEQ = 155;
	int LT = 156;
	int LTEQ = 157;
	int LITERAL_is = 158;
	int LITERAL_isnot = 159;
	int ANDEQ = 160;
	int OREQ = 161;
	int CONCAT = 162;
	int LITERAL_to = 163;
	int MINUS = 164;
	int LITERAL_div = 165;
	int LITERAL_idiv = 166;
	int LITERAL_mod = 167;
	int PRAGMA_START = 168;
	int PRAGMA_END = 169;
	int LITERAL_union = 170;
	int LITERAL_intersect = 171;
	int LITERAL_except = 172;
	int SLASH = 173;
	int DSLASH = 174;
	int LITERAL_text = 175;
	int LITERAL_node = 176;
	int LITERAL_attribute = 177;
	int LITERAL_comment = 178;
	// "processing-instruction" = 179
	// "document-node" = 180
	int LITERAL_document = 181;
	int SELF = 182;
	int XML_COMMENT = 183;
	int XML_PI = 184;
	int LPPAREN = 185;
	int RPPAREN = 186;
	int AT = 187;
	int PARENT = 188;
	int LITERAL_child = 189;
	int LITERAL_self = 190;
	int LITERAL_descendant = 191;
	// "descendant-or-self" = 192
	// "following-sibling" = 193
	int LITERAL_parent = 194;
	int LITERAL_ancestor = 195;
	// "ancestor-or-self" = 196
	// "preceding-sibling" = 197
	int DOUBLE_LITERAL = 198;
	int DECIMAL_LITERAL = 199;
	int INTEGER_LITERAL = 200;
	// "schema-element" = 201
	int END_TAG_START = 202;
	int QUOT = 203;
	int APOS = 204;
	int QUOT_ATTRIBUTE_CONTENT = 205;
	int ESCAPE_QUOT = 206;
	int APOS_ATTRIBUTE_CONTENT = 207;
	int ESCAPE_APOS = 208;
	int ELEMENT_CONTENT = 209;
	int XML_COMMENT_END = 210;
	int XML_PI_END = 211;
	int XML_CDATA = 212;
	int LITERAL_collection = 213;
	int LITERAL_validate = 214;
	int XML_PI_START = 215;
	int XML_CDATA_START = 216;
	int XML_CDATA_END = 217;
	int LETTER = 218;
	int DIGITS = 219;
	int HEX_DIGITS = 220;
	int NMSTART = 221;
	int NMCHAR = 222;
	int WS = 223;
	int EXPR_COMMENT = 224;
	int PREDEFINED_ENTITY_REF = 225;
	int CHAR_REF = 226;
	int S = 227;
	int NEXT_TOKEN = 228;
	int CHAR = 229;
	int BASECHAR = 230;
	int IDEOGRAPHIC = 231;
	int COMBINING_CHAR = 232;
	int DIGIT = 233;
	int EXTENDER = 234;
}
