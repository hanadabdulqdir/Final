package anime.hanad.com.anime;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import anime.hanad.com.anime.AnimeDetail.fragment.AnimeDetailFragment;

import anime.hanad.com.anime.AnimeList.fragment.AnimeFragment;
import anime.hanad.com.anime.AnimeList.fragment.tabFragments.Tab2;
import anime.hanad.com.anime.AnimeList.fragment.tabFragments.dataFragment;
import anime.hanad.com.anime.network.service.IRequestInterface;
import anime.hanad.com.anime.realm_database.controller.RealmHelper;
import anime.hanad.com.anime.realm_database.model.AnimeModel;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;
    private static Button button_sbm;
    private static FragmentManager fragmentManager;
    private IRequestInterface iRequestInterface;
    RecyclerView recyclerView;
    Bundle savedInstanceState;
    private Realm realm;
    private static RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadLocate();
        setContentView(R.layout.activity_main);

        //ActionBar actionBar = getSupportActionBar();
        // actionBar.setTitle(getResources().getString(R.string.app_name));

        tabView();
        //--
        fragmentManager = getSupportFragmentManager();
        initRealm();
        //rockMusicFragment();
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //-------
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_AnimeList:
                    Toast.makeText(MainActivity.this, "Anime List", Toast.LENGTH_SHORT).show();
                    rockMusicFragment();
                    //tabView();
                    return true;

                case R.id.navigation_Episode:
                    Toast.makeText(MainActivity.this, "Episodes", Toast.LENGTH_SHORT).show();
                    tabView();
                    classicMusicFragment();
                    return true;

                case R.id.navigation_Search:
                    Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                    popMusicFragment();
                    return true;
            }
            return false;
        }
    };

    private void initRealm() {
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
    }

    public static ArrayList<AnimeModel> getRealmDatabase() {
        Log.i("realm db", String.valueOf(realmHelper.getAnimeModle().size()));
        return realmHelper.getAnimeModle();
    }

    public static void deleteRealmDatabase() {

        realmHelper.deleteRealmData();
    }

    public static void saveRealm(String artistName, String collectionName, String artworkUrl60, String trackPrice) {
        AnimeModel classicAnimeModel = new AnimeModel(artistName, collectionName, artworkUrl60, trackPrice);
        realmHelper.storeData(classicAnimeModel);
        Log.i("realm database", String.valueOf(realmHelper.getAnimeModle().size()));
    }

    public void rockMusicFragment() {
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, new AnimeFragment())
                    .disallowAddToBackStack()
                    .commit();
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void classicMusicFragment() {
        Toast.makeText(this, "Page 2", Toast.LENGTH_SHORT).show();
    }

    public void popMusicFragment() {
        Toast.makeText(this, "Page 3", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ChangeLanguage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Toast.makeText(this, "AnimeList", Toast.LENGTH_SHORT).show();
            rockMusicFragment();
            // Handle the camera action
            //setFragment(new dataFragment());
        } else if (id == R.id.nav_gallery) {
            tabView();
            Toast.makeText(this, "Episodes", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_slideshow) {
            classicMusicFragment();
            Toast.makeText(this, "Page 2", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_manage) {
            //popMusicFragment();
            Toast.makeText(this, "Page 3", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "Page 4", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Page 5", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    public void setFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentContainer, fragment);//changed from content_main to fragmentContainer
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void tabView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLanguage();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setFragment(new dataFragment());//init
    }

    public void ChangeLanguage() {
        final String[] ListItems = {"Spanish", "English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(ListItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    //Spanish
                    setLocale("es");
                    recreate();
                } else if (i == 1) {
                    //Spanish
                    setLocale("");
                    recreate();
                }

                dialog.dismiss();

            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void LoadLocate() {
        SharedPreferences pref = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String language = pref.getString("My_Lang", "");
        setLocale(language);
    }


    public static void diplayEpisode(String id) {
        Tab2 tab2 = new Tab2();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        tab2.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, tab2)
                .addToBackStack(null)
                .commit();
    }
}
