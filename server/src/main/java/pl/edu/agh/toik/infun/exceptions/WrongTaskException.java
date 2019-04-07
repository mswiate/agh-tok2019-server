package pl.edu.agh.toik.infun.exceptions;

public class WrongTaskException extends InFunException {
    public WrongTaskException() {
        super();
    }

    public WrongTaskException(String message) {
        super(message);
    }

    public WrongTaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongTaskException(Throwable cause) {
        super(cause);
    }
}
