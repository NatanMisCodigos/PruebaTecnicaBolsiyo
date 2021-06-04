package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.R;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.Location;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.view.WeatherActivityView;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.UserViewHolder> {

    private List<Location> usersList;
    private static Context context;

    public LocationAdapter(List<Location> listLocations, Context context) {
        this.usersList = listLocations;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.location_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bindData(usersList.get(position));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView locationType;
        CardView cardView;
        Location location;

        public UserViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            locationType = itemView.findViewById(R.id.typeLocation);
            cardView = itemView.findViewById(R.id.card_view_location);
        }

        void bindData(final Location location) {
            this.location = location;
            title.setText(location.getTitle());
            locationType.setText(location.getLocation_type());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    weatherIntenet(location);
                }
            });
        }

        void weatherIntenet(Location location){
            Intent weather = new Intent(context, WeatherActivityView.class);
            weather.putExtra("location", location);
            context.startActivity(weather);
        }
    }
}
