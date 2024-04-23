package ru.otus.java.pro.homework02.test;

import ru.otus.java.pro.homework02.BankAccount;
import ru.otus.java.pro.homework02.annotation.*;
import ru.otus.java.pro.homework02.exception.TestFailedException;

import static ru.otus.java.pro.homework02.annotation.TestPriority.*;

public class BankAccountTest {
    private static Integer turnoverOfFunds;

    BankAccount bankAccount;

    @BeforeSuite
    public static void initializeCounter() {
        turnoverOfFunds = 0;
    }

    @AfterSuite
    public static void printTurnoverOfFunds() {
        System.out.printf("turnover of funds: %s\n", turnoverOfFunds);
    }

    @Before
    public void setUp() throws Exception {
        bankAccount = new BankAccount(1000, "EUR");
    }

    @Test
    public void correctAddMoneyTest() throws Exception {
        System.out.println("Running 2nd test");
        bankAccount.addMoney(500);
        turnoverOfFunds += 500;
        var actual = bankAccount.getAccountBalance();
        var expected = 1500;
        if (expected != actual)
            throw new TestFailedException();
    }

    @Test(priority = TEN)
    public void incorrectAddMoneyTest() throws TestFailedException {
        System.out.println("Running 1st test");
        bankAccount.addMoney(500);
        turnoverOfFunds += 500;
        var actual = bankAccount.getAccountBalance();
        var expected = 5;
        if (expected != actual)
            throw new TestFailedException();
    }

    @Test(priority = ONE)
    public void correctConversionTest() throws Exception {
        System.out.println("Running 3rd test");
        var expected = new BankAccount(50000, "RUB");
        bankAccount.convertToRub(50);
        if (!expected.equals(bankAccount))
            throw new TestFailedException();
    }

    @After
    public void after() throws Exception {
        if (bankAccount == null)
            throw new TestFailedException();
    }
}
