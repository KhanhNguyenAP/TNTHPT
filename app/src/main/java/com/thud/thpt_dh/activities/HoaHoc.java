package com.thud.thpt_dh.activities;

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

public class HoaHoc extends AppCompatActivity {
    DrawerLayout drawerLayout_hoahoc;
    Toolbar toolbar_hoahoc;
    ActionBar actionBar_hoahoc;
    TextView textView_hoahoc;

    GridView grid;
    String[] TenHoaHoc = {"Hóa Học 10", "Hóa Học 11", "Hóa Học 12"};
    int[] IconHoaHoc = {R.drawable.hoa, R.drawable.hoa, R.drawable.hoa};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_hoc);

        toolbar_hoahoc = (Toolbar) findViewById(R.id.toolbar_hoahoc);
        setSupportActionBar(toolbar_hoahoc);

        actionBar_hoahoc = getSupportActionBar();
        actionBar_hoahoc.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_hoahoc.setDisplayHomeAsUpEnabled(true);

        drawerLayout_hoahoc = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_hoahoc);

        NavigationView navigationView_hoahoc = (NavigationView) findViewById(R.id.navigation_view_hoahoc);
        if (navigationView_hoahoc != null)
        {
            setupNavigationDrawerContent(navigationView_hoahoc);
        }

        setupNavigationDrawerContent(navigationView_hoahoc);

        //Main Menu
        CustomGridView adapter_hoahoc = new CustomGridView(HoaHoc.this, TenHoaHoc, IconHoaHoc);
        grid = (GridView) findViewById(R.id.gdv_hoahoc);
        grid.setAdapter(new CustomGridView(this, TenHoaHoc, IconHoaHoc));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout_hoahoc.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
    }
}