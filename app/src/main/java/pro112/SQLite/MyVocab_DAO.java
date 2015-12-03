package pro112.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pro112.model.Personal;

/**
 * Created by nhan on 29/11/15.
 */
public class MyVocab_DAO {
    DbHelper dbHelper;
    SQLiteDatabase db;
    public MyVocab_DAO(Context context) {
        dbHelper = new DbHelper(context);

    }
    public void insert(Personal personal) {
         db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("Topic_ID",topic.getTopic_ID());
        values.put("Voca_Eng",personal.getNew_MyVocab());
        values.put("Voca_Vie",personal.getMean_MyVocab());
        values.put("Voca_Image", String.valueOf(personal.getVoca_Image()));
        values.put("Voca_Audio",personal.getVoca_Audio());
        db.insert("MYVOCABULARY", null, values);
        db.close();
    }

    public void update(Personal personal) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Voca_Eng", personal.getNew_MyVocab());
        values.put("Voca_Vie",personal.getMean_MyVocab());
        values.put("Voca_Image", String.valueOf(personal.getVoca_Image()));
        db.update("MYVOCABULARY", values, "Voca_ID" + " = ?", new String[]{String.valueOf(personal.getId_MyVocab())});
        db.close();
    }


    public void delete(int id){
        db = dbHelper.getWritableDatabase();
        db.delete("MYVOCABULARY", "Voca_ID" + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public ArrayList<Personal> getMyVocaById(int audioId) {
        String sql = "SELECT * FROM MYVOCABULARY"
                + " WHERE Voca_ID" + " = " + audioId;
        return getMyVocab(sql);
    }
    public ArrayList<Personal> getAllMyVoca() {
        String sql = "SELECT * FROM MYVOCABULARY";
        return getMyVocab(sql);
    }
    public ArrayList<Personal> getMyVocab(String sql, String... selectionArgs) {
        ArrayList<Personal> personnals = new ArrayList<Personal>();
        String selectQuery = "SELECT * FROM MYVOCABULARY";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Personal ps = null;
        if(cursor.moveToFirst()) {
            do {
                ps = new Personal();
                ps.setId_MyVocab(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Voca_ID"))));
                ps.setNew_MyVocab(cursor.getString(cursor.getColumnIndex("Voca_Eng")));
                ps.setMean_MyVocab(cursor.getString(cursor.getColumnIndex("Voca_Vie")));
                ps.setVoca_Image(cursor.getString(cursor.getColumnIndex("Voca_Image")));
                ps.setVoca_Audio(cursor.getString(cursor.getColumnIndex("Voca_Audio")));
                personnals.add(ps);
            } while(cursor.moveToNext());
        }


        return personnals;
    }
}

