package pl.edu.agh.toik.infun.exceptions;

public class RoomAlreadyExistsException extends InFunException {
    public RoomAlreadyExistsException() {
        super();
    }

    public RoomAlreadyExistsException(String message) {
        super(message);
    }

    public RoomAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
