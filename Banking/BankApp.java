import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BankApp extends JFrame {

    private double balance = 0;
    private ArrayList<String> transactions = new ArrayList<>();
    private JTextField txtAmount;
    private JTextArea txtArea;

    public BankApp() {

        setTitle("Bank Application");
        setSize(550, 680);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel("BANK APPLICATION");
        lblTitle.setBounds(180, 20, 200, 30);
        add(lblTitle);

        JLabel lblAmount = new JLabel("Enter Amount:");
        lblAmount.setBounds(30, 80, 120, 25);
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(150, 80, 150, 25);
        add(txtAmount);

        JButton btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(30, 130, 120, 30);
        add(btnDeposit);

        JButton btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setBounds(170, 130, 120, 30);
        add(btnWithdraw);

        JButton btnBalance = new JButton("Check Balance");
        btnBalance.setBounds(310, 130, 150, 30);
        add(btnBalance);

        JButton btnHistory = new JButton("Transaction History");
        btnHistory.setBounds(150, 180, 200, 30);
        add(btnHistory);

        txtArea = new JTextArea();
        txtArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtArea);
        scroll.setBounds(30, 240, 480, 380);
        add(scroll);

        btnDeposit.addActionListener(e -> deposit());
        btnWithdraw.addActionListener(e -> withdraw());
        btnBalance.addActionListener(e -> showBalance());
        btnHistory.addActionListener(e -> showHistory());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void deposit() {
        try {
            double amt = Double.parseDouble(txtAmount.getText());
            if (amt <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid amount");
                return;
            }
            balance += amt;
            transactions.add("Deposited: " + amt);
            txtArea.append("Deposited: " + amt + "\n");
            txtAmount.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter valid number");
        }
    }

    private void withdraw() {
        try {
            double amt = Double.parseDouble(txtAmount.getText());
            if (amt <= 0 || amt > balance) {
                JOptionPane.showMessageDialog(this, "Insufficient balance");
                return;
            }
            balance -= amt;
            transactions.add("Withdrawn: " + amt);
            txtArea.append("Withdrawn: " + amt + "\n");
            txtAmount.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter valid number");
        }
    }

    private void showBalance() {
        txtArea.append("Current Balance: " + balance + "\n");
    }

    private void showHistory() {
        txtArea.append("---- Transaction History ----\n");
        for (String t : transactions) {
            txtArea.append(t + "\n");
        }
    }
}
