package pro112.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pro112.model.Topic;

public class Topic_DAO {
	private SQLiteDatabase db;

	public Topic_DAO(Context context) {
		DbHelper dbHelper = new DbHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	public void insert(Topic topic) {
		ContentValues values = new ContentValues();
		//values.put("Topic_ID",topic.getTopic_ID());
		values.put("Topic_Name",topic.getTopic_Name());
		db.insert("Topic", null, values);
		db.close();
	}

	public void update(Topic topic) {
		ContentValues values = new ContentValues();
		values.put("Topic_Name", topic.getTopic_Name());
		db.update("Topic", values, "Topic_ID"+" = ?", new String[]{String.valueOf(topic.getTopic_ID())});
		db.close();
	}


	public void delete(int id){
		db.delete("Topic", "Topic_ID" + " = ?", new String[] {String.valueOf(id)});
		db.close();
	}
	public List<Topic> getTopic() {
		List<Topic> TopicList = new ArrayList<Topic>();
		String selectQuery = "SELECT * FROM TOPIC";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				int Topic_ID = cursor.getInt(0);
				String Topic_Name = cursor.getString(1);
				Topic tp = new Topic(Topic_ID,Topic_Name);
				TopicList.add(tp);
			} while (cursor.moveToNext());
		}
		return TopicList;
	}
	public List<String> getTopicName() {
		List<String> TopicList = new ArrayList<>();
		String selectQuery = "SELECT * FROM TOPIC";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				int Topic_ID = cursor.getInt(0);
				String Topic_Name = cursor.getString(1);
				TopicList.add(Topic_Name);
			} while (cursor.moveToNext());
		}
		return TopicList;
	}
}
