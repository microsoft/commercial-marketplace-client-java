// Copyright (c) Microsoft Corporation.
// Licensed under the MIT license.

package com.azure.marketplace.tests;

import com.azure.marketplace.ApiException;
import com.azure.marketplace.ApiVersion;
import com.azure.marketplace.Error;
import java.util.UUID;
import com.azure.marketplace.UsageEvent;
import com.azure.marketplace.UsageEventOkResponse;
import com.azure.marketplace.api.SaasMarketplaceMeterApi;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for SaasMarketplaceMeterApi
 */
@Ignore
public class SaasMarketplaceMeterApiTest {

    private final SaasMarketplaceMeterApi api = new SaasMarketplaceMeterApi();

    /**
     * Posts a set of usage events to the marketplace metering service API.
     *
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch usage event request references the metering services dimension defined by the publisher when publishing the offer.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void postBatchUsageEventTest() throws ApiException {
        List<UsageEvent> body = null;
        String authorization = null;
        ApiVersion apiVersion = null;
        UUID xMsRequestid = null;
        UUID xMsCorrelationid = null;
        UsageEventOkResponse response = api.postBatchUsageEvent(body, apiVersion, xMsRequestid, xMsCorrelationid);

        // TODO: test validations
    }
    /**
     * Posts a single usage event to the marketplace metering service API.
     *
     * Posts a single usage event to the marketplace metering service API.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void postUsageEventTest() throws ApiException {
        List<UsageEvent> body = null;
        String authorization = null;
        ApiVersion apiVersion = null;
        UUID xMsRequestid = null;
        UUID xMsCorrelationid = null;
        UsageEventOkResponse response = api.postUsageEvent(body, apiVersion, xMsRequestid, xMsCorrelationid);

        // TODO: test validations
    }
}
