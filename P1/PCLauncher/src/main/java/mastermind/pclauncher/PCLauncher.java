package mastermind.pclauncher;

import mastermind.logic.Logic;
import mastermind.pcengine.PCEngine;

public class PCLauncher {
    public static void main(String[] args) {
        PCEngine engine = new PCEngine();
        engine.getGraphics().setResolution(400, 600);
        Logic logic = new Logic(engine);
        engine.setLogic(logic);
        engine.run();
    }
}