package org.myvotingsystem.app.util.exception;

import org.myvotingsystem.app.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {
    private static Logger LOG = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ExceptionInfo bindValidationError(HttpServletRequest req, BindingResult result) {
        return logAndGetValidationErrorInfo(req, result);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ExceptionInfo restValidationError(HttpServletRequest req, MethodArgumentNotValidException e) {
        return logAndGetValidationErrorInfo(req, e.getBindingResult());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ExceptionInfo conflict(HttpServletRequest request, DataIntegrityViolationException e) {
        return logAndGetErrorInfo(request, e);
    }

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public ResponseEntity<ExceptionInfo> applicationError(HttpServletRequest req, AppException e) {
        return getErrorInfoResponseEntity(req, e, e.getMsgCode(), e.getHttpStatus(), e.getArgs());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ExceptionInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e);
    }

    private ExceptionInfo logAndGetValidationErrorInfo(HttpServletRequest req, BindingResult result) {
        String[] details = result.getAllErrors().stream()
                .map(fe -> messageSource.getMessage(fe, LocaleContextHolder.getLocale()))
                .toArray(String[]::new);
        LOG.warn("{} exception at request {}: {}", "ValidationException", req.getRequestURL(), Arrays.toString(details));
        return new ExceptionInfo(req.getRequestURL(), "ValidationException", details);
    }

    private ResponseEntity<ExceptionInfo> getErrorInfoResponseEntity(HttpServletRequest req, Exception e, String msgCode, HttpStatus httpStatus, String... args) {
        LOG.warn("Application error: {}", ValidationUtil.getRootCause(e).toString());
        ExceptionInfo exceptionInfo = new ExceptionInfo(req.getRequestURL(),
                ValidationUtil.getRootCause(e).getClass().getSimpleName(),
                messageSource.getMessage(msgCode, args, LocaleContextHolder.getLocale()));
        return new ResponseEntity<>(exceptionInfo, httpStatus);
    }

    private ExceptionInfo logAndGetErrorInfo(HttpServletRequest req, Exception e) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        LOG.warn("Exception at request {}" + req.getRequestURL(), rootCause);
        return new ExceptionInfo(req.getRequestURL(), rootCause);
    }
}
