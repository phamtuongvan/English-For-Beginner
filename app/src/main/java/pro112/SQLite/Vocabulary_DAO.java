package pro112.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pro112.model.Vocabulary;

public class Vocabulary_DAO {
    private SQLiteDatabase db;
    public Vocabulary_DAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void insert(Vocabulary voca) {
        ContentValues values = new ContentValues();
        //values.put("Voca_ID",voca.getVoca_ID());
        values.put("Voca_Eng", voca.getVoca_Eng());
        values.put("Voca_Vie", voca.getVoca_Vie());
        values.put("Voca_Image", voca.getVoca_Image());
        values.put("Voca_Audio", voca.getVoca_Audio());
        values.put("Topic_ID", voca.getTopic_ID());
        db.insert("VOCABULARY", null, values);
        db.close();
    }

    public void update(Vocabulary voca) {
        ContentValues values = new ContentValues();
        //values.put("Voca_ID",voca.getVoca_ID());
        values.put("Voca_Eng", voca.getVoca_Eng());
        values.put("Voca_Vie", voca.getVoca_Vie());
        values.put("Voca_Image", voca.getVoca_Image());
        values.put("Voca_Audio", voca.getVoca_Audio());
        values.put("Topic_ID", voca.getTopic_ID());
        db.update("VOCABULARY", values, "Voca_ID=?", new String[]{String.valueOf(voca.getVoca_ID())});
        db.close();
    }

    public void delete(String Voca_ID) {
        db.delete("Vocabulary", "Voca_ID=?", new String[]{Voca_ID});
        db.close();
    }

    public List<Vocabulary> getVocabularyByTopic(int topic_id) {
        List<Vocabulary> VocaList = new ArrayList<Vocabulary>();
        String selectQuery = "SELECT * FROM VOCABULARY WHERE Topic_ID =" + topic_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int Voca_ID = cursor.getInt(0);
                String Voca_Eng = cursor.getString(1);
                String Voca_Vie = cursor.getString(2);
                String Voca_Image = cursor.getString(3);
                String Voca_Audio = cursor.getString(4);
                int Topic_ID = cursor.getInt(5);
                Vocabulary voca = new Vocabulary(Voca_ID, Voca_Eng, Voca_Vie, Voca_Image, Voca_Audio, Topic_ID);
                VocaList.add(voca);
            } while (cursor.moveToNext());
        }
        return VocaList;
    }

    public List<Vocabulary> getVocabularyAll() {
        List<Vocabulary> VocaList = new ArrayList<Vocabulary>();
        String selectQuery = "SELECT * FROM VOCABULARY";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int Voca_ID = cursor.getInt(0);
                String Voca_Eng = cursor.getString(1);
                String Voca_Vie = cursor.getString(2);
                String Voca_Image = cursor.getString(3);
                String Voca_Audio = cursor.getString(4);
                int Topic_ID = cursor.getInt(5);
                Vocabulary voca = new Vocabulary(Voca_ID, Voca_Eng, Voca_Vie, Voca_Image, Voca_Audio, Topic_ID);
                VocaList.add(voca);
            } while (cursor.moveToNext());
        }
        return VocaList;
    }

    public List<Vocabulary> getVocabulary(int topic_id) {
        List<Vocabulary> VocaList = new ArrayList<Vocabulary>();
        String selectQuery = "SELECT * FROM VOCABULARY WHERE Topic_ID <>" + topic_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int Voca_ID = cursor.getInt(0);
                String Voca_Eng = cursor.getString(1);
                String Voca_Vie = cursor.getString(2);
                String Voca_Image = cursor.getString(3);
                String Voca_Audio = cursor.getString(4);
                int Topic_ID = cursor.getInt(5);
                Vocabulary voca = new Vocabulary(Voca_ID, Voca_Eng, Voca_Vie, Voca_Image, Voca_Audio, Topic_ID);
                VocaList.add(voca);
            } while (cursor.moveToNext());
        }
        return VocaList;
    }

    public List<Vocabulary> getVocabularyByEng(String Eng) {
        List<Vocabulary> VocaList = new ArrayList<Vocabulary>();
        String selectQuery = "SELECT * FROM VOCABULARY WHERE Voca_Eng =" + Eng;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int Voca_ID = cursor.getInt(0);
                String Voca_Eng = cursor.getString(1);
                String Voca_Vie = cursor.getString(2);
                String Voca_Image = cursor.getString(3);
                String Voca_Audio = cursor.getString(4);
                int Topic_ID = cursor.getInt(5);
                Vocabulary voca = new Vocabulary(Voca_ID, Voca_Eng, Voca_Vie, Voca_Image, Voca_Audio, Topic_ID);
                VocaList.add(voca);
            } while (cursor.moveToNext());
        }
        return VocaList;
    }
}
