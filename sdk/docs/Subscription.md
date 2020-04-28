# Subscription

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**name** | **String** |  |  [optional]
**publisherId** | **String** |  |  [optional]
**offerId** | **String** |  |  [optional]
**planId** | **String** |  |  [optional]
**quantity** | **Integer** |  |  [optional]
**sessionMode** | [**SessionModeEnum**](#SessionModeEnum) | Dry Run indicates all transactions run as Test-Mode in the commerce stack |  [optional]
**isFreeTrial** | **Boolean** | true - the customer subscription is currently in free trial, false - the customer subscription is not currently in free trial.(optional field - default false) |  [optional]
**isTest** | **Boolean** | Indicating whether the current subscription is a test asset. |  [optional]
**sandboxType** | [**SandboxTypeEnum**](#SandboxTypeEnum) | Possible Values are None, Csp (Csp sandbox purchase) |  [optional]
**saasSubscriptionStatus** | [**SaasSubscriptionStatusEnum**](#SaasSubscriptionStatusEnum) | Indicates the status of the operation. |  [optional]
**beneficiary** | [**AadIdentifier**](AadIdentifier.md) |  |  [optional]
**purchaser** | [**AadIdentifier**](AadIdentifier.md) |  |  [optional]
**term** | [**SubscriptionTerm**](SubscriptionTerm.md) |  |  [optional]
**allowedCustomerOperations** | [**List&lt;AllowedCustomerOperationsEnum&gt;**](#List&lt;AllowedCustomerOperationsEnum&gt;) |  |  [optional]

<a name="SessionModeEnum"></a>
## Enum: SessionModeEnum
Name | Value
---- | -----
NONE | &quot;None&quot;
DRYRUN | &quot;DryRun&quot;

<a name="SandboxTypeEnum"></a>
## Enum: SandboxTypeEnum
Name | Value
---- | -----
NONE | &quot;None&quot;
CSP | &quot;Csp&quot;

<a name="SaasSubscriptionStatusEnum"></a>
## Enum: SaasSubscriptionStatusEnum
Name | Value
---- | -----
NOTSTARTED | &quot;NotStarted&quot;
PENDINGFULFILLMENTSTART | &quot;PendingFulfillmentStart&quot;
SUBSCRIBED | &quot;Subscribed&quot;
SUSPENDED | &quot;Suspended&quot;
UNSUBSCRIBED | &quot;Unsubscribed&quot;

<a name="List<AllowedCustomerOperationsEnum>"></a>
## Enum: List&lt;AllowedCustomerOperationsEnum&gt;
Name | Value
---- | -----
READ | &quot;Read&quot;
UPDATE | &quot;Update&quot;
DELETE | &quot;Delete&quot;
