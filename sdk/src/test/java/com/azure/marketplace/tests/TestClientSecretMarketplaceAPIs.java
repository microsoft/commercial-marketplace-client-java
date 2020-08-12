/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 **/
package com.azure.marketplace.tests;

import com.azure.marketplace.ClientSecretTokenProvider;
import com.azure.marketplace.ClientSecretTokenProviderSettings;
import com.azure.marketplace.MarketplaceClient;
import com.azure.marketplace.implementation.MarketplaceClientImpl;
import com.azure.marketplace.models.Operation;
import com.azure.marketplace.models.OperationList;
import com.azure.marketplace.models.Subscription;
import com.azure.marketplace.models.SubscriptionPlans;
import com.microsoft.azure.PagedList;
import org.apache.commons.lang3.StringUtils;
import org.junit.*;

import java.util.List;
import java.util.UUID;

public class TestClientSecretMarketplaceAPIs {
    private MarketplaceClient client;
    private UUID subscriptionId;

    void initClient(){
        String tenantId = System.getenv("AAD_TENANT_ID");
        String clientId = System.getenv("AAD_APP_CLIENT_ID");
        String clientSecret = System.getenv("AAD_APP_CLIENT_SECRET");

        if (StringUtils.isEmpty(clientId) || StringUtils.isEmpty(clientSecret)){
            throw new IllegalStateException("AAD_TENANT_ID, AAD_APP_CLIENT_ID, and AAD_APP_CLIENT_SECRET must be defined as environment variables.");
        }

        ClientSecretTokenProviderSettings settings = new ClientSecretTokenProviderSettings();
        settings.set_tenantId(UUID.fromString(tenantId));
        settings.set_clientId(UUID.fromString(clientId));
        settings.set_clientSecret(clientSecret);
        ClientSecretTokenProvider credentials = new ClientSecretTokenProvider(settings);
        this.client = new MarketplaceClientImpl(credentials);
    }

    @Before
    public void setup(){
        initClient();
        PagedList<Subscription> subscriptions = this.client.fulfillmentOperations().listSubscriptions();
        subscriptions.loadAll();
        if (subscriptions.isEmpty()) {
            throw new AssumptionViolatedException("No subscriptions are active for this login. Giving up.");
        }

        this.subscriptionId = subscriptions.get(0).id();
    }

    @Test
    @Ignore
    public void getOperationStatusTest() {
        PagedList<Subscription> subscriptions = this.client.fulfillmentOperations().listSubscriptions();
        if (subscriptions.isEmpty()) {
            Assert.fail("Test could not complete. No subscriptions in account. Consider adding subscriptions or disabling this test.");
            return;
        }

        subscriptions.loadAll();

        for (int i = 0; i < subscriptions.size(); ++i) {
            Subscription sub = subscriptions.get(i);
            OperationList operationList = this.client.subscriptionOperations().listOperations(sub.id());
            List<Operation> operations = operationList.operations();
            if (operations.isEmpty()) {
                continue;
            }

            Operation operation = operations.get(0);
            Operation operationStatus = this.client.subscriptionOperations().getOperationStatus(sub.id(), operation.id());
            Assert.assertEquals(operation.status(), operationStatus.status());
            return;
        }

        Assert.fail("Test could not complete. No operations against any subscription in account. " +
                "Consider adding an inflight operation or disabling this test.");
    }

    @Test
    public void getSubscriptionTest() {
        Subscription response = this.client.fulfillmentOperations().getSubscription(this.subscriptionId);
        Assert.assertEquals(this.subscriptionId, response.id());
    }

    @Test
    public void listAvailablePlansTest() {
        SubscriptionPlans response = this.client.fulfillmentOperations().listAvailablePlans(this.subscriptionId);
        Assert.assertFalse("No plans returned.", response.plans().isEmpty());
    }

    @Test
    public void listSubscriptionsTest() {
        PagedList<Subscription> subscriptions = this.client.fulfillmentOperations().listSubscriptions();
        subscriptions.loadAll();
        Assert.assertFalse("No subscriptions available for this account. Try another.", subscriptions.isEmpty());
    }
}
