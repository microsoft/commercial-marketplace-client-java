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
import com.microsoft.marketplace.meter.MeteringAPI;
import com.microsoft.marketplace.saas.SaaSAPI;
import com.microsoft.marketplace.saas.models.OperationList;
import com.microsoft.marketplace.saas.models.Subscription;
import com.microsoft.marketplace.saas.models.SubscriptionPlans;
import com.microsoft.marketplace.saas.models.SubscriptionStatusEnum;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.PreconditionViolationException;

import java.util.List;
import java.util.UUID;

public class TestClientSecretMarketplaceAPIs {

    private static UUID subscriptionId;

    private static SaaSAPI saasApiClient;
    private static MeteringAPI meteringAPIClient;

    static void initClient(){
        String tenantId = System.getenv("AAD_TENANT_ID");
        String clientId = System.getenv("AAD_APP_CLIENT_ID");
        String clientSecret = System.getenv("AAD_APP_CLIENT_SECRET");

        if (StringUtils.isEmpty(tenantId)){
            throw new IllegalStateException("AAD_TENANT_ID must be defined as an environment variable.");
        }
        
        if (StringUtils.isEmpty(clientId)){
            throw new IllegalStateException("AAD_APP_CLIENT_ID must be defined as an environment variable.");
        }

        if (StringUtils.isEmpty(clientSecret)){
            throw new IllegalStateException("AAD_APP_CLIENT_SECRET must be defined as an environment variable.");
        }

        ClientSecretTokenProviderSettings settings = new ClientSecretTokenProviderSettings();
        settings.setTenantId(UUID.fromString(tenantId));
        settings.setClientId(UUID.fromString(clientId));
        settings.setClientSecret(clientSecret);
        ClientSecretTokenProvider credentials = new ClientSecretTokenProvider(settings);
        saasApiClient = MarketplaceClient.SaasApiClient(credentials);
        meteringAPIClient = MarketplaceClient.MeteringAPIClient(credentials);
    }


    @BeforeAll
    public static void setup(){
        initClient();
        Subscription subscription = saasApiClient.getFulfillmentOperations().listSubscriptionsAsync(null,
                UUID.randomUUID(), UUID.randomUUID()).filter(s -> s.getSaasSubscriptionStatus() == SubscriptionStatusEnum.SUBSCRIBED).blockFirst();
        if (null == subscription) {
            throw new PreconditionViolationException("No subscriptions are active for this login. Giving up.");
        }
        subscriptionId = subscription.getId();
    }

    @Test
    public void getSubscriptionTest() {
        Subscription response = saasApiClient.getFulfillmentOperations().getSubscriptionAsync(subscriptionId,
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertEquals(subscriptionId, response.getId());
    }

    @Test
    public void listAvailablePlansTest() {
        SubscriptionPlans response = saasApiClient.getFulfillmentOperations().listAvailablePlansAsync(subscriptionId,
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertFalse(response.getPlans().isEmpty());
    }

    @Test
    public void listSubscriptionsTest() {
        Subscription subscription = saasApiClient.getFulfillmentOperations().listSubscriptionsAsync(null,
                UUID.randomUUID(), UUID.randomUUID()).blockFirst();
        Assertions.assertNotNull(subscription);
    }

    @Test
    public void listSubscriptionsPagedTest() {
        String continuationToken = null;
        List<Subscription> subscriptions = saasApiClient.getFulfillmentOperations().listSubscriptionsSinglePageAsync(continuationToken,
                UUID.randomUUID(), UUID.randomUUID()).block().getValue();
        Assertions.assertNotNull(subscriptions);
    }

    @Test
    public void listPlansTest(){
        List<Subscription> subscriptions = saasApiClient.getFulfillmentOperations().listSubscriptionsAsync(null,
                UUID.randomUUID(), UUID.randomUUID()).filter(s -> s.getSaasSubscriptionStatus() == SubscriptionStatusEnum.SUBSCRIBED).buffer().blockFirst();

        Assertions.assertNotNull(subscriptions);
        Assertions.assertFalse(subscriptions.isEmpty());
        
        Subscription firstSubscription = subscriptions.get(0);

        SubscriptionPlans plans = saasApiClient.getFulfillmentOperations().listAvailablePlansAsync(firstSubscription.getId(), UUID.randomUUID(), UUID.randomUUID()).block();

        Assertions.assertNotNull(plans);
        Assertions.assertFalse(plans.getPlans().isEmpty());
    }

    // SubscriptionOperations tests
    @Test
    public void listOperationsTest(){
        OperationList operationList = saasApiClient.getSubscriptionOperations().listOperationsAsync(subscriptionId, 
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertNotNull(operationList.getOperations());
    }
}