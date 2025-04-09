import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ATM_GUI extends JFrame implements ActionListener {
    private int balance = 10000;
    private int tokenNumber, enteredToken;

    // GUI Components
    private JTextField tokenField, amountField;
    private JButton checkBalanceBtn, depositBtn, withdrawBtn, exitBtn;
    private JLabel welcomeLabel, tokenLabel, amountLabel, displayTokenLabel;

    public ATM_GUI() {
        // Window Settings
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the frame

        // Random Token Generation
        Random rand = new Random();
        tokenNumber = rand.nextInt(10000) + 1;

        // Layout
        setLayout(new GridLayout(7, 2, 10, 10));

        // Welcome Label
        welcomeLabel = new JLabel("Welcome to ATM Machine", SwingConstants.CENTER);
        add(welcomeLabel);

        // Token Display Label
        displayTokenLabel = new JLabel("Your Token Number: " + tokenNumber, SwingConstants.CENTER);
        add(displayTokenLabel);

        // Token Input
        tokenLabel = new JLabel("Enter Your Token Number:");
        add(tokenLabel);

        tokenField = new JTextField();
        add(tokenField);

        // Buttons for actions
        checkBalanceBtn = new JButton("Check Balance");
        checkBalanceBtn.addActionListener(this);
        add(checkBalanceBtn);

        depositBtn = new JButton("Deposit");
        depositBtn.addActionListener(this);
        add(depositBtn);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.addActionListener(this);
        add(withdrawBtn);

        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(this);
        add(exitBtn);

        // Amount Input (for deposit/withdraw)
        amountLabel = new JLabel("Enter Amount:");
        add(amountLabel);

        amountField = new JTextField();
        add(amountField);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Check Balance")) {
            enteredToken = Integer.parseInt(tokenField.getText());
            if (enteredToken == tokenNumber) {
                JOptionPane.showMessageDialog(this, "Your Balance: " + balance + " Rs");
            } else {
                JOptionPane.showMessageDialog(this, "Unauthorized Token!");
            }
        }
        else if (actionCommand.equals("Deposit")) {
            enteredToken = Integer.parseInt(tokenField.getText());
            if (enteredToken == tokenNumber) {
                try {
                    // Validate that the entered amount is a valid number and greater than zero
                    int depositAmount = Integer.parseInt(amountField.getText());

                    if (depositAmount <= 0) {
                        JOptionPane.showMessageDialog(this, "Please enter a positive amount for deposit!");
                    } else {
                        balance += depositAmount;
                        JOptionPane.showMessageDialog(this, "Amount Deposited Successfully! New Balance: " + balance + " Rs");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount entered. Please enter a valid number.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Unauthorized Token!");
            }
        }

        else if (actionCommand.equals("Withdraw")) {
            enteredToken = Integer.parseInt(tokenField.getText());
            if (enteredToken == tokenNumber) {
                int withdrawAmount = Integer.parseInt(amountField.getText());
                if (withdrawAmount <= balance) {
                    balance -= withdrawAmount;
                    JOptionPane.showMessageDialog(this, "Amount Withdrawn Successfully! New Balance: " + balance + " Rs");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Unauthorized Token!");
            }
        }
        else if (actionCommand.equals("Exit")) {
            JOptionPane.showMessageDialog(this, "Thank you for using ATM!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ATM_GUI();
    }
}
