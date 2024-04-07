package homework.test;

import homework.BankAccount;
import homework.annotations.*;

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

    @Test(priority = 5)
    public void correctAddMoneyTest() throws Exception {
        System.out.println("Running 2nd test");
        bankAccount.addMoney(500);
        turnoverOfFunds += 500;
        var actual = bankAccount.getAccountBalance();
        var expected = 1500;
        if (expected != actual)
            throw new Exception();
    }

    @Test(priority = 10)
    public void incorrectAddMoneyTest() throws Exception {
        System.out.println("Running 1st test");
        bankAccount.addMoney(500);
        turnoverOfFunds += 500;
        var actual = bankAccount.getAccountBalance();
        var expected = 5;
        if (expected != actual)
            throw new Exception();
    }

    @Test(priority = 1)
    public void correctConversionTest() throws Exception {
        System.out.println("Running 3rd test");
        var expected = new BankAccount(50000, "RUB");
        bankAccount.convertToRub(50);
        if (!expected.equals(bankAccount))
            throw new Exception();
    }

    @After
    public void after() throws Exception {
        if (bankAccount == null)
            throw new Exception();
    }
}
