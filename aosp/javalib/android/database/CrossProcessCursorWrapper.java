package android.database;

public class CrossProcessCursorWrapper extends CursorWrapper implements CrossProcessCursor {
  public CrossProcessCursorWrapper(Cursor paramCursor) {
    super(paramCursor);
  }
  
  public void fillWindow(int paramInt, CursorWindow paramCursorWindow) {
    if (this.mCursor instanceof CrossProcessCursor) {
      ((CrossProcessCursor)this.mCursor).fillWindow(paramInt, paramCursorWindow);
      return;
    } 
    DatabaseUtils.cursorFillWindow(this.mCursor, paramInt, paramCursorWindow);
  }
  
  public CursorWindow getWindow() {
    return (this.mCursor instanceof CrossProcessCursor) ? ((CrossProcessCursor)this.mCursor).getWindow() : null;
  }
  
  public boolean onMove(int paramInt1, int paramInt2) {
    return (this.mCursor instanceof CrossProcessCursor) ? ((CrossProcessCursor)this.mCursor).onMove(paramInt1, paramInt2) : true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/CrossProcessCursorWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */