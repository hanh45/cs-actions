package io.cloudslang.content.jclouds.factory.helpers;

import io.cloudslang.content.jclouds.entities.constants.Constants;
import io.cloudslang.content.jclouds.entities.inputs.InputsWrapper;
import io.cloudslang.content.jclouds.utils.InputsUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mihai Tusa.
 * 9/9/2016.
 */
public class VolumeUtils {
    private static final String AVAILABILITY_ZONE = "AvailabilityZone";
    private static final String DEVICE = "Device";
    private static final String KMS_KEY_ID = "KmsKeyId";
    private static final String SIZE = "Size";

    public Map<String, String> getAttachVolumeQueryParamsMap(InputsWrapper wrapper) {
        return getAttachDetachVolumeCommonQueryParamsMap(wrapper);
    }

    public Map<String, String> getCreateVolumeQueryParamsMap(InputsWrapper wrapper) {
        Map<String, String> queryParamsMap = new HashMap<>();
        InputsUtil.setCommonQueryParamsMap(queryParamsMap, wrapper.getCommonInputs().getAction(),
                wrapper.getCommonInputs().getVersion());
        queryParamsMap.put(AVAILABILITY_ZONE, wrapper.getCustomInputs().getAvailabilityZone());

        String volumeType = Constants.Miscellaneous.NOT_RELEVANT.equals(wrapper.getCustomInputs().getVolumeType()) ?
                Constants.AwsParams.STANDARD : wrapper.getCustomInputs().getVolumeType();
        queryParamsMap.put(Constants.AwsParams.VOLUME_TYPE, volumeType);

        InputsUtil.setOptionalMapEntry(queryParamsMap, KMS_KEY_ID, wrapper.getCustomInputs().getKmsKeyId(),
                StringUtils.isNotBlank(wrapper.getCustomInputs().getKmsKeyId()));
        InputsUtil.setOptionalMapEntry(queryParamsMap, SIZE, wrapper.getVolumeInputs().getSize(),
                StringUtils.isNotBlank(wrapper.getVolumeInputs().getSize()));
        InputsUtil.setOptionalMapEntry(queryParamsMap, Constants.AwsParams.SNAPSHOT_ID, wrapper.getVolumeInputs().getSnapshotId(),
                StringUtils.isNotBlank(wrapper.getVolumeInputs().getSnapshotId()));
        InputsUtil.setOptionalMapEntry(queryParamsMap, Constants.AwsParams.ENCRYPTED, String.valueOf(Constants.Values.ONE),
                wrapper.getVolumeInputs().isEncrypted());
        InputsUtil.setOptionalMapEntry(queryParamsMap, Constants.AwsParams.IOPS, wrapper.getVolumeInputs().getIops(),
                !Constants.Miscellaneous.NOT_RELEVANT.equals(wrapper.getVolumeInputs().getIops()));

        return queryParamsMap;
    }

    public Map<String, String> getDetachVolumeQueryParamsMap(InputsWrapper wrapper) {
        Map<String, String> queryParamsMap = getAttachDetachVolumeCommonQueryParamsMap(wrapper);
        InputsUtil.setOptionalMapEntry(queryParamsMap, Constants.AwsParams.FORCE, String.valueOf(wrapper.getVolumeInputs().isForce()),
                wrapper.getVolumeInputs().isForce());

        return queryParamsMap;
    }

    public Map<String, String> getDeleteVolumeQueryParamsMap(InputsWrapper wrapper) {
        Map<String, String> queryParamsMap = new HashMap<>();
        InputsUtil.setCommonQueryParamsMap(queryParamsMap, wrapper.getCommonInputs().getAction(),
                wrapper.getCommonInputs().getVersion());
        queryParamsMap.put(Constants.AwsParams.VOLUME_ID, wrapper.getCustomInputs().getVolumeId());

        return queryParamsMap;
    }

    private Map<String, String> getAttachDetachVolumeCommonQueryParamsMap(InputsWrapper wrapper) {
        Map<String, String> queryParamsMap = new HashMap<>();
        InputsUtil.setCommonQueryParamsMap(queryParamsMap, wrapper.getCommonInputs().getAction(),
                wrapper.getCommonInputs().getVersion());
        queryParamsMap.put(DEVICE, wrapper.getVolumeInputs().getDeviceName());
        queryParamsMap.put(Constants.AwsParams.INSTANCE_ID, wrapper.getCustomInputs().getInstanceId());
        queryParamsMap.put(Constants.AwsParams.VOLUME_ID, wrapper.getCustomInputs().getVolumeId());

        return queryParamsMap;
    }
}