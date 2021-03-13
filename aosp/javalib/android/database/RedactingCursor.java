package android.database;

import android.util.SparseArray;
import java.util.Map;

public class RedactingCursor extends CrossProcessCursorWrapper {
  private final SparseArray<Object> mRedactions;
  
  private RedactingCursor(Cursor paramCursor, SparseArray<Object> paramSparseArray) {
    super(paramCursor);
    this.mRedactions = paramSparseArray;
  }
  
  public static Cursor create(Cursor paramCursor, Map<String, Object> paramMap) {
    SparseArray<Object> sparseArray = new SparseArray();
    String[] arrayOfString = paramCursor.getColumnNames();
    for (byte b = 0; b < arrayOfString.length; b++) {
      if (paramMap.containsKey(arrayOfString[b]))
        sparseArray.put(b, paramMap.get(arrayOfString[b])); 
    } 
    return (sparseArray.size() == 0) ? paramCursor : new RedactingCursor(paramCursor, sparseArray);
  }
  
  public void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer) {
    int i = this.mRedactions.indexOfKey(paramInt);
    if (i >= 0) {
      paramCharArrayBuffer.data = ((String)this.mRedactions.valueAt(i)).toCharArray();
      paramCharArrayBuffer.sizeCopied = paramCharArrayBuffer.data.length;
    } else {
      super.copyStringToBuffer(paramInt, paramCharArrayBuffer);
    } 
  }
  
  public void fillWindow(int paramInt, CursorWindow paramCursorWindow) {
    DatabaseUtils.cursorFillWindow(this, paramInt, paramCursorWindow);
  }
  
  public byte[] getBlob(int paramInt) {
    int i = this.mRedactions.indexOfKey(paramInt);
    return (i >= 0) ? (byte[])this.mRedactions.valueAt(i) : super.getBlob(paramInt);
  }
  
  public double getDouble(int paramInt) {
    int i = this.mRedactions.indexOfKey(paramInt);
    return (i >= 0) ? ((Double)this.mRedactions.valueAt(i)).doubleValue() : super.getDouble(paramInt);
  }
  
  public float getFloat(int paramInt) {
    int i = this.mRedactions.indexOfKey(paramInt);
    return (i >= 0) ? ((Float)this.mRedactions.valueAt(i)).floatValue() : super.getFloat(paramInt);
  }
  
  public int getInt(int paramInt) {
    int i = this.mRedactions.indexOfKey(paramInt);
    return (i >= 0) ? ((Integer)this.mRedactions.valueAt(i)).intValue() : super.getInt(paramInt);
  }
  
  public long getLong(int paramInt) {
    int i = this.mRedactions.indexOfKey(paramInt);
    return (i >= 0) ? ((Long)this.mRedactions.valueAt(i)).longValue() : super.getLong(paramInt);
  }
  
  public short getShort(int paramInt) {
    int i = this.mRedactions.indexOfKey(paramInt);
    return (i >= 0) ? ((Short)this.mRedactions.valueAt(i)).shortValue() : super.getShort(paramInt);
  }
  
  public String getString(int paramInt) {
    int i = this.mRedactions.indexOfKey(paramInt);
    return (i >= 0) ? (String)this.mRedactions.valueAt(i) : super.getString(paramInt);
  }
  
  public int getType(int paramInt) {
    int i = this.mRedactions.indexOfKey(paramInt);
    return (i >= 0) ? DatabaseUtils.getTypeOfObject(this.mRedactions.valueAt(i)) : super.getType(paramInt);
  }
  
  public CursorWindow getWindow() {
    return null;
  }
  
  public Cursor getWrappedCursor() {
    throw new UnsupportedOperationException("Returning underlying cursor risks leaking redacted data");
  }
  
  public boolean isNull(int paramInt) {
    int i = this.mRedactions.indexOfKey(paramInt);
    if (i >= 0) {
      boolean bool;
      if (this.mRedactions.valueAt(i) == null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    return super.isNull(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/RedactingCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */