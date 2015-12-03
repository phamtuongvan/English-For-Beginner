package pro112.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "EnglishForBeginnerDB";
    public static final int DB_VERSION = 1;
    SQLiteDatabase db = this.getWritableDatabase();

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // TODO Auto-generated constructor stub
    }

    public DbHelper(Context context, String name, CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table
        String CREATE_TOPIC_TABLE = "CREATE TABLE TOPIC (Topic_ID INTEGER PRIMARY KEY AUTOINCREMENT,Topic_Name VARCHAR NOT NULL);";
        db.execSQL(CREATE_TOPIC_TABLE);
        String CREATE_VOCABULARY_TABLE = "CREATE TABLE VOCABULARY (Voca_ID INTEGER PRIMARY KEY AUTOINCREMENT,Voca_Eng VARCHAR NOT NULL,Voca_Vie VARCHAR NOT NULL,Voca_Image VARCHAR NOT NULL,Voca_Audio VARCHAR NOT NULL,Topic_ID INTEGER NOT NULL CONSTRAINT FK1 REFERENCES [TOPIC]([Topic_ID]));";
        db.execSQL(CREATE_VOCABULARY_TABLE);
        String CREATE_TRANSCRIPT_TABLE = "CREATE TABLE TRANSCRIPT (Trans_ID INTEGER PRIMARY KEY AUTOINCREMENT,Trans_mark VARCHAR NOT NULL,Topic_ID INTEGER NOT NULL CONSTRAINT FK1 REFERENCES [TOPIC]([Topic_ID]));";
        db.execSQL(CREATE_TRANSCRIPT_TABLE);
        String CREATE_MYVOCABULARY_TABLE = "CREATE TABLE MYVOCABULARY (Voca_ID INTEGER PRIMARY KEY AUTOINCREMENT, Voca_Eng VARCHAR, Voca_Vie VARCHAR , Voca_Image VARCHAR ,Voca_Audio VARCHAR);";
        db.execSQL(CREATE_MYVOCABULARY_TABLE);
        String INSERT_TOPIC_TABLE = "INSERT INTO TOPIC (Topic_ID,Topic_Name) VALUES (null,'Jobs'),(null, 'Love'),(null, 'Place'),(null, 'Holidays'),(null, 'Hobbies'),(null, 'Entertainment'),(null, 'Nature');";
        db.execSQL(INSERT_TOPIC_TABLE);
        String INSERT_VOCABULARY_TABLE = "INSERT INTO VOCABULARY (Voca_ID,Voca_Eng,Voca_Vie,Voca_Image,Voca_Audio,Topic_ID) VALUES " +
                //TOPIC JOBS
                "(null,'accountant','kế toán','accountant','accountant',1)," +
                "(null,'actor','diễn viên nam','actor','actor',1)," +
                "(null,'actress','diễn viên nữ','actress','actress',1)," +
                "(null,'architect','kiến trúc sư','architect','architect',1)," +
                "(null,'artist','họa sĩ','artist','artist',1)," +
                "(null,'babysitter','người giữ trẻ','babysitter','babysitter',1)," +
                "(null,'barber','thợ cắt tóc','barber','barber',1)," +
                "(null,'baker','thợ làm bánh','baker','baker',1)," +
                "(null,'carpenter','thợ mộc','carpenter','carpenter',1)," +
                "(null,'cashier','thu ngân','cashier','cashier',1)," +
                "(null,'chef','đầu bếp','chef','chef',1)," +
                "(null,'doctor','bác sĩ','doctor','doctor',1)," +
                "(null,'engineer','kỹ sư','engineer','engineer',1)," +
                "(null,'farmer','nông dân','farmer','farmer',1)," +
                "(null,'home maker','người nội trợ','homemaker','homemaker',1)," +
                "(null,'lawyer','luật sư','lawyer','lawyer',1)," +
                "(null,'nurse','y tá','nurse','nurse',1)," +
                "(null,'photographer','nhiếp ảnh gia','photographer','photographer',1)," +
                "(null,'secretary','thư ký','secretary','secretary',1)," +
                "(null,'teacher','giáo viên','teacher','teacher',1)," +
                //TOPIC LOVE
                "(null,'date','hẹn hò','date','date',2)," +
                "(null,'engagement','đính hôn','engagement','engagement',2)," +
                "(null,'ring','nhẫn','ring','ring',2)," +
                "(null,'romantic','lãng mạng','romantic','romantic',2)," +
                "(null,'sweet','ngọt ngào','sweet','sweet',2)," +
                "(null,'miss','nhớ nhung','miss','miss',2)," +
                "(null,'alone','một mình','alone','alone',2)," +
                "(null,'couple','cặp đôi','couple','couple',2)," +
                "(null,'forever','mãi mãi','forever','forever',2)," +
                "(null,'boyfriend','bạn trai','boyfriend','boyfriend',2)," +
                "(null,'girlfriend','bạn gái','girlfriend','girlfriend',2)," +
                "(null,'kiss','hôn','kiss','kiss',2)," +
                "(null,'heart','trái tim','heart','heart',2)," +
                "(null,'hug','ôm','hug','hug',2)," +
                "(null,'propose','cầu hôn','propose','propose',2)," +
                "(null,'chocolate','sô cô la','chocolate','chocolate',2)," +
                "(null,'wedding','đám cưới','wedding','wedding',2)," +
                "(null,'anniversary','ngày kỷ niệm','anniversary','anniversary',2)," +
                "(null,'darling','thân yêu','darling','darling',2)," +
                "(null,'single','độc thân','single','single',2);";
                /*//TOPIC PLACE
                "(null,'bakery','tiệm bánh','bakery','bakery',3)," +
                "(null,'bank','ngân hàng','bank','bank',3)," +
                "(null,'hair salon','tiệm cắt tóc','hair salon','hair salon',3)," +
                "(null,'supermarket','siêu thị','supermarket','supermarket',3)," +
                "(null,'post office','bưu điện','post office','post office',3)," +
                "(null,'train station','ga xe lửa','train station','train station',3)," +
                "(null,'park','công viên','park','park',3)," +
                "(null,'hotel','khách sạn','hotel','hotel',3)," +
                "(null,'coffee shop','quán cà phê','coffee shop','coffee shop',3)," +
                "(null,'flower shop','cửa hàng hoa','flower shop','flower shop',3)," +
                "(null,'library','thư viện','library','library',3)," +
                "(null,'movie theater','rạp chiếu phim','movie theater','movie theater',3)," +
                "(null,'pharmacy','hiệu thuốc','pharmacy','pharmacy',3)," +
                "(null,'bookstore','nhà sách','bookstore','bookstore',3)," +
                "(null,'bus station','trạm xe buýt','bus station','bus station',3)," +
                "(null,'dry cleaner','tiệm giặt ủi','dry cleaner','dry cleaner',3)," +
                "(null,'fast-food restaurant','tiệm thức ăn nhanh','fast-food restaurant','fast-food restaurant',3)," +
                "(null,'furniture store','cửa hàng nội thất','furniture store','furniture store',3)," +
                "(null,'gas station','trạm xăng','gas station','gas station',3)," +
                "(null,'grocery store','cửa hàng tạp hóa','grocery store','grocery store',3)," +
                //TOPIC HOLIDAYS
                "(null,'celebrate','kỷ niệm','celebrate','celebrate',4)," +
                "(null,'champagne','rượu sâm panh','champagne','champagne',4)," +
                "(null,'Thanksgiving Day','Ngày lễ Tạ ơn','thanksgivingday','thanksgivingday',4)," +
                "(null,'Chrismas','Giáng sinh','christmas','christmas',4)," +
                "(null,'Easter','Lễ phục sinh','easter','easter',4)," +
                "(null,'Festival','Lễ hội','festival','festival',4)," +
                "(null,'firework','pháo hoa','firework','firework',4)," +
                "(null,'Halloween','Lễ hội Hóa Lộ Quỉ','halloween','halloween',4)," +
                "(null,'Independence Day','Ngày Độc Lập','independenceday','independenceday',4)," +
                "(null,'lantern','lồng đèn','lantern','lantern',4)," +
                "(null,'Mother's Day','Ngày của mẹ','mother'sday','mother'sday',4)," +
                "(null,'New Year's Eve','Đêm giao thừa','newyear'seve','newyear'seve',4)," +
                "(null,'parade','cuộc diễu hành','parade','parade',4)," +
                "(null,'vacation','kỳ nghỉ','vacation','vacation',4)," +
                "(null,'Valentine Day','Ngày lễ Tình nhân','valentine'sday','valentine'sday',4)," +
                "(null,'party','tiệc, bữa tiệc','party','party',4)," +
                "(null,'decorate','trang trí','decorate','decorate',4)," +
                "(null,'travel','đi du lịch','travel','travel',4)," +
                "(null,'reunion','cuộc hội ngộ','reunion','reunion',4)," +
                "(null,'turkey','Gà tây','turkey','turkey',4);";*/
        db.execSQL(INSERT_VOCABULARY_TABLE);
        String INSERT_MYVOCA_TABLE = "INSERT INTO MYVOCABULARY (Voca_ID,Voca_Eng,Voca_Vie,Voca_Image,Voca_Audio) VALUES (null,'accountant','kếtoán','accountant','accountant'),( null,'actor','diễn viên nam','actor','actor'),( null,'actress','diễn viên nữ','actress','actress'),( null,'architect','kiến trúc sư','architect','architect'),( null,'artist','họa sĩ','artist','artist');";
        db.execSQL(INSERT_MYVOCA_TABLE);
        String INSERT_TRANS_TABLE = "INSERT INTO TRANSCRIPT (Trans_ID,Trans_mark,Topic_ID) VALUES (null,'5',1),(null,'5',1),(null,'5',1),(null,'5',1),(null,'5',1);";
        db.execSQL(INSERT_TRANS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        String dropTableTOPIC = "DROP TABLE IF EXISTS TOPIC";
        String dropTableVOCABULARY = "DROP TABLE IF EXISTS VOCABULARY";
        String dropTableTRANSCRIPT = "DROP TABLE IF EXISTS VOCABULARY";
        String dropTableMYVOCABULARY = "DROP TABLE IF EXISTS MYVOCABULARY";
        db.execSQL(dropTableTOPIC);
        db.execSQL(dropTableVOCABULARY);
        db.execSQL(dropTableMYVOCABULARY);
        onCreate(db);
    }

}
