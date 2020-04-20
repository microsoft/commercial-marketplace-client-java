# ErrorErrorAdditionalInfo

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**usageEventId** | [**UUID**](UUID.md) | Unique identifier associated with the usage event |  [optional]
**status** | [**StatusEnum**](#StatusEnum) | Accepted|NotProcessed|Expired |  [optional]
**messageTime** | [**OffsetDateTime**](OffsetDateTime.md) | Time this message was created in UTC |  [optional]
**resourceId** | [**UUID**](UUID.md) | Identifier of the resource against which usage is emitted |  [optional]
**quantity** | **Long** |  |  [optional]
**dimension** | **String** | Dimension identifier |  [optional]
**effectiveStartTime** | [**OffsetDateTime**](OffsetDateTime.md) | Time in UTC when the usage event occurred |  [optional]
**planId** | [**UUID**](UUID.md) | Plan associated with the purchased offer |  [optional]

<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
ACCEPTED | &quot;Accepted&quot;
NOTPROCESSED | &quot;NotProcessed&quot;
EXPIRED | &quot;Expired&quot;
