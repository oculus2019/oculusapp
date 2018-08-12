package com.spit.fest.oculus2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>
{
    private Context context;
    private ArrayList<Event> eventList;
    private LayoutInflater layoutInflater;

    public EventAdapter(Context context)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        eventList=new ArrayList<>();
    }

    public void addEventList(ArrayList<Event> events)
    {
        eventList.clear();
        eventList.addAll(events);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_photo,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.bindData(event);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder
    {

        private View mView;
        private TextView eventName;
        private CheckableImageView imageLike;
        private TextView textLike;
        private ImageView moreOptions;

        EventViewHolder(View view) {
            super(view);
            mView=view;
            eventName=mView.findViewById(R.id.txtTitle);
            eventName.setSelected(true);
            imageLike=mView.findViewById(R.id.img_like);
            textLike=mView.findViewById(R.id.txt_like);
            moreOptions=mView.findViewById(R.id.moreOptions);
        }

        void bindData(Event event)
        {
            imageLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!imageLike.isChecked())
                    {
                        Log.e("EventHolder",""+imageLike.isChecked());
                        imageLike.setImageResource(R.drawable.ic_thumb_up_primary_24dp);
                        textLike.setTextColor(context.getResources().getColor(R.color.likeText));
                        imageLike.setChecked(true);
                    }
                    else
                    {
                        Log.e("EventHolder",""+imageLike.isChecked());
                        imageLike.setImageResource(R.drawable.ic_thumb_up_black_24dp);
                        textLike.setTextColor(context.getResources().getColor(R.color.unlikeTextColor));
                        imageLike.setChecked(false);
                    }
                }
            });
            moreOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu=new PopupMenu(context,moreOptions);
                    popupMenu.inflate(R.menu.item_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {

                            int id=menuItem.getItemId();
                            switch (id)
                            {
                                case R.id.action_share:
                                    Log.e("EventAdapter: ",String.valueOf("Share ")+getAdapterPosition());
                                    break;
                                case R.id.action_wishlist:
                                    Log.e("EventAdapter: ",String.valueOf("Add to wishlist ")+getAdapterPosition());
                                    break;

                            }

                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });
            eventName.setText(event.getTitle());
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
