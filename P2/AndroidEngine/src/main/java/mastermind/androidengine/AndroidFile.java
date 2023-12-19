package mastermind.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import mastermind.engine.IFile;

public class AndroidFile implements IFile {

    private final AssetManager assetManager;
    File mFile;
    String infoFileString;

    public AndroidFile(Context context) {
        this.assetManager = context.getAssets();;
    }

    void AndroidFile(String path) throws Exception {
        InputStream in = assetManager.open(path);
        this.mFile = new File(path);

        this.infoFileString = AndroidFileToString(mFile);
    }

    private String AndroidFileToString(File file) throws Exception {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        try {
            fin.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    private String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String readFileAsString(String filePath) {
        String result = "";
        File file = new File(filePath);
        if ( file.exists() ) {
            //byte[] buffer = new byte[(int) new File(filePath).length()];
            FileInputStream fis = null;
            try {
                //f = new BufferedInputStream(new FileInputStream(filePath));
                //f.read(buffer);

                fis = new FileInputStream(file);
                char current;
                while (fis.available() > 0) {
                    current = (char) fis.read();
                    result = result + String.valueOf(current);
                }
            } catch (Exception e) {
                Log.d("TourGuide", e.toString());
            } finally {
                if (fis != null)
                    try {
                        fis.close();
                    } catch (IOException ignored) {
                    }
            }
            //result = new String(buffer);
        }
        return result;
    }
    public File getFile(){
        return  this.mFile;
    }

    public String getInfoFileString(){
        return this.infoFileString;
    }
    @Override
    public boolean isDirectory() {
        return this.mFile.isDirectory();
    }

    @Override
    public String[] list() {
        return this.mFile.list();
    }
}
