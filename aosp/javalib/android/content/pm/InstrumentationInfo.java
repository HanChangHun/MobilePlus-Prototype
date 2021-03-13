package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

public class InstrumentationInfo extends PackageItemInfo implements Parcelable {
  public static final Parcelable.Creator<InstrumentationInfo> CREATOR = new Parcelable.Creator<InstrumentationInfo>() {
      public InstrumentationInfo createFromParcel(Parcel param1Parcel) {
        return new InstrumentationInfo(param1Parcel);
      }
      
      public InstrumentationInfo[] newArray(int param1Int) {
        return new InstrumentationInfo[param1Int];
      }
    };
  
  public String credentialProtectedDataDir;
  
  public String dataDir;
  
  public String deviceProtectedDataDir;
  
  public boolean functionalTest;
  
  public boolean handleProfiling;
  
  public String nativeLibraryDir;
  
  public String primaryCpuAbi;
  
  public String publicSourceDir;
  
  public String secondaryCpuAbi;
  
  public String secondaryNativeLibraryDir;
  
  public String sourceDir;
  
  public SparseArray<int[]> splitDependencies;
  
  public String[] splitNames;
  
  public String[] splitPublicSourceDirs;
  
  public String[] splitSourceDirs;
  
  public String targetPackage;
  
  public String targetProcesses;
  
  public InstrumentationInfo() {}
  
  public InstrumentationInfo(InstrumentationInfo paramInstrumentationInfo) {
    super(paramInstrumentationInfo);
    this.targetPackage = paramInstrumentationInfo.targetPackage;
    this.targetProcesses = paramInstrumentationInfo.targetProcesses;
    this.sourceDir = paramInstrumentationInfo.sourceDir;
    this.publicSourceDir = paramInstrumentationInfo.publicSourceDir;
    this.splitNames = paramInstrumentationInfo.splitNames;
    this.splitSourceDirs = paramInstrumentationInfo.splitSourceDirs;
    this.splitPublicSourceDirs = paramInstrumentationInfo.splitPublicSourceDirs;
    this.splitDependencies = paramInstrumentationInfo.splitDependencies;
    this.dataDir = paramInstrumentationInfo.dataDir;
    this.deviceProtectedDataDir = paramInstrumentationInfo.deviceProtectedDataDir;
    this.credentialProtectedDataDir = paramInstrumentationInfo.credentialProtectedDataDir;
    this.primaryCpuAbi = paramInstrumentationInfo.primaryCpuAbi;
    this.secondaryCpuAbi = paramInstrumentationInfo.secondaryCpuAbi;
    this.nativeLibraryDir = paramInstrumentationInfo.nativeLibraryDir;
    this.secondaryNativeLibraryDir = paramInstrumentationInfo.secondaryNativeLibraryDir;
    this.handleProfiling = paramInstrumentationInfo.handleProfiling;
    this.functionalTest = paramInstrumentationInfo.functionalTest;
  }
  
  private InstrumentationInfo(Parcel paramParcel) {
    super(paramParcel);
    boolean bool2;
    this.targetPackage = paramParcel.readString8();
    this.targetProcesses = paramParcel.readString8();
    this.sourceDir = paramParcel.readString8();
    this.publicSourceDir = paramParcel.readString8();
    this.splitNames = paramParcel.createString8Array();
    this.splitSourceDirs = paramParcel.createString8Array();
    this.splitPublicSourceDirs = paramParcel.createString8Array();
    this.splitDependencies = paramParcel.readSparseArray(null);
    this.dataDir = paramParcel.readString8();
    this.deviceProtectedDataDir = paramParcel.readString8();
    this.credentialProtectedDataDir = paramParcel.readString8();
    this.primaryCpuAbi = paramParcel.readString8();
    this.secondaryCpuAbi = paramParcel.readString8();
    this.nativeLibraryDir = paramParcel.readString8();
    this.secondaryNativeLibraryDir = paramParcel.readString8();
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.handleProfiling = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.functionalTest = bool2;
  }
  
  public void copyTo(ApplicationInfo paramApplicationInfo) {
    paramApplicationInfo.packageName = this.packageName;
    paramApplicationInfo.sourceDir = this.sourceDir;
    paramApplicationInfo.publicSourceDir = this.publicSourceDir;
    paramApplicationInfo.splitNames = this.splitNames;
    paramApplicationInfo.splitSourceDirs = this.splitSourceDirs;
    paramApplicationInfo.splitPublicSourceDirs = this.splitPublicSourceDirs;
    paramApplicationInfo.splitDependencies = this.splitDependencies;
    paramApplicationInfo.dataDir = this.dataDir;
    paramApplicationInfo.deviceProtectedDataDir = this.deviceProtectedDataDir;
    paramApplicationInfo.credentialProtectedDataDir = this.credentialProtectedDataDir;
    paramApplicationInfo.primaryCpuAbi = this.primaryCpuAbi;
    paramApplicationInfo.secondaryCpuAbi = this.secondaryCpuAbi;
    paramApplicationInfo.nativeLibraryDir = this.nativeLibraryDir;
    paramApplicationInfo.secondaryNativeLibraryDir = this.secondaryNativeLibraryDir;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("InstrumentationInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.packageName);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString8(this.targetPackage);
    paramParcel.writeString8(this.targetProcesses);
    paramParcel.writeString8(this.sourceDir);
    paramParcel.writeString8(this.publicSourceDir);
    paramParcel.writeString8Array(this.splitNames);
    paramParcel.writeString8Array(this.splitSourceDirs);
    paramParcel.writeString8Array(this.splitPublicSourceDirs);
    paramParcel.writeSparseArray(this.splitDependencies);
    paramParcel.writeString8(this.dataDir);
    paramParcel.writeString8(this.deviceProtectedDataDir);
    paramParcel.writeString8(this.credentialProtectedDataDir);
    paramParcel.writeString8(this.primaryCpuAbi);
    paramParcel.writeString8(this.secondaryCpuAbi);
    paramParcel.writeString8(this.nativeLibraryDir);
    paramParcel.writeString8(this.secondaryNativeLibraryDir);
    paramParcel.writeInt(this.handleProfiling);
    paramParcel.writeInt(this.functionalTest);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstrumentationInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */