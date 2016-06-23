package io.cloudslang.content.xml.utils;

/**
 * Created by markowis on 18/02/2016.
 */
public class Constants {
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String EMPTY_STRING = "";
    public static final String YES = "yes";
    public static final String XML_PATH = "xmlPath";
    public static final String XML_URL = "xmlUrl";
    public static final String INVALID_XML_DOCUMENT_SOURCE = " is an invalid input value. Valid values are: xmlString and xmlPath and xmlUrl";
    public static final String INVALID_XSD_DOCUMENT_SOURCE = " is an invalid input value. Valid values are: xsdString and xsdPath";
    public static final String XSD_PATH = "xsdPath";

    public static final class OutputNames {
        public static final String RETURN_RESULT = "returnResult";
        public static final String RESULT_TEXT = "result";
        public static final String SELECTED_VALUE = "selectedValue";
        public static final String RESULT_XML = "resultXML";
        public static final String RETURN_CODE = "returnCode";
        public static final String EXCEPTION = "exception";
    }

    public static final class InputNames {
        public static final String XML_DOCUMENT = "xmlDocument";
        public static final String XML_DOCUMENT_SOURCE = "xmlDocumentSource";
        public static final String XSD_DOCUMENT = "xsdDocument";
        public static final String XSD_DOCUMENT_SOURCE = "xsdDocumentSource";
        public static final String XPATH_QUERY = "xPathQuery";
        public static final String XPATH_ELEMENT_QUERY = "xPathElementQuery";
        public static final String QUERY_TYPE = "queryType";
        public static final String DELIMITER = "delimiter";
        public static final String ATTRIBUTE_NAME = "attributeName";
        public static final String VALUE = "value";
        public static final String XML_ELEMENT = "xmlElement";
        public static final String SECURE_PROCESSING = "secureProcessing";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String TRUST_ALL_ROOTS = "trustAllRoots";
        public static final String KEYSTORE = "keystore";
        public static final String KEYSTORE_PASSWORD = "keystorePassword";
        public static final String TRUST_KEYSTORE = "trustKeystore";
        public static final String TRUST_PASSWORD = "trustPassword";
        public static final String X_509_HOSTNAME_VERIFIER = "x509HostnameVerifier";
        public static final String PROXY_HOST = "proxyHost";
        public static final String PROXY_PORT = "proxyPort";
        public static final String PROXY_USERNAME = "proxyUsername";
        public static final String PROXY_PASSWORD = "proxyPassword";
    }

    public static final class ResponseNames {
        public static final String SUCCESS = "success";
        public static final String FAILURE = "failure";
    }

    public static final class QueryTypes {
        public static final String NODE = "node";
        public static final String NODE_LIST = "nodelist";
        public static final String VALUE = "value";
    }

    public static final class ErrorMessages {
        public static final String GENERAL_ERROR = "Error: ";
        public static final String PARSING_ERROR = "Parsing error: ";
        public static final String TRANSFORMER_ERROR = "Transformer error: ";
        public static final String XPATH_PARSING_ERROR = "XPath parsing error: ";
        public static final String NEED_ELEMENT_TYPE = "XPath must return element types.";
        public static final String ELEMENT_NOT_FOUND = "Element not found.";
        public static final String ADD_ATTRIBUTE_FAILURE = "Addition of attribute failed: ";
        public static final String APPEND_CHILD_FAILURE = "Append failed: ";
        public static final String INSERT_BEFORE_FAILURE = "Insert failed: ";
        public static final String REMOVE_FAILURE = "Removal failed: ";
        public static final String SET_VALUE_FAILURE = "Setting value failed: ";
        public static final String VALIDATION_FAILURE =  "Validation failed: ";
    }

    public static final class SuccessMessages {
        public static final String ADD_ATTRIBUTE_SUCCESS = "Attribute set successfully.";
        public static final String APPEND_CHILD_SUCCESS = "Child appended successfully.";
        public static final String INSERT_BEFORE_SUCCESS = "Inserted before successfully.";
        public static final String REMOVE_SUCCESS = "Removed successfully.";
        public static final String SELECT_SUCCESS = "XPath queried successfully.";
        public static final String SET_VALUE_SUCCESS = "Value set successfully.";
        public static final String PARSING_SUCCESS = "Parsing successful.";
        public static final String VALIDATION_SUCCESS = "XML is valid.";
    }

    public static final class Defaults {
        public static final String DELIMITER = ",";
        public static final String XML_DOCUMENT_SOURCE = "xmlString";
        public static final String XSD_DOCUMENT_SOURCE = "xsdString";
    }

    public static final class ReturnCodes {
        public static final String SUCCESS = "0";
        public static final String FAILURE = "-1";
    }
}
