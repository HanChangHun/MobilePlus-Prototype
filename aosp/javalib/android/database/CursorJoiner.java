package android.database;

import java.util.Iterator;

public final class CursorJoiner implements Iterator<CursorJoiner.Result>, Iterable<CursorJoiner.Result> {
  private int[] mColumnsLeft;
  
  private int[] mColumnsRight;
  
  private Result mCompareResult;
  
  private boolean mCompareResultIsValid;
  
  private Cursor mCursorLeft;
  
  private Cursor mCursorRight;
  
  private String[] mValues;
  
  public CursorJoiner(Cursor paramCursor1, String[] paramArrayOfString1, Cursor paramCursor2, String[] paramArrayOfString2) {
    if (paramArrayOfString1.length == paramArrayOfString2.length) {
      this.mCursorLeft = paramCursor1;
      this.mCursorRight = paramCursor2;
      paramCursor1.moveToFirst();
      this.mCursorRight.moveToFirst();
      this.mCompareResultIsValid = false;
      this.mColumnsLeft = buildColumnIndiciesArray(paramCursor1, paramArrayOfString1);
      this.mColumnsRight = buildColumnIndiciesArray(paramCursor2, paramArrayOfString2);
      this.mValues = new String[this.mColumnsLeft.length * 2];
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("you must have the same number of columns on the left and right, ");
    stringBuilder.append(paramArrayOfString1.length);
    stringBuilder.append(" != ");
    stringBuilder.append(paramArrayOfString2.length);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private int[] buildColumnIndiciesArray(Cursor paramCursor, String[] paramArrayOfString) {
    int[] arrayOfInt = new int[paramArrayOfString.length];
    for (byte b = 0; b < paramArrayOfString.length; b++)
      arrayOfInt[b] = paramCursor.getColumnIndexOrThrow(paramArrayOfString[b]); 
    return arrayOfInt;
  }
  
  private static int compareStrings(String... paramVarArgs) {
    if (paramVarArgs.length % 2 == 0) {
      for (byte b = 0; b < paramVarArgs.length; b += 2) {
        String str = paramVarArgs[b];
        byte b1 = -1;
        if (str == null) {
          if (paramVarArgs[b + 1] != null)
            return -1; 
        } else {
          if (paramVarArgs[b + 1] == null)
            return 1; 
          int i = paramVarArgs[b].compareTo(paramVarArgs[b + 1]);
          if (i != 0) {
            if (i < 0) {
              b = b1;
            } else {
              b = 1;
            } 
            return b;
          } 
        } 
      } 
      return 0;
    } 
    throw new IllegalArgumentException("you must specify an even number of values");
  }
  
  private void incrementCursors() {
    if (this.mCompareResultIsValid) {
      int i = null.$SwitchMap$android$database$CursorJoiner$Result[this.mCompareResult.ordinal()];
      if (i != 1) {
        if (i != 2) {
          if (i == 3)
            this.mCursorRight.moveToNext(); 
        } else {
          this.mCursorLeft.moveToNext();
        } 
      } else {
        this.mCursorLeft.moveToNext();
        this.mCursorRight.moveToNext();
      } 
      this.mCompareResultIsValid = false;
    } 
  }
  
  private static void populateValues(String[] paramArrayOfString, Cursor paramCursor, int[] paramArrayOfint, int paramInt) {
    for (byte b = 0; b < paramArrayOfint.length; b++)
      paramArrayOfString[b * 2 + paramInt] = paramCursor.getString(paramArrayOfint[b]); 
  }
  
  public boolean hasNext() {
    boolean bool = this.mCompareResultIsValid;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    null = false;
    if (bool) {
      int i = null.$SwitchMap$android$database$CursorJoiner$Result[this.mCompareResult.ordinal()];
      if (i != 1) {
        if (i != 2) {
          if (i == 3) {
            if (!this.mCursorLeft.isAfterLast() || !this.mCursorRight.isLast())
              null = true; 
            return null;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("bad value for mCompareResult, ");
          stringBuilder.append(this.mCompareResult);
          throw new IllegalStateException(stringBuilder.toString());
        } 
        if (this.mCursorLeft.isLast()) {
          null = bool1;
          return !this.mCursorRight.isAfterLast() ? true : null;
        } 
      } else {
        if (this.mCursorLeft.isLast()) {
          null = bool2;
          if (!this.mCursorRight.isLast())
            null = true; 
          return null;
        } 
        null = true;
      } 
    } else {
      if (this.mCursorLeft.isAfterLast()) {
        null = bool3;
        if (!this.mCursorRight.isAfterLast())
          null = true; 
        return null;
      } 
      null = true;
    } 
    return true;
  }
  
  public Iterator<Result> iterator() {
    return this;
  }
  
  public Result next() {
    if (hasNext()) {
      incrementCursors();
      int i = this.mCursorLeft.isAfterLast() ^ true;
      boolean bool = this.mCursorRight.isAfterLast();
      if (i != 0 && (bool ^ true) != 0) {
        populateValues(this.mValues, this.mCursorLeft, this.mColumnsLeft, 0);
        populateValues(this.mValues, this.mCursorRight, this.mColumnsRight, 1);
        i = compareStrings(this.mValues);
        if (i != -1) {
          if (i != 0) {
            if (i == 1)
              this.mCompareResult = Result.RIGHT; 
          } else {
            this.mCompareResult = Result.BOTH;
          } 
        } else {
          this.mCompareResult = Result.LEFT;
        } 
      } else if (i != 0) {
        this.mCompareResult = Result.LEFT;
      } else {
        this.mCompareResult = Result.RIGHT;
      } 
      this.mCompareResultIsValid = true;
      return this.mCompareResult;
    } 
    throw new IllegalStateException("you must only call next() when hasNext() is true");
  }
  
  public void remove() {
    throw new UnsupportedOperationException("not implemented");
  }
  
  public enum Result {
    BOTH, LEFT, RIGHT;
    
    static {
      Result result = new Result("BOTH", 2);
      BOTH = result;
      $VALUES = new Result[] { RIGHT, LEFT, result };
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/CursorJoiner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */