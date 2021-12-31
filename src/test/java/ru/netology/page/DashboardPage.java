package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper.CardInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement headerDashboardPage = $(byText("Ваши карты"));
    private final ElementsCollection cards = $$("div[data-test-id]");
    private final String startBalance = "баланс:";
    private final String finishBalance = "р.";

    public DashboardPage() {
        headerDashboardPage.shouldBe(visible);
    }

    public String getHeader() {
        return headerDashboardPage.text();
    }

    public TransferPage selectCardToTransfer(CardInfo card) {
        cards.findBy(text(card.getNumber().substring(15, 19))).$("[data-test-id='action-deposit']").click();
        return new TransferPage();
    }

    public int getCardBalance(CardInfo card) {
        var text = cards.findBy(text(card.getNumber().substring(15, 19))).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        int startIndex = text.indexOf(startBalance);
        int finishIndex = text.indexOf(finishBalance);
        String balance = text.substring(startIndex + startBalance.length(), finishIndex).trim();
        return Integer.parseInt(balance);
    }
}
