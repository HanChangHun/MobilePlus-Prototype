package android.accessibilityservice;

import android.graphics.Region;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public interface IAccessibilityServiceClient extends IInterface {
  void clearAccessibilityCache() throws RemoteException;
  
  void init(IAccessibilityServiceConnection paramIAccessibilityServiceConnection, int paramInt, IBinder paramIBinder) throws RemoteException;
  
  void onAccessibilityButtonAvailabilityChanged(boolean paramBoolean) throws RemoteException;
  
  void onAccessibilityButtonClicked(int paramInt) throws RemoteException;
  
  void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent, boolean paramBoolean) throws RemoteException;
  
  void onFingerprintCapturingGesturesChanged(boolean paramBoolean) throws RemoteException;
  
  void onFingerprintGesture(int paramInt) throws RemoteException;
  
  void onGesture(AccessibilityGestureEvent paramAccessibilityGestureEvent) throws RemoteException;
  
  void onInterrupt() throws RemoteException;
  
  void onKeyEvent(KeyEvent paramKeyEvent, int paramInt) throws RemoteException;
  
  void onMagnificationChanged(int paramInt, Region paramRegion, float paramFloat1, float paramFloat2, float paramFloat3) throws RemoteException;
  
  void onPerformGestureResult(int paramInt, boolean paramBoolean) throws RemoteException;
  
  void onSoftKeyboardShowModeChanged(int paramInt) throws RemoteException;
  
  void onSystemActionsChanged() throws RemoteException;
  
  public static class Default implements IAccessibilityServiceClient {
    public IBinder asBinder() {
      return null;
    }
    
    public void clearAccessibilityCache() throws RemoteException {}
    
    public void init(IAccessibilityServiceConnection param1IAccessibilityServiceConnection, int param1Int, IBinder param1IBinder) throws RemoteException {}
    
    public void onAccessibilityButtonAvailabilityChanged(boolean param1Boolean) throws RemoteException {}
    
    public void onAccessibilityButtonClicked(int param1Int) throws RemoteException {}
    
    public void onAccessibilityEvent(AccessibilityEvent param1AccessibilityEvent, boolean param1Boolean) throws RemoteException {}
    
    public void onFingerprintCapturingGesturesChanged(boolean param1Boolean) throws RemoteException {}
    
    public void onFingerprintGesture(int param1Int) throws RemoteException {}
    
    public void onGesture(AccessibilityGestureEvent param1AccessibilityGestureEvent) throws RemoteException {}
    
    public void onInterrupt() throws RemoteException {}
    
    public void onKeyEvent(KeyEvent param1KeyEvent, int param1Int) throws RemoteException {}
    
    public void onMagnificationChanged(int param1Int, Region param1Region, float param1Float1, float param1Float2, float param1Float3) throws RemoteException {}
    
    public void onPerformGestureResult(int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void onSoftKeyboardShowModeChanged(int param1Int) throws RemoteException {}
    
    public void onSystemActionsChanged() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAccessibilityServiceClient {
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
    
    public static IAccessibilityServiceClient asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.accessibilityservice.IAccessibilityServiceClient");
      return (iInterface != null && iInterface instanceof IAccessibilityServiceClient) ? (IAccessibilityServiceClient)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAccessibilityServiceClient getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IAccessibilityServiceClient param1IAccessibilityServiceClient) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAccessibilityServiceClient != null) {
          Proxy.sDefaultImpl = param1IAccessibilityServiceClient;
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
        boolean bool1 = false;
        boolean bool2 = false;
        boolean bool3 = false;
        boolean bool4 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 14:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            onSystemActionsChanged();
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            if (param1Parcel1.readInt() != 0)
              bool4 = true; 
            onAccessibilityButtonAvailabilityChanged(bool4);
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            onAccessibilityButtonClicked(param1Parcel1.readInt());
            return true;
          case 11:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            onFingerprintGesture(param1Parcel1.readInt());
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            bool4 = bool1;
            if (param1Parcel1.readInt() != 0)
              bool4 = true; 
            onFingerprintCapturingGesturesChanged(bool4);
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            param1Int1 = param1Parcel1.readInt();
            bool4 = bool2;
            if (param1Parcel1.readInt() != 0)
              bool4 = true; 
            onPerformGestureResult(param1Int1, bool4);
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            onSoftKeyboardShowModeChanged(param1Parcel1.readInt());
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              Region region = (Region)Region.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            onMagnificationChanged(param1Int1, (Region)param1Parcel2, param1Parcel1.readFloat(), param1Parcel1.readFloat(), param1Parcel1.readFloat());
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            if (param1Parcel1.readInt() != 0) {
              KeyEvent keyEvent = (KeyEvent)KeyEvent.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            onKeyEvent((KeyEvent)param1Parcel2, param1Parcel1.readInt());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            clearAccessibilityCache();
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            if (param1Parcel1.readInt() != 0) {
              AccessibilityGestureEvent accessibilityGestureEvent = (AccessibilityGestureEvent)AccessibilityGestureEvent.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onGesture((AccessibilityGestureEvent)param1Parcel1);
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            onInterrupt();
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
            if (param1Parcel1.readInt() != 0) {
              AccessibilityEvent accessibilityEvent = (AccessibilityEvent)AccessibilityEvent.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            bool4 = bool3;
            if (param1Parcel1.readInt() != 0)
              bool4 = true; 
            onAccessibilityEvent((AccessibilityEvent)param1Parcel2, bool4);
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceClient");
        init(IAccessibilityServiceConnection.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readInt(), param1Parcel1.readStrongBinder());
        return true;
      } 
      param1Parcel2.writeString("android.accessibilityservice.IAccessibilityServiceClient");
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
  
  private static class Proxy implements IAccessibilityServiceClient {
    public static IAccessibilityServiceClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public void init(IAccessibilityServiceConnection param1IAccessibilityServiceConnection, int param1Int, IBinder param1IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (param1IAccessibilityServiceConnection != null) {
          iBinder = param1IAccessibilityServiceConnection.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeInt(param1Int);
        parcel.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().init(param1IAccessibilityServiceConnection, param1Int, param1IBinder);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAccessibilityButtonAvailabilityChanged(boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(13, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onAccessibilityButtonAvailabilityChanged(param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAccessibilityButtonClicked(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(12, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onAccessibilityButtonClicked(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAccessibilityEvent(AccessibilityEvent param1AccessibilityEvent, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        boolean bool = false;
        if (param1AccessibilityEvent != null) {
          parcel.writeInt(1);
          param1AccessibilityEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(2, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onAccessibilityEvent(param1AccessibilityEvent, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFingerprintCapturingGesturesChanged(boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(10, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onFingerprintCapturingGesturesChanged(param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFingerprintGesture(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(11, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onFingerprintGesture(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onGesture(AccessibilityGestureEvent param1AccessibilityGestureEvent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (param1AccessibilityGestureEvent != null) {
          parcel.writeInt(1);
          param1AccessibilityGestureEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onGesture(param1AccessibilityGestureEvent);
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
    
    public void onKeyEvent(KeyEvent param1KeyEvent, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        if (param1KeyEvent != null) {
          parcel.writeInt(1);
          param1KeyEvent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onKeyEvent(param1KeyEvent, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onMagnificationChanged(int param1Int, Region param1Region, float param1Float1, float param1Float2, float param1Float3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        parcel.writeInt(param1Int);
        if (param1Region != null) {
          parcel.writeInt(1);
          param1Region.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeFloat(param1Float1);
        parcel.writeFloat(param1Float2);
        parcel.writeFloat(param1Float3);
        if (!this.mRemote.transact(7, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onMagnificationChanged(param1Int, param1Region, param1Float1, param1Float2, param1Float3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPerformGestureResult(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        parcel.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(9, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onPerformGestureResult(param1Int, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSoftKeyboardShowModeChanged(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceClient.Stub.getDefaultImpl().onSoftKeyboardShowModeChanged(param1Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/IAccessibilityServiceClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */