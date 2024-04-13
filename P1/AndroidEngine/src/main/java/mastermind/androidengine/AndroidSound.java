package mastermind.androidengine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;

import mastermind.engine.ISound;

public class AndroidSound implements ISound {

    private SoundPool soundPool;
    private AssetManager assetManager;
    private int soundID,streamID;

    int loop=0;

    public AndroidSound(String file,SoundPool soundPool, Context context){
        this.soundPool=soundPool;
        this.soundID=-1;
        this.streamID=-1;
        this.assetManager= context.getAssets();
        try {
            AssetFileDescriptor assetFileDescriptor= assetManager.openFd(file);
            this.soundID=soundPool.load(assetFileDescriptor,1);
            assetFileDescriptor.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * Reproduce el sonido loop veces (-1 es siempre, hasta que lo paremos por codigo)
     */
    @Override
    public void play() {
        this.streamID=soundPool.play(soundID,1,1,1,loop,1);
    }

    /**
     * Para el sonido
     */
    @Override
    public void stop() {
        soundPool.stop(this.streamID);
    }

    /**
     * Cambia el loop de reproducir una vez a hacerlo hasta que lo paremos y viceversa
     */
    @Override
    public void setLoop() {

        if(this.loop==0)
            this.loop=-1;
        else
            this.loop=0;
    }


    @Override
    public void setVolume(float volume) {
        this.soundPool.setVolume(this.streamID, volume, volume);
    }
}
