package android.database;

import android.net.Uri;
import java.util.Arrays;

final class Transport extends IContentObserver.Stub {
  private ContentObserver mContentObserver;
  
  public Transport(ContentObserver paramContentObserver) {
    this.mContentObserver = paramContentObserver;
  }
  
  public void onChange(boolean paramBoolean, Uri paramUri, int paramInt) {
    onChangeEtc(paramBoolean, new Uri[] { paramUri }, 0, paramInt);
  }
  
  public void onChangeEtc(boolean paramBoolean, Uri[] paramArrayOfUri, int paramInt1, int paramInt2) {
    ContentObserver contentObserver = this.mContentObserver;
    if (contentObserver != null)
      contentObserver.dispatchChange(paramBoolean, Arrays.asList(paramArrayOfUri), paramInt1, paramInt2); 
  }
  
  public void releaseContentObserver() {
    this.mContentObserver = null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/ContentObserver$Transport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */