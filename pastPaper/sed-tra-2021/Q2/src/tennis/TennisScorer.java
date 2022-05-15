package tennis;

import javax.swing.*;

public class TennisScorer implements Updatable{
  // important view for update
  JButton playerOneScores;
  JButton playerTwoScores;
  TennisEngine backend;

  private JTextField scoreDisplay;

  public static void main(String[] args) {
    new TennisScorer().display();
  }

  private void display() {
    backend = new TennisEngine();
    backend.addObserver(this);

    JFrame window = new JFrame("Tennis");
    window.setSize(400, 150);

    playerOneScores = new JButton("Player One Scores");
    playerTwoScores = new JButton("Player Two Scores");

    scoreDisplay = new JTextField(20);
    scoreDisplay.setHorizontalAlignment(JTextField.CENTER);
    scoreDisplay.setEditable(false);

    playerOneScores.addActionListener(
            e -> {
              backend.playerOneWinsPoint();
            });

    playerTwoScores.addActionListener(
            e -> {
              backend.playerTwoWinsPoint();
            });

    JPanel panel = new JPanel();
    panel.add(playerOneScores);
    panel.add(playerTwoScores);
    panel.add(scoreDisplay);

    window.add(panel);

    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    window.setVisible(true);

  }

  @Override
  public void update(String event) {
    scoreDisplay.setText(event);
    if (backend.gameHasEnded()) {
      playerOneScores.setEnabled(false);
      playerTwoScores.setEnabled(false);
    }
  }




}