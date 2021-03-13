package android.app;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.CompatibilityInfo;
import android.os.Bundle;
import android.os.IBinder;

final class ReceiverData extends BroadcastReceiver.PendingResult {
  CompatibilityInfo compatInfo;
  
  ActivityInfo info;
  
  Intent intent;
  
  public ReceiverData(Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, IBinder paramIBinder, int paramInt2) {
    super(paramInt1, paramString, paramBundle, 0, paramBoolean1, paramBoolean2, paramIBinder, paramInt2, paramIntent.getFlags());
    this.intent = paramIntent;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ReceiverData{intent=");
    stringBuilder.append(this.intent);
    stringBuilder.append(" packageName=");
    stringBuilder.append(this.info.packageName);
    stringBuilder.append(" resultCode=");
    stringBuilder.append(getResultCode());
    stringBuilder.append(" resultData=");
    stringBuilder.append(getResultData());
    stringBuilder.append(" resultExtras=");
    stringBuilder.append(getResultExtras(false));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$ReceiverData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */