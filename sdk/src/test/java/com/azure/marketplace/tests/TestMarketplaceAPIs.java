package com.azure.marketplace.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedResponse;
import com.microsoft.marketplace.meter.MeteringAPI;
import com.microsoft.marketplace.meter.MeteringOperations;
import com.microsoft.marketplace.meter.models.BatchUsageEvent;
import com.microsoft.marketplace.meter.models.BatchUsageEventOkResponse;
import com.microsoft.marketplace.meter.models.UsageBatchEventOkMessage;
import com.microsoft.marketplace.meter.models.UsageEvent;
import com.microsoft.marketplace.meter.models.UsageEventOkResponse;
import com.microsoft.marketplace.meter.models.UsageEventStatusEnum;
import com.microsoft.marketplace.saas.FulfillmentOperations;
import com.microsoft.marketplace.saas.SaaSAPI;
import com.microsoft.marketplace.saas.SubscriptionOperations;
import com.microsoft.marketplace.saas.models.Operation;
import com.microsoft.marketplace.saas.models.OperationList;
import com.microsoft.marketplace.saas.models.OperationStatusEnum;
import com.microsoft.marketplace.saas.models.Plan;
import com.microsoft.marketplace.saas.models.ResolvedSubscription;
import com.microsoft.marketplace.saas.models.SubscriberPlan;
import com.microsoft.marketplace.saas.models.Subscription;
import com.microsoft.marketplace.saas.models.SubscriptionPlans;
import com.microsoft.marketplace.saas.models.SubscriptionStatusEnum;
import com.microsoft.marketplace.saas.models.UpdateOperation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;

public class TestMarketplaceAPIs {

    private static UUID subscriptionId;
    private static SaaSAPI saasApiClient;
    private static MeteringAPI meteringAPIClient;
    private static String marketplacePurchaseIdentificationToken;
    private static BatchUsageEvent batchUsageEvent;
    private static UsageEvent usageEvent;
    private static SubscriptionOperations mockSubscriptionOperations;

    private static void initClient(){
        subscriptionId = UUID.randomUUID();
        marketplacePurchaseIdentificationToken = "testidentificationtoken";
        batchUsageEvent = new BatchUsageEvent();
        usageEvent = new UsageEvent();

        Subscription mockSub = mock(Subscription.class);
        when(mockSub.getId()).thenReturn(subscriptionId);
        when(mockSub.getPlanId()).thenReturn("silver", "gold");
        when(mockSub.getSaasSubscriptionStatus()).thenReturn(SubscriptionStatusEnum.PENDING_FULFILLMENT_START, SubscriptionStatusEnum.SUBSCRIBED);
        Mono<Subscription> mockSubMono = Mono.just(mockSub);

        Plan mockPlan = mock(Plan.class);
        List<Plan> mockPlanList = List.of(mockPlan);
        SubscriptionPlans mockSubPlans = mock(SubscriptionPlans.class);
        when(mockSubPlans.getPlans()).thenReturn(mockPlanList);
        Mono<SubscriptionPlans> mockSubPlansMono = Mono.just(mockSubPlans);

        PagedFlux<Subscription> mockPagedFlux = (PagedFlux<Subscription>) mock(PagedFlux.class);
        when(mockPagedFlux.blockFirst()).thenReturn(mockSub);

        List<Subscription> mockSubList = List.of(mockSub);
        List<Subscription> emptyMockSubList = List.of();
        PagedResponse<Subscription> mockSubPagedResponse = (PagedResponse<Subscription>) mock(PagedResponse.class);
        when(mockSubPagedResponse.getValue()).thenReturn(mockSubList, mockSubList, emptyMockSubList);
        Mono<PagedResponse<Subscription>> mockSubPagedResponseMono = Mono.just(mockSubPagedResponse);

        ResolvedSubscription mockResSub = mock(ResolvedSubscription.class);
        when(mockResSub.getSubscription()).thenReturn(mockSub);
        Mono<ResolvedSubscription> mockResSubMono = Mono.just(mockResSub);

        Mono<Void> mockVoidMono = Mono.just(mock(Void.class));

        FulfillmentOperations mockFullfillmentOperations = mock(FulfillmentOperations.class);
        when(mockFullfillmentOperations.getSubscriptionAsync(any(UUID.class), any(UUID.class), any(UUID.class))).thenReturn(mockSubMono);
        when(mockFullfillmentOperations.listAvailablePlansAsync(any(UUID.class), any(UUID.class), any(UUID.class))).thenReturn(mockSubPlansMono);
        when(mockFullfillmentOperations.listSubscriptionsAsync(any(String.class), any(UUID.class), any(UUID.class))).thenReturn(mockPagedFlux);
        when(mockFullfillmentOperations.listSubscriptionsSinglePageAsync(any(String.class), any(UUID.class), any(UUID.class))).thenReturn(mockSubPagedResponseMono);
        when(mockFullfillmentOperations.updateSubscriptionAsync(any(UUID.class), any(SubscriberPlan.class), any(UUID.class), any(UUID.class))).thenReturn(mockVoidMono);
        when(mockFullfillmentOperations.deleteSubscriptionAsync(any(UUID.class), any(UUID.class), any(UUID.class))).thenReturn(mockVoidMono);
        when(mockFullfillmentOperations.resolveAsync(any(String.class), any(UUID.class), any(UUID.class))).thenReturn(mockResSubMono);
        when(mockFullfillmentOperations.activateSubscriptionAsync(any(UUID.class), any(SubscriberPlan.class), any(UUID.class), any(UUID.class))).thenReturn(mockVoidMono);
        
        Operation mockOp = mock(Operation.class);
        when(mockOp.getStatus()).thenReturn(OperationStatusEnum.NOT_STARTED, OperationStatusEnum.IN_PROGRESS);
        List<Operation> opList = List.of(mockOp);
        OperationList mockOpList = mock(OperationList.class);
        when(mockOpList.getOperations()).thenReturn(opList);
        Mono<OperationList> mockOpListMono = Mono.just(mockOpList);

        Mono<Operation> mockOpMono = Mono.just(mockOp);

        mockSubscriptionOperations = mock(SubscriptionOperations.class);
        when(mockSubscriptionOperations.listOperationsAsync(any(UUID.class), any(UUID.class), any(UUID.class))).thenReturn(mockOpListMono);
        when(mockSubscriptionOperations.getOperationStatusAsync(any(UUID.class), any(UUID.class), any(UUID.class), any(UUID.class))).thenReturn(mockOpMono);
        when(mockSubscriptionOperations.updateOperationStatusAsync(any(UUID.class), any(UUID.class), any(UpdateOperation.class), any(UUID.class), any(UUID.class))).thenReturn(mockVoidMono);
 
        saasApiClient = mock(SaaSAPI.class);
        when(saasApiClient.getFulfillmentOperations()).thenReturn(mockFullfillmentOperations);
        when(saasApiClient.getSubscriptionOperations()).thenReturn(mockSubscriptionOperations);

        UsageBatchEventOkMessage mockBatchEvenOkMessage = mock(UsageBatchEventOkMessage.class);
        when(mockBatchEvenOkMessage.getStatus()).thenReturn(UsageEventStatusEnum.ACCEPTED);
        List<UsageBatchEventOkMessage> usageBatchEventOkMessageList = List.of(mockBatchEvenOkMessage);
        BatchUsageEventOkResponse mockBatchUsageEventOk = mock(BatchUsageEventOkResponse.class);
        when(mockBatchUsageEventOk.getResult()).thenReturn(usageBatchEventOkMessageList);
        Mono<BatchUsageEventOkResponse> mockBatchUsageEventOkMono = Mono.just(mockBatchUsageEventOk);

        UsageEventOkResponse mockUsageEventOk = mock(UsageEventOkResponse.class);
        when(mockUsageEventOk.getStatus()).thenReturn(UsageEventStatusEnum.ACCEPTED);
        Mono<UsageEventOkResponse> mockUsageEventOkMono = Mono.just(mockUsageEventOk);
        
        MeteringOperations mockMeteringOperations = mock(MeteringOperations.class);
        when(mockMeteringOperations.postBatchUsageEventAsync(any(BatchUsageEvent.class), any(UUID.class), any(UUID.class))).thenReturn(mockBatchUsageEventOkMono);
        when(mockMeteringOperations.postUsageEventAsync(any(UsageEvent.class), any(UUID.class), any(UUID.class))).thenReturn(mockUsageEventOkMono);

        meteringAPIClient = mock(MeteringAPI.class);
        when(meteringAPIClient.getMeteringOperations()).thenReturn(mockMeteringOperations);
    }

    @BeforeAll
    public static void setup(){
        initClient();
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
        Subscription subscription = saasApiClient.getFulfillmentOperations().listSubscriptionsAsync("fakeconttoken",
                UUID.randomUUID(), UUID.randomUUID()).blockFirst();
        Assertions.assertNotNull(subscription);
    }

    @Test
    public void listSubscriptionsPagedTest() {
        PagedResponse<Subscription> pagedResponse = saasApiClient.getFulfillmentOperations().listSubscriptionsSinglePageAsync("fakeconttoken",
                UUID.randomUUID(), UUID.randomUUID()).block();
        List<Subscription> subscriptions = pagedResponse.getValue();
        Assertions.assertNotNull(subscriptions);
    }

    @Test
    public void resolveSubscriptionTest(){
        ResolvedSubscription resolvedSubscription = saasApiClient.getFulfillmentOperations().resolveAsync(marketplacePurchaseIdentificationToken, 
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertEquals(subscriptionId, resolvedSubscription.getSubscription().getId());
    }

    @Test
    public void updateSubscriptionTest(){
        Subscription response = saasApiClient.getFulfillmentOperations().getSubscriptionAsync(subscriptionId,
                UUID.randomUUID(), UUID.randomUUID()).block();

        String plan = response.getPlanId() == "silver" ? "gold" : "silver";
        SubscriberPlan subPlan = new SubscriberPlan();
        subPlan.setPlanId(plan);

        saasApiClient.getFulfillmentOperations().updateSubscriptionAsync(subscriptionId, 
                subPlan, UUID.randomUUID(), UUID.randomUUID()).block();

        Subscription updatedSubscription = saasApiClient.getFulfillmentOperations().getSubscriptionAsync(subscriptionId, 
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertEquals(updatedSubscription.getPlanId(), plan);
    }

    @Test
    public void deleteSubscriptionTest() {
        PagedResponse<Subscription> pagedResponse = saasApiClient.getFulfillmentOperations().listSubscriptionsSinglePageAsync("fakeconttoken",
                UUID.randomUUID(), UUID.randomUUID()).block();
        List<Subscription> subscriptions = pagedResponse.getValue();
        Assertions.assertNotEquals(0, subscriptions.size());

        saasApiClient.getFulfillmentOperations().deleteSubscriptionAsync(subscriptionId, UUID.randomUUID(), UUID.randomUUID());

        PagedResponse<Subscription> updatedPagedResponse = saasApiClient.getFulfillmentOperations().listSubscriptionsSinglePageAsync("fakeconttoken",
                UUID.randomUUID(), UUID.randomUUID()).block();
        List<Subscription> updateSubscriptions = updatedPagedResponse.getValue();
        Assertions.assertEquals(0, updateSubscriptions.size());
    }

    @Test
    public void activateSubscriptionTest(){
        Subscription response = saasApiClient.getFulfillmentOperations().getSubscriptionAsync(subscriptionId,
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertEquals(SubscriptionStatusEnum.PENDING_FULFILLMENT_START, response.getSaasSubscriptionStatus());
                
        SubscriberPlan subPlan = new SubscriberPlan();
        subPlan.setPlanId("gold");

        saasApiClient.getFulfillmentOperations().activateSubscriptionAsync(subscriptionId, subPlan, UUID.randomUUID(), UUID.randomUUID());
        Subscription updatedSubscription = saasApiClient.getFulfillmentOperations().getSubscriptionAsync(subscriptionId, 
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertNotEquals(SubscriptionStatusEnum.PENDING_FULFILLMENT_START, updatedSubscription.getSaasSubscriptionStatus());
    }

    // SubscriptionOperations tests
    @Test
    public void listOperationsTest(){
        OperationList operationList = saasApiClient.getSubscriptionOperations().listOperationsAsync(subscriptionId, 
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertNotNull(operationList.getOperations());
    }

    @Test
    public void getOperationStatusTest(){
        Operation operation = saasApiClient.getSubscriptionOperations().getOperationStatusAsync(subscriptionId, 
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertNotNull(operation.getStatus());
    }

    @Test
    public void updateOperationStatusTest(){
        Operation operation = saasApiClient.getSubscriptionOperations().getOperationStatusAsync(subscriptionId, 
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()).block();
        OperationStatusEnum origStatus = operation.getStatus();

        saasApiClient.getSubscriptionOperations().updateOperationStatusAsync(subscriptionId, 
                UUID.randomUUID(), mock(UpdateOperation.class), UUID.randomUUID(), UUID.randomUUID()).block();
        verify(mockSubscriptionOperations).updateOperationStatusAsync(any(UUID.class), any(UUID.class), any(UpdateOperation.class), any(UUID.class), any(UUID.class));
        
        Operation updatedOperation = saasApiClient.getSubscriptionOperations().getOperationStatusAsync(subscriptionId, 
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertNotEquals(origStatus, updatedOperation.getStatus());
    }

    // MeteringOperations tests
    @Test
    public void postBatchUsageEventTest(){
        BatchUsageEventOkResponse batchUsageEventOkResponse = meteringAPIClient.getMeteringOperations().postBatchUsageEventAsync(batchUsageEvent, 
                UUID.randomUUID(), UUID.randomUUID()).block();
        List<UsageBatchEventOkMessage> usageBatchEventOkMsgList = batchUsageEventOkResponse.getResult();
        Assertions.assertNotNull(usageBatchEventOkMsgList);
        Assertions.assertEquals(UsageEventStatusEnum.ACCEPTED, usageBatchEventOkMsgList.get(0).getStatus());
    }

    @Test
    public void postUsageEventTest(){
        UsageEventOkResponse usageEventOkResponse = meteringAPIClient.getMeteringOperations().postUsageEventAsync(usageEvent, 
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assertions.assertEquals(UsageEventStatusEnum.ACCEPTED, usageEventOkResponse.getStatus());
    }
}
