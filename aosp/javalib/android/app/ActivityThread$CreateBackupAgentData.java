package android.app;

import android.content.pm.ApplicationInfo;
import android.content.res.CompatibilityInfo;

final class CreateBackupAgentData {
  ApplicationInfo appInfo;
  
  int backupMode;
  
  CompatibilityInfo compatInfo;
  
  int userId;
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CreateBackupAgentData{appInfo=");
    stringBuilder.append(this.appInfo);
    stringBuilder.append(" backupAgent=");
    stringBuilder.append(this.appInfo.backupAgentName);
    stringBuilder.append(" mode=");
    stringBuilder.append(this.backupMode);
    stringBuilder.append(" userId=");
    stringBuilder.append(this.userId);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$CreateBackupAgentData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */