package com.thud.thpt_dh.datas;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KhanhNguyen on 10/22/2015.
 * Hỗ trợ đóng mở CSDL
 */
public class DBAdapter
{
    public static DatabaseHelper DbHelper;
    public static SQLiteDatabase db;
    private final Context context;

    public DBAdapter(Context context)
    {
        this.context = context;
        DbHelper = new DatabaseHelper(context);
    }

    public DBAdapter open() throws SQLException
    {
        try
        {
            db = DbHelper.getReadableDatabase();
        }
        catch (SQLException sqle)
        {
            throw sqle;
        }
        return this;
    }

    public void close()
    {
        DbHelper.close();
    }
}
