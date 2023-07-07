package com.promptpicture.backend.core.common.enumeration;

public enum OrderStatus {

    NEW, // New order received in the system
    IN_PROGRESS, // Order confirmation email sent
    ORDER_PAID, // Order confirmation email sent
    INVOICE_SENT, // An invoice has been sent
    ORDER_COMPLETED // Order completely finished
}
