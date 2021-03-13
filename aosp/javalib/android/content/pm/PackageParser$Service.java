package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public final class Service extends PackageParser.Component<PackageParser.ServiceIntentInfo> implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Service>() {
      public PackageParser.Service createFromParcel(Parcel param2Parcel) {
        return new PackageParser.Service(param2Parcel);
      }
      
      public PackageParser.Service[] newArray(int param2Int) {
        return new PackageParser.Service[param2Int];
      }
    };
  
  public final ServiceInfo info;
  
  public Service(PackageParser.ParseComponentArgs paramParseComponentArgs, ServiceInfo paramServiceInfo) {
    super(paramParseComponentArgs, paramServiceInfo);
    this.info = paramServiceInfo;
    paramServiceInfo.applicationInfo = paramParseComponentArgs.owner.applicationInfo;
  }
  
  private Service(Parcel paramParcel) {
    super(paramParcel);
    this.info = (ServiceInfo)paramParcel.readParcelable(Object.class.getClassLoader());
    for (PackageParser.ServiceIntentInfo serviceIntentInfo : this.intents) {
      serviceIntentInfo.service = this;
      this.order = Math.max(serviceIntentInfo.getOrder(), this.order);
    } 
    if (this.info.permission != null) {
      ServiceInfo serviceInfo = this.info;
      serviceInfo.permission = serviceInfo.permission.intern();
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
    stringBuilder.append("Service{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    appendComponentShortName(stringBuilder);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(this.info, paramInt | 0x2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Service.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */