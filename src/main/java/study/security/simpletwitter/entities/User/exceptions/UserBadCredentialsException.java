package study.security.simpletwitter.entities.User.exceptions;

public class UserBadCredentialsException extends RuntimeException{
    public UserBadCredentialsException () {
        super("Usuário ou Senha incorretos!");
    }

    public UserBadCredentialsException (String message) {
        super(message);
    }
}
