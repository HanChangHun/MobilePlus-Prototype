package android.app;

import android.accessibilityservice.IAccessibilityServiceClient;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.view.InputEvent;
import android.view.WindowAnimationFrameStats;
import android.view.WindowContentFrameStats;

public abstract class Stub extends Binder implements IUiAutomationConnection {
  private static final String DESCRIPTOR = "android.app.IUiAutomationConnection";
  
  static final int TRANSACTION_adoptShellPermissionIdentity = 14;
  
  static final int TRANSACTION_clearWindowAnimationFrameStats = 9;
  
  static final int TRANSACTION_clearWindowContentFrameStats = 7;
  
  static final int TRANSACTION_connect = 1;
  
  static final int TRANSACTION_disconnect = 2;
  
  static final int TRANSACTION_dropShellPermissionIdentity = 15;
  
  static final int TRANSACTION_executeShellCommand = 11;
  
  static final int TRANSACTION_getWindowAnimationFrameStats = 10;
  
  static final int TRANSACTION_getWindowContentFrameStats = 8;
  
  static final int TRANSACTION_grantRuntimePermission = 12;
  
  static final int TRANSACTION_injectInputEvent = 3;
  
  static final int TRANSACTION_revokeRuntimePermission = 13;
  
  static final int TRANSACTION_setRotation = 5;
  
  static final int TRANSACTION_shutdown = 16;
  
  static final int TRANSACTION_syncInputTransactions = 4;
  
  static final int TRANSACTION_takeScreenshot = 6;
  
  public Stub() {
    attachInterface(this, "android.app.IUiAutomationConnection");
  }
  
  public static IUiAutomationConnection asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IUiAutomationConnection");
    return (iInterface != null && iInterface instanceof IUiAutomationConnection) ? (IUiAutomationConnection)iInterface : new Proxy(paramIBinder);
  }
  
  public static IUiAutomationConnection getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 16:
        return "shutdown";
      case 15:
        return "dropShellPermissionIdentity";
      case 14:
        return "adoptShellPermissionIdentity";
      case 13:
        return "revokeRuntimePermission";
      case 12:
        return "grantRuntimePermission";
      case 11:
        return "executeShellCommand";
      case 10:
        return "getWindowAnimationFrameStats";
      case 9:
        return "clearWindowAnimationFrameStats";
      case 8:
        return "getWindowContentFrameStats";
      case 7:
        return "clearWindowContentFrameStats";
      case 6:
        return "takeScreenshot";
      case 5:
        return "setRotation";
      case 4:
        return "syncInputTransactions";
      case 3:
        return "injectInputEvent";
      case 2:
        return "disconnect";
      case 1:
        break;
    } 
    return "connect";
  }
  
  public static boolean setDefaultImpl(IUiAutomationConnection paramIUiAutomationConnection) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIUiAutomationConnection != null) {
        Proxy.sDefaultImpl = paramIUiAutomationConnection;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902) {
      boolean bool;
      WindowAnimationFrameStats windowAnimationFrameStats;
      WindowContentFrameStats windowContentFrameStats;
      Bitmap bitmap;
      String str;
      ParcelFileDescriptor parcelFileDescriptor;
      boolean bool1 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 16:
          paramParcel1.enforceInterface("android.app.IUiAutomationConnection");
          shutdown();
          return true;
        case 15:
          paramParcel1.enforceInterface("android.app.IUiAutomationConnection");
          dropShellPermissionIdentity();
          paramParcel2.writeNoException();
          return true;
        case 14:
          paramParcel1.enforceInterface("android.app.IUiAutomationConnection");
          adoptShellPermissionIdentity(paramParcel1.readInt(), paramParcel1.createStringArray());
          paramParcel2.writeNoException();
          return true;
        case 13:
          paramParcel1.enforceInterface("android.app.IUiAutomationConnection");
          revokeRuntimePermission(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 12:
          paramParcel1.enforceInterface("android.app.IUiAutomationConnection");
          grantRuntimePermission(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 11:
          paramParcel1.enforceInterface("android.app.IUiAutomationConnection");
          str = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            parcelFileDescriptor = null;
          } 
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          executeShellCommand(str, parcelFileDescriptor, (ParcelFileDescriptor)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 10:
          paramParcel1.enforceInterface("android.app.IUiAutomationConnection");
          windowAnimationFrameStats = getWindowAnimationFrameStats();
          paramParcel2.writeNoException();
          if (windowAnimationFrameStats != null) {
            paramParcel2.writeInt(1);
            windowAnimationFrameStats.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 9:
          windowAnimationFrameStats.enforceInterface("android.app.IUiAutomationConnection");
          clearWindowAnimationFrameStats();
          paramParcel2.writeNoException();
          return true;
        case 8:
          windowAnimationFrameStats.enforceInterface("android.app.IUiAutomationConnection");
          windowContentFrameStats = getWindowContentFrameStats(windowAnimationFrameStats.readInt());
          paramParcel2.writeNoException();
          if (windowContentFrameStats != null) {
            paramParcel2.writeInt(1);
            windowContentFrameStats.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 7:
          windowContentFrameStats.enforceInterface("android.app.IUiAutomationConnection");
          bool = clearWindowContentFrameStats(windowContentFrameStats.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 6:
          windowContentFrameStats.enforceInterface("android.app.IUiAutomationConnection");
          if (windowContentFrameStats.readInt() != 0) {
            Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)windowContentFrameStats);
          } else {
            parcelFileDescriptor = null;
          } 
          bitmap = takeScreenshot((Rect)parcelFileDescriptor, windowContentFrameStats.readInt());
          paramParcel2.writeNoException();
          if (bitmap != null) {
            paramParcel2.writeInt(1);
            bitmap.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 5:
          bitmap.enforceInterface("android.app.IUiAutomationConnection");
          bool = setRotation(bitmap.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 4:
          bitmap.enforceInterface("android.app.IUiAutomationConnection");
          syncInputTransactions();
          paramParcel2.writeNoException();
          return true;
        case 3:
          bitmap.enforceInterface("android.app.IUiAutomationConnection");
          if (bitmap.readInt() != 0) {
            InputEvent inputEvent = (InputEvent)InputEvent.CREATOR.createFromParcel((Parcel)bitmap);
          } else {
            parcelFileDescriptor = null;
          } 
          if (bitmap.readInt() != 0)
            bool1 = true; 
          bool = injectInputEvent((InputEvent)parcelFileDescriptor, bool1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 2:
          bitmap.enforceInterface("android.app.IUiAutomationConnection");
          disconnect();
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      bitmap.enforceInterface("android.app.IUiAutomationConnection");
      connect(IAccessibilityServiceClient.Stub.asInterface(bitmap.readStrongBinder()), bitmap.readInt());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.app.IUiAutomationConnection");
    return true;
  }
  
  private static class Proxy implements IUiAutomationConnection {
    public static IUiAutomationConnection sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void adoptShellPermissionIdentity(int param2Int, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeInt(param2Int);
        parcel1.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          IUiAutomationConnection.Stub.getDefaultImpl().adoptShellPermissionIdentity(param2Int, param2ArrayOfString);
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
    
    public boolean clearWindowContentFrameStats(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          bool = IUiAutomationConnection.Stub.getDefaultImpl().clearWindowContentFrameStats(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void connect(IAccessibilityServiceClient param2IAccessibilityServiceClient, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        if (param2IAccessibilityServiceClient != null) {
          iBinder = param2IAccessibilityServiceClient.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          IUiAutomationConnection.Stub.getDefaultImpl().connect(param2IAccessibilityServiceClient, param2Int);
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
    
    public void executeShellCommand(String param2String, ParcelFileDescriptor param2ParcelFileDescriptor1, ParcelFileDescriptor param2ParcelFileDescriptor2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeString(param2String);
        if (param2ParcelFileDescriptor1 != null) {
          parcel1.writeInt(1);
          param2ParcelFileDescriptor1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ParcelFileDescriptor2 != null) {
          parcel1.writeInt(1);
          param2ParcelFileDescriptor2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          IUiAutomationConnection.Stub.getDefaultImpl().executeShellCommand(param2String, param2ParcelFileDescriptor1, param2ParcelFileDescriptor2);
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
    
    public WindowContentFrameStats getWindowContentFrameStats(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        WindowContentFrameStats windowContentFrameStats;
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          windowContentFrameStats = IUiAutomationConnection.Stub.getDefaultImpl().getWindowContentFrameStats(param2Int);
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
    
    public void grantRuntimePermission(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          IUiAutomationConnection.Stub.getDefaultImpl().grantRuntimePermission(param2String1, param2String2, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean injectInputEvent(InputEvent param2InputEvent, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        boolean bool = true;
        if (param2InputEvent != null) {
          parcel1.writeInt(1);
          param2InputEvent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          param2Boolean = IUiAutomationConnection.Stub.getDefaultImpl().injectInputEvent(param2InputEvent, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void revokeRuntimePermission(String param2String1, String param2String2, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          IUiAutomationConnection.Stub.getDefaultImpl().revokeRuntimePermission(param2String1, param2String2, param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setRotation(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          bool = IUiAutomationConnection.Stub.getDefaultImpl().setRotation(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
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
    
    public Bitmap takeScreenshot(Rect param2Rect, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        if (param2Rect != null) {
          parcel1.writeInt(1);
          param2Rect.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null)
          return IUiAutomationConnection.Stub.getDefaultImpl().takeScreenshot(param2Rect, param2Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel2);
        } else {
          param2Rect = null;
        } 
        return (Bitmap)param2Rect;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUiAutomationConnection$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */