package pl.edu.agh.toik.infun.exceptions;

public class NoMoreAvailableTasksException extends InFunException {
    public NoMoreAvailableTasksException() {
        super();
    }

    public NoMoreAvailableTasksException(String message) {
        super(message);
    }

    public NoMoreAvailableTasksException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoreAvailableTasksException(Throwable cause) {
        super(cause);
    }
}
