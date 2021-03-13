package android.accessibilityservice;

import android.graphics.Region;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public abstract class Stub extends Binder implements IAccessibilityServiceClient {
  private static final String DESCRIPTOR = "android.accessibilityservice.IAccessibilityServiceClient";
  
  static final int TRANSACTION_clearAccessibilityCache = 5;
  
  static final int TRANSACTION_init = 1;
  
  static final int TRANSACTION_onAccessibilityButtonAvailabilityChanged = 13;
  
  static final int TRANSACTION_onAccessibilityButtonClicked = 12;
  
  static final int TRANSACTION_onAccessibilityEvent = 2;
  
  static final int TRANSACTION_onFingerprintCapturingGesturesChanged = 10;
  
  static final int TRANSACTION_onFingerprintGesture = 11;
  
  static final int TRANSACTION_onGesture = 4;
  
  static final int TRANSACTION_onInterrupt = 3;
  
  static final int TRANSACTION_onKeyEvent = 6;
  
  static final int TRANSACTION_onMagnificationChanged = 7;
  
  static final int TRANSACTION_onPerformGestureResult = 9;
  
  static final int TRANSACTION_onSoftKeyboardShowModeChanged = 8;
  
  static final int TRANSACTION_onSystemActionsChanged = 14;
  
  public Stub() {
    attachInterface(this, "android.accessibilityservice.IAccessibilityServiceClient");
  }
  
  public static IAccessibilityServiceClient asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.accessibilityservice.IAccessibilityServiceClient");
    return (iInterface != null && iInterface instanceof IAccessibilityServiceClient) ? (IAccessibilityServiceClient)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAccessibilityServiceClient getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 14:
        return "onSystemActionsChanged";
      case 13:
        return "onAccessibilityButtonAvailabilityChanged";
      case 12:
        return "onAccessibilityButtonClicked";
      case 11:
        return "onFingerprintGesture";
      case 10:
        return "onFingerprintCapturingGesturesChanged";
      case 9:
        return "onPerformGestureResult";
      case 8:
        return "onSoftKeyboardShowModeChanged";
      case 7:
        return "onMagnificationChanged";
      case 6:
        return "onKeyEvent";
      case 5:
        return "clearAccessibilityCache";
      case 4:
        return "onGesture";
      case 3:
        return "onInterrupt";
      case 2:
        return "onAccessibilityEvent";
      case 1:
        break;
    } 
    return "init";
  }
  
  public static boolean setDefaultImpl(IAccessibilityServiceClient paramIAccessibilityServiceClient) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAccessibilityServiceClient != null) {
        Proxy.sDefaultImpl = paramIAccessibilityServiceClient;
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
      boolean bool1 = false;
      boolean bool2 = false;
      boolean bool3 = false;
      boolean bool4 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 14:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          onSystemActionsChanged();
          return true;
        case 13:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          onAccessibilityButtonAvailabilityChanged(bool4);
          return true;
        case 12:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          onAccessibilityButtonClicked(paramParcel1.readInt());
          return true;
        case 11:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          onFingerprintGesture(paramParcel1.readInt());
          return true;
        case 10:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          bool4 = bool1;
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          onFingerprintCapturingGesturesChanged(bool4);
          return true;
        case 9:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          paramInt1 = paramParcel1.readInt();
          bool4 = bool2;
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          onPerformGestureResult(paramInt1, bool4);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          onSoftKeyboardShowModeChanged(paramParcel1.readInt());
          return true;
        case 7:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            Region region = (Region)Region.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          onMagnificationChanged(paramInt1, (Region)paramParcel2, paramParcel1.readFloat(), paramParcel1.readFloat(), paramParcel1.readFloat());
          return true;
        case 6:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          if (paramParcel1.readInt() != 0) {
            KeyEvent keyEvent = (KeyEvent)KeyEvent.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          onKeyEvent((KeyEvent)paramParcel2, paramParcel1.readInt());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          clearAccessibilityCache();
          return true;
        case 4:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          if (paramParcel1.readInt() != 0) {
            AccessibilityGestureEvent accessibilityGestureEvent = (AccessibilityGestureEvent)AccessibilityGestureEvent.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onGesture((AccessibilityGestureEvent)paramParcel1);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          onInterrupt();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
          if (paramParcel1.readInt() != 0) {
            AccessibilityEvent accessibilityEvent = (AccessibilityEvent)AccessibilityEvent.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          bool4 = bool3;
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          onAccessibilityEvent((AccessibilityEvent)paramParcel2, bool4);
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
      init(IAccessibilityServiceConnection.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readStrongBinder());
      return true;
    } 
    paramParcel2.writeString("android.accessibilityservice.IAccessibilityServiceClient");
    return true;
  }
  
  private static class Proxy implements IAccessibilityServiceClient {
    public static IAccessibilityServiceClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void clearAccessibilityCache() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (!this.mRemote.transact(5, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().clearAccessibilityCache();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.accessibilityservice.IAccessibilityServiceClient";
    }
    
    public void init(IAccessibilityServiceConnection param2IAccessibilityServiceConnection, int param2Int, IBinder param2IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (param2IAccessibilityServiceConnection != null) {
          iBinder = param2IAccessibilityServiceConnection.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeInt(param2Int);
        parcel.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().init(param2IAccessibilityServiceConnection, param2Int, param2IBinder);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAccessibilityButtonAvailabilityChanged(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(13, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onAccessibilityButtonAvailabilityChanged(param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAccessibilityButtonClicked(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(12, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onAccessibilityButtonClicked(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAccessibilityEvent(AccessibilityEvent param2AccessibilityEvent, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        boolean bool = false;
        if (param2AccessibilityEvent != null) {
          parcel.writeInt(1);
          param2AccessibilityEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(2, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onAccessibilityEvent(param2AccessibilityEvent, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFingerprintCapturingGesturesChanged(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(10, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onFingerprintCapturingGesturesChanged(param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFingerprintGesture(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(11, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onFingerprintGesture(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onGesture(AccessibilityGestureEvent param2AccessibilityGestureEvent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (param2AccessibilityGestureEvent != null) {
          parcel.writeInt(1);
          param2AccessibilityGestureEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onGesture(param2AccessibilityGestureEvent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onInterrupt() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (!this.mRemote.transact(3, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onInterrupt();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onKeyEvent(KeyEvent param2KeyEvent, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (param2KeyEvent != null) {
          parcel.writeInt(1);
          param2KeyEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(6, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onKeyEvent(param2KeyEvent, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onMagnificationChanged(int param2Int, Region param2Region, float param2Float1, float param2Float2, float param2Float3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        parcel.writeInt(param2Int);
        if (param2Region != null) {
          parcel.writeInt(1);
          param2Region.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeFloat(param2Float1);
        parcel.writeFloat(param2Float2);
        parcel.writeFloat(param2Float3);
        if (!this.mRemote.transact(7, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onMagnificationChanged(param2Int, param2Region, param2Float1, param2Float2, param2Float3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPerformGestureResult(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        parcel.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(9, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onPerformGestureResult(param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSoftKeyboardShowModeChanged(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onSoftKeyboardShowModeChanged(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSystemActionsChanged() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (!this.mRemote.transact(14, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onSystemActionsChanged();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/IAccessibilityServiceClient$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */