// Copyright (c) Microsoft Corporation.
// Licensed under the MIT license.

package com.azure.marketplace.tests;

import com.azure.marketplace.MarketplaceClient;
import com.azure.marketplace.models.Operation;
import com.azure.marketplace.models.OperationList;
import com.azure.marketplace.models.Subscription;
import com.azure.marketplace.models.SubscriptionPlans;
import com.microsoft.azure.PagedList;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MarketplaceClientCredentialConfig.class})
@ComponentScan(basePackages={"com.azure.spring.marketplace.implementation"})
@EnableAutoConfiguration
public class TestMarketplaceAPIs {
    @Autowired
    private MarketplaceClient _client;

    private UUID _subscriptionId;

    @Before
    public void setup(){
        PagedList<Subscription> subscriptions = _client.fulfillmentOperations().listSubscriptions();
        subscriptions.loadAll();
        if (subscriptions.isEmpty()) {
            throw new AssumptionViolatedException("No subscriptions are active for this login. Giving up.");
        }

        _subscriptionId = subscriptions.get(0).id();
    }

    @Test
    @Ignore
    public void getOperationStatusTest() {
        PagedList<Subscription> subscriptions = _client.fulfillmentOperations().listSubscriptions();
        if (subscriptions.isEmpty()) {
            Assert.fail("Test could not complete. No subscriptions in account. Consider adding subscriptions or disabling this test.");
            return;
        }

        subscriptions.loadAll();

        for (int i = 0; i < subscriptions.size(); ++i) {
            Subscription sub = subscriptions.get(i);
            OperationList operationList = _client.subscriptionOperations().listOperations(sub.id());
            List<Operation> operations = operationList.operations();
            if (operations.isEmpty()) {
                continue;
            }

            Operation operation = operations.get(0);
            Operation operationStatus = _client.subscriptionOperations().getOperationStatus(sub.id(), operation.id());
            Assert.assertEquals(operation.status(), operationStatus.status());
            return;
        }

        Assert.fail("Test could not complete. No operations against any subscription in account. " +
                "Consider adding an inflight operation or disabling this test.");
    }

    @Test
    public void getSubscriptionTest() {
        Subscription response = _client.fulfillmentOperations().getSubscription(_subscriptionId);
        Assert.assertEquals(_subscriptionId, response.id());
    }

    @Test
    public void listAvailablePlansTest() {
        SubscriptionPlans response = _client.fulfillmentOperations().listAvailablePlans(_subscriptionId);
        Assert.assertFalse("No plans returned.", response.plans().isEmpty());
    }

    @Test
    public void listSubscriptionsTest() {
        PagedList<Subscription> subscriptions = _client.fulfillmentOperations().listSubscriptions();
        subscriptions.loadAll();
        Assert.assertFalse("No subscriptions available for this account. Try another.", subscriptions.isEmpty());
    }
}
