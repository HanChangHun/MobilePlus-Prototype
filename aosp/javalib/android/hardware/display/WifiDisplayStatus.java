package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class WifiDisplayStatus implements Parcelable {
  public static final Parcelable.Creator<WifiDisplayStatus> CREATOR = new Parcelable.Creator<WifiDisplayStatus>() {
      public WifiDisplayStatus createFromParcel(Parcel param1Parcel) {
        WifiDisplay wifiDisplay;
        int i = param1Parcel.readInt();
        int j = param1Parcel.readInt();
        int k = param1Parcel.readInt();
        if (param1Parcel.readInt() != 0) {
          wifiDisplay = (WifiDisplay)WifiDisplay.CREATOR.createFromParcel(param1Parcel);
        } else {
          wifiDisplay = null;
        } 
        WifiDisplay[] arrayOfWifiDisplay = (WifiDisplay[])WifiDisplay.CREATOR.newArray(param1Parcel.readInt());
        for (byte b = 0; b < arrayOfWifiDisplay.length; b++)
          arrayOfWifiDisplay[b] = (WifiDisplay)WifiDisplay.CREATOR.createFromParcel(param1Parcel); 
        return new WifiDisplayStatus(i, j, k, wifiDisplay, arrayOfWifiDisplay, (WifiDisplaySessionInfo)WifiDisplaySessionInfo.CREATOR.createFromParcel(param1Parcel));
      }
      
      public WifiDisplayStatus[] newArray(int param1Int) {
        return new WifiDisplayStatus[param1Int];
      }
    };
  
  public static final int DISPLAY_STATE_CONNECTED = 2;
  
  public static final int DISPLAY_STATE_CONNECTING = 1;
  
  public static final int DISPLAY_STATE_NOT_CONNECTED = 0;
  
  public static final int FEATURE_STATE_DISABLED = 1;
  
  public static final int FEATURE_STATE_OFF = 2;
  
  public static final int FEATURE_STATE_ON = 3;
  
  public static final int FEATURE_STATE_UNAVAILABLE = 0;
  
  public static final int SCAN_STATE_NOT_SCANNING = 0;
  
  public static final int SCAN_STATE_SCANNING = 1;
  
  private final WifiDisplay mActiveDisplay;
  
  private final int mActiveDisplayState;
  
  private final WifiDisplay[] mDisplays;
  
  private final int mFeatureState;
  
  private final int mScanState;
  
  private final WifiDisplaySessionInfo mSessionInfo;
  
  public WifiDisplayStatus() {
    this(0, 0, 0, null, WifiDisplay.EMPTY_ARRAY, null);
  }
  
  public WifiDisplayStatus(int paramInt1, int paramInt2, int paramInt3, WifiDisplay paramWifiDisplay, WifiDisplay[] paramArrayOfWifiDisplay, WifiDisplaySessionInfo paramWifiDisplaySessionInfo) {
    if (paramArrayOfWifiDisplay != null) {
      WifiDisplaySessionInfo wifiDisplaySessionInfo;
      this.mFeatureState = paramInt1;
      this.mScanState = paramInt2;
      this.mActiveDisplayState = paramInt3;
      this.mActiveDisplay = paramWifiDisplay;
      this.mDisplays = paramArrayOfWifiDisplay;
      if (paramWifiDisplaySessionInfo != null) {
        wifiDisplaySessionInfo = paramWifiDisplaySessionInfo;
      } else {
        wifiDisplaySessionInfo = new WifiDisplaySessionInfo();
      } 
      this.mSessionInfo = wifiDisplaySessionInfo;
      return;
    } 
    throw new IllegalArgumentException("displays must not be null");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public WifiDisplay getActiveDisplay() {
    return this.mActiveDisplay;
  }
  
  public int getActiveDisplayState() {
    return this.mActiveDisplayState;
  }
  
  public WifiDisplay[] getDisplays() {
    return this.mDisplays;
  }
  
  public int getFeatureState() {
    return this.mFeatureState;
  }
  
  public int getScanState() {
    return this.mScanState;
  }
  
  public WifiDisplaySessionInfo getSessionInfo() {
    return this.mSessionInfo;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("WifiDisplayStatus{featureState=");
    stringBuilder.append(this.mFeatureState);
    stringBuilder.append(", scanState=");
    stringBuilder.append(this.mScanState);
    stringBuilder.append(", activeDisplayState=");
    stringBuilder.append(this.mActiveDisplayState);
    stringBuilder.append(", activeDisplay=");
    stringBuilder.append(this.mActiveDisplay);
    stringBuilder.append(", displays=");
    stringBuilder.append(Arrays.toString((Object[])this.mDisplays));
    stringBuilder.append(", sessionInfo=");
    stringBuilder.append(this.mSessionInfo);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mFeatureState);
    paramParcel.writeInt(this.mScanState);
    paramParcel.writeInt(this.mActiveDisplayState);
    WifiDisplay wifiDisplay = this.mActiveDisplay;
    byte b = 0;
    if (wifiDisplay != null) {
      paramParcel.writeInt(1);
      this.mActiveDisplay.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.mDisplays.length);
    WifiDisplay[] arrayOfWifiDisplay = this.mDisplays;
    int i = arrayOfWifiDisplay.length;
    while (b < i) {
      arrayOfWifiDisplay[b].writeToParcel(paramParcel, paramInt);
      b++;
    } 
    this.mSessionInfo.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/WifiDisplayStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */