package com.miller.criminalintent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by miller on 16/6/23.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;

    private static final String TAG = "CrimeLab";
    private static final String SAVE_FILE_NAME = "crimes.json";
    private CriminalIntentJSONSerializer mSerializer = null;


    private CrimeLab(Context appConText) {
        mAppContext = appConText;
        mSerializer = new CriminalIntentJSONSerializer(mAppContext, SAVE_FILE_NAME);

        try{
            mCrimes = mSerializer.loadCrime();
        }catch (Exception e) {
            mCrimes = new ArrayList<>();
            Log.e(TAG, "error loading crime" , e);
        }
    }

    public static CrimeLab get(Context c) {
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime c: mCrimes) {
            if(c.getID().equals(id)){
                return  c;
            }
        }
        return null;
    }

    public void addCrime(Crime c){
        mCrimes.add(c);
    }

    public boolean saveCrimes() {
        try {
            mSerializer.saveCrimes(mCrimes);
            return true;
        }
        catch (Exception e) {
            Log.d(TAG, "Err save crimes" + e.getMessage());
            return false;
        }
    }
}
