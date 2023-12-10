package mastermind.androidengine;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import mastermind.engine.IJSON;

public class AndroidJSON implements IJSON {

    JSONObject mJsonObject;
    private String infoString;
    public AndroidJSON(String fileJSON){
        FileInputStream in;
        try {
            in = new FileInputStream(fileJSON);

            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();

            while (true) {
                try {
                    if (!((this.infoString = bufferedReader.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sb.append(this.infoString);
            }
            inputStreamReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            this.mJsonObject = new JSONObject(this.infoString);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String getInfoJSON() {
        return this.infoString;
    }

    @Override
    public int getIntKey(String s) {
        try {
            return this.mJsonObject.getInt(s);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getStringKey(String s) {
        try {
            return this.mJsonObject.getString(s);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean getBoleanKey(String s) {
        try {
            return this.mJsonObject.getBoolean(s);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


}
