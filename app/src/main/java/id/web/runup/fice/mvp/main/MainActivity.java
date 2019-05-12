package id.web.runup.fice.mvp.main;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import id.web.runup.fice.R;
import id.web.runup.fice.mvp.find.FindFragment;
import id.web.runup.fice.mvp.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());
    }

    private boolean loadFragment (Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.nav_home:
                setTheme(R.style.AppTheme);
                fragment = new HomeFragment();
                break;

            case R.id.nav_search:
                setTheme(R.style.AppTheme);
                fragment = new FindFragment();
                break;

            case R.id.nav_profile:
                setTheme(R.style.AppTheme);
                //fragment = new TransaksiFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
