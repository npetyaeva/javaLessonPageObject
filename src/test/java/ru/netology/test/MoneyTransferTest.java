package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void shouldTransferMoneyFromFirstToSecondCards() {
        assertTrue(dashboardPage.cashIn(1500, 1, 2));
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCards() {
        assertTrue(dashboardPage.cashIn(1500, 2, 1));
    }

    @Test
    void shouldCheckIfFieldAmountEmpty() {
        var text = dashboardPage.fieldAmountEmpty(1, 2);
        assertTrue("Ваши карты".contains(text));
    }

    @Test
    void shouldCheckIfFieldCardFromEmpty() {
        var text = dashboardPage.fieldCardFromEmpty(1500, 1);
        assertTrue("Ошибка! Произошла ошибка".contains(text));
    }

    @Test
    void shouldCheckIfFieldCardInvalid() {
        var text = dashboardPage.fieldCardInvalid(1500, 1);
        assertTrue("Ошибка! Произошла ошибка".contains(text));
    }
}
