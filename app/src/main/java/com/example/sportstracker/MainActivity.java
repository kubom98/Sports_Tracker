package com.example.sportstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.navigation)
    NavigationView navigation;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.calendarView)
    CalendarView mCalendarView;

//    @BindView(R.id.calendarView)
//    MCalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, 0, 0);
        drawerToggle.syncState();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (item.isChecked()) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                }

                if (id == R.id.allActivities) {
                    startActivity(new Intent(getApplicationContext(), ListOfActivities.class));

                } else if (id == R.id.statistics) {
                    //startActivity(new Intent(getApplicationContext(), DiaryActivity.class));
                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChooseSportActivity.class));
            }
        });

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                String date = dayOfMonth + "." + month + "." + year;
                Intent intent = new Intent(MainActivity.this, ChooseSportActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

//        mCalendarView.setOnDateClickListener(new OnDateClickListener() {
//            @Override
//            public void onDateClick(View view, DateData date) {
//                mCalendarView.markDate(date.getDay(), date.getMonth(), date.getYear());
//                String dateStr = date.getDay() + "." + date.getMonth() + "." + date.getYear();
//                Intent intent = new Intent(MainActivity.this, ChooseSportActivity.class);
//                intent.putExtra("date", dateStr);
//                startActivity(intent);
//            }
//        });

    }
}



