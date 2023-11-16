package vdm.mastermind.logic.scenes;

import java.util.ArrayList;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.engine.interfaces.objects.ISound;
import vdm.mastermind.logic.DaltonicListener;
import vdm.mastermind.logic.Password;
import vdm.mastermind.logic.buttons.DaltonicButton;
import vdm.mastermind.logic.gameobjects.ColorTable;
import vdm.mastermind.logic.gameobjects.GameObject;
import vdm.mastermind.logic.gameobjects.Table;

public class GameScene extends Scene{


    public GameScene(IEngine engine, int colors, int intentos, int tamPassWord) {
        super(engine);
    }

    @Override
    public void init() {
        super.init();
    }
}

