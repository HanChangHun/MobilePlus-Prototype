package android.content.pm;

import android.os.Bundle;
import android.os.UserHandle;
import java.util.List;

class CallbackInfo {
  Bundle launcherExtras;
  
  String packageName;
  
  String[] packageNames;
  
  boolean replacing;
  
  List<ShortcutInfo> shortcuts;
  
  UserHandle user;
  
  private CallbackInfo() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$CallbackMessageHandler$CallbackInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */