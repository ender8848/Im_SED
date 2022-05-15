package retail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    private List<Product> items;
    private CreditCardDetails creditCardDetails;
    private Address billingAddress;
    private Address shippingAddress;
    private Courier courier;
    private boolean giftWrap;
    private BigDecimal discount;


    public OrderBuilder() {
        this.items = new ArrayList<>();
    }

    public OrderBuilder addItem(Product item) {
        items.add(item);
        discount = new BigDecimal("0.");
        giftWrap = false;
        return this;
    }

    public OrderBuilder setCreditCardDetails(CreditCardDetails creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
        return this;
    }

    public OrderBuilder setCourier(Courier courier) {
        this.courier = courier;
        return this;
    }

    public OrderBuilder setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        if (shippingAddress == null) {
            shippingAddress = billingAddress;
        }
        return this;
    }

    public OrderBuilder setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }



    public Order build() {
        // defensive behavior
        if (items.size() == 0) {
            throw new RuntimeException("No items!");
        }
        if (creditCardDetails == null ||
            shippingAddress == null ||
            courier == null) {
            throw new RuntimeException("Information incomplete");
        }
        if (items.size() > 3 && giftWrap) {
            throw new RuntimeException("Bulk order can not be gift wrapped");
        }
        if (items.size() <= 3 && !discount.equals(new BigDecimal("0."))) {
            throw new RuntimeException("Small order cannot have discount");
        }

        if (items.size() <= 3) {
            return new SmallOrder(items, creditCardDetails, billingAddress, shippingAddress, courier, giftWrap);
        } else {
            return new BulkOrder(items, creditCardDetails, billingAddress, shippingAddress, courier, discount);
        }
    }

    public OrderBuilder withGiftWrap() {
        giftWrap = true;
        return this;
    }

    public OrderBuilder withDiscount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }
}
