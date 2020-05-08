// Copyright (c) Microsoft Corporation.
// Licensed under the MIT license.

package com.azure.marketplace.tests;

import com.azure.marketplace.*;
import com.azure.marketplace.auth.OAuth;
import com.azure.marketplace.generated.SaasFulfillmentApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.fail;

/**
 * API tests for SaasFulfillmentApi
 */
public class SaasFulfillmentApiTest {

    private SaasFulfillmentApi api;
    private MarketplaceSettingsProvider marketplaceSettingsProvider;
    private static final String JsonContent = "application/json";
    private static final UUID DefaultSubscription = UUID.fromString("0aa95e32-8be7-4e5e-94f9-563f6d7d9dcd");
    private static final UUID OperationSubscription = UUID.fromString("37f9dea2-4345-438f-b0bd-03d40d28c7e0");
    @Before
    public void before() throws Exception {
        marketplaceSettingsProvider = new MarketplaceSettingsProvider();
        // Authenticate and store the authentication with the Api.
        Configuration.initDefaultApiClient(marketplaceSettingsProvider.getAadTenantId(),
                marketplaceSettingsProvider.getAadAppClientId(),
                marketplaceSettingsProvider.getAadAppClientSecret());

        api = new SaasFulfillmentApi(Configuration.getDefaultApiClient());
    }

    @Test
    public void checkAccessToken() {
        ApiClient client = Configuration.getDefaultApiClient();
        OAuth auth= (OAuth)client.getAuthentication("OAuth2");
        String accessToken = auth.getAccessToken();
        Assert.assertFalse("Failed to authenticate. Check that the tenant, client, and secret are correct.",
                accessToken.substring(0, 5).equalsIgnoreCase("error"));
    }

    /**
     * Activate a subscription
     *
     * Use this call to activate a subscription.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void activateSubscriptionTest() throws ApiException {
        SubscriberPlan body = new SubscriberPlan();
        //body.setQuantity(100l);
        body.setPlanId("Basic");
        body.setQuantity(100l);
        String contentType = JsonContent;
        ApiVersion apiVersion = ApiVersion._09_15;
        UUID subscriptionId = DefaultSubscription;
        UUID xMsRequestid = UUID.randomUUID();
        UUID xMsCorrelationid = UUID.randomUUID();

        try {
            api.activateSubscription(body, contentType, apiVersion, subscriptionId, xMsRequestid, xMsCorrelationid);
        } catch(Exception ex){
            fail(String.format("Test should activate the subscription with nothing more than a 200 response. Failure: %s",
                    ex.toString()));
        }
    }

    /**
     * Delete a subscription
     *
     * Unsubscribe and delete the specified subscription.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteSubscriptionTest() throws ApiException {
        UUID subscriptionId = DefaultSubscription;
        ApiVersion apiVersion = ApiVersion._09_15;
        String contentType = JsonContent;
        UUID xMsRequestid = UUID.randomUUID();
        UUID xMsCorrelationid = UUID.randomUUID();

        try {
            api.deleteSubscription(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid);
        } catch(Exception ex){
            fail(String.format("Delete subscription failed. %s", ex.toString()));
        }
        // TODO: test validations
    }
    /**
     * Get operation status
     *
     * Enables the publisher to track the status of the specified triggered async operation (such as Subscribe, Unsubscribe, ChangePlan, or ChangeQuantity).
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getOperationStatusTest() throws ApiException {
        UUID subscriptionId = DefaultSubscription;
        UUID operationId = UUID.fromString("ea0b5d24-112b-4fc0-a6fc-44f3b8119458");
        ApiVersion apiVersion = ApiVersion._09_15;
        String contentType = JsonContent;
        UUID xMsRequestid = UUID.randomUUID();
        UUID xMsCorrelationid = UUID.randomUUID();
        OperationType response;
        try {
            response = api.getOperationStatus(subscriptionId, operationId, apiVersion, contentType, xMsRequestid, xMsCorrelationid);
        } catch (Exception ex) {
            fail(String.format("Exception was caught: %s", ex.toString()));
        }
    }

    /**
     * Get subscription
     *
     * Gets the specified SaaS subscription. Use this call to get license information and plan information.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getSubscriptionTest() throws ApiException {
        UUID subscriptionId = DefaultSubscription;
        ApiVersion apiVersion = ApiVersion._09_15;
        String contentType = JsonContent;
        UUID xMsRequestid = UUID.randomUUID();
        UUID xMsCorrelationid = UUID.randomUUID();

        try {
            Subscription response = api.getSubscription(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid);
        }
        catch(Exception ex) {
            fail(String.format("Failed test. %s", ex.toString()));
        }

    }
    /**
     * List available plans
     *
     * Use this call to find out if there are any private or public offers for the current publisher.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void listAvailablePlansTest() throws ApiException {
        UUID subscriptionId = OperationSubscription;
        ApiVersion apiVersion = ApiVersion._09_15;
        String contentType = JsonContent;
        UUID xMsRequestid = UUID.randomUUID();
        UUID xMsCorrelationid = UUID.randomUUID();

        try {
            PlansResponse response = api.listAvailablePlans(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid);
        } catch(Exception ex){
            fail(String.format("listAvailablePlansTest subscription failed. %s", ex.toString()));
        }
    }
    /**
     * List outstanding operations
     *
     * Lists the outstanding operations for the current publisher.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void listOperationsTest() throws ApiException {
        UUID subscriptionId = DefaultSubscription;
        ApiVersion apiVersion = ApiVersion._09_15;
        String contentType = JsonContent;
        UUID xMsRequestid = UUID.randomUUID();
        UUID xMsCorrelationid = UUID.randomUUID();

        try {
            OperationList response = api.listOperations(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid);
        } catch(Exception ex){
            fail(String.format("listOperationsTest failed. %s", ex.toString()));
        }
    }
    /**
     * List subscriptions
     *
     * Lists all the SaaS subscriptions for a publisher.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void listSubscriptionsTest() throws ApiException {
        ApiVersion apiVersion = ApiVersion._08_31;
        String contentType = JsonContent;
        UUID xMsRequestid = UUID.randomUUID();
        UUID xMsCorrelationid = UUID.randomUUID();
        SubscriptionsResponse response = api.listSubscriptions(apiVersion, contentType, null, xMsRequestid, xMsCorrelationid);

        String next = response.getAtNextLink();
        while (next != null && !next.isEmpty()){
            String continuationToken = SubscriptionResponseHelper.ExtractContinuationToken(next);
            response = api.listSubscriptions(apiVersion, contentType, continuationToken, xMsRequestid, xMsCorrelationid);
            next = response.getAtNextLink();
        }
    }
    /**
     * Patch a subscription
     *
     * Use this call to update the plan, the user count (quantity), or both.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void patchSubscriptionTest() throws ApiException {
        SubscriberPlan body = new SubscriberPlan();
        String contentType = JsonContent;
        ApiVersion apiVersion = ApiVersion._09_15;
        UUID subscriptionId = DefaultSubscription;
        UUID xMsRequestid = UUID.randomUUID();
        UUID xMsCorrelationid = UUID.randomUUID();

        body.setPlanId("Premium");

        try {
            api.patchSubscription(body, contentType, apiVersion, subscriptionId, xMsRequestid, xMsCorrelationid);
        } catch(Exception ex){
            fail(String.format("patchSubscription failed. %s", ex.toString()));
        }
    }


    //https://portal.seelyinc.com/index.html?token=7lbAkybVZD%2BjIkSrUB8f7nz3yE%2Fq5OfcJ67HZdnuHZBC1QSEl2L%2BeSlpcqQ%2BSSd7oQPI0jcW9CEJFBpq%2BryIxcySrVpEUwxGtEOEI8528ofgscw2hsZsf0O2VJ8GOpxT322Saf7KddVFI7IuamyQ5g1iS6Zi19JwzP5jqY6%2BaE8BdBfVZSHRaeah8qcN70sC7Q9BKzu4o66Wxfd6Y97A7TKHFSP%2FLaNX%2B8VKPYSwqK%2F1i9kFeugI37r9H6V0YTq2PMkwf301EE1AKRBvOkKaEQ%3D%3D
    /**
     * Resolve a subscription
     *
     * The resolve endpoint enables the publisher to resolve a marketplace token to a persistent resource ID. The resource ID is the unique identifier for a SaaS subscription. When a user is redirected to a partner&#x27;s website, the URL contains a token in the query parameters. The partner is expected to use this token and make a request to resolve it. The response contains the unique SaaS subscription ID, name, offer ID, and plan for the resource. This token is valid for one hour only.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void resolveTest() throws ApiException {
        ApiVersion apiVersion = ApiVersion._09_15;
        String contentType = JsonContent;
        UUID xMsRequestid = UUID.randomUUID();
        UUID xMsCorrelationid = UUID.randomUUID();
        String xMsMarketplaceToken = "bogus";

        try {
            SubscriptionSummary response = api.resolve(apiVersion, contentType, xMsMarketplaceToken, xMsRequestid, xMsCorrelationid);
        } catch(Exception ex){
            fail(String.format("Resolve token failed. %s", ex.toString()));
        }
    }
    /**
     * Update the status of an operation
     *
     * Update the status of an operation to indicate success or failure with the provided values.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateOperationStatusTest() throws ApiException {
        UpdateOperation body = new UpdateOperation();
        String contentType = JsonContent;
        ApiVersion apiVersion = ApiVersion._09_15;
        UUID subscriptionId = OperationSubscription;
        UUID operationId = UUID.fromString("74dfb4db-c193-4891-827d-eb05fbdc64b0");
        UUID xMsRequestid = UUID.randomUUID();
        UUID xMsCorrelationid = UUID.randomUUID();

        body.setPlanId("Platinum");
        body.setQuantity(15l);
        body.setStatus(UpdateOperation.StatusEnum.SUCCESS);
        try {
            api.updateOperationStatus(body, contentType, apiVersion, subscriptionId, operationId, xMsRequestid, xMsCorrelationid);
        } catch(Exception ex){
            fail(String.format("Resolve token failed. %s", ex.toString()));
        }
    }
}
