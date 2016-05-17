package com.thud.thpt_dh.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.thud.thpt_dh.R;

import com.thud.thpt_dh.fragment.FragToanHoc;
import com.thud.thpt_dh.utils.interfaces.ActivityInterface;

public class ToanHoc extends BaseActivity implements ActivityInterface {
    DrawerLayout drawerLayout_toan;
    Toolbar toolbar_toan;
    ActionBar actionBar_toan;

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

        FragToanHoc fragToanHoc = (FragToanHoc) getFragmentManager().findFragmentById(R.id.fra_toanhoc);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragToanHoc = new FragToanHoc();
        fragmentTransaction.replace(R.id.fra_toanhoc, fragToanHoc, "Toan Hoc");
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
}