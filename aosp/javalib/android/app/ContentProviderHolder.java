package android.app;

import android.content.ContentProviderNative;
import android.content.IContentProvider;
import android.content.pm.ProviderInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public class ContentProviderHolder implements Parcelable {
  public static final Parcelable.Creator<ContentProviderHolder> CREATOR = new Parcelable.Creator<ContentProviderHolder>() {
      public ContentProviderHolder createFromParcel(Parcel param1Parcel) {
        return new ContentProviderHolder(param1Parcel);
      }
      
      public ContentProviderHolder[] newArray(int param1Int) {
        return new ContentProviderHolder[param1Int];
      }
    };
  
  public IBinder connection;
  
  public final ProviderInfo info;
  
  public boolean noReleaseNeeded;
  
  public IContentProvider provider;
  
  public ContentProviderHolder(ProviderInfo paramProviderInfo) {
    this.info = paramProviderInfo;
  }
  
  private ContentProviderHolder(Parcel paramParcel) {
    boolean bool;
    this.info = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(paramParcel);
    this.provider = ContentProviderNative.asInterface(paramParcel.readStrongBinder());
    this.connection = paramParcel.readStrongBinder();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.noReleaseNeeded = bool;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    this.info.writeToParcel(paramParcel, 0);
    IContentProvider iContentProvider = this.provider;
    if (iContentProvider != null) {
      paramParcel.writeStrongBinder(iContentProvider.asBinder());
    } else {
      paramParcel.writeStrongBinder(null);
    } 
    paramParcel.writeStrongBinder(this.connection);
    paramParcel.writeInt(this.noReleaseNeeded);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ContentProviderHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */