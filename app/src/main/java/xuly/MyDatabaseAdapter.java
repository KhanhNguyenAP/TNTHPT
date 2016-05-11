package xuly;

/**
 * Created by Le on 10/10/2015.
 */
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyDatabaseAdapter extends SQLiteOpenHelper {

    private Context context;

    private String DB_PATH = "data/data/com.thud.thpt_dh/";//duong dan
    private static String DB_NAME = "testlatex.db";//database
    private SQLiteDatabase myDatabase;

    public MyDatabaseAdapter(Context context)
    {
        super(context, DB_NAME, null, 1);

        this.context = context;
        boolean dbexist = checkdatabase();

        if(dbexist)
        {
        }
        else
        {
            System.out.println("Database doesn't exist!");//bat loi khi database ko ton tai

            createDatabse();
        }
    }

    public void createDatabse() {

        this.getReadableDatabase();

        try
        {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SQLiteDatabase getMyDatabase()
    {
        return myDatabase;
    }

    private void copyDatabase() throws IOException {

        AssetManager dirPath = context.getAssets();

        InputStream myinput = context.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream("data/data/com.thud.thpt_dh/testlatex.db");

        byte[] buffer = new byte[1024];
        int length;

        while ((length = myinput.read(buffer)) > 0)
        {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myinput.close();
    }

    public void open()
    {
        myDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);//mo database
    }

    public synchronized void close()
    {
        myDatabase.close();//dong database
        super.close();
    }

    private boolean checkdatabase()
    {
        boolean checkdb = false;

        try
        {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        }
        catch (SQLiteException e)
        {
            System.out.println("Databse doesn't exist!");
        }

        return checkdb;
    }

    public MyDatabaseAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}