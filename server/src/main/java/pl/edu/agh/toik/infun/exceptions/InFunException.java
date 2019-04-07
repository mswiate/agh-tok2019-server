package pl.edu.agh.toik.infun.exceptions;

public class InFunException extends Exception {
    public InFunException() {
        super();
    }

    public InFunException(String message) {
        super(message);
    }

    public InFunException(String message, Throwable cause) {
        super(message, cause);
    }

    public InFunException(Throwable cause) {
        super(cause);
    }
}
