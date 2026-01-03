
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class SavingsAccount {
    private String accountNumber;
    private String holderName;
    private double balance = 0;
    private ArrayList<String> transactions = new ArrayList<>();

    public SavingsAccount(String accNo, String name) {
        this.accountNumber = accNo;
        this.holderName = name;
        transactions.add("Account Created for " + holderName);
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawn: " + amount);
        } else {
            transactions.add("Failed Withdraw: Insufficient Balance");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void showTransactions(JTextArea area) {
        area.setText("");
        for (String t : transactions) {
            area.append(t + "\n");
        }
    }
}

public class BankAppGUI {

    static SavingsAccount account;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Bank Application");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField accNoField = new JTextField();
        JTextField nameField = new JTextField();
        JButton createBtn = new JButton("Create Account");

        topPanel.add(new JLabel("Account Number:"));
        topPanel.add(accNoField);
        topPanel.add(new JLabel("Account Holder Name:"));
        topPanel.add(nameField);
        topPanel.add(new JLabel(""));
        topPanel.add(createBtn);

        frame.add(topPanel, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        frame.add(scroll, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton balanceBtn = new JButton("Check Balance");
        JButton historyBtn = new JButton("Transaction History");

        bottomPanel.add(depositBtn);
        bottomPanel.add(withdrawBtn);
        bottomPanel.add(balanceBtn);
        bottomPanel.add(historyBtn);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        depositBtn.setEnabled(false);
        withdrawBtn.setEnabled(false);
        balanceBtn.setEnabled(false);
        historyBtn.setEnabled(false);

        createBtn.addActionListener(e -> {
            String accNo = accNoField.getText();
            String name = nameField.getText();

            if (accNo.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Enter all details");
                return;
            }

            account = new SavingsAccount(accNo, name);
            area.setText("Account Created Successfully\n");

            depositBtn.setEnabled(true);
            withdrawBtn.setEnabled(true);
            balanceBtn.setEnabled(true);
            historyBtn.setEnabled(true);

            accNoField.setEditable(false);
            nameField.setEditable(false);
            createBtn.setEnabled(false);
        });

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter deposit amount:");
            if (input != null) account.deposit(Double.parseDouble(input));
        });

        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter withdrawal amount:");
            if (input != null) account.withdraw(Double.parseDouble(input));
        });

        balanceBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Balance: " + account.getBalance());
        });

        historyBtn.addActionListener(e -> {
            account.showTransactions(area);
        });

        frame.setVisible(true);
    }
}
