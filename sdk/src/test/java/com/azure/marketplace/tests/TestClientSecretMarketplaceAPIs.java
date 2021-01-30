/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 */
package com.azure.marketplace.tests;

import com.microsoft.marketplace.ClientSecretTokenProvider;
import com.microsoft.marketplace.ClientSecretTokenProviderSettings;
import com.microsoft.marketplace.MarketplaceClient;
import com.microsoft.marketplace.saas.SaaSAPI;
import com.microsoft.marketplace.saas.models.Subscription;
import com.microsoft.marketplace.saas.models.SubscriptionPlans;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class TestClientSecretMarketplaceAPIs {
    private SaaSAPI client;
    private UUID subscriptionId;

    void initClient(){
        String tenantId = System.getenv("AAD_TENANT_ID");
        String clientId = System.getenv("AAD_APP_CLIENT_ID");
        String clientSecret = System.getenv("AAD_APP_CLIENT_SECRET");

        if (StringUtils.isEmpty(clientId) || StringUtils.isEmpty(clientSecret)){
            throw new IllegalStateException("AAD_TENANT_ID, AAD_APP_CLIENT_ID, and AAD_APP_CLIENT_SECRET must be defined as environment variables.");
        }

        ClientSecretTokenProviderSettings settings = new ClientSecretTokenProviderSettings();
        settings.setTenantId(UUID.fromString(tenantId));
        settings.setClientId(UUID.fromString(clientId));
        settings.setClientSecret(clientSecret);
        ClientSecretTokenProvider credentials = new ClientSecretTokenProvider(settings);
        this.client = MarketplaceClient.ApiClient(credentials);
    }

    @Before
    public void setup(){
        initClient();
        Subscription subscription = this.client.getFulfillmentOperations().listSubscriptionsAsync(null,
                UUID.randomUUID(), UUID.randomUUID()).blockFirst();

        if (null == subscription) {
            throw new AssumptionViolatedException("No subscriptions are active for this login. Giving up.");
        }

        this.subscriptionId = subscription.getId();
    }

    @Test
    public void getSubscriptionTest() {
        Subscription response = this.client.getFulfillmentOperations().getSubscriptionAsync(this.subscriptionId,
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assert.assertEquals(this.subscriptionId, response.getId());
    }

    @Test
    public void listAvailablePlansTest() {
        SubscriptionPlans response = this.client.getFulfillmentOperations().listAvailablePlansAsync(this.subscriptionId,
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assert.assertFalse("No plans returned.", response.getPlans().isEmpty());
    }

    @Test
    public void listSubscriptionsTest() {
        Subscription subscription = this.client.getFulfillmentOperations().listSubscriptionsAsync(null,
                UUID.randomUUID(), UUID.randomUUID()).blockFirst();

        Assert.assertNotNull("No subscriptions available for this account. Try another.", subscription);
    }
}