package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    public DataHelper() {}

    @Value
    public static class ClientInfo {
        String login;
        String password;
    }

    public static ClientInfo getClientInfo() {
        return new ClientInfo("vasya", "qwerty123");
    }

    /*public static ClientInfo getOtherClientInfo(ClientInfo original) {
        return new ClientInfo("petya", "123qwerty");
    } */

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        String number;
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002");
    }

    public static CardInfo getInvalidCardInfo() {
        Faker faker = new Faker();
        return new CardInfo(faker.numerify("################"));
    }
}
