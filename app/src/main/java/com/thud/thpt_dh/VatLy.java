package com.thud.thpt_dh;

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

import xuly.CustomGridView;

public class VatLy extends AppCompatActivity
{
    DrawerLayout drawerLayout_vatly;
    Toolbar toolbar_vatly;
    ActionBar actionBar_vatly;
    TextView textView_vatly;

    GridView grid;
    String[] TenVatLy = {"Vật Lý 10", "Vật Lý 11", "Vật Lý 12"};
    int[] IconVatLy = {R.drawable.vatly10, R.drawable.vatly11, R.drawable.vatly12};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vat_ly);

        toolbar_vatly = (Toolbar) findViewById(R.id.toolbar_vatly);
        setSupportActionBar(toolbar_vatly);

        actionBar_vatly = getSupportActionBar();
        actionBar_vatly.setHomeAsUpIndicator(R.drawable.menu);
        actionBar_vatly.setDisplayHomeAsUpEnabled(true);

        drawerLayout_vatly = (DrawerLayout) findViewById(R.id.navigation_drawer_layout_vatly);

        NavigationView navigationView_vatly = (NavigationView) findViewById(R.id.navigation_view_vatly);
        if (navigationView_vatly != null)
        {
            setupNavigationDrawerContent(navigationView_vatly);
        }

        setupNavigationDrawerContent(navigationView_vatly);

        //Main Menu
        CustomGridView adapter_anhvan = new CustomGridView(VatLy.this, TenVatLy, IconVatLy);
        grid = (GridView) findViewById(R.id.gdv_vatly);
        grid.setAdapter(new CustomGridView(this, TenVatLy, IconVatLy));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_vat_ly, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout_vatly.openDrawer(GravityCompat.START);
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
                        textView_vatly = (TextView) findViewById(R.id.textView_vatly);
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_home_vatly:
                                menuItem.setChecked(true);
                                Toast.makeText(VatLy.this, "Return " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout_vatly.closeDrawer(GravityCompat.START);
                                Intent intent_vatly = new Intent(VatLy.this, MainActivity.class);
                                startActivity(intent_vatly);
                                return true;

                            case R.id.item_navigation_drawer_vatly10:
                                menuItem.setChecked(true);
                                textView_vatly.setText(menuItem.getTitle());
                                drawerLayout_vatly.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_vatly11:
                                menuItem.setChecked(true);
                                textView_vatly.setText(menuItem.getTitle());
                                drawerLayout_vatly.closeDrawer(GravityCompat.START);
                                return true;

                            case R.id.item_navigation_drawer_vatly12:
                                menuItem.setChecked(true);
                                textView_vatly.setText(menuItem.getTitle());
                                drawerLayout_vatly.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }
}