package com.thud.thpt_dh.activities;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.net.ConnectivityManager;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.support.design.widget.NavigationView;
    import android.support.v4.view.GravityCompat;
    import android.support.v4.widget.DrawerLayout;
    import android.support.v7.app.ActionBar;
    import android.support.v7.app.AlertDialog;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.Toolbar;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.GridView;
    import android.widget.Toast;

    import com.thud.thpt_dh.R;

    import com.thud.thpt_dh.customcontrols.CustomGridView;
    import com.thud.thpt_dh.utils.async.SaveAllDataFromSerVer;
    import com.thud.thpt_dh.utils.interfaces.Flags;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;

    GridView grid;
    String[] TenMon = {"Toán", "Ngữ Văn", "Anh Văn", "Sinh Học","Vật Lý", "Hóa", "Lịch Sử", "Địa Lý", "Đề Thi"};
    int[] Icon = {R.drawable.toan, R.drawable.nguvan, R.drawable.anhvan,
            R.drawable.sinh, R.drawable.vatly, R.drawable.hoa,
            R.drawable.lichsu, R.drawable.dialy, R.drawable.lichsu};

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cai dat toolbar thay the cho Actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //tìm drawer view
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null)  {
            setupNavigationDrawerContent(navigationView);
        }
        setupNavigationDrawerContent(navigationView);
        navigationView.getMenu().getItem(0).setChecked(true);

        //Main Menu
        onContentGridView();
        if (Flags.synch_data == 0){
            new SaveAllDataFromSerVer(MainActivity.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    public void onContentGridView(){
        CustomGridView adapter = new CustomGridView(MainActivity.this, TenMon, Icon);
        grid = (GridView) findViewById(R.id.gdv_menu);
        grid.setAdapter(new CustomGridView(this, TenMon, Icon));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener()   {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)   {
                Intent intent = null;
                switch (pos) {
                    case 0:
                        intent = new Intent(MainActivity.this, ToanHocActivity.class);
                        Flags.chosen_mon = 1;
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, ToanHocActivity.class);
                        Flags.chosen_mon = 2;
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, ToanHocActivity.class);
                        Flags.chosen_mon = 3;
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, ToanHocActivity.class);
                        Flags.chosen_mon = 6;
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, ToanHocActivity.class);
                        Flags.chosen_mon = 4;
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, ToanHocActivity.class);
                        Flags.chosen_mon = 5;
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, ToanHocActivity.class);
                        Flags.chosen_mon = 7;
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, ToanHocActivity.class);
                        Flags.chosen_mon = 8;
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, DeThiActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        switch (item.getItemId())    {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView)  {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)  {
                        switch (menuItem.getItemId())  {
                            case R.id.item_navigation_drawer_trangchu:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_montoan:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Flags.chosen_mon = 1;
                                intent = new Intent(MainActivity.this, ToanHocActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monnguvan:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Flags.chosen_mon = 2;
                                intent = new Intent(MainActivity.this, ToanHocActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monanhvan:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Flags.chosen_mon = 3;
                                intent = new Intent(MainActivity.this, ToanHocActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monvatly:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Flags.chosen_mon = 4;
                                intent = new Intent(MainActivity.this, ToanHocActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monhoahoc:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Flags.chosen_mon = 5;
                                intent = new Intent(MainActivity.this, ToanHocActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monsinhoc:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Flags.chosen_mon = 6;
                                intent = new Intent(MainActivity.this, ToanHocActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_monlichsu:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Flags.chosen_mon = 7;
                                intent = new Intent(MainActivity.this, ToanHocActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_mondialy:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Flags.chosen_mon = 8;
                                intent = new Intent(MainActivity.this, ToanHocActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_dethi:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(MainActivity.this, DeThiActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_taidulieu:
                                inInternetOn();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                intent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_navigation_drawer_thoat:
                                showDialog();
                                return true;
                        }
                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed(){
        showDialog();
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this application ?")
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.setTitle(R.string.close_app);
        alert.show();
    }

    public final boolean inInternetOn(){
        ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet
            Flags.synch_data = 0;
            Flags.chosen_synch_data = 1;
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Flags.synch_data = 1;
            Flags.chosen_synch_data = 0;

            Toast.makeText(this, "You are not connect to the internet ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
}
