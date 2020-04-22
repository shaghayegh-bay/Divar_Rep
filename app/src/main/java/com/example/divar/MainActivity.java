package com.example.divar;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.divar.RecyclerView.ModelRecycler;
import com.example.divar.RecyclerView.RetrofitAdapter;
import com.example.divar.Retrofit.APIService;
import com.example.divar.Retrofit.GetAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RetrofitAdapter retrofitAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//--------------------------------------------------------------------------------
// خط کد زیر برای باز نشدن کیبورد بخاطر ادیت تکست هست
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
//--------------------------------------------------------------------------------
        recyclerView = findViewById(R.id.recycler);
        fetchJson();
    }

    // رتروفیت, گرفتن داده از سرور
    private void fetchJson() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService api = retrofit.create(APIService.class);
        Call<List<GetAds>> call = api.getAdsModels();

        call.enqueue(new Callback<List<GetAds>>() {
            @Override
            public void onResponse(Call<List<GetAds>> call, Response<List<GetAds>> response) {

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
            public void onFailure(Call<List<GetAds>> call, Throwable t) {
                Log.e("onEmptyResponse", t.getMessage());//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();

                // Toast.makeText(MainActivity.this, "" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // قرار دادن داده های دریافت شده از سرور در ریسایکلر ویو
    private void writeRecycler(String response) {

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
          //  if (obj.optString("status").equals("true")) {

                ArrayList<ModelRecycler> modelRecyclerArrayList = new ArrayList<>();
                JSONArray dataArray = obj.getJSONArray("ads");

                for (int i = 0; i < dataArray.length(); i++) {

                    ModelRecycler modelRecycler = new ModelRecycler();
                    JSONObject dataobj = dataArray.getJSONObject(i);

                    modelRecycler.setAvatar(dataobj.getString("avatar"));
                    modelRecycler.setTitle(dataobj.getString("title"));
                    modelRecycler.setPrice(dataobj.getString("price"));
                    modelRecycler.setTime(dataobj.getString("time"));

                    modelRecyclerArrayList.add(modelRecycler);
                }

                retrofitAdapter = new RetrofitAdapter(modelRecyclerArrayList, this);
                recyclerView.setAdapter(retrofitAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//
//            } else {
//                Toast.makeText(MainActivity.this, obj.optString("message") + "", Toast.LENGTH_SHORT).show();
//            }

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
