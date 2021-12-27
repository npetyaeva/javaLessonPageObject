package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTransferTest {
    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:9999/");
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCards() {
        var clientInfo = DataHelper.getClientInfo();
        var loginPage = new LoginPage();
        var verifyPage = loginPage.validLogin(clientInfo);
        var verifyCode = DataHelper.getVerificationCode();
        var dashboardPage = verifyPage.validVerify(verifyCode);
        assertTrue(dashboardPage.cashIn(1500, 1, 2));
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCards() {
        var clientInfo = DataHelper.getClientInfo();
        var loginPage = new LoginPage();
        var verifyPage = loginPage.validLogin(clientInfo);
        var verifyCode = DataHelper.getVerificationCode();
        var dashboardPage = verifyPage.validVerify(verifyCode);
        assertTrue(dashboardPage.cashIn(1500, 2, 1));
    }
}
