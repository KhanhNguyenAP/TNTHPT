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

public class NguVan extends AppCompatActivity
{
    DrawerLayout drawerLayout_nguvan;
    Toolbar toolbar_nguvan;
    ActionBar actionBar_nguvan;
    TextView textView_nguvan;

    GridView grid;
    String[] TenNguVan = {"Ngữ Văn 10", "Ngữ Văn 11", "Ngữ Văn 12"};
    int[] IconNguVan = {R.drawable.nguvan, R.drawable.nguvan, R.drawable.nguvan};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngu_van);

        toolbar_nguvan = (Toolbar) findViewById(R.id.toolbar_nguvan);
        setSupportActionBar(toolbar_nguvan);

        actionBar_nguvan = getSupportActionBar();
        actionBar_nguvan.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_nguvan.setDisplayHomeAsUpEnabled(true);

        drawerLayout_nguvan = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_nguvan);

        NavigationView navigationView_nguvan= (NavigationView) findViewById(R.id.navigation_view_nguvan);
        if (navigationView_nguvan != null)
        {
            setupNavigationDrawerContent(navigationView_nguvan);
        }

        setupNavigationDrawerContent(navigationView_nguvan);

        //Main Menu
        CustomGridView adapter_toan = new CustomGridView(NguVan.this, TenNguVan, IconNguVan);
        grid = (GridView) findViewById(R.id.gdv_nguvan);
        grid.setAdapter(new CustomGridView(this, TenNguVan, IconNguVan));
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
}