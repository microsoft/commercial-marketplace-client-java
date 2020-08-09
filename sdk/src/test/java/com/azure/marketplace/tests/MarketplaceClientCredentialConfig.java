package com.azure.marketplace.tests;

import com.azure.marketplace.MarketplaceClient;
import com.azure.spring.marketplace.ClientSecretTokenProvider;
import com.azure.spring.marketplace.implementation.MarketplaceClientImpl;
import com.microsoft.rest.credentials.ServiceClientCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class MarketplaceClientCredentialConfig {
    @Bean
    public ServiceClientCredentials serviceClientCredentials() {
        ClientSecretTokenProvider instance = new ClientSecretTokenProvider(
                UUID.fromString("0f1a53c8-1135-457a-bcdb-d58abc87e4b7"),
                UUID.fromString("4ac2e37a-8296-46cd-ac7e-323732979b50"),
                    "49CZ2a2_tI-iOf_YrZQIiiP1S_J6V_1mAn");
        return instance;
    }

    @Bean
    public MarketplaceClient marketplaceClient() {
        MarketplaceClient instance = new MarketplaceClientImpl(this.serviceClientCredentials());
        return instance;
    }
}
