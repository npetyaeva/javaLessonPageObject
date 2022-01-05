package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class MoneyTransferTest {
    private DashboardPage dashboardPage;

    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:9999/");
        var clientInfo = DataHelper.getClientInfo();
        var loginPage = new LoginPage();
        var verifyPage = loginPage.validLogin(clientInfo);
        var verifyCode = DataHelper.getVerificationCode();
        dashboardPage = verifyPage.validVerify(verifyCode);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int amount = 1500;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCards() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int amount = 1500;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) + amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) - amount;
        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        dashboardPage = transferPage.makeTransfer(String.valueOf(amount), secondCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldCheckIfFieldAmountEmpty() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        var text = transferPage.amountEmptyTransfer(secondCardInfo);
        assertEquals("Ошибка! Произошла ошибка", text);
    }

    @Test
    void shouldCheckIfFieldCardEmpty() {
        var firstCardInfo = getFirstCardInfo();
        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        var text = transferPage.cardEmptyTransfer("1500");
        assertEquals("Ошибка! Произошла ошибка", text);
    }

    @Test
    void shouldCheckIfFieldCardInvalid() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getInvalidCardInfo();
        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        var text = transferPage.cardInvalidTransfer("1500", secondCardInfo);
        assertEquals("Ошибка! Произошла ошибка", text);
    }
}
