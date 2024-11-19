package com.example.gps_employee_tracking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gps_employee_tracking.Adapters.CustomAdapter;
import com.example.gps_employee_tracking.home_fragmets.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class sites_and_work extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites_and_work);

        Button button = findViewById(R.id.save_btn_sites);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_sites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CustomAdapter adapter = new CustomAdapter(this, generateData(),generatePN(),generateservices(),generateLocation_cord());
        recyclerView.setAdapter(adapter);

        // back to home
        ImageButton back_hm = findViewById(R.id.back_home);


        back_hm.setOnClickListener(v -> {
            Intent intent = new Intent(sites_and_work.this, Home_Screen.class);
            startActivity(intent);
            finish();
        });

        // btn to save
        button.setOnClickListener(v -> {
           Toast.makeText(this,"got button",Toast.LENGTH_SHORT).show();
        });

    }

    private List<String> generateData() {
        List<String> data = new ArrayList<>();
        data.add("Vit Chennai");
        data.add("Ambattur OT");
        data.add("Office");
        return data;
    }
    private List<String> generatePN() {
        List<String> data_PN = new ArrayList<>();
        data_PN.add("1111111111");
        data_PN.add("2222222222");
        data_PN.add("3333333333");
        return data_PN;
    }
    private List<String> generateservices() {
        List<String> data_PN = new ArrayList<>();
        data_PN.add("Plumbing");
        data_PN.add("Electrical");
        data_PN.add("Plumbing");
        return data_PN;
    }
    private List<String> generateLocation_cord() {
        List<String> data_Loc = new ArrayList<>();
        data_Loc.add("12.847503013144635, 80.16166803600242"); // add a location coordinate as a string
        data_Loc.add("13.119867332446761, 80.150092720911");
        data_Loc.add("13.093833, 80.288987");
        // add more location coordinates as per your requirement
        return data_Loc;
    }


}
