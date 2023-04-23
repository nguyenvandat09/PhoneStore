package com.example.phonestore.ultil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db_login extends SQLiteOpenHelper {
    public static final String TABLE_NAME_THUTHU = "THUTHU";
    public static final String KEY_THUTHU_MATT = "MATHUTHU";
    public static final String KEY_THUTHU_TAIKHOAN = "TAIKHOANTHUTHU";
    public static final String KEY_THUTHU_MATKHAU = "MATKHAUTHUTHU";
    public static final String KEY_THUTHU_DIACHI = "DIACHI";
    public db_login(Context context) {
        super(context, "quanlithuvienn2.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1 = "CREATE TABLE " + TABLE_NAME_THUTHU
                + "("
                + KEY_THUTHU_MATT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_THUTHU_DIACHI + " TEXT NOT NULL ,"
                + KEY_THUTHU_TAIKHOAN + " TEXT NOT NULL ,"
                + KEY_THUTHU_MATKHAU + " TEXT NOT NULL)";

        sqLiteDatabase.execSQL(sql1);


        sql1 = "INSERT INTO " + TABLE_NAME_THUTHU + " VALUES ( null ,'Tầng 29, Tòa nhà trung tâm Lotte Hà Nội, Số 12 Láng Hạ, Thành Công, Quận Ba Đình, Hà Nội','admin' ,'12345678' )";
        sqLiteDatabase.execSQL(sql1);


    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_THUTHU);
    }
}
