package android.content.pm;

import android.content.IntentFilter;
import android.os.Parcel;

public abstract class IntentInfo extends IntentFilter {
  public int banner;
  
  public boolean hasDefault;
  
  public int icon;
  
  public int labelRes;
  
  public int logo;
  
  public CharSequence nonLocalizedLabel;
  
  public int preferred;
  
  protected IntentInfo() {}
  
  protected IntentInfo(Parcel paramParcel) {
    super(paramParcel);
    int i = paramParcel.readInt();
    boolean bool = true;
    if (i != 1)
      bool = false; 
    this.hasDefault = bool;
    this.labelRes = paramParcel.readInt();
    this.nonLocalizedLabel = paramParcel.readCharSequence();
    this.icon = paramParcel.readInt();
    this.logo = paramParcel.readInt();
    this.banner = paramParcel.readInt();
    this.preferred = paramParcel.readInt();
  }
  
  public void writeIntentInfoToParcel(Parcel paramParcel, int paramInt) {
    writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.hasDefault);
    paramParcel.writeInt(this.labelRes);
    paramParcel.writeCharSequence(this.nonLocalizedLabel);
    paramParcel.writeInt(this.icon);
    paramParcel.writeInt(this.logo);
    paramParcel.writeInt(this.banner);
    paramParcel.writeInt(this.preferred);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$IntentInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */