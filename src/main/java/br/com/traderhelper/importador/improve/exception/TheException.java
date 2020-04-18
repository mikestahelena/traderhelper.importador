package br.com.traderhelper.importador.improve.exception;

import lombok.Getter;

public class TheException extends RuntimeException {

    @Getter
    private String CODBDI;

    @Getter
    private String CODNEG;

    public TheException(String codbdi, String codneg, String message) {
        super(message);
        CODBDI = codbdi;
        CODNEG = codneg;
    }

    public TheException(String message, Throwable cause) {
        super(message, cause);
        CODBDI = null;
    }

    public TheException(String message) {
        super(message);
        CODBDI = null;
    }
}
