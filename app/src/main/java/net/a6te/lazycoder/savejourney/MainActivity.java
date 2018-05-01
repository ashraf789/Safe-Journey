package net.a6te.lazycoder.savejourney;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.a6te.lazycoder.savejourney.fragment.FragmentEmergency;
import net.a6te.lazycoder.savejourney.fragment.FragmentFeedback;
import net.a6te.lazycoder.savejourney.fragment.FragmentMedicalHelp;
import net.a6te.lazycoder.savejourney.fragment.FragmentTicket;
import net.a6te.lazycoder.savejourney.fragment.FragmentTravelTourism;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

    private DataSaveToSp statusSave;
    private Fragment fragment;
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    private RelativeLayout navEmergencyRl, navMedicalHelpRl, navTravelTourismRl, navTicketRl, navFeedbackRl, safeJourneyUrlRl;
    private ImageView logOutBtnIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);


        toggle.syncState();
        initializeAll();//initialize all variable
        transaction.add(R.id.container,fragment);
        transaction.commit();

        logOutBtnIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusSave.saveLoginStatus(false);
                finish();
            }
        });
    }

    private void initializeAll() {
        fragment = new FragmentEmergency();
        navTravelTourismRl = (RelativeLayout) findViewById(R.id.navTravelAndTourRl);
        navTicketRl = (RelativeLayout) findViewById(R.id.navTicketRl);
        navMedicalHelpRl = (RelativeLayout) findViewById(R.id.navMedicalHelpRl);
        navEmergencyRl = (RelativeLayout) findViewById(R.id.navEmergencyRl);
        navFeedbackRl = (RelativeLayout) findViewById(R.id.navFeedbackRl);
        safeJourneyUrlRl = (RelativeLayout) findViewById(R.id.safeJourneyUrlRl);


        navTravelTourismRl.setOnClickListener(this);
        navTicketRl.setOnClickListener(this);
        navMedicalHelpRl.setOnClickListener(this);
        navEmergencyRl.setOnClickListener(this);
        navFeedbackRl.setOnClickListener(this);
        safeJourneyUrlRl.setOnClickListener(this);

        logOutBtnIv = (ImageView) findViewById(R.id.logoutBtn);
        statusSave = new DataSaveToSp(this);

    }





    //navbar item click listener
    @Override
    public void onClick(View v) {
        transaction = getSupportFragmentManager().beginTransaction();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch (v.getId()){

            case R.id.navEmergencyRl:

                fragment = new FragmentEmergency();
                transaction.replace(R.id.container,fragment);

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;

            case R.id.navMedicalHelpRl:

                fragment = new FragmentMedicalHelp();
                transaction.replace(R.id.container,fragment);

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;
            case R.id.navTravelAndTourRl:

                fragment = new FragmentTravelTourism();
                transaction.replace(R.id.container,fragment);

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;
            case R.id.navTicketRl:

                fragment = new FragmentTicket();
                transaction.replace(R.id.container,fragment);

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;

            case R.id.navFeedbackRl:

                fragment = new FragmentFeedback();
                transaction.replace(R.id.container,fragment);

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;
            case R.id.safeJourneyUrlRl:
                // TODO: 18/5/17 need to add website url
                break;
        }
        transaction.commit();
    }

    public void showToast(String message){
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        statusSave.saveLoginStatus(true);
        moveTaskToBack(true);
    }

}
