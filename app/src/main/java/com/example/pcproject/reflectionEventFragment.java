package com.example.pcproject;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class reflectionEventFragment extends Fragment {
    private static final String TAG = "reflectionEventFragment";
    private reflectionEventFragmentListener reflectionEventFragmentListener;
    private Button finishEventB;
    private EditText reflectionConvoTopics;
    private EditText reflectionActionsLiked;
    private EditText reflectionActionsNotLiked;
    private EditText reflectionNotableEvents;
    public reflectionEventFragment() {
        // Required empty public constructor
    }
    public interface reflectionEventFragmentListener
    {
        void onInputReflectionEventSent(String convoTopics);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"reflectionEventFragment onCreateView coming.....1");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reflection_event, container, false);
        finishEventB = v.findViewById(R.id.finishEventB);
        reflectionConvoTopics = v.findViewById(R.id.reflectionConvoTopics);
        reflectionActionsLiked = v.findViewById(R.id.reflectionActionsLiked);
        reflectionActionsNotLiked = v.findViewById(R.id.reflectionActionsNotLiked);
        reflectionNotableEvents = v.findViewById(R.id.reflectionNotableEvents);
        finishEventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reflectionEventFragmentListener.onInputReflectionEventSent(reflectionConvoTopics.getText().toString());
            }
        });
        return v;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof reflectionEventFragment.reflectionEventFragmentListener) {
            reflectionEventFragmentListener = (reflectionEventFragment.reflectionEventFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "The activity must implement reflectionEventFragmentListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}