package android.database;

public interface CrossProcessCursor extends Cursor {
  void fillWindow(int paramInt, CursorWindow paramCursorWindow);
  
  CursorWindow getWindow();
  
  boolean onMove(int paramInt1, int paramInt2);
}


/* Location:              /home/chun/Desktop/temp/!/android/database/CrossProcessCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */