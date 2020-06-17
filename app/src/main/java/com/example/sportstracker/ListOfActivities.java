package com.example.sportstracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListOfActivities extends AppCompatActivity implements SportOnClickedListener {

    @BindView(R.id.navigation)
    NavigationView navigation;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;

    @BindView(R.id.activityRecyclerView)
    RecyclerView activityRecyclerView;

    ActivityListAdapter activityListAdapter;

    ActivityViewModel activityViewModel;


    protected void onCreate(Bundle savedInsState) {
        super.onCreate(savedInsState);
        setContentView(R.layout.list_of_activities);

        ButterKnife.bind(this);

        activityRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        activityListAdapter = new ActivityListAdapter(this);

        activityRecyclerView.setAdapter(activityListAdapter);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, 0, 0);
        drawerToggle.syncState();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(ListOfActivities.this, item.getTitle(), Toast.LENGTH_SHORT).show();
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

                if (id == R.id.mainMenu) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else if (id == R.id.statistics) {
                    //startActivity(new Intent(getApplicationContext(), DiaryActivity.class));
                    Toast.makeText(ListOfActivities.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ViewModelProvider.AndroidViewModelFactory viewModelFactory
                = new
                ViewModelProvider.AndroidViewModelFactory(getApplication());
        ViewModelProvider viewModelProvider = new
                ViewModelProvider(this, viewModelFactory);
        activityViewModel = viewModelProvider.get(ActivityViewModel.class);

        activityViewModel.getActivities().observe(this, activities -> activityListAdapter.submitList(activities));

        ItemTouchHelper.SimpleCallback swipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //  activityViewModel.deleteActvity(activityListAdapter.getItem(viewHolder.getLayoutPosition()));

            }
        };
        new ItemTouchHelper(swipeCallback).attachToRecyclerView(activityRecyclerView);


    }

    @Override
    public void onSportClick(Activity activity) {
        new InfoDialog(this) {

            @Override
            protected void setData() {

                String type = activity.getType();
                switch (type) {
                    case "basketball":
                        this.sportImageView.setImageResource(R.drawable.basketball);
                        break;
                    case "box":
                        this.sportImageView.setImageResource(R.drawable.box);
                        break;
                    case "cycling":
                        this.sportImageView.setImageResource(R.drawable.cycling);
                        break;
                    case "football":
                        this.sportImageView.setImageResource(R.drawable.football);
                        break;
                    case "gym":
                        this.sportImageView.setImageResource(R.drawable.gym);
                        break;
                    case "gymnastics":
                        this.sportImageView.setImageResource(R.drawable.gymnastics);
                        break;
                    case "rollerskating":
                        this.sportImageView.setImageResource(R.drawable.rollerskating);
                        break;
                    case "running":
                        this.sportImageView.setImageResource(R.drawable.running);
                        break;
                    case "skiing":
                        this.sportImageView.setImageResource(R.drawable.skiing);
                        break;
                    case "swimming":
                        this.sportImageView.setImageResource(R.drawable.swimming);
                        break;
                    default:
                        this.sportImageView.setImageResource(R.drawable.tennis);
                        break;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

                this.timeFromText.setText(R.string.time_from_is);
                this.timeFromTextView.setText(sdf.format(activity.getTimeFrom()));

                this.timeToText.setText(R.string.time_to_is);
                this.timeToTextView.setText(sdf.format(activity.getTimeTo()));

                if (activity.getDistance() != 0) {
                    this.distanceText.setText(R.string.distance_is);
                    this.distanceTextView.setText(activity.getDistance() + " km");
                }
            }
        };
    }

}
