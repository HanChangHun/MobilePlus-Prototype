package android.accounts;

import android.os.Parcel;
import android.os.Parcelable;

public class AuthenticatorDescription implements Parcelable {
  public static final Parcelable.Creator<AuthenticatorDescription> CREATOR = new Parcelable.Creator<AuthenticatorDescription>() {
      public AuthenticatorDescription createFromParcel(Parcel param1Parcel) {
        return new AuthenticatorDescription(param1Parcel);
      }
      
      public AuthenticatorDescription[] newArray(int param1Int) {
        return new AuthenticatorDescription[param1Int];
      }
    };
  
  public final int accountPreferencesId;
  
  public final boolean customTokens;
  
  public final int iconId;
  
  public final int labelId;
  
  public final String packageName;
  
  public final int smallIconId;
  
  public final String type;
  
  private AuthenticatorDescription(Parcel paramParcel) {
    this.type = paramParcel.readString();
    this.packageName = paramParcel.readString();
    this.labelId = paramParcel.readInt();
    this.iconId = paramParcel.readInt();
    this.smallIconId = paramParcel.readInt();
    this.accountPreferencesId = paramParcel.readInt();
    byte b = paramParcel.readByte();
    boolean bool = true;
    if (b != 1)
      bool = false; 
    this.customTokens = bool;
  }
  
  private AuthenticatorDescription(String paramString) {
    this.type = paramString;
    this.packageName = null;
    this.labelId = 0;
    this.iconId = 0;
    this.smallIconId = 0;
    this.accountPreferencesId = 0;
    this.customTokens = false;
  }
  
  public AuthenticatorDescription(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this(paramString1, paramString2, paramInt1, paramInt2, paramInt3, paramInt4, false);
  }
  
  public AuthenticatorDescription(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
    if (paramString1 != null) {
      if (paramString2 != null) {
        this.type = paramString1;
        this.packageName = paramString2;
        this.labelId = paramInt1;
        this.iconId = paramInt2;
        this.smallIconId = paramInt3;
        this.accountPreferencesId = paramInt4;
        this.customTokens = paramBoolean;
        return;
      } 
      throw new IllegalArgumentException("packageName cannot be null");
    } 
    throw new IllegalArgumentException("type cannot be null");
  }
  
  public static AuthenticatorDescription newKey(String paramString) {
    if (paramString != null)
      return new AuthenticatorDescription(paramString); 
    throw new IllegalArgumentException("type cannot be null");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof AuthenticatorDescription))
      return false; 
    paramObject = paramObject;
    return this.type.equals(((AuthenticatorDescription)paramObject).type);
  }
  
  public int hashCode() {
    return this.type.hashCode();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AuthenticatorDescription {type=");
    stringBuilder.append(this.type);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.type);
    paramParcel.writeString(this.packageName);
    paramParcel.writeInt(this.labelId);
    paramParcel.writeInt(this.iconId);
    paramParcel.writeInt(this.smallIconId);
    paramParcel.writeInt(this.accountPreferencesId);
    paramParcel.writeByte((byte)this.customTokens);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AuthenticatorDescription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */