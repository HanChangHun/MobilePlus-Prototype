package android.app;

import android.content.Intent;
import android.os.IBinder;

final class BindServiceData {
  Intent intent;
  
  boolean rebind;
  
  IBinder token;
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BindServiceData{token=");
    stringBuilder.append(this.token);
    stringBuilder.append(" intent=");
    stringBuilder.append(this.intent);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$BindServiceData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */