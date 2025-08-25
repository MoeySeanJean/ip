package jaiden.exception;

import java.util.Objects;

public class DukeException extends Exception {
    private String message;

    public DukeException() {}

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DukeException other)) {
            return false;
        }
        return Objects.equals(message, other.message);
    }
}
