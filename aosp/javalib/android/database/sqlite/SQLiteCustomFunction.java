package android.database.sqlite;

public final class SQLiteCustomFunction {
  public final SQLiteDatabase.CustomFunction callback;
  
  public final String name;
  
  public final int numArgs;
  
  public SQLiteCustomFunction(String paramString, int paramInt, SQLiteDatabase.CustomFunction paramCustomFunction) {
    if (paramString != null) {
      this.name = paramString;
      this.numArgs = paramInt;
      this.callback = paramCustomFunction;
      return;
    } 
    throw new IllegalArgumentException("name must not be null.");
  }
  
  private void dispatchCallback(String[] paramArrayOfString) {
    this.callback.callback(paramArrayOfString);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteCustomFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */