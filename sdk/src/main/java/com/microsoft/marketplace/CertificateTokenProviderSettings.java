/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 **/
package com.microsoft.marketplace;

import lombok.Data;

import java.security.KeyStore;
import java.util.UUID;

@Data
public class CertificateTokenProviderSettings {
    private UUID tenantId;
    private UUID clientId;
    private KeyStore.PrivateKeyEntry certificatePrivateKey;
}