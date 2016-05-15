package com.thud.thpt_dh.activities;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;

import com.thud.thpt_dh.fragment.NavigationDrawerFragment;
import com.thud.thpt_dh.utils.Preference;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;

import java.util.ArrayList;

/**
 * Created by khanh on 5/14/2016.
 */
public class TestClass extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, ActivityInterface {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private long mLastClickTime = 0;
    private LinearLayout linear_back, linear_menu;
    private boolean is_open_drawer = false;
    private DrawerLayout drawer_layout;
    private Preference preference;

    private NavigationDrawerFragment navigationDrawerFragment;

    @Override
    public void initFlags() {

    }

    @Override
    public void initControl() {

    }

    @Override
    public void setEventForControl() {

    }

    @Override
    public void getData(String... params) {

    }

    @Override
    public void setData() {

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }
}
