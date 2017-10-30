package util;

public class AuthRequestMessage extends AbstractMessage{

    private String login;
    private String password;

    public AuthRequestMessage(String login, String password) {
        this.login = login;
        this.password = password;
        setType(MessageType.AUTH_REQUEST);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
