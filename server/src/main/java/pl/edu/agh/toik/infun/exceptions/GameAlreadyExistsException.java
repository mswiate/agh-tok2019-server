package pl.edu.agh.toik.infun.exceptions;

public class GameAlreadyExistsException extends InFunException {
    public GameAlreadyExistsException() {
        super();
    }

    public GameAlreadyExistsException(String message) {
        super(message);
    }

    public GameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
