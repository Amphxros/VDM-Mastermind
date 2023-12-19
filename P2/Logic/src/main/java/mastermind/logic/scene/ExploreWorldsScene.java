package mastermind.logic.scene;

import java.io.File;
import java.util.Arrays;

import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.WorldObject;

public class ExploreWorldsScene extends Scene {

    private WorldObject mWorldsObject [];

    public ExploreWorldsScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {

        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",20,false);

        int maxWidth = getEngine().getGraphics().getWidth();
        int centerX = maxWidth / 2;

        addGameObject(new Text(this,"Explorando mundos",font)
                .setPosition(centerX, 20));


        File directorio = new File("levels");
        if(directorio.exists()){
            if(directorio.isDirectory()){
                String[] lista = directorio.list();
                Arrays.sort(lista);
                for (int i = 0; i < lista.length; i++) {
                    System.out.println(lista[i]);
                }
            }
        }

    }
}
