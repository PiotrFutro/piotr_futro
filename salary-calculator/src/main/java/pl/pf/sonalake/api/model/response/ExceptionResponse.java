package pl.pf.sonalake.api.model.response;

import java.time.LocalDateTime;

/**
 * Przykłądowa klasa odpowiedzi dedykoana obsłudze wyjątku
 *
 * @author pfutro
 */
public final class ExceptionResponse {

    private final String message;
    private final LocalDateTime timestamp;

    public ExceptionResponse(String message) {
        this.message = message;
        timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}