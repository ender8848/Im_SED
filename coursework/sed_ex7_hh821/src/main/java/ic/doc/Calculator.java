package ic.doc;

import javax.swing.*;

public class Calculator implements Updatable{
    JTextField textField;

  public static void main(String[] args) {

    new Calculator().display();
  }

  // view
  private void display() {
    JFrame frame = new JFrame("Calculator");
    ArithmeticEngine calc = new ArithmeticEngine();
    calc.addObserver(this);
    frame.setSize(500, 400);

    JPanel panel = new JPanel();
    textField = new JTextField(10);
    panel.add(textField);
    addNumberButtons(calc, panel);
    addPOperationButtons(calc, panel);
    frame.getContentPane().add(panel);

    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // controller
  private void addPOperationButtons(ArithmeticEngine calc, JPanel panel) {
    for (Operation operation : Operation.values()) {
      JButton button = new JButton(operation.value());
      button.addActionListener(e -> calc.apply(operation));
      panel.add(button);
    }
  }

  private void addNumberButtons(ArithmeticEngine calc, JPanel panel) {
    for (int i = 0; i < 5; ++i) {
      JButton button = new JButton(String.valueOf(i));
      int finalI = i;
      button.addActionListener(e -> calc.input(finalI));
      panel.add(button);
    }
  }

    @Override
    public void update(Integer event) {
        textField.setText(String.valueOf(event));
    }
}
