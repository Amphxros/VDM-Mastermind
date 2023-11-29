package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.logic.Scene;
import mastermind.logic.Table;
import mastermind.logic.Text;
import mastermind.logic.button.GoToChooseLevel;

public class WinScene extends Scene {
    Color[] colors;
    int[] solution;
    boolean hasWon;

    public WinScene(IEngine engine, Color[] colors, int[] solution, boolean hasWon) {
        super(engine);
        this.colors=colors.clone();
        this.solution=solution.clone();
        this.hasWon=hasWon;
    }

    @Override
    public void init() {
        IFont font= getEngine().getGraphics().newFont("fonts/handwriting_draft.ttf",30,false);
        Table t= new Table(this,this.solution.length,font,false);
        t.setPosition(100,200);
        t.setSize(200,50);
        t.setStrokeColor(new Color(200,200,200));

        t.init();
        for(int i=0;i<this.solution.length;i++){
            t.fillCell(colors[solution[i]],solution[i]);
        }

        Text text= new Text(this,"Game over",font);
        text.setPosition(200,10);
        text.setStrokeColor(new Color(20,20,20));
        if(hasWon){
            text.setText("You Won");
        }

        addGameObject(text);

        text= new Text(this,"Solution:",font);
        text.setPosition(200,150);
        text.setStrokeColor(new Color(120,120,120));
        addGameObject(text);



        addGameObject(new GoToChooseLevel(this)
                .setPosition(50,450)
                .setStrokeColor(colors[0])
                .setSize(300,50)
                .addChild(new Text(this,"Go to other levels",font)
                        .setPosition(150,40)
                        .setStrokeColor(Color.BLACK)
                )

        );
        super.init();
        addGameObject(t);
    }
}
