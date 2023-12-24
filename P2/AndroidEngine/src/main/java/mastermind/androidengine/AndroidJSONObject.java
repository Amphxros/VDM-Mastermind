package mastermind.androidengine;

import org.json.JSONException;
import org.json.JSONObject;

import mastermind.engine.IJsonObject;

public class AndroidJSONObject implements IJsonObject {

    String infoJSON;
    JSONObject jsonObject;

    public AndroidJSONObject(String infoJSON) throws JSONException {
        this.infoJSON=infoJSON;
        jsonObject= new JSONObject(infoJSON);
    }
    @Override
    public String getInfoJSON() {
        return infoJSON;
    }

    @Override
    public int getIntKey(String s) {
        try {
            return jsonObject.getInt(s);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public String getStringKey(String s) {
        try {
            return jsonObject.getString(s);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean getBooleanKey(String s) {
        try {
            return jsonObject.getBoolean(s);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
