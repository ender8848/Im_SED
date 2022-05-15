package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingBasketTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    Processor processor = context.mock(Processor.class);
    ShoppingBasket shoppingBasket = new ShoppingBasket(processor);
    Item item = new Item("book", 5);


    @Test
    public void canAddItem(){
        assertEquals(shoppingBasket.getItemNum(), 0);
        shoppingBasket.addItem(new Item("Laptop", 500));
        assertEquals(shoppingBasket.getItemNum(), 1);
    }

    @Test
    public void canAddCardDetail(){
        shoppingBasket.enterCardDetails("1234");
        assertEquals(shoppingBasket.getCardDetail(), "1234");
    }

    @Test
    public void shoppingBasketCheckoutCanProcess() {
        shoppingBasket.enterCardDetails("1234");
        context.checking(new Expectations() {{
            exactly(1).of(processor).process("1234", 5);
        }});
        shoppingBasket.addItem(item);
        shoppingBasket.checkout();
    }
}
