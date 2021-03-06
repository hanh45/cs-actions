package io.cloudslang.content.jclouds.actions.snapshots;

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
 * 6/28/2016.
 */
public class CreateSnapshotAction {
    /**
     * Creates a snapshot of an EBS volume and stores it in Amazon S3. You can use snapshots for backups, to make copies
     * of EBS volumes, and to save data before shutting down an instance.
     * Note: When a snapshot is created, any AWS Marketplace product codes that are associated with the source volume are
     * propagated to the snapshot. You can take a snapshot of an attached volume that is in use. However, snapshots only
     * capture data that has been written to your EBS volume at the time the snapshot command is issued; this may exclude
     * any data that has been cached by any applications or the operating system. If you can pause any file systems on the
     * volume long enough to take a snapshot, your snapshot should be complete. However, if you cannot pause all file writes
     * to the volume, you should un-mount the volume from within the instance, issue the snapshot command, and then remount
     * the volume to ensure a consistent and complete snapshot. You may remount and use your volume while the snapshot
     * status is pending. To create a snapshot for EBS volumes that serve as root devices, you should stop the instance
     * before taking the snapshot. Snapshots that are taken from encrypted volumes are automatically encrypted. Volumes
     * that are created from encrypted snapshots are also automatically encrypted. Your encrypted volumes and any associated
     * snapshots always remain protected. For more information, see Amazon Elastic Block Store and Amazon EBS Encryption
     * in the Amazon Elastic Compute Cloud User Guide.
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
     *                      Example: "2016-09-15"
     * @param volumeId      ID of the EBS volume to be deleted.
     * @return A map with strings as keys and strings as values that contains: outcome of the action, returnCode of the
     * operation, or failure message and the exception if there is one
     */
    @Action(name = "Create Snapshot",
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
    public Map<String, String> createSnapshot(@Param(value = Inputs.CommonInputs.ENDPOINT, required = true) String endpoint,
                                              @Param(value = Inputs.CommonInputs.IDENTITY) String identity,
                                              @Param(value = Inputs.CommonInputs.CREDENTIAL, encrypted = true) String credential,
                                              @Param(value = Inputs.CommonInputs.PROXY_HOST) String proxyHost,
                                              @Param(value = Inputs.CommonInputs.PROXY_PORT) String proxyPort,
                                              @Param(value = Inputs.CommonInputs.PROXY_USERNAME) String proxyUsername,
                                              @Param(value = Inputs.CommonInputs.PROXY_PASSWORD, encrypted = true) String proxyPassword,
                                              @Param(value = Inputs.CommonInputs.HEADERS) String headers,
                                              @Param(value = Inputs.CommonInputs.QUERY_PARAMS) String queryParams,
                                              @Param(value = Inputs.CommonInputs.VERSION, required = true) String version,

                                              @Param(value = Inputs.CustomInputs.VOLUME_ID, required = true) String volumeId,

                                              @Param(value = Inputs.VolumeInputs.SNAPSHOT_DESCRIPTION) String snapshotDescription) {
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
                    .withAction(Constants.QueryApiActions.CREATE_SNAPSHOT)
                    .withApiService(Constants.Apis.AMAZON_EC2_API)
                    .withRequestUri(Constants.Miscellaneous.EMPTY)
                    .withRequestPayload(Constants.Miscellaneous.EMPTY)
                    .withHttpClientMethod(Constants.AwsParams.HTTP_CLIENT_METHOD_GET)
                    .build();

            CustomInputs customInputs = new CustomInputs.Builder().withVolumeId(volumeId).build();
            VolumeInputs volumeInputs = new VolumeInputs.Builder().withDescription(snapshotDescription).build();

            return new QueryApiExecutor().execute(commonInputs, customInputs, volumeInputs);
        } catch (Exception e) {
            return ExceptionProcessor.getExceptionResult(e);
        }
    }
}