package com.spit.fest.oculus2019.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spit.fest.oculus2019.HelperClass.TabLayoutAdapter;
import com.spit.fest.oculus2019.R;

import java.util.Objects;

public class HomeFragment extends Fragment {

//    private EventAdapter eventAdapter;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(getApplicationContext());
//        mAuth=FirebaseAuth.getInstance();
//        mAuthListener=new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                updateUI(firebaseAuth.getCurrentUser());
//            }
//        };
//        LoginButton signOut = view.findViewById(R.id.sign_out_button);
//        signOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LoginManager.getInstance().logOut();
//                FirebaseAuth.getInstance().signOut();
//            }
//        });
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        tabLayoutAdapter.addFragment(new ChampionshipFragment(),"Championships");
        tabLayoutAdapter.addFragment(new TalkAndExhibitFragment(),"Talks and Exhibitions");
        tabLayoutAdapter.addFragment(new FeaturedFragment(),"Featured");
        tabLayoutAdapter.addFragment(new FunFragment(),"Fun");
        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        eventAdapter=new EventAdapter(getApplicationContext());
//        populateEventList();
//        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
//        recyclerView.setAdapter(eventAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

//    private void updateUI(FirebaseUser firebaseUser)
//    {
//        if (firebaseUser==null)
//        {
//            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            finish();
//        }
//    }

//    private void populateEventList()
//    {
//        ArrayList<Event> list=new ArrayList<>();
//        list.add(new Event("Virtual Stock Market"));
//        list.add(new Event("TechRace"));
//        list.add(new Event("Codatron"));
//        list.add(new Event("RoboWars"));
//        list.add(new Event("Hacking Bad"));
//        eventAdapter.addEventList(list);
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
}