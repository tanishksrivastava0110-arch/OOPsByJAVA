class BankAccount {
    int balance = 500;

    synchronized void withdraw(int amount) {
        System.out.println("Withdrawal thread started...");

        if (balance < amount) {
            System.out.println("Insufficient balance: ");
            System.out.println("Waiting for deposit...");

            try {
                wait();
            } 
            catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        balance = balance - amount;
        System.out.println("Withdrawal of " + amount + " successful.");
        System.out.println("Remaining balance: " + balance);
    }

    synchronized void deposit(int amount) {
        System.out.println("Deposit thread started...");

        balance = balance + amount;

        System.out.println("Deposited " + amount);
        System.out.println("New balance: " + balance);
        notify();
    }
}


// Withdrawal Thread
class WithdrawalThread extends Thread {
    BankAccount account;
    int amount;

    WithdrawalThread(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        try {
            Thread.sleep(1000);
        }
         catch (InterruptedException e) {
            System.out.println(e);
        }

        account.withdraw(amount);
    }
}

class DepositThread extends Thread {
    BankAccount account;
    int amount;

    DepositThread(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        account.deposit(amount);
    }
}


// Main class
public class interthread {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        System.out.println("Initial balance: " + account.balance);

        WithdrawalThread w =
            new WithdrawalThread(account, 1000);

        DepositThread d =
            new DepositThread(account, 1000);

        w.start();
        d.start();
    }
}
