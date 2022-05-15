package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class ArithmeticEngineTest {
  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();
  Updatable display = context.mock(Updatable.class);

  @Test
  public void updateDisplayWhenANewNumberIsInput() {
    ArithmeticEngine calc = new ArithmeticEngine();
    calc.addObserver(display);
    context.checking(
        new Expectations() {
          {
            exactly(1).of(display).update(5);
          }
        });
    calc.input(5);
  }

  @Test
  public void supportAddingTwoValues() {
    ArithmeticEngine calc = new ArithmeticEngine();
    calc.addObserver(display);
    context.checking(
        new Expectations() {
          {
            exactly(1).of(display).update(5);
            exactly(1).of(display).update(4);
            exactly(1).of(display).update(9);
          }
        });
    calc.input(5);
    calc.input(4);
    calc.apply(Operation.PLUS);
  }

    @Test
    public void supportMinusingTwoValues() {
        ArithmeticEngine calc = new ArithmeticEngine();
        calc.addObserver(display);
        context.checking(
                new Expectations() {
                    {
                        exactly(1).of(display).update(5);
                        exactly(1).of(display).update(4);
                        exactly(1).of(display).update(1);
                    }
                });
        calc.input(5);
        calc.input(4);
        calc.apply(Operation.MINUS);
    }
}
