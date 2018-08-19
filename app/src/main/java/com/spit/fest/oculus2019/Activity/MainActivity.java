package com.spit.fest.oculus2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.spit.fest.oculus2019.Fragment.AboutUsFragment;
import com.spit.fest.oculus2019.Fragment.CommitteeFragment;
import com.spit.fest.oculus2019.Fragment.ContactUsFragment;
import com.spit.fest.oculus2019.Fragment.DevelopersFragment;
import com.spit.fest.oculus2019.Fragment.GalleryFragment;
import com.spit.fest.oculus2019.Fragment.HomeFragment;
import com.spit.fest.oculus2019.Fragment.MyEventFragment;
import com.spit.fest.oculus2019.Fragment.MyRegistrationFragment;
import com.spit.fest.oculus2019.Fragment.SponsorsFragment;
import com.spit.fest.oculus2019.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =  findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()==null)
                {
                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        Profile profile= Profile.getCurrentProfile();
        if (profile!=null)
        {
            TextView name=header.findViewById(R.id.user_profile_name);
            name.setText(profile.getFirstName()+" "+profile.getLastName());
            TextView emailId=header.findViewById(R.id.user_email_id);
            emailId.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//            Picasso.get().load(profile.getProfilePictureUri(72,72)).into((ImageView)(header.findViewById(R.id.user_profile_photo)));
            Glide.with(this)
                    .load(profile.getProfilePictureUri(72,72))
                    .apply(RequestOptions.circleCropTransform())
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher_round))
                    .transition(withCrossFade())
                    .into((ImageView)(header.findViewById(R.id.user_profile_photo)));
        }

        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,new HomeFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(HomeFragment.class.getSimpleName())
                .commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment=null;

        switch(id){
            case R.id.nav_home:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
                fragment=new HomeFragment();
                break;
            case R.id.nav_my_event:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                fragment=new MyEventFragment();
                break;
            case R.id.nav_my_registration:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                fragment=new MyRegistrationFragment();
                break;
            case R.id.nav_sponsors:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                fragment=new SponsorsFragment();
                break;
            case R.id.nav_gallery:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                fragment=new GalleryFragment();
                break;
            case R.id.nav_contact_us:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                fragment=new ContactUsFragment();
                break;
            case R.id.nav_committee:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                fragment=new CommitteeFragment();
                break;
            case R.id.nav_developers:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                fragment=new DevelopersFragment();
                break;
            case R.id.nav_about_us:
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                fragment=new AboutUsFragment();
                break;
            case R.id.nav_sign_out:
                LoginManager.getInstance().logOut();
                mAuth.signOut();
                break;
        }

        if (fragment!=null)
        {
            fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}