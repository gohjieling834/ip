package berry.data;

/**
 * Represents an exception specific to the Berry chatbot.
 */
public class BerryException extends RuntimeException {
    public BerryException(String message) {
        super(message);
    }
}