package bankapplication.BankConsoleApp;

import static bankapplication.BankConsoleApp.Bank.findAccount;
import java.util.Scanner;
import java.text.DecimalFormat;

public class BankTest {

    private static Bank bank;
    private static int choice;
    private static Scanner in;
    private static boolean isRunning = true;

    public static void main(String[] args) {
        in = new Scanner(System.in);

        bank = new Bank();
        while (isRunning) {
            options();
            choice = in.nextInt();
            process(choice);
        }

    }

    public static void options() {
        System.out.println("\n*****Welcome to NationNarrow Building Society*****");
        System.out.println("\r");
        System.out.println("1. Open a new account.");
        System.out.println("2. Deposit.");
        System.out.println("3. Withdraw.");
        System.out.println("4. View Available Balance.");
        System.out.println("5. Transfers.");
        System.out.println("6. View Transaction History.");
        System.out.println("7. Exit.");
        System.out.println("\r");
        System.out.println("Please select an option: ");
    }

    public static void process(int choice) {
        switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                Bank.makeDeposit();
                break;
            case 3:
                Bank.makeWithdrawal();
                break;
            case 4:
                Bank.accountInfo();
                break;
            case 5:
                Bank.transfer();
                break;
            case 6:
                Bank.transactionHistory();
                break;
            case 7:
                System.out.println("Thank you!");
                isRunning = false;
                break;
            default:
                System.out.println("Not a valid choice!");
        }
    }

    public static void createAccount() {
        Customer customer = null;
        Account account = null;
        String name, surname, address, postcode, telephoneNumber, emailAddress;
        String accountType = "";
        int age, accountNumber = 0;
        int customerNumber;

        // Get new customer's info
        System.out.println("\nEnter customer's first name: ");
        name = in.next();
        System.out.println("\nEnter customer's surname: ");
        surname = in.next();
        System.out.println("\nEnter customer's date of birth: ");
        age = in.nextInt();
        System.out.println("\nEnter customer's address (first line of the address): ");
        address = in.next();
        address += in.nextLine();
        System.out.println("\nEnter customer's post code: ");
        postcode = in.nextLine();
        System.out.println("\nEnter customer's phone number: ");
        telephoneNumber = in.nextLine();
        System.out.println("\nEnter customer's email address: ");
        emailAddress = in.nextLine();

        // check age to set type of customer
        if (age >= 65) {
            customer = new Senior(name, surname, address, postcode, age, telephoneNumber, emailAddress);
        } else if (age < 65 && age >= 16) {
            customer = new Adult(name, surname, address, postcode, age, telephoneNumber, emailAddress);
        } else if (age < 16) {
            customer = new Student(name, surname, address, postcode, age, telephoneNumber, emailAddress);
        } else {
            customer = new Adult(name, surname, address, postcode, age, telephoneNumber, emailAddress);
        }

        System.out.println("\nEnter the 3 digit account number#: ");
        accountNumber = in.nextInt();
        // check what type of account this is: personal or business
        System.out.println("\nEnter account type: \"p\" for personal \"b\" for business");
        accountType = in.next();
        if (accountType.equalsIgnoreCase("p")) {
            System.out.println("Creating personal account");
            account = new SavingsAccount(customer, accountNumber);
        } else if (accountType.equalsIgnoreCase("b")) {
            System.out.println("Creating business account");
            account = new CheckingAccount(customer, accountNumber);
        } else {
            System.out.println("Not a valid option! Please try again");
        }
        in.nextLine(); // Clears the scanner by advancing the scanner to the next line

        System.out.println("Initial deposit: ");
        double deposit = in.nextDouble();
        if (deposit > 0) {
            account.setBalance(account.getBalance() + Double.parseDouble(new DecimalFormat(".##").format(deposit)));
        }
        in.nextLine(); // Clears the scanner by advancing the scanner to the next line
        // Add an account
        bank.addAccount(account);
        System.out.println("\nBank: "
                + (account instanceof CheckingAccount ? "Personal " : "Business ")
                + "Account \nName: " + name + "\nAge: " + age + "\nAccount Number: " + accountNumber + "\nDeposit: " + deposit + "\nCreated successfully.");
    }

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
        System.out.println("Deposit Completed");
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
        return (bank.getAccount(name, surname, accountNumber));
    }

    public static Bank getBank() {
        return bank;
    }
}
