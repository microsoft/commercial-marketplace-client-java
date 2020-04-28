# UsageEventOkResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**usageEventId** | [**UUID**](UUID.md) | Unique identifier associated with the usage event |  [optional]
**status** | [**StatusEnum**](#StatusEnum) | Status of the operation. |  [optional]
**messageTime** | [**OffsetDateTime**](OffsetDateTime.md) | Time this message was created in UTC |  [optional]
**resourceId** | [**UUID**](UUID.md) | Identifier of the resource against which usage is emitted |  [optional]
**quantity** | **Long** | Number of units consumed |  [optional]
**dimension** | **String** | Dimension identifier |  [optional]
**effectiveStartTime** | [**OffsetDateTime**](OffsetDateTime.md) | Time in UTC when the usage event occurred |  [optional]
**planId** | **String** | Plan associated with the purchased offer |  [optional]
**error** | [**Error**](Error.md) |  |  [optional]

<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
ACCEPTED | &quot;Accepted&quot;
EXPIRED | &quot;Expired&quot;
DUPLICATE | &quot;Duplicate&quot;
ERROR | &quot;Error&quot;
RESOURCENOTFOUND | &quot;ResourceNotFound&quot;
RESOURCENOTAUTHORIZED | &quot;ResourceNotAuthorized&quot;
INVALIDDIMENSION_BADARGUMENT | &quot;InvalidDimension|BadArgument&quot;
