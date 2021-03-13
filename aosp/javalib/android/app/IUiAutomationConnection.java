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

public interface IUiAutomationConnection extends IInterface {
  void adoptShellPermissionIdentity(int paramInt, String[] paramArrayOfString) throws RemoteException;
  
  void clearWindowAnimationFrameStats() throws RemoteException;
  
  boolean clearWindowContentFrameStats(int paramInt) throws RemoteException;
  
  void connect(IAccessibilityServiceClient paramIAccessibilityServiceClient, int paramInt) throws RemoteException;
  
  void disconnect() throws RemoteException;
  
  void dropShellPermissionIdentity() throws RemoteException;
  
  void executeShellCommand(String paramString, ParcelFileDescriptor paramParcelFileDescriptor1, ParcelFileDescriptor paramParcelFileDescriptor2) throws RemoteException;
  
  WindowAnimationFrameStats getWindowAnimationFrameStats() throws RemoteException;
  
  WindowContentFrameStats getWindowContentFrameStats(int paramInt) throws RemoteException;
  
  void grantRuntimePermission(String paramString1, String paramString2, int paramInt) throws RemoteException;
  
  boolean injectInputEvent(InputEvent paramInputEvent, boolean paramBoolean) throws RemoteException;
  
  void revokeRuntimePermission(String paramString1, String paramString2, int paramInt) throws RemoteException;
  
  boolean setRotation(int paramInt) throws RemoteException;
  
  void shutdown() throws RemoteException;
  
  void syncInputTransactions() throws RemoteException;
  
  Bitmap takeScreenshot(Rect paramRect, int paramInt) throws RemoteException;
  
  public static class Default implements IUiAutomationConnection {
    public void adoptShellPermissionIdentity(int param1Int, String[] param1ArrayOfString) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
    
    public void clearWindowAnimationFrameStats() throws RemoteException {}
    
    public boolean clearWindowContentFrameStats(int param1Int) throws RemoteException {
      return false;
    }
    
    public void connect(IAccessibilityServiceClient param1IAccessibilityServiceClient, int param1Int) throws RemoteException {}
    
    public void disconnect() throws RemoteException {}
    
    public void dropShellPermissionIdentity() throws RemoteException {}
    
    public void executeShellCommand(String param1String, ParcelFileDescriptor param1ParcelFileDescriptor1, ParcelFileDescriptor param1ParcelFileDescriptor2) throws RemoteException {}
    
    public WindowAnimationFrameStats getWindowAnimationFrameStats() throws RemoteException {
      return null;
    }
    
    public WindowContentFrameStats getWindowContentFrameStats(int param1Int) throws RemoteException {
      return null;
    }
    
    public void grantRuntimePermission(String param1String1, String param1String2, int param1Int) throws RemoteException {}
    
    public boolean injectInputEvent(InputEvent param1InputEvent, boolean param1Boolean) throws RemoteException {
      return false;
    }
    
    public void revokeRuntimePermission(String param1String1, String param1String2, int param1Int) throws RemoteException {}
    
    public boolean setRotation(int param1Int) throws RemoteException {
      return false;
    }
    
    public void shutdown() throws RemoteException {}
    
    public void syncInputTransactions() throws RemoteException {}
    
    public Bitmap takeScreenshot(Rect param1Rect, int param1Int) throws RemoteException {
      return null;
    }
  }
  
  public static abstract class Stub extends Binder implements IUiAutomationConnection {
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
    
    public static IUiAutomationConnection asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IUiAutomationConnection");
      return (iInterface != null && iInterface instanceof IUiAutomationConnection) ? (IUiAutomationConnection)iInterface : new Proxy(param1IBinder);
    }
    
    public static IUiAutomationConnection getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IUiAutomationConnection param1IUiAutomationConnection) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IUiAutomationConnection != null) {
          Proxy.sDefaultImpl = param1IUiAutomationConnection;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        boolean bool;
        WindowAnimationFrameStats windowAnimationFrameStats;
        WindowContentFrameStats windowContentFrameStats;
        Bitmap bitmap;
        String str;
        ParcelFileDescriptor parcelFileDescriptor;
        boolean bool1 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 16:
            param1Parcel1.enforceInterface("android.app.IUiAutomationConnection");
            shutdown();
            return true;
          case 15:
            param1Parcel1.enforceInterface("android.app.IUiAutomationConnection");
            dropShellPermissionIdentity();
            param1Parcel2.writeNoException();
            return true;
          case 14:
            param1Parcel1.enforceInterface("android.app.IUiAutomationConnection");
            adoptShellPermissionIdentity(param1Parcel1.readInt(), param1Parcel1.createStringArray());
            param1Parcel2.writeNoException();
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.app.IUiAutomationConnection");
            revokeRuntimePermission(param1Parcel1.readString(), param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.app.IUiAutomationConnection");
            grantRuntimePermission(param1Parcel1.readString(), param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 11:
            param1Parcel1.enforceInterface("android.app.IUiAutomationConnection");
            str = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              parcelFileDescriptor = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            executeShellCommand(str, parcelFileDescriptor, (ParcelFileDescriptor)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.app.IUiAutomationConnection");
            windowAnimationFrameStats = getWindowAnimationFrameStats();
            param1Parcel2.writeNoException();
            if (windowAnimationFrameStats != null) {
              param1Parcel2.writeInt(1);
              windowAnimationFrameStats.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 9:
            windowAnimationFrameStats.enforceInterface("android.app.IUiAutomationConnection");
            clearWindowAnimationFrameStats();
            param1Parcel2.writeNoException();
            return true;
          case 8:
            windowAnimationFrameStats.enforceInterface("android.app.IUiAutomationConnection");
            windowContentFrameStats = getWindowContentFrameStats(windowAnimationFrameStats.readInt());
            param1Parcel2.writeNoException();
            if (windowContentFrameStats != null) {
              param1Parcel2.writeInt(1);
              windowContentFrameStats.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 7:
            windowContentFrameStats.enforceInterface("android.app.IUiAutomationConnection");
            bool = clearWindowContentFrameStats(windowContentFrameStats.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 6:
            windowContentFrameStats.enforceInterface("android.app.IUiAutomationConnection");
            if (windowContentFrameStats.readInt() != 0) {
              Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)windowContentFrameStats);
            } else {
              parcelFileDescriptor = null;
            } 
            bitmap = takeScreenshot((Rect)parcelFileDescriptor, windowContentFrameStats.readInt());
            param1Parcel2.writeNoException();
            if (bitmap != null) {
              param1Parcel2.writeInt(1);
              bitmap.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 5:
            bitmap.enforceInterface("android.app.IUiAutomationConnection");
            bool = setRotation(bitmap.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 4:
            bitmap.enforceInterface("android.app.IUiAutomationConnection");
            syncInputTransactions();
            param1Parcel2.writeNoException();
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
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 2:
            bitmap.enforceInterface("android.app.IUiAutomationConnection");
            disconnect();
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        bitmap.enforceInterface("android.app.IUiAutomationConnection");
        connect(IAccessibilityServiceClient.Stub.asInterface(bitmap.readStrongBinder()), bitmap.readInt());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.app.IUiAutomationConnection");
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
  
  private static class Proxy implements IUiAutomationConnection {
    public static IUiAutomationConnection sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void adoptShellPermissionIdentity(int param1Int, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeInt(param1Int);
        parcel1.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          IUiAutomationConnection.Stub.getDefaultImpl().adoptShellPermissionIdentity(param1Int, param1ArrayOfString);
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
    
    public boolean clearWindowContentFrameStats(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          bool = IUiAutomationConnection.Stub.getDefaultImpl().clearWindowContentFrameStats(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void connect(IAccessibilityServiceClient param1IAccessibilityServiceClient, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        if (param1IAccessibilityServiceClient != null) {
          iBinder = param1IAccessibilityServiceClient.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          IUiAutomationConnection.Stub.getDefaultImpl().connect(param1IAccessibilityServiceClient, param1Int);
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
    
    public void executeShellCommand(String param1String, ParcelFileDescriptor param1ParcelFileDescriptor1, ParcelFileDescriptor param1ParcelFileDescriptor2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeString(param1String);
        if (param1ParcelFileDescriptor1 != null) {
          parcel1.writeInt(1);
          param1ParcelFileDescriptor1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1ParcelFileDescriptor2 != null) {
          parcel1.writeInt(1);
          param1ParcelFileDescriptor2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          IUiAutomationConnection.Stub.getDefaultImpl().executeShellCommand(param1String, param1ParcelFileDescriptor1, param1ParcelFileDescriptor2);
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
    
    public WindowContentFrameStats getWindowContentFrameStats(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        WindowContentFrameStats windowContentFrameStats;
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          windowContentFrameStats = IUiAutomationConnection.Stub.getDefaultImpl().getWindowContentFrameStats(param1Int);
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
    
    public void grantRuntimePermission(String param1String1, String param1String2, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          IUiAutomationConnection.Stub.getDefaultImpl().grantRuntimePermission(param1String1, param1String2, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean injectInputEvent(InputEvent param1InputEvent, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        boolean bool = true;
        if (param1InputEvent != null) {
          parcel1.writeInt(1);
          param1InputEvent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          param1Boolean = IUiAutomationConnection.Stub.getDefaultImpl().injectInputEvent(param1InputEvent, param1Boolean);
          return param1Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param1Boolean = bool;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void revokeRuntimePermission(String param1String1, String param1String2, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          IUiAutomationConnection.Stub.getDefaultImpl().revokeRuntimePermission(param1String1, param1String2, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setRotation(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null) {
          bool = IUiAutomationConnection.Stub.getDefaultImpl().setRotation(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
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
    
    public Bitmap takeScreenshot(Rect param1Rect, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IUiAutomationConnection");
        if (param1Rect != null) {
          parcel1.writeInt(1);
          param1Rect.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IUiAutomationConnection.Stub.getDefaultImpl() != null)
          return IUiAutomationConnection.Stub.getDefaultImpl().takeScreenshot(param1Rect, param1Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel2);
        } else {
          param1Rect = null;
        } 
        return (Bitmap)param1Rect;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUiAutomationConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */