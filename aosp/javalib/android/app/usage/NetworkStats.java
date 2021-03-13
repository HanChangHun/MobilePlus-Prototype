package android.app.usage;

import android.content.Context;
import android.net.INetworkStatsService;
import android.net.INetworkStatsSession;
import android.net.NetworkStatsHistory;
import android.net.NetworkTemplate;
import android.os.RemoteException;
import android.util.IntArray;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class NetworkStats implements AutoCloseable {
  private static final String TAG = "NetworkStats";
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private final long mEndTimeStamp;
  
  private int mEnumerationIndex = 0;
  
  private NetworkStatsHistory mHistory = null;
  
  private NetworkStatsHistory.Entry mRecycledHistoryEntry = null;
  
  private android.net.NetworkStats.Entry mRecycledSummaryEntry = null;
  
  private INetworkStatsSession mSession;
  
  private final long mStartTimeStamp;
  
  private int mState = -1;
  
  private android.net.NetworkStats mSummary = null;
  
  private int mTag = 0;
  
  private NetworkTemplate mTemplate;
  
  private int mUidOrUidIndex;
  
  private int[] mUids;
  
  NetworkStats(Context paramContext, NetworkTemplate paramNetworkTemplate, int paramInt, long paramLong1, long paramLong2, INetworkStatsService paramINetworkStatsService) throws RemoteException, SecurityException {
    this.mSession = paramINetworkStatsService.openSessionForUsageStats(paramInt, paramContext.getOpPackageName());
    this.mCloseGuard.open("close");
    this.mTemplate = paramNetworkTemplate;
    this.mStartTimeStamp = paramLong1;
    this.mEndTimeStamp = paramLong2;
  }
  
  private void fillBucketFromSummaryEntry(Bucket paramBucket) {
    Bucket.access$102(paramBucket, Bucket.convertUid(this.mRecycledSummaryEntry.uid));
    Bucket.access$302(paramBucket, Bucket.convertTag(this.mRecycledSummaryEntry.tag));
    Bucket.access$502(paramBucket, Bucket.convertState(this.mRecycledSummaryEntry.set));
    Bucket.access$702(paramBucket, Bucket.convertDefaultNetworkStatus(this.mRecycledSummaryEntry.defaultNetwork));
    Bucket.access$902(paramBucket, Bucket.convertMetered(this.mRecycledSummaryEntry.metered));
    Bucket.access$1102(paramBucket, Bucket.convertRoaming(this.mRecycledSummaryEntry.roaming));
    Bucket.access$1302(paramBucket, this.mStartTimeStamp);
    Bucket.access$1402(paramBucket, this.mEndTimeStamp);
    Bucket.access$1502(paramBucket, this.mRecycledSummaryEntry.rxBytes);
    Bucket.access$1602(paramBucket, this.mRecycledSummaryEntry.rxPackets);
    Bucket.access$1702(paramBucket, this.mRecycledSummaryEntry.txBytes);
    Bucket.access$1802(paramBucket, this.mRecycledSummaryEntry.txPackets);
  }
  
  private boolean getNextHistoryBucket(Bucket paramBucket) {
    if (paramBucket != null) {
      NetworkStatsHistory networkStatsHistory = this.mHistory;
      if (networkStatsHistory != null) {
        if (this.mEnumerationIndex < networkStatsHistory.size()) {
          networkStatsHistory = this.mHistory;
          int i = this.mEnumerationIndex;
          this.mEnumerationIndex = i + 1;
          this.mRecycledHistoryEntry = networkStatsHistory.getValues(i, this.mRecycledHistoryEntry);
          Bucket.access$102(paramBucket, Bucket.convertUid(getUid()));
          Bucket.access$302(paramBucket, Bucket.convertTag(this.mTag));
          Bucket.access$502(paramBucket, this.mState);
          Bucket.access$702(paramBucket, -1);
          Bucket.access$902(paramBucket, -1);
          Bucket.access$1102(paramBucket, -1);
          Bucket.access$1302(paramBucket, this.mRecycledHistoryEntry.bucketStart);
          Bucket.access$1402(paramBucket, this.mRecycledHistoryEntry.bucketStart + this.mRecycledHistoryEntry.bucketDuration);
          Bucket.access$1502(paramBucket, this.mRecycledHistoryEntry.rxBytes);
          Bucket.access$1602(paramBucket, this.mRecycledHistoryEntry.rxPackets);
          Bucket.access$1702(paramBucket, this.mRecycledHistoryEntry.txBytes);
          Bucket.access$1802(paramBucket, this.mRecycledHistoryEntry.txPackets);
          return true;
        } 
        if (hasNextUid()) {
          stepHistory();
          return getNextHistoryBucket(paramBucket);
        } 
      } 
    } 
    return false;
  }
  
  private boolean getNextSummaryBucket(Bucket paramBucket) {
    if (paramBucket != null && this.mEnumerationIndex < this.mSummary.size()) {
      android.net.NetworkStats networkStats = this.mSummary;
      int i = this.mEnumerationIndex;
      this.mEnumerationIndex = i + 1;
      this.mRecycledSummaryEntry = networkStats.getValues(i, this.mRecycledSummaryEntry);
      fillBucketFromSummaryEntry(paramBucket);
      return true;
    } 
    return false;
  }
  
  private int getUid() {
    if (isUidEnumeration()) {
      int i = this.mUidOrUidIndex;
      if (i >= 0) {
        int[] arrayOfInt = this.mUids;
        if (i < arrayOfInt.length)
          return arrayOfInt[i]; 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Index=");
      stringBuilder.append(this.mUidOrUidIndex);
      stringBuilder.append(" mUids.length=");
      stringBuilder.append(this.mUids.length);
      throw new IndexOutOfBoundsException(stringBuilder.toString());
    } 
    return this.mUidOrUidIndex;
  }
  
  private boolean hasNextUid() {
    boolean bool = isUidEnumeration();
    boolean bool1 = true;
    if (!bool || this.mUidOrUidIndex + 1 >= this.mUids.length)
      bool1 = false; 
    return bool1;
  }
  
  private boolean isUidEnumeration() {
    boolean bool;
    if (this.mUids != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void setSingleUidTagState(int paramInt1, int paramInt2, int paramInt3) {
    this.mUidOrUidIndex = paramInt1;
    this.mTag = paramInt2;
    this.mState = paramInt3;
  }
  
  private void stepHistory() {
    if (hasNextUid()) {
      stepUid();
      this.mHistory = null;
      try {
        this.mHistory = this.mSession.getHistoryIntervalForUid(this.mTemplate, getUid(), -1, 0, -1, this.mStartTimeStamp, this.mEndTimeStamp);
      } catch (RemoteException remoteException) {
        Log.w("NetworkStats", (Throwable)remoteException);
      } 
      this.mEnumerationIndex = 0;
    } 
  }
  
  private void stepUid() {
    if (this.mUids != null)
      this.mUidOrUidIndex++; 
  }
  
  public void close() {
    INetworkStatsSession iNetworkStatsSession = this.mSession;
    if (iNetworkStatsSession != null)
      try {
        iNetworkStatsSession.close();
      } catch (RemoteException remoteException) {
        Log.w("NetworkStats", (Throwable)remoteException);
      }  
    this.mSession = null;
    CloseGuard closeGuard = this.mCloseGuard;
    if (closeGuard != null)
      closeGuard.close(); 
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mCloseGuard != null)
        this.mCloseGuard.warnIfOpen(); 
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  Bucket getDeviceSummaryForNetwork() throws RemoteException {
    android.net.NetworkStats networkStats = this.mSession.getDeviceSummaryForNetwork(this.mTemplate, this.mStartTimeStamp, this.mEndTimeStamp);
    this.mSummary = networkStats;
    this.mEnumerationIndex = networkStats.size();
    return getSummaryAggregate();
  }
  
  public boolean getNextBucket(Bucket paramBucket) {
    return (this.mSummary != null) ? getNextSummaryBucket(paramBucket) : getNextHistoryBucket(paramBucket);
  }
  
  Bucket getSummaryAggregate() {
    if (this.mSummary == null)
      return null; 
    Bucket bucket = new Bucket();
    if (this.mRecycledSummaryEntry == null)
      this.mRecycledSummaryEntry = new android.net.NetworkStats.Entry(); 
    this.mSummary.getTotal(this.mRecycledSummaryEntry);
    fillBucketFromSummaryEntry(bucket);
    return bucket;
  }
  
  public boolean hasNextBucket() {
    android.net.NetworkStats networkStats = this.mSummary;
    boolean bool1 = true;
    boolean bool2 = true;
    if (networkStats != null) {
      if (this.mEnumerationIndex >= networkStats.size())
        bool2 = false; 
      return bool2;
    } 
    NetworkStatsHistory networkStatsHistory = this.mHistory;
    return (networkStatsHistory != null) ? ((this.mEnumerationIndex < networkStatsHistory.size() || hasNextUid()) ? bool1 : false) : false;
  }
  
  void startHistoryEnumeration(int paramInt1, int paramInt2, int paramInt3) {
    this.mHistory = null;
    try {
      this.mHistory = this.mSession.getHistoryIntervalForUid(this.mTemplate, paramInt1, Bucket.convertSet(paramInt3), paramInt2, -1, this.mStartTimeStamp, this.mEndTimeStamp);
      setSingleUidTagState(paramInt1, paramInt2, paramInt3);
    } catch (RemoteException remoteException) {
      Log.w("NetworkStats", (Throwable)remoteException);
    } 
    this.mEnumerationIndex = 0;
  }
  
  void startSummaryEnumeration() throws RemoteException {
    this.mSummary = this.mSession.getSummaryForAllUid(this.mTemplate, this.mStartTimeStamp, this.mEndTimeStamp, false);
    this.mEnumerationIndex = 0;
  }
  
  void startUserUidEnumeration() throws RemoteException {
    int[] arrayOfInt = this.mSession.getRelevantUids();
    IntArray intArray = new IntArray(arrayOfInt.length);
    int i = arrayOfInt.length;
    for (byte b = 0; b < i; b++) {
      int j = arrayOfInt[b];
      try {
        NetworkStatsHistory networkStatsHistory = this.mSession.getHistoryIntervalForUid(this.mTemplate, j, -1, 0, -1, this.mStartTimeStamp, this.mEndTimeStamp);
        if (networkStatsHistory != null && networkStatsHistory.size() > 0)
          intArray.add(j); 
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error while getting history of uid ");
        stringBuilder.append(j);
        Log.w("NetworkStats", stringBuilder.toString(), (Throwable)remoteException);
      } 
    } 
    this.mUids = intArray.toArray();
    this.mUidOrUidIndex = -1;
    stepHistory();
  }
  
  public static class Bucket {
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
    
    private static int convertDefaultNetworkStatus(int param1Int) {
      return (param1Int != -1) ? ((param1Int != 0) ? ((param1Int != 1) ? 0 : 2) : 1) : -1;
    }
    
    private static int convertMetered(int param1Int) {
      return (param1Int != -1) ? ((param1Int != 0) ? ((param1Int != 1) ? 0 : 2) : 1) : -1;
    }
    
    private static int convertRoaming(int param1Int) {
      return (param1Int != -1) ? ((param1Int != 0) ? ((param1Int != 1) ? 0 : 2) : 1) : -1;
    }
    
    private static int convertSet(int param1Int) {
      return (param1Int != -1) ? ((param1Int != 2) ? 0 : 1) : -1;
    }
    
    private static int convertState(int param1Int) {
      return (param1Int != -1) ? ((param1Int != 0) ? ((param1Int != 1) ? 0 : 2) : 1) : -1;
    }
    
    private static int convertTag(int param1Int) {
      return (param1Int != 0) ? param1Int : 0;
    }
    
    private static int convertUid(int param1Int) {
      return (param1Int != -5) ? ((param1Int != -4) ? param1Int : -4) : -5;
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
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DefaultNetworkStatus {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Metered {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Roaming {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface State {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/NetworkStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */