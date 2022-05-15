package retail;

import java.math.BigDecimal;
import java.util.List;

public class SmallOrder extends Order {

  private static final BigDecimal GIFT_WRAP_CHARGE = new BigDecimal(3);
  private final boolean giftWrap;

  public SmallOrder(
          List<Product> items,
          CreditCardDetails creditCardDetails,
          Address billingAddress,
          Address shippingAddress,
          Courier courier,
          boolean giftWrap) {
    super(items, creditCardDetails, billingAddress, shippingAddress, courier);

    this.giftWrap = giftWrap;
  }

  @Override
  protected void sendToCourier() {
    if (giftWrap) {
      courier.send(new GiftBox(items), shippingAddress);
    } else {
      courier.send(new Parcel(items), shippingAddress);
    }
  }

  @Override
  protected BigDecimal adjustPrice(BigDecimal price) {
    price = price.add(courier.deliveryCharge());

    if (giftWrap) {
      price = price.add(GIFT_WRAP_CHARGE);
    }
    return price;
  }
}
