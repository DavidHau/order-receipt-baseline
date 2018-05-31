package org.katas.refactoring;

import java.util.List;

public class Order {
    Customer customer;
    List<LineItem> lineItems;

    public Order(String customerName, String customerAddress, List<LineItem> lineItems) {
        this.customer = new Customer(customerName, customerAddress);
        this.lineItems = lineItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public double getTotalAmount() {
        if (lineItems == null) {
            return 0;
        }

        double totalAmount = 0;
        for (LineItem lineItem: lineItems) {
            totalAmount += lineItem.totalAmount();
        }
        return totalAmount;
    }

}
