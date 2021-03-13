package android.content;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;

public class SyncInfo implements Parcelable {
  public static final Parcelable.Creator<SyncInfo> CREATOR;
  
  private static final Account REDACTED_ACCOUNT = new Account("*****", "*****");
  
  public final Account account;
  
  public final String authority;
  
  public final int authorityId;
  
  public final long startTime;
  
  static {
    CREATOR = new Parcelable.Creator<SyncInfo>() {
        public SyncInfo createFromParcel(Parcel param1Parcel) {
          return new SyncInfo(param1Parcel);
        }
        
        public SyncInfo[] newArray(int param1Int) {
          return new SyncInfo[param1Int];
        }
      };
  }
  
  public SyncInfo(int paramInt, Account paramAccount, String paramString, long paramLong) {
    this.authorityId = paramInt;
    this.account = paramAccount;
    this.authority = paramString;
    this.startTime = paramLong;
  }
  
  public SyncInfo(SyncInfo paramSyncInfo) {
    this.authorityId = paramSyncInfo.authorityId;
    this.account = new Account(paramSyncInfo.account.name, paramSyncInfo.account.type);
    this.authority = paramSyncInfo.authority;
    this.startTime = paramSyncInfo.startTime;
  }
  
  SyncInfo(Parcel paramParcel) {
    this.authorityId = paramParcel.readInt();
    this.account = (Account)paramParcel.readParcelable(Account.class.getClassLoader());
    this.authority = paramParcel.readString();
    this.startTime = paramParcel.readLong();
  }
  
  public static SyncInfo createAccountRedacted(int paramInt, String paramString, long paramLong) {
    return new SyncInfo(paramInt, REDACTED_ACCOUNT, paramString, paramLong);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.authorityId);
    paramParcel.writeParcelable((Parcelable)this.account, paramInt);
    paramParcel.writeString(this.authority);
    paramParcel.writeLong(this.startTime);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */