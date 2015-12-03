package pro112.model;

/**
 * Created by Tuong Vinh on 11/7/2015.
 */
public class Vocabulary {
    private int Voca_ID;
    private String Voca_Eng;
    private String Voca_Vie;
    private String Voca_Image;
    private String Voca_Audio;
    private int Topic_ID;

    public Vocabulary() {
    }

    public Vocabulary(int voca_ID, String voca_Eng, String voca_Vie, String voca_Image, String voca_Audio, int topic_ID) {
        Voca_ID = voca_ID;
        Voca_Eng = voca_Eng;
        Voca_Vie = voca_Vie;
        Voca_Image = voca_Image;
        Voca_Audio = voca_Audio;
        Topic_ID = topic_ID;
    }

    public int getVoca_ID() {
        return Voca_ID;
    }

    public void setVoca_ID(int voca_ID) {
        Voca_ID = voca_ID;
    }

    public String getVoca_Eng() {
        return Voca_Eng;
    }

    public void setVoca_Eng(String voca_Eng) {
        Voca_Eng = voca_Eng;
    }

    public String getVoca_Vie() {
        return Voca_Vie;
    }

    public void setVoca_Vie(String voca_Vie) {
        Voca_Vie = voca_Vie;
    }

    public String getVoca_Image() {
        return Voca_Image;
    }

    public void setVoca_Image(String voca_Image) {
        Voca_Image = voca_Image;
    }

    public String getVoca_Audio() {
        return Voca_Audio;
    }

    public void setVoca_Audio(String voca_Audio) {
        Voca_Audio = voca_Audio;
    }

    public int getTopic_ID() {
        return Topic_ID;
    }

    public void setTopic_ID(int topic_ID) {
        Topic_ID = topic_ID;
    }
}
