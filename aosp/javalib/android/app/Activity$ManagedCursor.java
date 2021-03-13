package android.app;

import android.database.Cursor;

final class ManagedCursor {
  private final Cursor mCursor;
  
  private boolean mReleased;
  
  private boolean mUpdated;
  
  ManagedCursor(Cursor paramCursor) {
    this.mCursor = paramCursor;
    this.mReleased = false;
    this.mUpdated = false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Activity$ManagedCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */