package com.thud.thpt_dh.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.thud.thpt_dh.R;
import com.thud.thpt_dh.model.HinhAnh;
import com.thud.thpt_dh.model.HinhAnhChiTiet;
import com.thud.thpt_dh.model.Result;
import com.thud.thpt_dh.model.ResultStatus;
import com.thud.thpt_dh.utils.interfaces.Def;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by khanh on 5/12/2016.
 */
public class HinhAnhDAL {
    private Context context;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public HinhAnhDAL(Context current){
        this.context = current;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //get all Hinh Anh from server
    public Result<ArrayList<HinhAnh>> getAllHinhAnh() {
        final ArrayList<HinhAnh> arr_HinhAnh = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery(""+HinhAnh.TENBANG);
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e ==  null){
                    for (ParseObject ob : objects){
                        HinhAnh hinhAnh = new HinhAnh(ob.getObjectId(),
                                ob.getParseFile(""+HinhAnh.HINHANH),
                                ob.getString(""+HinhAnh.MABAIGIAI),
                                ob.getString(""+HinhAnh.MACHITIETBAIHOC));
                        arr_HinhAnh.add(hinhAnh);
                    }

                    if (arr_HinhAnh.size() >0){
                        Result<String> result = new AllDAL(context).saveAll(arr_HinhAnh);
                    }

                    /*for(int i=0; i<arr_HinhAnh.size(); i++){
                        String name = arr_HinhAnh.get(i).getHinhanh().getName();
                        downloadDocumentByURL(arr_HinhAnh.get(i).getHinhanh(), name);
                    }*/
                }
            }
        });

        return new Result<ArrayList<HinhAnh>>(ResultStatus.TRUE, arr_HinhAnh);
    }

    /*
    * insert all data from server
    * */
    public Result<String> insertHinhAnhFromLocal(ArrayList<HinhAnh> hinhAnhs){
        try {
            database = dbHelper.getWritableDatabase();
            database.beginTransaction();

            for(HinhAnh hinhAnh : hinhAnhs){
                ContentValues HinhAnhDb = DbModel.getContentValueHinhAnh(hinhAnh);

                //insert database
                database.insert(HinhAnh.TENBANG, null, HinhAnhDb);

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


    /*private String downloadDocumentByURL(String pUrl, String pName){
        try{
            path_document = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
            File folder = new File(path_document);
            if (!folder.exists()) {
                folder.mkdir();
            }
            folder = new File(path_document, context.getResources().getString(R.string.onthi_document));
            if (!folder.exists()) {
                folder.mkdir();
            }

            String path_view_document = folder.getAbsolutePath() + "/"+ pName;
            File f = new File(path_view_document);
            if(f.exists() && !f.isDirectory()) {
                return "file existed";
            }

            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(pUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {

                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(f);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        output.write(data, 0, count);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                    ignored.printStackTrace();
                    return ignored.getMessage();
                }

                if (connection != null)
                    connection.disconnect();
            }
            //new ToastMessage(context).showToast(path_document);
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }

    }*/
}
