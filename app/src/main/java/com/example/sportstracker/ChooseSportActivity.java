package com.example.sportstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseSportActivity extends AppCompatActivity {

    String sport;
    String dateStr;
    int hour;
    int min;


    @BindView(R.id.navigation)
    NavigationView navigation;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;

    @BindView(R.id.fab2)
    FloatingActionButton fab;

    @BindView(R.id.chooseSportSpinner)
    Spinner chooseSportSpinner;

    @BindView(R.id.timeFromButton)
    MaterialButton timeFromButton;

    @BindView(R.id.timeToButton)
    MaterialButton timeToButton;

    @BindView(R.id.distanceEditText)
    TextInputEditText distanceEditText;

    ActivityViewModel activityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sport);

        ButterKnife.bind(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, 0, 0);
        drawerToggle.syncState();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(ChooseSportActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
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

                } else if (id == R.id.mainMenu) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                } else if (id == R.id.statistics) {
                    //startActivity(new Intent(getApplicationContext(), DiaryActivity.class));
                    Toast.makeText(ChooseSportActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sports, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseSportSpinner.setAdapter(adapter);

        chooseSportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    sport = "basketball";
                    distanceEditText.setEnabled(false);
                } else if (position == 1) {
                    sport = "box";
                    distanceEditText.setEnabled(false);
                } else if (position == 2) {
                    sport = "cycling";
                    distanceEditText.setEnabled(true);
                } else if (position == 3) {
                    sport = "football";
                    distanceEditText.setEnabled(false);
                } else if (position == 4) {
                    sport = "gym";
                    distanceEditText.setEnabled(false);
                } else if (position == 5) {
                    sport = "gymnastics";
                    distanceEditText.setEnabled(false);
                } else if (position == 6) {
                    sport = "rollerskating";
                    distanceEditText.setEnabled(true);
                } else if (position == 7) {
                    sport = "running";
                    distanceEditText.setEnabled(true);
                } else if (position == 8) {
                    sport = "skiing";
                    distanceEditText.setEnabled(true);
                } else if (position == 9) {
                    sport = "swimming";
                    distanceEditText.setEnabled(true);
                } else {
                    sport = "tennis";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sport = "basketball";
            }
        });

        timeFromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(0, 0, timeFromButton);
            }
        });

        timeToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(0, 0, timeToButton);
            }
        });

        ViewModelProvider.AndroidViewModelFactory viewModelFactory
                = new
                ViewModelProvider.AndroidViewModelFactory(getApplication());
        ViewModelProvider viewModelProvider = new
                ViewModelProvider(this, viewModelFactory);
        activityViewModel = viewModelProvider.get(ActivityViewModel.class);

        Intent incomingIntent = getIntent();
        dateStr = incomingIntent.getStringExtra("date");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = new Activity();
                activity.setType(sport);
                SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
                Date date = new Date();
                if (dateStr != null) {
                    try {
                        date = sdfDate.parse(dateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    activity.setDate(date);
                } else {
                    activity.setDate(new Date());
                }
                SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
                try {
                    activity.setTimeFrom(sdfTime.parse((String) timeFromButton.getText()));
                    activity.setTimeTo(sdfTime.parse((String) timeToButton.getText()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (!distanceEditText.isEnabled()) {
                    activity.setDistance(0);
                } else {
                    activity.setDistance(Double.parseDouble(distanceEditText.getText().toString()));
                }
                activityViewModel.addActivity(activity);

                Toast.makeText(ChooseSportActivity.this, R.string.added_activity, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void showTimePickerDialog(int h, int m, MaterialButton button) {
        TimePickerDialog builder = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        ChooseSportActivity.this.hour = hour;
                        ChooseSportActivity.this.min = min;
                        button.setText(hour + ":" + min);
                    }
                }, h, m, true);
        builder.show();
    }

}
