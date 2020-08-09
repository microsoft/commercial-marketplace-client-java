package com.azure.spring.marketplace;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.microsoft.rest.credentials.ServiceClientCredentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

@Service
public class ClientSecretTokenProvider implements ServiceClientCredentials {
    UUID _tenantId;
    UUID _clientId;
    String _clientSecret;

    public ClientSecretTokenProvider(UUID tenantId, UUID clientId, String clientSecret) {
        _tenantId = tenantId;
        _clientId = clientId;
        _clientSecret = clientSecret;
    }

    @Override
    public void applyCredentialsFilter(OkHttpClient.Builder builder) {
        builder.addNetworkInterceptor(
                chain -> {
                    Request request;
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

    private String acquireToken() throws IOException {
        String authority = String.format("https://login.microsoftonline.com/%s/oauth2/v2.0/token", _tenantId);
        URL url = new URL(authority);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(con.getOutputStream());
        String urlPostParameters =
                String.format("grant_type=client_credentials&scope=%s&client_id=%s&client_secret=%s",
                        Constants.MarketplaceResourceScope,
                        URLEncoder.encode(_clientId.toString(), "UTF-8"),
                        URLEncoder.encode(_clientSecret, "UTF-8"));
        outputStream.writeBytes(urlPostParameters);
        outputStream.flush();
        outputStream.close();
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            String allContent = response.toString();
            Gson gson = new Gson();
            OAuthInfo info = gson.fromJson(allContent, OAuthInfo.class);
            return info.getAccessToken();
        }

        return String.format("Error: responseCode: %d", responseCode);
    }

    static class OAuthInfo{
        @SerializedName("access_token")
        private String _accessToken;

        public String getAccessToken() { return _accessToken; }
    }
}
