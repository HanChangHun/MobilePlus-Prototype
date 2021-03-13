package android.content;

import android.database.Cursor;
import android.os.RemoteException;

public abstract class CursorEntityIterator implements EntityIterator {
  private final Cursor mCursor;
  
  private boolean mIsClosed = false;
  
  public CursorEntityIterator(Cursor paramCursor) {
    this.mCursor = paramCursor;
    paramCursor.moveToFirst();
  }
  
  public final void close() {
    if (!this.mIsClosed) {
      this.mIsClosed = true;
      this.mCursor.close();
      return;
    } 
    throw new IllegalStateException("closing when already closed");
  }
  
  public abstract Entity getEntityAndIncrementCursor(Cursor paramCursor) throws RemoteException;
  
  public final boolean hasNext() {
    if (!this.mIsClosed)
      return this.mCursor.isAfterLast() ^ true; 
    throw new IllegalStateException("calling hasNext() when the iterator is closed");
  }
  
  public Entity next() {
    if (!this.mIsClosed) {
      if (hasNext())
        try {
          return getEntityAndIncrementCursor(this.mCursor);
        } catch (RemoteException remoteException) {
          throw new RuntimeException("caught a remote exception, this process will die soon", remoteException);
        }  
      throw new IllegalStateException("you may only call next() if hasNext() is true");
    } 
    throw new IllegalStateException("calling next() when the iterator is closed");
  }
  
  public void remove() {
    throw new UnsupportedOperationException("remove not supported by EntityIterators");
  }
  
  public final void reset() {
    if (!this.mIsClosed) {
      this.mCursor.moveToFirst();
      return;
    } 
    throw new IllegalStateException("calling reset() when the iterator is closed");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/CursorEntityIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */