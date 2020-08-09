package com.azure.spring.marketplace.implementation;

import com.microsoft.rest.credentials.ServiceClientCredentials;
import org.springframework.stereotype.Component;

@Component
public class MarketplaceClientImpl extends com.azure.marketplace.implementation.MarketplaceClientImpl {
    public MarketplaceClientImpl(ServiceClientCredentials credentials) {
        super(credentials);
    }
}
