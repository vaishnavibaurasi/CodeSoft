package com.atm;

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00); // Initial balance set to $1000
        ATM atm = new ATM(account);
        atm.handleUserInput();
    }
}