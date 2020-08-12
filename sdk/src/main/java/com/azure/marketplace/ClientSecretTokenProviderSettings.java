/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 **/
package com.azure.marketplace;

import lombok.Data;

import java.util.UUID;

@Data
public class ClientSecretTokenProviderSettings {
    UUID _clientId;
    UUID _tenantId;
    String _clientSecret;
}
