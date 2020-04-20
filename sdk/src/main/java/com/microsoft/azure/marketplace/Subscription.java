/*
 * SaaS fulfillment APIs, version 2
 * This spec details the APIs that enable partners to sell their SaaS applications in the AppSource marketplace and the Azure Marketplace. These APIs are a requirement for transactable SaaS offers on the AppSource and Azure Marketplace.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.microsoft.azure.marketplace;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.microsoft.azure.marketplace.AadIdentifier;
import com.microsoft.azure.marketplace.SubscriptionTerm;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * Subscription
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-04-19T20:04:00.284-07:00[America/Los_Angeles]")
public class Subscription {
  @SerializedName("id")
  private UUID id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("publisherId")
  private String publisherId = null;

  @SerializedName("offerId")
  private String offerId = null;

  @SerializedName("planId")
  private String planId = null;

  @SerializedName("quantity")
  private Integer quantity = null;

  /**
   * Dry Run indicates all transactions run as Test-Mode in the commerce stack
   */
  @JsonAdapter(SessionModeEnum.Adapter.class)
  public enum SessionModeEnum {
    NONE("None"),
    DRYRUN("DryRun");

    private String value;

    SessionModeEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static SessionModeEnum fromValue(String text) {
      for (SessionModeEnum b : SessionModeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<SessionModeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final SessionModeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public SessionModeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return SessionModeEnum.fromValue(String.valueOf(value));
      }
    }
  }  @SerializedName("sessionMode")
  private SessionModeEnum sessionMode = null;

  @SerializedName("isFreeTrial")
  private Boolean isFreeTrial = null;

  @SerializedName("isTest")
  private Boolean isTest = null;

  /**
   * Possible Values are None, Csp (Csp sandbox purchase)
   */
  @JsonAdapter(SandboxTypeEnum.Adapter.class)
  public enum SandboxTypeEnum {
    NONE("None"),
    CSP("Csp");

    private String value;

    SandboxTypeEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static SandboxTypeEnum fromValue(String text) {
      for (SandboxTypeEnum b : SandboxTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<SandboxTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final SandboxTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public SandboxTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return SandboxTypeEnum.fromValue(String.valueOf(value));
      }
    }
  }  @SerializedName("sandboxType")
  private SandboxTypeEnum sandboxType = null;

  /**
   * Indicates the status of the operation.
   */
  @JsonAdapter(SaasSubscriptionStatusEnum.Adapter.class)
  public enum SaasSubscriptionStatusEnum {
    NOTSTARTED("NotStarted"),
    PENDINGFULFILLMENTSTART("PendingFulfillmentStart"),
    SUBSCRIBED("Subscribed"),
    SUSPENDED("Suspended"),
    UNSUBSCRIBED("Unsubscribed");

    private String value;

    SaasSubscriptionStatusEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static SaasSubscriptionStatusEnum fromValue(String text) {
      for (SaasSubscriptionStatusEnum b : SaasSubscriptionStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<SaasSubscriptionStatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final SaasSubscriptionStatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public SaasSubscriptionStatusEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return SaasSubscriptionStatusEnum.fromValue(String.valueOf(value));
      }
    }
  }  @SerializedName("saasSubscriptionStatus")
  private SaasSubscriptionStatusEnum saasSubscriptionStatus = null;

  @SerializedName("beneficiary")
  private AadIdentifier beneficiary = null;

  @SerializedName("purchaser")
  private AadIdentifier purchaser = null;

  @SerializedName("term")
  private SubscriptionTerm term = null;

  /**
   * Gets or Sets allowedCustomerOperations
   */
  @JsonAdapter(AllowedCustomerOperationsEnum.Adapter.class)
  public enum AllowedCustomerOperationsEnum {
    READ("Read"),
    UPDATE("Update"),
    DELETE("Delete");

    private String value;

    AllowedCustomerOperationsEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static AllowedCustomerOperationsEnum fromValue(String text) {
      for (AllowedCustomerOperationsEnum b : AllowedCustomerOperationsEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<AllowedCustomerOperationsEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final AllowedCustomerOperationsEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public AllowedCustomerOperationsEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return AllowedCustomerOperationsEnum.fromValue(String.valueOf(value));
      }
    }
  }  @SerializedName("allowedCustomerOperations")
  private List<AllowedCustomerOperationsEnum> allowedCustomerOperations = null;

  public Subscription id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @Schema(description = "")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Subscription name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @Schema(description = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Subscription publisherId(String publisherId) {
    this.publisherId = publisherId;
    return this;
  }

   /**
   * Get publisherId
   * @return publisherId
  **/
  @Schema(description = "")
  public String getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(String publisherId) {
    this.publisherId = publisherId;
  }

  public Subscription offerId(String offerId) {
    this.offerId = offerId;
    return this;
  }

   /**
   * Get offerId
   * @return offerId
  **/
  @Schema(description = "")
  public String getOfferId() {
    return offerId;
  }

  public void setOfferId(String offerId) {
    this.offerId = offerId;
  }

  public Subscription planId(String planId) {
    this.planId = planId;
    return this;
  }

   /**
   * Get planId
   * @return planId
  **/
  @Schema(description = "")
  public String getPlanId() {
    return planId;
  }

  public void setPlanId(String planId) {
    this.planId = planId;
  }

  public Subscription quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

   /**
   * Get quantity
   * @return quantity
  **/
  @Schema(description = "")
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Subscription sessionMode(SessionModeEnum sessionMode) {
    this.sessionMode = sessionMode;
    return this;
  }

   /**
   * Dry Run indicates all transactions run as Test-Mode in the commerce stack
   * @return sessionMode
  **/
  @Schema(description = "Dry Run indicates all transactions run as Test-Mode in the commerce stack")
  public SessionModeEnum getSessionMode() {
    return sessionMode;
  }

  public void setSessionMode(SessionModeEnum sessionMode) {
    this.sessionMode = sessionMode;
  }

  public Subscription isFreeTrial(Boolean isFreeTrial) {
    this.isFreeTrial = isFreeTrial;
    return this;
  }

   /**
   * true - the customer subscription is currently in free trial, false - the customer subscription is not currently in free trial.(optional field - default false)
   * @return isFreeTrial
  **/
  @Schema(description = "true - the customer subscription is currently in free trial, false - the customer subscription is not currently in free trial.(optional field - default false)")
  public Boolean isIsFreeTrial() {
    return isFreeTrial;
  }

  public void setIsFreeTrial(Boolean isFreeTrial) {
    this.isFreeTrial = isFreeTrial;
  }

  public Subscription isTest(Boolean isTest) {
    this.isTest = isTest;
    return this;
  }

   /**
   * Indicating whether the current subscription is a test asset.
   * @return isTest
  **/
  @Schema(description = "Indicating whether the current subscription is a test asset.")
  public Boolean isIsTest() {
    return isTest;
  }

  public void setIsTest(Boolean isTest) {
    this.isTest = isTest;
  }

  public Subscription sandboxType(SandboxTypeEnum sandboxType) {
    this.sandboxType = sandboxType;
    return this;
  }

   /**
   * Possible Values are None, Csp (Csp sandbox purchase)
   * @return sandboxType
  **/
  @Schema(description = "Possible Values are None, Csp (Csp sandbox purchase)")
  public SandboxTypeEnum getSandboxType() {
    return sandboxType;
  }

  public void setSandboxType(SandboxTypeEnum sandboxType) {
    this.sandboxType = sandboxType;
  }

  public Subscription saasSubscriptionStatus(SaasSubscriptionStatusEnum saasSubscriptionStatus) {
    this.saasSubscriptionStatus = saasSubscriptionStatus;
    return this;
  }

   /**
   * Indicates the status of the operation.
   * @return saasSubscriptionStatus
  **/
  @Schema(description = "Indicates the status of the operation.")
  public SaasSubscriptionStatusEnum getSaasSubscriptionStatus() {
    return saasSubscriptionStatus;
  }

  public void setSaasSubscriptionStatus(SaasSubscriptionStatusEnum saasSubscriptionStatus) {
    this.saasSubscriptionStatus = saasSubscriptionStatus;
  }

  public Subscription beneficiary(AadIdentifier beneficiary) {
    this.beneficiary = beneficiary;
    return this;
  }

   /**
   * Get beneficiary
   * @return beneficiary
  **/
  @Schema(description = "")
  public AadIdentifier getBeneficiary() {
    return beneficiary;
  }

  public void setBeneficiary(AadIdentifier beneficiary) {
    this.beneficiary = beneficiary;
  }

  public Subscription purchaser(AadIdentifier purchaser) {
    this.purchaser = purchaser;
    return this;
  }

   /**
   * Get purchaser
   * @return purchaser
  **/
  @Schema(description = "")
  public AadIdentifier getPurchaser() {
    return purchaser;
  }

  public void setPurchaser(AadIdentifier purchaser) {
    this.purchaser = purchaser;
  }

  public Subscription term(SubscriptionTerm term) {
    this.term = term;
    return this;
  }

   /**
   * Get term
   * @return term
  **/
  @Schema(description = "")
  public SubscriptionTerm getTerm() {
    return term;
  }

  public void setTerm(SubscriptionTerm term) {
    this.term = term;
  }

  public Subscription allowedCustomerOperations(List<AllowedCustomerOperationsEnum> allowedCustomerOperations) {
    this.allowedCustomerOperations = allowedCustomerOperations;
    return this;
  }

  public Subscription addAllowedCustomerOperationsItem(AllowedCustomerOperationsEnum allowedCustomerOperationsItem) {
    if (this.allowedCustomerOperations == null) {
      this.allowedCustomerOperations = new ArrayList<AllowedCustomerOperationsEnum>();
    }
    this.allowedCustomerOperations.add(allowedCustomerOperationsItem);
    return this;
  }

   /**
   * Get allowedCustomerOperations
   * @return allowedCustomerOperations
  **/
  @Schema(description = "")
  public List<AllowedCustomerOperationsEnum> getAllowedCustomerOperations() {
    return allowedCustomerOperations;
  }

  public void setAllowedCustomerOperations(List<AllowedCustomerOperationsEnum> allowedCustomerOperations) {
    this.allowedCustomerOperations = allowedCustomerOperations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subscription subscription = (Subscription) o;
    return Objects.equals(this.id, subscription.id) &&
        Objects.equals(this.name, subscription.name) &&
        Objects.equals(this.publisherId, subscription.publisherId) &&
        Objects.equals(this.offerId, subscription.offerId) &&
        Objects.equals(this.planId, subscription.planId) &&
        Objects.equals(this.quantity, subscription.quantity) &&
        Objects.equals(this.sessionMode, subscription.sessionMode) &&
        Objects.equals(this.isFreeTrial, subscription.isFreeTrial) &&
        Objects.equals(this.isTest, subscription.isTest) &&
        Objects.equals(this.sandboxType, subscription.sandboxType) &&
        Objects.equals(this.saasSubscriptionStatus, subscription.saasSubscriptionStatus) &&
        Objects.equals(this.beneficiary, subscription.beneficiary) &&
        Objects.equals(this.purchaser, subscription.purchaser) &&
        Objects.equals(this.term, subscription.term) &&
        Objects.equals(this.allowedCustomerOperations, subscription.allowedCustomerOperations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, publisherId, offerId, planId, quantity, sessionMode, isFreeTrial, isTest, sandboxType, saasSubscriptionStatus, beneficiary, purchaser, term, allowedCustomerOperations);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Subscription {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    publisherId: ").append(toIndentedString(publisherId)).append("\n");
    sb.append("    offerId: ").append(toIndentedString(offerId)).append("\n");
    sb.append("    planId: ").append(toIndentedString(planId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    sessionMode: ").append(toIndentedString(sessionMode)).append("\n");
    sb.append("    isFreeTrial: ").append(toIndentedString(isFreeTrial)).append("\n");
    sb.append("    isTest: ").append(toIndentedString(isTest)).append("\n");
    sb.append("    sandboxType: ").append(toIndentedString(sandboxType)).append("\n");
    sb.append("    saasSubscriptionStatus: ").append(toIndentedString(saasSubscriptionStatus)).append("\n");
    sb.append("    beneficiary: ").append(toIndentedString(beneficiary)).append("\n");
    sb.append("    purchaser: ").append(toIndentedString(purchaser)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
    sb.append("    allowedCustomerOperations: ").append(toIndentedString(allowedCustomerOperations)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
