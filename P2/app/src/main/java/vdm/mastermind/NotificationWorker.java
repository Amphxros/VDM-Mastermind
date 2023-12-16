package vdm.mastermind;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Thread for launching notifications
 */
public class NotificationWorker extends Worker {
    public static final String INPUT_TITLE = "title";
    public static final String INPUT_CONTENT = "content";
    public static final String INPUT_BIGGER_TEXT = "bigger_text";
    public static final String INPUT_CHANNEL_ID = "channel_id";
    public static final String INPUT_AUTO_CANCEL = "auto_cancel";
    private static final int NOTIFICATION_ID = 1;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        final String title = getInputData().getString(INPUT_TITLE);
        final String contentText = getInputData().getString(INPUT_CONTENT);
        final String biggerText = getInputData().getString(INPUT_BIGGER_TEXT);
        final String channelId = getInputData().getString(INPUT_CHANNEL_ID);
        final boolean autoCancel = getInputData().getBoolean(INPUT_AUTO_CANCEL, true);
        send(title, contentText, biggerText, channelId, autoCancel);
        return Result.success();
    }


    public void send(String tittle, String content, String bigger,String channelID, boolean autoCancel){
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setSmallIcon(R.drawable.mastermind)
                .setContentTitle(tittle)
                .setContentText(content)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigger))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(autoCancel);

    }
}
