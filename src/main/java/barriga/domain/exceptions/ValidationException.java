package barriga.domain.exceptions;

import java.io.Serial;

public class ValidationException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = 3186639474437673544L;

    public ValidationException(String message) {
        super(message);
    }
}
