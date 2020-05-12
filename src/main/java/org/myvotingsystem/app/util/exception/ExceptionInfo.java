package org.myvotingsystem.app.util.exception;

public class ExceptionInfo {

    private final String url;
    private final String cause;
    private final String[] details;

    public ExceptionInfo(CharSequence url, String cause, String... details) {
        this.url = url.toString();
        this.cause = cause;
        this.details = details;
    }

    public ExceptionInfo(CharSequence url, Throwable ex) {
        this(url, ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }
}
