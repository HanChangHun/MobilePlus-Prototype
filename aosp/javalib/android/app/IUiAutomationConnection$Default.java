package android.app;

import android.accessibilityservice.IAccessibilityServiceClient;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.view.InputEvent;
import android.view.WindowAnimationFrameStats;
import android.view.WindowContentFrameStats;

public class Default implements IUiAutomationConnection {
  public void adoptShellPermissionIdentity(int paramInt, String[] paramArrayOfString) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void clearWindowAnimationFrameStats() throws RemoteException {}
  
  public boolean clearWindowContentFrameStats(int paramInt) throws RemoteException {
    return false;
  }
  
  public void connect(IAccessibilityServiceClient paramIAccessibilityServiceClient, int paramInt) throws RemoteException {}
  
  public void disconnect() throws RemoteException {}
  
  public void dropShellPermissionIdentity() throws RemoteException {}
  
  public void executeShellCommand(String paramString, ParcelFileDescriptor paramParcelFileDescriptor1, ParcelFileDescriptor paramParcelFileDescriptor2) throws RemoteException {}
  
  public WindowAnimationFrameStats getWindowAnimationFrameStats() throws RemoteException {
    return null;
  }
  
  public WindowContentFrameStats getWindowContentFrameStats(int paramInt) throws RemoteException {
    return null;
  }
  
  public void grantRuntimePermission(String paramString1, String paramString2, int paramInt) throws RemoteException {}
  
  public boolean injectInputEvent(InputEvent paramInputEvent, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public void revokeRuntimePermission(String paramString1, String paramString2, int paramInt) throws RemoteException {}
  
  public boolean setRotation(int paramInt) throws RemoteException {
    return false;
  }
  
  public void shutdown() throws RemoteException {}
  
  public void syncInputTransactions() throws RemoteException {}
  
  public Bitmap takeScreenshot(Rect paramRect, int paramInt) throws RemoteException {
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUiAutomationConnection$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */