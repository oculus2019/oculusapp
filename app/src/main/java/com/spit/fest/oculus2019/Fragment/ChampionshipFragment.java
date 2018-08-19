package com.spit.fest.oculus2019.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spit.fest.oculus2019.HelperClass.Event;
import com.spit.fest.oculus2019.HelperClass.EventAdapter;
import com.spit.fest.oculus2019.R;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChampionshipFragment extends Fragment {

    private EventAdapter eventAdapter;

    public ChampionshipFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_championship, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        eventAdapter=new EventAdapter(getApplicationContext());
        populateEventList();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_championship);
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void populateEventList()
    {
        ArrayList<Event> list=new ArrayList<>();
        list.add(new Event("Virtual Stock Market"));
        list.add(new Event("TechRace"));
        list.add(new Event("Codatron"));
        list.add(new Event("RoboWars"));
        list.add(new Event("Hacking Bad"));
        eventAdapter.addEventList(list);
    }
}
