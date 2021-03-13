package android.appwidget;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AppWidgetProviderInfo> {
  public AppWidgetProviderInfo createFromParcel(Parcel paramParcel) {
    return new AppWidgetProviderInfo(paramParcel);
  }
  
  public AppWidgetProviderInfo[] newArray(int paramInt) {
    return new AppWidgetProviderInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/AppWidgetProviderInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */