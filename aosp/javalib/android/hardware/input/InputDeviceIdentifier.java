package android.hardware.input;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Objects;

public final class InputDeviceIdentifier implements Parcelable {
  public static final Parcelable.Creator<InputDeviceIdentifier> CREATOR = new Parcelable.Creator<InputDeviceIdentifier>() {
      public InputDeviceIdentifier createFromParcel(Parcel param1Parcel) {
        return new InputDeviceIdentifier(param1Parcel);
      }
      
      public InputDeviceIdentifier[] newArray(int param1Int) {
        return new InputDeviceIdentifier[param1Int];
      }
    };
  
  private final String mDescriptor;
  
  private final int mProductId;
  
  private final int mVendorId;
  
  private InputDeviceIdentifier(Parcel paramParcel) {
    this.mDescriptor = paramParcel.readString();
    this.mVendorId = paramParcel.readInt();
    this.mProductId = paramParcel.readInt();
  }
  
  public InputDeviceIdentifier(String paramString, int paramInt1, int paramInt2) {
    this.mDescriptor = paramString;
    this.mVendorId = paramInt1;
    this.mProductId = paramInt2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || !(paramObject instanceof InputDeviceIdentifier))
      return false; 
    paramObject = paramObject;
    if (this.mVendorId != ((InputDeviceIdentifier)paramObject).mVendorId || this.mProductId != ((InputDeviceIdentifier)paramObject).mProductId || !TextUtils.equals(this.mDescriptor, ((InputDeviceIdentifier)paramObject).mDescriptor))
      bool = false; 
    return bool;
  }
  
  public String getDescriptor() {
    return this.mDescriptor;
  }
  
  public int getProductId() {
    return this.mProductId;
  }
  
  public int getVendorId() {
    return this.mVendorId;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mDescriptor, Integer.valueOf(this.mVendorId), Integer.valueOf(this.mProductId) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mDescriptor);
    paramParcel.writeInt(this.mVendorId);
    paramParcel.writeInt(this.mProductId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/InputDeviceIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */