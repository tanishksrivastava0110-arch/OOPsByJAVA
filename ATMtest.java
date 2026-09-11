import java.util.*;
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
public class ATMtest {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double balance = sc.nextDouble();
        double withdrawAmount = sc.nextDouble();
        try {
            withdraw(balance, withdrawAmount);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } 
        finally {
            System.out.println("Transaction attempt completed.");
        }
    }

    static void withdraw(double balance, double amount)
            throws InsufficientBalanceException {

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient balance. Withdrawal amount exceeds available balance."
            );
        } else {
            System.out.println( "Withdrawal successful. New balance: " + (balance - amount));
        }
    }
}
