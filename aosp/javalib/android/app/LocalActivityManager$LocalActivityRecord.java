package android.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Binder;
import android.os.Bundle;
import android.view.Window;

class LocalActivityRecord extends Binder {
  Activity activity;
  
  ActivityInfo activityInfo;
  
  int curState = 0;
  
  final String id;
  
  Bundle instanceState;
  
  Intent intent;
  
  Window window;
  
  LocalActivityRecord(String paramString, Intent paramIntent) {
    this.id = paramString;
    this.intent = paramIntent;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LocalActivityManager$LocalActivityRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */