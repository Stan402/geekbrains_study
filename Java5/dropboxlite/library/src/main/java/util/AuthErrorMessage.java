package util;

public class AuthErrorMessage extends AbstractMessage{

    public AuthErrorMessage() {
        setType(MessageType.AUTH_ERROR);
    }
}
