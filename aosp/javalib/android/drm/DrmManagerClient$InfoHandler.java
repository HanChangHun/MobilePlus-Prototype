package android.drm;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;

class InfoHandler extends Handler {
  public static final int INFO_EVENT_TYPE = 1;
  
  public InfoHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    DrmInfoEvent drmInfoEvent;
    DrmErrorEvent drmErrorEvent;
    String str2 = null;
    StringBuilder stringBuilder = null;
    if (paramMessage.what != 1) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown message type ");
      stringBuilder.append(paramMessage.what);
      Log.e("DrmManagerClient", stringBuilder.toString());
      return;
    } 
    int i = paramMessage.arg1;
    int j = paramMessage.arg2;
    String str1 = paramMessage.obj.toString();
    switch (j) {
      default:
        drmErrorEvent = new DrmErrorEvent(i, j, str1);
        str1 = str2;
        break;
      case 2:
        try {
          DrmUtils.removeFile(str1);
        } catch (IOException iOException) {
          iOException.printStackTrace();
        } 
        drmInfoEvent = new DrmInfoEvent(i, j, str1);
        break;
      case 1:
      case 3:
      case 4:
      case 5:
      case 6:
        drmInfoEvent = new DrmInfoEvent(i, j, (String)drmInfoEvent);
        break;
    } 
    if (DrmManagerClient.access$700(DrmManagerClient.this) != null && drmInfoEvent != null)
      DrmManagerClient.access$700(DrmManagerClient.this).onInfo(DrmManagerClient.this, drmInfoEvent); 
    if (DrmManagerClient.access$600(DrmManagerClient.this) != null && drmErrorEvent != null)
      DrmManagerClient.access$600(DrmManagerClient.this).onError(DrmManagerClient.this, drmErrorEvent); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmManagerClient$InfoHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */