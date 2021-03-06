package io.cloudslang.content.jclouds.entities.inputs;

import io.cloudslang.content.jclouds.entities.aws.InstanceInitiatedShutdownBehavior;
import io.cloudslang.content.jclouds.entities.aws.InstanceState;
import io.cloudslang.content.jclouds.entities.aws.MonitoringState;
import io.cloudslang.content.jclouds.entities.aws.Tenancy;
import io.cloudslang.content.jclouds.utils.InputsUtil;

/**
 * Created by Mihai Tusa.
 * 6/1/2016.
 */
public class InstanceInputs {
    private static final long DEFAULT_CHECK_STATE_TIMEOUT = 20000;
    private static final long DEFAULT_POLING_INTERVAL = 20000;

    private CustomInputs customInputs;
    private NetworkInputs networkInputs;

    private String affinity;
    private String clientToken;
    private String dnsName;
    private String groupName;
    private String iamArn;
    private String instanceLifecycle;
    private String instanceStateCode;
    private String instanceStateName;
    private String instanceGroupId;
    private String instanceGroupName;
    private String ipAddress;
    private String keyName;
    private String launchIndex;
    private String launchTime;
    private String monitoringState;
    private String placementGroupName;
    private String privateDnsName;
    private String privateIpAddress;
    private String reason;
    private String requesterId;
    private String sourceDestinationCheck;
    private String spotInstanceRequestId;
    private String tenancy;
    private String publicIp;
    private String ipOwnerId;
    private String instanceInitiatedShutdownBehavior;
    private String userData;

    private int minCount;
    private int maxCount;
    private long checkStateTimeout;
    private long polingInterval;

    private boolean disableApiTermination;
    private boolean monitoring;
    private boolean forceStop;

    private InstanceInputs(Builder builder) {
        this.customInputs = builder.customInputs;
        this.networkInputs = builder.networkInputs;

        this.affinity = builder.affinity;
        this.clientToken = builder.clientToken;
        this.dnsName = builder.dnsName;
        this.groupName = builder.groupName;
        this.iamArn = builder.iamArn;
        this.instanceLifecycle = builder.instanceLifecycle;
        this.instanceStateCode = builder.instanceStateCode;
        this.instanceStateName = builder.instanceStateName;
        this.instanceGroupId = builder.instanceGroupId;
        this.instanceGroupName = builder.instanceGroupName;
        this.ipAddress = builder.ipAddress;
        this.keyName = builder.keyName;
        this.launchIndex = builder.launchIndex;
        this.launchTime = builder.launchTime;
        this.monitoringState = builder.monitoringState;
        this.placementGroupName = builder.placementGroupName;
        this.privateDnsName = builder.privateDnsName;
        this.privateIpAddress = builder.privateIpAddress;
        this.reason = builder.reason;
        this.requesterId = builder.requesterId;
        this.sourceDestinationCheck = builder.sourceDestinationCheck;
        this.spotInstanceRequestId = builder.spotInstanceRequestId;
        this.tenancy = builder.tenancy;
        this.publicIp = builder.publicIp;
        this.ipOwnerId = builder.ipOwnerId;
        this.instanceInitiatedShutdownBehavior = builder.instanceInitiatedShutdownBehavior;
        this.userData = builder.userData;

        this.minCount = builder.minCount;
        this.maxCount = builder.maxCount;
        this.checkStateTimeout = builder.checkStateTimeout;
        this.polingInterval = builder.polingInterval;

        this.disableApiTermination = builder.disableApiTermination;
        this.monitoring = builder.monitoring;
        this.forceStop = builder.forceStop;
    }

    public CustomInputs getCustomInputs() {
        return customInputs;
    }

    public NetworkInputs getNetworkInputs() {
        return networkInputs;
    }

    public String getAffinity() {
        return affinity;
    }

    public String getClientToken() {
        return clientToken;
    }

    public String getDnsName() {
        return dnsName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getIamArn() {
        return iamArn;
    }

    public String getInstanceLifecycle() {
        return instanceLifecycle;
    }

    public String getInstanceStateCode() {
        return instanceStateCode;
    }

    public String getInstanceStateName() {
        return instanceStateName;
    }

    public String getInstanceGroupId() {
        return instanceGroupId;
    }

    public String getInstanceGroupName() {
        return instanceGroupName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getLaunchIndex() {
        return launchIndex;
    }

    public String getLaunchTime() {
        return launchTime;
    }

    public String getMonitoringState() {
        return monitoringState;
    }

    public String getPlacementGroupName() {
        return placementGroupName;
    }

    public String getPrivateDnsName() {
        return privateDnsName;
    }

    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    public String getReason() {
        return reason;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public String getSourceDestinationCheck() {
        return sourceDestinationCheck;
    }

    public String getSpotInstanceRequestId() {
        return spotInstanceRequestId;
    }

    public String getTenancy() {
        return tenancy;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public String getIpOwnerId() {
        return ipOwnerId;
    }

    public String getInstanceInitiatedShutdownBehavior() {
        return instanceInitiatedShutdownBehavior;
    }

    public String getUserData() {
        return userData;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public long getCheckStateTimeout() {
        return checkStateTimeout;
    }

    public long getPolingInterval() {
        return polingInterval;
    }

    public boolean isDisableApiTermination() {
        return disableApiTermination;
    }

    public boolean isMonitoring() {
        return monitoring;
    }

    public boolean isForceStop() {
        return forceStop;
    }

    public static class Builder {
        private CustomInputs customInputs;
        private NetworkInputs networkInputs;

        private String affinity;
        private String clientToken;
        private String dnsName;
        private String groupName;
        private String iamArn;
        private String instanceLifecycle;
        private String instanceStateCode;
        private String instanceStateName;
        private String instanceGroupId;
        private String instanceGroupName;
        private String ipAddress;
        private String keyName;
        private String launchIndex;
        private String launchTime;
        private String monitoringState;
        private String placementGroupName;
        private String privateDnsName;
        private String privateIpAddress;
        private String reason;
        private String requesterId;
        private String sourceDestinationCheck;
        private String spotInstanceRequestId;
        private String tenancy;
        private String publicIp;
        private String ipOwnerId;
        private String instanceInitiatedShutdownBehavior;
        private String userData;

        private int minCount;
        private int maxCount;
        private long checkStateTimeout;
        private long polingInterval;

        private boolean disableApiTermination;
        private boolean monitoring;
        private boolean forceStop;

        public InstanceInputs build() {
            return new InstanceInputs(this);
        }

        public Builder withCustomInputs(CustomInputs inputs) {
            customInputs = inputs;
            return this;
        }

        public Builder withNetworkInputs(NetworkInputs inputs) {
            networkInputs = inputs;
            return this;
        }

        public Builder withAffinity(String inputValue) {
            affinity = inputValue;
            return this;
        }

        public Builder withClientToken(String inputValue) {
            clientToken = inputValue;
            return this;
        }

        public Builder withDnsName(String inputValue) {
            dnsName = inputValue;
            return this;
        }

        public Builder withGroupName(String inputValue) {
            groupName = inputValue;
            return this;
        }

        public Builder withIamArn(String inputValue) {
            iamArn = inputValue;
            return this;
        }

        public Builder withInstanceLifecycle(String inputValue) {
            instanceLifecycle = inputValue;
            return this;
        }

        public Builder withInstanceStateCode(String inputValue) {
            instanceStateCode = String.valueOf(InputsUtil.getValidInstanceStateCode(inputValue));
            return this;
        }

        public Builder withInstanceStateName(String inputValue) {
            instanceStateName = InstanceState.getValue(inputValue);
            return this;
        }

        public Builder withInstanceGroupId(String inputValue) {
            instanceGroupId = inputValue;
            return this;
        }

        public Builder withInstanceGroupName(String inputValue) {
            instanceGroupName = inputValue;
            return this;
        }

        public Builder withIpAddress(String inputValue) {
            ipAddress = inputValue;
            return this;
        }

        public Builder withKeyName(String inputValue) {
            keyName = inputValue;
            return this;
        }

        public Builder withLaunchIndex(String inputValue) {
            launchIndex = inputValue;
            return this;
        }

        public Builder withLaunchTime(String inputValue) {
            launchTime = inputValue;
            return this;
        }

        public Builder withMonitoringState(String inputValue) throws Exception {
            monitoringState = MonitoringState.getValue(inputValue);
            return this;
        }

        public Builder withPlacementGroupName(String inputValue) {
            placementGroupName = inputValue;
            return this;
        }

        public Builder withPrivateDnsName(String inputValue) {
            privateDnsName = inputValue;
            return this;
        }

        public Builder withPrivateIpAddress(String inputValue) {
            privateIpAddress = inputValue;
            return this;
        }

        public Builder withReason(String inputValue) {
            reason = inputValue;
            return this;
        }

        public Builder withRequesterId(String inputValue) {
            requesterId = inputValue;
            return this;
        }

        public Builder withSourceDestinationCheck(String inputValue) {
            sourceDestinationCheck = InputsUtil.getRelevantBooleanString(inputValue);
            return this;
        }

        public Builder withSpotInstanceRequestId(String inputValue) {
            spotInstanceRequestId = inputValue;
            return this;
        }

        public Builder withTenancy(String inputValue) throws Exception {
            tenancy = Tenancy.getValue(inputValue);
            return this;
        }

        public Builder withPublicIp(String inputValue) {
            publicIp = inputValue;
            return this;
        }

        public Builder withIpOwnerId(String inputValue) {
            ipOwnerId = inputValue;
            return this;
        }

        public Builder withInstanceInitiatedShutdownBehavior(String inputValue) throws Exception {
            instanceInitiatedShutdownBehavior = InstanceInitiatedShutdownBehavior.getValue(inputValue);
            return this;
        }

        public Builder withUserData(String inputValue) {
            userData = inputValue;
            return this;
        }

        public Builder withMinCount(String inputValue) {
            minCount = InputsUtil.getValidInstancesCount(inputValue);
            return this;
        }

        public Builder withMaxCount(String inputValue) {
            maxCount = InputsUtil.getValidInstancesCount(inputValue);
            return this;
        }

        public Builder withCheckStateTimeout(String inputValue) {
            checkStateTimeout = InputsUtil.getValidLong(inputValue, DEFAULT_CHECK_STATE_TIMEOUT);
            return this;
        }

        public Builder withPolingInterval(String inputValue) {
            polingInterval = InputsUtil.getValidLong(inputValue, DEFAULT_POLING_INTERVAL);
            return this;
        }

        public Builder withDisableApiTermination(String inputValue) {
            disableApiTermination = Boolean.parseBoolean(inputValue);
            return this;
        }

        public Builder withMonitoring(String inputValue) {
            monitoring = InputsUtil.getEnforcedBooleanCondition(inputValue, false);
            return this;
        }

        public Builder withForceStop(String inputValue) {
            forceStop = InputsUtil.getEnforcedBooleanCondition(inputValue, false);
            return this;
        }
    }
}
