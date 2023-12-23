package mastermind.logic;

import mastermind.engine.IImage;
import mastermind.engine.IScene;

public class Grid extends GameObject{
    int rows, cols;
    public Grid(IScene scene, int rows, int cols) {
        super(scene);
        this.rows=rows;
        this.cols=cols;
    }

    @Override
    public void init() {
        for(int i=0; i< getChildren().size(); i++){

        }
        super.init();
    }
}
