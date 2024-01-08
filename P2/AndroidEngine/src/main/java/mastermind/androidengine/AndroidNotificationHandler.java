package mastermind.androidengine;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;

import mastermind.engine.INotificationHandler;
import mastermind.engine.Notification;

public class AndroidNotificationHandler implements INotificationHandler {
    private static final String CHANNEL_ID = "My Notification"; //canal de notificaciones
    private final Context context; // contexto de la app
    private final ArrayList<Notification> notifications = new ArrayList<>(); // notificaciones pendientes de enviar
    public AndroidNotificationHandler(Context context){
        this.context=context;
        createNotificationChannel();
    }

    /**
     * Creamos el canal donde se mandaran las notificaciones pendientes al salir de la app
     */
    public void createNotificationChannel(){
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "notifications", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.createNotificationChannel(channel);
    }

    /**
     * @return El ID del canal de notificaciones
     */
    @Override
    public String getChannelID() {
        return CHANNEL_ID;
    }

    /**
     *
     * @return Las notificaciones pendientes
     */
    @Override
    public ArrayList<Notification> getPendingEntries() {
        return notifications;
    }

    /**
     * Encola una notificacion
     * @param notification {@link Notification} to the queue.
     */
    @Override
    public void add(Notification notification) {
        notifications.add(notification);
    }
}
