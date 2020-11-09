package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class EventForm extends AppCompatActivity implements
        mainEventFragment.mainEventFragmentListener,
        reflectionEventFragment.reflectionEventFragmentListener
{
    private static final String TAG = "EVENT FORM";

    private mainEventFragment mainEvent;
    private reflectionEventFragment reflectionFragment;

    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);
        mainEvent = new mainEventFragment();
        fragmentManager = getSupportFragmentManager();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d(TAG, "Fragment count in back stack " + fragmentManager.getBackStackEntryCount());
            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.eventMainLayout, mainEvent).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onInputMainEventSent() {
        Log.d(TAG,"onInputPage1Sent coming.....1");
        reflectionFragment = new reflectionEventFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainLayout, reflectionFragment)
                .commit();
    }

    @Override
    public void onInputReflectionEventSent(String convoTopics) {
        Log.d(TAG,"onInputPage3Sent coming.....1");
        /*if(!convoTopics.isEmpty())
        {
            appUser.setIdealRelationshipFeel(convoTopics);
            Log.d(TAG,"Reflection Convo Topics" + appUser.getIdealRelationshipFeel());
            eventAdded = new eventAddedfragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainLayout, eventAdded)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Please fill above option", Toast.LENGTH_SHORT).show();
        }*/
    }
}