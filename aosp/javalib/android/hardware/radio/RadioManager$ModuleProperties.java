package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ModuleProperties implements Parcelable {
  public static final Parcelable.Creator<ModuleProperties> CREATOR = new Parcelable.Creator<ModuleProperties>() {
      public RadioManager.ModuleProperties createFromParcel(Parcel param2Parcel) {
        return new RadioManager.ModuleProperties(param2Parcel);
      }
      
      public RadioManager.ModuleProperties[] newArray(int param2Int) {
        return new RadioManager.ModuleProperties[param2Int];
      }
    };
  
  private final RadioManager.BandDescriptor[] mBands;
  
  private final int mClassId;
  
  private final Map<String, Integer> mDabFrequencyTable;
  
  private final int mId;
  
  private final String mImplementor;
  
  private final boolean mIsBgScanSupported;
  
  private final boolean mIsCaptureSupported;
  
  private final boolean mIsInitializationRequired;
  
  private final int mNumAudioSources;
  
  private final int mNumTuners;
  
  private final String mProduct;
  
  private final String mSerial;
  
  private final String mServiceName;
  
  private final Set<Integer> mSupportedIdentifierTypes;
  
  private final Set<Integer> mSupportedProgramTypes;
  
  private final Map<String, String> mVendorInfo;
  
  private final String mVersion;
  
  public ModuleProperties(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, RadioManager.BandDescriptor[] paramArrayOfBandDescriptor, boolean paramBoolean3, int[] paramArrayOfint1, int[] paramArrayOfint2, Map<String, Integer> paramMap, Map<String, String> paramMap1) {
    Map<String, String> map;
    this.mId = paramInt1;
    if (TextUtils.isEmpty(paramString1))
      paramString1 = "default"; 
    this.mServiceName = paramString1;
    this.mClassId = paramInt2;
    this.mImplementor = paramString2;
    this.mProduct = paramString3;
    this.mVersion = paramString4;
    this.mSerial = paramString5;
    this.mNumTuners = paramInt3;
    this.mNumAudioSources = paramInt4;
    this.mIsInitializationRequired = paramBoolean1;
    this.mIsCaptureSupported = paramBoolean2;
    this.mBands = paramArrayOfBandDescriptor;
    this.mIsBgScanSupported = paramBoolean3;
    this.mSupportedProgramTypes = arrayToSet(paramArrayOfint1);
    this.mSupportedIdentifierTypes = arrayToSet(paramArrayOfint2);
    if (paramMap != null)
      for (Map.Entry<String, Integer> entry : paramMap.entrySet()) {
        Objects.requireNonNull((String)entry.getKey());
        Objects.requireNonNull((Integer)entry.getValue());
      }  
    this.mDabFrequencyTable = paramMap;
    if (paramMap1 == null) {
      HashMap<Object, Object> hashMap = new HashMap<>();
    } else {
      map = paramMap1;
    } 
    this.mVendorInfo = map;
  }
  
  private ModuleProperties(Parcel paramParcel) {
    this.mId = paramParcel.readInt();
    String str = paramParcel.readString();
    if (TextUtils.isEmpty(str))
      str = "default"; 
    this.mServiceName = str;
    this.mClassId = paramParcel.readInt();
    this.mImplementor = paramParcel.readString();
    this.mProduct = paramParcel.readString();
    this.mVersion = paramParcel.readString();
    this.mSerial = paramParcel.readString();
    this.mNumTuners = paramParcel.readInt();
    this.mNumAudioSources = paramParcel.readInt();
    int i = paramParcel.readInt();
    boolean bool1 = false;
    if (i == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsInitializationRequired = bool2;
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsCaptureSupported = bool2;
    Parcelable[] arrayOfParcelable = paramParcel.readParcelableArray(RadioManager.BandDescriptor.class.getClassLoader());
    this.mBands = new RadioManager.BandDescriptor[arrayOfParcelable.length];
    for (i = 0; i < arrayOfParcelable.length; i++)
      this.mBands[i] = (RadioManager.BandDescriptor)arrayOfParcelable[i]; 
    boolean bool2 = bool1;
    if (paramParcel.readInt() == 1)
      bool2 = true; 
    this.mIsBgScanSupported = bool2;
    this.mSupportedProgramTypes = arrayToSet(paramParcel.createIntArray());
    this.mSupportedIdentifierTypes = arrayToSet(paramParcel.createIntArray());
    this.mDabFrequencyTable = Utils.readStringIntMap(paramParcel);
    this.mVendorInfo = Utils.readStringMap(paramParcel);
  }
  
  private static Set<Integer> arrayToSet(int[] paramArrayOfint) {
    return Arrays.stream(paramArrayOfint).boxed().collect((Collector)Collectors.toSet());
  }
  
  private static int[] setToArray(Set<Integer> paramSet) {
    return paramSet.stream().mapToInt((ToIntFunction)_$$Lambda$UV1wDVoVlbcxpr8zevj_aMFtUGw.INSTANCE).toArray();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof ModuleProperties))
      return false; 
    paramObject = paramObject;
    return (this.mId != paramObject.getId()) ? false : (!TextUtils.equals(this.mServiceName, ((ModuleProperties)paramObject).mServiceName) ? false : ((this.mClassId != ((ModuleProperties)paramObject).mClassId) ? false : (!Objects.equals(this.mImplementor, ((ModuleProperties)paramObject).mImplementor) ? false : (!Objects.equals(this.mProduct, ((ModuleProperties)paramObject).mProduct) ? false : (!Objects.equals(this.mVersion, ((ModuleProperties)paramObject).mVersion) ? false : (!Objects.equals(this.mSerial, ((ModuleProperties)paramObject).mSerial) ? false : ((this.mNumTuners != ((ModuleProperties)paramObject).mNumTuners) ? false : ((this.mNumAudioSources != ((ModuleProperties)paramObject).mNumAudioSources) ? false : ((this.mIsInitializationRequired != ((ModuleProperties)paramObject).mIsInitializationRequired) ? false : ((this.mIsCaptureSupported != ((ModuleProperties)paramObject).mIsCaptureSupported) ? false : (!Objects.equals(this.mBands, ((ModuleProperties)paramObject).mBands) ? false : ((this.mIsBgScanSupported != ((ModuleProperties)paramObject).mIsBgScanSupported) ? false : (!Objects.equals(this.mDabFrequencyTable, ((ModuleProperties)paramObject).mDabFrequencyTable) ? false : (!!Objects.equals(this.mVendorInfo, ((ModuleProperties)paramObject).mVendorInfo)))))))))))))));
  }
  
  public RadioManager.BandDescriptor[] getBands() {
    return this.mBands;
  }
  
  public int getClassId() {
    return this.mClassId;
  }
  
  public Map<String, Integer> getDabFrequencyTable() {
    return this.mDabFrequencyTable;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public String getImplementor() {
    return this.mImplementor;
  }
  
  public int getNumAudioSources() {
    return this.mNumAudioSources;
  }
  
  public int getNumTuners() {
    return this.mNumTuners;
  }
  
  public String getProduct() {
    return this.mProduct;
  }
  
  public String getSerial() {
    return this.mSerial;
  }
  
  public String getServiceName() {
    return this.mServiceName;
  }
  
  public Map<String, String> getVendorInfo() {
    return this.mVendorInfo;
  }
  
  public String getVersion() {
    return this.mVersion;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { 
          Integer.valueOf(this.mId), this.mServiceName, Integer.valueOf(this.mClassId), this.mImplementor, this.mProduct, this.mVersion, this.mSerial, Integer.valueOf(this.mNumTuners), Integer.valueOf(this.mNumAudioSources), Boolean.valueOf(this.mIsInitializationRequired), 
          Boolean.valueOf(this.mIsCaptureSupported), this.mBands, Boolean.valueOf(this.mIsBgScanSupported), this.mDabFrequencyTable, this.mVendorInfo });
  }
  
  public boolean isBackgroundScanningSupported() {
    return this.mIsBgScanSupported;
  }
  
  public boolean isCaptureSupported() {
    return this.mIsCaptureSupported;
  }
  
  public boolean isInitializationRequired() {
    return this.mIsInitializationRequired;
  }
  
  public boolean isProgramIdentifierSupported(int paramInt) {
    return this.mSupportedIdentifierTypes.contains(Integer.valueOf(paramInt));
  }
  
  public boolean isProgramTypeSupported(int paramInt) {
    return this.mSupportedProgramTypes.contains(Integer.valueOf(paramInt));
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ModuleProperties [mId=");
    stringBuilder.append(this.mId);
    stringBuilder.append(", mServiceName=");
    stringBuilder.append(this.mServiceName);
    stringBuilder.append(", mClassId=");
    stringBuilder.append(this.mClassId);
    stringBuilder.append(", mImplementor=");
    stringBuilder.append(this.mImplementor);
    stringBuilder.append(", mProduct=");
    stringBuilder.append(this.mProduct);
    stringBuilder.append(", mVersion=");
    stringBuilder.append(this.mVersion);
    stringBuilder.append(", mSerial=");
    stringBuilder.append(this.mSerial);
    stringBuilder.append(", mNumTuners=");
    stringBuilder.append(this.mNumTuners);
    stringBuilder.append(", mNumAudioSources=");
    stringBuilder.append(this.mNumAudioSources);
    stringBuilder.append(", mIsInitializationRequired=");
    stringBuilder.append(this.mIsInitializationRequired);
    stringBuilder.append(", mIsCaptureSupported=");
    stringBuilder.append(this.mIsCaptureSupported);
    stringBuilder.append(", mIsBgScanSupported=");
    stringBuilder.append(this.mIsBgScanSupported);
    stringBuilder.append(", mBands=");
    stringBuilder.append(Arrays.toString((Object[])this.mBands));
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mId);
    paramParcel.writeString(this.mServiceName);
    paramParcel.writeInt(this.mClassId);
    paramParcel.writeString(this.mImplementor);
    paramParcel.writeString(this.mProduct);
    paramParcel.writeString(this.mVersion);
    paramParcel.writeString(this.mSerial);
    paramParcel.writeInt(this.mNumTuners);
    paramParcel.writeInt(this.mNumAudioSources);
    paramParcel.writeInt(this.mIsInitializationRequired);
    paramParcel.writeInt(this.mIsCaptureSupported);
    paramParcel.writeParcelableArray((Parcelable[])this.mBands, paramInt);
    paramParcel.writeInt(this.mIsBgScanSupported);
    paramParcel.writeIntArray(setToArray(this.mSupportedProgramTypes));
    paramParcel.writeIntArray(setToArray(this.mSupportedIdentifierTypes));
    Utils.writeStringIntMap(paramParcel, this.mDabFrequencyTable);
    Utils.writeStringMap(paramParcel, this.mVendorInfo);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$ModuleProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */