/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 **/
package com.azure.marketplace;

import com.microsoft.rest.credentials.ServiceClientCredentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public interface MarketplaceTokenProvider extends ServiceClientCredentials {

    default void applyCredentialsFilter(OkHttpClient.Builder builder) {
        builder.addNetworkInterceptor(
                chain -> {
                    Request request = null;
                    Request original = chain.request();
                    String token = acquireToken();
                    String bearerToken = String.format("Bearer %s", token);
                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("Authorization", bearerToken);
                    request = requestBuilder.build();
                    return chain.proceed(request);
                });
    }

    String acquireToken() throws IOException;
}
