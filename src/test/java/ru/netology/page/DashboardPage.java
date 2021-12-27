package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement header = $("h1"); //[data-test-id='dashboard']");
    private final ElementsCollection cards = $$("ul div[data-test-id]");
    private final ElementsCollection addButton = $$("ul button");
    private final SelenideElement balanceField = $("[data-test-id='amount'] input");
    private final SelenideElement cardFromField = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement errorPopup = $("[data-test-id='error-notification'] .notification__content");

    private final String startBalance = "баланс:";
    private final String finishBalance = "р.";

    public DashboardPage() {
        header.shouldBe(visible);
    }

    public boolean cashIn(int amount, int numberCardFrom, int numberCardTo) {
        int balanceFrom = getCardBalance(cards.get(numberCardFrom - 1));
        int balanceTo = getCardBalance(cards.get(numberCardTo - 1));
        addButton.get(numberCardTo - 1).click();
        balanceField.setValue(String.valueOf(amount));
        cardFromField.setValue("5559 0000 0000 000" + numberCardFrom);
        transferButton.click();
        return (balanceFrom - amount == getCardBalance(cards.get(numberCardFrom - 1))
                && balanceTo + amount == getCardBalance(cards.get(numberCardTo - 1)));
    }

    public String fieldAmountEmpty(int numberCardFrom, int numberCardTo) {
        addButton.get(numberCardTo - 1).click();
        cardFromField.setValue("5559 0000 0000 000" + numberCardFrom);
        transferButton.click();
        return header.shouldBe(visible).text();
    }

    public String fieldCardFromEmpty(int amount, int numberCardTo) {
        addButton.get(numberCardTo - 1).click();
        balanceField.setValue(String.valueOf(amount));
        transferButton.click();
        return errorPopup.shouldBe(visible).text();
    }

    public String fieldCardInvalid(int amount, int numberCardTo) {
        addButton.get(numberCardTo - 1).click();
        balanceField.setValue(String.valueOf(amount));
        cardFromField.setValue("5559 1111 1111 1111");
        transferButton.click();
        return errorPopup.shouldBe(visible).text();
    }

    private int getCardBalance(SelenideElement card) {
        String text = card.text();
        int startIndex = text.indexOf(startBalance);
        int finishIndex = text.indexOf(finishBalance);
        String balance = text.substring(startIndex + startBalance.length(), finishIndex).trim();
        return Integer.parseInt(balance);
    }
}
