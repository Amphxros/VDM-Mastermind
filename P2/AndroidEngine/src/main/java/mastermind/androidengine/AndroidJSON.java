package mastermind.androidengine;

import org.json.JSONException;
import org.json.JSONObject;

import mastermind.engine.IJSON;

public class AndroidJSON implements IJSON {

    JSONObject mJsonObject;
    private String infoString;
    public AndroidJSON(AndroidFile fileJSON){

        this.infoString = fileJSON.getInfoFileString();

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
