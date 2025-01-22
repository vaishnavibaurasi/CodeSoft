package com.atm;

import java.util.Scanner;

public class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("\nWelcome to the ATM!");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    public void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: $" + account.getBalance());
                        break;

                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        account.deposit(depositAmount);
                        System.out.println("Successfully deposited $" + depositAmount);
                        break;

                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = Double.parseDouble(scanner.nextLine());
                        account.withdraw(withdrawalAmount);
                        System.out.println("Successfully withdrew $" + withdrawalAmount);
                        break;

                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}

