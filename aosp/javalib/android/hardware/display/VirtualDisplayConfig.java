package android.hardware.display;

import android.annotation.IntRange;
import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;
import com.android.internal.util.AnnotationValidations;

public final class VirtualDisplayConfig implements Parcelable {
  public static final Parcelable.Creator<VirtualDisplayConfig> CREATOR = new Parcelable.Creator<VirtualDisplayConfig>() {
      public VirtualDisplayConfig createFromParcel(Parcel param1Parcel) {
        return new VirtualDisplayConfig(param1Parcel);
      }
      
      public VirtualDisplayConfig[] newArray(int param1Int) {
        return new VirtualDisplayConfig[param1Int];
      }
    };
  
  private int mDensityDpi;
  
  private int mDisplayIdToMirror;
  
  private int mFlags;
  
  private int mHeight;
  
  private String mName;
  
  private Surface mSurface;
  
  private String mUniqueId;
  
  private int mWidth;
  
  VirtualDisplayConfig(Parcel paramParcel) {
    Surface surface;
    String str2;
    this.mFlags = 0;
    this.mSurface = null;
    this.mUniqueId = null;
    this.mDisplayIdToMirror = 0;
    int i = paramParcel.readInt();
    String str1 = paramParcel.readString();
    int j = paramParcel.readInt();
    int k = paramParcel.readInt();
    int m = paramParcel.readInt();
    int n = paramParcel.readInt();
    if ((i & 0x20) == 0) {
      surface = null;
    } else {
      surface = (Surface)paramParcel.readTypedObject(Surface.CREATOR);
    } 
    if ((i & 0x40) == 0) {
      str2 = null;
    } else {
      str2 = paramParcel.readString();
    } 
    i = paramParcel.readInt();
    this.mName = str1;
    AnnotationValidations.validate(NonNull.class, null, str1);
    this.mWidth = j;
    AnnotationValidations.validate(IntRange.class, null, j, "from", 1L);
    this.mHeight = k;
    AnnotationValidations.validate(IntRange.class, null, k, "from", 1L);
    this.mDensityDpi = m;
    AnnotationValidations.validate(IntRange.class, null, m, "from", 1L);
    this.mFlags = n;
    this.mSurface = surface;
    this.mUniqueId = str2;
    this.mDisplayIdToMirror = i;
  }
  
  VirtualDisplayConfig(String paramString1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Surface paramSurface, String paramString2, int paramInt5) {
    this.mFlags = 0;
    this.mSurface = null;
    this.mUniqueId = null;
    this.mDisplayIdToMirror = 0;
    this.mName = paramString1;
    AnnotationValidations.validate(NonNull.class, null, paramString1);
    this.mWidth = paramInt1;
    AnnotationValidations.validate(IntRange.class, null, paramInt1, "from", 1L);
    this.mHeight = paramInt2;
    AnnotationValidations.validate(IntRange.class, null, paramInt2, "from", 1L);
    this.mDensityDpi = paramInt3;
    AnnotationValidations.validate(IntRange.class, null, paramInt3, "from", 1L);
    this.mFlags = paramInt4;
    this.mSurface = paramSurface;
    this.mUniqueId = paramString2;
    this.mDisplayIdToMirror = paramInt5;
  }
  
  @Deprecated
  private void __metadata() {}
  
  public int describeContents() {
    return 0;
  }
  
  public int getDensityDpi() {
    return this.mDensityDpi;
  }
  
  public int getDisplayIdToMirror() {
    return this.mDisplayIdToMirror;
  }
  
  public int getFlags() {
    return this.mFlags;
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public String getName() {
    return this.mName;
  }
  
  public Surface getSurface() {
    return this.mSurface;
  }
  
  public String getUniqueId() {
    return this.mUniqueId;
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = 0;
    if (this.mSurface != null)
      i = 0x0 | 0x20; 
    int j = i;
    if (this.mUniqueId != null)
      j = i | 0x40; 
    paramParcel.writeInt(j);
    paramParcel.writeString(this.mName);
    paramParcel.writeInt(this.mWidth);
    paramParcel.writeInt(this.mHeight);
    paramParcel.writeInt(this.mDensityDpi);
    paramParcel.writeInt(this.mFlags);
    Surface surface = this.mSurface;
    if (surface != null)
      paramParcel.writeTypedObject((Parcelable)surface, paramInt); 
    String str = this.mUniqueId;
    if (str != null)
      paramParcel.writeString(str); 
    paramParcel.writeInt(this.mDisplayIdToMirror);
  }
  
  public static final class Builder {
    private long mBuilderFieldsSet = 0L;
    
    private int mDensityDpi;
    
    private int mDisplayIdToMirror;
    
    private int mFlags;
    
    private int mHeight;
    
    private String mName;
    
    private Surface mSurface;
    
    private String mUniqueId;
    
    private int mWidth;
    
    public Builder(String param1String, int param1Int1, int param1Int2, int param1Int3) {
      this.mName = param1String;
      AnnotationValidations.validate(NonNull.class, null, param1String);
      this.mWidth = param1Int1;
      AnnotationValidations.validate(IntRange.class, null, param1Int1, "from", 1L);
      this.mHeight = param1Int2;
      AnnotationValidations.validate(IntRange.class, null, param1Int2, "from", 1L);
      this.mDensityDpi = param1Int3;
      AnnotationValidations.validate(IntRange.class, null, param1Int3, "from", 1L);
    }
    
    private void checkNotUsed() {
      if ((this.mBuilderFieldsSet & 0x100L) == 0L)
        return; 
      throw new IllegalStateException("This Builder should not be reused. Use a new Builder instance instead");
    }
    
    public VirtualDisplayConfig build() {
      checkNotUsed();
      long l = this.mBuilderFieldsSet | 0x100L;
      this.mBuilderFieldsSet = l;
      if ((l & 0x10L) == 0L)
        this.mFlags = 0; 
      if ((this.mBuilderFieldsSet & 0x20L) == 0L)
        this.mSurface = null; 
      if ((this.mBuilderFieldsSet & 0x40L) == 0L)
        this.mUniqueId = null; 
      if ((this.mBuilderFieldsSet & 0x80L) == 0L)
        this.mDisplayIdToMirror = 0; 
      return new VirtualDisplayConfig(this.mName, this.mWidth, this.mHeight, this.mDensityDpi, this.mFlags, this.mSurface, this.mUniqueId, this.mDisplayIdToMirror);
    }
    
    public Builder setDensityDpi(int param1Int) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x8L;
      this.mDensityDpi = param1Int;
      return this;
    }
    
    public Builder setDisplayIdToMirror(int param1Int) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x80L;
      this.mDisplayIdToMirror = param1Int;
      return this;
    }
    
    public Builder setFlags(int param1Int) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x10L;
      this.mFlags = param1Int;
      return this;
    }
    
    public Builder setHeight(int param1Int) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x4L;
      this.mHeight = param1Int;
      return this;
    }
    
    public Builder setName(String param1String) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x1L;
      this.mName = param1String;
      return this;
    }
    
    public Builder setSurface(Surface param1Surface) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x20L;
      this.mSurface = param1Surface;
      return this;
    }
    
    public Builder setUniqueId(String param1String) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x40L;
      this.mUniqueId = param1String;
      return this;
    }
    
    public Builder setWidth(int param1Int) {
      checkNotUsed();
      this.mBuilderFieldsSet |= 0x2L;
      this.mWidth = param1Int;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/VirtualDisplayConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */