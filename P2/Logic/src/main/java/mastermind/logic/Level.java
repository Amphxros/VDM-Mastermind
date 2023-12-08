package mastermind.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import mastermind.engine.IScene;
import mastermind.logic.button.Button;


public class Level extends Button {
    int mCodeSize;
    int mCodeOpt;
    boolean mRepeat;
    int mAttempts;
    public Level(IScene scene, String fileJSON) {
        super(scene);



    }

    public String readFile(String fileJSON){
        String line = "";
        FileInputStream in;
        try {
            in = new FileInputStream(fileJSON);

            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();

            while (true) {
                try {
                    if (!((line = bufferedReader.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sb.append(line);
            }
            inputStreamReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return line;
    }
}
