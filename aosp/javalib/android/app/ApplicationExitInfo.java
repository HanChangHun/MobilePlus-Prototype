package android.app;

import android.icu.text.SimpleDateFormat;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.DebugUtils;
import android.util.proto.ProtoInputStream;
import android.util.proto.ProtoOutputStream;
import android.util.proto.WireTypeMismatchException;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

public final class ApplicationExitInfo implements Parcelable {
  public static final Parcelable.Creator<ApplicationExitInfo> CREATOR = new Parcelable.Creator<ApplicationExitInfo>() {
      public ApplicationExitInfo createFromParcel(Parcel param1Parcel) {
        return new ApplicationExitInfo(param1Parcel);
      }
      
      public ApplicationExitInfo[] newArray(int param1Int) {
        return new ApplicationExitInfo[param1Int];
      }
    };
  
  public static final int REASON_ANR = 6;
  
  public static final int REASON_CRASH = 4;
  
  public static final int REASON_CRASH_NATIVE = 5;
  
  public static final int REASON_DEPENDENCY_DIED = 12;
  
  public static final int REASON_EXCESSIVE_RESOURCE_USAGE = 9;
  
  public static final int REASON_EXIT_SELF = 1;
  
  public static final int REASON_INITIALIZATION_FAILURE = 7;
  
  public static final int REASON_LOW_MEMORY = 3;
  
  public static final int REASON_OTHER = 13;
  
  public static final int REASON_PERMISSION_CHANGE = 8;
  
  public static final int REASON_SIGNALED = 2;
  
  public static final int REASON_UNKNOWN = 0;
  
  public static final int REASON_USER_REQUESTED = 10;
  
  public static final int REASON_USER_STOPPED = 11;
  
  public static final int SUBREASON_EXCESSIVE_CPU = 7;
  
  public static final int SUBREASON_IMPERCEPTIBLE = 15;
  
  public static final int SUBREASON_INVALID_START = 13;
  
  public static final int SUBREASON_INVALID_STATE = 14;
  
  public static final int SUBREASON_ISOLATED_NOT_NEEDED = 17;
  
  public static final int SUBREASON_KILL_ALL_BG_EXCEPT = 10;
  
  public static final int SUBREASON_KILL_ALL_FG = 9;
  
  public static final int SUBREASON_KILL_PID = 12;
  
  public static final int SUBREASON_KILL_UID = 11;
  
  public static final int SUBREASON_LARGE_CACHED = 5;
  
  public static final int SUBREASON_MEMORY_PRESSURE = 6;
  
  public static final int SUBREASON_REMOVE_LRU = 16;
  
  public static final int SUBREASON_SYSTEM_UPDATE_DONE = 8;
  
  public static final int SUBREASON_TOO_MANY_CACHED = 2;
  
  public static final int SUBREASON_TOO_MANY_EMPTY = 3;
  
  public static final int SUBREASON_TRIM_EMPTY = 4;
  
  public static final int SUBREASON_UNKNOWN = 0;
  
  public static final int SUBREASON_WAIT_FOR_DEBUGGER = 1;
  
  private IAppTraceRetriever mAppTraceRetriever;
  
  private int mConnectionGroup;
  
  private int mDefiningUid;
  
  private String mDescription;
  
  private int mImportance;
  
  private String[] mPackageList;
  
  private String mPackageName;
  
  private int mPackageUid;
  
  private int mPid;
  
  private String mProcessName;
  
  private long mPss;
  
  private int mRealUid;
  
  private int mReason;
  
  private long mRss;
  
  private byte[] mState;
  
  private int mStatus;
  
  private int mSubReason;
  
  private long mTimestamp;
  
  private File mTraceFile;
  
  public ApplicationExitInfo() {}
  
  public ApplicationExitInfo(ApplicationExitInfo paramApplicationExitInfo) {
    this.mPid = paramApplicationExitInfo.mPid;
    this.mRealUid = paramApplicationExitInfo.mRealUid;
    this.mPackageUid = paramApplicationExitInfo.mPackageUid;
    this.mDefiningUid = paramApplicationExitInfo.mDefiningUid;
    this.mProcessName = paramApplicationExitInfo.mProcessName;
    this.mPackageName = paramApplicationExitInfo.mPackageName;
    this.mConnectionGroup = paramApplicationExitInfo.mConnectionGroup;
    this.mReason = paramApplicationExitInfo.mReason;
    this.mStatus = paramApplicationExitInfo.mStatus;
    this.mSubReason = paramApplicationExitInfo.mSubReason;
    this.mImportance = paramApplicationExitInfo.mImportance;
    this.mPss = paramApplicationExitInfo.mPss;
    this.mRss = paramApplicationExitInfo.mRss;
    this.mTimestamp = paramApplicationExitInfo.mTimestamp;
    this.mDescription = paramApplicationExitInfo.mDescription;
    this.mPackageName = paramApplicationExitInfo.mPackageName;
    this.mPackageList = paramApplicationExitInfo.mPackageList;
    this.mState = paramApplicationExitInfo.mState;
    this.mTraceFile = paramApplicationExitInfo.mTraceFile;
    this.mAppTraceRetriever = paramApplicationExitInfo.mAppTraceRetriever;
  }
  
  private ApplicationExitInfo(Parcel paramParcel) {
    this.mPid = paramParcel.readInt();
    this.mRealUid = paramParcel.readInt();
    this.mPackageUid = paramParcel.readInt();
    this.mDefiningUid = paramParcel.readInt();
    this.mProcessName = paramParcel.readString();
    this.mPackageName = paramParcel.readString();
    this.mConnectionGroup = paramParcel.readInt();
    this.mReason = paramParcel.readInt();
    this.mSubReason = paramParcel.readInt();
    this.mStatus = paramParcel.readInt();
    this.mImportance = paramParcel.readInt();
    this.mPss = paramParcel.readLong();
    this.mRss = paramParcel.readLong();
    this.mTimestamp = paramParcel.readLong();
    this.mDescription = paramParcel.readString();
    this.mState = paramParcel.createByteArray();
    if (paramParcel.readInt() == 1)
      this.mAppTraceRetriever = IAppTraceRetriever.Stub.asInterface(paramParcel.readStrongBinder()); 
  }
  
  private static String reasonCodeToString(int paramInt) {
    switch (paramInt) {
      default:
        return "UNKNOWN";
      case 13:
        return "OTHER KILLS BY SYSTEM";
      case 12:
        return "DEPENDENCY DIED";
      case 11:
        return "USER STOPPED";
      case 10:
        return "USER REQUESTED";
      case 9:
        return "EXCESSIVE RESOURCE USAGE";
      case 8:
        return "PERMISSION CHANGE";
      case 7:
        return "INITIALIZATION FAILURE";
      case 6:
        return "ANR";
      case 5:
        return "APP CRASH(NATIVE)";
      case 4:
        return "APP CRASH(EXCEPTION)";
      case 3:
        return "LOW_MEMORY";
      case 2:
        return "SIGNALED";
      case 1:
        break;
    } 
    return "EXIT_SELF";
  }
  
  public static String subreasonToString(int paramInt) {
    switch (paramInt) {
      default:
        return "UNKNOWN";
      case 17:
        return "ISOLATED NOT NEEDED";
      case 16:
        return "REMOVE LRU";
      case 15:
        return "IMPERCEPTIBLE";
      case 14:
        return "INVALID STATE";
      case 13:
        return "INVALID START";
      case 12:
        return "KILL PID";
      case 11:
        return "KILL UID";
      case 10:
        return "KILL ALL BG EXCEPT";
      case 9:
        return "KILL ALL FG";
      case 8:
        return "SYSTEM UPDATE_DONE";
      case 7:
        return "EXCESSIVE CPU USAGE";
      case 6:
        return "MEMORY PRESSURE";
      case 5:
        return "LARGE CACHED";
      case 4:
        return "TRIM EMPTY";
      case 3:
        return "TOO MANY EMPTY PROCS";
      case 2:
        return "TOO MANY CACHED PROCS";
      case 1:
        break;
    } 
    return "WAIT FOR DEBUGGER";
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(PrintWriter paramPrintWriter, String paramString1, String paramString2, SimpleDateFormat paramSimpleDateFormat) {
    String str;
    StringBuilder stringBuilder4 = new StringBuilder();
    stringBuilder4.append(paramString1);
    stringBuilder4.append("ApplicationExitInfo ");
    stringBuilder4.append(paramString2);
    stringBuilder4.append(":");
    paramPrintWriter.println(stringBuilder4.toString());
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  timestamp=");
    stringBuilder2.append(paramSimpleDateFormat.format(new Date(this.mTimestamp)));
    paramPrintWriter.println(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  pid=");
    stringBuilder2.append(this.mPid);
    paramPrintWriter.println(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  realUid=");
    stringBuilder2.append(this.mRealUid);
    paramPrintWriter.println(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  packageUid=");
    stringBuilder2.append(this.mPackageUid);
    paramPrintWriter.println(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  definingUid=");
    stringBuilder2.append(this.mDefiningUid);
    paramPrintWriter.println(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  user=");
    stringBuilder2.append(UserHandle.getUserId(this.mPackageUid));
    paramPrintWriter.println(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  process=");
    stringBuilder2.append(this.mProcessName);
    paramPrintWriter.println(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  reason=");
    stringBuilder2.append(this.mReason);
    stringBuilder2.append(" (");
    stringBuilder2.append(reasonCodeToString(this.mReason));
    stringBuilder2.append(")");
    paramPrintWriter.println(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  status=");
    stringBuilder2.append(this.mStatus);
    paramPrintWriter.println(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  importance=");
    stringBuilder2.append(this.mImportance);
    paramPrintWriter.println(stringBuilder2.toString());
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  pss=");
    paramPrintWriter.print(stringBuilder2.toString());
    DebugUtils.printSizeValue(paramPrintWriter, this.mPss << 10L);
    paramPrintWriter.println();
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  rss=");
    paramPrintWriter.print(stringBuilder2.toString());
    DebugUtils.printSizeValue(paramPrintWriter, this.mRss << 10L);
    paramPrintWriter.println();
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString1);
    stringBuilder2.append("  description=");
    stringBuilder2.append(this.mDescription);
    paramPrintWriter.println(stringBuilder2.toString());
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(paramString1);
    stringBuilder3.append("  state=");
    if (ArrayUtils.isEmpty(this.mState)) {
      str = "empty";
    } else {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(Integer.toString(this.mState.length));
      stringBuilder2.append(" bytes");
      str = stringBuilder2.toString();
    } 
    stringBuilder3.append(str);
    paramPrintWriter.println(stringBuilder3.toString());
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(paramString1);
    stringBuilder1.append("  trace=");
    stringBuilder1.append(this.mTraceFile);
    paramPrintWriter.println(stringBuilder1.toString());
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null || !(paramObject instanceof ApplicationExitInfo))
      return false; 
    paramObject = paramObject;
    if (this.mPid == ((ApplicationExitInfo)paramObject).mPid && this.mRealUid == ((ApplicationExitInfo)paramObject).mRealUid && this.mPackageUid == ((ApplicationExitInfo)paramObject).mPackageUid && this.mDefiningUid == ((ApplicationExitInfo)paramObject).mDefiningUid && this.mConnectionGroup == ((ApplicationExitInfo)paramObject).mConnectionGroup && this.mReason == ((ApplicationExitInfo)paramObject).mReason && this.mSubReason == ((ApplicationExitInfo)paramObject).mSubReason && this.mImportance == ((ApplicationExitInfo)paramObject).mImportance && this.mStatus == ((ApplicationExitInfo)paramObject).mStatus && this.mTimestamp == ((ApplicationExitInfo)paramObject).mTimestamp && this.mPss == ((ApplicationExitInfo)paramObject).mPss && this.mRss == ((ApplicationExitInfo)paramObject).mRss && TextUtils.equals(this.mProcessName, ((ApplicationExitInfo)paramObject).mProcessName) && TextUtils.equals(this.mDescription, ((ApplicationExitInfo)paramObject).mDescription))
      bool = true; 
    return bool;
  }
  
  public int getConnectionGroup() {
    return this.mConnectionGroup;
  }
  
  public int getDefiningUid() {
    return this.mDefiningUid;
  }
  
  public String getDescription() {
    return this.mDescription;
  }
  
  public int getImportance() {
    return this.mImportance;
  }
  
  public String[] getPackageList() {
    return this.mPackageList;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public int getPackageUid() {
    return this.mPackageUid;
  }
  
  public int getPid() {
    return this.mPid;
  }
  
  public String getProcessName() {
    return this.mProcessName;
  }
  
  public byte[] getProcessStateSummary() {
    return this.mState;
  }
  
  public long getPss() {
    return this.mPss;
  }
  
  public int getRealUid() {
    return this.mRealUid;
  }
  
  public int getReason() {
    return this.mReason;
  }
  
  public long getRss() {
    return this.mRss;
  }
  
  public int getStatus() {
    return this.mStatus;
  }
  
  public int getSubReason() {
    return this.mSubReason;
  }
  
  public long getTimestamp() {
    return this.mTimestamp;
  }
  
  public File getTraceFile() {
    return this.mTraceFile;
  }
  
  public InputStream getTraceInputStream() throws IOException {
    IAppTraceRetriever iAppTraceRetriever = this.mAppTraceRetriever;
    if (iAppTraceRetriever == null)
      return null; 
    try {
      ParcelFileDescriptor parcelFileDescriptor = iAppTraceRetriever.getTraceFileDescriptor(this.mPackageName, this.mPackageUid, this.mPid);
      if (parcelFileDescriptor == null)
        return null; 
      ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream();
      this(parcelFileDescriptor);
      return new GZIPInputStream((InputStream)autoCloseInputStream);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public UserHandle getUserHandle() {
    return UserHandle.of(UserHandle.getUserId(this.mRealUid));
  }
  
  public int hashCode() {
    return ((((((((((((this.mPid * 31 + this.mRealUid) * 31 + this.mPackageUid) * 31 + this.mDefiningUid) * 31 + this.mConnectionGroup) * 31 + this.mReason) * 31 + this.mSubReason) * 31 + this.mImportance) * 31 + this.mStatus) * 31 + (int)this.mPss) * 31 + (int)this.mRss) * 31 + Long.hashCode(this.mTimestamp)) * 31 + Objects.hashCode(this.mProcessName)) * 31 + Objects.hashCode(this.mDescription);
  }
  
  public void readFromProto(ProtoInputStream paramProtoInputStream, long paramLong) throws IOException, WireTypeMismatchException {
    paramLong = paramProtoInputStream.start(paramLong);
    while (paramProtoInputStream.nextField() != -1) {
      String str;
      switch (paramProtoInputStream.getFieldNumber()) {
        default:
          continue;
        case 16:
          str = paramProtoInputStream.readString(1138166333456L);
          if (!TextUtils.isEmpty(str))
            this.mTraceFile = new File(str); 
          continue;
        case 15:
          this.mState = paramProtoInputStream.readBytes(1151051235343L);
          continue;
        case 14:
          this.mDescription = paramProtoInputStream.readString(1138166333454L);
          continue;
        case 13:
          this.mTimestamp = paramProtoInputStream.readLong(1112396529677L);
          continue;
        case 12:
          this.mRss = paramProtoInputStream.readLong(1112396529676L);
          continue;
        case 11:
          this.mPss = paramProtoInputStream.readLong(1112396529675L);
          continue;
        case 10:
          this.mImportance = paramProtoInputStream.readInt(1159641169930L);
          continue;
        case 9:
          this.mStatus = paramProtoInputStream.readInt(1120986464265L);
          continue;
        case 8:
          this.mSubReason = paramProtoInputStream.readInt(1159641169928L);
          continue;
        case 7:
          this.mReason = paramProtoInputStream.readInt(1159641169927L);
          continue;
        case 6:
          this.mConnectionGroup = paramProtoInputStream.readInt(1120986464262L);
          continue;
        case 5:
          this.mProcessName = paramProtoInputStream.readString(1138166333445L);
          continue;
        case 4:
          this.mDefiningUid = paramProtoInputStream.readInt(1120986464260L);
          continue;
        case 3:
          this.mPackageUid = paramProtoInputStream.readInt(1120986464259L);
          continue;
        case 2:
          this.mRealUid = paramProtoInputStream.readInt(1120986464258L);
          continue;
        case 1:
          break;
      } 
      this.mPid = paramProtoInputStream.readInt(1120986464257L);
    } 
    paramProtoInputStream.end(paramLong);
  }
  
  public void setAppTraceRetriever(IAppTraceRetriever paramIAppTraceRetriever) {
    this.mAppTraceRetriever = paramIAppTraceRetriever;
  }
  
  public void setConnectionGroup(int paramInt) {
    this.mConnectionGroup = paramInt;
  }
  
  public void setDefiningUid(int paramInt) {
    this.mDefiningUid = paramInt;
  }
  
  public void setDescription(String paramString) {
    this.mDescription = paramString;
  }
  
  public void setImportance(int paramInt) {
    this.mImportance = paramInt;
  }
  
  public void setPackageList(String[] paramArrayOfString) {
    this.mPackageList = paramArrayOfString;
  }
  
  public void setPackageName(String paramString) {
    this.mPackageName = paramString;
  }
  
  public void setPackageUid(int paramInt) {
    this.mPackageUid = paramInt;
  }
  
  public void setPid(int paramInt) {
    this.mPid = paramInt;
  }
  
  public void setProcessName(String paramString) {
    this.mProcessName = paramString;
  }
  
  public void setProcessStateSummary(byte[] paramArrayOfbyte) {
    this.mState = paramArrayOfbyte;
  }
  
  public void setPss(long paramLong) {
    this.mPss = paramLong;
  }
  
  public void setRealUid(int paramInt) {
    this.mRealUid = paramInt;
  }
  
  public void setReason(int paramInt) {
    this.mReason = paramInt;
  }
  
  public void setRss(long paramLong) {
    this.mRss = paramLong;
  }
  
  public void setStatus(int paramInt) {
    this.mStatus = paramInt;
  }
  
  public void setSubReason(int paramInt) {
    this.mSubReason = paramInt;
  }
  
  public void setTimestamp(long paramLong) {
    this.mTimestamp = paramLong;
  }
  
  public void setTraceFile(File paramFile) {
    this.mTraceFile = paramFile;
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ApplicationExitInfo(timestamp=");
    stringBuilder.append((new SimpleDateFormat()).format(new Date(this.mTimestamp)));
    stringBuilder.append(" pid=");
    stringBuilder.append(this.mPid);
    stringBuilder.append(" realUid=");
    stringBuilder.append(this.mRealUid);
    stringBuilder.append(" packageUid=");
    stringBuilder.append(this.mPackageUid);
    stringBuilder.append(" definingUid=");
    stringBuilder.append(this.mDefiningUid);
    stringBuilder.append(" user=");
    stringBuilder.append(UserHandle.getUserId(this.mPackageUid));
    stringBuilder.append(" process=");
    stringBuilder.append(this.mProcessName);
    stringBuilder.append(" reason=");
    stringBuilder.append(this.mReason);
    stringBuilder.append(" (");
    stringBuilder.append(reasonCodeToString(this.mReason));
    stringBuilder.append(")");
    stringBuilder.append(" status=");
    stringBuilder.append(this.mStatus);
    stringBuilder.append(" importance=");
    stringBuilder.append(this.mImportance);
    stringBuilder.append(" pss=");
    DebugUtils.sizeValueToString(this.mPss << 10L, stringBuilder);
    stringBuilder.append(" rss=");
    DebugUtils.sizeValueToString(this.mRss << 10L, stringBuilder);
    stringBuilder.append(" description=");
    stringBuilder.append(this.mDescription);
    stringBuilder.append(" state=");
    if (ArrayUtils.isEmpty(this.mState)) {
      str = "empty";
    } else {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(Integer.toString(this.mState.length));
      stringBuilder1.append(" bytes");
      str = stringBuilder1.toString();
    } 
    stringBuilder.append(str);
    stringBuilder.append(" trace=");
    stringBuilder.append(this.mTraceFile);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mPid);
    paramParcel.writeInt(this.mRealUid);
    paramParcel.writeInt(this.mPackageUid);
    paramParcel.writeInt(this.mDefiningUid);
    paramParcel.writeString(this.mProcessName);
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeInt(this.mConnectionGroup);
    paramParcel.writeInt(this.mReason);
    paramParcel.writeInt(this.mSubReason);
    paramParcel.writeInt(this.mStatus);
    paramParcel.writeInt(this.mImportance);
    paramParcel.writeLong(this.mPss);
    paramParcel.writeLong(this.mRss);
    paramParcel.writeLong(this.mTimestamp);
    paramParcel.writeString(this.mDescription);
    paramParcel.writeByteArray(this.mState);
    if (this.mAppTraceRetriever != null) {
      paramParcel.writeInt(1);
      paramParcel.writeStrongBinder(this.mAppTraceRetriever.asBinder());
    } else {
      paramParcel.writeInt(0);
    } 
  }
  
  public void writeToProto(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    String str;
    paramLong = paramProtoOutputStream.start(paramLong);
    paramProtoOutputStream.write(1120986464257L, this.mPid);
    paramProtoOutputStream.write(1120986464258L, this.mRealUid);
    paramProtoOutputStream.write(1120986464259L, this.mPackageUid);
    paramProtoOutputStream.write(1120986464260L, this.mDefiningUid);
    paramProtoOutputStream.write(1138166333445L, this.mProcessName);
    paramProtoOutputStream.write(1120986464262L, this.mConnectionGroup);
    paramProtoOutputStream.write(1159641169927L, this.mReason);
    paramProtoOutputStream.write(1159641169928L, this.mSubReason);
    paramProtoOutputStream.write(1120986464265L, this.mStatus);
    paramProtoOutputStream.write(1159641169930L, this.mImportance);
    paramProtoOutputStream.write(1112396529675L, this.mPss);
    paramProtoOutputStream.write(1112396529676L, this.mRss);
    paramProtoOutputStream.write(1112396529677L, this.mTimestamp);
    paramProtoOutputStream.write(1138166333454L, this.mDescription);
    paramProtoOutputStream.write(1151051235343L, this.mState);
    File file = this.mTraceFile;
    if (file == null) {
      file = null;
    } else {
      str = file.getAbsolutePath();
    } 
    paramProtoOutputStream.write(1138166333456L, str);
    paramProtoOutputStream.end(paramLong);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Reason {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SubReason {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationExitInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */