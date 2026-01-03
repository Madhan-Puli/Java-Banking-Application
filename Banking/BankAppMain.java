import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BankAppMain extends JFrame {

    // ===== BANK DATA =====
    private String accountNumber;
    private String holderName;
    private double balance = 0.0;
    private ArrayList<String> transactions = new ArrayList<>();

    // ===== BANK UI =====
    private JTextField txtAmount;
    private JTextArea txtHistory;

    // ===== BANK CONSTRUCTOR =====
    public BankAppMain(String accNo, String name) {
        this.accountNumber = accNo;
        this.holderName = name;

        setTitle("Bank Application");
        setSize(550, 650);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblAcc = new JLabel("Account No: " + accountNumber);
        lblAcc.setBounds(30, 20, 300, 25);
        add(lblAcc);

        JLabel lblName = new JLabel("Holder Name: " + holderName);
        lblName.setBounds(30, 50, 300, 25);
        add(lblName);

        JLabel lblAmount = new JLabel("Enter Amount:");
        lblAmount.setBounds(30, 90, 100, 25);
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(150, 90, 150, 25);
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
        btnHistory.setBounds(30, 180, 200, 30);
        add(btnHistory);

        txtHistory = new JTextArea();
        txtHistory.setBounds(30, 230, 470, 350);
        txtHistory.setEditable(false);
        add(txtHistory);

        // ===== BUTTON ACTIONS =====

        btnDeposit.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(txtAmount.getText());
                if (amt <= 0) {
                    JOptionPane.showMessageDialog(this, "Enter valid amount");
                    return;
                }
                balance += amt;
                transactions.add("Deposited: " + amt);
                JOptionPane.showMessageDialog(this, "Deposit successful");
                txtAmount.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });

        btnWithdraw.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(txtAmount.getText());
                if (amt <= 0) {
                    JOptionPane.showMessageDialog(this, "Enter valid amount");
                    return;
                }
                if (amt > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance");
                    return;
                }
                balance -= amt;
                transactions.add("Withdrawn: " + amt);
                JOptionPane.showMessageDialog(this, "Withdraw successful");
                txtAmount.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input");
            }
        });

        btnBalance.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Current Balance: " + balance)
        );

        btnHistory.addActionListener(e -> {
            txtHistory.setText("");
            for (String t : transactions) {
                txtHistory.append(t + "\n");
            }
        });

        setVisible(true);
    }

    // ===== MAIN METHOD (LOGIN + ACCOUNT DETAILS) =====
    public static void main(String[] args) {

        // ===== LOGIN FRAME =====
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 200);
        loginFrame.setLayout(null);
        loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(30, 30, 80, 25);
        loginFrame.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(120, 30, 120, 25);
        loginFrame.add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(30, 70, 80, 25);
        loginFrame.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(120, 70, 120, 25);
        loginFrame.add(txtPass);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(90, 120, 100, 30);
        loginFrame.add(btnLogin);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {

                // ===== ACCOUNT DETAILS FRAME =====
                JFrame accFrame = new JFrame("Account Details");
                accFrame.setSize(350, 250);
                accFrame.setLayout(null);
                accFrame.setLocationRelativeTo(null);

                JLabel lblAcc = new JLabel("Account Number:");
                lblAcc.setBounds(30, 40, 120, 25);
                accFrame.add(lblAcc);

                JTextField txtAccNo = new JTextField();
                txtAccNo.setBounds(160, 40, 140, 25);
                accFrame.add(txtAccNo);

                JLabel lblName = new JLabel("Holder Name:");
                lblName.setBounds(30, 80, 120, 25);
                accFrame.add(lblName);

                JTextField txtName = new JTextField();
                txtName.setBounds(160, 80, 140, 25);
                accFrame.add(txtName);

                JButton btnContinue = new JButton("Continue");
                btnContinue.setBounds(110, 140, 120, 30);
                accFrame.add(btnContinue);

                // ===== MOST IMPORTANT LINE LOCATION =====
                btnContinue.addActionListener(ev -> {
                    String accNo = txtAccNo.getText();
                    String name = txtName.getText();

                    if (accNo.isEmpty() || name.isEmpty()) {
                        JOptionPane.showMessageDialog(accFrame, "Enter all details");
                        return;
                    }

                    // ✅ OPEN BANK APPLICATION
                    new BankAppMain(accNo, name);

                    accFrame.dispose();
                });

                accFrame.setVisible(true);
                loginFrame.dispose();

            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid login");
            }
        });

        loginFrame.setVisible(true);
    }
}
