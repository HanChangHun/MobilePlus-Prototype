package android.app;

import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.autofill.AutofillManager;
import android.view.autofill.Helper;
import java.util.ArrayList;

public class Application extends ContextWrapper implements ComponentCallbacks2 {
  private static final String TAG = "Application";
  
  private ArrayList<ActivityLifecycleCallbacks> mActivityLifecycleCallbacks = new ArrayList<>();
  
  private ArrayList<OnProvideAssistDataListener> mAssistCallbacks = null;
  
  private ArrayList<ComponentCallbacks> mComponentCallbacks = new ArrayList<>();
  
  public LoadedApk mLoadedApk;
  
  public Application() {
    super(null);
  }
  
  private Object[] collectActivityLifecycleCallbacks() {
    null = null;
    synchronized (this.mActivityLifecycleCallbacks) {
      if (this.mActivityLifecycleCallbacks.size() > 0)
        null = this.mActivityLifecycleCallbacks.toArray(); 
      return null;
    } 
  }
  
  private Object[] collectComponentCallbacks() {
    null = null;
    synchronized (this.mComponentCallbacks) {
      if (this.mComponentCallbacks.size() > 0)
        null = this.mComponentCallbacks.toArray(); 
      return null;
    } 
  }
  
  public static String getProcessName() {
    return ActivityThread.currentProcessName();
  }
  
  final void attach(Context paramContext) {
    attachBaseContext(paramContext);
    this.mLoadedApk = (ContextImpl.getImpl(paramContext)).mPackageInfo;
  }
  
  void dispatchActivityCreated(Activity paramActivity, Bundle paramBundle) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityCreated(paramActivity, paramBundle);  
  }
  
  void dispatchActivityDestroyed(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityDestroyed(paramActivity);  
  }
  
  void dispatchActivityPaused(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPaused(paramActivity);  
  }
  
  void dispatchActivityPostCreated(Activity paramActivity, Bundle paramBundle) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPostCreated(paramActivity, paramBundle);  
  }
  
  void dispatchActivityPostDestroyed(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPostDestroyed(paramActivity);  
  }
  
  void dispatchActivityPostPaused(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPostPaused(paramActivity);  
  }
  
  void dispatchActivityPostResumed(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPostResumed(paramActivity);  
  }
  
  void dispatchActivityPostSaveInstanceState(Activity paramActivity, Bundle paramBundle) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPostSaveInstanceState(paramActivity, paramBundle);  
  }
  
  void dispatchActivityPostStarted(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPostStarted(paramActivity);  
  }
  
  void dispatchActivityPostStopped(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPostStopped(paramActivity);  
  }
  
  void dispatchActivityPreCreated(Activity paramActivity, Bundle paramBundle) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPreCreated(paramActivity, paramBundle);  
  }
  
  void dispatchActivityPreDestroyed(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPreDestroyed(paramActivity);  
  }
  
  void dispatchActivityPrePaused(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPrePaused(paramActivity);  
  }
  
  void dispatchActivityPreResumed(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPreResumed(paramActivity);  
  }
  
  void dispatchActivityPreSaveInstanceState(Activity paramActivity, Bundle paramBundle) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPreSaveInstanceState(paramActivity, paramBundle);  
  }
  
  void dispatchActivityPreStarted(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPreStarted(paramActivity);  
  }
  
  void dispatchActivityPreStopped(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPreStopped(paramActivity);  
  }
  
  void dispatchActivityResumed(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityResumed(paramActivity);  
  }
  
  void dispatchActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivitySaveInstanceState(paramActivity, paramBundle);  
  }
  
  void dispatchActivityStarted(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityStarted(paramActivity);  
  }
  
  void dispatchActivityStopped(Activity paramActivity) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityStopped(paramActivity);  
  }
  
  void dispatchOnProvideAssistData(Activity paramActivity, Bundle paramBundle) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mAssistCallbacks : Ljava/util/ArrayList;
    //   6: ifnonnull -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: getfield mAssistCallbacks : Ljava/util/ArrayList;
    //   16: invokevirtual toArray : ()[Ljava/lang/Object;
    //   19: astore_3
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_3
    //   23: ifnull -> 56
    //   26: iconst_0
    //   27: istore #4
    //   29: iload #4
    //   31: aload_3
    //   32: arraylength
    //   33: if_icmpge -> 56
    //   36: aload_3
    //   37: iload #4
    //   39: aaload
    //   40: checkcast android/app/Application$OnProvideAssistDataListener
    //   43: aload_1
    //   44: aload_2
    //   45: invokeinterface onProvideAssistData : (Landroid/app/Activity;Landroid/os/Bundle;)V
    //   50: iinc #4, 1
    //   53: goto -> 29
    //   56: return
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	57	finally
    //   12	22	57	finally
    //   58	60	57	finally
  }
  
  public AutofillManager.AutofillClient getAutofillClient() {
    AutofillManager.AutofillClient autofillClient = super.getAutofillClient();
    if (autofillClient != null)
      return autofillClient; 
    if (Helper.sVerbose)
      Log.v("Application", "getAutofillClient(): null on super, trying to find activity thread"); 
    ActivityThread activityThread = ActivityThread.currentActivityThread();
    if (activityThread == null)
      return null; 
    int i = activityThread.mActivities.size();
    for (byte b = 0; b < i; b++) {
      ActivityThread.ActivityClientRecord activityClientRecord = (ActivityThread.ActivityClientRecord)activityThread.mActivities.valueAt(b);
      if (activityClientRecord != null) {
        Activity activity = activityClientRecord.activity;
        if (activity != null && activity.getWindow().getDecorView().hasFocus()) {
          if (Helper.sVerbose) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getAutofillClient(): found activity for ");
            stringBuilder.append(this);
            stringBuilder.append(": ");
            stringBuilder.append(activity);
            Log.v("Application", stringBuilder.toString());
          } 
          return activity;
        } 
      } 
    } 
    if (Helper.sVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getAutofillClient(): none of the ");
      stringBuilder.append(i);
      stringBuilder.append(" activities on ");
      stringBuilder.append(this);
      stringBuilder.append(" have focus");
      Log.v("Application", stringBuilder.toString());
    } 
    return null;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    Object[] arrayOfObject = collectComponentCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ComponentCallbacks)arrayOfObject[b]).onConfigurationChanged(paramConfiguration);  
  }
  
  public void onCreate() {}
  
  public void onLowMemory() {
    Object[] arrayOfObject = collectComponentCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((ComponentCallbacks)arrayOfObject[b]).onLowMemory();  
  }
  
  public void onTerminate() {}
  
  public void onTrimMemory(int paramInt) {
    Object[] arrayOfObject = collectComponentCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++) {
        Object object = arrayOfObject[b];
        if (object instanceof ComponentCallbacks2)
          ((ComponentCallbacks2)object).onTrimMemory(paramInt); 
      }  
  }
  
  public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks paramActivityLifecycleCallbacks) {
    synchronized (this.mActivityLifecycleCallbacks) {
      this.mActivityLifecycleCallbacks.add(paramActivityLifecycleCallbacks);
      return;
    } 
  }
  
  public void registerComponentCallbacks(ComponentCallbacks paramComponentCallbacks) {
    synchronized (this.mComponentCallbacks) {
      this.mComponentCallbacks.add(paramComponentCallbacks);
      return;
    } 
  }
  
  public void registerOnProvideAssistDataListener(OnProvideAssistDataListener paramOnProvideAssistDataListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mAssistCallbacks : Ljava/util/ArrayList;
    //   6: ifnonnull -> 22
    //   9: new java/util/ArrayList
    //   12: astore_2
    //   13: aload_2
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: aload_2
    //   19: putfield mAssistCallbacks : Ljava/util/ArrayList;
    //   22: aload_0
    //   23: getfield mAssistCallbacks : Ljava/util/ArrayList;
    //   26: aload_1
    //   27: invokevirtual add : (Ljava/lang/Object;)Z
    //   30: pop
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: astore_1
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	34	finally
    //   22	33	34	finally
    //   35	37	34	finally
  }
  
  public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks paramActivityLifecycleCallbacks) {
    synchronized (this.mActivityLifecycleCallbacks) {
      this.mActivityLifecycleCallbacks.remove(paramActivityLifecycleCallbacks);
      return;
    } 
  }
  
  public void unregisterComponentCallbacks(ComponentCallbacks paramComponentCallbacks) {
    synchronized (this.mComponentCallbacks) {
      this.mComponentCallbacks.remove(paramComponentCallbacks);
      return;
    } 
  }
  
  public void unregisterOnProvideAssistDataListener(OnProvideAssistDataListener paramOnProvideAssistDataListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mAssistCallbacks : Ljava/util/ArrayList;
    //   6: ifnull -> 18
    //   9: aload_0
    //   10: getfield mAssistCallbacks : Ljava/util/ArrayList;
    //   13: aload_1
    //   14: invokevirtual remove : (Ljava/lang/Object;)Z
    //   17: pop
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	21	finally
    //   18	20	21	finally
    //   22	24	21	finally
  }
  
  public static interface ActivityLifecycleCallbacks {
    void onActivityCreated(Activity param1Activity, Bundle param1Bundle);
    
    void onActivityDestroyed(Activity param1Activity);
    
    void onActivityPaused(Activity param1Activity);
    
    default void onActivityPostCreated(Activity param1Activity, Bundle param1Bundle) {}
    
    default void onActivityPostDestroyed(Activity param1Activity) {}
    
    default void onActivityPostPaused(Activity param1Activity) {}
    
    default void onActivityPostResumed(Activity param1Activity) {}
    
    default void onActivityPostSaveInstanceState(Activity param1Activity, Bundle param1Bundle) {}
    
    default void onActivityPostStarted(Activity param1Activity) {}
    
    default void onActivityPostStopped(Activity param1Activity) {}
    
    default void onActivityPreCreated(Activity param1Activity, Bundle param1Bundle) {}
    
    default void onActivityPreDestroyed(Activity param1Activity) {}
    
    default void onActivityPrePaused(Activity param1Activity) {}
    
    default void onActivityPreResumed(Activity param1Activity) {}
    
    default void onActivityPreSaveInstanceState(Activity param1Activity, Bundle param1Bundle) {}
    
    default void onActivityPreStarted(Activity param1Activity) {}
    
    default void onActivityPreStopped(Activity param1Activity) {}
    
    void onActivityResumed(Activity param1Activity);
    
    void onActivitySaveInstanceState(Activity param1Activity, Bundle param1Bundle);
    
    void onActivityStarted(Activity param1Activity);
    
    void onActivityStopped(Activity param1Activity);
  }
  
  public static interface OnProvideAssistDataListener {
    void onProvideAssistData(Activity param1Activity, Bundle param1Bundle);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Application.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */