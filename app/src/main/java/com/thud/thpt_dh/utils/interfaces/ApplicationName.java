package com.thud.thpt_dh.utils.interfaces;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by khanh on 5/11/2016.
 */
public class ApplicationName extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);
        //Parse initialize
        Parse.initialize(this, "I1im41rwCz02rCUrX6daYMTHov4IMwFJAmcfylBW",
                            "DnjjAX7h9dXdH8DBjO08VSwIoZEaOGiX4IyZ78ac");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        //set ACL
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
