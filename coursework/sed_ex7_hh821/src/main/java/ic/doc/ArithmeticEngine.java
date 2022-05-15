package ic.doc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ArithmeticEngine {
  List<Updatable> observers;
  Stack<Integer> numbers;

  ArithmeticEngine() {
    this.observers = new ArrayList<>();
    this.numbers = new Stack<Integer>();
  }

  public void input(int value) {
    numbers.push(value);
    notifyObservers(value);
  }

  public void apply(Operation operation) {
    if (operation == Operation.PLUS) {
      if (numbers.size() < 2) {
        return;
      } else {
        Integer result = numbers.pop() + numbers.pop();
        numbers.push(result);
        notifyObservers(result);
      }
    }
    if (operation == Operation.MINUS) {
        if (numbers.size() < 2) {
            return;
        } else {
            Integer result = -numbers.pop() + numbers.pop();
            numbers.push(result);
            notifyObservers(result);
        }
    }


  }

  public void addObserver(Updatable observer) {
    observers.add(observer);
  }

  public void removeObserver(Updatable observer) {
    observers.remove(observer);
  }

  public void notifyObservers(Integer event) {
    for (var observer : observers) {
      observer.update(event);
    }
  }
}
