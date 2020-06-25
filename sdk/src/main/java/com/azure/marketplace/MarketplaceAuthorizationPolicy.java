/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 */
package com.azure.marketplace;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipelineCallContext;
import com.azure.core.http.HttpPipelineNextPolicy;
import com.azure.core.http.HttpResponse;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.util.logging.ClientLogger;
import reactor.core.publisher.Mono;

import java.io.IOException;

public class MarketplaceAuthorizationPolicy implements HttpPipelinePolicy {
    private MarketplaceTokenProvider tokenProvider;
    private final ClientLogger logger = new ClientLogger(MarketplaceAuthorizationPolicy.class);
    public MarketplaceAuthorizationPolicy(MarketplaceTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Mono<HttpResponse> process(HttpPipelineCallContext httpPipelineCallContext, HttpPipelineNextPolicy httpPipelineNextPolicy) {
        HttpHeaders headers = httpPipelineCallContext.getHttpRequest().getHeaders();
        try {
            String token = tokenProvider.acquireToken();
            String bearer = String.format("Bearer %s", token);
            headers.put("Authorization", bearer);
        }
        catch(IOException exception){
            logger.error(exception.toString());
        }
        return httpPipelineNextPolicy.process();
    }
}
