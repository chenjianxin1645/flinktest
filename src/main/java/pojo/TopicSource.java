package pojo;

public class TopicSource {
    private long timestamp;
    private int num;

    public void TopicSource() {}

    public long getTimestamp() {
        return timestamp;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return timestamp+":"+num;
    }
}
