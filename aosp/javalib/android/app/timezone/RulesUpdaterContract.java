package android.app.timezone;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

public final class RulesUpdaterContract {
  public static final String ACTION_TRIGGER_RULES_UPDATE_CHECK = "com.android.intent.action.timezone.TRIGGER_RULES_UPDATE_CHECK";
  
  public static final String EXTRA_CHECK_TOKEN = "com.android.intent.extra.timezone.CHECK_TOKEN";
  
  public static final String TRIGGER_TIME_ZONE_RULES_CHECK_PERMISSION = "android.permission.TRIGGER_TIME_ZONE_RULES_CHECK";
  
  public static final String UPDATE_TIME_ZONE_RULES_PERMISSION = "android.permission.UPDATE_TIME_ZONE_RULES";
  
  public static Intent createUpdaterIntent(String paramString) {
    Intent intent = new Intent("com.android.intent.action.timezone.TRIGGER_RULES_UPDATE_CHECK");
    intent.setPackage(paramString);
    intent.setFlags(32);
    return intent;
  }
  
  public static void sendBroadcast(Context paramContext, String paramString, byte[] paramArrayOfbyte) {
    Intent intent = createUpdaterIntent(paramString);
    intent.putExtra("com.android.intent.extra.timezone.CHECK_TOKEN", paramArrayOfbyte);
    paramContext.sendBroadcastAsUser(intent, UserHandle.SYSTEM, "android.permission.UPDATE_TIME_ZONE_RULES");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/RulesUpdaterContract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */