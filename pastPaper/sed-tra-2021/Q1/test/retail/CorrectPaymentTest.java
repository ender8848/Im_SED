package retail;

import org.junit.Test;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class CorrectPaymentTest {
     private OrderBuilder alreadyHasThree = new OrderBuilder()
             .addItem(new Product("One Book", new BigDecimal("10.00")))
             .addItem(new Product("One Book", new BigDecimal("10.00")))
             .addItem(new Product("One Book", new BigDecimal("10.00")))
             .setBillingAddress(new Address("nowhere"))
             .setCreditCardDetails(new CreditCardDetails("1234123412341234", 111));

     private Courier fourPounds = new Courier() {
         @Override
         public void send(Parcel shipment, Address shippingAddress) { }

         @Override
         public BigDecimal deliveryCharge() {
             return new BigDecimal(4.);
         }
     };

    @Test
    public void smallOrderWithGiftWrap() {
        Payable<BigDecimal> order = alreadyHasThree.setCourier(fourPounds)
                .withGiftWrap()
                .build();
        assertEquals(order.process(), new BigDecimal("37.00"));
    }

    @Test
    public void bulkOrderWithDiscount() {
        Payable<BigDecimal> order = alreadyHasThree.setCourier(fourPounds)
                .addItem(new Product("One Book", new BigDecimal("10.00")))
                .withDiscount(new BigDecimal(4))
                .build();
        assertEquals(order.process(), new BigDecimal("36.00"));
    }
}
