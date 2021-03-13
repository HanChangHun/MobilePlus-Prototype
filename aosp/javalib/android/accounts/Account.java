package android.accounts;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import java.util.Set;

public class Account implements Parcelable {
  public static final Parcelable.Creator<Account> CREATOR;
  
  private static final String TAG = "Account";
  
  private static final Set<Account> sAccessedAccounts = (Set<Account>)new ArraySet();
  
  private final String accessId;
  
  private String mSafeName;
  
  public final String name;
  
  public final String type;
  
  static {
    CREATOR = new Parcelable.Creator<Account>() {
        public Account createFromParcel(Parcel param1Parcel) {
          return new Account(param1Parcel);
        }
        
        public Account[] newArray(int param1Int) {
          return new Account[param1Int];
        }
      };
  }
  
  public Account(Account paramAccount, String paramString) {
    this(paramAccount.name, paramAccount.type, paramString);
  }
  
  public Account(Parcel paramParcel) {
    this.name = paramParcel.readString();
    this.type = paramParcel.readString();
    if (!TextUtils.isEmpty(this.name)) {
      if (!TextUtils.isEmpty(this.type)) {
        String str = paramParcel.readString();
        this.accessId = str;
        if (str != null)
          synchronized (sAccessedAccounts) {
            boolean bool = sAccessedAccounts.add(this);
            if (bool)
              try {
                IAccountManager.Stub.asInterface(ServiceManager.getService("account")).onAccountAccessed(this.accessId);
              } catch (RemoteException remoteException) {
                Log.e("Account", "Error noting account access", (Throwable)remoteException);
              }  
          }  
        return;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("the type must not be empty: ");
      stringBuilder1.append(this.type);
      throw new BadParcelableException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("the name must not be empty: ");
    stringBuilder.append(this.name);
    throw new BadParcelableException(stringBuilder.toString());
  }
  
  public Account(String paramString1, String paramString2) {
    this(paramString1, paramString2, null);
  }
  
  public Account(String paramString1, String paramString2, String paramString3) {
    StringBuilder stringBuilder1;
    if (!TextUtils.isEmpty(paramString1)) {
      if (!TextUtils.isEmpty(paramString2)) {
        this.name = paramString1;
        this.type = paramString2;
        this.accessId = paramString3;
        return;
      } 
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("the type must not be empty: ");
      stringBuilder1.append(paramString2);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("the name must not be empty: ");
    stringBuilder2.append((String)stringBuilder1);
    throw new IllegalArgumentException(stringBuilder2.toString());
  }
  
  public static String toSafeName(String paramString, char paramChar) {
    StringBuilder stringBuilder = new StringBuilder(64);
    int i = paramString.length();
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      if (Character.isLetterOrDigit(c)) {
        stringBuilder.append(paramChar);
      } else {
        stringBuilder.append(c);
      } 
    } 
    return stringBuilder.toString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof Account))
      return false; 
    paramObject = paramObject;
    if (!this.name.equals(((Account)paramObject).name) || !this.type.equals(((Account)paramObject).type))
      bool = false; 
    return bool;
  }
  
  public String getAccessId() {
    return this.accessId;
  }
  
  public int hashCode() {
    return (17 * 31 + this.name.hashCode()) * 31 + this.type.hashCode();
  }
  
  public String toSafeString() {
    if (this.mSafeName == null)
      this.mSafeName = toSafeName(this.name, 'x'); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Account {name=");
    stringBuilder.append(this.mSafeName);
    stringBuilder.append(", type=");
    stringBuilder.append(this.type);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Account {name=");
    stringBuilder.append(this.name);
    stringBuilder.append(", type=");
    stringBuilder.append(this.type);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.name);
    paramParcel.writeString(this.type);
    paramParcel.writeString(this.accessId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/Account.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */