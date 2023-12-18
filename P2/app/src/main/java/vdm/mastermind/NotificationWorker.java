package vdm.mastermind;

import android.content.Context;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificationWorker extends Worker {
    public NotificationWorker( Context context,WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        return Result.success();
    }
}
