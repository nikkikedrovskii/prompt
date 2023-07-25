package com.promptpicture.backend.core.order.domain;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderModifiedNotificationMessage {

    public static final String PROMPT_PRICE_MODIFIED = "The price of a {0} resolution picture has been changed. New price: {1}";
    public static final String COUNTRY_VAT_MODIFIED = "The value added tax for country {0} has been changed. New tax: {1}";
    public static final String PRICE_WITHOUT_VAT_MODIFIED = "The price without value added tax has been changed. New price: {0}";
    public static final String PRICE_WITH_VAT_MODIFIED = "The price with value added tax has been changed. New price: {0}";

}
