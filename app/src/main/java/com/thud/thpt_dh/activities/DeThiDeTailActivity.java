package com.thud.thpt_dh.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.fragment.FragDeThi;
import com.thud.thpt_dh.fragment.FragShowInfoDeThi;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;
import com.thud.thpt_dh.utils.interfaces.Def;
import com.thud.thpt_dh.utils.interfaces.Flags;

public class DeThiDeTailActivity extends BaseActivity implements ActivityInterface {
    DrawerLayout drawerLayout_toan;
    Toolbar toolbar_toan;
    ActionBar actionBar_toan;
    android.app.FragmentManager fragmentManager = this.getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toan_hoc);

        toolbar_toan = (Toolbar) findViewById(R.id.toolbar_toan);
        setSupportActionBar(toolbar_toan);
        actionBar_toan = getSupportActionBar();
        actionBar_toan.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_toan.setDisplayHomeAsUpEnabled(true);
        drawerLayout_toan = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_toan);

        NavigationView navigationView_toan= (NavigationView) findViewById(R.id.navigation_view_toan);
        if (navigationView_toan != null)  {
            setupNavigationDrawerContent(navigationView_toan);
        }
        setupNavigationDrawerContent(navigationView_toan);
        Flags.main_dethi = false;

        FragDeThi fragDeThi = (FragDeThi) getFragmentManager().findFragmentById(R.id.fra_toanhoc);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragDeThi = new FragDeThi();
        fragmentTransaction.replace(R.id.fra_toanhoc, fragDeThi, "De Thi");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())  {
            case android.R.id.home:
                drawerLayout_toan.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
    public void onBackPressed(){
        if(Flags.main_dethi == true){
            finish();
        }
        showRangeList(fragmentManager);
    }

    public static void showRangeList(android.app.FragmentManager fragmentManager){
        try{
            android.app.Fragment fragmentRangeList =
                    fragmentManager.findFragmentByTag(Def.TAG_SETTING_MONTOAN);

            if(fragmentRangeList != null){
                fragmentManager.beginTransaction().remove(fragmentRangeList).commitAllowingStateLoss();
            }

            fragmentRangeList = new FragDeThi();
            fragmentManager.beginTransaction()
                    .add(R.id.fra_toanhoc, fragmentRangeList, Def.TAG_SETTING_MONTOAN)
                    .commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }//--end showRangeList


    private void setupNavigationDrawerContent(NavigationView navigationView)  {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)  {
                        Intent intent = new Intent(DeThiDeTailActivity.this, ToanHocActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        switch (menuItem.getItemId())  {
                            case R.id.item_navigation_drawer_dethi:
                                menuItem.setChecked(true);
                                return true;

                            case R.id.item_navigation_drawer_montoan:
                                if (Flags.chosen_mon == 1){
                                    menuItem.setCheckable(true);
                                }
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 1;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monnguvan:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 2;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monanhvan:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 2;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monvatly:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 4;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monhoahoc:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 5;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monsinhoc:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 6;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monlichsu:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 7;
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_mondialy:
                                menuItem.setChecked(true);
                                Flags.chosen_mon = 8;
                                startActivity(intent);
                                return true;
                        }
                        return true;
                    }
                });
    }
}