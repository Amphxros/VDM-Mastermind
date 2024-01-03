package mastermind.androidengine;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.view.PixelCopy;
import android.view.SurfaceView;

import java.io.OutputStream;

import mastermind.engine.IShareContentManager;

public class AndroidShareContentManager implements IShareContentManager {
    Context context;
    SurfaceView view;
    public AndroidShareContentManager(Context context, SurfaceView view){
        this.context=context;
        this.view=view;
    }
    @Override
    public void share() {
        final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final HandlerThread handlerThread = new HandlerThread("PixelCopy");
            handlerThread.start();
            PixelCopy.request(view, bitmap, new PixelCopy.OnPixelCopyFinishedListener() {
                @Override
                public void onPixelCopyFinished(int copyResult) {
                    if (copyResult == PixelCopy.SUCCESS) {
                        Bitmap screenBitmap = Bitmap.createBitmap(bitmap, 0, 0, view.getWidth(), view.getHeight());
                        String imageUri = MediaStore.Images.Media.insertImage(context.getContentResolver(), screenBitmap, "descriptionTitle", "description");
                        Uri uri = Uri.parse(imageUri);
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "extraMessage");
                        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                        context.startActivity(Intent.createChooser(shareIntent, "shareTitle"));
                    }
                    handlerThread.quitSafely();
                }
            }, new Handler(handlerThread.getLooper()));
        }
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Mastermind");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DESCRIPTION, "level");
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        OutputStream outputStream;
        try {
            outputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        share.putExtra(Intent.EXTRA_STREAM, uri);

        context.startActivity(Intent.createChooser(share, "Share Image"));

    }


    private void screenshotView(Context context, Bitmap screenBitmap, String shareTitle, String extraMessage, String descriptionTitle, String description) {

    }
}
