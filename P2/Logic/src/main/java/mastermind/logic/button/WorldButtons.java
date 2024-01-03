package mastermind.logic.button;

import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.scene.ExploreWorldsScene;
public class WorldButtons extends Button{

        int id;
        public WorldButtons(IScene scene, int id) {
            super(scene);
            this.id=id;
        }

        @Override
        public boolean onTouchDown(TouchEvent event) {

            ExploreWorldsScene worldsScene= (ExploreWorldsScene) getScene();

            if(worldsScene!=null){
                worldsScene.onClick(id);
                return super.onTouchDown(event);
            }

            return super.onTouchDown(event);
        }

        @Override
        public void render(IGraphics graphics) {
            graphics.setColor(color);
            graphics.fillRoundRectangle(getX(),getY(),getWidth(), getHeight(), 20);
            super.render(graphics);
        }
}
