package vdm.mastermind.androidengine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import vdm.mastermind.engine.interfaces.IFileManager;

public class AndroidFileManager implements IFileManager {
    public AndroidFileManager(){

    }
    @Override
    public InputStream openInputFile(String path) throws FileNotFoundException, IOException {
        return null;
    }

    @Override
    public OutputStream openOutputFile(String path) {
        return null;
    }

    @Override
    public String readFile(String path) {
        return null;
    }
}
