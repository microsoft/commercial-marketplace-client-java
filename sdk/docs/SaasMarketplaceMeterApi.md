# SaasMarketplaceMeterApi

All URIs are relative to *https://marketplaceapi.microsoft.com/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**postBatchUsageEvent**](SaasMarketplaceMeterApi.md#postBatchUsageEvent) | **POST** /batchUsageEvent | Posts a set of usage events to the marketplace metering service API.
[**postUsageEvent**](SaasMarketplaceMeterApi.md#postUsageEvent) | **POST** /usageEvent | Posts a single usage event to the marketplace metering service API.

<a name="postBatchUsageEvent"></a>
# **postBatchUsageEvent**
> UsageEventOkResponse postBatchUsageEvent(body, apiVersion, xMsRequestid, xMsCorrelationid)

Posts a set of usage events to the marketplace metering service API.

The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch usage event request references the metering services dimension defined by the publisher when publishing the offer.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasMarketplaceMeterApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasMarketplaceMeterApi apiInstance = new SaasMarketplaceMeterApi();
List<UsageEvent> body = Arrays.asList(new UsageEvent()); // List<UsageEvent> | 
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    UsageEventOkResponse result = apiInstance.postBatchUsageEvent(body, apiVersion, xMsRequestid, xMsCorrelationid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasMarketplaceMeterApi#postBatchUsageEvent");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**List&lt;UsageEvent&gt;**](UsageEvent.md)|  |
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

[**UsageEventOkResponse**](UsageEventOkResponse.md)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="postUsageEvent"></a>
# **postUsageEvent**
> UsageEventOkResponse postUsageEvent(body, apiVersion, xMsRequestid, xMsCorrelationid)

Posts a single usage event to the marketplace metering service API.

Posts a single usage event to the marketplace metering service API.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasMarketplaceMeterApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasMarketplaceMeterApi apiInstance = new SaasMarketplaceMeterApi();
List<UsageEvent> body = Arrays.asList(new UsageEvent()); // List<UsageEvent> | 
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    UsageEventOkResponse result = apiInstance.postUsageEvent(body, apiVersion, xMsRequestid, xMsCorrelationid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasMarketplaceMeterApi#postUsageEvent");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**List&lt;UsageEvent&gt;**](UsageEvent.md)|  |
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

[**UsageEventOkResponse**](UsageEventOkResponse.md)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

