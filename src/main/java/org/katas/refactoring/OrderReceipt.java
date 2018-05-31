package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * 
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();

		appendHeader(output);
		appendCustomerDetails(output);

		double totSalesTx = 0d;
		double tot = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			appendLineItem(output, lineItem);

			// calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * .10;
            totSalesTx += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            tot += lineItem.totalAmount() + salesTax;
		}

		appendStateTax(output, totSalesTx);
		appendTotalAmount(output, tot);
		return output.toString();
	}

	public void appendTotalAmount(StringBuilder output, double tot) {
		output.append("Total Amount").append('\t').append(tot);
	}

	public void appendStateTax(StringBuilder output, double totSalesTx) {
		output.append("Sales Tax").append('\t').append(totSalesTx);
	}

	public void appendLineItem(StringBuilder output, LineItem lineItem) {
		output.append(lineItem.getDescription());
		output.append('\t');
		output.append(lineItem.getPrice());
		output.append('\t');
		output.append(lineItem.getQuantity());
		output.append('\t');
		output.append(lineItem.totalAmount());
		output.append('\n');
	}

	private void appendCustomerDetails(StringBuilder output) {
		output.append(order.getCustomer().getName());
		output.append(order.getCustomer().getAddress());
	}

	private void appendHeader(StringBuilder output) {
		output.append("======Printing Orders======\n");
	}
}