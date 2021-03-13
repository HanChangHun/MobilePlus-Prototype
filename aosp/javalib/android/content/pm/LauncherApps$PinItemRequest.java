package android.content.pm;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PinItemRequest implements Parcelable {
  public static final Parcelable.Creator<PinItemRequest> CREATOR = new Parcelable.Creator<PinItemRequest>() {
      public LauncherApps.PinItemRequest createFromParcel(Parcel param2Parcel) {
        return new LauncherApps.PinItemRequest(param2Parcel);
      }
      
      public LauncherApps.PinItemRequest[] newArray(int param2Int) {
        return new LauncherApps.PinItemRequest[param2Int];
      }
    };
  
  public static final int REQUEST_TYPE_APPWIDGET = 2;
  
  public static final int REQUEST_TYPE_SHORTCUT = 1;
  
  private final IPinItemRequest mInner;
  
  private final int mRequestType;
  
  public PinItemRequest(IPinItemRequest paramIPinItemRequest, int paramInt) {
    this.mInner = paramIPinItemRequest;
    this.mRequestType = paramInt;
  }
  
  private PinItemRequest(Parcel paramParcel) {
    getClass().getClassLoader();
    this.mRequestType = paramParcel.readInt();
    this.mInner = IPinItemRequest.Stub.asInterface(paramParcel.readStrongBinder());
  }
  
  public boolean accept() {
    return accept(null);
  }
  
  public boolean accept(Bundle paramBundle) {
    try {
      return this.mInner.accept(paramBundle);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public AppWidgetProviderInfo getAppWidgetProviderInfo(Context paramContext) {
    try {
      AppWidgetProviderInfo appWidgetProviderInfo = this.mInner.getAppWidgetProviderInfo();
      if (appWidgetProviderInfo == null)
        return null; 
      appWidgetProviderInfo.updateDimensions(paramContext.getResources().getDisplayMetrics());
      return appWidgetProviderInfo;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public Bundle getExtras() {
    try {
      return this.mInner.getExtras();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public int getRequestType() {
    return this.mRequestType;
  }
  
  public ShortcutInfo getShortcutInfo() {
    try {
      return this.mInner.getShortcutInfo();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public boolean isValid() {
    try {
      return this.mInner.isValid();
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mRequestType);
    paramParcel.writeStrongBinder(this.mInner.asBinder());
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RequestType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$PinItemRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */