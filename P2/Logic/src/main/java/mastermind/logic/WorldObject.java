package mastermind.logic;

import mastermind.engine.HorizontalAlignment;
import mastermind.engine.IFont;
import mastermind.engine.IScene;
import mastermind.logic.button.Button;

public class WorldObject extends Button {
    int mNumLevels;
    Text mTitle;
    public WorldObject(IScene scene, int numLevels, String title, IFont font) {
        super(scene);
        mNumLevels = numLevels;
        mTitle = new Text(scene, "partida rapida",font);
        mTitle.setPosition(0,0); // LOCALIZAR BIEN
        mTitle.setAlignment(HorizontalAlignment.CENTRE);

        addChild(mTitle);
        mTitle.setPosition(0,25);

        //CARGA DE NIVELES CON EL TITULO DEL MUNDO

    }


}
