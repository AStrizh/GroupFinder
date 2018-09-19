package com.erudos.groupfinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder>  {


    //private YelpSearchResponse restaurants;
    private ArrayList<Event> events;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView eventNameText;
        private TextView eventDescriptionText;
        private TextView eventDateTime;
        private TextView eventBusinessName;
        private ImageView restaurantPicture;
        private LinearLayout itemLayout;

        ViewHolder(View view) {
            super(view);
            eventNameText = view.findViewById(R.id.eventNameTextView);
            eventDescriptionText = view.findViewById(R.id.eventShortDescriptionTextView);
            eventDateTime = view.findViewById(R.id.eventDateTimeTextView);
            eventBusinessName = view.findViewById(R.id.eventLocationNameTextView);
            restaurantPicture = view.findViewById(R.id.restaurantImageView);
            itemLayout = view.findViewById(R.id.itemLayout);
        }

        void bindRestaurant(final Event event) {

            eventNameText.setText(event.getEventName());
            eventDescriptionText.setText(event.getDescription());
            eventDateTime.setText(event.getTime());
            eventBusinessName.setText(event.getBusinessLocation().getName());

            if (event.getBusinessLocation().getImageUrl().trim().length() == 0){
                Picasso.with(itemView.getContext()).load(R.drawable.default_image).into(restaurantPicture);
            }
            else{
                Picasso.with(itemView.getContext()).load(event.getBusinessLocation().getImageUrl()).into(restaurantPicture);
            }

        }
    }

    //TODO:Make this accept an array of events
    EventListAdapter(ArrayList<Event> events) {
        this.events = events;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.event_items;
        LayoutInflater inflater = LayoutInflater.from(context);

        // create a new view
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new EventListAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EventListAdapter.ViewHolder holder, int position) {
        holder.bindRestaurant(events.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return events.size();
    }

}
