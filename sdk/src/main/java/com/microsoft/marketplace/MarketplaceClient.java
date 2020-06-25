package com.microsoft.marketplace;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.microsoft.marketplace.saas.SaaSAPI;
import com.microsoft.marketplace.saas.SaaSAPIBuilder;

public class MarketplaceClient {
    private MarketplaceTokenProvider tokenProvider;

    static public SaaSAPI ApiClient(MarketplaceTokenProvider tokenProvider){
        HttpPipeline pipeline =
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy(), new MarketplaceAuthorizationPolicy(tokenProvider))
                        .build();
        SaaSAPIBuilder builder = new SaaSAPIBuilder();
        builder.pipeline(pipeline);
        return builder.buildClient();
    }
}
