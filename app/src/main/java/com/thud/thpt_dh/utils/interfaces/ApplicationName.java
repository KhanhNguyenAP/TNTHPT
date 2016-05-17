package com.thud.thpt_dh.utils.interfaces;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.thud.thpt_dh.datas.DBHelper;

/**
 * Created by khanh on 5/11/2016.
 */
public class ApplicationName extends Application {
    DBHelper dbHelper;

    @Override
    public void onCreate(){
        super.onCreate();

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Parse.enableLocalDatastore(this);
        //Parse initialize
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("I1im41rwCz02rCUrX6daYMTHov4IMwFJAmcfylBW")
                .clientKey("DnjjAX7h9dXdH8DBjO08VSwIoZEaOGiX4IyZ78ac")
                .server("https://api.parse.com/1/").build()
                );
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        //set ACL
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
