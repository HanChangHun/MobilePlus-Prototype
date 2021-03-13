package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProgramInfo implements Parcelable {
  public static final Parcelable.Creator<ProgramInfo> CREATOR = new Parcelable.Creator<ProgramInfo>() {
      public RadioManager.ProgramInfo createFromParcel(Parcel param2Parcel) {
        return new RadioManager.ProgramInfo(param2Parcel);
      }
      
      public RadioManager.ProgramInfo[] newArray(int param2Int) {
        return new RadioManager.ProgramInfo[param2Int];
      }
    };
  
  private static final int FLAG_LIVE = 1;
  
  private static final int FLAG_MUTED = 2;
  
  private static final int FLAG_STEREO = 32;
  
  private static final int FLAG_TRAFFIC_ANNOUNCEMENT = 8;
  
  private static final int FLAG_TRAFFIC_PROGRAM = 4;
  
  private static final int FLAG_TUNED = 16;
  
  private final int mInfoFlags;
  
  private final ProgramSelector.Identifier mLogicallyTunedTo;
  
  private final RadioMetadata mMetadata;
  
  private final ProgramSelector.Identifier mPhysicallyTunedTo;
  
  private final Collection<ProgramSelector.Identifier> mRelatedContent;
  
  private final ProgramSelector mSelector;
  
  private final int mSignalQuality;
  
  private final Map<String, String> mVendorInfo;
  
  public ProgramInfo(ProgramSelector paramProgramSelector, ProgramSelector.Identifier paramIdentifier1, ProgramSelector.Identifier paramIdentifier2, Collection<ProgramSelector.Identifier> paramCollection, int paramInt1, int paramInt2, RadioMetadata paramRadioMetadata, Map<String, String> paramMap) {
    Map<String, String> map;
    Objects.requireNonNull(paramProgramSelector);
    this.mSelector = paramProgramSelector;
    this.mLogicallyTunedTo = paramIdentifier1;
    this.mPhysicallyTunedTo = paramIdentifier2;
    if (paramCollection == null) {
      this.mRelatedContent = Collections.emptyList();
    } else {
      Preconditions.checkCollectionElementsNotNull(paramCollection, "relatedContent");
      this.mRelatedContent = paramCollection;
    } 
    this.mInfoFlags = paramInt1;
    this.mSignalQuality = paramInt2;
    this.mMetadata = paramRadioMetadata;
    if (paramMap == null) {
      HashMap<Object, Object> hashMap = new HashMap<>();
    } else {
      map = paramMap;
    } 
    this.mVendorInfo = map;
  }
  
  private ProgramInfo(Parcel paramParcel) {
    ProgramSelector programSelector = (ProgramSelector)paramParcel.readTypedObject(ProgramSelector.CREATOR);
    Objects.requireNonNull(programSelector);
    this.mSelector = programSelector;
    this.mLogicallyTunedTo = (ProgramSelector.Identifier)paramParcel.readTypedObject(ProgramSelector.Identifier.CREATOR);
    this.mPhysicallyTunedTo = (ProgramSelector.Identifier)paramParcel.readTypedObject(ProgramSelector.Identifier.CREATOR);
    this.mRelatedContent = paramParcel.createTypedArrayList(ProgramSelector.Identifier.CREATOR);
    this.mInfoFlags = paramParcel.readInt();
    this.mSignalQuality = paramParcel.readInt();
    this.mMetadata = (RadioMetadata)paramParcel.readTypedObject(RadioMetadata.CREATOR);
    this.mVendorInfo = Utils.readStringMap(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof ProgramInfo))
      return false; 
    paramObject = paramObject;
    return !Objects.equals(this.mSelector, ((ProgramInfo)paramObject).mSelector) ? false : (!Objects.equals(this.mLogicallyTunedTo, ((ProgramInfo)paramObject).mLogicallyTunedTo) ? false : (!Objects.equals(this.mPhysicallyTunedTo, ((ProgramInfo)paramObject).mPhysicallyTunedTo) ? false : (!Objects.equals(this.mRelatedContent, ((ProgramInfo)paramObject).mRelatedContent) ? false : ((this.mInfoFlags != ((ProgramInfo)paramObject).mInfoFlags) ? false : ((this.mSignalQuality != ((ProgramInfo)paramObject).mSignalQuality) ? false : (!Objects.equals(this.mMetadata, ((ProgramInfo)paramObject).mMetadata) ? false : (!!Objects.equals(this.mVendorInfo, ((ProgramInfo)paramObject).mVendorInfo))))))));
  }
  
  @Deprecated
  public int getChannel() {
    try {
      long l = this.mSelector.getFirstId(1);
      return (int)l;
    } catch (IllegalArgumentException illegalArgumentException) {
      Log.w("BroadcastRadio.manager", "Not an AM/FM program");
      return 0;
    } 
  }
  
  public ProgramSelector.Identifier getLogicallyTunedTo() {
    return this.mLogicallyTunedTo;
  }
  
  public RadioMetadata getMetadata() {
    return this.mMetadata;
  }
  
  public ProgramSelector.Identifier getPhysicallyTunedTo() {
    return this.mPhysicallyTunedTo;
  }
  
  public Collection<ProgramSelector.Identifier> getRelatedContent() {
    return this.mRelatedContent;
  }
  
  public ProgramSelector getSelector() {
    return this.mSelector;
  }
  
  public int getSignalStrength() {
    return this.mSignalQuality;
  }
  
  @Deprecated
  public int getSubChannel() {
    try {
      long l = this.mSelector.getFirstId(4);
      return (int)l + 1;
    } catch (IllegalArgumentException illegalArgumentException) {
      return 0;
    } 
  }
  
  public Map<String, String> getVendorInfo() {
    return this.mVendorInfo;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mSelector, this.mLogicallyTunedTo, this.mPhysicallyTunedTo, this.mRelatedContent, Integer.valueOf(this.mInfoFlags), Integer.valueOf(this.mSignalQuality), this.mMetadata, this.mVendorInfo });
  }
  
  @Deprecated
  public boolean isDigital() {
    ProgramSelector.Identifier identifier1 = this.mLogicallyTunedTo;
    ProgramSelector.Identifier identifier2 = identifier1;
    if (identifier1 == null)
      identifier2 = this.mSelector.getPrimaryId(); 
    int i = identifier2.getType();
    boolean bool = true;
    if (i == 1 || i == 2)
      bool = false; 
    return bool;
  }
  
  public boolean isLive() {
    int i = this.mInfoFlags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public boolean isMuted() {
    boolean bool;
    if ((this.mInfoFlags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStereo() {
    boolean bool;
    if ((this.mInfoFlags & 0x20) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isTrafficAnnouncementActive() {
    boolean bool;
    if ((this.mInfoFlags & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isTrafficProgram() {
    boolean bool;
    if ((this.mInfoFlags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isTuned() {
    boolean bool;
    if ((this.mInfoFlags & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ProgramInfo [selector=");
    stringBuilder.append(this.mSelector);
    stringBuilder.append(", logicallyTunedTo=");
    stringBuilder.append(Objects.toString(this.mLogicallyTunedTo));
    stringBuilder.append(", physicallyTunedTo=");
    stringBuilder.append(Objects.toString(this.mPhysicallyTunedTo));
    stringBuilder.append(", relatedContent=");
    stringBuilder.append(this.mRelatedContent.size());
    stringBuilder.append(", infoFlags=");
    stringBuilder.append(this.mInfoFlags);
    stringBuilder.append(", mSignalQuality=");
    stringBuilder.append(this.mSignalQuality);
    stringBuilder.append(", mMetadata=");
    stringBuilder.append(Objects.toString(this.mMetadata));
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedObject(this.mSelector, paramInt);
    paramParcel.writeTypedObject(this.mLogicallyTunedTo, paramInt);
    paramParcel.writeTypedObject(this.mPhysicallyTunedTo, paramInt);
    Utils.writeTypedCollection(paramParcel, this.mRelatedContent);
    paramParcel.writeInt(this.mInfoFlags);
    paramParcel.writeInt(this.mSignalQuality);
    paramParcel.writeTypedObject(this.mMetadata, paramInt);
    Utils.writeStringMap(paramParcel, this.mVendorInfo);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$ProgramInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */