package mastermind.androidengine;

import android.content.Context;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import mastermind.engine.IFileManager;
import mastermind.engine.IJsonObject;

public class AndroidFileManager implements IFileManager {
    private Context context;
    public AndroidFileManager(Context context){
        this.context=context;
    }
    @Override
    public InputStream openInputFile(String path) throws FileNotFoundException, IOException {
        return context.openFileInput(path);
    }

    @Override
    public OutputStream openOutputFile(String path) {
        try {
            return context.openFileOutput(path, Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String readFile(String path) {
        StringBuilder result = new StringBuilder();

        try (InputStreamReader is = new InputStreamReader(context.getAssets().open(path))) {
            BufferedReader buffer = new BufferedReader(is);
            do {
                result.append(buffer.readLine());
                result.append('\n');
            } while (buffer.ready());

            is.close();
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    @Override
    public IJsonObject readJSON(String path) {
        try {
            return new AndroidJSONObject(readFile(path));
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
