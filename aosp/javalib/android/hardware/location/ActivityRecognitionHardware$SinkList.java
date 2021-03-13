package android.hardware.location;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.util.Log;

class SinkList extends RemoteCallbackList<IActivityRecognitionHardwareSink> {
  private SinkList() {}
  
  private void disableActivityEventIfEnabled(int paramInt1, int paramInt2) {
    if (ActivityRecognitionHardware.access$400(ActivityRecognitionHardware.this)[paramInt1][paramInt2] != 1)
      return; 
    int i = ActivityRecognitionHardware.access$500(ActivityRecognitionHardware.this, paramInt1, paramInt2);
    ActivityRecognitionHardware.access$400(ActivityRecognitionHardware.this)[paramInt1][paramInt2] = 0;
    Log.e("ActivityRecognitionHW", String.format("DisableActivityEvent: activityType=%d, eventType=%d, result=%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(i) }));
  }
  
  public void onCallbackDied(IActivityRecognitionHardwareSink paramIActivityRecognitionHardwareSink) {
    int i = ActivityRecognitionHardware.access$100(ActivityRecognitionHardware.this).getRegisteredCallbackCount();
    if (ActivityRecognitionHardware.access$200()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("RegisteredCallbackCount: ");
      stringBuilder.append(i);
      Log.d("ActivityRecognitionHW", stringBuilder.toString());
    } 
    if (i != 0)
      return; 
    for (i = 0; i < ActivityRecognitionHardware.access$300(ActivityRecognitionHardware.this); i++) {
      for (byte b = 0; b < 3; b++)
        disableActivityEventIfEnabled(i, b); 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ActivityRecognitionHardware$SinkList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */