package com.microsoft.azure.marketplace;

public class SubscriptionResponseHelper {
    public static String ExtractContinuationToken(String nextLink)  {
        com.squareup.okhttp.HttpUrl url = com.squareup.okhttp.HttpUrl.parse(nextLink);
        return url.queryParameter("continuationToken");
    }
}
