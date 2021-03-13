package android.content.pm.parsing.component;

import android.content.ComponentName;
import android.content.pm.parsing.ParsingPackageImpl;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class ParsedService extends ParsedMainComponent {
  public static final Parcelable.Creator<ParsedService> CREATOR = new Parcelable.Creator<ParsedService>() {
      public ParsedService createFromParcel(Parcel param1Parcel) {
        return new ParsedService(param1Parcel);
      }
      
      public ParsedService[] newArray(int param1Int) {
        return new ParsedService[param1Int];
      }
    };
  
  int foregroundServiceType;
  
  private String permission;
  
  public ParsedService() {}
  
  public ParsedService(ParsedService paramParsedService) {
    super(paramParsedService);
    this.foregroundServiceType = paramParsedService.foregroundServiceType;
    this.permission = paramParsedService.permission;
  }
  
  protected ParsedService(Parcel paramParcel) {
    super(paramParcel);
    this.foregroundServiceType = paramParcel.readInt();
    this.permission = ParsingPackageImpl.sForInternedString.unparcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getForegroundServiceType() {
    return this.foregroundServiceType;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public ParsedMainComponent setPermission(String paramString) {
    if (TextUtils.isEmpty(paramString)) {
      paramString = null;
    } else {
      paramString = paramString.intern();
    } 
    this.permission = paramString;
    return this;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("Service{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(' ');
    ComponentName.appendShortString(stringBuilder, getPackageName(), getName());
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.foregroundServiceType);
    ParsingPackageImpl.sForInternedString.parcel(this.permission, paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */