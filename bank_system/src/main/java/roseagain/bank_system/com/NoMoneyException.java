package roseagain.bank_system.com;

public class NoMoneyException extends RuntimeException { // Ну чисто свое создал - тут просто сообщение будет прокидываться
    // не стал сильно заморачиваться
    public NoMoneyException(String message) {
        super(message);
    }
}
