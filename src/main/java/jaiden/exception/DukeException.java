package jaiden.exception;

import java.util.Objects;

/**
 * Class for exceptions from Jaiden.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructor.
     */
    public DukeException() {}

    /**
     * Constructor with error message.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @inheritDoc
     */
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
