package ru.otus.java.pro.homework07.ex01_bad;

public class MockChatServer {
    private AuthenticationService authenticationService;

    public void start() {
        try {
            authenticationService = new AuthenticationService();
            authenticationService.init();



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            authenticationService.shutdown();
        }
    }
}
