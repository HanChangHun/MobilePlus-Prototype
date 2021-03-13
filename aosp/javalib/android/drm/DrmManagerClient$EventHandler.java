package android.drm;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;

class EventHandler extends Handler {
  public EventHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    DrmEvent drmEvent1;
    DrmErrorEvent drmErrorEvent;
    DrmEvent drmEvent2 = null;
    StringBuilder stringBuilder = null;
    HashMap<Object, Object> hashMap = new HashMap<>();
    int i = paramMessage.what;
    if (i != 1001) {
      if (i != 1002) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown message type ");
        stringBuilder.append(paramMessage.what);
        Log.e("DrmManagerClient", stringBuilder.toString());
        return;
      } 
      DrmInfo drmInfo = (DrmInfo)paramMessage.obj;
      DrmManagerClient drmManagerClient = DrmManagerClient.this;
      DrmInfoStatus drmInfoStatus = DrmManagerClient.access$100(drmManagerClient, DrmManagerClient.access$000(drmManagerClient), drmInfo);
      hashMap.put("drm_info_status_object", drmInfoStatus);
      hashMap.put("drm_info_object", drmInfo);
      if (drmInfoStatus != null && 1 == drmInfoStatus.statusCode) {
        drmEvent1 = new DrmEvent(DrmManagerClient.access$000(DrmManagerClient.this), DrmManagerClient.access$200(DrmManagerClient.this, drmInfoStatus.infoType), null, (HashMap)hashMap);
      } else {
        if (drmInfoStatus != null) {
          i = drmInfoStatus.infoType;
        } else {
          i = drmEvent1.getInfoType();
        } 
        drmErrorEvent = new DrmErrorEvent(DrmManagerClient.access$000(DrmManagerClient.this), DrmManagerClient.access$300(DrmManagerClient.this, i), null, (HashMap)hashMap);
        drmEvent1 = drmEvent2;
      } 
    } else {
      DrmManagerClient drmManagerClient = DrmManagerClient.this;
      if (DrmManagerClient.access$400(drmManagerClient, DrmManagerClient.access$000(drmManagerClient)) == 0) {
        drmEvent1 = new DrmEvent(DrmManagerClient.access$000(DrmManagerClient.this), 1001, null);
      } else {
        drmErrorEvent = new DrmErrorEvent(DrmManagerClient.access$000(DrmManagerClient.this), 2007, null);
        drmEvent1 = drmEvent2;
      } 
    } 
    if (DrmManagerClient.access$500(DrmManagerClient.this) != null && drmEvent1 != null)
      DrmManagerClient.access$500(DrmManagerClient.this).onEvent(DrmManagerClient.this, drmEvent1); 
    if (DrmManagerClient.access$600(DrmManagerClient.this) != null && drmErrorEvent != null)
      DrmManagerClient.access$600(DrmManagerClient.this).onError(DrmManagerClient.this, drmErrorEvent); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmManagerClient$EventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */