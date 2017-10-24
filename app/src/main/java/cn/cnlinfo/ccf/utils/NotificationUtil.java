package cn.cnlinfo.ccf.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import cn.cnlinfo.ccf.R;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class NotificationUtil {
    private NotificationManager notificationManager;
    private static Context context;
    private static  NotificationUtil notificationUtil;
    private NotificationUtil(Context context) {
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static synchronized NotificationUtil getInstance(Context context){
        if (notificationUtil==null){
            notificationUtil = new NotificationUtil(context);
        }
        return notificationUtil;
    }

    /**
     * 点击一次出现一条
     * 修改PendingIntent.getActivity(context,Integer.valueOf(code),intent,PendingIntent.FLAG_CANCEL_CURRENT);二随机值、四两个参数
     *  notificationManager.notify(Integer.valueOf(code),notification)修改第一个参数，随机值
     * @param code
     */
    public void sendNormalNotification(String code){
        Notification .Builder builder = new Notification.Builder(context);
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context,Integer.valueOf(code),intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ccf)
                .setContentTitle("手机验证")
                .setContentText("【碳控因子】欢迎您,您的验证码是"+code)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setOngoing(false)
                .setTicker("碳控因子")
                .setVibrate(new long[]{500,500,500,500})
                .setDefaults(Notification.DEFAULT_SOUND);
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(Integer.valueOf(code),notification);
    }
}
