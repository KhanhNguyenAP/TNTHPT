package com.thud.thpt_dh.fragment;

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
import android.widget.Toast;

import com.thud.thpt_dh.activities.MainActivity;
import com.thud.thpt_dh.R;

public class HuongDanSuDung extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdsd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_hdsd);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.nav_leftmenu_hdsd);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view_hdsd);
        if (navigationView != null)  {
            setupNavigationDrawerContent(navigationView);
        }
        setupNavigationDrawerContent(navigationView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView)  {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_home:
                                Toast.makeText(HuongDanSuDung.this, menuItem.getTitle().toString() + " đang thực thi", Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(HuongDanSuDung.this, MainActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_quyche:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(HuongDanSuDung.this, QuyCheThi.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_hdsd:
                                menuItem.setChecked(true);
                                Toast.makeText(HuongDanSuDung.this, menuItem.getTitle().toString() + " đang thực thi", Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(HuongDanSuDung.this, QuyCheThi.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_lichsu:
                                menuItem.setChecked(true);
                                Toast.makeText(HuongDanSuDung.this, menuItem.getTitle().toString() + " đang thực thi", Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(HuongDanSuDung.this, QuyCheThi.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_about:
                                menuItem.setChecked(true);
                                Toast.makeText(HuongDanSuDung.this, menuItem.getTitle().toString() + " đang thực thi", Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(HuongDanSuDung.this, QuyCheThi.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_star:
                                menuItem.setChecked(true);
                                Toast.makeText(HuongDanSuDung.this, menuItem.getTitle().toString() + " đang thực thi", Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(HuongDanSuDung.this, QuyCheThi.class);
                                startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);
                                Toast.makeText(HuongDanSuDung.this, menuItem.getTitle().toString() + " đang thực thi", Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(HuongDanSuDung.this, QuyCheThi.class);
                                startActivity(intent);
                                return true;
                        }
                        return true;
                    }
                });
    }
}
