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

public class SinhHoc extends AppCompatActivity
{
    DrawerLayout drawerLayout_sinhhoc;
    Toolbar toolbar_sinhhoc;
    ActionBar actionBar_sinhhoc;
    TextView textView_sinhhoc;

    GridView grid;
    String[] TenSinhHoc = {"Sinh Học 10", "Sinh Học 11", "Sinh Học 12"};
    int[] IconSinhHoc = {R.drawable.sinh, R.drawable.sinh, R.drawable.sinh};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_hoc);

        toolbar_sinhhoc = (Toolbar) findViewById(R.id.toolbar_sinhhoc);
        setSupportActionBar(toolbar_sinhhoc);

        actionBar_sinhhoc = getSupportActionBar();
        actionBar_sinhhoc.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_sinhhoc.setDisplayHomeAsUpEnabled(true);

        drawerLayout_sinhhoc = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_sinhhoc);

        NavigationView navigationView_sinhhoc= (NavigationView) findViewById(R.id.navigation_view_sinhhoc);
        if (navigationView_sinhhoc != null)
        {
            setupNavigationDrawerContent(navigationView_sinhhoc);
        }

        setupNavigationDrawerContent(navigationView_sinhhoc);

        //Main Menu
        CustomGridView adapter_sinhhoc = new CustomGridView(SinhHoc.this, TenSinhHoc, IconSinhHoc);
        grid = (GridView) findViewById(R.id.gdv_sinhhoc);
        grid.setAdapter(new CustomGridView(this, TenSinhHoc, IconSinhHoc));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout_sinhhoc.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView){
    }
}