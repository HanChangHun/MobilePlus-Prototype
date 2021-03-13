package android.app;

import android.accessibilityservice.IAccessibilityServiceClient;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.view.InputEvent;
import android.view.WindowAnimationFrameStats;
import android.view.WindowContentFrameStats;

class Proxy implements IUiAutomationConnection {
  public static IUiAutomationConnection sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void adoptShellPermissionIdentity(int paramInt, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      parcel1.writeInt(paramInt);
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        IUiAutomationConnection.Stub.getDefaultImpl().adoptShellPermissionIdentity(paramInt, paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void clearWindowAnimationFrameStats() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        IUiAutomationConnection.Stub.getDefaultImpl().clearWindowAnimationFrameStats();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean clearWindowContentFrameStats(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(7, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        bool = IUiAutomationConnection.Stub.getDefaultImpl().clearWindowContentFrameStats(paramInt);
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
  
  public void connect(IAccessibilityServiceClient paramIAccessibilityServiceClient, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      if (paramIAccessibilityServiceClient != null) {
        iBinder = paramIAccessibilityServiceClient.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        IUiAutomationConnection.Stub.getDefaultImpl().connect(paramIAccessibilityServiceClient, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void disconnect() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        IUiAutomationConnection.Stub.getDefaultImpl().disconnect();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void dropShellPermissionIdentity() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        IUiAutomationConnection.Stub.getDefaultImpl().dropShellPermissionIdentity();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void executeShellCommand(String paramString, ParcelFileDescriptor paramParcelFileDescriptor1, ParcelFileDescriptor paramParcelFileDescriptor2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      parcel1.writeString(paramString);
      if (paramParcelFileDescriptor1 != null) {
        parcel1.writeInt(1);
        paramParcelFileDescriptor1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramParcelFileDescriptor2 != null) {
        parcel1.writeInt(1);
        paramParcelFileDescriptor2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        IUiAutomationConnection.Stub.getDefaultImpl().executeShellCommand(paramString, paramParcelFileDescriptor1, paramParcelFileDescriptor2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IUiAutomationConnection";
  }
  
  public WindowAnimationFrameStats getWindowAnimationFrameStats() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      WindowAnimationFrameStats windowAnimationFrameStats;
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        windowAnimationFrameStats = IUiAutomationConnection.Stub.getDefaultImpl().getWindowAnimationFrameStats();
        return windowAnimationFrameStats;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        windowAnimationFrameStats = (WindowAnimationFrameStats)WindowAnimationFrameStats.CREATOR.createFromParcel(parcel2);
      } else {
        windowAnimationFrameStats = null;
      } 
      return windowAnimationFrameStats;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public WindowContentFrameStats getWindowContentFrameStats(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      WindowContentFrameStats windowContentFrameStats;
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        windowContentFrameStats = IUiAutomationConnection.Stub.getDefaultImpl().getWindowContentFrameStats(paramInt);
        return windowContentFrameStats;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        windowContentFrameStats = (WindowContentFrameStats)WindowContentFrameStats.CREATOR.createFromParcel(parcel2);
      } else {
        windowContentFrameStats = null;
      } 
      return windowContentFrameStats;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void grantRuntimePermission(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        IUiAutomationConnection.Stub.getDefaultImpl().grantRuntimePermission(paramString1, paramString2, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean injectInputEvent(InputEvent paramInputEvent, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      boolean bool = true;
      if (paramInputEvent != null) {
        parcel1.writeInt(1);
        paramInputEvent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        paramBoolean = IUiAutomationConnection.Stub.getDefaultImpl().injectInputEvent(paramInputEvent, paramBoolean);
        return paramBoolean;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0) {
        paramBoolean = bool;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void revokeRuntimePermission(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        IUiAutomationConnection.Stub.getDefaultImpl().revokeRuntimePermission(paramString1, paramString2, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setRotation(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(5, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        bool = IUiAutomationConnection.Stub.getDefaultImpl().setRotation(paramInt);
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
  
  public void shutdown() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IUiAutomationConnection");
      if (!this.mRemote.transact(16, parcel, null, 1) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        IUiAutomationConnection.Stub.getDefaultImpl().shutdown();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void syncInputTransactions() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
        IUiAutomationConnection.Stub.getDefaultImpl().syncInputTransactions();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bitmap takeScreenshot(Rect paramRect, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
      if (paramRect != null) {
        parcel1.writeInt(1);
        paramRect.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null)
        return IUiAutomationConnection.Stub.getDefaultImpl().takeScreenshot(paramRect, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel2);
      } else {
        paramRect = null;
      } 
      return (Bitmap)paramRect;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUiAutomationConnection$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */