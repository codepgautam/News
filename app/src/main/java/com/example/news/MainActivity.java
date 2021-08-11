package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    RecyclerView myRecyclerView;
    RestApiInterface restApiInterface;
    CustomAdapter myCustomAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<ArticlesList> my_list;

    private static final String BASE_URL = "https://newsapi.org/";
    String apikey = "af2d2c9c5f3649f1bb0769e574bb8950";
    String country = "in";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_menu);
        drawerLayout = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.news_feed:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.settings:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent i = new Intent(MainActivity.this, Settings_Acttivity.class);
                        startActivity(i);
                        break;
                }
                return true;
            }
        });

        my_list = new ArrayList<>();
        myRecyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        myRecyclerView.setLayoutManager(linearLayoutManager);


        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restApiInterface = retrofit.create(RestApiInterface.class);
        Call<ResponseModel> call = restApiInterface.getlists(country, apikey);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null) {
                    my_list = response.body().getArticles_lists();
                    myCustomAdapter = new CustomAdapter(getApplicationContext(), my_list);
                    myRecyclerView.setAdapter(myCustomAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });


    }
}