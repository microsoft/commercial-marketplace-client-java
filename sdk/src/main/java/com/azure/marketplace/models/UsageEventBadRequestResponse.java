/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.azure.marketplace.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The UsageEventBadRequestResponse model.
 */
public class UsageEventBadRequestResponse {
    /**
     * The code property.
     */
    @JsonProperty(value = "code")
    private String code;

    /**
     * The message property.
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * The target property.
     */
    @JsonProperty(value = "target")
    private String target;

    /**
     * The details property.
     */
    @JsonProperty(value = "details")
    private List<UsageEventBadRequestResponseDetail> details;

    /**
     * Get the code value.
     *
     * @return the code value
     */
    public String code() {
        return this.code;
    }

    /**
     * Set the code value.
     *
     * @param code the code value to set
     * @return the UsageEventBadRequestResponse object itself.
     */
    public UsageEventBadRequestResponse withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the message value.
     *
     * @return the message value
     */
    public String message() {
        return this.message;
    }

    /**
     * Set the message value.
     *
     * @param message the message value to set
     * @return the UsageEventBadRequestResponse object itself.
     */
    public UsageEventBadRequestResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the target value.
     *
     * @return the target value
     */
    public String target() {
        return this.target;
    }

    /**
     * Set the target value.
     *
     * @param target the target value to set
     * @return the UsageEventBadRequestResponse object itself.
     */
    public UsageEventBadRequestResponse withTarget(String target) {
        this.target = target;
        return this;
    }

    /**
     * Get the details value.
     *
     * @return the details value
     */
    public List<UsageEventBadRequestResponseDetail> details() {
        return this.details;
    }

    /**
     * Set the details value.
     *
     * @param details the details value to set
     * @return the UsageEventBadRequestResponse object itself.
     */
    public UsageEventBadRequestResponse withDetails(List<UsageEventBadRequestResponseDetail> details) {
        this.details = details;
        return this;
    }

}