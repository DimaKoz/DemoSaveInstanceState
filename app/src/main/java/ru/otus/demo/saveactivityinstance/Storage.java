package ru.otus.demo.saveactivityinstance;

public class Storage {

    private String mMessage;

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    private Storage() {
    }

    public static Storage getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final Storage INSTANCE = new Storage();
    }
}
