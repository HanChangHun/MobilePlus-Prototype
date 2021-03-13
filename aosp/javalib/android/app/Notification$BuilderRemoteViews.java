package android.app;

import android.content.pm.ApplicationInfo;
import android.os.Parcel;
import android.widget.RemoteViews;

class BuilderRemoteViews extends RemoteViews {
  public BuilderRemoteViews(ApplicationInfo paramApplicationInfo, int paramInt) {
    super(paramApplicationInfo, paramInt);
  }
  
  public BuilderRemoteViews(Parcel paramParcel) {
    super(paramParcel);
  }
  
  public BuilderRemoteViews clone() {
    Parcel parcel = Parcel.obtain();
    writeToParcel(parcel, 0);
    parcel.setDataPosition(0);
    BuilderRemoteViews builderRemoteViews = new BuilderRemoteViews(parcel);
    parcel.recycle();
    return builderRemoteViews;
  }
  
  protected boolean shouldUseStaticFilter() {
    return true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$BuilderRemoteViews.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */