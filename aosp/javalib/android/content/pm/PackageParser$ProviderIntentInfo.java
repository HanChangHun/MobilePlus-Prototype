package android.content.pm;

import android.os.Parcel;

public final class ProviderIntentInfo extends PackageParser.IntentInfo {
  public PackageParser.Provider provider;
  
  public ProviderIntentInfo(PackageParser.Provider paramProvider) {
    this.provider = paramProvider;
  }
  
  public ProviderIntentInfo(Parcel paramParcel) {
    super(paramParcel);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("ProviderIntentInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    this.provider.appendComponentShortName(stringBuilder);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$ProviderIntentInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */