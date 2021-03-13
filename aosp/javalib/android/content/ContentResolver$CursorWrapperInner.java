package android.content;

import android.database.CrossProcessCursorWrapper;
import android.database.Cursor;
import dalvik.system.CloseGuard;
import java.util.concurrent.atomic.AtomicBoolean;

final class CursorWrapperInner extends CrossProcessCursorWrapper {
  private final CloseGuard mCloseGuard;
  
  private final IContentProvider mContentProvider;
  
  private final AtomicBoolean mProviderReleased = new AtomicBoolean();
  
  CursorWrapperInner(Cursor paramCursor, IContentProvider paramIContentProvider) {
    super(paramCursor);
    CloseGuard closeGuard = CloseGuard.get();
    this.mCloseGuard = closeGuard;
    this.mContentProvider = paramIContentProvider;
    closeGuard.open("close");
  }
  
  public void close() {
    this.mCloseGuard.close();
    super.close();
    if (this.mProviderReleased.compareAndSet(false, true))
      ContentResolver.this.releaseProvider(this.mContentProvider); 
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mCloseGuard != null)
        this.mCloseGuard.warnIfOpen(); 
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentResolver$CursorWrapperInner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */