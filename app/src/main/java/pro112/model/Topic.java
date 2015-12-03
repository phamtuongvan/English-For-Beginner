package pro112.model;

/**
 * Created by Tuong Vinh on 11/7/2015.
 */
public class Topic {
    private int Topic_ID;
    private String Topic_Name;

    public Topic(int topic_ID, String topic_Name) {
        Topic_ID = topic_ID;
        Topic_Name = topic_Name;
    }
    public Topic() {

    }
    public int getTopic_ID() {
        return Topic_ID;
    }

    public void setTopic_ID(int topic_ID) {
        Topic_ID = topic_ID;
    }

    public String getTopic_Name() {
        return Topic_Name;
    }

    public void setTopic_Name(String topic_Name) {
        Topic_Name = topic_Name;
    }
}
