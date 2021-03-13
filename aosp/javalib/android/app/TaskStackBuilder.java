package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import java.util.ArrayList;

public class TaskStackBuilder {
  private static final String TAG = "TaskStackBuilder";
  
  private final ArrayList<Intent> mIntents = new ArrayList<>();
  
  private final Context mSourceContext;
  
  private TaskStackBuilder(Context paramContext) {
    this.mSourceContext = paramContext;
  }
  
  public static TaskStackBuilder create(Context paramContext) {
    return new TaskStackBuilder(paramContext);
  }
  
  public TaskStackBuilder addNextIntent(Intent paramIntent) {
    this.mIntents.add(paramIntent);
    return this;
  }
  
  public TaskStackBuilder addNextIntentWithParentStack(Intent paramIntent) {
    ComponentName componentName1 = paramIntent.getComponent();
    ComponentName componentName2 = componentName1;
    if (componentName1 == null)
      componentName2 = paramIntent.resolveActivity(this.mSourceContext.getPackageManager()); 
    if (componentName2 != null)
      addParentStack(componentName2); 
    addNextIntent(paramIntent);
    return this;
  }
  
  public TaskStackBuilder addParentStack(Activity paramActivity) {
    Intent intent = paramActivity.getParentActivityIntent();
    if (intent != null) {
      ComponentName componentName2 = intent.getComponent();
      ComponentName componentName1 = componentName2;
      if (componentName2 == null)
        componentName1 = intent.resolveActivity(this.mSourceContext.getPackageManager()); 
      addParentStack(componentName1);
      addNextIntent(intent);
    } 
    return this;
  }
  
  public TaskStackBuilder addParentStack(ComponentName paramComponentName) {
    int i = this.mIntents.size();
    PackageManager packageManager = this.mSourceContext.getPackageManager();
    try {
      ActivityInfo activityInfo = packageManager.getActivityInfo(paramComponentName, 0);
      String str = activityInfo.parentActivityName;
      while (str != null) {
        Intent intent;
        ComponentName componentName = new ComponentName();
        this(activityInfo.packageName, str);
        activityInfo = packageManager.getActivityInfo(componentName, 0);
        String str2 = activityInfo.parentActivityName;
        if (str2 == null && i == 0) {
          intent = Intent.makeMainActivity(componentName);
        } else {
          intent = new Intent();
          this();
          intent = intent.setComponent(componentName);
        } 
        this.mIntents.add(i, intent);
        String str1 = str2;
      } 
      return this;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
      throw new IllegalArgumentException(nameNotFoundException);
    } 
  }
  
  public TaskStackBuilder addParentStack(Class<?> paramClass) {
    return addParentStack(new ComponentName(this.mSourceContext, paramClass));
  }
  
  public Intent editIntentAt(int paramInt) {
    return this.mIntents.get(paramInt);
  }
  
  public int getIntentCount() {
    return this.mIntents.size();
  }
  
  public Intent[] getIntents() {
    Intent[] arrayOfIntent = new Intent[this.mIntents.size()];
    if (arrayOfIntent.length == 0)
      return arrayOfIntent; 
    arrayOfIntent[0] = (new Intent(this.mIntents.get(0))).addFlags(268484608);
    for (byte b = 1; b < arrayOfIntent.length; b++)
      arrayOfIntent[b] = new Intent(this.mIntents.get(b)); 
    return arrayOfIntent;
  }
  
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2) {
    return getPendingIntent(paramInt1, paramInt2, null);
  }
  
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2, Bundle paramBundle) {
    if (!this.mIntents.isEmpty())
      return PendingIntent.getActivities(this.mSourceContext, paramInt1, getIntents(), paramInt2, paramBundle); 
    throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
  }
  
  public PendingIntent getPendingIntent(int paramInt1, int paramInt2, Bundle paramBundle, UserHandle paramUserHandle) {
    if (!this.mIntents.isEmpty())
      return PendingIntent.getActivitiesAsUser(this.mSourceContext, paramInt1, getIntents(), paramInt2, paramBundle, paramUserHandle); 
    throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
  }
  
  public int startActivities(Bundle paramBundle, UserHandle paramUserHandle) {
    if (!this.mIntents.isEmpty())
      return this.mSourceContext.startActivitiesAsUser(getIntents(), paramBundle, paramUserHandle); 
    throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
  }
  
  public void startActivities() {
    startActivities(null);
  }
  
  public void startActivities(Bundle paramBundle) {
    startActivities(paramBundle, this.mSourceContext.getUser());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/TaskStackBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */