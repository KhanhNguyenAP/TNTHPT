package xuly;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.sql.Connection;

public class DataDB
{
	String noidungbd;
	
	public String getNameDB(Context context){
		ConectionDB eventoConexion = new ConectionDB(context);//khai bao ket noi
		
		try {
			eventoConexion.createDataBase();
		} catch (IOException e) {
		}

		if(eventoConexion.checkDataBase())
		{
			eventoConexion.openDataBase();//mo ket noi den database
			SQLiteDatabase db = eventoConexion.getWritableDatabase();//
			Cursor cursor = db.rawQuery("SELECT NOIDUNGBH FROM BAIHOC", null);//cau lenh load du lieu
	        while(cursor.moveToNext())
			{
				noidungbd = cursor.getString(0);
	        }
	        
	        eventoConexion.close();
	        return noidungbd;//tra ket qua
		}
		else
		{
			return "ERROR";
		}
	}
}
