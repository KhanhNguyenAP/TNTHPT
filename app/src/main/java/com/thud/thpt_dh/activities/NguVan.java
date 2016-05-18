package com.thud.thpt_dh.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.thud.thpt_dh.R;

import com.thud.thpt_dh.customcontrols.CustomGridView;
import com.thud.thpt_dh.fragment.FragToanHoc;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Def;
import com.thud.thpt_dh.utils.interfaces.Flags;

public class NguVan extends BaseActivity implements ActivityInterface {
    DrawerLayout drawerLayout_nguvan;
    Toolbar toolbar_nguvan;
    ActionBar actionBar_nguvan;
    android.app.FragmentManager fragmentManager = this.getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngu_van);

        toolbar_nguvan = (Toolbar) findViewById(R.id.toolbar_nguvan);
        setSupportActionBar(toolbar_nguvan);

        actionBar_nguvan = getSupportActionBar();
        actionBar_nguvan.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_nguvan.setDisplayHomeAsUpEnabled(true);
        drawerLayout_nguvan = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_nguvan);

        NavigationView navigationView_nguvan= (NavigationView) findViewById(R.id.navigation_view_nguvan);
        if (navigationView_nguvan != null) {
            setupNavigationDrawerContent(navigationView_nguvan);
        }

        setupNavigationDrawerContent(navigationView_nguvan);
        Flags.main_nguvan = false;

        FragToanHoc fragToanHoc = (FragToanHoc) getFragmentManager().findFragmentById(R.id.fra_nguvan);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragToanHoc = new FragToanHoc();
        fragmentTransaction.replace(R.id.fra_nguvan, fragToanHoc, "Ngu Van");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout_nguvan.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView)  {
    }

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
    public void  onBackPressed(){
        if (Flags.main_nguvan == true){
            finish();
        }

        showRangeList(fragmentManager);
    }

    public static void showRangeList(android.app.FragmentManager fragmentManager){
        try{
            android.app.Fragment fragmentRangeList =
                    fragmentManager.findFragmentByTag(Def.TAG_SETTING_MONNGUVAN);

            if(fragmentRangeList != null){
                fragmentManager.beginTransaction().remove(fragmentRangeList).commitAllowingStateLoss();
            }

            fragmentRangeList = new FragToanHoc();
            fragmentManager.beginTransaction()
                    .add(R.id.fra_toanhoc, fragmentRangeList, Def.TAG_SETTING_MONNGUVAN)
                    .commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }//--end showRangeList
}