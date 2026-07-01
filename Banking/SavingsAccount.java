import java.util.ArrayList;

public class SavingsAccount extends Account {
    private ArrayList<String> transactions = new ArrayList<>();

    public SavingsAccount(long accountNumber, String holderName) {
        super(accountNumber, holderName);
        transactions.add("Account Created for " + holderName);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            super.deposit(amount);
            transactions.add("Deposited: " + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= super.getBalance()) {
            super.withdraw(amount);
            transactions.add("Withdrawn: " + amount);
        } else {
            transactions.add("Failed withdrawal attempt: " + amount);
        }
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }
}
