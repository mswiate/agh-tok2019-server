package pl.edu.agh.toik.infun.exceptions;

public class CannotRemoveRoomException extends InFunException {
    public CannotRemoveRoomException() {
        super();
    }

    public CannotRemoveRoomException(String message) {
        super(message);
    }

    public CannotRemoveRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotRemoveRoomException(Throwable cause) {
        super(cause);
    }
}

