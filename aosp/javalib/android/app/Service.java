package android.app;

import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.contentcapture.ContentCaptureManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Service extends ContextWrapper implements ComponentCallbacks2, ContentCaptureManager.ContentCaptureClient {
  public static final int START_CONTINUATION_MASK = 15;
  
  public static final int START_FLAG_REDELIVERY = 1;
  
  public static final int START_FLAG_RETRY = 2;
  
  public static final int START_NOT_STICKY = 2;
  
  public static final int START_REDELIVER_INTENT = 3;
  
  public static final int START_STICKY = 1;
  
  public static final int START_STICKY_COMPATIBILITY = 0;
  
  public static final int START_TASK_REMOVED_COMPLETE = 1000;
  
  public static final int STOP_FOREGROUND_DETACH = 2;
  
  public static final int STOP_FOREGROUND_REMOVE = 1;
  
  private static final String TAG = "Service";
  
  private IActivityManager mActivityManager = null;
  
  private Application mApplication = null;
  
  private String mClassName = null;
  
  private boolean mStartCompatibility = false;
  
  private ActivityThread mThread = null;
  
  private IBinder mToken = null;
  
  public Service() {
    super(null);
  }
  
  public final void attach(Context paramContext, ActivityThread paramActivityThread, String paramString, IBinder paramIBinder, Application paramApplication, Object paramObject) {
    boolean bool;
    attachBaseContext(paramContext);
    this.mThread = paramActivityThread;
    this.mClassName = paramString;
    this.mToken = paramIBinder;
    this.mApplication = paramApplication;
    this.mActivityManager = (IActivityManager)paramObject;
    if ((getApplicationInfo()).targetSdkVersion < 5) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mStartCompatibility = bool;
    setContentCaptureOptions(paramApplication.getContentCaptureOptions());
  }
  
  protected void attachBaseContext(Context paramContext) {
    super.attachBaseContext(paramContext);
    if (paramContext != null)
      paramContext.setContentCaptureOptions(getContentCaptureOptions()); 
  }
  
  public final ComponentName contentCaptureClientGetComponentName() {
    return new ComponentName((Context)this, this.mClassName);
  }
  
  public final void detachAndCleanUp() {
    this.mToken = null;
  }
  
  protected void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.println("nothing to dump");
  }
  
  public final Application getApplication() {
    return this.mApplication;
  }
  
  final String getClassName() {
    return this.mClassName;
  }
  
  public final ContentCaptureManager.ContentCaptureClient getContentCaptureClient() {
    return this;
  }
  
  public final int getForegroundServiceType() {
    int i = 0;
    try {
      IActivityManager iActivityManager = this.mActivityManager;
      ComponentName componentName = new ComponentName();
      this((Context)this, this.mClassName);
      int j = iActivityManager.getForegroundServiceType(componentName, this.mToken);
      i = j;
    } catch (RemoteException remoteException) {}
    return i;
  }
  
  public abstract IBinder onBind(Intent paramIntent);
  
  public void onConfigurationChanged(Configuration paramConfiguration) {}
  
  public void onCreate() {}
  
  public void onDestroy() {}
  
  public void onLowMemory() {}
  
  public void onRebind(Intent paramIntent) {}
  
  @Deprecated
  public void onStart(Intent paramIntent, int paramInt) {}
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    onStart(paramIntent, paramInt2);
    return this.mStartCompatibility ^ true;
  }
  
  public void onTaskRemoved(Intent paramIntent) {}
  
  public void onTrimMemory(int paramInt) {}
  
  public boolean onUnbind(Intent paramIntent) {
    return false;
  }
  
  @Deprecated
  public final void setForeground(boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setForeground: ignoring old API call on ");
    stringBuilder.append(getClass().getName());
    Log.w("Service", stringBuilder.toString());
  }
  
  public final void startForeground(int paramInt, Notification paramNotification) {
    try {
      IActivityManager iActivityManager = this.mActivityManager;
      ComponentName componentName = new ComponentName();
      this((Context)this, this.mClassName);
      iActivityManager.setServiceForeground(componentName, this.mToken, paramInt, paramNotification, 0, -1);
    } catch (RemoteException remoteException) {}
  }
  
  public final void startForeground(int paramInt1, Notification paramNotification, int paramInt2) {
    try {
      IActivityManager iActivityManager = this.mActivityManager;
      ComponentName componentName = new ComponentName();
      this((Context)this, this.mClassName);
      iActivityManager.setServiceForeground(componentName, this.mToken, paramInt1, paramNotification, 0, paramInt2);
    } catch (RemoteException remoteException) {}
  }
  
  public final void stopForeground(int paramInt) {
    try {
      IActivityManager iActivityManager = this.mActivityManager;
      ComponentName componentName = new ComponentName();
      this((Context)this, this.mClassName);
      iActivityManager.setServiceForeground(componentName, this.mToken, 0, null, paramInt, 0);
    } catch (RemoteException remoteException) {}
  }
  
  public final void stopForeground(boolean paramBoolean) {
    stopForeground(paramBoolean);
  }
  
  public final void stopSelf() {
    stopSelf(-1);
  }
  
  public final void stopSelf(int paramInt) {
    IActivityManager iActivityManager = this.mActivityManager;
    if (iActivityManager == null)
      return; 
    try {
      ComponentName componentName = new ComponentName();
      this((Context)this, this.mClassName);
      iActivityManager.stopServiceToken(componentName, this.mToken, paramInt);
    } catch (RemoteException remoteException) {}
  }
  
  public final boolean stopSelfResult(int paramInt) {
    IActivityManager iActivityManager = this.mActivityManager;
    if (iActivityManager == null)
      return false; 
    try {
      ComponentName componentName = new ComponentName();
      this((Context)this, this.mClassName);
      return iActivityManager.stopServiceToken(componentName, this.mToken, paramInt);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface StartArgFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface StartResult {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface StopForegroundFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Service.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */