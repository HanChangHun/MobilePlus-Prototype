package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHdmiRecordListener extends IInterface {
  byte[] getOneTouchRecordSource(int paramInt) throws RemoteException;
  
  void onClearTimerRecordingResult(int paramInt1, int paramInt2) throws RemoteException;
  
  void onOneTouchRecordResult(int paramInt1, int paramInt2) throws RemoteException;
  
  void onTimerRecordingResult(int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IHdmiRecordListener {
    public IBinder asBinder() {
      return null;
    }
    
    public byte[] getOneTouchRecordSource(int param1Int) throws RemoteException {
      return null;
    }
    
    public void onClearTimerRecordingResult(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onOneTouchRecordResult(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onTimerRecordingResult(int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IHdmiRecordListener {
    private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiRecordListener";
    
    static final int TRANSACTION_getOneTouchRecordSource = 1;
    
    static final int TRANSACTION_onClearTimerRecordingResult = 4;
    
    static final int TRANSACTION_onOneTouchRecordResult = 2;
    
    static final int TRANSACTION_onTimerRecordingResult = 3;
    
    public Stub() {
      attachInterface(this, "android.hardware.hdmi.IHdmiRecordListener");
    }
    
    public static IHdmiRecordListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.hdmi.IHdmiRecordListener");
      return (iInterface != null && iInterface instanceof IHdmiRecordListener) ? (IHdmiRecordListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IHdmiRecordListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? null : "onClearTimerRecordingResult") : "onTimerRecordingResult") : "onOneTouchRecordResult") : "getOneTouchRecordSource";
    }
    
    public static boolean setDefaultImpl(IHdmiRecordListener param1IHdmiRecordListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IHdmiRecordListener != null) {
          Proxy.sDefaultImpl = param1IHdmiRecordListener;
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
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              if (param1Int1 != 1598968902)
                return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
              param1Parcel2.writeString("android.hardware.hdmi.IHdmiRecordListener");
              return true;
            } 
            param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiRecordListener");
            onClearTimerRecordingResult(param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          } 
          param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiRecordListener");
          onTimerRecordingResult(param1Parcel1.readInt(), param1Parcel1.readInt());
          param1Parcel2.writeNoException();
          return true;
        } 
        param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiRecordListener");
        onOneTouchRecordResult(param1Parcel1.readInt(), param1Parcel1.readInt());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.hdmi.IHdmiRecordListener");
      byte[] arrayOfByte = getOneTouchRecordSource(param1Parcel1.readInt());
      param1Parcel2.writeNoException();
      param1Parcel2.writeByteArray(arrayOfByte);
      return true;
    }
    
    private static class Proxy implements IHdmiRecordListener {
      public static IHdmiRecordListener sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.hdmi.IHdmiRecordListener";
      }
      
      public byte[] getOneTouchRecordSource(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null)
            return IHdmiRecordListener.Stub.getDefaultImpl().getOneTouchRecordSource(param2Int); 
          parcel2.readException();
          return parcel2.createByteArray();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void onClearTimerRecordingResult(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
            IHdmiRecordListener.Stub.getDefaultImpl().onClearTimerRecordingResult(param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void onOneTouchRecordResult(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
            IHdmiRecordListener.Stub.getDefaultImpl().onOneTouchRecordResult(param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void onTimerRecordingResult(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
            IHdmiRecordListener.Stub.getDefaultImpl().onTimerRecordingResult(param2Int1, param2Int2);
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
  }
  
  private static class Proxy implements IHdmiRecordListener {
    public static IHdmiRecordListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiRecordListener";
    }
    
    public byte[] getOneTouchRecordSource(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null)
          return IHdmiRecordListener.Stub.getDefaultImpl().getOneTouchRecordSource(param1Int); 
        parcel2.readException();
        return parcel2.createByteArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onClearTimerRecordingResult(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
          IHdmiRecordListener.Stub.getDefaultImpl().onClearTimerRecordingResult(param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onOneTouchRecordResult(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
          IHdmiRecordListener.Stub.getDefaultImpl().onOneTouchRecordResult(param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onTimerRecordingResult(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiRecordListener");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IHdmiRecordListener.Stub.getDefaultImpl() != null) {
          IHdmiRecordListener.Stub.getDefaultImpl().onTimerRecordingResult(param1Int1, param1Int2);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiRecordListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */