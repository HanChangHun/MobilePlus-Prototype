package android.content;

import android.database.ContentObserver;
import android.os.Handler;

@Deprecated
public final class ForceLoadContentObserver extends ContentObserver {
  public ForceLoadContentObserver() {
    super(new Handler());
  }
  
  public boolean deliverSelfNotifications() {
    return true;
  }
  
  public void onChange(boolean paramBoolean) {
    Loader.this.onContentChanged();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/Loader$ForceLoadContentObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */