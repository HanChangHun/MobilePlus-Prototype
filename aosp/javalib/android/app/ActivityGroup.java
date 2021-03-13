package android.app;

import android.content.Intent;
import android.os.Bundle;
import java.util.HashMap;

@Deprecated
public class ActivityGroup extends Activity {
  static final String PARENT_NON_CONFIG_INSTANCE_KEY = "android:parent_non_config_instance";
  
  private static final String STATES_KEY = "android:states";
  
  protected LocalActivityManager mLocalActivityManager;
  
  public ActivityGroup() {
    this(true);
  }
  
  public ActivityGroup(boolean paramBoolean) {
    this.mLocalActivityManager = new LocalActivityManager(this, paramBoolean);
  }
  
  void dispatchActivityResult(String paramString1, int paramInt1, int paramInt2, Intent paramIntent, String paramString2) {
    if (paramString1 != null) {
      Activity activity = this.mLocalActivityManager.getActivity(paramString1);
      if (activity != null) {
        activity.onActivityResult(paramInt1, paramInt2, paramIntent);
        return;
      } 
    } 
    super.dispatchActivityResult(paramString1, paramInt1, paramInt2, paramIntent, paramString2);
  }
  
  public Activity getCurrentActivity() {
    return this.mLocalActivityManager.getCurrentActivity();
  }
  
  public final LocalActivityManager getLocalActivityManager() {
    return this.mLocalActivityManager;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (paramBundle != null) {
      paramBundle = paramBundle.getBundle("android:states");
    } else {
      paramBundle = null;
    } 
    this.mLocalActivityManager.dispatchCreate(paramBundle);
  }
  
  protected void onDestroy() {
    super.onDestroy();
    this.mLocalActivityManager.dispatchDestroy(isFinishing());
  }
  
  protected void onPause() {
    super.onPause();
    this.mLocalActivityManager.dispatchPause(isFinishing());
  }
  
  protected void onResume() {
    super.onResume();
    this.mLocalActivityManager.dispatchResume();
  }
  
  public HashMap<String, Object> onRetainNonConfigurationChildInstances() {
    return this.mLocalActivityManager.dispatchRetainNonConfigurationInstance();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    Bundle bundle = this.mLocalActivityManager.saveInstanceState();
    if (bundle != null)
      paramBundle.putBundle("android:states", bundle); 
  }
  
  protected void onStop() {
    super.onStop();
    this.mLocalActivityManager.dispatchStop();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */