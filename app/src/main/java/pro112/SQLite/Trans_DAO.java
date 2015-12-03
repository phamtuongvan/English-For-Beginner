package pro112.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pro112.model.Transcript;

public class Trans_DAO {
    private SQLiteDatabase db;

    public Trans_DAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void insert(Transcript trans) {
        ContentValues values = new ContentValues();
        values.put("Trans_mark", trans.getTrans_mark());
        values.put("Topic_ID", trans.getTopic_ID());
        db.insert("Transcript", null, values);
        db.close();
    }

    public void update(Transcript trans) {
        ContentValues values = new ContentValues();
        values.put("Trans_mark", trans.getTrans_mark());
        values.put("Topic_ID", trans.getTopic_ID());
        db.update("Transcript", values, "Trans_ID" + " = ?", new String[]{String.valueOf(trans.getTrans_ID())});
        db.close();
    }


    public void delete(int id) {
        db.delete("Transcript", "Trans_ID" + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Transcript> getTranscript() {
        List<Transcript> TopicList = new ArrayList<>();
        String selectQuery = "SELECT * FROM TRANSCRIPT";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int Trans_ID = cursor.getInt(0);
                String Trans_mark = cursor.getString(1);
                int Topic_ID  = cursor.getInt(2);
                Transcript trans = new Transcript(Trans_ID,Trans_mark,Topic_ID);
                TopicList.add(trans);
            } while (cursor.moveToNext());
        }
        return TopicList;
    }
    public List<Transcript> getTranscriptByTopic(int topic_id) {
        List<Transcript> TopicList = new ArrayList<>();
        String selectQuery = "SELECT * FROM TRANSCRIPT WHERE Topic_ID =" + topic_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int Trans_ID = cursor.getInt(0);
                String Trans_mark = cursor.getString(1);
                int Topic_ID  = cursor.getInt(2);
                Transcript trans = new Transcript(Trans_ID,Trans_mark,Topic_ID);
                TopicList.add(trans);
            } while (cursor.moveToNext());
        }
        return TopicList;
    }
}
