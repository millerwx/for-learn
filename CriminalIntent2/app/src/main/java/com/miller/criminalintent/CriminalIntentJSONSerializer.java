package com.miller.criminalintent;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by miller on 16/7/8.
 */
public class CriminalIntentJSONSerializer {
    private Context mContext;
    private String mFileName;
    private String TAG = "CriminalIntentJSONSerializer";

    public CriminalIntentJSONSerializer(Context mContext, String mFileName) {
        this.mContext = mContext;
        this.mFileName = mFileName;
    }

    public ArrayList<Crime> loadCrime() throws IOException, JSONException {
        ArrayList<Crime> crimes = new ArrayList<Crime>();
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // parse the JSON
            JSONArray array = (JSONArray)new JSONTokener(jsonString.toString()).nextValue();

            for(int i=0; i< array.length();i++) {
                crimes.add(new Crime(array.getJSONObject(i)));
            }
        }
        catch (FileNotFoundException e) {

        }
        finally {
            if(reader != null) {
                reader.close();
            }

        }
        return crimes;
    }


    public void saveCrimes(ArrayList<Crime> crimes){
        try{
            JSONArray array = new JSONArray();
            for(Crime c : crimes){
                array.put(c.toJson());
            }

            Writer writer = null;
            OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
            writer.close();
        }
        catch (Exception e){
            Log.e("TAG", "Err:" + e.getMessage());
        }
    }
}
