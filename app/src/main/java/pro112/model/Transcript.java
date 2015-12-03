package pro112.model;

/**
 * Created by Tuong Vinh on 11/7/2015.
 */
public class Transcript {
    private int Trans_ID;
    private String Trans_mark;
    private int Topic_ID;

    public Transcript() {
    }

    public Transcript(int trans_ID, String trans_mark, int topic_ID) {
        Trans_ID = trans_ID;
        Trans_mark = trans_mark;
        Topic_ID = topic_ID;
    }

    public int getTrans_ID() {
        return Trans_ID;
    }

    public void setTrans_ID(int trans_ID) {
        Trans_ID = trans_ID;
    }

    public String getTrans_mark() {
        return Trans_mark;
    }

    public void setTrans_mark(String trans_mark) {
        Trans_mark = trans_mark;
    }

    public int getTopic_ID() {
        return Topic_ID;
    }

    public void setTopic_ID(int topic_ID) {
        Topic_ID = topic_ID;
    }
}
