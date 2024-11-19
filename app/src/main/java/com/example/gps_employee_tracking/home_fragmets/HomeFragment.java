package com.example.gps_employee_tracking.home_fragmets;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gps_employee_tracking.Adapters.CustomAdapter;
import com.example.gps_employee_tracking.R;
import com.example.gps_employee_tracking.sites_and_work;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton event_and_sites;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Spinner spinner_sites = view.findViewById(R.id.site_spinner);

        List<String> site_items = new ArrayList<>();
        site_items.add("Vit Chennai");
        site_items.add("Ambattur OT");
        site_items.add("Office");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                        android.R.layout.simple_spinner_item, site_items);

        // Set the dropdown layout style for the adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        // Set the adapter to the spinner
        spinner_sites.setAdapter(adapter);

        // Task
        //-----------------------------------------
        Spinner spinner_task = view.findViewById(R.id.task_spinner);
        List<String> task_items = new ArrayList<>();
        task_items.add("Electrical");
        task_items.add("Plumbing");
        task_items.add("Cleaner");

        // Create an adapter for the spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, task_items);

        // Set the dropdown layout style for the adapter
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the spinner
        spinner_task.setAdapter(adapter1);

        // image button for events activity
        event_and_sites = view.findViewById(R.id.sites_img_btn);

        event_and_sites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new Intent object to start the new activity
                Intent intent = new Intent(getActivity(), sites_and_work.class);
                startActivity(intent);

                // Display a toast message with haptic feedback
                Toast.makeText(getActivity(), "Loading sites available", Toast.LENGTH_SHORT).show();

                Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.DEFAULT_AMPLITUDE));
                }
            }
        });

        return view;


    }

}