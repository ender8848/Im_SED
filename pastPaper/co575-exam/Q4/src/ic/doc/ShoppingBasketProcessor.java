package ic.doc;

import com.worldpay.CardNumber;
import com.worldpay.CreditCardTransaction;
import com.worldpay.TransactionProcessor;

public class ShoppingBasketProcessor implements Processor {
    @Override
    public void process(String cardNum, int pounds) {
        CardNumber cardNumber;
        try {
            cardNumber = new CardNumber(cardNum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        new TransactionProcessor().process(new CreditCardTransaction(cardNumber, pounds, 0));
    }
}
