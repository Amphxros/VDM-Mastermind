package mastermind.androidengine;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import mastermind.engine.IFile;
import mastermind.engine.IJSON;

public class AndroidJSON implements IJSON, IFile {

    JSONObject mJsonObject;
    private String infoString;
    File file;
    public AndroidJSON(File file, String info){
        this.file= file;
        this.infoString = info;

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


    @Override
    public boolean isDirectory() {
        return this.file.isDirectory();
    }

    @Override
    public String[] list() {
        return this.file.list();
    }


}
