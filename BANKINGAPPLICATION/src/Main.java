import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your Account holder name:");
            String accountHolderName = scanner.nextLine();

            String accountNumber = AccountService.getAccountNumberByName(accountHolderName);
            if (accountNumber == null) {
                System.out.println("Account not found for the given name.");
                return;
            }

            while (true) {
                System.out.println("Select an operation:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Transfer Money");
                System.out.println("5. Exit");
                int choice = scanner.nextInt();

                try {
                    Account account;
                    switch (choice) {
                        // Check balance
                        case 1:
                            account = AccountService.checkBalance(accountNumber);
                            System.out.println("Balance: " + account.getBalance() + " Account holder: " + account.getAccountHolderName());
                            break;
                        // Deposit Money
                        case 2:
                            System.out.println("Enter amount you want to deposit:");
                            double depositAmount = scanner.nextDouble();
                            AccountService.deposit(accountNumber, depositAmount);
                            account = AccountService.checkBalance(accountNumber);
                            System.out.println("Deposited " + depositAmount + " to account " + accountNumber + " " + account.getAccountHolderName());
                            System.out.println("Your new Balance: " + account.getBalance() + " Account holder: " + account.getAccountHolderName());
                            break;
                        // Withdraw money
                        case 3:
                            System.out.println("Enter amount you want to withdraw:");
                            double withdrawAmount = scanner.nextDouble();
                            account = AccountService.checkBalance(accountNumber);
                            if (account.getBalance() < withdrawAmount || account.getBalance()==0.0) {
                                System.out.println("Insufficient balance. Your balance is " + account.getBalance());
                            } else {
                                AccountService.withdraw(accountNumber, withdrawAmount);
                                account = AccountService.checkBalance(accountNumber);
                                System.out.println("Withdrew " + withdrawAmount + " from account " + accountNumber);
                                System.out.println("Your new Balance: " + account.getBalance() + " Account holder: " + account.getAccountHolderName());
                            }
                            break;
                        // Transfer money
                        case 4:
                            System.out.println("Enter the account number to transfer to:");
                            String toAccountNumber = scanner.next();
                            System.out.println("Enter amount you want to transfer:");
                            double transferAmount = scanner.nextDouble();
                            account = AccountService.checkBalance(accountNumber);
                            if (account.getBalance() < transferAmount) {
                                System.out.println("Insufficient balance. Your balance is " + account.getBalance());
                            } else {
                                AccountService.transfer(accountNumber, toAccountNumber, transferAmount);
                                account = AccountService.checkBalance(accountNumber);
                                System.out.println("Transferred " + transferAmount + " from account " + accountNumber + " to account " + toAccountNumber);
                                System.out.println("Your new Balance: " + account.getBalance() + " Account holder: " + account.getAccountHolderName());
                            }
                            break;
                        // Exit
                        case 5:
                            System.out.println("Exiting...");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
