package android.app.compat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

final class ChangeIdStateQuery {
  static final int QUERY_BY_PACKAGE_NAME = 0;
  
  static final int QUERY_BY_UID = 1;
  
  public long changeId;
  
  public String packageName;
  
  public int type;
  
  public int uid;
  
  public int userId;
  
  private ChangeIdStateQuery(int paramInt1, long paramLong, String paramString, int paramInt2, int paramInt3) {
    this.type = paramInt1;
    this.changeId = paramLong;
    this.packageName = paramString;
    this.uid = paramInt2;
    this.userId = paramInt3;
  }
  
  static ChangeIdStateQuery byPackageName(long paramLong, String paramString, int paramInt) {
    return new ChangeIdStateQuery(0, paramLong, paramString, 0, paramInt);
  }
  
  static ChangeIdStateQuery byUid(long paramLong, int paramInt) {
    return new ChangeIdStateQuery(1, paramLong, null, paramInt, 0);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || !(paramObject instanceof ChangeIdStateQuery))
      return false; 
    paramObject = paramObject;
    if (this.type != ((ChangeIdStateQuery)paramObject).type || this.changeId != ((ChangeIdStateQuery)paramObject).changeId || !Objects.equals(this.packageName, ((ChangeIdStateQuery)paramObject).packageName) || this.uid != ((ChangeIdStateQuery)paramObject).uid || this.userId != ((ChangeIdStateQuery)paramObject).userId)
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.type), Long.valueOf(this.changeId), this.packageName, Integer.valueOf(this.uid), Integer.valueOf(this.userId) });
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface QueryType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/compat/ChangeIdStateQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */