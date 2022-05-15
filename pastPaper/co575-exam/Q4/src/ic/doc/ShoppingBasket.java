package ic.doc;

// a basket should not concern those details
// import com.worldpay.CardNumber;
// import com.worldpay.TransactionProcessor;

import java.util.HashMap;
import java.util.Map;

public class ShoppingBasket {

  private final Map<Item, Integer> items = new HashMap<>();
  private final Processor processor;
  private String cardNumber;

  public ShoppingBasket(Processor processor) {
    this.processor = processor;
  }

  public void addItem(Item item) {
    if (items.containsKey(item)) {
      items.put(item, items.get(item) + 1);
    } else {
      items.put(item, 1);
    }
  }

  public void enterCardDetails(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public void checkout() {
    int totalPounds = 0;
    int totalItems = 0;
    for (Item item : items.keySet()) {
      Integer quantity = items.get(item);
      totalItems = totalItems + quantity;
      totalPounds = totalPounds + quantity * item.priceInPounds();
    }

    if (totalItems > 3) {
      totalPounds = Math.min(totalPounds, totalPounds - 5);
    }

    // Adapter pattern
    // Hexagonal Architectures
    processor.process(cardNumber, totalPounds);
  }

  public int getItemNum() {
    Integer num = 0;
    for (Item item : items.keySet()) {
      num += items.get(item);
    }
    return num;
  }

  public String getCardDetail() {
    return cardNumber;
  }
}