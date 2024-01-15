/**
 * Exception for wrong coordinates
 */
public class BadCoordsException extends RuntimeException {

    public BadCoordsException() {
    }

    public BadCoordsException(String message) {
        super(message);
    }

    public BadCoordsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadCoordsException(Throwable cause) {
        super(cause);
    }

    public BadCoordsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
