package android.app;

import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.content.res.CompatibilityInfo;
import android.os.IBinder;

final class CreateServiceData {
  CompatibilityInfo compatInfo;
  
  ServiceInfo info;
  
  Intent intent;
  
  IBinder token;
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CreateServiceData{token=");
    stringBuilder.append(this.token);
    stringBuilder.append(" className=");
    stringBuilder.append(this.info.name);
    stringBuilder.append(" packageName=");
    stringBuilder.append(this.info.packageName);
    stringBuilder.append(" intent=");
    stringBuilder.append(this.intent);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$CreateServiceData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */