package android.database;

public class RowBuilder {
  private final int endIndex;
  
  private int index;
  
  private final int row;
  
  RowBuilder(int paramInt) {
    this.row = paramInt;
    paramInt = MatrixCursor.access$000(paramMatrixCursor) * paramInt;
    this.index = paramInt;
    this.endIndex = paramInt + MatrixCursor.access$000(paramMatrixCursor);
  }
  
  public final RowBuilder add(int paramInt, Object paramObject) {
    MatrixCursor.access$100(MatrixCursor.this)[this.row * MatrixCursor.access$000(MatrixCursor.this) + paramInt] = paramObject;
    return this;
  }
  
  public RowBuilder add(Object paramObject) {
    if (this.index != this.endIndex) {
      Object[] arrayOfObject = MatrixCursor.access$100(MatrixCursor.this);
      int i = this.index;
      this.index = i + 1;
      arrayOfObject[i] = paramObject;
      return this;
    } 
    throw new CursorIndexOutOfBoundsException("No more columns left.");
  }
  
  public RowBuilder add(String paramString, Object paramObject) {
    for (byte b = 0; b < (MatrixCursor.access$200(MatrixCursor.this)).length; b++) {
      if (paramString.equals(MatrixCursor.access$200(MatrixCursor.this)[b]))
        MatrixCursor.access$100(MatrixCursor.this)[this.row * MatrixCursor.access$000(MatrixCursor.this) + b] = paramObject; 
    } 
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/MatrixCursor$RowBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */