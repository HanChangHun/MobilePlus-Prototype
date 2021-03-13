package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class SyncAdapterType implements Parcelable {
  public static final Parcelable.Creator<SyncAdapterType> CREATOR = new Parcelable.Creator<SyncAdapterType>() {
      public SyncAdapterType createFromParcel(Parcel param1Parcel) {
        return new SyncAdapterType(param1Parcel);
      }
      
      public SyncAdapterType[] newArray(int param1Int) {
        return new SyncAdapterType[param1Int];
      }
    };
  
  public final String accountType;
  
  private final boolean allowParallelSyncs;
  
  public final String authority;
  
  private final boolean isAlwaysSyncable;
  
  public final boolean isKey;
  
  private final String packageName;
  
  private final String settingsActivity;
  
  private final boolean supportsUploading;
  
  private final boolean userVisible;
  
  public SyncAdapterType(Parcel paramParcel) {
    this(str1, str2, bool1, bool2, bool3, bool4, paramParcel.readString(), paramParcel.readString());
  }
  
  private SyncAdapterType(String paramString1, String paramString2) {
    StringBuilder stringBuilder1;
    if (!TextUtils.isEmpty(paramString1)) {
      if (!TextUtils.isEmpty(paramString2)) {
        this.authority = paramString1;
        this.accountType = paramString2;
        this.userVisible = true;
        this.supportsUploading = true;
        this.isAlwaysSyncable = false;
        this.allowParallelSyncs = false;
        this.settingsActivity = null;
        this.isKey = true;
        this.packageName = null;
        return;
      } 
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("the accountType must not be empty: ");
      stringBuilder1.append(paramString2);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("the authority must not be empty: ");
    stringBuilder2.append((String)stringBuilder1);
    throw new IllegalArgumentException(stringBuilder2.toString());
  }
  
  public SyncAdapterType(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2) {
    StringBuilder stringBuilder1;
    if (!TextUtils.isEmpty(paramString1)) {
      if (!TextUtils.isEmpty(paramString2)) {
        this.authority = paramString1;
        this.accountType = paramString2;
        this.userVisible = paramBoolean1;
        this.supportsUploading = paramBoolean2;
        this.isAlwaysSyncable = false;
        this.allowParallelSyncs = false;
        this.settingsActivity = null;
        this.isKey = false;
        this.packageName = null;
        return;
      } 
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("the accountType must not be empty: ");
      stringBuilder1.append(paramString2);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("the authority must not be empty: ");
    stringBuilder2.append((String)stringBuilder1);
    throw new IllegalArgumentException(stringBuilder2.toString());
  }
  
  public SyncAdapterType(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, String paramString3, String paramString4) {
    StringBuilder stringBuilder1;
    if (!TextUtils.isEmpty(paramString1)) {
      if (!TextUtils.isEmpty(paramString2)) {
        this.authority = paramString1;
        this.accountType = paramString2;
        this.userVisible = paramBoolean1;
        this.supportsUploading = paramBoolean2;
        this.isAlwaysSyncable = paramBoolean3;
        this.allowParallelSyncs = paramBoolean4;
        this.settingsActivity = paramString3;
        this.isKey = false;
        this.packageName = paramString4;
        return;
      } 
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("the accountType must not be empty: ");
      stringBuilder1.append(paramString2);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("the authority must not be empty: ");
    stringBuilder2.append((String)stringBuilder1);
    throw new IllegalArgumentException(stringBuilder2.toString());
  }
  
  public static SyncAdapterType newKey(String paramString1, String paramString2) {
    return new SyncAdapterType(paramString1, paramString2);
  }
  
  public boolean allowParallelSyncs() {
    if (!this.isKey)
      return this.allowParallelSyncs; 
    throw new IllegalStateException("this method is not allowed to be called when this is a key");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof SyncAdapterType))
      return false; 
    paramObject = paramObject;
    if (!this.authority.equals(((SyncAdapterType)paramObject).authority) || !this.accountType.equals(((SyncAdapterType)paramObject).accountType))
      bool = false; 
    return bool;
  }
  
  public String getPackageName() {
    return this.packageName;
  }
  
  public String getSettingsActivity() {
    if (!this.isKey)
      return this.settingsActivity; 
    throw new IllegalStateException("this method is not allowed to be called when this is a key");
  }
  
  public int hashCode() {
    return (17 * 31 + this.authority.hashCode()) * 31 + this.accountType.hashCode();
  }
  
  public boolean isAlwaysSyncable() {
    if (!this.isKey)
      return this.isAlwaysSyncable; 
    throw new IllegalStateException("this method is not allowed to be called when this is a key");
  }
  
  public boolean isUserVisible() {
    if (!this.isKey)
      return this.userVisible; 
    throw new IllegalStateException("this method is not allowed to be called when this is a key");
  }
  
  public boolean supportsUploading() {
    if (!this.isKey)
      return this.supportsUploading; 
    throw new IllegalStateException("this method is not allowed to be called when this is a key");
  }
  
  public String toString() {
    if (this.isKey) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("SyncAdapterType Key {name=");
      stringBuilder1.append(this.authority);
      stringBuilder1.append(", type=");
      stringBuilder1.append(this.accountType);
      stringBuilder1.append("}");
      return stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SyncAdapterType {name=");
    stringBuilder.append(this.authority);
    stringBuilder.append(", type=");
    stringBuilder.append(this.accountType);
    stringBuilder.append(", userVisible=");
    stringBuilder.append(this.userVisible);
    stringBuilder.append(", supportsUploading=");
    stringBuilder.append(this.supportsUploading);
    stringBuilder.append(", isAlwaysSyncable=");
    stringBuilder.append(this.isAlwaysSyncable);
    stringBuilder.append(", allowParallelSyncs=");
    stringBuilder.append(this.allowParallelSyncs);
    stringBuilder.append(", settingsActivity=");
    stringBuilder.append(this.settingsActivity);
    stringBuilder.append(", packageName=");
    stringBuilder.append(this.packageName);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (!this.isKey) {
      paramParcel.writeString(this.authority);
      paramParcel.writeString(this.accountType);
      paramParcel.writeInt(this.userVisible);
      paramParcel.writeInt(this.supportsUploading);
      paramParcel.writeInt(this.isAlwaysSyncable);
      paramParcel.writeInt(this.allowParallelSyncs);
      paramParcel.writeString(this.settingsActivity);
      paramParcel.writeString(this.packageName);
      return;
    } 
    throw new IllegalStateException("keys aren't parcelable");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncAdapterType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */