package android.content.pm;

import android.annotation.NonNull;
import android.annotation.SystemApi;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import com.android.internal.util.AnnotationValidations;

@SystemApi
public final class InstantAppRequestInfo implements Parcelable {
  public static final Parcelable.Creator<InstantAppRequestInfo> CREATOR = new Parcelable.Creator<InstantAppRequestInfo>() {
      public InstantAppRequestInfo createFromParcel(Parcel param1Parcel) {
        return new InstantAppRequestInfo(param1Parcel);
      }
      
      public InstantAppRequestInfo[] newArray(int param1Int) {
        return new InstantAppRequestInfo[param1Int];
      }
    };
  
  private final int[] mHostDigestPrefix;
  
  private final Intent mIntent;
  
  private final boolean mRequesterInstantApp;
  
  private final String mToken;
  
  private final UserHandle mUserHandle;
  
  public InstantAppRequestInfo(Intent paramIntent, int[] paramArrayOfint, UserHandle paramUserHandle, boolean paramBoolean, String paramString) {
    this.mIntent = paramIntent;
    AnnotationValidations.validate(NonNull.class, null, paramIntent);
    this.mHostDigestPrefix = paramArrayOfint;
    this.mUserHandle = paramUserHandle;
    AnnotationValidations.validate(NonNull.class, null, paramUserHandle);
    this.mRequesterInstantApp = paramBoolean;
    this.mToken = paramString;
    AnnotationValidations.validate(NonNull.class, null, paramString);
  }
  
  InstantAppRequestInfo(Parcel paramParcel) {
    boolean bool;
    int[] arrayOfInt;
    byte b = paramParcel.readByte();
    if ((b & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Intent intent = (Intent)paramParcel.readTypedObject(Intent.CREATOR);
    if ((b & 0x2) == 0) {
      arrayOfInt = null;
    } else {
      arrayOfInt = paramParcel.createIntArray();
    } 
    UserHandle userHandle = (UserHandle)paramParcel.readTypedObject(UserHandle.CREATOR);
    String str = paramParcel.readString();
    this.mIntent = intent;
    AnnotationValidations.validate(NonNull.class, null, intent);
    this.mHostDigestPrefix = arrayOfInt;
    this.mUserHandle = userHandle;
    AnnotationValidations.validate(NonNull.class, null, userHandle);
    this.mRequesterInstantApp = bool;
    this.mToken = str;
    AnnotationValidations.validate(NonNull.class, null, str);
  }
  
  @Deprecated
  private void __metadata() {}
  
  public int describeContents() {
    return 0;
  }
  
  public int[] getHostDigestPrefix() {
    return this.mHostDigestPrefix;
  }
  
  public Intent getIntent() {
    return this.mIntent;
  }
  
  public String getToken() {
    return this.mToken;
  }
  
  public UserHandle getUserHandle() {
    return this.mUserHandle;
  }
  
  public boolean isRequesterInstantApp() {
    return this.mRequesterInstantApp;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    byte b1 = 0;
    if (this.mRequesterInstantApp)
      b1 = (byte)(0x0 | 0x8); 
    byte b2 = b1;
    if (this.mHostDigestPrefix != null) {
      b1 = (byte)(b1 | 0x2);
      b2 = b1;
    } 
    paramParcel.writeByte(b2);
    paramParcel.writeTypedObject((Parcelable)this.mIntent, paramInt);
    int[] arrayOfInt = this.mHostDigestPrefix;
    if (arrayOfInt != null)
      paramParcel.writeIntArray(arrayOfInt); 
    paramParcel.writeTypedObject((Parcelable)this.mUserHandle, paramInt);
    paramParcel.writeString(this.mToken);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstantAppRequestInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */