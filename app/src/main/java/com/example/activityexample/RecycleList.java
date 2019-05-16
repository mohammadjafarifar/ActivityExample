package com.example.activityexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleList extends AppCompatActivity {

    RecyclerView recycleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_list);
        ArrayList<PersonalViewModel> personalViewModels=new ArrayList<>();



        personalViewModels.add(new PersonalViewModel("Mohammad","jafarifar",0,"Sematec","test"));
        personalViewModels.add(new PersonalViewModel("Majid","Bagheri",1,"Sematec","test"));
        personalViewModels.add(new PersonalViewModel("Zakaria","Tajik",2,"Sematec","test"));
        personalViewModels.add(new PersonalViewModel("Farshad","Danaee",3,"Sematec","test"));
        personalViewModels.add(new PersonalViewModel("MohammadReza","Farahani",4,"Sematec","test"));
        personalViewModels.add(new PersonalViewModel("MohammadReza","Monsef",5,"Sematec","test"));
        personalViewModels.add(new PersonalViewModel("Mehrshad","Mollaafzal",6,"Sematec","test"));
        personalViewModels.add(new PersonalViewModel("Reza","Sadeghi",7,"Sematec","test"));
        personalViewModels.add(new PersonalViewModel("Mohsen","KarimiNezhad",8,"Sematec","test"));



        recycleList=findViewById(R.id.recycleList);

        AdapterRecycle adapterRecycle=new AdapterRecycle(personalViewModels);


        recycleList.setAdapter(adapterRecycle);

        recycleList.setLayoutManager(new LinearLayoutManager(RecycleList.this, RecyclerView.VERTICAL, false));
    }
}
