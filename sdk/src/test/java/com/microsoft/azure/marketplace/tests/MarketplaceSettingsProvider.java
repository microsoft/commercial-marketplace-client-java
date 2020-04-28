package com.microsoft.azure.marketplace.tests;

public class MarketplaceSettingsProvider {
    public String getAadTenantId() {
        String retval = System.getenv("AAD_TENANT_ID");
        return retval;
    }

    public String getAadAppClientId() {

        String retval = System.getenv("AAD_APP_CLIENT_ID");
        return retval;
    }

    public String getAadAppClientSecret() {
        String retval = System.getenv("AAD_APP_CLIENT_SECRET");
        return retval;
    }

    public String getAmpApiVersion() {
        String retval = System.getenv("AMP_API_VERSION");
        return retval;
    }
}