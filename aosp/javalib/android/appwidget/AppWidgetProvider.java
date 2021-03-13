package android.appwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AppWidgetProvider extends BroadcastReceiver {
  public void onAppWidgetOptionsChanged(Context paramContext, AppWidgetManager paramAppWidgetManager, int paramInt, Bundle paramBundle) {}
  
  public void onDeleted(Context paramContext, int[] paramArrayOfint) {}
  
  public void onDisabled(Context paramContext) {}
  
  public void onEnabled(Context paramContext) {}
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    int[] arrayOfInt;
    String str = paramIntent.getAction();
    if ("android.appwidget.action.APPWIDGET_UPDATE".equals(str)) {
      Bundle bundle = paramIntent.getExtras();
      if (bundle != null) {
        arrayOfInt = bundle.getIntArray("appWidgetIds");
        if (arrayOfInt != null && arrayOfInt.length > 0)
          onUpdate(paramContext, AppWidgetManager.getInstance(paramContext), arrayOfInt); 
      } 
    } else {
      Bundle bundle;
      if ("android.appwidget.action.APPWIDGET_DELETED".equals(str)) {
        bundle = arrayOfInt.getExtras();
        if (bundle != null && bundle.containsKey("appWidgetId"))
          onDeleted(paramContext, new int[] { bundle.getInt("appWidgetId") }); 
      } else if ("android.appwidget.action.APPWIDGET_UPDATE_OPTIONS".equals(str)) {
        bundle = bundle.getExtras();
        if (bundle != null && bundle.containsKey("appWidgetId") && bundle.containsKey("appWidgetOptions")) {
          int i = bundle.getInt("appWidgetId");
          bundle = bundle.getBundle("appWidgetOptions");
          onAppWidgetOptionsChanged(paramContext, AppWidgetManager.getInstance(paramContext), i, bundle);
        } 
      } else if ("android.appwidget.action.APPWIDGET_ENABLED".equals(str)) {
        onEnabled(paramContext);
      } else if ("android.appwidget.action.APPWIDGET_DISABLED".equals(str)) {
        onDisabled(paramContext);
      } else if ("android.appwidget.action.APPWIDGET_RESTORED".equals(str)) {
        Bundle bundle1 = bundle.getExtras();
        if (bundle1 != null) {
          int[] arrayOfInt1 = bundle1.getIntArray("appWidgetOldIds");
          int[] arrayOfInt2 = bundle1.getIntArray("appWidgetIds");
          if (arrayOfInt1 != null && arrayOfInt1.length > 0) {
            onRestored(paramContext, arrayOfInt1, arrayOfInt2);
            onUpdate(paramContext, AppWidgetManager.getInstance(paramContext), arrayOfInt2);
          } 
        } 
      } 
    } 
  }
  
  public void onRestored(Context paramContext, int[] paramArrayOfint1, int[] paramArrayOfint2) {}
  
  public void onUpdate(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfint) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/AppWidgetProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */