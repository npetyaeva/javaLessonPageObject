package ru.netology.data;

import lombok.Value;

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
}
