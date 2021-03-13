package android.content;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public class PeriodicSync implements Parcelable {
  public static final Parcelable.Creator<PeriodicSync> CREATOR = new Parcelable.Creator<PeriodicSync>() {
      public PeriodicSync createFromParcel(Parcel param1Parcel) {
        return new PeriodicSync(param1Parcel);
      }
      
      public PeriodicSync[] newArray(int param1Int) {
        return new PeriodicSync[param1Int];
      }
    };
  
  public final Account account;
  
  public final String authority;
  
  public final Bundle extras;
  
  public final long flexTime;
  
  public final long period;
  
  public PeriodicSync(Account paramAccount, String paramString, Bundle paramBundle, long paramLong) {
    this.account = paramAccount;
    this.authority = paramString;
    if (paramBundle == null) {
      this.extras = new Bundle();
    } else {
      this.extras = new Bundle(paramBundle);
    } 
    this.period = paramLong;
    this.flexTime = 0L;
  }
  
  public PeriodicSync(Account paramAccount, String paramString, Bundle paramBundle, long paramLong1, long paramLong2) {
    this.account = paramAccount;
    this.authority = paramString;
    this.extras = new Bundle(paramBundle);
    this.period = paramLong1;
    this.flexTime = paramLong2;
  }
  
  public PeriodicSync(PeriodicSync paramPeriodicSync) {
    this.account = paramPeriodicSync.account;
    this.authority = paramPeriodicSync.authority;
    this.extras = new Bundle(paramPeriodicSync.extras);
    this.period = paramPeriodicSync.period;
    this.flexTime = paramPeriodicSync.flexTime;
  }
  
  private PeriodicSync(Parcel paramParcel) {
    this.account = (Account)paramParcel.readParcelable(null);
    this.authority = paramParcel.readString();
    this.extras = paramParcel.readBundle();
    this.period = paramParcel.readLong();
    this.flexTime = paramParcel.readLong();
  }
  
  public static boolean syncExtrasEquals(Bundle paramBundle1, Bundle paramBundle2) {
    if (paramBundle1.size() != paramBundle2.size())
      return false; 
    if (paramBundle1.isEmpty())
      return true; 
    for (String str : paramBundle1.keySet()) {
      if (!paramBundle2.containsKey(str))
        return false; 
      if (!Objects.equals(paramBundle1.get(str), paramBundle2.get(str)))
        return false; 
    } 
    return true;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof PeriodicSync))
      return false; 
    paramObject = paramObject;
    if (!this.account.equals(((PeriodicSync)paramObject).account) || !this.authority.equals(((PeriodicSync)paramObject).authority) || this.period != ((PeriodicSync)paramObject).period || !syncExtrasEquals(this.extras, ((PeriodicSync)paramObject).extras))
      bool = false; 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("account: ");
    stringBuilder.append(this.account);
    stringBuilder.append(", authority: ");
    stringBuilder.append(this.authority);
    stringBuilder.append(". period: ");
    stringBuilder.append(this.period);
    stringBuilder.append("s , flex: ");
    stringBuilder.append(this.flexTime);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeParcelable((Parcelable)this.account, paramInt);
    paramParcel.writeString(this.authority);
    paramParcel.writeBundle(this.extras);
    paramParcel.writeLong(this.period);
    paramParcel.writeLong(this.flexTime);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/PeriodicSync.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */