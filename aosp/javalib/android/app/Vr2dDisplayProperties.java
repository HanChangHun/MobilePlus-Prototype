package android.app;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SystemApi
public final class Vr2dDisplayProperties implements Parcelable {
  public static final Parcelable.Creator<Vr2dDisplayProperties> CREATOR = new Parcelable.Creator<Vr2dDisplayProperties>() {
      public Vr2dDisplayProperties createFromParcel(Parcel param1Parcel) {
        return new Vr2dDisplayProperties(param1Parcel);
      }
      
      public Vr2dDisplayProperties[] newArray(int param1Int) {
        return new Vr2dDisplayProperties[param1Int];
      }
    };
  
  public static final int FLAG_VIRTUAL_DISPLAY_ENABLED = 1;
  
  private final int mAddedFlags;
  
  private final int mDpi;
  
  private final int mHeight;
  
  private final int mRemovedFlags;
  
  private final int mWidth;
  
  public Vr2dDisplayProperties(int paramInt1, int paramInt2, int paramInt3) {
    this(paramInt1, paramInt2, paramInt3, 0, 0);
  }
  
  private Vr2dDisplayProperties(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    this.mDpi = paramInt3;
    this.mAddedFlags = paramInt4;
    this.mRemovedFlags = paramInt5;
  }
  
  private Vr2dDisplayProperties(Parcel paramParcel) {
    this.mWidth = paramParcel.readInt();
    this.mHeight = paramParcel.readInt();
    this.mDpi = paramParcel.readInt();
    this.mAddedFlags = paramParcel.readInt();
    this.mRemovedFlags = paramParcel.readInt();
  }
  
  private static String toReadableFlags(int paramInt) {
    String str = "{";
    if ((paramInt & 0x1) == 1) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("{");
      stringBuilder1.append("enabled");
      str = stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(PrintWriter paramPrintWriter, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(toString());
    paramPrintWriter.println(stringBuilder.toString());
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (getAddedFlags() != paramObject.getAddedFlags())
      return false; 
    if (getRemovedFlags() != paramObject.getRemovedFlags())
      return false; 
    if (getWidth() != paramObject.getWidth())
      return false; 
    if (getHeight() != paramObject.getHeight())
      return false; 
    if (getDpi() != paramObject.getDpi())
      bool = false; 
    return bool;
  }
  
  public int getAddedFlags() {
    return this.mAddedFlags;
  }
  
  public int getDpi() {
    return this.mDpi;
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public int getRemovedFlags() {
    return this.mRemovedFlags;
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  public int hashCode() {
    return (getWidth() * 31 + getHeight()) * 31 + getDpi();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Vr2dDisplayProperties{mWidth=");
    stringBuilder.append(this.mWidth);
    stringBuilder.append(", mHeight=");
    stringBuilder.append(this.mHeight);
    stringBuilder.append(", mDpi=");
    stringBuilder.append(this.mDpi);
    stringBuilder.append(", flags=");
    stringBuilder.append(toReadableFlags(this.mAddedFlags));
    stringBuilder.append(", removed_flags=");
    stringBuilder.append(toReadableFlags(this.mRemovedFlags));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mWidth);
    paramParcel.writeInt(this.mHeight);
    paramParcel.writeInt(this.mDpi);
    paramParcel.writeInt(this.mAddedFlags);
    paramParcel.writeInt(this.mRemovedFlags);
  }
  
  public static final class Builder {
    private int mAddedFlags = 0;
    
    private int mDpi = -1;
    
    private int mHeight = -1;
    
    private int mRemovedFlags = 0;
    
    private int mWidth = -1;
    
    public Builder addFlags(int param1Int) {
      this.mAddedFlags |= param1Int;
      this.mRemovedFlags &= param1Int;
      return this;
    }
    
    public Vr2dDisplayProperties build() {
      return new Vr2dDisplayProperties(this.mWidth, this.mHeight, this.mDpi, this.mAddedFlags, this.mRemovedFlags);
    }
    
    public Builder removeFlags(int param1Int) {
      this.mRemovedFlags |= param1Int;
      this.mAddedFlags &= param1Int;
      return this;
    }
    
    public Builder setDimensions(int param1Int1, int param1Int2, int param1Int3) {
      this.mWidth = param1Int1;
      this.mHeight = param1Int2;
      this.mDpi = param1Int3;
      return this;
    }
    
    public Builder setEnabled(boolean param1Boolean) {
      if (param1Boolean) {
        addFlags(1);
      } else {
        removeFlags(1);
      } 
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Vr2dDisplayFlag {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Vr2dDisplayProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */