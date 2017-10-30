package util;

public class AuthAcceptMessage extends AbstractMessage{

    private String login;

    public AuthAcceptMessage(String login) {
        this.login = login;
        setType(MessageType.AUTH_ACCEPT);
    }

    public String getLogin() {
        return login;
    }
}
