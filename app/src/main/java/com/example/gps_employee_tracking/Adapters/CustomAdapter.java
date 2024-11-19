package com.example.gps_employee_tracking.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gps_employee_tracking.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private static List<String> items;
    private static List<String> site_Phone_Numbers;
    private static List<String> site_services;
    private static List<String> site_cords;

    public CustomAdapter(Context context, List<String> items ,List<String> site_Phone_Numbers ,List<String> site_services , List<String> site_cords) {
        CustomAdapter.items = items;
        CustomAdapter.site_Phone_Numbers = site_Phone_Numbers;
        CustomAdapter.site_services = site_services;
        CustomAdapter.site_cords = site_cords;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sites_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemTextView.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        public TextView itemTextView;
        public ImageButton imageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemTextView_sites);
            imageButton = itemView.findViewById(R.id.item_opt);

            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("catch","onclick: "+getAdapterPosition());
            overlay_menu(v);

        }

        private void overlay_menu(View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
            popupMenu.inflate(R.menu.recycler_overlay_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.overlay_info:
                    String siteName = items.get(getAdapterPosition());
                    String sitePhoneNumber = site_Phone_Numbers.get(getAdapterPosition());
                    String siteService = site_services.get(getAdapterPosition());

                    // Display the details in a dialog or a new activity
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setTitle(siteName);
                    builder.setMessage("Phone Number: " + sitePhoneNumber + "\nService: " + siteService);
                    builder.setPositiveButton("OK", null);
                    builder.show();
                    return true;

                case R.id.overlay_direction:
                    Log.d("onMenuItem","info available at direction "+getAdapterPosition());
                    // Handle direction click for search string
//                    String location = items.get(getAdapterPosition()); // Location to be passed to Google Maps
//                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + location);
//                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                    mapIntent.setPackage("com.google.android.apps.maps");
//                    if (mapIntent.resolveActivity(itemView.getContext().getPackageManager()) != null) {
//                        itemView.getContext().startActivity(mapIntent);
//                    }
//                    else {
//                        Toast.makeText(itemView.getContext(), "Google Maps app not found!", Toast.LENGTH_SHORT).show();
//                    }
                    String cords = site_cords.get(getAdapterPosition()); // get the coordinates for the current site
                    String[] parts = cords.split(","); // split the coordinates into latitude and longitude
                    double latitude = Double.parseDouble(parts[0]);
                    double longitude = Double.parseDouble(parts[1]);
                    Log.d("Location_tag","Longitude "+longitude+" lattitude:"+latitude);
                    // create a Uri for the Google Maps intent with the extracted coordinates
                    Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude);
                    // create an Intent to open the Google Maps app
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    // check if the Google Maps app is installed and start the activity
                    if (mapIntent.resolveActivity(itemView.getContext().getPackageManager()) != null) {
                        itemView.getContext().startActivity(mapIntent);
                    }
                    else {
                        Toast.makeText(itemView.getContext(), "Google Maps app not found!", Toast.LENGTH_SHORT).show();
                    }
                    return true;

                }
            return false;
        }
    }
}

