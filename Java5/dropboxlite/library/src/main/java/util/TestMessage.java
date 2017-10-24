package util;

public class TestMessage extends AbstractMessage{

    public TestMessage() {
        setType(MessageType.TEST_MESSAGE);
    }

    private String testString = "This is a test string!";

    public String getTestString() {
        return testString;
    }
}
