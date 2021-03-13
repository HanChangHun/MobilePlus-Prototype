package android.hardware.display;

import android.content.pm.ParceledListSlice;
import android.graphics.Point;
import android.media.projection.IMediaProjection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.DisplayInfo;
import android.view.Surface;

public class Default implements IDisplayManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void connectWifiDisplay(String paramString) throws RemoteException {}
  
  public int createVirtualDisplay(VirtualDisplayConfig paramVirtualDisplayConfig, IVirtualDisplayCallback paramIVirtualDisplayCallback, IMediaProjection paramIMediaProjection, String paramString) throws RemoteException {
    return 0;
  }
  
  public void disconnectWifiDisplay() throws RemoteException {}
  
  public void forgetWifiDisplay(String paramString) throws RemoteException {}
  
  public ParceledListSlice getAmbientBrightnessStats() throws RemoteException {
    return null;
  }
  
  public BrightnessConfiguration getBrightnessConfigurationForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getBrightnessEvents(String paramString) throws RemoteException {
    return null;
  }
  
  public BrightnessConfiguration getDefaultBrightnessConfiguration() throws RemoteException {
    return null;
  }
  
  public int[] getDisplayIds() throws RemoteException {
    return null;
  }
  
  public DisplayInfo getDisplayInfo(int paramInt) throws RemoteException {
    return null;
  }
  
  public Curve getMinimumBrightnessCurve() throws RemoteException {
    return null;
  }
  
  public int getPreferredWideGamutColorSpaceId() throws RemoteException {
    return 0;
  }
  
  public Point getStableDisplaySize() throws RemoteException {
    return null;
  }
  
  public WifiDisplayStatus getWifiDisplayStatus() throws RemoteException {
    return null;
  }
  
  public boolean isMinimalPostProcessingRequested(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isUidPresentOnDisplay(int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public void pauseWifiDisplay() throws RemoteException {}
  
  public void registerCallback(IDisplayManagerCallback paramIDisplayManagerCallback) throws RemoteException {}
  
  public void releaseVirtualDisplay(IVirtualDisplayCallback paramIVirtualDisplayCallback) throws RemoteException {}
  
  public void renameWifiDisplay(String paramString1, String paramString2) throws RemoteException {}
  
  public void requestColorMode(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void resizeVirtualDisplay(IVirtualDisplayCallback paramIVirtualDisplayCallback, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void resumeWifiDisplay() throws RemoteException {}
  
  public void setBrightnessConfigurationForUser(BrightnessConfiguration paramBrightnessConfiguration, int paramInt, String paramString) throws RemoteException {}
  
  public void setTemporaryAutoBrightnessAdjustment(float paramFloat) throws RemoteException {}
  
  public void setTemporaryBrightness(float paramFloat) throws RemoteException {}
  
  public void setVirtualDisplayState(IVirtualDisplayCallback paramIVirtualDisplayCallback, boolean paramBoolean) throws RemoteException {}
  
  public void setVirtualDisplaySurface(IVirtualDisplayCallback paramIVirtualDisplayCallback, Surface paramSurface) throws RemoteException {}
  
  public void startWifiDisplayScan() throws RemoteException {}
  
  public void stopWifiDisplayScan() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IDisplayManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */