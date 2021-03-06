package io.cloudslang.content.jclouds.services.impl;

import com.google.common.collect.Multimap;
import io.cloudslang.content.jclouds.entities.aws.InstanceType;
import io.cloudslang.content.jclouds.entities.constants.Constants;
import io.cloudslang.content.jclouds.entities.inputs.CommonInputs;
import io.cloudslang.content.jclouds.entities.inputs.InstanceInputs;
import io.cloudslang.content.jclouds.services.ComputeService;
import io.cloudslang.content.jclouds.services.JCloudsService;
import io.cloudslang.content.jclouds.services.helpers.AmazonComputeServiceHelper;
import io.cloudslang.content.jclouds.services.helpers.FiltersHelper;
import io.cloudslang.content.jclouds.utils.InputsUtil;
import org.jclouds.ContextBuilder;
import org.jclouds.ec2.EC2Api;
import org.jclouds.ec2.domain.InstanceState;
import org.jclouds.ec2.domain.InstanceStateChange;
import org.jclouds.ec2.domain.Reservation;
import org.jclouds.ec2.domain.RunningInstance;
import org.jclouds.ec2.features.InstanceApi;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by persdana on 5/27/2015.
 */
public class AmazonComputeServiceImpl extends JCloudsService implements ComputeService {
    private static final String INSTANCE_SUCCESSFULLY_UPDATED = "Instance successfully updated.";

    EC2Api ec2Api = null;

    protected String region;

    public AmazonComputeServiceImpl(String endpoint, String identity, String credential, String proxyHost, String proxyPort) {
        super(endpoint, identity, credential, proxyHost, proxyPort);
    }

    protected void init(boolean isDebugMode) {
        ContextBuilder contextBuilder = super.init(region, Constants.Apis.AMAZON_EC2_API, isDebugMode);
        ec2Api = new FiltersHelper().getEC2Api(contextBuilder);
    }

    @Override
    public Set<String> describeInstancesInRegion(CommonInputs commonInputs, InstanceInputs instanceInputs) {
        InstanceApi instanceApi = getEC2InstanceApi(instanceInputs.getCustomInputs().getRegion(),
                commonInputs.isDebugMode(), true);

        Multimap<String, String> filtersMap = new AmazonComputeServiceHelper()
                .getInstanceFiltersMap(instanceInputs, commonInputs.getDelimiter());

        Set<? extends Reservation<? extends RunningInstance>> instancesInRegion;
        Set<String> nodesSet = new HashSet<>();

        if (filtersMap.isEmpty()) {
            instancesInRegion = instanceApi.describeInstancesInRegion(instanceInputs.getCustomInputs().getRegion());
            populateNodesSet(instancesInRegion, nodesSet);

            return nodesSet;
        }

        instancesInRegion = instanceApi.describeInstancesInRegionWithFilter(instanceInputs.getCustomInputs().getRegion(), filtersMap);
        populateNodesSet(instancesInRegion, nodesSet);

        return nodesSet;
    }

    @Override
    public Set<String> describeRegions(boolean isDebugMode) {
        init(isDebugMode);
        return ec2Api.getConfiguredRegions();
    }

    @Override
    public String updateInstanceType(String region, String serverId, String instanceType, long checkStateTimeout,
                                     long polingInterval, boolean isDebugMode) throws Exception {
        InstanceApi instanceApi = getEC2InstanceApi(region, isDebugMode, false);

        AmazonComputeServiceHelper helper = new AmazonComputeServiceHelper();
        InstanceState previousState = helper.getInstanceState(instanceApi, region, serverId);
        helper.stopAndWaitToStopInstance(instanceApi, previousState, region, serverId, checkStateTimeout, polingInterval);

        if (Constants.Miscellaneous.NOT_RELEVANT.equalsIgnoreCase(instanceType)) {
            instanceType = InstanceType.T2_MICRO.getValue();
        }

        instanceApi.setInstanceTypeForInstanceInRegion(region, serverId, instanceType);

        if (InstanceState.RUNNING.equals(previousState)) {
            Set<? extends InstanceStateChange> instanceChanged = instanceApi.startInstancesInRegion(region, serverId);
            return instanceChanged.toString();
        }

        return INSTANCE_SUCCESSFULLY_UPDATED;
    }

    void lazyInit(String region, boolean isDebugMode) {
        this.region = InputsUtil.getDefaultStringInput(region, Constants.AwsParams.DEFAULT_AMAZON_REGION);
        init(isDebugMode);
    }

    private InstanceApi getEC2InstanceApi(String region, boolean isDebugMode, boolean isForRegion) {
        lazyInit(region, isDebugMode);
        return isForRegion ? ec2Api.getInstanceApiForRegion(region).get() : ec2Api.getInstanceApi().get();
    }

    private void populateNodesSet(Set<? extends Reservation<? extends RunningInstance>> instancesInRegion, Set<String> nodesSet) {
        for (Reservation<? extends RunningInstance> reservation : instancesInRegion) {
            nodesSet.add(reservation.toString());
        }
    }
}
