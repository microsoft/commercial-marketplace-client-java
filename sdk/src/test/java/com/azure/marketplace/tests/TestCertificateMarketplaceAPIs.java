package com.azure.marketplace.tests;

import com.azure.marketplace.MarketplaceClient;
import com.azure.marketplace.models.Subscription;
import com.microsoft.azure.PagedList;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MarketplaceCertConfig.class})
@ComponentScan(basePackages={"com.azure.spring.marketplace.implementation"})
@EnableAutoConfiguration
public class TestCertificateMarketplaceAPIs {
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
    public void getSubscriptionTest() {
        Subscription response = _client.fulfillmentOperations().getSubscription(_subscriptionId);
        Assert.assertEquals(_subscriptionId, response.id());
    }
}
