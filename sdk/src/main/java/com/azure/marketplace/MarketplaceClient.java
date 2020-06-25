package com.azure.marketplace;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

public class MarketplaceClient {
    private MarketplaceTokenProvider tokenProvider;

    static public SaaSFulfillmentAPIsVersion2 ApiClient(MarketplaceTokenProvider tokenProvider){
        HttpPipeline pipeline =
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy(), new MarketplaceAuthorizationPolicy(tokenProvider))
                        .build();
        SaaSFulfillmentAPIsVersion2Builder builder = new SaaSFulfillmentAPIsVersion2Builder();
        builder.pipeline(pipeline);
        return builder.buildClient();
    }
}
