package io.cloudslang.content.jclouds.services;

import io.cloudslang.content.jclouds.entities.inputs.CommonInputs;
import io.cloudslang.content.jclouds.entities.inputs.InstanceInputs;

import java.util.Set;

/**
 * Created by persdana on 5/27/2015.
 */
public interface ComputeService {
    Set<String> describeRegions(boolean isDebugMode);

    Set<String> describeInstancesInRegion(CommonInputs commonInputs, InstanceInputs instanceInputs) throws Exception;

    String updateInstanceType(String region, String instanceId, String instanceType, long checkStateTimeout,
                              long polingInterval, boolean isDebugMode) throws Exception;
}