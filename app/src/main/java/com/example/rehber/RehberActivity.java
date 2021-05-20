package com.example.rehber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.rehber.Adapter.adapter;
import com.example.rehber.Model.Address;
import com.example.rehber.Model.Model;
import com.example.rehber.Response.RetrofitApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RehberActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    Toolbar toolbar;

    public adapter adapter;
    public RecyclerView recyclerView;
    public FloatingActionButton fab;
    public ArrayList<Model> modelclassList=new ArrayList<>();
    public ArrayList<Model> memoryclassList=new ArrayList<>();
    public ArrayList<Model> List =new ArrayList<>();

    String username,userEmail,userCity,userWeb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehber);
        fab = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

//gson parse converter



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RehberActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });



        toolbar = findViewById(R.id.toolbar);
      //  toolbar.setTitle("Arama");
        setSupportActionBar(toolbar);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

        Call<List<Model>> call = retrofitApi.getUsers();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if(!response.isSuccessful())
                {
                    System.out.println("error");
                    return;
                }

                List<Model> users = response.body();



                Bundle bundle = getIntent().getExtras();
                if (bundle!=null){

                    Model model = getIntent().getParcelableExtra("model");
                    Address address = getIntent().getParcelableExtra("adres");
                    List.add(model);
                    List.addAll(loadDate());
                    System.out.println(modelclassList.size());
                    saveData(List);
                }
                    if(loadDate()!=null)
                    {
                        modelclassList.clear();
                            for(int i = 0;i<users.size();i++)
                            {
                                modelclassList.add(users.get(i));
                            }

                      modelclassList.addAll(loadDate());
                    }



                adapter = new adapter(RehberActivity.this,modelclassList);
                recyclerView.setAdapter(adapter);




            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                System.out.println("failure");
                return;
            }
        });



    }
    private void saveData(ArrayList<Model> modelclassList)
    {
        SharedPreferences sharedPreferences =getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(modelclassList);
        editor.putString("task List",json);
        editor.apply();
    }

    private ArrayList<Model>  loadDate()
    {
        SharedPreferences sharedPreferences =getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task List",null);
        Type type = new TypeToken<ArrayList<Model>>() {}.getType();
        memoryclassList = gson.fromJson(json,type);

        if(memoryclassList == null)
        {
            memoryclassList = new ArrayList<>();
        }
        else
        {
            System.out.println(memoryclassList.size());
        }
        return memoryclassList;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        System.out.println("Gonderilen Arama Sonucu "+query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        System.out.println("Harf girdikce Sonucu "+newText);
        return true;
    }





}