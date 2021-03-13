package android.hardware.input;

import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;

public final class KeyboardLayout implements Parcelable, Comparable<KeyboardLayout> {
  public static final Parcelable.Creator<KeyboardLayout> CREATOR = new Parcelable.Creator<KeyboardLayout>() {
      public KeyboardLayout createFromParcel(Parcel param1Parcel) {
        return new KeyboardLayout(param1Parcel);
      }
      
      public KeyboardLayout[] newArray(int param1Int) {
        return new KeyboardLayout[param1Int];
      }
    };
  
  private final String mCollection;
  
  private final String mDescriptor;
  
  private final String mLabel;
  
  private final LocaleList mLocales;
  
  private final int mPriority;
  
  private final int mProductId;
  
  private final int mVendorId;
  
  private KeyboardLayout(Parcel paramParcel) {
    this.mDescriptor = paramParcel.readString();
    this.mLabel = paramParcel.readString();
    this.mCollection = paramParcel.readString();
    this.mPriority = paramParcel.readInt();
    this.mLocales = (LocaleList)LocaleList.CREATOR.createFromParcel(paramParcel);
    this.mVendorId = paramParcel.readInt();
    this.mProductId = paramParcel.readInt();
  }
  
  public KeyboardLayout(String paramString1, String paramString2, String paramString3, int paramInt1, LocaleList paramLocaleList, int paramInt2, int paramInt3) {
    this.mDescriptor = paramString1;
    this.mLabel = paramString2;
    this.mCollection = paramString3;
    this.mPriority = paramInt1;
    this.mLocales = paramLocaleList;
    this.mVendorId = paramInt2;
    this.mProductId = paramInt3;
  }
  
  public int compareTo(KeyboardLayout paramKeyboardLayout) {
    int i = Integer.compare(paramKeyboardLayout.mPriority, this.mPriority);
    int j = i;
    if (i == 0)
      j = this.mLabel.compareToIgnoreCase(paramKeyboardLayout.mLabel); 
    i = j;
    if (j == 0)
      i = this.mCollection.compareToIgnoreCase(paramKeyboardLayout.mCollection); 
    return i;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getCollection() {
    return this.mCollection;
  }
  
  public String getDescriptor() {
    return this.mDescriptor;
  }
  
  public String getLabel() {
    return this.mLabel;
  }
  
  public LocaleList getLocales() {
    return this.mLocales;
  }
  
  public int getProductId() {
    return this.mProductId;
  }
  
  public int getVendorId() {
    return this.mVendorId;
  }
  
  public String toString() {
    if (this.mCollection.isEmpty())
      return this.mLabel; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mLabel);
    stringBuilder.append(" - ");
    stringBuilder.append(this.mCollection);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mDescriptor);
    paramParcel.writeString(this.mLabel);
    paramParcel.writeString(this.mCollection);
    paramParcel.writeInt(this.mPriority);
    this.mLocales.writeToParcel(paramParcel, 0);
    paramParcel.writeInt(this.mVendorId);
    paramParcel.writeInt(this.mProductId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/KeyboardLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */