package android.content.pm;

import android.content.ComponentName;
import android.content.LocusId;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class ShortcutQuery {
  @Deprecated
  public static final int FLAG_GET_ALL_KINDS = 27;
  
  @Deprecated
  public static final int FLAG_GET_DYNAMIC = 1;
  
  public static final int FLAG_GET_KEY_FIELDS_ONLY = 4;
  
  @Deprecated
  public static final int FLAG_GET_MANIFEST = 8;
  
  @Deprecated
  public static final int FLAG_GET_PINNED = 2;
  
  public static final int FLAG_MATCH_ALL_KINDS = 27;
  
  public static final int FLAG_MATCH_ALL_KINDS_WITH_ALL_PINNED = 1051;
  
  public static final int FLAG_MATCH_CACHED = 16;
  
  public static final int FLAG_MATCH_DYNAMIC = 1;
  
  public static final int FLAG_MATCH_MANIFEST = 8;
  
  public static final int FLAG_MATCH_PINNED = 2;
  
  public static final int FLAG_MATCH_PINNED_BY_ANY_LAUNCHER = 1024;
  
  ComponentName mActivity;
  
  long mChangedSince;
  
  List<LocusId> mLocusIds;
  
  String mPackage;
  
  int mQueryFlags;
  
  List<String> mShortcutIds;
  
  public ShortcutQuery setActivity(ComponentName paramComponentName) {
    this.mActivity = paramComponentName;
    return this;
  }
  
  public ShortcutQuery setChangedSince(long paramLong) {
    this.mChangedSince = paramLong;
    return this;
  }
  
  public ShortcutQuery setLocusIds(List<LocusId> paramList) {
    this.mLocusIds = paramList;
    return this;
  }
  
  public ShortcutQuery setPackage(String paramString) {
    this.mPackage = paramString;
    return this;
  }
  
  public ShortcutQuery setQueryFlags(int paramInt) {
    this.mQueryFlags = paramInt;
    return this;
  }
  
  public ShortcutQuery setShortcutIds(List<String> paramList) {
    this.mShortcutIds = paramList;
    return this;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface QueryFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$ShortcutQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */