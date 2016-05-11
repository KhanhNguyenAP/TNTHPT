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

public class LichSu extends AppCompatActivity
{
    DrawerLayout drawerLayout_lichsu;
    Toolbar toolbar_lichsu;
    ActionBar actionBar_lichsu;
    TextView textView_lichsu;

    GridView grid;
    String[] TenLichSu = {"Lịch Sử 10", "Lịch Sử 11", "Lịch Sử 12"};
    int[] IconLichSu = {R.drawable.lichsu10, R.drawable.lichsu11, R.drawable.lichsu12};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);

        toolbar_lichsu = (Toolbar) findViewById(R.id.toolbar_lichsu);
        setSupportActionBar(toolbar_lichsu);

        actionBar_lichsu = getSupportActionBar();
        actionBar_lichsu.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_lichsu.setDisplayHomeAsUpEnabled(true);

        drawerLayout_lichsu = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_lichsu);

        NavigationView navigationView_lichsu = (NavigationView) findViewById(R.id.navigation_view_lichsu);
        if (navigationView_lichsu != null)
        {
            setupNavigationDrawerContent(navigationView_lichsu);
        }

        setupNavigationDrawerContent(navigationView_lichsu);

        //Main Menu
        CustomGridView adapter_anhvan = new CustomGridView(LichSu.this, TenLichSu, IconLichSu);
        grid = (GridView) findViewById(R.id.gdv_lichsu);
        grid.setAdapter(new CustomGridView(this, TenLichSu, IconLichSu));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_lich_su, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout_lichsu.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        textView_lichsu = (TextView) findViewById(R.id.textView_lichsu);
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_home_lichsu:
                                menuItem.setChecked(true);
                                Toast.makeText(LichSu.this, "Return " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout_lichsu.closeDrawer(GravityCompat.START);
                                Intent intent_lichsu = new Intent(LichSu.this, MainActivity.class);
                                startActivity(intent_lichsu);
                                return true;

                            case R.id.item_navigation_drawer_lichsu10:
                                menuItem.setChecked(true);
                                textView_lichsu.setText(menuItem.getTitle());
                                drawerLayout_lichsu.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_lichsu11:
                                menuItem.setChecked(true);
                                textView_lichsu.setText(menuItem.getTitle());
                                drawerLayout_lichsu.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_lichsu12:
                                menuItem.setChecked(true);
                                textView_lichsu.setText(menuItem.getTitle());
                                drawerLayout_lichsu.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }
}