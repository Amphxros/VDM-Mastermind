Escritorio: java awt graphics

Android:

SurfaceView → Vista Android especial preparada para poder
ejecutarse en otro hilo, preparada para dibujarse detrás de sus
vistas hermanas.
● Esto permite superponer botones de Android y otros elementos
encima
● Problema → Puede afectar al rendimiento y a la gestión de
transparencia

CREAR SURFACE VIEW:
SurfaceView surfaceView = new SurfaceView(this);
setContentView(surfaceView); 

Pintar en Android
Para dibujar algo, se necesitan 4 componentes básicos:
● Un mapa de bits para mantener los píxeles
● Un lienzo para albergar las llamadas de dibujo (escribir en el
mapa de bits)
● Una primitiva de dibujo (por ejemplo, Rect, Path, Text, Bitmap)
● Una pintura (para describir los colores y estilos para el dibujo).


Proporciona una superficie de dibujo dedicada incrustada dentro de una jerarquía de vistas.
La superficie puede se utiliza mediante SurfaceHolder, puede obtenerse llamando a
getHolder(). Además permite que un hilo secundario pueda renderizar en la pantalla.
Para ello hay que tener en cuenta:
● Todos los métodos de SurfaceView y SurfaceHolder serán llamados desde el hilo
que ejecuta la ventana de SurfaceView (normalmente el hilo principal de la
aplicación). Es decir, es necesario sincronizar cualquier estado que sea utilizado por el
hilo de dibujado.
● Debes asegurarte de que el hilo de dibujado sólo utiliza la superficie subyacente
mientras es válida - entre SurfaceHolder.Callback.surfaceCreated() y
SurfaceHolder.Callback.surfaceDestroyed().



SurfaceHolder holder = surfaceView.getHolder();
while (!holder.getSurface().isValid());
Canvas canvas = holder.lockCanvas();
//pintar
holder.unlockCanvasAndPost(canvas);

Para pintar como tal:
android.graphics.Canvas
Contiene las llamadas / métodos de "dibujar".
Ejemplos:
● drawBitmap(Bitmap bitmap, Rect src, Rect dst, Paint paint);
● drawCircle(float cx, float cy, float radius, Paint paint);
● drawLine(float startX, float startY,
float stopX, float stopY, Paint paint);
● drawRect(float left, float top,
float right, float bottom, Paint paint);

Paint android.graphics.Paint
Contiene la información de estilo, grosor y color sobre cómo dibujar
geometrías, texto y mapas de bits.
Ejemplos:
● Paint.Align
● public void setColor (int color);
● public Shader setShader (Shader shader);
● public void getTextBounds (String text, int start,
int end, Rect bounds);

Texto - android.graphics.Typeface - Pintar texto

Typeface tface = Typeface.createFromAsset(assetManager,
filePath);
Canvas canvas = holder.lockCanvas();
Paint paint = new Paint();
paint.setTypeface(tface);
paint.setTextSize(size);
canvas.drawText(text, x, y, paint);

Imagenes - android.graphics.Bitmap - Pintar imágenes
InputStream is = assetManager.open(filePath);
Bitmap bitmap = BitmapFactory.decodeStream(is);
Canvas canvas = holder.lockCanvas();
Paint paint = new Paint();
canvas.drawBitmap(bitmap, x, y, paint);

Resources
Permite acceder a archivos que no son código al igual que AssetManager
Diferencias con AssetManager:
https://es.acervolima.com/carpeta-sin-procesar-de-recursos
-en-android-studio/
Resources se obtiene desde una clase Activity o Context mediante
getResources()



