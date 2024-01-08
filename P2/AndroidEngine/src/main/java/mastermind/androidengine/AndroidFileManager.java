package mastermind.androidengine;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import mastermind.engine.IFileManager;
import mastermind.engine.IJsonObject;

/**
 * Gestor de archivos de android
 */
public class AndroidFileManager implements IFileManager {
    private Context context;
    public AndroidFileManager(Context context){
        this.context=context;
    }

    /**
     *Metodo que llama a una funcion de c++ con el algoritmo picosha
     *
     */
    static{ System.loadLibrary("native-sha-lib"); }
    public static native String Hash(String data);
    @Override
    public String generateSHA(String data) {
        return Hash(data);
    }


    /**
     * Abre un archivo para la lectura
     * @param path ruta del archivo.
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public InputStream openInputFile(String path) throws FileNotFoundException, IOException {
        return context.openFileInput(path);
    }


    /**
     * Abre un archivo para su escritura
     * @param path The path to create the output stream for.
     * @return
     */
    @Override
    public OutputStream openOutputFile(String path) {
        try {
            return context.openFileOutput(path, Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lee el contenido del archivo
     * @param path The path of the file to read.
     * @return el contenido del archivo
     */
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

    /**
     * Crea un objeto Json
     * @param path
     * @return una instancia de {@link AndroidJSONObject} de la ruta path
     */
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

    /**
     *
     * @param path
     * @return la lista con el contenido de los dir de la carpeta
     */
    @Override
    public String [] getFileListDirectory(String path) {
        try {
            return context.getAssets().list(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
