package com.example.activityexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activityexample.json.example.activityexample.Aladhan;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class OwghatPrayer extends AppCompatActivity {

    TextView txtSobh,txtZohr,txtMaghreb;
    EditText edtSearch;
    Button btnSearch;
    RecyclerView Cities;
    ArrayList<String> Citylists=new ArrayList<>();
    AdapterOwghat oghatAdapter = new AdapterOwghat(Citylists);
    ArrayList<OwghatViewModel> HawkTemp=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owghat_prayer);

        txtSobh=findViewById(R.id.txtSobh);
        txtZohr=findViewById(R.id.txtZohr);
        txtMaghreb=findViewById(R.id.txtMaghreb);
        edtSearch=findViewById(R.id.edtSearch);
        btnSearch=findViewById(R.id.btnSearch);

        Cities=findViewById(R.id.recycleCity);

        Hawk.init(this).build();
        try {
            ArrayList<OwghatViewModel> temp=  Hawk.get("Owghat");


            if(Hawk.count()>0)
            {
                for (int i = 0; i < Hawk.count(); i++) {
                    Citylists.add(temp.get(i).City);

                }
            }
            Cities.setAdapter(oghatAdapter);
            Cities.setLayoutManager(new LinearLayoutManager(OwghatPrayer.this, RecyclerView.HORIZONTAL, true));

        }
        catch (Exception e)
        {
            Log.d("tt",e.getMessage());
            Toast.makeText(OwghatPrayer.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(edtSearch.length()>0)
                {
                AsyncHttpClient client=new AsyncHttpClient();

                    client.get("http://api.aladhan.com/v1/timingsByCity?city="+edtSearch.getText().toString().toLowerCase()+"&country=Iran&method=8",new JsonHttpResponseHandler(){

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            try {
                                if(Hawk.get(edtSearch.getText().toString().toLowerCase())==null)
                                {

                                    useHawkGson(response);
                                    Citylists.add(edtSearch.getText().toString().toLowerCase());

                                    oghatAdapter.notifyDataSetChanged();

                                }
                                else{

                                    Toast.makeText(OwghatPrayer.this, "وجود دارد", Toast.LENGTH_SHORT).show();

                                }

                            }catch (Exception e){
                                Toast.makeText(OwghatPrayer.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                            Toast.makeText(OwghatPrayer.this, "نام شهر وجود ندارد.", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else
                {

                    Toast.makeText(OwghatPrayer.this, "لطفا نام  شهر را وارد نمایید", Toast.LENGTH_SHORT).show();
                    edtSearch.findFocus();
                    return;
                }

            }
        });
        oghatAdapter.setOnItemClickListener(new AdapterOwghat.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ArrayList<OwghatViewModel> temp=Hawk.get("Owghat");
                OwghatViewModel t=temp.get(position);
                txtSobh.setText("اذان صبح:"+t.Sobh);
                txtZohr.setText("اذان ظهر:"+t.Zohr);
                txtMaghreb.setText("اذان مغرب:"+t.Maghreb);
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });


    }
    public void useHawkGson(JSONObject Response)
    {
        Gson Temp=new Gson();
        Aladhan jsonAladhan=Temp.fromJson(Response.toString(),Aladhan.class);
        OwghatViewModel owghatViewModel= new OwghatViewModel();
        owghatViewModel.Sobh=jsonAladhan.getData().getTimings().getFajr();
        owghatViewModel.Zohr=jsonAladhan.getData().getTimings().getDhuhr();
        owghatViewModel.Maghreb=jsonAladhan.getData().getTimings().getMaghrib();
        owghatViewModel.City=edtSearch.getText().toString();
        HawkTemp.add(owghatViewModel);
        Hawk.put("Owghat",HawkTemp);

       txtSobh.setText("اذان صبح:"+owghatViewModel.Sobh);
        txtZohr.setText("اذان ظهر:"+owghatViewModel.Zohr);
        txtMaghreb.setText("اذان مغرب:"+owghatViewModel.Maghreb);
    }
}
