package android.content.pm;

import android.os.Parcel;

public final class ServiceIntentInfo extends PackageParser.IntentInfo {
  public PackageParser.Service service;
  
  public ServiceIntentInfo(PackageParser.Service paramService) {
    this.service = paramService;
  }
  
  public ServiceIntentInfo(Parcel paramParcel) {
    super(paramParcel);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("ServiceIntentInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    this.service.appendComponentShortName(stringBuilder);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$ServiceIntentInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */