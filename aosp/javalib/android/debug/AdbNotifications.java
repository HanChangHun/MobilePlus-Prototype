package android.debug;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.UserHandle;
import com.android.internal.notification.SystemNotificationChannels;

public final class AdbNotifications {
  private static final String ADB_NOTIFICATION_CHANNEL_ID_TV = "usbdevicemanager.adb.tv";
  
  public static Notification createNotification(Context paramContext, byte paramByte) {
    StringBuilder stringBuilder;
    int i;
    int j;
    Resources resources = paramContext.getResources();
    if (paramByte == 0) {
      j = 17039604;
      i = 17039603;
    } else if (i == 1) {
      j = 17039607;
      i = 17039606;
    } else {
      stringBuilder = new StringBuilder();
      stringBuilder.append("createNotification called with unknown transport type=");
      stringBuilder.append(i);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    CharSequence charSequence1 = resources.getText(j);
    CharSequence charSequence2 = resources.getText(i);
    Intent intent = new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS");
    intent.addFlags(268468224);
    ResolveInfo resolveInfo = stringBuilder.getPackageManager().resolveActivity(intent, 1048576);
    if (resolveInfo != null) {
      intent.setPackage(resolveInfo.activityInfo.packageName);
      PendingIntent pendingIntent = PendingIntent.getActivityAsUser((Context)stringBuilder, 0, intent, 67108864, null, UserHandle.CURRENT);
    } else {
      resolveInfo = null;
    } 
    return (new Notification.Builder((Context)stringBuilder, SystemNotificationChannels.DEVELOPER_IMPORTANT)).setSmallIcon(17303547).setWhen(0L).setOngoing(true).setTicker(charSequence1).setDefaults(0).setColor(stringBuilder.getColor(17170460)).setContentTitle(charSequence1).setContentText(charSequence2).setContentIntent((PendingIntent)resolveInfo).setVisibility(1).extend((Notification.Extender)(new Notification.TvExtender()).setChannelId("usbdevicemanager.adb.tv")).build();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/debug/AdbNotifications.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */