package android.hardware.location;

import android.content.Context;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

public class ActivityRecognitionHardware extends IActivityRecognitionHardware.Stub {
  private static final boolean DEBUG = Log.isLoggable("ActivityRecognitionHW", 3);
  
  private static final String ENFORCE_HW_PERMISSION_MESSAGE = "Permission 'android.permission.LOCATION_HARDWARE' not granted to access ActivityRecognitionHardware";
  
  private static final int EVENT_TYPE_COUNT = 3;
  
  private static final int EVENT_TYPE_DISABLED = 0;
  
  private static final int EVENT_TYPE_ENABLED = 1;
  
  private static final String HARDWARE_PERMISSION = "android.permission.LOCATION_HARDWARE";
  
  private static final int INVALID_ACTIVITY_TYPE = -1;
  
  private static final int NATIVE_SUCCESS_RESULT = 0;
  
  private static final String TAG = "ActivityRecognitionHW";
  
  private static ActivityRecognitionHardware sSingletonInstance;
  
  private static final Object sSingletonInstanceLock = new Object();
  
  private final Context mContext;
  
  private final SinkList mSinks = new SinkList();
  
  private final String[] mSupportedActivities;
  
  private final int mSupportedActivitiesCount;
  
  private final int[][] mSupportedActivitiesEnabledEvents;
  
  static {
    nativeClassInit();
  }
  
  private ActivityRecognitionHardware(Context paramContext) {
    nativeInitialize();
    this.mContext = paramContext;
    String[] arrayOfString = fetchSupportedActivities();
    this.mSupportedActivities = arrayOfString;
    int i = arrayOfString.length;
    this.mSupportedActivitiesCount = i;
    this.mSupportedActivitiesEnabledEvents = new int[i][3];
  }
  
  private void checkPermissions() {
    this.mContext.enforceCallingPermission("android.permission.LOCATION_HARDWARE", "Permission 'android.permission.LOCATION_HARDWARE' not granted to access ActivityRecognitionHardware");
  }
  
  private String[] fetchSupportedActivities() {
    String[] arrayOfString = nativeGetSupportedActivities();
    return (arrayOfString != null) ? arrayOfString : new String[0];
  }
  
  private String getActivityName(int paramInt) {
    if (paramInt >= 0) {
      String[] arrayOfString = this.mSupportedActivities;
      if (paramInt < arrayOfString.length)
        return arrayOfString[paramInt]; 
    } 
    Log.e("ActivityRecognitionHW", String.format("Invalid ActivityType: %d, SupportedActivities: %d", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(this.mSupportedActivities.length) }));
    return null;
  }
  
  private int getActivityType(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return -1; 
    int i = this.mSupportedActivities.length;
    for (byte b = 0; b < i; b++) {
      if (paramString.equals(this.mSupportedActivities[b]))
        return b; 
    } 
    return -1;
  }
  
  public static ActivityRecognitionHardware getInstance(Context paramContext) {
    synchronized (sSingletonInstanceLock) {
      if (sSingletonInstance == null) {
        ActivityRecognitionHardware activityRecognitionHardware = new ActivityRecognitionHardware();
        this(paramContext);
        sSingletonInstance = activityRecognitionHardware;
      } 
      return sSingletonInstance;
    } 
  }
  
  public static boolean isSupported() {
    return nativeIsSupported();
  }
  
  private static native void nativeClassInit();
  
  private native int nativeDisableActivityEvent(int paramInt1, int paramInt2);
  
  private native int nativeEnableActivityEvent(int paramInt1, int paramInt2, long paramLong);
  
  private native int nativeFlush();
  
  private native String[] nativeGetSupportedActivities();
  
  private native void nativeInitialize();
  
  private static native boolean nativeIsSupported();
  
  private native void nativeRelease();
  
  private void onActivityChanged(Event[] paramArrayOfEvent) {
    if (paramArrayOfEvent == null || paramArrayOfEvent.length == 0) {
      if (DEBUG)
        Log.d("ActivityRecognitionHW", "No events to broadcast for onActivityChanged."); 
      return;
    } 
    int i = paramArrayOfEvent.length;
    ActivityRecognitionEvent[] arrayOfActivityRecognitionEvent = new ActivityRecognitionEvent[i];
    byte b;
    for (b = 0; b < i; b++) {
      Event event = paramArrayOfEvent[b];
      arrayOfActivityRecognitionEvent[b] = new ActivityRecognitionEvent(getActivityName(event.activity), event.type, event.timestamp);
    } 
    ActivityChangedEvent activityChangedEvent = new ActivityChangedEvent(arrayOfActivityRecognitionEvent);
    i = this.mSinks.beginBroadcast();
    for (b = 0; b < i; b++) {
      IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink = (IActivityRecognitionHardwareSink)this.mSinks.getBroadcastItem(b);
      try {
        iActivityRecognitionHardwareSink.onActivityChanged(activityChangedEvent);
      } catch (RemoteException remoteException) {
        Log.e("ActivityRecognitionHW", "Error delivering activity changed event.", (Throwable)remoteException);
      } 
    } 
    this.mSinks.finishBroadcast();
  }
  
  public boolean disableActivityEvent(String paramString, int paramInt) {
    checkPermissions();
    int i = getActivityType(paramString);
    if (i == -1)
      return false; 
    if (nativeDisableActivityEvent(i, paramInt) == 0) {
      this.mSupportedActivitiesEnabledEvents[i][paramInt] = 0;
      return true;
    } 
    return false;
  }
  
  public boolean enableActivityEvent(String paramString, int paramInt, long paramLong) {
    checkPermissions();
    int i = getActivityType(paramString);
    if (i == -1)
      return false; 
    if (nativeEnableActivityEvent(i, paramInt, paramLong) == 0) {
      this.mSupportedActivitiesEnabledEvents[i][paramInt] = 1;
      return true;
    } 
    return false;
  }
  
  public boolean flush() {
    boolean bool;
    checkPermissions();
    if (nativeFlush() == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String[] getSupportedActivities() {
    checkPermissions();
    return this.mSupportedActivities;
  }
  
  public boolean isActivitySupported(String paramString) {
    boolean bool;
    checkPermissions();
    if (getActivityType(paramString) != -1) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean registerSink(IActivityRecognitionHardwareSink paramIActivityRecognitionHardwareSink) {
    checkPermissions();
    return this.mSinks.register(paramIActivityRecognitionHardwareSink);
  }
  
  public boolean unregisterSink(IActivityRecognitionHardwareSink paramIActivityRecognitionHardwareSink) {
    checkPermissions();
    return this.mSinks.unregister(paramIActivityRecognitionHardwareSink);
  }
  
  private static class Event {
    public int activity;
    
    public long timestamp;
    
    public int type;
  }
  
  private class SinkList extends RemoteCallbackList<IActivityRecognitionHardwareSink> {
    private SinkList() {}
    
    private void disableActivityEventIfEnabled(int param1Int1, int param1Int2) {
      if (ActivityRecognitionHardware.this.mSupportedActivitiesEnabledEvents[param1Int1][param1Int2] != 1)
        return; 
      int i = ActivityRecognitionHardware.this.nativeDisableActivityEvent(param1Int1, param1Int2);
      ActivityRecognitionHardware.this.mSupportedActivitiesEnabledEvents[param1Int1][param1Int2] = 0;
      Log.e("ActivityRecognitionHW", String.format("DisableActivityEvent: activityType=%d, eventType=%d, result=%d", new Object[] { Integer.valueOf(param1Int1), Integer.valueOf(param1Int2), Integer.valueOf(i) }));
    }
    
    public void onCallbackDied(IActivityRecognitionHardwareSink param1IActivityRecognitionHardwareSink) {
      int i = ActivityRecognitionHardware.this.mSinks.getRegisteredCallbackCount();
      if (ActivityRecognitionHardware.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RegisteredCallbackCount: ");
        stringBuilder.append(i);
        Log.d("ActivityRecognitionHW", stringBuilder.toString());
      } 
      if (i != 0)
        return; 
      for (i = 0; i < ActivityRecognitionHardware.this.mSupportedActivitiesCount; i++) {
        for (byte b = 0; b < 3; b++)
          disableActivityEventIfEnabled(i, b); 
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ActivityRecognitionHardware.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */