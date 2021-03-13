package android.appwidget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.RemoteViews;

class UpdateHandler extends Handler {
  public UpdateHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i != 4) {
            if (i == 5)
              AppWidgetHost.this.dispatchOnAppWidgetRemoved(paramMessage.arg1); 
          } else {
            AppWidgetHost.this.viewDataChanged(paramMessage.arg1, paramMessage.arg2);
          } 
        } else {
          AppWidgetHost.this.onProvidersChanged();
        } 
      } else {
        AppWidgetHost.this.onProviderChanged(paramMessage.arg1, (AppWidgetProviderInfo)paramMessage.obj);
      } 
    } else {
      AppWidgetHost.this.updateAppWidgetView(paramMessage.arg1, (RemoteViews)paramMessage.obj);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/AppWidgetHost$UpdateHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */