package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.logic.Scene;
import mastermind.logic.Table;
import mastermind.logic.Text;
import mastermind.logic.button.GoToChooseLevel;

/**
 * Clase que representa la escena de victoria o derrota en el juego.
 */
public final class WinScene extends Scene {
    final Color[] colors; // Arreglo de colores utilizados en el juego.
    final int[] solution; // Solución del juego.
    final boolean hasWon; // Indica si el jugador ha ganado.

    final int windowW, windowH;

    /**
     * Constructor de la clase WinScene.
     *
     * @param engine   El motor del juego.
     * @param colors   Arreglo de colores utilizados en el juego.
     * @param solution Solución del juego.
     * @param hasWon   Indica si el jugador ha ganado.
     */
    public WinScene(IEngine engine, Color[] colors, int[] solution, boolean hasWon) {
        super(engine);
        this.colors=colors.clone();
        this.solution=solution.clone();
        this.hasWon=hasWon;
        windowW = engine.getGraphics().getWidth();
        windowH = engine.getGraphics().getHeight();
    }

    /**
     * Inicializa la escena de victoria o derrota, mostrando la solución y proporcionando la opción de ir a otros niveles.
     */
    @Override
    public void init() {
        // Crea una fuente para el texto.
        IFont font= getEngine().getGraphics().newFont("fonts/handwriting.ttf",30,false);

        // Crea una tabla para mostrar la solución.
        Table t= new Table(this,this.solution.length,font,false,null);
        t.setPosition( (this.windowW/2) - (this.solution.length * 50)/2, windowH/2);
        t.setSize(this.solution.length * 50,50);
        t.setStrokeColor(new Color(200,200,200));

        t.init();

        // Rellena la tabla con la contraseña.
        for(int i=0;i<this.solution.length;i++){
            t.fillCell(colors[solution[i]],solution[i]);
        }

        // Crea un texto indicando el resultado del juego.
        Text text= new Text(this,"Game over",font);
        text.setPosition(200,60);
        text.setStrokeColor(new Color(20,20,20));
        if(hasWon){
            text.setText("You Won");
        }

        addGameObject(text);

        // Añade un texto indicando la sección de la solución.
        text= new Text(this,"Solution:",font);
        text.setPosition(200,150);
        text.setStrokeColor(new Color(120,120,120));
        addGameObject(text);

        // Añade un botón para ir a otros niveles.
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
        addGameObject(t); // Añadido después para evitar la creación de nuevas celdas (inicialización de la tabla).
    }
}
