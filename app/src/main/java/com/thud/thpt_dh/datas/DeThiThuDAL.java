package com.thud.thpt_dh.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.DeThiThu;
import com.thud.thpt_dh.model.MonHoc;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;
import com.thud.thpt_dh.utils.interfaces.Flags;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khanh on 5/12/2016.
 */
public class DeThiThuDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public DeThiThuDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all de thi thu from server
    public Result<ArrayList<DeThiThu>> getAllDeThiThu() {
        final ArrayList<DeThiThu> arr_DeThiThu = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+DeThiThu.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        DeThiThu deThiThu = new DeThiThu(ob.getObjectId(),
                                ob.getString(""+DeThiThu.MAMONHOC),
                                ob.getString(""+DeThiThu.TENDE),
                                ob.getInt(""+DeThiThu.SOLUONG),
                                ob.getInt(""+DeThiThu.LOAI),
                                ob.getParseFile(""+DeThiThu.PDF),
                                ob.getInt(""+DeThiThu.SHOWPDF));
                        arr_DeThiThu.add(deThiThu);
                    }

                    if (arr_DeThiThu.size() >0){
                        Result<String> result = new AllDAL(context).saveAll(arr_DeThiThu);

                        for (int i=0; i<arr_DeThiThu.size(); i++){
                            if (arr_DeThiThu.get(i).getShowpdf() == Flags.xem_pdf_dethi){
                                new DownloadFile().execute(""+arr_DeThiThu.get(i).getPdf().getUrl(),""+ arr_DeThiThu.get(i).getPdf().getName());
                            }
                        }

                    }
                }
            }
        });

        return new Result<ArrayList<DeThiThu>>(ResultStatus.TRUE, arr_DeThiThu);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertDeThiThuFromLocal(ArrayList<DeThiThu> deThiThus){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(DeThiThu deThiThu : deThiThus){
                ContentValues DeThiThuDb = DbModel.getContentValueDeThiThu(deThiThu);

                //insert database
                database.insert(DeThiThu.TENBANG, null, DeThiThuDb);
            }

            database.setTransactionSuccessful();
            database.endTransaction();
            database.close();

            return new Result<String>(ResultStatus.TRUE, context.getResources().getString(R.string.msg_data_has_been_saved));
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }

        return new Result<String>(ResultStatus.FALSE, null);
    }

    /*---------------------------SQLite---------------------------*/
    public Result<ArrayList<DeThiThu>> getAllDeThiThuFromLoCal(int mamonhoc){
        database = dbHelper.getReadableDatabase();
        MonHoc monHoc = new MonHoc();
        ArrayList<DeThiThu> deThiThus = new ArrayList<>();
        try{
            String query_mon = "SELECT * FROM " + MonHoc.TENBANG + " WHERE " + MonHoc.MAMON + " = " +mamonhoc;
            Cursor cursor_mon = database.rawQuery(query_mon, null);

            if(cursor_mon != null && cursor_mon.moveToFirst()){
                monHoc = DbModel.getMonHoc(cursor_mon);
            }

            String query = "SELECT * FROM " + DeThiThu.TENBANG + " WHERE " + DeThiThu.MAMONHOC  +" = '" + monHoc.getId() +"'";
            Cursor cursor = database.rawQuery(query, null);

            if (cursor != null && cursor.moveToFirst()){
                do{
                    DeThiThu deThiThu = DbModel.getDeThiThu(cursor);
                    deThiThus.add(deThiThu);
                }while (cursor.moveToNext());
            }

            database.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }

        return new Result<ArrayList<DeThiThu>>(ResultStatus.TRUE, deThiThus);
    }

    public Result<DeThiThu> getDeThiThuFromLoCal(String madethi){
        database = dbHelper.getReadableDatabase();
        DeThiThu deThiThu = new DeThiThu();
        try{
            String query = "SELECT * FROM " + DeThiThu.TENBANG + " WHERE " + DeThiThu.ID  +" = '" + madethi +"'";
            Cursor cursor = database.rawQuery(query, null);

            if (cursor != null && cursor.moveToFirst()){
                deThiThu = DbModel.getDeThiThu(cursor);
            }

            database.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(Def.ERROR, e.getMessage());
        }

        return new Result<DeThiThu>(ResultStatus.TRUE, deThiThu);
    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {

            String fileUrl = strings[0];
            String fileName = strings[1];
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            downloadFile(fileUrl, pdfFile);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public static void downloadFile(String fileUrl, File directory){
        try {

            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            int totalSize = urlConnection.getContentLength();

            byte[] buffer = new byte[1024 * 1024];
            int bufferLength = 0;
            while((bufferLength = inputStream.read(buffer)) >0 )
                fileOutputStream.write(buffer, 0, bufferLength);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
