package pro112.model;

import android.graphics.drawable.Drawable;

import java.sql.Blob;

/**
 * Created by Tuong Vinh on 11/7/2015.
 */
public class Personal {
    public int id_MyVocab ;
    public String New_MyVocab;
    public String Mean_MyVocab;
    public String Voca_Image;
    public String Voca_Audio;

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

    public Personal() {
    }

    public Personal(int id_MyVocab, String new_MyVocab, String mean_MyVocab) {
        this.id_MyVocab = id_MyVocab;
        New_MyVocab = new_MyVocab;
        Mean_MyVocab = mean_MyVocab;
    }

    public int getId_MyVocab() {
        return id_MyVocab;
    }

    public void setId_MyVocab(int id_MyVocab) {
        this.id_MyVocab = id_MyVocab;
    }

    public String getNew_MyVocab() {
        return New_MyVocab;
    }

    public void setNew_MyVocab(String new_MyVocab) {
        New_MyVocab = new_MyVocab;
    }

    public String getMean_MyVocab() {
        return Mean_MyVocab;
    }

    public void setMean_MyVocab(String mean_MyVocab) {
        Mean_MyVocab = mean_MyVocab;
    }
}
