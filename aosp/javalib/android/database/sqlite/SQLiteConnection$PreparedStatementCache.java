package android.database.sqlite;

import android.util.LruCache;
import android.util.Printer;
import java.util.Map;

final class PreparedStatementCache extends LruCache<String, SQLiteConnection.PreparedStatement> {
  public PreparedStatementCache(int paramInt) {
    super(paramInt);
  }
  
  public void dump(Printer paramPrinter) {
    paramPrinter.println("  Prepared statement cache:");
    Map map = snapshot();
    if (!map.isEmpty()) {
      byte b = 0;
      for (Map.Entry entry : map.entrySet()) {
        SQLiteConnection.PreparedStatement preparedStatement = (SQLiteConnection.PreparedStatement)entry.getValue();
        if (preparedStatement.mInCache) {
          String str = (String)entry.getKey();
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("    ");
          stringBuilder.append(b);
          stringBuilder.append(": statementPtr=0x");
          stringBuilder.append(Long.toHexString(preparedStatement.mStatementPtr));
          stringBuilder.append(", numParameters=");
          stringBuilder.append(preparedStatement.mNumParameters);
          stringBuilder.append(", type=");
          stringBuilder.append(preparedStatement.mType);
          stringBuilder.append(", readOnly=");
          stringBuilder.append(preparedStatement.mReadOnly);
          stringBuilder.append(", sql=\"");
          stringBuilder.append(SQLiteConnection.access$200(str));
          stringBuilder.append("\"");
          paramPrinter.println(stringBuilder.toString());
        } 
        b++;
      } 
    } else {
      paramPrinter.println("    <none>");
    } 
  }
  
  protected void entryRemoved(boolean paramBoolean, String paramString, SQLiteConnection.PreparedStatement paramPreparedStatement1, SQLiteConnection.PreparedStatement paramPreparedStatement2) {
    paramPreparedStatement1.mInCache = false;
    if (!paramPreparedStatement1.mInUse)
      SQLiteConnection.access$100(SQLiteConnection.this, paramPreparedStatement1); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteConnection$PreparedStatementCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */