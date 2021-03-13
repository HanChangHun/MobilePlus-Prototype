package android.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;

class HostCallbacks extends FragmentHostCallback<Activity> {
  public HostCallbacks() {
    super(paramActivity);
  }
  
  public void onAttachFragment(Fragment paramFragment) {
    Activity.this.onAttachFragment(paramFragment);
  }
  
  public void onDump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    Activity.this.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  public <T extends android.view.View> T onFindViewById(int paramInt) {
    return Activity.this.findViewById(paramInt);
  }
  
  public Activity onGetHost() {
    return Activity.this;
  }
  
  public LayoutInflater onGetLayoutInflater() {
    LayoutInflater layoutInflater = Activity.this.getLayoutInflater();
    return onUseFragmentManagerInflaterFactory() ? layoutInflater.cloneInContext((Context)Activity.this) : layoutInflater;
  }
  
  public int onGetWindowAnimations() {
    int i;
    Window window = Activity.this.getWindow();
    if (window == null) {
      i = 0;
    } else {
      i = (window.getAttributes()).windowAnimations;
    } 
    return i;
  }
  
  public boolean onHasView() {
    boolean bool;
    Window window = Activity.this.getWindow();
    if (window != null && window.peekDecorView() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean onHasWindowAnimations() {
    boolean bool;
    if (Activity.this.getWindow() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void onInvalidateOptionsMenu() {
    Activity.this.invalidateOptionsMenu();
  }
  
  public void onRequestPermissionsFromFragment(Fragment paramFragment, String[] paramArrayOfString, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("@android:requestPermissions:");
    stringBuilder.append(paramFragment.mWho);
    String str = stringBuilder.toString();
    Intent intent = Activity.this.getPackageManager().buildRequestPermissionsIntent(paramArrayOfString);
    Activity.this.startActivityForResult(str, intent, paramInt, (Bundle)null);
  }
  
  public boolean onShouldSaveFragmentState(Fragment paramFragment) {
    return Activity.this.isFinishing() ^ true;
  }
  
  public void onStartActivityAsUserFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle, UserHandle paramUserHandle) {
    Activity.access$600(Activity.this, paramFragment, paramIntent, paramInt, paramBundle, paramUserHandle);
  }
  
  public void onStartActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle) {
    Activity.this.startActivityFromFragment(paramFragment, paramIntent, paramInt, paramBundle);
  }
  
  public void onStartIntentSenderFromFragment(Fragment paramFragment, IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle) throws IntentSender.SendIntentException {
    if (Activity.this.mParent == null) {
      Activity.access$700(Activity.this, paramIntentSender, paramFragment.mWho, paramInt1, paramIntent, paramInt2, paramInt3, paramBundle);
    } else if (paramBundle != null) {
      Activity.access$800(Activity.this.mParent, paramFragment, paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramBundle);
    } 
  }
  
  public boolean onUseFragmentManagerInflaterFactory() {
    boolean bool;
    if ((Activity.this.getApplicationInfo()).targetSdkVersion >= 21) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Activity$HostCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */