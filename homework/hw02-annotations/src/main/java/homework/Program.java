package homework;

import homework.test.BankAccountTest;

import static homework.Launcher.launch;

public class Program {
    public static void main(String[] args) {
        try {
            launch(BankAccountTest.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
}
