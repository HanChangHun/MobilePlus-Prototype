package android.database.sqlite;

public class DbStats {
  public String cache;
  
  public String dbName;
  
  public long dbSize;
  
  public int lookaside;
  
  public long pageSize;
  
  public DbStats(String paramString, long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.dbName = paramString;
    this.pageSize = paramLong2 / 1024L;
    this.dbSize = paramLong1 * paramLong2 / 1024L;
    this.lookaside = paramInt1;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramInt2);
    stringBuilder.append("/");
    stringBuilder.append(paramInt3);
    stringBuilder.append("/");
    stringBuilder.append(paramInt4);
    this.cache = stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteDebug$DbStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */