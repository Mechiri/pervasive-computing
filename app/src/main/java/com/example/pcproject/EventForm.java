package com.example.pcproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class EventForm extends AppCompatActivity implements
        mainEventFragment.mainEventFragmentListener,
        reflectionEventFragment.reflectionEventFragmentListener,
        dateEventFragment.dateEventFragmentListener,
        fightEventFragment.fightEventFragmentListener,
        otherEventFragment.otherEventFragmentListener
{
    private static final String TAG = "EVENT FORM";
    private mainEventFragment mainEvent;
    private reflectionEventFragment reflectionFragment;
    private dateEventFragment dateFragment;
    private fightEventFragment fightFragment;
    private otherEventFragment otherFragment;
    private eventAddedFragment addedFragment;

    private String itemSelection;
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
        Log.d(TAG, "onInputMainEventSent coming.....1");
        if(itemSelection.equals("Date")) {
            dateFragment = new dateEventFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.eventMainLayout, dateFragment)
                    .commit();
        }
        else if(itemSelection.equals("Fight")) {
            fightFragment = new fightEventFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.eventMainLayout, fightFragment)
                    .commit();
        }
        else if(itemSelection.equals("Other")) {
            otherFragment = new otherEventFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.eventMainLayout, otherFragment)
                    .commit();
        }
    }
    @Override
    public void onInputItemSelected(String itemSelected) {
        itemSelection = itemSelected;
    }
    @Override
    public void onInputReflectionEventSent(String convoTopics) {
        Log.d(TAG,"onReflectionEventSent coming.....3");
        addedFragment = new eventAddedFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.eventMainLayout, addedFragment)
                .commit();
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

    @Override
    public void onInputDateEventSent() {
        Log.d(TAG, "onInputDateEventSent coming....2");
        reflectionFragment = new reflectionEventFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.eventMainLayout, reflectionFragment)
                .commit();
    }

    @Override
    public void onInputFightEventSent() {
        Log.d(TAG, "onInputDateEventSent coming....2");
        reflectionFragment = new reflectionEventFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.eventMainLayout, reflectionFragment)
                .commit();
    }

    @Override
    public void onInputOtherEventSent() {
        Log.d(TAG, "onInputDateEventSent coming....2");
        reflectionFragment = new reflectionEventFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.eventMainLayout, reflectionFragment)
                .commit();
    }
}