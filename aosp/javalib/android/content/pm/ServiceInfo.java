package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Printer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ServiceInfo extends ComponentInfo implements Parcelable {
  public static final Parcelable.Creator<ServiceInfo> CREATOR = new Parcelable.Creator<ServiceInfo>() {
      public ServiceInfo createFromParcel(Parcel param1Parcel) {
        return new ServiceInfo(param1Parcel);
      }
      
      public ServiceInfo[] newArray(int param1Int) {
        return new ServiceInfo[param1Int];
      }
    };
  
  public static final int FLAG_EXTERNAL_SERVICE = 4;
  
  public static final int FLAG_ISOLATED_PROCESS = 2;
  
  public static final int FLAG_SINGLE_USER = 1073741824;
  
  public static final int FLAG_STOP_WITH_TASK = 1;
  
  public static final int FLAG_USE_APP_ZYGOTE = 8;
  
  public static final int FLAG_VISIBLE_TO_INSTANT_APP = 1048576;
  
  public static final int FOREGROUND_SERVICE_TYPE_CAMERA = 64;
  
  public static final int FOREGROUND_SERVICE_TYPE_CONNECTED_DEVICE = 16;
  
  public static final int FOREGROUND_SERVICE_TYPE_DATA_SYNC = 1;
  
  public static final int FOREGROUND_SERVICE_TYPE_LOCATION = 8;
  
  public static final int FOREGROUND_SERVICE_TYPE_MANIFEST = -1;
  
  public static final int FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK = 2;
  
  public static final int FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION = 32;
  
  public static final int FOREGROUND_SERVICE_TYPE_MICROPHONE = 128;
  
  public static final int FOREGROUND_SERVICE_TYPE_NONE = 0;
  
  public static final int FOREGROUND_SERVICE_TYPE_PHONE_CALL = 4;
  
  public int flags;
  
  public int mForegroundServiceType = 0;
  
  public String permission;
  
  public ServiceInfo() {}
  
  public ServiceInfo(ServiceInfo paramServiceInfo) {
    super(paramServiceInfo);
    this.permission = paramServiceInfo.permission;
    this.flags = paramServiceInfo.flags;
    this.mForegroundServiceType = paramServiceInfo.mForegroundServiceType;
  }
  
  private ServiceInfo(Parcel paramParcel) {
    super(paramParcel);
    this.permission = paramParcel.readString8();
    this.flags = paramParcel.readInt();
    this.mForegroundServiceType = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    dump(paramPrinter, paramString, 3);
  }
  
  void dump(Printer paramPrinter, String paramString, int paramInt) {
    dumpFront(paramPrinter, paramString);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("permission=");
    stringBuilder.append(this.permission);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("flags=0x");
    stringBuilder.append(Integer.toHexString(this.flags));
    paramPrinter.println(stringBuilder.toString());
    dumpBack(paramPrinter, paramString, paramInt);
  }
  
  public int getForegroundServiceType() {
    return this.mForegroundServiceType;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ServiceInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.name);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString8(this.permission);
    paramParcel.writeInt(this.flags);
    paramParcel.writeInt(this.mForegroundServiceType);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ForegroundServiceType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ServiceInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */