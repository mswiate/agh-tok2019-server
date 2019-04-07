package pl.edu.agh.toik.infun.exceptions;


public class NoSuchRoomException extends InFunException {
    public NoSuchRoomException() {
        super();
    }

    public NoSuchRoomException(String message) {
        super(message);
    }

    public NoSuchRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRoomException(Throwable cause) {
        super(cause);
    }
}
