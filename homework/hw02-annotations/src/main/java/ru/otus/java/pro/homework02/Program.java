package ru.otus.java.pro.homework02;

import ru.otus.java.pro.homework02.test.BankAccountTest;

import static ru.otus.java.pro.homework02.Launcher.launch;

public class Program {
    public static void main(String[] args) {
        try {
            launch(BankAccountTest.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
