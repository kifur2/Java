package view;

import input.Input;

import javax.swing.*;
import java.util.Vector;

import triode.Triode;

public class CalculatorView {
    private JPanel mainPanel;
    private JButton zeroButton;
    private JButton dotButton;
    private JButton equalsButton;
    private JButton plusButton;
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton multiplicationButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton divisionButton;
    private JButton minusButton;
    private JButton backspaceButton;
    private JButton clearButton;
    private JButton leftParenthesisButton;
    private JButton rightParenthesisButton;
    private JTextField ioTextField;

    private static final Vector<Triode> infoVector = new Vector<>();

    private Input myInput;

    private void printinfoVector() {
        for (Triode triode : infoVector) {
            System.out.println(triode.toString());
        }
        System.out.println();
    }

    public CalculatorView() {
        equalsButton.addActionListener(e -> {
            try {
                ioTextField.setText(ioTextField.getText() + "=");
                String sVal = ioTextField.getText();
                double result = myInput.inputting(sVal);
                ioTextField.setText(Double.toString(result));
                infoVector.clear();
                if (ioTextField.getText(0, 1).charAt(0) == '0')
                    infoVector.addElement(new Triode(true, true, 1));
                else
                    infoVector.addElement(new Triode(true, true, 0));
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "W działaniu występuje dzielenie przez 0, albo niewłaściwa liczba nawiasów!");
            }
        });
        zeroButton.addActionListener(e -> {
            try {
                if (infoVector.lastElement().getIsANumber() || ioTextField.getText().length() == 0 || (ioTextField.getText().length() > 0 && !ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("0")))
                    ioTextField.setText(ioTextField.getText() + "0");
                else throw new UnsupportedOperationException();
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Cyfry '0' nie można dać jako następną cyfrę liczby '0'!");
            }
        });

        oneButton.addActionListener(e -> {
            try {
                if (!infoVector.lastElement().getIsANumber()) {
                    if (ioTextField.getText().length() > 0 && ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("0"))
                        ioTextField.setText(ioTextField.getText(0, ioTextField.getText().length() - 1));
                    infoVector.addElement(new Triode(true, false, ioTextField.getText().length()));
                }
                ioTextField.setText(ioTextField.getText() + "1");
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Coś nie tak!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

        twoButton.addActionListener(e -> {
            try {
                if (!infoVector.lastElement().getIsANumber()) {
                    if (ioTextField.getText().length() > 0 && ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("0"))
                        ioTextField.setText(ioTextField.getText(0, ioTextField.getText().length() - 1));
                    infoVector.addElement(new Triode(true, false, ioTextField.getText().length()));
                }
                ioTextField.setText(ioTextField.getText() + "2");
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Coś nie tak!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

        threeButton.addActionListener(e -> {
            try {
                if (!infoVector.lastElement().getIsANumber()) {
                    if (ioTextField.getText().length() > 0 && ioTextField.getText().length() > 0 && ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("0"))
                        ioTextField.setText(ioTextField.getText(0, ioTextField.getText().length() - 1));
                    infoVector.addElement(new Triode(true, false, ioTextField.getText().length()));
                }

                ioTextField.setText(ioTextField.getText() + "3");
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Coś nie tak!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

        fourButton.addActionListener(e -> {
            try {
                if (!infoVector.lastElement().getIsANumber()) {
                    if (ioTextField.getText().length() > 0 && ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("0"))
                        ioTextField.setText(ioTextField.getText(0, ioTextField.getText().length() - 1));
                    infoVector.addElement(new Triode(true, false, ioTextField.getText().length()));
                }
                ioTextField.setText(ioTextField.getText() + "4");
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Coś nie tak!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

        fiveButton.addActionListener(e -> {
            try {
                if (!infoVector.lastElement().getIsANumber()) {
                    if (ioTextField.getText().length() > 0 && ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("0"))
                        ioTextField.setText(ioTextField.getText(0, ioTextField.getText().length() - 1));
                    infoVector.addElement(new Triode(true, false, ioTextField.getText().length()));
                }
                ioTextField.setText(ioTextField.getText() + "5");
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Coś nie tak!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

        sixButton.addActionListener(e -> {
            try {
                if (!infoVector.lastElement().getIsANumber()) {
                    if (ioTextField.getText().length() > 0 && ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("0"))
                        ioTextField.setText(ioTextField.getText(0, ioTextField.getText().length() - 1));
                    infoVector.addElement(new Triode(true, false, ioTextField.getText().length()));
                }
                ioTextField.setText(ioTextField.getText() + "6");
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Coś nie tak!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

        sevenButton.addActionListener(e -> {
            try {
                if (!infoVector.lastElement().getIsANumber()) {
                    if (ioTextField.getText().length() > 0 && ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("0"))
                        ioTextField.setText(ioTextField.getText(0, ioTextField.getText().length() - 1));
                    infoVector.addElement(new Triode(true, false, ioTextField.getText().length()));
                }
                ioTextField.setText(ioTextField.getText() + "7");
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Coś nie tak!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

        eightButton.addActionListener(e -> {
            try {
                if (!infoVector.lastElement().getIsANumber()) {
                    if (ioTextField.getText().length() > 0 && ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("0"))
                        ioTextField.setText(ioTextField.getText(0, ioTextField.getText().length() - 1));
                    infoVector.addElement(new Triode(true, false, ioTextField.getText().length()));
                }
                ioTextField.setText(ioTextField.getText() + "8");
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Coś nie tak!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

        nineButton.addActionListener(e -> {
            try {
                if (!infoVector.lastElement().getIsANumber()) {
                    if (ioTextField.getText().length() > 0 && ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("0"))
                        ioTextField.setText(ioTextField.getText(0, ioTextField.getText().length() - 1));
                    infoVector.addElement(new Triode(true, false, ioTextField.getText().length()));
                }
                ioTextField.setText(ioTextField.getText() + "9");
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Coś nie tak!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

        leftParenthesisButton.addActionListener(e -> {
            try {
                if (infoVector.lastElement().getIsANumber().equals(false)) {
                    ioTextField.setText(ioTextField.getText() + "(");
                    //printinfoVector();
                }
                else throw new UnsupportedOperationException();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Znak '(' można dać wyłącznie po znaku !");
            }
        });

        rightParenthesisButton.addActionListener(e -> {
            try {
                if (infoVector.lastElement().getIsANumber().equals(true)) {
                    ioTextField.setText(ioTextField.getText() + ")");
                    //printinfoVector();
                }
                else throw new UnsupportedOperationException();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Znak ')' można dać wyłącznie po liczbie !");
            }
        });

        dotButton.addActionListener(e -> {
            try {
                if (!infoVector.lastElement().getHasADot()) {
                    if (!infoVector.lastElement().getIsANumber() && ioTextField.getText().length() > 0 && ioTextField.getText().charAt(ioTextField.getText().length() - 1) == '0') {
                        infoVector.addElement(new Triode(true, true, ioTextField.getText().length()));
                        ioTextField.setText(ioTextField.getText() + ".");
                    } else if (infoVector.lastElement().getIsANumber()) {
                        ioTextField.setText(ioTextField.getText() + ".");
                        infoVector.lastElement().setHasADot(true);
                    }
                }
                else throw new UnsupportedOperationException();
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Znak '.' można dać wyłącznie w liczbie, w której nie występuje wcześniej inna kropka !");
            }
        });

        plusButton.addActionListener(e -> {
            try {
                if (infoVector.lastElement().getIsANumber().equals(true) || ioTextField.getText(ioTextField.getText().length()-1,1).equals("0")) {
                    infoVector.addElement(new Triode(false, false, ioTextField.getText().length()));
                    ioTextField.setText(ioTextField.getText() + "+");
                    //printinfoVector();
                }
                else throw new UnsupportedOperationException();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Znak '+' można dać wyłącznie po liczbie !");
            }
        });

        minusButton.addActionListener(e -> {
            try {
                if(ioTextField.getText().isEmpty() ||ioTextField.getText(ioTextField.getText().length() - 1, 1).equals("(") || ioTextField.getText(ioTextField.getText().length() - 1, 1).equals(")") || infoVector.lastElement().getIsANumber().equals(true) || ioTextField.getText(ioTextField.getText().length()-1,1).equals("0")) {
                    infoVector.addElement(new Triode(false, false, ioTextField.getText().length()));
                    ioTextField.setText(ioTextField.getText() + "-");
                    //printinfoVector();
                }
                else
                    throw new UnsupportedOperationException();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Znak '-' można dać wyłącznie na początku, po liczbie, lub po '(', ')' !");
            }
        });

        multiplicationButton.addActionListener(e -> {
            try {
                if (infoVector.lastElement().getIsANumber().equals(true)|| ioTextField.getText(ioTextField.getText().length()-1,1).equals("0")) {
                    infoVector.addElement(new Triode(false, false, ioTextField.getText().length()));
                    ioTextField.setText(ioTextField.getText() + "*");
                    //printinfoVector();
                }
                else
                    throw new UnsupportedOperationException();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Znak '*' można dać wyłącznie po liczbie !");
            }
        });

        divisionButton.addActionListener(e -> {
            try {
                if (infoVector.lastElement().getIsANumber().equals(true)|| ioTextField.getText(ioTextField.getText().length()-1,1).equals("0")) {
                    infoVector.addElement(new Triode(false, false, ioTextField.getText().length()));
                    ioTextField.setText(ioTextField.getText() + "/");
                    //printinfoVector();
                }
                else
                    throw new UnsupportedOperationException();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Znak '/' można dać wyłącznie po liczbie !");
            }
        });

        backspaceButton.addActionListener(e -> {
            try {
                if (ioTextField.getText().length() > 0)
                    ioTextField.setText(ioTextField.getText(0, ioTextField.getText().length() - 1));
                if (infoVector.lastElement().getIndex().equals(ioTextField.getText().length()))
                    infoVector.remove(infoVector.size() - 1);
                if (infoVector.isEmpty())
                    infoVector.addElement(new Triode(false, false, 0));
                if (infoVector.size() == 1) {
                    infoVector.set(0, new Triode(false, false, 0));
                    ioTextField.setText("");
                }
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Blad <--!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

        clearButton.addActionListener(e -> {
            try {
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, ioTextField.getText().length()));
                //printinfoVector();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Blad CLR!");
                ioTextField.setText("");
                infoVector.clear();
                infoVector.addElement(new Triode(false, false, 0));
            }
        });

    }

    public static void initRates(CalculatorView frm) {
        frm.myInput = new Input();
        infoVector.addElement(new Triode(false, false, 0));
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Calculator");
        CalculatorView form = new CalculatorView();
        mainFrame.setContentPane(form.mainPanel);
        initRates(form);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
