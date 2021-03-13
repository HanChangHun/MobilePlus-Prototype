package android.content;

import android.database.CrossProcessCursorWrapper;
import android.database.Cursor;
import dalvik.system.CloseGuard;

final class CursorWrapperInner extends CrossProcessCursorWrapper {
  private final CloseGuard mCloseGuard;
  
  CursorWrapperInner(Cursor paramCursor) {
    super(paramCursor);
    CloseGuard closeGuard = CloseGuard.get();
    this.mCloseGuard = closeGuard;
    closeGuard.open("close");
  }
  
  public void close() {
    this.mCloseGuard.close();
    super.close();
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


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderClient$CursorWrapperInner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */