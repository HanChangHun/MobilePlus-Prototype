package android.app;

import android.annotation.IntRange;
import android.annotation.NonNull;
import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@SystemApi
public final class OpEntry implements Parcelable {
  public static final Parcelable.Creator<OpEntry> CREATOR = new Parcelable.Creator<OpEntry>() {
      public AppOpsManager.OpEntry createFromParcel(Parcel param2Parcel) {
        return new AppOpsManager.OpEntry(param2Parcel);
      }
      
      public AppOpsManager.OpEntry[] newArray(int param2Int) {
        return new AppOpsManager.OpEntry[param2Int];
      }
    };
  
  private final Map<String, AppOpsManager.AttributedOpEntry> mAttributedOpEntries;
  
  private final int mMode;
  
  private final int mOp;
  
  public OpEntry(int paramInt1, int paramInt2, Map<String, AppOpsManager.AttributedOpEntry> paramMap) {
    this.mOp = paramInt1;
    AnnotationValidations.validate(IntRange.class, null, paramInt1, "from", 0L, "to", 102L);
    this.mMode = paramInt2;
    AnnotationValidations.validate(AppOpsManager.Mode.class, null, paramInt2);
    this.mAttributedOpEntries = paramMap;
    AnnotationValidations.validate(NonNull.class, null, paramMap);
  }
  
  OpEntry(Parcel paramParcel) {
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
    paramParcel.readMap(linkedHashMap, AppOpsManager.AttributedOpEntry.class.getClassLoader());
    this.mOp = i;
    AnnotationValidations.validate(IntRange.class, null, i, "from", 0L, "to", 102L);
    this.mMode = j;
    AnnotationValidations.validate(AppOpsManager.Mode.class, null, j);
    this.mAttributedOpEntries = (Map)linkedHashMap;
    AnnotationValidations.validate(NonNull.class, null, linkedHashMap);
  }
  
  private AppOpsManager.NoteOpEvent getLastAccessEvent(int paramInt1, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #4
    //   3: aload_0
    //   4: getfield mAttributedOpEntries : Ljava/util/Map;
    //   7: invokeinterface values : ()Ljava/util/Collection;
    //   12: invokeinterface iterator : ()Ljava/util/Iterator;
    //   17: astore #5
    //   19: aload #5
    //   21: invokeinterface hasNext : ()Z
    //   26: ifeq -> 90
    //   29: aload #5
    //   31: invokeinterface next : ()Ljava/lang/Object;
    //   36: checkcast android/app/AppOpsManager$AttributedOpEntry
    //   39: iload_1
    //   40: iload_2
    //   41: iload_3
    //   42: invokestatic access$700 : (Landroid/app/AppOpsManager$AttributedOpEntry;III)Landroid/app/AppOpsManager$NoteOpEvent;
    //   45: astore #6
    //   47: aload #4
    //   49: ifnull -> 79
    //   52: aload #4
    //   54: astore #7
    //   56: aload #6
    //   58: ifnull -> 83
    //   61: aload #4
    //   63: astore #7
    //   65: aload #6
    //   67: invokevirtual getNoteTime : ()J
    //   70: aload #4
    //   72: invokevirtual getNoteTime : ()J
    //   75: lcmp
    //   76: ifle -> 83
    //   79: aload #6
    //   81: astore #7
    //   83: aload #7
    //   85: astore #4
    //   87: goto -> 19
    //   90: aload #4
    //   92: areturn
  }
  
  private AppOpsManager.NoteOpEvent getLastRejectEvent(int paramInt1, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #4
    //   3: aload_0
    //   4: getfield mAttributedOpEntries : Ljava/util/Map;
    //   7: invokeinterface values : ()Ljava/util/Collection;
    //   12: invokeinterface iterator : ()Ljava/util/Iterator;
    //   17: astore #5
    //   19: aload #5
    //   21: invokeinterface hasNext : ()Z
    //   26: ifeq -> 90
    //   29: aload #5
    //   31: invokeinterface next : ()Ljava/lang/Object;
    //   36: checkcast android/app/AppOpsManager$AttributedOpEntry
    //   39: iload_1
    //   40: iload_2
    //   41: iload_3
    //   42: invokestatic access$800 : (Landroid/app/AppOpsManager$AttributedOpEntry;III)Landroid/app/AppOpsManager$NoteOpEvent;
    //   45: astore #6
    //   47: aload #4
    //   49: ifnull -> 79
    //   52: aload #4
    //   54: astore #7
    //   56: aload #6
    //   58: ifnull -> 83
    //   61: aload #4
    //   63: astore #7
    //   65: aload #6
    //   67: invokevirtual getNoteTime : ()J
    //   70: aload #4
    //   72: invokevirtual getNoteTime : ()J
    //   75: lcmp
    //   76: ifle -> 83
    //   79: aload #6
    //   81: astore #7
    //   83: aload #7
    //   85: astore #4
    //   87: goto -> 19
    //   90: aload #4
    //   92: areturn
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Map<String, AppOpsManager.AttributedOpEntry> getAttributedOpEntries() {
    return this.mAttributedOpEntries;
  }
  
  @Deprecated
  public long getDuration() {
    return getLastDuration(31);
  }
  
  public long getLastAccessBackgroundTime(int paramInt) {
    return getLastAccessTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public long getLastAccessForegroundTime(int paramInt) {
    return getLastAccessTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public long getLastAccessTime(int paramInt) {
    return getLastAccessTime(100, 700, paramInt);
  }
  
  public long getLastAccessTime(int paramInt1, int paramInt2, int paramInt3) {
    AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(paramInt1, paramInt2, paramInt3);
    return (noteOpEvent == null) ? -1L : noteOpEvent.getNoteTime();
  }
  
  public long getLastBackgroundDuration(int paramInt) {
    return getLastDuration(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public AppOpsManager.OpEventProxyInfo getLastBackgroundProxyInfo(int paramInt) {
    return getLastProxyInfo(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public long getLastDuration(int paramInt) {
    return getLastDuration(100, 700, paramInt);
  }
  
  public long getLastDuration(int paramInt1, int paramInt2, int paramInt3) {
    AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(paramInt1, paramInt2, paramInt3);
    return (noteOpEvent == null) ? -1L : noteOpEvent.getDuration();
  }
  
  public long getLastForegroundDuration(int paramInt) {
    return getLastDuration(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public AppOpsManager.OpEventProxyInfo getLastForegroundProxyInfo(int paramInt) {
    return getLastProxyInfo(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public AppOpsManager.OpEventProxyInfo getLastProxyInfo(int paramInt) {
    return getLastProxyInfo(100, 700, paramInt);
  }
  
  public AppOpsManager.OpEventProxyInfo getLastProxyInfo(int paramInt1, int paramInt2, int paramInt3) {
    AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(paramInt1, paramInt2, paramInt3);
    return (noteOpEvent == null) ? null : noteOpEvent.getProxy();
  }
  
  public long getLastRejectBackgroundTime(int paramInt) {
    return getLastRejectTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public long getLastRejectForegroundTime(int paramInt) {
    return getLastRejectTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public long getLastRejectTime(int paramInt) {
    return getLastRejectTime(100, 700, paramInt);
  }
  
  public long getLastRejectTime(int paramInt1, int paramInt2, int paramInt3) {
    AppOpsManager.NoteOpEvent noteOpEvent = getLastRejectEvent(paramInt1, paramInt2, paramInt3);
    return (noteOpEvent == null) ? -1L : noteOpEvent.getNoteTime();
  }
  
  public int getMode() {
    return this.mMode;
  }
  
  public int getOp() {
    return this.mOp;
  }
  
  public String getOpStr() {
    return AppOpsManager.access$600()[this.mOp];
  }
  
  @Deprecated
  public String getProxyPackageName() {
    AppOpsManager.OpEventProxyInfo opEventProxyInfo = getLastProxyInfo(31);
    return (opEventProxyInfo == null) ? null : opEventProxyInfo.getPackageName();
  }
  
  @Deprecated
  public String getProxyPackageName(int paramInt1, int paramInt2) {
    AppOpsManager.OpEventProxyInfo opEventProxyInfo = getLastProxyInfo(paramInt1, paramInt1, paramInt2);
    return (opEventProxyInfo == null) ? null : opEventProxyInfo.getPackageName();
  }
  
  @Deprecated
  public int getProxyUid() {
    AppOpsManager.OpEventProxyInfo opEventProxyInfo = getLastProxyInfo(31);
    return (opEventProxyInfo == null) ? -1 : opEventProxyInfo.getUid();
  }
  
  @Deprecated
  public int getProxyUid(int paramInt1, int paramInt2) {
    AppOpsManager.OpEventProxyInfo opEventProxyInfo = getLastProxyInfo(paramInt1, paramInt1, paramInt2);
    return (opEventProxyInfo == null) ? -1 : opEventProxyInfo.getUid();
  }
  
  @Deprecated
  public long getRejectTime() {
    return getLastRejectTime(31);
  }
  
  @Deprecated
  public long getTime() {
    return getLastAccessTime(31);
  }
  
  public boolean isRunning() {
    Iterator<AppOpsManager.AttributedOpEntry> iterator = this.mAttributedOpEntries.values().iterator();
    while (iterator.hasNext()) {
      if (((AppOpsManager.AttributedOpEntry)iterator.next()).isRunning())
        return true; 
    } 
    return false;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mOp);
    paramParcel.writeInt(this.mMode);
    paramParcel.writeMap(this.mAttributedOpEntries);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$OpEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */