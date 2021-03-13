package android.database;

import android.net.Uri;
import java.util.Iterator;

public class ContentObservable extends Observable<ContentObserver> {
  @Deprecated
  public void dispatchChange(boolean paramBoolean) {
    dispatchChange(paramBoolean, null);
  }
  
  public void dispatchChange(boolean paramBoolean, Uri paramUri) {
    synchronized (this.mObservers) {
      for (ContentObserver contentObserver : this.mObservers) {
        if (!paramBoolean || contentObserver.deliverSelfNotifications())
          contentObserver.dispatchChange(paramBoolean, paramUri); 
      } 
      return;
    } 
  }
  
  @Deprecated
  public void notifyChange(boolean paramBoolean) {
    synchronized (this.mObservers) {
      Iterator<ContentObserver> iterator = this.mObservers.iterator();
      while (iterator.hasNext())
        ((ContentObserver)iterator.next()).onChange(paramBoolean, null); 
      return;
    } 
  }
  
  public void registerObserver(ContentObserver paramContentObserver) {
    super.registerObserver(paramContentObserver);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/ContentObservable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */