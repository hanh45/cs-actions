package io.cloudslang.content.jclouds.actions.tags;

import com.hp.oo.sdk.content.annotations.Action;
import com.hp.oo.sdk.content.annotations.Output;
import com.hp.oo.sdk.content.annotations.Param;
import com.hp.oo.sdk.content.annotations.Response;
import com.hp.oo.sdk.content.plugin.ActionMetadata.MatchType;
import com.hp.oo.sdk.content.plugin.ActionMetadata.ResponseType;
import io.cloudslang.content.jclouds.entities.constants.Constants;
import io.cloudslang.content.jclouds.entities.constants.Inputs;
import io.cloudslang.content.jclouds.entities.constants.Outputs;
import io.cloudslang.content.jclouds.entities.inputs.CommonInputs;
import io.cloudslang.content.jclouds.entities.inputs.CustomInputs;
import io.cloudslang.content.jclouds.execute.queries.QueryApiExecutor;
import io.cloudslang.content.jclouds.utils.ExceptionProcessor;

import java.util.Map;

/**
 * Created by Mihai Tusa.
 * 7/21/2016.
 */
public class CreateTagsAction {
    /**
     * Adds or overwrites one or more tags for the specified Amazon EC2 resource/resources.
     * Note: Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be
     * unique per resource. For more information about tags, see Tagging Your Resources:
     * http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html in the Amazon Elastic Compute Cloud User Guide.
     * For more information about creating IAM policies that control users' access to resources based on tags, see
     * Supported Resource-Level Permissions: http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html
     * for Amazon EC2 API Actions in the Amazon Elastic Compute Cloud User Guide.
     *
     * @param endpoint          Endpoint to which request will be sent.
     *                          Default: "https://ec2.amazonaws.com"
     * @param identity          ID of the secret access key associated with your Amazon AWS or IAM account.
     *                          Example: "AKIAIOSFODNN7EXAMPLE"
     * @param credential        Secret access key associated with your Amazon AWS or IAM account.
     *                          Example: "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"
     * @param proxyHost         Optional - proxy server used to connect to Amazon API. If empty no proxy will be used.
     *                          Default: ""
     * @param proxyPort         Optional - proxy server port. You must either specify values for both <proxyHost> and
     *                          <proxyPort> inputs or leave them both empty.
     *                          Default: ""
     * @param proxyUsername     Optional - proxy server user name.
     *                          Default: ""
     * @param proxyPassword     Optional - proxy server password associated with the <proxyUsername> input value.
     *                          Default: ""
     * @param headers           Optional - string containing the headers to use for the request separated by new line (CRLF).
     *                          The header name-value pair will be separated by ":".
     *                          Format: Conforming with HTTP standard for headers (RFC 2616)
     *                          Examples: "Accept:text/plain"
     *                          Default: ""
     * @param queryParams       Optional - string containing query parameters that will be appended to the URL. The names
     *                          and the values must not be URL encoded because if they are encoded then a double encoded
     *                          will occur. The separator between name-value pairs is "&" symbol. The query name will be
     *                          separated from query value by "=".
     *                          Examples: "parameterName1=parameterValue1&parameterName2=parameterValue2"
     *                          Default: ""
     * @param version           Version of the web service to made the call against it.
     *                          Example: "2016-09-15"
     * @param delimiter         Optional - delimiter that will be used
     *                          Default: ","
     * @param resourceIdsString String that contains Id's of one or more resources to tag.
     *                          Example: "i-12345678"
     * @param keyTagsString     String that contains one or more key tags separated by delimiter.
     *                          Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters.
     *                          May not begin with "aws:";  Each resource can have a maximum of 50 tags.
     *                          Note: if you want to overwrite the existing tag and replace it with empty value then
     *                          specify the parameter with "Not relevant" string
     *                          Example: "Name,webserver,stack,scope"
     *                          Default: ""
     * @param valueTagsString   String that contains one or more tag values separated by delimiter. The value parameter
     *                          is required, but if you don't want the tag to have a value, specify the parameter with
     *                          "Not relevant" string, and we set the value to an empty string.
     *                          Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters;
     *                          Each resource can have a maximum of 50 tags
     *                          Example of values string for tagging resourceswith values corresponding to the keys from
     *                          above example: "Tagged from API call,Not relevant,Testing,For testing purposes"
     *                          Default: ""
     * @return A map with strings as keys and strings as values that contains: outcome of the action, returnCode of the
     * operation, or failure message and the exception if there is one
     */
    @Action(name = "Create Tags",
            outputs = {
                    @Output(Outputs.RETURN_CODE),
                    @Output(Outputs.RETURN_RESULT),
                    @Output(Outputs.EXCEPTION)
            },
            responses = {
                    @Response(text = Outputs.SUCCESS, field = Outputs.RETURN_CODE, value = Outputs.SUCCESS_RETURN_CODE,
                            matchType = MatchType.COMPARE_EQUAL, responseType = ResponseType.RESOLVED),
                    @Response(text = Outputs.FAILURE, field = Outputs.RETURN_CODE, value = Outputs.FAILURE_RETURN_CODE,
                            matchType = MatchType.COMPARE_EQUAL, responseType = ResponseType.ERROR)
            }
    )
    public Map<String, String> createTags(@Param(value = Inputs.CommonInputs.ENDPOINT, required = true) String endpoint,
                                          @Param(value = Inputs.CommonInputs.IDENTITY, required = true) String identity,
                                          @Param(value = Inputs.CommonInputs.CREDENTIAL, required = true, encrypted = true) String credential,
                                          @Param(value = Inputs.CommonInputs.PROXY_HOST) String proxyHost,
                                          @Param(value = Inputs.CommonInputs.PROXY_PORT) String proxyPort,
                                          @Param(value = Inputs.CommonInputs.PROXY_USERNAME) String proxyUsername,
                                          @Param(value = Inputs.CommonInputs.PROXY_PASSWORD, encrypted = true) String proxyPassword,
                                          @Param(value = Inputs.CommonInputs.HEADERS) String headers,
                                          @Param(value = Inputs.CommonInputs.QUERY_PARAMS) String queryParams,
                                          @Param(value = Inputs.CommonInputs.VERSION, required = true) String version,
                                          @Param(value = Inputs.CommonInputs.DELIMITER) String delimiter,

                                          @Param(value = Inputs.CustomInputs.RESOURCE_IDS_STRING, required = true) String resourceIdsString,
                                          @Param(value = Inputs.CustomInputs.KEY_TAGS_STRING, required = true) String keyTagsString,
                                          @Param(value = Inputs.CustomInputs.VALUE_TAGS_STRING, required = true) String valueTagsString) {
        try {
            CommonInputs commonInputs = new CommonInputs.Builder()
                    .withEndpoint(endpoint)
                    .withIdentity(identity)
                    .withCredential(credential)
                    .withProxyHost(proxyHost)
                    .withProxyPort(proxyPort)
                    .withProxyUsername(proxyUsername)
                    .withProxyPassword(proxyPassword)
                    .withHeaders(headers)
                    .withQueryParams(queryParams)
                    .withVersion(version)
                    .withDelimiter(delimiter)
                    .withAction(Constants.QueryApiActions.CREATE_TAGS)
                    .withApiService(Constants.Apis.AMAZON_EC2_API)
                    .withRequestUri(Constants.Miscellaneous.EMPTY)
                    .withRequestPayload(Constants.Miscellaneous.EMPTY)
                    .withHttpClientMethod(Constants.AwsParams.HTTP_CLIENT_METHOD_GET)
                    .build();

            CustomInputs customInputs = new CustomInputs.Builder()
                    .withKeyTagsString(keyTagsString)
                    .withValueTagsString(valueTagsString)
                    .withResourceIdsString(resourceIdsString)
                    .build();

            return new QueryApiExecutor().execute(commonInputs, customInputs);
        } catch (Exception e) {
            return ExceptionProcessor.getExceptionResult(e);
        }
    }
}