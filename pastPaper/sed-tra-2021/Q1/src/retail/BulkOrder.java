package retail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public class BulkOrder extends Order{

  private final BigDecimal discount;

  public BulkOrder(
          List<Product> items,
          CreditCardDetails creditCardDetails,
          Address billingAddress,
          Address shippingAddress,
          Courier courier,
          BigDecimal discount) {
    super(items, creditCardDetails, billingAddress, shippingAddress, courier);;
    this.discount = discount;
  }

  @Override
  protected void sendToCourier() {
    courier.send(new Parcel(items), shippingAddress);
  }

  protected BigDecimal adjustPrice(BigDecimal price) {
    if (items.size() > 10) {
      price = price.multiply(BigDecimal.valueOf(0.8));
    } else if (items.size() > 5) {
      price = price.multiply(BigDecimal.valueOf(0.9));
    }

    price = price.subtract(discount);
    return price;
  }

  private BigDecimal round(BigDecimal amount) {
    return amount.setScale(2, RoundingMode.CEILING);
  }
}
