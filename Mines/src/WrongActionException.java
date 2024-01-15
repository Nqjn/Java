/**
 * Exception for wrong actions (like marking uncovered field).
 */
public class WrongActionException extends Exception{

    public WrongActionException() {
    }

    public WrongActionException(String message) {
        super(message);
    }

    public WrongActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongActionException(Throwable cause) {
        super(cause);
    }

    public WrongActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
