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
import com.microsoft.marketplace.saas.models.Plan;
import com.microsoft.marketplace.saas.models.ResolvedSubscription;
import com.microsoft.marketplace.saas.models.SubscriberPlan;
import com.microsoft.marketplace.saas.models.Subscription;
import com.microsoft.marketplace.saas.models.SubscriptionPlans;
import com.microsoft.marketplace.saas.models.SubscriptionStatusEnum;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.PreconditionViolationException;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

public class TestClientSecretMarketplaceAPIs {

    private static UUID subscriptionId;

    private static SaaSAPI saasApiClient;
    private static MeteringAPI meteringAPIClient;
    
    private String marketplacePurchaseIdentificationToken;

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
        // Subscription subscription = saasApiClient.getFulfillmentOperations().listSubscriptionsAsync(null,
        //         UUID.randomUUID(), UUID.randomUUID()).blockFirst();

        // if (null == subscription) {
        //     throw new PreconditionViolationException("No subscriptions are active for this login. Giving up.");
        // }

        // subscriptionId = subscription.getId();
        //marketplacePurchaseIdentificationToken = "IPZARpJbiIC3rDAESnmm2Zg1GJdHYHshWjLOvr/g+ASyLao9rPVrQ+mu2Li6uOBdtf7v/vXH6v+FdBT8egYh0Pmrv/kLoIbf/MuINnMLi+4IW2UFhR2qsrxRbEBfxYthSjKIrk28TgeZp19rDcNV1GqSXqR49Pma9i9EkPj6E8OTd71WWrJHG/4j+GOtm3Q1sFoBIzslczlC+BX89bcH8/a3zbir8fy+rDl576YKdE8=";
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



    // not sure how to handle the token here - ask julio 
    // @Test
    // public void resolveSubscriptionTest(){
    //     ResolvedSubscription resolvedSubscription = saasApiClient.getFulfillmentOperations().resolveAsync(marketplacePurchaseIdentificationToken, 
    //             UUID.randomUUID(), UUID.randomUUID()).block();
    //     Assertions.assertNotNull("No resolved subscription returned.", resolvedSubscription);
    // }

    // cannot imitate dotnet test because nothing returned from updateSubscriptionAsync
    // @Test
    // public void updateSubscriptionTest(){
    //     List<Subscription> subscriptions = saasApiClient.getFulfillmentOperations().listSubscriptionsAsync(null,
    //             UUID.randomUUID(), UUID.randomUUID()).buffer().blockFirst();
        
    //     Assertions.assertNotNull(subscriptions);
    //     Assertions.assertFalse(subscriptions.isEmpty());
    //     Subscription firstActiveSubscription = subscriptions.stream().filter(s -> s.getSaasSubscriptionStatus() == SubscriptionStatusEnum.SUBSCRIBED).findFirst().orElse(null);

    //     String plan = firstActiveSubscription.getPlanId() == "silver" ? "gold" : "silver";

    //     SubscriberPlan subPlan = new SubscriberPlan();
    //     subPlan.setPlanId(plan);

    //     saasApiClient.getFulfillmentOperations().updateSubscriptionAsync(firstActiveSubscription.getId(), 
    //             subPlan, UUID.randomUUID(), UUID.randomUUID()).block();

    //     Subscription updatedSubscription = saasApiClient.getFulfillmentOperations().getSubscriptionAsync(firstActiveSubscription.getId(), 
    //             UUID.randomUUID(), UUID.randomUUID()).block();
    //     Assertions.assertEquals(updatedSubscription.getPlanId(), plan);

    // }

    // Delete not working currently
    // @Test
    // public void deleteSubscriptionTest() {
    //     List<Subscription> subscriptions = saasApiClient.getFulfillmentOperations().listSubscriptionsAsync(null,
    //             UUID.randomUUID(), UUID.randomUUID()).buffer().blockFirst();

    //     Assertions.assertNotNull(subscriptions);
    //     Assertions.assertFalse(subscriptions.isEmpty());

    //     int intSubCount = subscriptions.size();
    //     Subscription firstActiveSubscription = subscriptions.get(0);
    //     saasApiClient.getFulfillmentOperations().deleteSubscriptionAsync(firstActiveSubscription.getId(), UUID.randomUUID(), UUID.randomUUID());

    //     List<Subscription> updateSubscriptions = saasApiClient.getFulfillmentOperations().listSubscriptionsAsync(null,
    //             UUID.randomUUID(), UUID.randomUUID()).buffer().blockFirst();

    //     Assertions.assertNotEquals(updateSubscriptions.size(), intSubCount);
    // }

    @Test
    public void listPlansTest(){
        List<Subscription> subscriptions = saasApiClient.getFulfillmentOperations().listSubscriptionsAsync(null,
                UUID.randomUUID(), UUID.randomUUID()).buffer().blockFirst();

        Assertions.assertNotNull(subscriptions);
        Assertions.assertFalse(subscriptions.isEmpty());
        
        Subscription firstSubscription = subscriptions.get(0);

        SubscriptionPlans plans = saasApiClient.getFulfillmentOperations().listAvailablePlansAsync(firstSubscription.getId(), UUID.randomUUID(), UUID.randomUUID()).block();

        Assertions.assertNotNull(plans);
        Assertions.assertFalse(plans.getPlans().isEmpty());
    }

    
    // @Test
    // public void activateSubscriptionTest(){
    //     List<Subscription> subscriptions = saasApiClient.getFulfillmentOperations().listSubscriptionsAsync(null,
    //                 UUID.randomUUID(), UUID.randomUUID()).buffer().blockFirst();
            
    //     Assertions.assertNotNull(subscriptions);
    //     Assertions.assertFalse(subscriptions.isEmpty());
    //     Subscription firstInactiveSubscription = subscriptions.stream().filter(s -> s.getSaasSubscriptionStatus() == SubscriptionStatusEnum.PENDING_FULFILLMENT_START).findFirst().orElse(null);
    //     SubscriberPlan subPlan = new SubscriberPlan();
    //     subPlan.setPlanId("gold");
    //     saasApiClient.getFulfillmentOperations().activateSubscriptionAsync(firstInactiveSubscription.getId(), subPlan, UUID.randomUUID(), UUID.randomUUID());
    //     Subscription updatedSubscription = saasApiClient.getFulfillmentOperations().getSubscriptionAsync(firstInactiveSubscription.getId(), 
    //             UUID.randomUUID(), UUID.randomUUID()).block();
    //     Assertions.assertNotEquals(updatedSubscription.getSaasSubscriptionStatus(), SubscriptionStatusEnum.PENDING_FULFILLMENT_START);
    // }

    // SubscriptionOperations tests
    @Test
    public void listOperationsTest(){
        OperationList operationList = saasApiClient.getSubscriptionOperations().listOperationsAsync(subscriptionId, 
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertNotNull(operationList.getOperations());
    }

    // how do I get the operationid? 
    // getoperationstatus


    // updateoperationstatus

    // MeteringOperations tests
    // @Test
    // public void postSingleUsageWithResourceUriTest(){
        

    // }

    //postUsageEventAsync - resource uri
    //postUsageEventAsync - resource id

    //postBatchUsageEventAsync
}