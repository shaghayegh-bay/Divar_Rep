package com.example.divar;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.divar.RecyclerView.Item;
import com.example.divar.RecyclerView.RetrofitAdapter;
import com.example.divar.Retrofit.APIService;
import com.example.divar.Retrofit.DetailsPojo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt_title, txt_price, txt_time;
    ImageView img_avatar;
    RecyclerView recyclerView;
    List<Item> items = new ArrayList<>();
    RetrofitAdapter retrofitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        txt_title = findViewById(R.id.txt_title);
        img_avatar = findViewById(R.id.img_avatar);
        txt_price = findViewById(R.id.txt_price);
        txt_time = findViewById(R.id.txt_time);


//--------------------------------------------------------------------------------
// خط کد زیر برای باز نشدن کیبورد بخاطر ادیت تکست هست
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
//--------------------------------------------------------------------------------

        // ریسایکلر ویو
        recyclerView = findViewById(R.id.recycler_view);

        retrofitAdapter = new RetrofitAdapter((ArrayList<Item>) items, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(retrofitAdapter);
        fetchData();

    }

    // رتروفیت, گرفتن داده از سرور
    private void fetchData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL) // Specify your api here
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService api = retrofit.create(APIService.class);
        Call<List<DetailsPojo>> call = api.getAdsModels();

        call.enqueue(new Callback<List<DetailsPojo>>() {
            @Override
            public void onResponse(Call<List<DetailsPojo>> call, Response<List<DetailsPojo>> response) {

                Log.e("ResponseString", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.e("onSuccess", response.body().toString());

                        String jsonResponse = response.body().toString();
                        writeRecycler(jsonResponse);

                    } else {
                        Log.e("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DetailsPojo>> call, Throwable t) {
                Log.e("onEmptyResponse", t.getMessage());//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();

                Toast.makeText(MainActivity.this, "" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // قرار دادن داده ها در ریسایکلر ویو با رتروفیت
    private void writeRecycler(String response) {

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
            if (obj.optString("status").equals("true")) {

                ArrayList<Item> modelRecyclerArrayList = new ArrayList<>();
                JSONArray dataArray = obj.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                    Item modelRecycler = new Item();
                    JSONObject dataobj = dataArray.getJSONObject(i);

                   // modelRecycler.setItemAvatar(dataobj.getString("avatar"));
                    modelRecycler.setItemTitle(dataobj.getString("title"));
                    modelRecycler.setItemPrice(dataobj.getString("price"));
                    modelRecycler.setItemTime(dataobj.getString("time"));

                    modelRecyclerArrayList.add(modelRecycler);
                }

                retrofitAdapter = new RetrofitAdapter(modelRecyclerArrayList, this);
                recyclerView.setAdapter(retrofitAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

            } else {
                Toast.makeText(MainActivity.this, obj.optString("message") + "", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //-----------------------------------------------------------------------------------------------------
    // bottom navigation
   // BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            if (item.getItemId() == R.id.favorites) {
//                Toast.makeText(getBaseContext(), "Favorites pressed!", Toast.LENGTH_LONG).show();
//                return true;
//            } else if (item.getItemId() == R.id.list) {
//                Toast.makeText(getBaseContext(), "List pressed!", Toast.LENGTH_LONG).show();
//                return true;
//            } else if (item.getItemId() == R.id.home) {
//                Toast.makeText(getBaseContext(), "Home pressed!", Toast.LENGTH_LONG).show();
//                return true;
//            }
//            return false;
//        }
//    });

}
