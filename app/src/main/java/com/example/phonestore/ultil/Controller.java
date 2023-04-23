package com.example.phonestore.ultil;

import static com.example.phonestore.ultil.db_login.KEY_THUTHU_DIACHI;
import static com.example.phonestore.ultil.db_login.KEY_THUTHU_MATKHAU;
import static com.example.phonestore.ultil.db_login.KEY_THUTHU_MATT;
import static com.example.phonestore.ultil.db_login.KEY_THUTHU_TAIKHOAN;
import static com.example.phonestore.ultil.db_login.TABLE_NAME_THUTHU;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.phonestore.model.ThuThu;

public class Controller {

    db_login mMySqlHeper;
    SQLiteDatabase mSQLiteDatabase;

    public Controller(Context context) {
        mMySqlHeper = new db_login(context);
    }

    public ThuThu getUserLogin(String username, String password) {
        this.mSQLiteDatabase = mMySqlHeper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_THUTHU
                + " WHERE " + KEY_THUTHU_TAIKHOAN + " = '" + username + "' and " + KEY_THUTHU_MATKHAU + " = '" + password + "'";
        Cursor cursor = this.mSQLiteDatabase.rawQuery(sql, null);
        ThuThu user = new ThuThu();
        if (cursor.moveToFirst()) {
            user.setMaThuThu(Integer.parseInt(cursor.getString(0)));
            user.setDiachi(cursor.getString(1));
            user.setTaiKhoan(cursor.getString(2));
            user.setMaKhau(cursor.getString(3));
        }
        cursor.close();
        this.mSQLiteDatabase.close();
        return user;
    }
    public boolean themKind(ThuThu thuthu) {
        this.mSQLiteDatabase = mMySqlHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_THUTHU_DIACHI, thuthu.getDiachi());
        values.put(KEY_THUTHU_TAIKHOAN, thuthu.getTaiKhoan());
        values.put(KEY_THUTHU_MATKHAU, thuthu.getMaKhau());

        long result = this.mSQLiteDatabase.insert(TABLE_NAME_THUTHU, null, values);
        if (result <= 0) {
            return false;
        }
        return true;
    }

}
