package com.example.pcproject;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class EvaluateRelationship {

    private static final String TAG = "EvaluateRelationship";
    //Attributes - Love Languages Labels
    final private Integer wordsOfAffirmation;
    final private Integer qualityTime;
    final private Integer receivingGifts;
    final private Integer actsOfService;
    final private Integer physicalTouch;

    public EvaluateRelationship() {
        this.wordsOfAffirmation = 7;
        this.qualityTime = 6;
        this.receivingGifts = 5;
        this.actsOfService = 5;
        this.physicalTouch = 7;
    }

    public Map<String, Integer> calculateLoveLanguagesRatio(Integer wordsOfAffirmation, Integer qualityTime, Integer receivingGifts, Integer actsOfService, Integer physicalTouch)
    {
        float waRatio = (wordsOfAffirmation/15.0f);
        float qtRatio = (qualityTime/15.0f);
        float rgRatio = (receivingGifts/15.0f);
        float asRatio = (actsOfService/15.0f);
        float ptRatio = (physicalTouch/15.0f);

        Log.d(TAG, "Love Languages floating:" + "WA:" + waRatio +" QT:"
                +qtRatio + " RG:"
                +rgRatio + " AS:"
                +asRatio + " PT:"
                +ptRatio + " End");



        Integer WaRatio = (int)Math.round(waRatio);
        Integer QtRatio = (int)Math.round(qtRatio);
        Integer RgRatio = (int)Math.round(rgRatio);
        Integer AsRatio = (int)Math.round(asRatio);
        Integer PtRatio = (int)Math.round(ptRatio);

        Log.d(TAG, "Love Languages overall:" + "WA:" + ((float)(100.0f* (waRatio/(float)(20.0f)))) +" QT:"
                +((Float)(100* (waRatio/(Float)(25.0f)))).intValue() + " RG:"
                +RgRatio + " AS:"
                +AsRatio + " PT:"
                +PtRatio + " End");

        Map<String,Integer> result = new HashMap<>();;
        result.put("wordsOfAffirmation", WaRatio);
        result.put("qualityTime", QtRatio);
        result.put("receivingGifts", RgRatio);
        result.put("actsOfService", AsRatio);
        result.put("physicalTouch", PtRatio);

        return result;
    }
}
