package org.katas.refactoring;

import java.util.List;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private Order order;
    private double totSalesTx = 0d;
    private double tot = 0d;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();
        calculate();

		appendHeader(output);
		appendCustomerDetails(output);
        appendLineItems(output, order.getLineItems());
        appendStateTax(output, totSalesTx);
		appendTotalAmount(output, tot);
		return output.toString();
	}

    public void calculate() {
        for (LineItem lineItem : order.getLineItems()) {
            double salesTax = lineItem.totalAmount() * .10;
            totSalesTx += salesTax;

            tot += lineItem.totalAmount() + salesTax;
        }
    }

    public void appendTotalAmount(StringBuilder output, double tot) {
		output.append("Total Amount").append('\t').append(tot);
	}

	public void appendStateTax(StringBuilder output, double totSalesTx) {
		output.append("Sales Tax").append('\t').append(totSalesTx);
	}

	public void appendLineItems(StringBuilder output, List<LineItem> lineItems) {
		for (LineItem lineItem : order.getLineItems()) {
			output.append(lineItem.getDescription());
			output.append('\t');
			output.append(lineItem.getPrice());
			output.append('\t');
			output.append(lineItem.getQuantity());
			output.append('\t');
			output.append(lineItem.totalAmount());
			output.append('\n');
		}
	}

	private void appendCustomerDetails(StringBuilder output) {
		output.append(order.getCustomer().getName());
		output.append(order.getCustomer().getAddress());
	}

	private void appendHeader(StringBuilder output) {
		output.append("======Printing Orders======\n");
	}
}