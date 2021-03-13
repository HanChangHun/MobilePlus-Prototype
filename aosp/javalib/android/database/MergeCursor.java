package android.database;

public class MergeCursor extends AbstractCursor {
  private Cursor mCursor;
  
  private Cursor[] mCursors;
  
  private DataSetObserver mObserver = new DataSetObserver() {
      public void onChanged() {
        MergeCursor.this.mPos = -1;
      }
      
      public void onInvalidated() {
        MergeCursor.this.mPos = -1;
      }
    };
  
  public MergeCursor(Cursor[] paramArrayOfCursor) {
    this.mCursors = paramArrayOfCursor;
    this.mCursor = paramArrayOfCursor[0];
    byte b = 0;
    while (true) {
      paramArrayOfCursor = this.mCursors;
      if (b < paramArrayOfCursor.length) {
        if (paramArrayOfCursor[b] != null)
          paramArrayOfCursor[b].registerDataSetObserver(this.mObserver); 
        b++;
        continue;
      } 
      break;
    } 
  }
  
  public void close() {
    int i = this.mCursors.length;
    for (byte b = 0; b < i; b++) {
      Cursor[] arrayOfCursor = this.mCursors;
      if (arrayOfCursor[b] != null)
        arrayOfCursor[b].close(); 
    } 
    super.close();
  }
  
  public void deactivate() {
    int i = this.mCursors.length;
    for (byte b = 0; b < i; b++) {
      Cursor[] arrayOfCursor = this.mCursors;
      if (arrayOfCursor[b] != null)
        arrayOfCursor[b].deactivate(); 
    } 
    super.deactivate();
  }
  
  public byte[] getBlob(int paramInt) {
    return this.mCursor.getBlob(paramInt);
  }
  
  public String[] getColumnNames() {
    Cursor cursor = this.mCursor;
    return (cursor != null) ? cursor.getColumnNames() : new String[0];
  }
  
  public int getCount() {
    int i = 0;
    int j = this.mCursors.length;
    byte b = 0;
    while (b < j) {
      Cursor[] arrayOfCursor = this.mCursors;
      int k = i;
      if (arrayOfCursor[b] != null)
        k = i + arrayOfCursor[b].getCount(); 
      b++;
      i = k;
    } 
    return i;
  }
  
  public double getDouble(int paramInt) {
    return this.mCursor.getDouble(paramInt);
  }
  
  public float getFloat(int paramInt) {
    return this.mCursor.getFloat(paramInt);
  }
  
  public int getInt(int paramInt) {
    return this.mCursor.getInt(paramInt);
  }
  
  public long getLong(int paramInt) {
    return this.mCursor.getLong(paramInt);
  }
  
  public short getShort(int paramInt) {
    return this.mCursor.getShort(paramInt);
  }
  
  public String getString(int paramInt) {
    return this.mCursor.getString(paramInt);
  }
  
  public int getType(int paramInt) {
    return this.mCursor.getType(paramInt);
  }
  
  public boolean isNull(int paramInt) {
    return this.mCursor.isNull(paramInt);
  }
  
  public boolean onMove(int paramInt1, int paramInt2) {
    this.mCursor = null;
    int i = 0;
    int j = this.mCursors.length;
    for (paramInt1 = 0; paramInt1 < j; paramInt1++) {
      Cursor[] arrayOfCursor = this.mCursors;
      if (arrayOfCursor[paramInt1] != null) {
        if (paramInt2 < arrayOfCursor[paramInt1].getCount() + i) {
          this.mCursor = this.mCursors[paramInt1];
          break;
        } 
        i += this.mCursors[paramInt1].getCount();
      } 
    } 
    Cursor cursor = this.mCursor;
    return (cursor != null) ? cursor.moveToPosition(paramInt2 - i) : false;
  }
  
  public void registerContentObserver(ContentObserver paramContentObserver) {
    int i = this.mCursors.length;
    for (byte b = 0; b < i; b++) {
      Cursor[] arrayOfCursor = this.mCursors;
      if (arrayOfCursor[b] != null)
        arrayOfCursor[b].registerContentObserver(paramContentObserver); 
    } 
  }
  
  public void registerDataSetObserver(DataSetObserver paramDataSetObserver) {
    int i = this.mCursors.length;
    for (byte b = 0; b < i; b++) {
      Cursor[] arrayOfCursor = this.mCursors;
      if (arrayOfCursor[b] != null)
        arrayOfCursor[b].registerDataSetObserver(paramDataSetObserver); 
    } 
  }
  
  public boolean requery() {
    int i = this.mCursors.length;
    for (byte b = 0; b < i; b++) {
      Cursor[] arrayOfCursor = this.mCursors;
      if (arrayOfCursor[b] != null && !arrayOfCursor[b].requery())
        return false; 
    } 
    return true;
  }
  
  public void unregisterContentObserver(ContentObserver paramContentObserver) {
    int i = this.mCursors.length;
    for (byte b = 0; b < i; b++) {
      Cursor[] arrayOfCursor = this.mCursors;
      if (arrayOfCursor[b] != null)
        arrayOfCursor[b].unregisterContentObserver(paramContentObserver); 
    } 
  }
  
  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver) {
    int i = this.mCursors.length;
    for (byte b = 0; b < i; b++) {
      Cursor[] arrayOfCursor = this.mCursors;
      if (arrayOfCursor[b] != null)
        arrayOfCursor[b].unregisterDataSetObserver(paramDataSetObserver); 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/MergeCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */