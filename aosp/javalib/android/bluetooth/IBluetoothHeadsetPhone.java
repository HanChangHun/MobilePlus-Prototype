package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBluetoothHeadsetPhone extends IInterface {
  boolean answerCall() throws RemoteException;
  
  void cdmaSetSecondCallState(boolean paramBoolean) throws RemoteException;
  
  void cdmaSwapSecondCallState() throws RemoteException;
  
  String getNetworkOperator() throws RemoteException;
  
  String getSubscriberNumber() throws RemoteException;
  
  boolean hangupCall() throws RemoteException;
  
  boolean listCurrentCalls() throws RemoteException;
  
  boolean processChld(int paramInt) throws RemoteException;
  
  boolean queryPhoneState() throws RemoteException;
  
  boolean sendDtmf(int paramInt) throws RemoteException;
  
  void updateBtHandsfreeAfterRadioTechnologyChange() throws RemoteException;
  
  public static class Default implements IBluetoothHeadsetPhone {
    public boolean answerCall() throws RemoteException {
      return false;
    }
    
    public IBinder asBinder() {
      return null;
    }
    
    public void cdmaSetSecondCallState(boolean param1Boolean) throws RemoteException {}
    
    public void cdmaSwapSecondCallState() throws RemoteException {}
    
    public String getNetworkOperator() throws RemoteException {
      return null;
    }
    
    public String getSubscriberNumber() throws RemoteException {
      return null;
    }
    
    public boolean hangupCall() throws RemoteException {
      return false;
    }
    
    public boolean listCurrentCalls() throws RemoteException {
      return false;
    }
    
    public boolean processChld(int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean queryPhoneState() throws RemoteException {
      return false;
    }
    
    public boolean sendDtmf(int param1Int) throws RemoteException {
      return false;
    }
    
    public void updateBtHandsfreeAfterRadioTechnologyChange() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBluetoothHeadsetPhone {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHeadsetPhone";
    
    static final int TRANSACTION_answerCall = 1;
    
    static final int TRANSACTION_cdmaSetSecondCallState = 11;
    
    static final int TRANSACTION_cdmaSwapSecondCallState = 10;
    
    static final int TRANSACTION_getNetworkOperator = 5;
    
    static final int TRANSACTION_getSubscriberNumber = 6;
    
    static final int TRANSACTION_hangupCall = 2;
    
    static final int TRANSACTION_listCurrentCalls = 7;
    
    static final int TRANSACTION_processChld = 4;
    
    static final int TRANSACTION_queryPhoneState = 8;
    
    static final int TRANSACTION_sendDtmf = 3;
    
    static final int TRANSACTION_updateBtHandsfreeAfterRadioTechnologyChange = 9;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothHeadsetPhone");
    }
    
    public static IBluetoothHeadsetPhone asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothHeadsetPhone");
      return (iInterface != null && iInterface instanceof IBluetoothHeadsetPhone) ? (IBluetoothHeadsetPhone)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothHeadsetPhone getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 11:
          return "cdmaSetSecondCallState";
        case 10:
          return "cdmaSwapSecondCallState";
        case 9:
          return "updateBtHandsfreeAfterRadioTechnologyChange";
        case 8:
          return "queryPhoneState";
        case 7:
          return "listCurrentCalls";
        case 6:
          return "getSubscriberNumber";
        case 5:
          return "getNetworkOperator";
        case 4:
          return "processChld";
        case 3:
          return "sendDtmf";
        case 2:
          return "hangupCall";
        case 1:
          break;
      } 
      return "answerCall";
    }
    
    public static boolean setDefaultImpl(IBluetoothHeadsetPhone param1IBluetoothHeadsetPhone) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothHeadsetPhone != null) {
          Proxy.sDefaultImpl = param1IBluetoothHeadsetPhone;
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
        String str;
        boolean bool1;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 11:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            cdmaSetSecondCallState(bool1);
            param1Parcel2.writeNoException();
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
            cdmaSwapSecondCallState();
            param1Parcel2.writeNoException();
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
            updateBtHandsfreeAfterRadioTechnologyChange();
            param1Parcel2.writeNoException();
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
            bool = queryPhoneState();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
            bool = listCurrentCalls();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
            str = getSubscriberNumber();
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str);
            return true;
          case 5:
            str.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
            str = getNetworkOperator();
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str);
            return true;
          case 4:
            str.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
            bool = processChld(str.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 3:
            str.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
            bool = sendDtmf(str.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 2:
            str.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
            bool = hangupCall();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 1:
            break;
        } 
        str.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
        boolean bool = answerCall();
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool);
        return true;
      } 
      param1Parcel2.writeString("android.bluetooth.IBluetoothHeadsetPhone");
      return true;
    }
    
    private static class Proxy implements IBluetoothHeadsetPhone {
      public static IBluetoothHeadsetPhone sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public boolean answerCall() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(1, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().answerCall();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void cdmaSetSecondCallState(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
            IBluetoothHeadsetPhone.Stub.getDefaultImpl().cdmaSetSecondCallState(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void cdmaSwapSecondCallState() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
            IBluetoothHeadsetPhone.Stub.getDefaultImpl().cdmaSwapSecondCallState();
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
        return "android.bluetooth.IBluetoothHeadsetPhone";
      }
      
      public String getNetworkOperator() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetPhone.Stub.getDefaultImpl().getNetworkOperator(); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getSubscriberNumber() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null)
            return IBluetoothHeadsetPhone.Stub.getDefaultImpl().getSubscriberNumber(); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean hangupCall() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(2, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().hangupCall();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean listCurrentCalls() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(7, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().listCurrentCalls();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean processChld(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(4, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().processChld(param2Int);
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
      
      public boolean queryPhoneState() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(8, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().queryPhoneState();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean sendDtmf(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(3, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
            bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().sendDtmf(param2Int);
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
      
      public void updateBtHandsfreeAfterRadioTechnologyChange() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
            IBluetoothHeadsetPhone.Stub.getDefaultImpl().updateBtHandsfreeAfterRadioTechnologyChange();
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
  
  private static class Proxy implements IBluetoothHeadsetPhone {
    public static IBluetoothHeadsetPhone sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public boolean answerCall() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(1, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().answerCall();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void cdmaSetSecondCallState(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
          IBluetoothHeadsetPhone.Stub.getDefaultImpl().cdmaSetSecondCallState(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cdmaSwapSecondCallState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
          IBluetoothHeadsetPhone.Stub.getDefaultImpl().cdmaSwapSecondCallState();
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
      return "android.bluetooth.IBluetoothHeadsetPhone";
    }
    
    public String getNetworkOperator() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetPhone.Stub.getDefaultImpl().getNetworkOperator(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getSubscriberNumber() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null)
          return IBluetoothHeadsetPhone.Stub.getDefaultImpl().getSubscriberNumber(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hangupCall() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().hangupCall();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean listCurrentCalls() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().listCurrentCalls();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean processChld(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().processChld(param1Int);
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
    
    public boolean queryPhoneState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().queryPhoneState();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean sendDtmf(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(3, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
          bool = IBluetoothHeadsetPhone.Stub.getDefaultImpl().sendDtmf(param1Int);
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
    
    public void updateBtHandsfreeAfterRadioTechnologyChange() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothHeadsetPhone.Stub.getDefaultImpl() != null) {
          IBluetoothHeadsetPhone.Stub.getDefaultImpl().updateBtHandsfreeAfterRadioTechnologyChange();
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHeadsetPhone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */