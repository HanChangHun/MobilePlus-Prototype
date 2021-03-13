package android.database;

import java.util.ArrayList;

public class MatrixCursor extends AbstractCursor {
  private final int columnCount;
  
  private final String[] columnNames;
  
  private Object[] data;
  
  private int rowCount = 0;
  
  public MatrixCursor(String[] paramArrayOfString) {
    this(paramArrayOfString, 16);
  }
  
  public MatrixCursor(String[] paramArrayOfString, int paramInt) {
    this.columnNames = paramArrayOfString;
    this.columnCount = paramArrayOfString.length;
    int i = paramInt;
    if (paramInt < 1)
      i = 1; 
    this.data = new Object[this.columnCount * i];
  }
  
  private void addRow(ArrayList<?> paramArrayList, int paramInt) {
    int i = paramArrayList.size();
    if (i == this.columnCount) {
      this.rowCount++;
      Object[] arrayOfObject = this.data;
      for (byte b = 0; b < i; b++)
        arrayOfObject[paramInt + b] = paramArrayList.get(b); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("columnNames.length = ");
    stringBuilder.append(this.columnCount);
    stringBuilder.append(", columnValues.size() = ");
    stringBuilder.append(i);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private void ensureCapacity(int paramInt) {
    Object[] arrayOfObject = this.data;
    if (paramInt > arrayOfObject.length) {
      Object[] arrayOfObject1 = this.data;
      int i = arrayOfObject.length * 2;
      int j = i;
      if (i < paramInt)
        j = paramInt; 
      arrayOfObject = new Object[j];
      this.data = arrayOfObject;
      System.arraycopy(arrayOfObject1, 0, arrayOfObject, 0, arrayOfObject1.length);
    } 
  }
  
  private Object get(int paramInt) {
    if (paramInt >= 0 && paramInt < this.columnCount) {
      if (this.mPos >= 0) {
        if (this.mPos < this.rowCount)
          return this.data[this.mPos * this.columnCount + paramInt]; 
        throw new CursorIndexOutOfBoundsException("After last row.");
      } 
      throw new CursorIndexOutOfBoundsException("Before first row.");
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Requested column: ");
    stringBuilder.append(paramInt);
    stringBuilder.append(", # of columns: ");
    stringBuilder.append(this.columnCount);
    throw new CursorIndexOutOfBoundsException(stringBuilder.toString());
  }
  
  public void addRow(Iterable<?> paramIterable) {
    int i = this.rowCount;
    int j = this.columnCount;
    i *= j;
    j += i;
    ensureCapacity(j);
    if (paramIterable instanceof ArrayList) {
      addRow((ArrayList)paramIterable, i);
      return;
    } 
    Object[] arrayOfObject = this.data;
    for (Object object : paramIterable) {
      if (i != j) {
        arrayOfObject[i] = object;
        i++;
        continue;
      } 
      throw new IllegalArgumentException("columnValues.size() > columnNames.length");
    } 
    if (i == j) {
      this.rowCount++;
      return;
    } 
    throw new IllegalArgumentException("columnValues.size() < columnNames.length");
  }
  
  public void addRow(Object[] paramArrayOfObject) {
    int i = paramArrayOfObject.length;
    int j = this.columnCount;
    if (i == j) {
      i = this.rowCount;
      this.rowCount = i + 1;
      i *= j;
      ensureCapacity(j + i);
      System.arraycopy(paramArrayOfObject, 0, this.data, i, this.columnCount);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("columnNames.length = ");
    stringBuilder.append(this.columnCount);
    stringBuilder.append(", columnValues.length = ");
    stringBuilder.append(paramArrayOfObject.length);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public byte[] getBlob(int paramInt) {
    return (byte[])get(paramInt);
  }
  
  public String[] getColumnNames() {
    return this.columnNames;
  }
  
  public int getCount() {
    return this.rowCount;
  }
  
  public double getDouble(int paramInt) {
    Object object = get(paramInt);
    return (object == null) ? 0.0D : ((object instanceof Number) ? ((Number)object).doubleValue() : Double.parseDouble(object.toString()));
  }
  
  public float getFloat(int paramInt) {
    Object object = get(paramInt);
    return (object == null) ? 0.0F : ((object instanceof Number) ? ((Number)object).floatValue() : Float.parseFloat(object.toString()));
  }
  
  public int getInt(int paramInt) {
    Object object = get(paramInt);
    return (object == null) ? 0 : ((object instanceof Number) ? ((Number)object).intValue() : Integer.parseInt(object.toString()));
  }
  
  public long getLong(int paramInt) {
    Object object = get(paramInt);
    return (object == null) ? 0L : ((object instanceof Number) ? ((Number)object).longValue() : Long.parseLong(object.toString()));
  }
  
  public short getShort(int paramInt) {
    Object object = get(paramInt);
    return (object == null) ? 0 : ((object instanceof Number) ? ((Number)object).shortValue() : Short.parseShort(object.toString()));
  }
  
  public String getString(int paramInt) {
    Object object = get(paramInt);
    return (object == null) ? null : object.toString();
  }
  
  public int getType(int paramInt) {
    return DatabaseUtils.getTypeOfObject(get(paramInt));
  }
  
  public boolean isNull(int paramInt) {
    boolean bool;
    if (get(paramInt) == null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public RowBuilder newRow() {
    int i = this.rowCount;
    int j = i + 1;
    this.rowCount = j;
    ensureCapacity(j * this.columnCount);
    return new RowBuilder(i);
  }
  
  public class RowBuilder {
    private final int endIndex;
    
    private int index;
    
    private final int row;
    
    RowBuilder(int param1Int) {
      this.row = param1Int;
      param1Int = MatrixCursor.this.columnCount * param1Int;
      this.index = param1Int;
      this.endIndex = param1Int + MatrixCursor.this.columnCount;
    }
    
    public final RowBuilder add(int param1Int, Object param1Object) {
      MatrixCursor.this.data[this.row * MatrixCursor.this.columnCount + param1Int] = param1Object;
      return this;
    }
    
    public RowBuilder add(Object param1Object) {
      if (this.index != this.endIndex) {
        Object[] arrayOfObject = MatrixCursor.this.data;
        int i = this.index;
        this.index = i + 1;
        arrayOfObject[i] = param1Object;
        return this;
      } 
      throw new CursorIndexOutOfBoundsException("No more columns left.");
    }
    
    public RowBuilder add(String param1String, Object param1Object) {
      for (byte b = 0; b < MatrixCursor.this.columnNames.length; b++) {
        if (param1String.equals(MatrixCursor.this.columnNames[b]))
          MatrixCursor.this.data[this.row * MatrixCursor.this.columnCount + b] = param1Object; 
      } 
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/MatrixCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */