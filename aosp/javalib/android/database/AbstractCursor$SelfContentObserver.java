package android.database;

import java.lang.ref.WeakReference;

public class SelfContentObserver extends ContentObserver {
  WeakReference<AbstractCursor> mCursor;
  
  public SelfContentObserver(AbstractCursor paramAbstractCursor) {
    super(null);
    this.mCursor = new WeakReference<>(paramAbstractCursor);
  }
  
  public boolean deliverSelfNotifications() {
    return false;
  }
  
  public void onChange(boolean paramBoolean) {
    AbstractCursor abstractCursor = this.mCursor.get();
    if (abstractCursor != null)
      abstractCursor.onChange(false); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/AbstractCursor$SelfContentObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */