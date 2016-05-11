package xuly;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by KhanhNguyen on 10/29/2015.
 */
public class ConectionDB extends SQLiteOpenHelper
{
    private static String DB_PATH = "/data/data/com.thud.thpt_dh/databases/";//duong dan den database
    private static String DB_NAME = "testonthi.db";//database
    private SQLiteDatabase myDataBase;
    private Context myContext;

    public ConectionDB(Context context)
    {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException
    {
       if (checkDataBase())
       {

       }
        else
       {
            copyDataBase();
       }

    }

    public void copyDataBase() throws IOException
    {
        try
        {
            InputStream is = myContext.getAssets().open(DB_NAME);
            String ou_filename = DB_PATH + DB_NAME;
            OutputStream outputStream = new FileOutputStream(ou_filename);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer))>0)
            {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean checkDataBase()
    {
      File file = new File(DB_PATH + DB_NAME);
        if (file.exists())
            return true;
        else
            return false;
    }

    public void openDataBase() throws SQLException
    {
        myDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDataBase()
    {
        if(myDataBase != null)
            myDataBase.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {
        // TODO Auto-generated method stub
    }
}
