package android.appwidget;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;

public class PendingHostUpdate implements Parcelable {
  public static final Parcelable.Creator<PendingHostUpdate> CREATOR = new Parcelable.Creator<PendingHostUpdate>() {
      public PendingHostUpdate createFromParcel(Parcel param1Parcel) {
        return new PendingHostUpdate(param1Parcel);
      }
      
      public PendingHostUpdate[] newArray(int param1Int) {
        return new PendingHostUpdate[param1Int];
      }
    };
  
  static final int TYPE_APP_WIDGET_REMOVED = 3;
  
  static final int TYPE_PROVIDER_CHANGED = 1;
  
  static final int TYPE_VIEWS_UPDATE = 0;
  
  static final int TYPE_VIEW_DATA_CHANGED = 2;
  
  final int appWidgetId;
  
  final int type;
  
  int viewId;
  
  RemoteViews views;
  
  AppWidgetProviderInfo widgetInfo;
  
  private PendingHostUpdate(int paramInt1, int paramInt2) {
    this.appWidgetId = paramInt1;
    this.type = paramInt2;
  }
  
  private PendingHostUpdate(Parcel paramParcel) {
    this.appWidgetId = paramParcel.readInt();
    int i = paramParcel.readInt();
    this.type = i;
    if (i != 0) {
      if (i != 1) {
        if (i == 2)
          this.viewId = paramParcel.readInt(); 
      } else if (paramParcel.readInt() != 0) {
        this.widgetInfo = new AppWidgetProviderInfo(paramParcel);
      } 
    } else if (paramParcel.readInt() != 0) {
      this.views = new RemoteViews(paramParcel);
    } 
  }
  
  public static PendingHostUpdate appWidgetRemoved(int paramInt) {
    return new PendingHostUpdate(paramInt, 3);
  }
  
  public static PendingHostUpdate providerChanged(int paramInt, AppWidgetProviderInfo paramAppWidgetProviderInfo) {
    PendingHostUpdate pendingHostUpdate = new PendingHostUpdate(paramInt, 1);
    pendingHostUpdate.widgetInfo = paramAppWidgetProviderInfo;
    return pendingHostUpdate;
  }
  
  public static PendingHostUpdate updateAppWidget(int paramInt, RemoteViews paramRemoteViews) {
    PendingHostUpdate pendingHostUpdate = new PendingHostUpdate(paramInt, 0);
    pendingHostUpdate.views = paramRemoteViews;
    return pendingHostUpdate;
  }
  
  public static PendingHostUpdate viewDataChanged(int paramInt1, int paramInt2) {
    PendingHostUpdate pendingHostUpdate = new PendingHostUpdate(paramInt1, 2);
    pendingHostUpdate.viewId = paramInt2;
    return pendingHostUpdate;
  }
  
  private void writeNullParcelable(Parcelable paramParcelable, Parcel paramParcel, int paramInt) {
    if (paramParcelable != null) {
      paramParcel.writeInt(1);
      paramParcelable.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.appWidgetId);
    paramParcel.writeInt(this.type);
    int i = this.type;
    if (i != 0) {
      if (i != 1) {
        if (i == 2)
          paramParcel.writeInt(this.viewId); 
      } else {
        writeNullParcelable(this.widgetInfo, paramParcel, paramInt);
      } 
    } else {
      writeNullParcelable((Parcelable)this.views, paramParcel, paramInt);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/PendingHostUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */