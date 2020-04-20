package com.microsoft.azure.marketplace;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MarketplaceApiClient extends ApiClient {
    static final String marketplaceResourceId = "62d94f6c-d599-489b-a797-3e10e42fbe22";

    public MarketplaceApiClient(String tenantId, String clientId, String clientSecret) throws Exception {
        if (tenantId == null || tenantId.isEmpty()){
            throw new IllegalArgumentException(String.format(
                    "tenantId must have a valid value. It is set to %s.",
                    (tenantId == null ? "(null)" : "(a blank or empty string)")));
        }

        if (clientId == null || clientId.isEmpty()){
            throw new IllegalArgumentException(String.format(
                    "clientId must have a valid value. It is set to %s.",
                    (clientId == null ? "(null)" : "(a blank or empty string)")));
        }

        if (clientSecret == null || clientSecret.isEmpty()){
            throw new IllegalArgumentException(String.format(
                    "clientSecret must have a valid value. It is set to %s.",
                    (clientSecret == null ? "(null)" : "(a blank or empty string)")));
        }

        String oauthToken = acquireToken(tenantId, clientId, clientSecret);
        this.setAccessToken(oauthToken);
    }

    private String acquireToken(String tenantId, String clientId, String clientSecret) throws Exception {
        String authority = String.format("https://login.microsoftonline.com/%s/oauth2/token", tenantId);
        URL url = new URL(authority);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(con.getOutputStream());
        String urlPostParameters =
                String.format("grant_type=client_credentials&resource=%s&client_id=%s&client_secret=%s",
                    marketplaceResourceId,
                    URLEncoder.encode(clientId, "UTF-8"),
                    URLEncoder.encode(clientSecret, "UTF-8"));
        outputStream.writeBytes(urlPostParameters);
        outputStream.flush();
        outputStream.close();
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

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

    class OAuthInfo{
        @SerializedName("access_token")
        private String accessToken;

        public String getAccessToken() { return accessToken; }

        public void setAccessToken(String value) { accessToken = value; }
    }
}
