package pl.edu.agh.toik.infun.exceptions;

public class NoRoomSelectedException extends InFunException {
    public NoRoomSelectedException() {
        super();
    }

    public NoRoomSelectedException(String message) {
        super(message);
    }

    public NoRoomSelectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRoomSelectedException(Throwable cause) {
        super(cause);
    }
}
