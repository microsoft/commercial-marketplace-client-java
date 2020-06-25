/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.azure.marketplace.implementation;

import retrofit2.Retrofit;
import com.azure.marketplace.Meterings;
import com.azure.marketplace.models.UsageEvent;
import com.azure.marketplace.models.UsageEventOkResponse;
import com.google.common.reflect.TypeToken;
import com.microsoft.azure.CloudException;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import com.microsoft.rest.Validator;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in Meterings.
 */
public class MeteringsImpl implements Meterings {
    /** The Retrofit service to perform REST calls. */
    private MeteringsService service;
    /** The service client containing this operation class. */
    private MarketplaceClientImpl client;

    /**
     * Initializes an instance of MeteringsImpl.
     *
     * @param retrofit the Retrofit instance built from a Retrofit Builder.
     * @param client the instance of the service client containing this operation class.
     */
    public MeteringsImpl(Retrofit retrofit, MarketplaceClientImpl client) {
        this.service = retrofit.create(MeteringsService.class);
        this.client = client;
    }

    /**
     * The interface defining all the services for Meterings to be
     * used by Retrofit to perform actually REST calls.
     */
    interface MeteringsService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.azure.marketplace.Meterings postUsageEvent" })
        @POST("usageEvent")
        Observable<Response<ResponseBody>> postUsageEvent(@Body UsageEvent body, @Query("api-version") String apiVersion, @Header("x-ms-requestid") UUID requestId, @Header("x-ms-correlationid") UUID correlationId, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.azure.marketplace.Meterings postBatchUsageEvent" })
        @POST("batchUsageEvent")
        Observable<Response<ResponseBody>> postBatchUsageEvent(@Body List<UsageEvent> body, @Query("api-version") String apiVersion, @Header("x-ms-requestid") UUID requestId, @Header("x-ms-correlationid") UUID correlationId, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

    }

    /**
     * Posts a single usage event to the marketplace metering service API.
     * Posts a single usage event to the marketplace metering service API.
     *
     * @param body the UsageEvent value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws CloudException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the UsageEventOkResponse object if successful.
     */
    public UsageEventOkResponse postUsageEvent(UsageEvent body) {
        return postUsageEventWithServiceResponseAsync(body).toBlocking().single().body();
    }

    /**
     * Posts a single usage event to the marketplace metering service API.
     * Posts a single usage event to the marketplace metering service API.
     *
     * @param body the UsageEvent value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<UsageEventOkResponse> postUsageEventAsync(UsageEvent body, final ServiceCallback<UsageEventOkResponse> serviceCallback) {
        return ServiceFuture.fromResponse(postUsageEventWithServiceResponseAsync(body), serviceCallback);
    }

    /**
     * Posts a single usage event to the marketplace metering service API.
     * Posts a single usage event to the marketplace metering service API.
     *
     * @param body the UsageEvent value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the UsageEventOkResponse object
     */
    public Observable<UsageEventOkResponse> postUsageEventAsync(UsageEvent body) {
        return postUsageEventWithServiceResponseAsync(body).map(new Func1<ServiceResponse<UsageEventOkResponse>, UsageEventOkResponse>() {
            @Override
            public UsageEventOkResponse call(ServiceResponse<UsageEventOkResponse> response) {
                return response.body();
            }
        });
    }

    /**
     * Posts a single usage event to the marketplace metering service API.
     * Posts a single usage event to the marketplace metering service API.
     *
     * @param body the UsageEvent value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the UsageEventOkResponse object
     */
    public Observable<ServiceResponse<UsageEventOkResponse>> postUsageEventWithServiceResponseAsync(UsageEvent body) {
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        final String apiVersion = "2018-08-31";
        final UUID requestId = null;
        final UUID correlationId = null;
        return service.postUsageEvent(body, apiVersion, requestId, correlationId, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<UsageEventOkResponse>>>() {
                @Override
                public Observable<ServiceResponse<UsageEventOkResponse>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<UsageEventOkResponse> clientResponse = postUsageEventDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    /**
     * Posts a single usage event to the marketplace metering service API.
     * Posts a single usage event to the marketplace metering service API.
     *
     * @param body the UsageEvent value
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws CloudException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the UsageEventOkResponse object if successful.
     */
    public UsageEventOkResponse postUsageEvent(UsageEvent body, UUID requestId, UUID correlationId) {
        return postUsageEventWithServiceResponseAsync(body, requestId, correlationId).toBlocking().single().body();
    }

    /**
     * Posts a single usage event to the marketplace metering service API.
     * Posts a single usage event to the marketplace metering service API.
     *
     * @param body the UsageEvent value
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<UsageEventOkResponse> postUsageEventAsync(UsageEvent body, UUID requestId, UUID correlationId, final ServiceCallback<UsageEventOkResponse> serviceCallback) {
        return ServiceFuture.fromResponse(postUsageEventWithServiceResponseAsync(body, requestId, correlationId), serviceCallback);
    }

    /**
     * Posts a single usage event to the marketplace metering service API.
     * Posts a single usage event to the marketplace metering service API.
     *
     * @param body the UsageEvent value
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the UsageEventOkResponse object
     */
    public Observable<UsageEventOkResponse> postUsageEventAsync(UsageEvent body, UUID requestId, UUID correlationId) {
        return postUsageEventWithServiceResponseAsync(body, requestId, correlationId).map(new Func1<ServiceResponse<UsageEventOkResponse>, UsageEventOkResponse>() {
            @Override
            public UsageEventOkResponse call(ServiceResponse<UsageEventOkResponse> response) {
                return response.body();
            }
        });
    }

    /**
     * Posts a single usage event to the marketplace metering service API.
     * Posts a single usage event to the marketplace metering service API.
     *
     * @param body the UsageEvent value
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the UsageEventOkResponse object
     */
    public Observable<ServiceResponse<UsageEventOkResponse>> postUsageEventWithServiceResponseAsync(UsageEvent body, UUID requestId, UUID correlationId) {
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        final String apiVersion = "2018-08-31";
        return service.postUsageEvent(body, apiVersion, requestId, correlationId, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<UsageEventOkResponse>>>() {
                @Override
                public Observable<ServiceResponse<UsageEventOkResponse>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<UsageEventOkResponse> clientResponse = postUsageEventDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<UsageEventOkResponse> postUsageEventDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<UsageEventOkResponse, CloudException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<UsageEventOkResponse>() { }.getType())
                .register(400, new TypeToken<Void>() { }.getType())
                .register(403, new TypeToken<Void>() { }.getType())
                .register(409, new TypeToken<Void>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

    /**
     * Posts a set of usage events to the marketplace metering service API.
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch usage event request references the metering services dimension defined by the publisher when publishing the offer.
     *
     * @param body the List&lt;UsageEvent&gt; value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws CloudException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the List&lt;UsageEventOkResponse&gt; object if successful.
     */
    public List<UsageEventOkResponse> postBatchUsageEvent(List<UsageEvent> body) {
        return postBatchUsageEventWithServiceResponseAsync(body).toBlocking().single().body();
    }

    /**
     * Posts a set of usage events to the marketplace metering service API.
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch usage event request references the metering services dimension defined by the publisher when publishing the offer.
     *
     * @param body the List&lt;UsageEvent&gt; value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<List<UsageEventOkResponse>> postBatchUsageEventAsync(List<UsageEvent> body, final ServiceCallback<List<UsageEventOkResponse>> serviceCallback) {
        return ServiceFuture.fromResponse(postBatchUsageEventWithServiceResponseAsync(body), serviceCallback);
    }

    /**
     * Posts a set of usage events to the marketplace metering service API.
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch usage event request references the metering services dimension defined by the publisher when publishing the offer.
     *
     * @param body the List&lt;UsageEvent&gt; value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;UsageEventOkResponse&gt; object
     */
    public Observable<List<UsageEventOkResponse>> postBatchUsageEventAsync(List<UsageEvent> body) {
        return postBatchUsageEventWithServiceResponseAsync(body).map(new Func1<ServiceResponse<List<UsageEventOkResponse>>, List<UsageEventOkResponse>>() {
            @Override
            public List<UsageEventOkResponse> call(ServiceResponse<List<UsageEventOkResponse>> response) {
                return response.body();
            }
        });
    }

    /**
     * Posts a set of usage events to the marketplace metering service API.
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch usage event request references the metering services dimension defined by the publisher when publishing the offer.
     *
     * @param body the List&lt;UsageEvent&gt; value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;UsageEventOkResponse&gt; object
     */
    public Observable<ServiceResponse<List<UsageEventOkResponse>>> postBatchUsageEventWithServiceResponseAsync(List<UsageEvent> body) {
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        final String apiVersion = "2018-08-31";
        final UUID requestId = null;
        final UUID correlationId = null;
        return service.postBatchUsageEvent(body, apiVersion, requestId, correlationId, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<List<UsageEventOkResponse>>>>() {
                @Override
                public Observable<ServiceResponse<List<UsageEventOkResponse>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<List<UsageEventOkResponse>> clientResponse = postBatchUsageEventDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    /**
     * Posts a set of usage events to the marketplace metering service API.
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch usage event request references the metering services dimension defined by the publisher when publishing the offer.
     *
     * @param body the List&lt;UsageEvent&gt; value
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws CloudException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the List&lt;UsageEventOkResponse&gt; object if successful.
     */
    public List<UsageEventOkResponse> postBatchUsageEvent(List<UsageEvent> body, UUID requestId, UUID correlationId) {
        return postBatchUsageEventWithServiceResponseAsync(body, requestId, correlationId).toBlocking().single().body();
    }

    /**
     * Posts a set of usage events to the marketplace metering service API.
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch usage event request references the metering services dimension defined by the publisher when publishing the offer.
     *
     * @param body the List&lt;UsageEvent&gt; value
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<List<UsageEventOkResponse>> postBatchUsageEventAsync(List<UsageEvent> body, UUID requestId, UUID correlationId, final ServiceCallback<List<UsageEventOkResponse>> serviceCallback) {
        return ServiceFuture.fromResponse(postBatchUsageEventWithServiceResponseAsync(body, requestId, correlationId), serviceCallback);
    }

    /**
     * Posts a set of usage events to the marketplace metering service API.
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch usage event request references the metering services dimension defined by the publisher when publishing the offer.
     *
     * @param body the List&lt;UsageEvent&gt; value
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;UsageEventOkResponse&gt; object
     */
    public Observable<List<UsageEventOkResponse>> postBatchUsageEventAsync(List<UsageEvent> body, UUID requestId, UUID correlationId) {
        return postBatchUsageEventWithServiceResponseAsync(body, requestId, correlationId).map(new Func1<ServiceResponse<List<UsageEventOkResponse>>, List<UsageEventOkResponse>>() {
            @Override
            public List<UsageEventOkResponse> call(ServiceResponse<List<UsageEventOkResponse>> response) {
                return response.body();
            }
        });
    }

    /**
     * Posts a set of usage events to the marketplace metering service API.
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch usage event request references the metering services dimension defined by the publisher when publishing the offer.
     *
     * @param body the List&lt;UsageEvent&gt; value
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from client operation with events on the server side. If this value isn't provided, one will be generated and provided in the response headers.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;UsageEventOkResponse&gt; object
     */
    public Observable<ServiceResponse<List<UsageEventOkResponse>>> postBatchUsageEventWithServiceResponseAsync(List<UsageEvent> body, UUID requestId, UUID correlationId) {
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        final String apiVersion = "2018-08-31";
        return service.postBatchUsageEvent(body, apiVersion, requestId, correlationId, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<List<UsageEventOkResponse>>>>() {
                @Override
                public Observable<ServiceResponse<List<UsageEventOkResponse>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<List<UsageEventOkResponse>> clientResponse = postBatchUsageEventDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<List<UsageEventOkResponse>> postBatchUsageEventDelegate(Response<ResponseBody> response) throws CloudException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<List<UsageEventOkResponse>, CloudException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<List<UsageEventOkResponse>>() { }.getType())
                .register(400, new TypeToken<Void>() { }.getType())
                .register(403, new TypeToken<Void>() { }.getType())
                .registerError(CloudException.class)
                .build(response);
    }

}
