package android.app.usage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Bucket {
  public static final int DEFAULT_NETWORK_ALL = -1;
  
  public static final int DEFAULT_NETWORK_NO = 1;
  
  public static final int DEFAULT_NETWORK_YES = 2;
  
  public static final int METERED_ALL = -1;
  
  public static final int METERED_NO = 1;
  
  public static final int METERED_YES = 2;
  
  public static final int ROAMING_ALL = -1;
  
  public static final int ROAMING_NO = 1;
  
  public static final int ROAMING_YES = 2;
  
  public static final int STATE_ALL = -1;
  
  public static final int STATE_DEFAULT = 1;
  
  public static final int STATE_FOREGROUND = 2;
  
  public static final int TAG_NONE = 0;
  
  public static final int UID_ALL = -1;
  
  public static final int UID_REMOVED = -4;
  
  public static final int UID_TETHERING = -5;
  
  private long mBeginTimeStamp;
  
  private int mDefaultNetworkStatus;
  
  private long mEndTimeStamp;
  
  private int mMetered;
  
  private int mRoaming;
  
  private long mRxBytes;
  
  private long mRxPackets;
  
  private int mState;
  
  private int mTag;
  
  private long mTxBytes;
  
  private long mTxPackets;
  
  private int mUid;
  
  private static int convertDefaultNetworkStatus(int paramInt) {
    return (paramInt != -1) ? ((paramInt != 0) ? ((paramInt != 1) ? 0 : 2) : 1) : -1;
  }
  
  private static int convertMetered(int paramInt) {
    return (paramInt != -1) ? ((paramInt != 0) ? ((paramInt != 1) ? 0 : 2) : 1) : -1;
  }
  
  private static int convertRoaming(int paramInt) {
    return (paramInt != -1) ? ((paramInt != 0) ? ((paramInt != 1) ? 0 : 2) : 1) : -1;
  }
  
  private static int convertSet(int paramInt) {
    return (paramInt != -1) ? ((paramInt != 2) ? 0 : 1) : -1;
  }
  
  private static int convertState(int paramInt) {
    return (paramInt != -1) ? ((paramInt != 0) ? ((paramInt != 1) ? 0 : 2) : 1) : -1;
  }
  
  private static int convertTag(int paramInt) {
    return (paramInt != 0) ? paramInt : 0;
  }
  
  private static int convertUid(int paramInt) {
    return (paramInt != -5) ? ((paramInt != -4) ? paramInt : -4) : -5;
  }
  
  public int getDefaultNetworkStatus() {
    return this.mDefaultNetworkStatus;
  }
  
  public long getEndTimeStamp() {
    return this.mEndTimeStamp;
  }
  
  public int getMetered() {
    return this.mMetered;
  }
  
  public int getRoaming() {
    return this.mRoaming;
  }
  
  public long getRxBytes() {
    return this.mRxBytes;
  }
  
  public long getRxPackets() {
    return this.mRxPackets;
  }
  
  public long getStartTimeStamp() {
    return this.mBeginTimeStamp;
  }
  
  public int getState() {
    return this.mState;
  }
  
  public int getTag() {
    return this.mTag;
  }
  
  public long getTxBytes() {
    return this.mTxBytes;
  }
  
  public long getTxPackets() {
    return this.mTxPackets;
  }
  
  public int getUid() {
    return this.mUid;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DefaultNetworkStatus {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Metered {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Roaming {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface State {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/NetworkStats$Bucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */