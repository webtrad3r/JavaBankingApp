package bankapplication.BankConsoleApp;

import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Bank {

    private static Account[] accounts;
    public static int SIZE = 2;
    private static Scanner in;

    public Bank() {
        accounts = new Account[SIZE];
        in = new Scanner(System.in);
    }

    public void addAccount(Account account) {
        if (account == null) {
            return;
        }

        boolean isFull = (Arrays.stream(accounts).filter(i -> i != null).count() < accounts.length);

        if (isFull) {
            reallocate(accounts.length * 2);
        }

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                break;
            }
        }
    }

    // Make a deposit
    public static void makeDeposit() {
        System.out.println("\nDeposit: ");
        Account account = findAccount();

        if (account == null) {
            System.out.println("\nAccount does not exist");
            return;
        }
        System.out.println("\nHow much would you like to deposit: ");
        double deposit = in.nextDouble();
        account.deposit(Double.parseDouble(new DecimalFormat(".##").format(deposit)));
        System.out.println(account);
        System.out.println("Deposit Complete");
    }

    public static void makeWithdrawal() {
        System.out.println("\nWithdrawal: ");
        Account account = findAccount();

        if (account == null) {
            System.out.println("\nAccount does not exist");
            return;
        }
        System.out.println("\nHow much would you like to withdraw: ");
        double withdraw = in.nextDouble();
        account.withdraw(Double.parseDouble(new DecimalFormat(".##").format(withdraw)));
        System.out.println(account);
        System.out.println("Withdrawal Completed");

    }

    public static void accountInfo() {
        System.out.println("\nAccount Info: ");
        Account account = findAccount();

        if (account == null) {
            System.out.println("\nAccount does not exist");
            return;
        }
        System.out.println(account);
        System.out.println("\nComplete.");
    }
    
    public static void transfer() {
        System.out.println("\nTransfer: ");
        Account account = findAccount();
        
        if (account == null) {
            System.out.println("\nAccount does not exist");
            return;
        }
        
        System.out.println("\nHow much would you like to transfer: ");
        double transfer = in.nextDouble();
        account.transfer(Double.parseDouble(new DecimalFormat(".##").format(transfer)));
        System.out.println(account);
        System.out.println("\nTransfer completed ");
    }
    
    public static void transactionHistory() {
        System.out.println("\nTransaction History: ");
        Account account = findAccount();
        
        if (account == null) {
            System.out.println("\nAccount does not exist");
            return;
        }
        System.out.println(account);
        System.out.println("\nComplete.");
    }

    public static Account findAccount() {
        String name;
        String surname;
        int accountNumber = 0;

        System.out.println("\nEnter customer's name: ");
        name = in.next();
        
        System.out.println("\nEnter customer's surname: ");
        surname = in.next();

        System.out.println("\nAccount #: ");
        accountNumber = in.nextInt();
        return (getAccount(name, surname, accountNumber));
    }

    public Account getAccount(int i) {
        return ((i >= 0 && i < accounts.length) ? accounts[i] : null);
    }

    public static Account getAccount(String name, String surname, int accountNumber) {
        Account account = null;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getCustomer().getName().equalsIgnoreCase(name) && accounts[i].getNumber() == accountNumber) {
                    account = accounts[i];
                    break;
                }
            }
        }
        return account;
    }

    public void reallocate(int size) {
        System.out.println("Reallocate ");
        if (size < 0) {
            return;
        }
        SIZE = size;
        Account[] temp = accounts;
        accounts = new Account[SIZE];
        System.arraycopy(temp, 0, accounts, 0, temp.length);

    }

}
