package android.content.pm;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import java.util.List;

class CallbackMessageHandler extends Handler {
  private static final int MSG_ADDED = 1;
  
  private static final int MSG_AVAILABLE = 4;
  
  private static final int MSG_CHANGED = 3;
  
  private static final int MSG_REMOVED = 2;
  
  private static final int MSG_SHORTCUT_CHANGED = 8;
  
  private static final int MSG_SUSPENDED = 6;
  
  private static final int MSG_UNAVAILABLE = 5;
  
  private static final int MSG_UNSUSPENDED = 7;
  
  private LauncherApps.Callback mCallback;
  
  public CallbackMessageHandler(Looper paramLooper, LauncherApps.Callback paramCallback) {
    super(paramLooper, null, true);
    this.mCallback = paramCallback;
  }
  
  public void handleMessage(Message paramMessage) {
    if (this.mCallback == null || !(paramMessage.obj instanceof CallbackInfo))
      return; 
    CallbackInfo callbackInfo = (CallbackInfo)paramMessage.obj;
    switch (paramMessage.what) {
      default:
        return;
      case 8:
        this.mCallback.onShortcutsChanged(callbackInfo.packageName, callbackInfo.shortcuts, callbackInfo.user);
      case 7:
        this.mCallback.onPackagesUnsuspended(callbackInfo.packageNames, callbackInfo.user);
      case 6:
        this.mCallback.onPackagesSuspended(callbackInfo.packageNames, callbackInfo.user, callbackInfo.launcherExtras);
      case 5:
        this.mCallback.onPackagesUnavailable(callbackInfo.packageNames, callbackInfo.user, callbackInfo.replacing);
      case 4:
        this.mCallback.onPackagesAvailable(callbackInfo.packageNames, callbackInfo.user, callbackInfo.replacing);
      case 3:
        this.mCallback.onPackageChanged(callbackInfo.packageName, callbackInfo.user);
      case 2:
        this.mCallback.onPackageRemoved(callbackInfo.packageName, callbackInfo.user);
      case 1:
        break;
    } 
    this.mCallback.onPackageAdded(callbackInfo.packageName, callbackInfo.user);
  }
  
  public void postOnPackageAdded(String paramString, UserHandle paramUserHandle) {
    CallbackInfo callbackInfo = new CallbackInfo();
    callbackInfo.packageName = paramString;
    callbackInfo.user = paramUserHandle;
    obtainMessage(1, callbackInfo).sendToTarget();
  }
  
  public void postOnPackageChanged(String paramString, UserHandle paramUserHandle) {
    CallbackInfo callbackInfo = new CallbackInfo();
    callbackInfo.packageName = paramString;
    callbackInfo.user = paramUserHandle;
    obtainMessage(3, callbackInfo).sendToTarget();
  }
  
  public void postOnPackageRemoved(String paramString, UserHandle paramUserHandle) {
    CallbackInfo callbackInfo = new CallbackInfo();
    callbackInfo.packageName = paramString;
    callbackInfo.user = paramUserHandle;
    obtainMessage(2, callbackInfo).sendToTarget();
  }
  
  public void postOnPackagesAvailable(String[] paramArrayOfString, UserHandle paramUserHandle, boolean paramBoolean) {
    CallbackInfo callbackInfo = new CallbackInfo();
    callbackInfo.packageNames = paramArrayOfString;
    callbackInfo.replacing = paramBoolean;
    callbackInfo.user = paramUserHandle;
    obtainMessage(4, callbackInfo).sendToTarget();
  }
  
  public void postOnPackagesSuspended(String[] paramArrayOfString, Bundle paramBundle, UserHandle paramUserHandle) {
    CallbackInfo callbackInfo = new CallbackInfo();
    callbackInfo.packageNames = paramArrayOfString;
    callbackInfo.user = paramUserHandle;
    callbackInfo.launcherExtras = paramBundle;
    obtainMessage(6, callbackInfo).sendToTarget();
  }
  
  public void postOnPackagesUnavailable(String[] paramArrayOfString, UserHandle paramUserHandle, boolean paramBoolean) {
    CallbackInfo callbackInfo = new CallbackInfo();
    callbackInfo.packageNames = paramArrayOfString;
    callbackInfo.replacing = paramBoolean;
    callbackInfo.user = paramUserHandle;
    obtainMessage(5, callbackInfo).sendToTarget();
  }
  
  public void postOnPackagesUnsuspended(String[] paramArrayOfString, UserHandle paramUserHandle) {
    CallbackInfo callbackInfo = new CallbackInfo();
    callbackInfo.packageNames = paramArrayOfString;
    callbackInfo.user = paramUserHandle;
    obtainMessage(7, callbackInfo).sendToTarget();
  }
  
  public void postOnShortcutChanged(String paramString, UserHandle paramUserHandle, List<ShortcutInfo> paramList) {
    CallbackInfo callbackInfo = new CallbackInfo();
    callbackInfo.packageName = paramString;
    callbackInfo.user = paramUserHandle;
    callbackInfo.shortcuts = paramList;
    obtainMessage(8, callbackInfo).sendToTarget();
  }
  
  private static class CallbackInfo {
    Bundle launcherExtras;
    
    String packageName;
    
    String[] packageNames;
    
    boolean replacing;
    
    List<ShortcutInfo> shortcuts;
    
    UserHandle user;
    
    private CallbackInfo() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$CallbackMessageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */