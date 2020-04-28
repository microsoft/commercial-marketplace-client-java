# SaasFulfillmentApi

All URIs are relative to *https://marketplaceapi.microsoft.com/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**activateSubscription**](SaasFulfillmentApi.md#activateSubscription) | **POST** /saas/subscriptions/{subscriptionId}/activate | Activate a subscription
[**deleteSubscription**](SaasFulfillmentApi.md#deleteSubscription) | **DELETE** /saas/subscriptions/{subscriptionId} | Delete a subscription
[**getOperationStatus**](SaasFulfillmentApi.md#getOperationStatus) | **GET** /saas/subscriptions/{subscriptionId}/operations/{operationId} | Get operation status
[**getSubscription**](SaasFulfillmentApi.md#getSubscription) | **GET** /saas/subscriptions/{subscriptionId} | Get subscription
[**listAvailablePlans**](SaasFulfillmentApi.md#listAvailablePlans) | **GET** /saas/subscriptions/{subscriptionId}/listAvailablePlans | List available plans
[**listOperations**](SaasFulfillmentApi.md#listOperations) | **GET** /saas/subscriptions/{subscriptionId}/operations | List outstanding operations
[**listSubscriptions**](SaasFulfillmentApi.md#listSubscriptions) | **GET** /saas/subscriptions/ | List subscriptions
[**patchSubscription**](SaasFulfillmentApi.md#patchSubscription) | **PATCH** /saas/subscriptions/{subscriptionId} | Patch a subscription
[**resolve**](SaasFulfillmentApi.md#resolve) | **POST** /saas/subscriptions/resolve | Resolve a subscription
[**updateOperationStatus**](SaasFulfillmentApi.md#updateOperationStatus) | **PATCH** /saas/subscriptions/{subscriptionId}/operations/{operationId} | Update the status of an operation

<a name="activateSubscription"></a>
# **activateSubscription**
> activateSubscription(body, contentType, apiVersion, subscriptionId, xMsRequestid, xMsCorrelationid)

Activate a subscription

Use this call to activate a subscription.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasFulfillmentApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasFulfillmentApi apiInstance = new SaasFulfillmentApi();
SubscriberPlan body = new SubscriberPlan(); // SubscriberPlan | 
String contentType = "contentType_example"; // String | application/json
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
UUID subscriptionId = new UUID(); // UUID | 
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    apiInstance.activateSubscription(body, contentType, apiVersion, subscriptionId, xMsRequestid, xMsCorrelationid);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasFulfillmentApi#activateSubscription");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SubscriberPlan**](SubscriberPlan.md)|  |
 **contentType** | **String**| application/json | [enum: application/json]
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **subscriptionId** | [**UUID**](.md)|  |
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

null (empty response body)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteSubscription"></a>
# **deleteSubscription**
> deleteSubscription(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid)

Delete a subscription

Unsubscribe and delete the specified subscription.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasFulfillmentApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasFulfillmentApi apiInstance = new SaasFulfillmentApi();
UUID subscriptionId = new UUID(); // UUID | 
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
String contentType = "contentType_example"; // String | application/json
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    apiInstance.deleteSubscription(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasFulfillmentApi#deleteSubscription");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **subscriptionId** | [**UUID**](.md)|  |
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **contentType** | **String**| application/json | [enum: application/json]
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

null (empty response body)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getOperationStatus"></a>
# **getOperationStatus**
> OperationType getOperationStatus(subscriptionId, operationId, apiVersion, contentType, xMsRequestid, xMsCorrelationid)

Get operation status

Enables the publisher to track the status of the specified triggered async operation (such as Subscribe, Unsubscribe, ChangePlan, or ChangeQuantity).

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasFulfillmentApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasFulfillmentApi apiInstance = new SaasFulfillmentApi();
UUID subscriptionId = new UUID(); // UUID | 
UUID operationId = new UUID(); // UUID | 
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
String contentType = "contentType_example"; // String | application/json
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    OperationType result = apiInstance.getOperationStatus(subscriptionId, operationId, apiVersion, contentType, xMsRequestid, xMsCorrelationid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasFulfillmentApi#getOperationStatus");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **subscriptionId** | [**UUID**](.md)|  |
 **operationId** | [**UUID**](.md)|  |
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **contentType** | **String**| application/json | [enum: application/json]
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

[**OperationType**](OperationType.md)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getSubscription"></a>
# **getSubscription**
> Subscription getSubscription(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid)

Get subscription

Gets the specified SaaS subscription. Use this call to get license information and plan information.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasFulfillmentApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasFulfillmentApi apiInstance = new SaasFulfillmentApi();
UUID subscriptionId = new UUID(); // UUID | 
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
String contentType = "contentType_example"; // String | application/json
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    Subscription result = apiInstance.getSubscription(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasFulfillmentApi#getSubscription");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **subscriptionId** | [**UUID**](.md)|  |
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **contentType** | **String**| application/json | [enum: application/json]
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

[**Subscription**](Subscription.md)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listAvailablePlans"></a>
# **listAvailablePlans**
> PlansResponse listAvailablePlans(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid)

List available plans

Use this call to find out if there are any private or public offers for the current publisher.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasFulfillmentApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasFulfillmentApi apiInstance = new SaasFulfillmentApi();
UUID subscriptionId = new UUID(); // UUID | 
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
String contentType = "contentType_example"; // String | application/json
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    PlansResponse result = apiInstance.listAvailablePlans(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasFulfillmentApi#listAvailablePlans");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **subscriptionId** | [**UUID**](.md)|  |
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **contentType** | **String**| application/json | [enum: application/json]
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

[**PlansResponse**](PlansResponse.md)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listOperations"></a>
# **listOperations**
> OperationList listOperations(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid)

List outstanding operations

Lists the outstanding operations for the current publisher.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasFulfillmentApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasFulfillmentApi apiInstance = new SaasFulfillmentApi();
UUID subscriptionId = new UUID(); // UUID | 
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
String contentType = "contentType_example"; // String | application/json
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    OperationList result = apiInstance.listOperations(subscriptionId, apiVersion, contentType, xMsRequestid, xMsCorrelationid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasFulfillmentApi#listOperations");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **subscriptionId** | [**UUID**](.md)|  |
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **contentType** | **String**| application/json | [enum: application/json]
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

[**OperationList**](OperationList.md)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listSubscriptions"></a>
# **listSubscriptions**
> SubscriptionsResponse listSubscriptions(apiVersion, contentType, continuationToken, xMsRequestid, xMsCorrelationid)

List subscriptions

Lists all the SaaS subscriptions for a publisher.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasFulfillmentApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasFulfillmentApi apiInstance = new SaasFulfillmentApi();
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
String contentType = "contentType_example"; // String | application/json
String continuationToken = "continuationToken_example"; // String | Optional value, only used for ListSubscriptions.
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    SubscriptionsResponse result = apiInstance.listSubscriptions(apiVersion, contentType, continuationToken, xMsRequestid, xMsCorrelationid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasFulfillmentApi#listSubscriptions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **contentType** | **String**| application/json | [enum: application/json]
 **continuationToken** | **String**| Optional value, only used for ListSubscriptions. | [optional]
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

[**SubscriptionsResponse**](SubscriptionsResponse.md)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="patchSubscription"></a>
# **patchSubscription**
> patchSubscription(body, contentType, apiVersion, subscriptionId, xMsRequestid, xMsCorrelationid)

Patch a subscription

Use this call to update the plan, the user count (quantity), or both.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasFulfillmentApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasFulfillmentApi apiInstance = new SaasFulfillmentApi();
SubscriberPlan body = new SubscriberPlan(); // SubscriberPlan | 
String contentType = "contentType_example"; // String | application/json
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
UUID subscriptionId = new UUID(); // UUID | 
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    apiInstance.patchSubscription(body, contentType, apiVersion, subscriptionId, xMsRequestid, xMsCorrelationid);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasFulfillmentApi#patchSubscription");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SubscriberPlan**](SubscriberPlan.md)|  |
 **contentType** | **String**| application/json | [enum: application/json]
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **subscriptionId** | [**UUID**](.md)|  |
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

null (empty response body)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="resolve"></a>
# **resolve**
> SubscriptionSummary resolve(apiVersion, contentType, xMsMarketplaceToken, xMsRequestid, xMsCorrelationid)

Resolve a subscription

The resolve endpoint enables the publisher to resolve a marketplace token to a persistent resource ID. The resource ID is the unique identifier for a SaaS subscription. When a user is redirected to a partner&#x27;s website, the URL contains a token in the query parameters. The partner is expected to use this token and make a request to resolve it. The response contains the unique SaaS subscription ID, name, offer ID, and plan for the resource. This token is valid for one hour only.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasFulfillmentApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasFulfillmentApi apiInstance = new SaasFulfillmentApi();
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
String contentType = "contentType_example"; // String | application/json
String xMsMarketplaceToken = "xMsMarketplaceToken_example"; // String | The token query parameter in the URL when the user is redirected to the SaaS partner's website from Azure (for example,  https://contoso.com/signup?token=..). Note, The URL decodes the token value from the browser before using it.
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    SubscriptionSummary result = apiInstance.resolve(apiVersion, contentType, xMsMarketplaceToken, xMsRequestid, xMsCorrelationid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasFulfillmentApi#resolve");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **contentType** | **String**| application/json | [enum: application/json]
 **xMsMarketplaceToken** | **String**| The token query parameter in the URL when the user is redirected to the SaaS partner&#x27;s website from Azure (for example,  https://contoso.com/signup?token&#x3D;..). Note, The URL decodes the token value from the browser before using it. |
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

[**SubscriptionSummary**](SubscriptionSummary.md)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updateOperationStatus"></a>
# **updateOperationStatus**
> updateOperationStatus(body, contentType, apiVersion, subscriptionId, operationId, xMsRequestid, xMsCorrelationid)

Update the status of an operation

Update the status of an operation to indicate success or failure with the provided values.

### Example
```java
// Import classes:
//import com.microsoft.azure.marketplace.ApiClient;
//import com.microsoft.azure.marketplace.ApiException;
//import com.microsoft.azure.marketplace.Configuration;
//import com.microsoft.azure.marketplace.auth.*;
//import com.microsoft.azure.marketplace.generated.SaasFulfillmentApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: OAuth2
OAuth OAuth2 = (OAuth) defaultClient.getAuthentication("OAuth2");
OAuth2.setAccessToken("YOUR ACCESS TOKEN");

SaasFulfillmentApi apiInstance = new SaasFulfillmentApi();
UpdateOperation body = new UpdateOperation(); // UpdateOperation | 
String contentType = "contentType_example"; // String | application/json
ApiVersion apiVersion = new ApiVersion(); // ApiVersion | Version of the API.
UUID subscriptionId = new UUID(); // UUID | 
UUID operationId = new UUID(); // UUID | 
UUID xMsRequestid = new UUID(); // UUID | A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
UUID xMsCorrelationid = new UUID(); // UUID | A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
try {
    apiInstance.updateOperationStatus(body, contentType, apiVersion, subscriptionId, operationId, xMsRequestid, xMsCorrelationid);
} catch (ApiException e) {
    System.err.println("Exception when calling SaasFulfillmentApi#updateOperationStatus");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UpdateOperation**](UpdateOperation.md)|  |
 **contentType** | **String**| application/json | [enum: application/json]
 **apiVersion** | [**ApiVersion**](.md)| Version of the API. |
 **subscriptionId** | [**UUID**](.md)|  |
 **operationId** | [**UUID**](.md)|  |
 **xMsRequestid** | [**UUID**](.md)| A unique string value for tracking the request from the client, preferably a GUID. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]
 **xMsCorrelationid** | [**UUID**](.md)| A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn&#x27;t provided, one will be generated and provided in the response headers. | [optional]

### Return type

null (empty response body)

### Authorization

[OAuth2](../README.md#OAuth2)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

