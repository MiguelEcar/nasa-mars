package com.ecarsm.preoday.mars.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Ecar. S. M.
 */
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MyException.class)
    protected ResponseEntity<MyExceptionReturn> handleResourceGeneric(MyException ex, WebRequest request) {
        String userMsg = this.messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        String devMsg = ExceptionUtils.getRootCauseMessage(ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MyExceptionReturn(ex, userMsg, devMsg));
    }
}
