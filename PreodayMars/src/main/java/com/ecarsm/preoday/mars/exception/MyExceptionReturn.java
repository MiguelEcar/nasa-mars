package com.ecarsm.preoday.mars.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Ecar. S. M.
 */
@Getter
@NoArgsConstructor
public class MyExceptionReturn {

    private String userMsg;
    private String devMsg;

    private MyException exception;

    private Class<MyException> exceptionClass;

    public MyExceptionReturn(MyException exception, String userMsg, String devMsg) {
        this.exception = exception;
        this.userMsg = userMsg;
        this.devMsg = devMsg;
        this.exceptionClass = (Class<MyException>) this.exception.getClass();
    }
}
