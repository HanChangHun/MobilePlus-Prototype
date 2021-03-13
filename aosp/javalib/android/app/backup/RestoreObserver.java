package android.app.backup;

import android.annotation.SystemApi;

public abstract class RestoreObserver {
  public void onUpdate(int paramInt, String paramString) {}
  
  public void restoreFinished(int paramInt) {}
  
  @SystemApi
  public void restoreSetsAvailable(RestoreSet[] paramArrayOfRestoreSet) {}
  
  public void restoreStarting(int paramInt) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/RestoreObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */