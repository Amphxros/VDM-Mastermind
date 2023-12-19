package mastermind.logic;

import mastermind.engine.HorizontalAlignment;
import mastermind.engine.IFont;
import mastermind.engine.IScene;
import mastermind.logic.button.Button;

public class WorldObject extends Button {
    int mNumLevels;
    Text mTitle;
    public WorldObject(IScene scene, int numLevels, int numWorld, IFont font) {
        super(scene);
        mNumLevels = numLevels;
        mTitle = new Text(scene, "partida rapida",font);
        mTitle.setPosition(0,0); // LOCALIZAR BIEN
        mTitle.setAlignment(HorizontalAlignment.CENTRE);

        addChild(mTitle);
        mTitle.setPosition(0,25);

        Level arrayLevels[];
        arrayLevels = new Level[mNumLevels];

        //CARGA DE NIVELES CON EL TITULO DEL MUNDO
        for(int i = 0; i < mNumLevels; ++i){
            String s = String.format("%02d", (i + 1));
            arrayLevels[i] = new Level(scene, "levels/world" + numWorld + "/level_" + numWorld + s + ".json");
        }
    }

    public void init(){

    }


}
