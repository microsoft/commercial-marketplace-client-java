// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for
// license information.
//
// Code generated by Microsoft (R) AutoRest Code Generator.
// Changes may cause incorrect behavior and will be lost if the code is
// regenerated.

package com.microsoft.marketplace.meter;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.microsoft.marketplace.meter.models.BatchUsageEvent;
import com.microsoft.marketplace.meter.models.BatchUsageEventOkResponse;
import com.microsoft.marketplace.meter.models.UsageEvent;
import com.microsoft.marketplace.meter.models.UsageEventBadRequestResponseException;
import com.microsoft.marketplace.meter.models.UsageEventOkResponse;
import java.util.UUID;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in MeteringOperations. */
public final class MeteringOperations {
    /** The proxy service used to perform REST calls. */
    private final MeteringOperationsService service;

    /** The service client containing this operation class. */
    private final MeteringAPI client;

    /**
     * Initializes an instance of MeteringOperations.
     *
     * @param client the instance of the service client containing this operation class.
     */
    MeteringOperations(MeteringAPI client) {
        this.service = RestProxy.create(MeteringOperationsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for MeteringAPIMeteringOperations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "MeteringAPIMeteringO")
    private interface MeteringOperationsService {
        @Post("/usageEvent")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(UsageEventBadRequestResponseException.class)
        Mono<Response<UsageEventOkResponse>> postUsageEvent(
                @HostParam("$host") String host,
                @QueryParam("api-version") String apiVersion,
                @HeaderParam("x-ms-requestid") UUID requestId,
                @HeaderParam("x-ms-correlationid") UUID correlationId,
                @BodyParam("application/json") UsageEvent body);

        @Post("/batchUsageEvent")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BatchUsageEventOkResponse>> postBatchUsageEvent(
                @HostParam("$host") String host,
                @QueryParam("api-version") String apiVersion,
                @HeaderParam("x-ms-requestid") UUID requestId,
                @HeaderParam("x-ms-correlationid") UUID correlationId,
                @BodyParam("application/json") BatchUsageEvent body);
    }

    /**
     * Posts a single usage event to the marketplace metering service API.
     *
     * @param body The body parameter.
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value
     *     isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from
     *     client operation with events on the server side. If this value isn't provided, one will be generated and
     *     provided in the response headers.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws UsageEventBadRequestResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<UsageEventOkResponse>> postUsageEventWithResponseAsync(
            UsageEvent body, UUID requestId, UUID correlationId) {
        return service.postUsageEvent(
                this.client.getHost(), this.client.getApiVersion(), requestId, correlationId, body);
    }

    /**
     * Posts a single usage event to the marketplace metering service API.
     *
     * @param body The body parameter.
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value
     *     isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from
     *     client operation with events on the server side. If this value isn't provided, one will be generated and
     *     provided in the response headers.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws UsageEventBadRequestResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<UsageEventOkResponse> postUsageEventAsync(UsageEvent body, UUID requestId, UUID correlationId) {
        return postUsageEventWithResponseAsync(body, requestId, correlationId)
                .flatMap(
                        (Response<UsageEventOkResponse> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch
     * usage event request references the metering services dimension defined by the publisher when publishing the
     * offer.
     *
     * @param body The body parameter.
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value
     *     isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from
     *     client operation with events on the server side. If this value isn't provided, one will be generated and
     *     provided in the response headers.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BatchUsageEventOkResponse>> postBatchUsageEventWithResponseAsync(
            BatchUsageEvent body, UUID requestId, UUID correlationId) {
        return service.postBatchUsageEvent(
                this.client.getHost(), this.client.getApiVersion(), requestId, correlationId, body);
    }

    /**
     * The batch usage event API allows you to emit usage events for more than one purchased entity at once. The batch
     * usage event request references the metering services dimension defined by the publisher when publishing the
     * offer.
     *
     * @param body The body parameter.
     * @param requestId A unique string value for tracking the request from the client, preferably a GUID. If this value
     *     isn't provided, one will be generated and provided in the response headers.
     * @param correlationId A unique string value for operation on the client. This parameter correlates all events from
     *     client operation with events on the server side. If this value isn't provided, one will be generated and
     *     provided in the response headers.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BatchUsageEventOkResponse> postBatchUsageEventAsync(
            BatchUsageEvent body, UUID requestId, UUID correlationId) {
        return postBatchUsageEventWithResponseAsync(body, requestId, correlationId)
                .flatMap(
                        (Response<BatchUsageEventOkResponse> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }
}
