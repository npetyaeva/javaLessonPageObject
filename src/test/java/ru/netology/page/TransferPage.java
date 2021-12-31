package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper.CardInfo;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement headerTransferPage = $(byText("Пополнение карты"));
    private final SelenideElement amountField = $("[data-test-id='amount'] input");
    private final SelenideElement cardFromField = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement errorPopup = $("[data-test-id='error-notification'] .notification__content");

    public TransferPage() {
        headerTransferPage.shouldBe(visible);
    }

    public DashboardPage makeTransfer(String amount, CardInfo cardNumber) {
        amountField.setValue(amount);
        cardFromField.setValue(cardNumber.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public DashboardPage amountEmptyTransfer(CardInfo cardNumber) {
        cardFromField.setValue(cardNumber.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public String cardEmptyTransfer(String amount) {
        amountField.setValue(amount);
        transferButton.click();
        return errorPopup.shouldBe(visible).text();
    }

    public String cardInvalidTransfer(String amount, CardInfo cardNumber) {
        amountField.setValue(amount);
        transferButton.click();
        return errorPopup.shouldBe(visible).text();
    }
}
