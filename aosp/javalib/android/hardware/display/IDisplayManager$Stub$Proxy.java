package android.hardware.display;

import android.content.pm.ParceledListSlice;
import android.graphics.Point;
import android.media.projection.IMediaProjection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.DisplayInfo;
import android.view.Surface;

class Proxy implements IDisplayManager {
  public static IDisplayManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void connectWifiDisplay(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().connectWifiDisplay(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int createVirtualDisplay(VirtualDisplayConfig paramVirtualDisplayConfig, IVirtualDisplayCallback paramIVirtualDisplayCallback, IMediaProjection paramIMediaProjection, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (paramVirtualDisplayConfig != null) {
        parcel1.writeInt(1);
        paramVirtualDisplayConfig.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      IBinder iBinder1 = null;
      if (paramIVirtualDisplayCallback != null) {
        iBinder2 = paramIVirtualDisplayCallback.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIMediaProjection != null)
        iBinder2 = paramIMediaProjection.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null)
        return IDisplayManager.Stub.getDefaultImpl().createVirtualDisplay(paramVirtualDisplayConfig, paramIVirtualDisplayCallback, paramIMediaProjection, paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void disconnectWifiDisplay() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().disconnectWifiDisplay();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void forgetWifiDisplay(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().forgetWifiDisplay(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getAmbientBrightnessStats() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IDisplayManager.Stub.getDefaultImpl().getAmbientBrightnessStats();
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public BrightnessConfiguration getBrightnessConfigurationForUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      BrightnessConfiguration brightnessConfiguration;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        brightnessConfiguration = IDisplayManager.Stub.getDefaultImpl().getBrightnessConfigurationForUser(paramInt);
        return brightnessConfiguration;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        brightnessConfiguration = (BrightnessConfiguration)BrightnessConfiguration.CREATOR.createFromParcel(parcel2);
      } else {
        brightnessConfiguration = null;
      } 
      return brightnessConfiguration;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getBrightnessEvents(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null)
        return IDisplayManager.Stub.getDefaultImpl().getBrightnessEvents(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public BrightnessConfiguration getDefaultBrightnessConfiguration() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      BrightnessConfiguration brightnessConfiguration;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        brightnessConfiguration = IDisplayManager.Stub.getDefaultImpl().getDefaultBrightnessConfiguration();
        return brightnessConfiguration;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        brightnessConfiguration = (BrightnessConfiguration)BrightnessConfiguration.CREATOR.createFromParcel(parcel2);
      } else {
        brightnessConfiguration = null;
      } 
      return brightnessConfiguration;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int[] getDisplayIds() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null)
        return IDisplayManager.Stub.getDefaultImpl().getDisplayIds(); 
      parcel2.readException();
      return parcel2.createIntArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public DisplayInfo getDisplayInfo(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      DisplayInfo displayInfo;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        displayInfo = IDisplayManager.Stub.getDefaultImpl().getDisplayInfo(paramInt);
        return displayInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        displayInfo = (DisplayInfo)DisplayInfo.CREATOR.createFromParcel(parcel2);
      } else {
        displayInfo = null;
      } 
      return displayInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.display.IDisplayManager";
  }
  
  public Curve getMinimumBrightnessCurve() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Curve curve;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        curve = IDisplayManager.Stub.getDefaultImpl().getMinimumBrightnessCurve();
        return curve;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        curve = (Curve)Curve.CREATOR.createFromParcel(parcel2);
      } else {
        curve = null;
      } 
      return curve;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPreferredWideGamutColorSpaceId() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null)
        return IDisplayManager.Stub.getDefaultImpl().getPreferredWideGamutColorSpaceId(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Point getStableDisplaySize() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Point point;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        point = IDisplayManager.Stub.getDefaultImpl().getStableDisplaySize();
        return point;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        point = (Point)Point.CREATOR.createFromParcel(parcel2);
      } else {
        point = null;
      } 
      return point;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public WifiDisplayStatus getWifiDisplayStatus() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      WifiDisplayStatus wifiDisplayStatus;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        wifiDisplayStatus = IDisplayManager.Stub.getDefaultImpl().getWifiDisplayStatus();
        return wifiDisplayStatus;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        wifiDisplayStatus = (WifiDisplayStatus)WifiDisplayStatus.CREATOR.createFromParcel(parcel2);
      } else {
        wifiDisplayStatus = null;
      } 
      return wifiDisplayStatus;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isMinimalPostProcessingRequested(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(26, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        bool = IDisplayManager.Stub.getDefaultImpl().isMinimalPostProcessingRequested(paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isUidPresentOnDisplay(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(3, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        bool = IDisplayManager.Stub.getDefaultImpl().isUidPresentOnDisplay(paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void pauseWifiDisplay() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().pauseWifiDisplay();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerCallback(IDisplayManagerCallback paramIDisplayManagerCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (paramIDisplayManagerCallback != null) {
        iBinder = paramIDisplayManagerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().registerCallback(paramIDisplayManagerCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void releaseVirtualDisplay(IVirtualDisplayCallback paramIVirtualDisplayCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (paramIVirtualDisplayCallback != null) {
        iBinder = paramIVirtualDisplayCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().releaseVirtualDisplay(paramIVirtualDisplayCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void renameWifiDisplay(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().renameWifiDisplay(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestColorMode(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().requestColorMode(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resizeVirtualDisplay(IVirtualDisplayCallback paramIVirtualDisplayCallback, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (paramIVirtualDisplayCallback != null) {
        iBinder = paramIVirtualDisplayCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().resizeVirtualDisplay(paramIVirtualDisplayCallback, paramInt1, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resumeWifiDisplay() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().resumeWifiDisplay();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setBrightnessConfigurationForUser(BrightnessConfiguration paramBrightnessConfiguration, int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (paramBrightnessConfiguration != null) {
        parcel1.writeInt(1);
        paramBrightnessConfiguration.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().setBrightnessConfigurationForUser(paramBrightnessConfiguration, paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setTemporaryAutoBrightnessAdjustment(float paramFloat) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeFloat(paramFloat);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().setTemporaryAutoBrightnessAdjustment(paramFloat);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setTemporaryBrightness(float paramFloat) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      parcel1.writeFloat(paramFloat);
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().setTemporaryBrightness(paramFloat);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setVirtualDisplayState(IVirtualDisplayCallback paramIVirtualDisplayCallback, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (paramIVirtualDisplayCallback != null) {
        iBinder = paramIVirtualDisplayCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().setVirtualDisplayState(paramIVirtualDisplayCallback, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setVirtualDisplaySurface(IVirtualDisplayCallback paramIVirtualDisplayCallback, Surface paramSurface) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (paramIVirtualDisplayCallback != null) {
        iBinder = paramIVirtualDisplayCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramSurface != null) {
        parcel1.writeInt(1);
        paramSurface.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().setVirtualDisplaySurface(paramIVirtualDisplayCallback, paramSurface);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startWifiDisplayScan() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().startWifiDisplayScan();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stopWifiDisplayScan() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
        IDisplayManager.Stub.getDefaultImpl().stopWifiDisplayScan();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IDisplayManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */