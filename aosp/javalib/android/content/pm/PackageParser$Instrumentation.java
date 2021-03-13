package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public final class Instrumentation extends PackageParser.Component<PackageParser.IntentInfo> implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Instrumentation>() {
      public PackageParser.Instrumentation createFromParcel(Parcel param2Parcel) {
        return new PackageParser.Instrumentation(param2Parcel);
      }
      
      public PackageParser.Instrumentation[] newArray(int param2Int) {
        return new PackageParser.Instrumentation[param2Int];
      }
    };
  
  public final InstrumentationInfo info;
  
  public Instrumentation(PackageParser.ParsePackageItemArgs paramParsePackageItemArgs, InstrumentationInfo paramInstrumentationInfo) {
    super(paramParsePackageItemArgs, paramInstrumentationInfo);
    this.info = paramInstrumentationInfo;
  }
  
  private Instrumentation(Parcel paramParcel) {
    super(paramParcel);
    InstrumentationInfo instrumentationInfo = (InstrumentationInfo)paramParcel.readParcelable(Object.class.getClassLoader());
    this.info = instrumentationInfo;
    if (instrumentationInfo.targetPackage != null) {
      instrumentationInfo = this.info;
      instrumentationInfo.targetPackage = instrumentationInfo.targetPackage.intern();
    } 
    if (this.info.targetProcesses != null) {
      instrumentationInfo = this.info;
      instrumentationInfo.targetProcesses = instrumentationInfo.targetProcesses.intern();
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void setPackageName(String paramString) {
    super.setPackageName(paramString);
    this.info.packageName = paramString;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("Instrumentation{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    appendComponentShortName(stringBuilder);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(this.info, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Instrumentation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */