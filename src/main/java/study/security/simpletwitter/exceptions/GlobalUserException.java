package study.security.simpletwitter.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import study.security.simpletwitter.entities.User.exceptions.UserBadCredentialsException;
import study.security.simpletwitter.entities.User.exceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalUserException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GlobalErrorResponse> handlerUserNotFoundException(
            UserNotFoundException ex,
            HttpServletRequest req
    ) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        GlobalErrorResponse errorResponse =  new GlobalErrorResponse(
                httpStatus.value(),
                req.getRemoteAddr(),
                ex.getMessage()
        );

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(UserBadCredentialsException.class)
    public ResponseEntity<GlobalErrorResponse> handlerUserBadCredentialsException(
            UserBadCredentialsException ex,
            HttpServletRequest req
    ) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        GlobalErrorResponse errorResponse = new GlobalErrorResponse(
                httpStatus.value(),
                req.getRemoteAddr(),
                ex.getMessage()
        );

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
