package util;

public class TextMessage extends AbstractMessage{
    
    String msg;

    public TextMessage(String msg) {
        this.msg = msg;
        setType(MessageType.TEXT_MESSAGE);
    }

    public String getMsg() {
        return msg;
    }
}
