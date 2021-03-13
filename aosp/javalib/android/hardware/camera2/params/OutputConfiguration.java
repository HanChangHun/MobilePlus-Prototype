package android.hardware.camera2.params;

import android.annotation.SystemApi;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.hardware.camera2.utils.SurfaceUtils;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class OutputConfiguration implements Parcelable {
  public static final Parcelable.Creator<OutputConfiguration> CREATOR = new Parcelable.Creator<OutputConfiguration>() {
      public OutputConfiguration createFromParcel(Parcel param1Parcel) {
        try {
          return new OutputConfiguration(param1Parcel);
        } catch (Exception exception) {
          Log.e("OutputConfiguration", "Exception creating OutputConfiguration from parcel", exception);
          return null;
        } 
      }
      
      public OutputConfiguration[] newArray(int param1Int) {
        return new OutputConfiguration[param1Int];
      }
    };
  
  private static final int MAX_SURFACES_COUNT = 4;
  
  @SystemApi
  public static final int ROTATION_0 = 0;
  
  @SystemApi
  public static final int ROTATION_180 = 2;
  
  @SystemApi
  public static final int ROTATION_270 = 3;
  
  @SystemApi
  public static final int ROTATION_90 = 1;
  
  public static final int SURFACE_GROUP_ID_NONE = -1;
  
  private static final String TAG = "OutputConfiguration";
  
  private final int SURFACE_TYPE_SURFACE_TEXTURE;
  
  private final int SURFACE_TYPE_SURFACE_VIEW;
  
  private final int SURFACE_TYPE_UNKNOWN;
  
  private final int mConfiguredDataspace;
  
  private final int mConfiguredFormat;
  
  private final int mConfiguredGenerationId;
  
  private final Size mConfiguredSize;
  
  private final boolean mIsDeferredConfig;
  
  private boolean mIsShared;
  
  private String mPhysicalCameraId;
  
  private final int mRotation;
  
  private final int mSurfaceGroupId;
  
  private final int mSurfaceType;
  
  private ArrayList<Surface> mSurfaces;
  
  public OutputConfiguration(int paramInt, Surface paramSurface) {
    this(paramInt, paramSurface, 0);
  }
  
  @SystemApi
  public OutputConfiguration(int paramInt1, Surface paramSurface, int paramInt2) {
    this.SURFACE_TYPE_UNKNOWN = -1;
    this.SURFACE_TYPE_SURFACE_VIEW = 0;
    this.SURFACE_TYPE_SURFACE_TEXTURE = 1;
    Preconditions.checkNotNull(paramSurface, "Surface must not be null");
    Preconditions.checkArgumentInRange(paramInt2, 0, 3, "Rotation constant");
    this.mSurfaceGroupId = paramInt1;
    this.mSurfaceType = -1;
    ArrayList<Surface> arrayList = new ArrayList();
    this.mSurfaces = arrayList;
    arrayList.add(paramSurface);
    this.mRotation = paramInt2;
    this.mConfiguredSize = SurfaceUtils.getSurfaceSize(paramSurface);
    this.mConfiguredFormat = SurfaceUtils.getSurfaceFormat(paramSurface);
    this.mConfiguredDataspace = SurfaceUtils.getSurfaceDataspace(paramSurface);
    this.mConfiguredGenerationId = paramSurface.getGenerationId();
    this.mIsDeferredConfig = false;
    this.mIsShared = false;
    this.mPhysicalCameraId = null;
  }
  
  public OutputConfiguration(OutputConfiguration paramOutputConfiguration) {
    this.SURFACE_TYPE_UNKNOWN = -1;
    this.SURFACE_TYPE_SURFACE_VIEW = 0;
    this.SURFACE_TYPE_SURFACE_TEXTURE = 1;
    if (paramOutputConfiguration != null) {
      this.mSurfaces = paramOutputConfiguration.mSurfaces;
      this.mRotation = paramOutputConfiguration.mRotation;
      this.mSurfaceGroupId = paramOutputConfiguration.mSurfaceGroupId;
      this.mSurfaceType = paramOutputConfiguration.mSurfaceType;
      this.mConfiguredDataspace = paramOutputConfiguration.mConfiguredDataspace;
      this.mConfiguredFormat = paramOutputConfiguration.mConfiguredFormat;
      this.mConfiguredSize = paramOutputConfiguration.mConfiguredSize;
      this.mConfiguredGenerationId = paramOutputConfiguration.mConfiguredGenerationId;
      this.mIsDeferredConfig = paramOutputConfiguration.mIsDeferredConfig;
      this.mIsShared = paramOutputConfiguration.mIsShared;
      this.mPhysicalCameraId = paramOutputConfiguration.mPhysicalCameraId;
      return;
    } 
    throw new IllegalArgumentException("OutputConfiguration shouldn't be null");
  }
  
  private OutputConfiguration(Parcel paramParcel) {
    boolean bool2;
    this.SURFACE_TYPE_UNKNOWN = -1;
    this.SURFACE_TYPE_SURFACE_VIEW = 0;
    boolean bool1 = true;
    this.SURFACE_TYPE_SURFACE_TEXTURE = 1;
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    int k = paramParcel.readInt();
    int m = paramParcel.readInt();
    int n = paramParcel.readInt();
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (paramParcel.readInt() != 1)
      bool1 = false; 
    ArrayList<Surface> arrayList = new ArrayList();
    paramParcel.readTypedList(arrayList, Surface.CREATOR);
    String str = paramParcel.readString();
    Preconditions.checkArgumentInRange(i, 0, 3, "Rotation constant");
    this.mSurfaceGroupId = j;
    this.mRotation = i;
    this.mSurfaces = arrayList;
    this.mConfiguredSize = new Size(m, n);
    this.mIsDeferredConfig = bool2;
    this.mIsShared = bool1;
    this.mSurfaces = arrayList;
    if (arrayList.size() > 0) {
      this.mSurfaceType = -1;
      this.mConfiguredFormat = SurfaceUtils.getSurfaceFormat(this.mSurfaces.get(0));
      this.mConfiguredDataspace = SurfaceUtils.getSurfaceDataspace(this.mSurfaces.get(0));
      this.mConfiguredGenerationId = ((Surface)this.mSurfaces.get(0)).getGenerationId();
    } else {
      this.mSurfaceType = k;
      this.mConfiguredFormat = StreamConfigurationMap.imageFormatToInternal(34);
      this.mConfiguredDataspace = StreamConfigurationMap.imageFormatToDataspace(34);
      this.mConfiguredGenerationId = 0;
    } 
    this.mPhysicalCameraId = str;
  }
  
  public <T> OutputConfiguration(Size paramSize, Class<T> paramClass) {
    this.SURFACE_TYPE_UNKNOWN = -1;
    this.SURFACE_TYPE_SURFACE_VIEW = 0;
    this.SURFACE_TYPE_SURFACE_TEXTURE = 1;
    Preconditions.checkNotNull(paramClass, "surfaceSize must not be null");
    Preconditions.checkNotNull(paramClass, "klass must not be null");
    if (paramClass == SurfaceHolder.class) {
      this.mSurfaceType = 0;
    } else if (paramClass == SurfaceTexture.class) {
      this.mSurfaceType = 1;
    } else {
      this.mSurfaceType = -1;
      throw new IllegalArgumentException("Unknow surface source class type");
    } 
    if (paramSize.getWidth() != 0 && paramSize.getHeight() != 0) {
      this.mSurfaceGroupId = -1;
      this.mSurfaces = new ArrayList<>();
      this.mRotation = 0;
      this.mConfiguredSize = paramSize;
      this.mConfiguredFormat = StreamConfigurationMap.imageFormatToInternal(34);
      this.mConfiguredDataspace = StreamConfigurationMap.imageFormatToDataspace(34);
      this.mConfiguredGenerationId = 0;
      this.mIsDeferredConfig = true;
      this.mIsShared = false;
      this.mPhysicalCameraId = null;
      return;
    } 
    throw new IllegalArgumentException("Surface size needs to be non-zero");
  }
  
  public OutputConfiguration(Surface paramSurface) {
    this(-1, paramSurface, 0);
  }
  
  @SystemApi
  public OutputConfiguration(Surface paramSurface, int paramInt) {
    this(-1, paramSurface, paramInt);
  }
  
  public void addSurface(Surface paramSurface) {
    Preconditions.checkNotNull(paramSurface, "Surface must not be null");
    if (!this.mSurfaces.contains(paramSurface)) {
      if (this.mSurfaces.size() != 1 || this.mIsShared) {
        if (this.mSurfaces.size() + 1 <= 4) {
          Size size = SurfaceUtils.getSurfaceSize(paramSurface);
          if (!size.equals(this.mConfiguredSize)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Added surface size ");
            stringBuilder.append(size);
            stringBuilder.append(" is different than pre-configured size ");
            stringBuilder.append(this.mConfiguredSize);
            stringBuilder.append(", the pre-configured size will be used.");
            Log.w("OutputConfiguration", stringBuilder.toString());
          } 
          if (this.mConfiguredFormat == SurfaceUtils.getSurfaceFormat(paramSurface)) {
            if (this.mConfiguredFormat == 34 || this.mConfiguredDataspace == SurfaceUtils.getSurfaceDataspace(paramSurface)) {
              this.mSurfaces.add(paramSurface);
              return;
            } 
            throw new IllegalArgumentException("The dataspace of added surface doesn't match");
          } 
          throw new IllegalArgumentException("The format of added surface format doesn't match");
        } 
        throw new IllegalArgumentException("Exceeds maximum number of surfaces");
      } 
      throw new IllegalStateException("Cannot have 2 surfaces for a non-sharing configuration");
    } 
    throw new IllegalStateException("Surface is already added!");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void enableSurfaceSharing() {
    this.mIsShared = true;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof OutputConfiguration) {
      paramObject = paramObject;
      if (this.mRotation == ((OutputConfiguration)paramObject).mRotation && this.mConfiguredSize.equals(((OutputConfiguration)paramObject).mConfiguredSize)) {
        int i = this.mConfiguredFormat;
        int j = ((OutputConfiguration)paramObject).mConfiguredFormat;
        if (i == j && this.mSurfaceGroupId == ((OutputConfiguration)paramObject).mSurfaceGroupId && this.mSurfaceType == ((OutputConfiguration)paramObject).mSurfaceType && this.mIsDeferredConfig == ((OutputConfiguration)paramObject).mIsDeferredConfig && this.mIsShared == ((OutputConfiguration)paramObject).mIsShared && i == j && this.mConfiguredDataspace == ((OutputConfiguration)paramObject).mConfiguredDataspace && this.mConfiguredGenerationId == ((OutputConfiguration)paramObject).mConfiguredGenerationId && Objects.equals(this.mPhysicalCameraId, ((OutputConfiguration)paramObject).mPhysicalCameraId)) {
          j = Math.min(this.mSurfaces.size(), ((OutputConfiguration)paramObject).mSurfaces.size());
          for (i = 0; i < j; i++) {
            if (this.mSurfaces.get(i) != ((OutputConfiguration)paramObject).mSurfaces.get(i))
              return false; 
          } 
          return true;
        } 
      } 
      return false;
    } 
    return false;
  }
  
  public int getMaxSharedSurfaceCount() {
    return 4;
  }
  
  @SystemApi
  public int getRotation() {
    return this.mRotation;
  }
  
  public Surface getSurface() {
    return (this.mSurfaces.size() == 0) ? null : this.mSurfaces.get(0);
  }
  
  public int getSurfaceGroupId() {
    return this.mSurfaceGroupId;
  }
  
  public List<Surface> getSurfaces() {
    return Collections.unmodifiableList(this.mSurfaces);
  }
  
  public int hashCode() {
    boolean bool1 = this.mIsDeferredConfig;
    int i = 0;
    int j = 0;
    if (bool1) {
      int i4 = this.mRotation;
      int i5 = this.mConfiguredSize.hashCode();
      int i6 = this.mConfiguredFormat;
      int i7 = this.mConfiguredDataspace;
      int i8 = this.mSurfaceGroupId;
      int i9 = this.mSurfaceType;
      boolean bool = this.mIsShared;
      String str1 = this.mPhysicalCameraId;
      if (str1 == null) {
        i = j;
      } else {
        i = str1.hashCode();
      } 
      return HashCodeHelpers.hashCode(new int[] { i4, i5, i6, i7, i8, i9, bool, i });
    } 
    int m = this.mRotation;
    int i2 = this.mSurfaces.hashCode();
    int n = this.mConfiguredGenerationId;
    int k = this.mConfiguredSize.hashCode();
    j = this.mConfiguredFormat;
    int i1 = this.mConfiguredDataspace;
    int i3 = this.mSurfaceGroupId;
    boolean bool2 = this.mIsShared;
    String str = this.mPhysicalCameraId;
    if (str != null)
      i = str.hashCode(); 
    return HashCodeHelpers.hashCode(new int[] { m, i2, n, k, j, i1, i3, bool2, i });
  }
  
  public boolean isDeferredConfiguration() {
    return this.mIsDeferredConfig;
  }
  
  public boolean isForPhysicalCamera() {
    boolean bool;
    if (this.mPhysicalCameraId != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void removeSurface(Surface paramSurface) {
    if (getSurface() != paramSurface) {
      if (this.mSurfaces.remove(paramSurface))
        return; 
      throw new IllegalArgumentException("Surface is not part of this output configuration");
    } 
    throw new IllegalArgumentException("Cannot remove surface associated with this output configuration");
  }
  
  public void setPhysicalCameraId(String paramString) {
    this.mPhysicalCameraId = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (paramParcel != null) {
      paramParcel.writeInt(this.mRotation);
      paramParcel.writeInt(this.mSurfaceGroupId);
      paramParcel.writeInt(this.mSurfaceType);
      paramParcel.writeInt(this.mConfiguredSize.getWidth());
      paramParcel.writeInt(this.mConfiguredSize.getHeight());
      paramParcel.writeInt(this.mIsDeferredConfig);
      paramParcel.writeInt(this.mIsShared);
      paramParcel.writeTypedList(this.mSurfaces);
      paramParcel.writeString(this.mPhysicalCameraId);
      return;
    } 
    throw new IllegalArgumentException("dest must not be null");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/OutputConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */