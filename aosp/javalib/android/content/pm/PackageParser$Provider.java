package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Iterator;

public final class Provider extends PackageParser.Component<PackageParser.ProviderIntentInfo> implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Provider>() {
      public PackageParser.Provider createFromParcel(Parcel param2Parcel) {
        return new PackageParser.Provider(param2Parcel);
      }
      
      public PackageParser.Provider[] newArray(int param2Int) {
        return new PackageParser.Provider[param2Int];
      }
    };
  
  public final ProviderInfo info;
  
  public boolean syncable;
  
  public Provider(PackageParser.ParseComponentArgs paramParseComponentArgs, ProviderInfo paramProviderInfo) {
    super(paramParseComponentArgs, paramProviderInfo);
    this.info = paramProviderInfo;
    paramProviderInfo.applicationInfo = paramParseComponentArgs.owner.applicationInfo;
    this.syncable = false;
  }
  
  public Provider(Provider paramProvider) {
    super(paramProvider);
    this.info = paramProvider.info;
    this.syncable = paramProvider.syncable;
  }
  
  private Provider(Parcel paramParcel) {
    super(paramParcel);
    this.info = (ProviderInfo)paramParcel.readParcelable(Object.class.getClassLoader());
    int i = paramParcel.readInt();
    boolean bool = true;
    if (i != 1)
      bool = false; 
    this.syncable = bool;
    Iterator<PackageParser.ProviderIntentInfo> iterator = this.intents.iterator();
    while (iterator.hasNext())
      ((PackageParser.ProviderIntentInfo)iterator.next()).provider = this; 
    if (this.info.readPermission != null) {
      ProviderInfo providerInfo = this.info;
      providerInfo.readPermission = providerInfo.readPermission.intern();
    } 
    if (this.info.writePermission != null) {
      ProviderInfo providerInfo = this.info;
      providerInfo.writePermission = providerInfo.writePermission.intern();
    } 
    if (this.info.authority != null) {
      ProviderInfo providerInfo = this.info;
      providerInfo.authority = providerInfo.authority.intern();
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
    stringBuilder.append("Provider{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    appendComponentShortName(stringBuilder);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(this.info, paramInt | 0x2);
    paramParcel.writeInt(this.syncable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$Provider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */