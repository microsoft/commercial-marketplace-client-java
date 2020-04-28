package com.microsoft.azure.marketplace.tests;

import com.microsoft.azure.marketplace.ApiException;
import com.microsoft.azure.marketplace.ApiVersion;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ValidateTestSetupTest {

    private MarketplaceSettingsProvider _provider = new MarketplaceSettingsProvider();

    static Boolean isBlank(String value){
        return (value == null) || (value.trim().length() == 0);
    }

    @Test
    public void validateAadTenantId() {
        String value = _provider.getAadTenantId();
        assertTrue("Environment variable AAD_TENANT_ID not found. Please set it and then re-run this test.",
                !isBlank((value)));
    }

    @Test
    public void validateAadClientId() {
        String value = _provider.getAadAppClientId();
        assertTrue("Environment variable AAD_APP_CLIENT_ID not found. Please set it and then re-run this test.",
                !isBlank((value)));
    }

    @Test
    public void validateAadClientSecret() {
        String value = _provider.getAadAppClientSecret();
        assertTrue("Environment variable AAD_APP_CLIENT_SECRET not found. Please set it and then re-run this test.",
                !isBlank((value)));
    }

    @Test
    public void validateAmpVersion() {
        String value = _provider.getAmpApiVersion();
        assertTrue("Environment variable AMP_API_VERSION not found. Please set it and then re-run this test.",
                !isBlank((value)));
    }

    @Test
    public void validateAmpVersionValue() {
        String value = _provider.getAmpApiVersion();
        ApiVersion[] versions = ApiVersion.values();

        for (int i = 0; i < versions.length; ++i){
            if (value.equalsIgnoreCase(versions[i].getValue())){
                return;
            }
        }

        assertTrue(String.format("AMP_API_VERSION must be set a valid value from ApiVersion. It is set to %s", value),
                false);
    }
}
