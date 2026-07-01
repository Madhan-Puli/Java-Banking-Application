import java.util.ArrayList;

import javax.swing.JTextArea;

public class Account {
    protected long accountNumber;
    protected String holderName;
    protected double balance = 0;
    protected ArrayList<String> transactions = new ArrayList<>();

    public Account(long accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;

        System.out.println("Account created");
    } 

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited money: "+ amount);
    }
    public void withdraw(double amount) {
        if(amount <= balance) {
            balance -= amount;
            transactions.add("Withdraw: " + amount);
        } else {
            transactions.add("withdraw failed(Insufficient balance)");
        } 
    }
    public double getBalance() {
        return balance;
    }

    public void showTransactionsGUI(JTextArea area) {
        area.setText(""); //remove old text
        for(String t : transactions) {
            area.append(t + "\n");
        }
    }

}