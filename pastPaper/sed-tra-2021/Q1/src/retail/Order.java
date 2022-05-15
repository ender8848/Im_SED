package retail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public abstract class Order implements Payable<BigDecimal>{
    protected final List<Product> items;
    protected final CreditCardDetails creditCardDetails;
    protected final Address billingAddress;
    protected final Address shippingAddress;
    protected final Courier courier;

    public Order(List<Product> items, CreditCardDetails creditCardDetails, Address billingAddress, Address shippingAddress, Courier courier) {
        this.items = Collections.unmodifiableList(items);
        this.creditCardDetails = creditCardDetails;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.courier = courier;
    }

    public BigDecimal process() {

        BigDecimal total = new BigDecimal(0);

        for (Product item : items) {
            total = total.add(item.unitPrice());
        }

        // adjustPrice
        total = adjustPrice(total);

        // sendToCourier
        sendToCourier();

        return round(total);
    }

    protected abstract void sendToCourier();

    protected abstract BigDecimal adjustPrice(BigDecimal price);

    private BigDecimal round(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.CEILING);
    }

    public Address getBillingAddress() {
        return billingAddress;
    }
}
