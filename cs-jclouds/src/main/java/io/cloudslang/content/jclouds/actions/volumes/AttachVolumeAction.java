package io.cloudslang.content.jclouds.actions.volumes;

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
import io.cloudslang.content.jclouds.entities.inputs.VolumeInputs;
import io.cloudslang.content.jclouds.execute.queries.QueryApiExecutor;
import io.cloudslang.content.jclouds.utils.ExceptionProcessor;

import java.util.Map;

/**
 * Created by Mihai Tusa.
 * 6/24/2016.
 */
public class AttachVolumeAction {
    /**
     * Attaches an EBS volume to a running or stopped instance and exposes it to the instance with the specified device
     * name.
     * Note: Encrypted EBS volumes may only be attached to instances that support Amazon EBS encryption. For more information,
     * see Amazon EBS Encryption in the Amazon Elastic Compute Cloud User Guide. For a list of supported device names, see
     * Attaching an EBS Volume to an Instance. Any device names that aren't reserved for instance store volumes can be used
     * for EBS volumes. For more information, see Amazon EC2 Instance Store in the Amazon Elastic Compute Cloud User Guide.
     * If a volume has an AWS Marketplace product code:
     * - The volume can be attached only to a stopped instance.
     * - AWS Marketplace product codes are copied from the volume to the instance.
     * - You must be subscribed to the product.
     * - The instance type and operating system of the instance must support the product. For example, you can't detach
     * a volume from a Windows instance and attach it to a Linux instance.
     * For more information about EBS volumes, see Attaching Amazon EBS Volumes in the Amazon Elastic Compute Cloud User
     * Guide.
     *
     * @param endpoint      Endpoint to which request will be sent.
     *                      Default: "https://ec2.amazonaws.com"
     * @param identity      ID of the secret access key associated with your Amazon AWS or IAM account.
     *                      Example: "AKIAIOSFODNN7EXAMPLE"
     * @param credential    Secret access key associated with your Amazon AWS or IAM account.
     *                      Example: "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"
     * @param proxyHost     Optional - proxy server used to connect to Amazon API. If empty no proxy will be used.
     *                      Default: ""
     * @param proxyPort     Optional - proxy server port. You must either specify values for both <proxyHost> and <proxyPort>
     *                      inputs or leave them both empty.
     *                      Default: ""
     * @param proxyUsername Optional - proxy server user name.
     *                      Default: ""
     * @param proxyPassword Optional - proxy server password associated with the <proxyUsername> input value.
     *                      Default: ""
     * @param headers       Optional - string containing the headers to use for the request separated by new line (CRLF).
     *                      The header name-value pair will be separated by ":".
     *                      Format: Conforming with HTTP standard for headers (RFC 2616)
     *                      Examples: "Accept:text/plain"
     *                      Default: ""
     * @param queryParams   Optional - string containing query parameters that will be appended to the URL. The names and
     *                      the values must not be URL encoded because if they are encoded then a double encoded will occur.
     *                      The separator between name-value pairs is "&" symbol. The query name will be separated from
     *                      query value by "=".
     *                      Examples: "parameterName1=parameterValue1&parameterName2=parameterValue2"
     *                      Default: ""
     * @param version       Version of the web service to made the call against it.
     *                      Example: "2016-04-01"
     * @param instanceId    ID of the instance.
     * @param volumeId      ID of the EBS volume. The volume and instance must be within the same Availability Zone.
     * @param deviceName    Device name to expose to the instance.
     *                      Example: "/dev/sdh", "xvdh"
     * @return A map with strings as keys and strings as values that contains: outcome of the action, returnCode of the
     * operation, or failure message and the exception if there is one
     */
    @Action(name = "Attach Volume",
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
    public Map<String, String> attachVolume(@Param(value = Inputs.CommonInputs.ENDPOINT, required = true) String endpoint,
                                            @Param(value = Inputs.CommonInputs.IDENTITY, required = true) String identity,
                                            @Param(value = Inputs.CommonInputs.CREDENTIAL, required = true, encrypted = true) String credential,
                                            @Param(value = Inputs.CommonInputs.PROXY_HOST) String proxyHost,
                                            @Param(value = Inputs.CommonInputs.PROXY_PORT) String proxyPort,
                                            @Param(value = Inputs.CommonInputs.PROXY_USERNAME) String proxyUsername,
                                            @Param(value = Inputs.CommonInputs.PROXY_PASSWORD, encrypted = true) String proxyPassword,
                                            @Param(value = Inputs.CommonInputs.HEADERS) String headers,
                                            @Param(value = Inputs.CommonInputs.QUERY_PARAMS) String queryParams,
                                            @Param(value = Inputs.CommonInputs.VERSION, required = true) String version,

                                            @Param(value = Inputs.CustomInputs.INSTANCE_ID, required = true) String instanceId,
                                            @Param(value = Inputs.CustomInputs.VOLUME_ID, required = true) String volumeId,

                                            @Param(value = Inputs.VolumeInputs.DEVICE_NAME, required = true) String deviceName) {
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
                    .withAction(Constants.QueryApiActions.ATTACH_VOLUME)
                    .withApiService(Constants.Apis.AMAZON_EC2_API)
                    .withRequestUri(Constants.Miscellaneous.EMPTY)
                    .withRequestPayload(Constants.Miscellaneous.EMPTY)
                    .withHttpClientMethod(Constants.AwsParams.HTTP_CLIENT_METHOD_GET)
                    .build();

            CustomInputs customInputs = new CustomInputs.Builder()
                    .withInstanceId(instanceId)
                    .withVolumeId(volumeId)
                    .build();

            VolumeInputs volumeInputs = new VolumeInputs.Builder()
                    .withDeviceName(deviceName)
                    .build();

            return new QueryApiExecutor().execute(commonInputs, customInputs, volumeInputs);
        } catch (Exception e) {
            return ExceptionProcessor.getExceptionResult(e);
        }
    }
}