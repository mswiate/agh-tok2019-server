package pl.edu.agh.toik.infun.exceptions;

public class SuchUserExistException extends InFunException {
    public SuchUserExistException() {
        super();
    }

    public SuchUserExistException(String message) {
        super(message);
    }

    public SuchUserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchUserExistException(Throwable cause) {
        super(cause);
    }
}
