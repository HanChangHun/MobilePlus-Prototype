package android.app;

import android.content.Intent;
import android.os.IBinder;

final class ServiceArgsData {
  Intent args;
  
  int flags;
  
  int startId;
  
  boolean taskRemoved;
  
  IBinder token;
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ServiceArgsData{token=");
    stringBuilder.append(this.token);
    stringBuilder.append(" startId=");
    stringBuilder.append(this.startId);
    stringBuilder.append(" args=");
    stringBuilder.append(this.args);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$ServiceArgsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */