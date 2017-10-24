package util;

import java.io.Serializable;

public abstract class AbstractMessage implements Serializable{

    private MessageType type;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
