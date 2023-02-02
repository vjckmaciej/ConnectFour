package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ConnectFour extends JFrame implements ActionListener {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(600, 600);
        setLocationRelativeTo(null);
        //panel with all buttons that are placed on game board
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0,0,600,500);
        buttonsPanel.setLayout(new GridLayout(6,7));
        buttonsPanel.setBackground(Color.RED);
        //loop that creates buttons
        int counter = 6;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                char letter = (char)('A' + j);
                arrayOfButtons[i][j] = new JButton();
                arrayOfButtons[i][j].setName("Button"+Character.toString(letter) + Integer.toString(counter));
                arrayOfButtons[i][j].setText(" ");
                arrayOfButtons[i][j].setBackground(Color.LIGHT_GRAY);
                buttonsPanel.add(arrayOfButtons[i][j]);
                arrayOfButtons[i][j].addActionListener(this);
            }
            counter--;
        }
        add(buttonsPanel);

        //button to reset map
        JButton ButtonReset = new JButton("Reset");
        ButtonReset.setName("ButtonReset");
        ButtonReset.setBounds(500, 510, 80, 40);
        ButtonReset.addActionListener(this);
        add(ButtonReset);

        setVisible(true);
        setTitle("Connect Four");
    }
    JButton[][] arrayOfButtons = new JButton[ROWS][COLS];

    int counterSign = 1;
    boolean endOfTheGame = false;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "Reset")) {
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    arrayOfButtons[row][col].setText(" ");
                    arrayOfButtons[row][col].setBackground(Color.LIGHT_GRAY);
                    counterSign = 1;
                    arrayOfButtons[row][col].setEnabled(true);
                    endOfTheGame = false;  }

              }
        }
        if (!endOfTheGame) {
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    if (arrayOfButtons[row][col] == e.getSource()) {
                        // here you have your row and column
                        for (int towerCell = ROWS - 1; towerCell >= 0; towerCell--) {
                            if (arrayOfButtons[towerCell][col].getActionCommand().equals(" ")) {
                                if (counterSign % 2 == 1) {
                                    arrayOfButtons[towerCell][col].setText("X");
                                    if (checkIfConnected(towerCell, col)) {
                                        for (row = 0; row < ROWS; row++) {
                                            for (col  = 0; col < COLS; col++) {
                                                arrayOfButtons[row][col].setEnabled(false);
                                            }
                                        }
                                        endOfTheGame = true;
                                    }
                                } else {
                                    arrayOfButtons[towerCell][col].setText("O");
                                    if (checkIfConnected(towerCell, col)) {
                                        for (row = 0; row < ROWS; row++) {
                                            for (col  = 0; col < COLS; col++) {
                                                arrayOfButtons[row][col].setEnabled(false);
                                            }
                                        }
                                        endOfTheGame = true;
                                    }
                                }
                                counterSign++;
                                break;
                            }
                        }
                    }

                }
            }
        }
    }

    public boolean checkIfConnected(int row, int col) {
        String symbol = arrayOfButtons[row][col].getText();
        System.out.println(symbol);
        int count = 0;
        // Check for a connect four in the horizontal direction
        for (int i = 0; i < COLS; i++) {
            if (arrayOfButtons[row][i].getText().equals(symbol)) {
                count++;
                if (count == 4) {
                    int counter = 0;
                    for (int l = i; counter < 4; counter++) {
                        arrayOfButtons[row][l].setBackground(Color.RED);
                        l--;
                    }
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Check for a connect four in the vertical direction
        count = 0;
        for (int i = 0; i < ROWS; i++) {
            if (arrayOfButtons[i][col].getText().equals(symbol)) {
                count++;
                if (count == 4) {
                    int counter = 0;
                    for (int k = i; counter < 4; counter++) {
                        arrayOfButtons[k][col].setBackground(Color.RED);
                        k--;
                    }
                    return true;
                }
            } else {
                count = 0;
            }
        }

        return checkDiagonally(row, col);
    }


    public boolean checkDiagonally(int row, int col) {
        String symbol = arrayOfButtons[row][col].getText();
        //top left to bottom right
        int count = 0;
        //i = 0, j = 0
        for (int r = 0, c = 0; r  <= 3; r++) {
            int i = r;
            int j = c;
            while (i < ROWS && j < COLS) {
                if (arrayOfButtons[i][j].getText().equals(symbol)) {
                    count++;
                    if (count == 4) {
                        int counter = 0;
                        for (int k = i, l = j; counter < 4;  counter++) {
                            arrayOfButtons[k][l].setBackground(Color.RED);
                            k--;
                            l--;
                        }
                        return true;
                    }
                } else {
                    count = 0;
                }
                i++;
                j++;
            }
        }

        count = 0;
        for (int r = 0, c = 0; c  <= 4; c++) {
            int i = r;
            int j = c;
            while (i < ROWS && j < COLS) {
                if (arrayOfButtons[i][j].getText().equals(symbol)) {
                    count++;
                    if (count == 4) {
                        int counter = 0;
                        for (int k = i, l = j; counter < 4;counter++) {
                            arrayOfButtons[k][l].setBackground(Color.RED);
                            k--;
                            l--;
                        }
                        return true;
                    }
                } else {
                    count = 0;
                }
                i++;
                j++;
            }
        }

        // Check for a connect four in the diagonal (bottom-left to top-right) direction
        count = 0;
        for (int r = ROWS - 1, c = 0; r  >= 3; r--) {
            int i = r;
            int j = c;
            while (i >= 0 && j < COLS) {
                if (arrayOfButtons[i][j].getText().equals(symbol)) {
                    count++;
                    if (count == 4) {
                        int counter = 0;
                        for (int k = i, l = j; counter < 4; counter++ ) {
                            arrayOfButtons[k][l].setBackground(Color.RED);
                            k++;
                            l--;
                        }
                        return true;
                    }
                } else {
                    count = 0;
                }
                i--;
                j++;
            }
        }

        count = 0;
        for (int r = ROWS - 1, c = 0; c  <= 4; c++) {
            int i = r;
            int j = c;
            while (i >= 0 && j < COLS) {
                if (arrayOfButtons[i][j].getText().equals(symbol)) {
                    count++;
                    if (count == 4) {
                        int counter = 0;
                        for (int k = i, l = j; counter < 4; counter++) {
                            arrayOfButtons[k][l].setBackground(Color.RED);
                            k++;
                            l--;
                        }
                        return true;
                    }
                } else {
                    count = 0;
                }
                i--;
                j++;
            }
        }
        return false;
    }

}