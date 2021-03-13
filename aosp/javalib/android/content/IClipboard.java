package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IClipboard extends IInterface {
  void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener paramIOnPrimaryClipChangedListener, String paramString, int paramInt) throws RemoteException;
  
  void clearPrimaryClip(String paramString, int paramInt) throws RemoteException;
  
  ClipData getPrimaryClip(String paramString, int paramInt) throws RemoteException;
  
  ClipDescription getPrimaryClipDescription(String paramString, int paramInt) throws RemoteException;
  
  boolean hasClipboardText(String paramString, int paramInt) throws RemoteException;
  
  boolean hasPrimaryClip(String paramString, int paramInt) throws RemoteException;
  
  void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener paramIOnPrimaryClipChangedListener, String paramString, int paramInt) throws RemoteException;
  
  void setPrimaryClip(ClipData paramClipData, String paramString, int paramInt) throws RemoteException;
  
  public static class Default implements IClipboard {
    public void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener param1IOnPrimaryClipChangedListener, String param1String, int param1Int) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
    
    public void clearPrimaryClip(String param1String, int param1Int) throws RemoteException {}
    
    public ClipData getPrimaryClip(String param1String, int param1Int) throws RemoteException {
      return null;
    }
    
    public ClipDescription getPrimaryClipDescription(String param1String, int param1Int) throws RemoteException {
      return null;
    }
    
    public boolean hasClipboardText(String param1String, int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean hasPrimaryClip(String param1String, int param1Int) throws RemoteException {
      return false;
    }
    
    public void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener param1IOnPrimaryClipChangedListener, String param1String, int param1Int) throws RemoteException {}
    
    public void setPrimaryClip(ClipData param1ClipData, String param1String, int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IClipboard {
    private static final String DESCRIPTOR = "android.content.IClipboard";
    
    static final int TRANSACTION_addPrimaryClipChangedListener = 6;
    
    static final int TRANSACTION_clearPrimaryClip = 2;
    
    static final int TRANSACTION_getPrimaryClip = 3;
    
    static final int TRANSACTION_getPrimaryClipDescription = 4;
    
    static final int TRANSACTION_hasClipboardText = 8;
    
    static final int TRANSACTION_hasPrimaryClip = 5;
    
    static final int TRANSACTION_removePrimaryClipChangedListener = 7;
    
    static final int TRANSACTION_setPrimaryClip = 1;
    
    public Stub() {
      attachInterface(this, "android.content.IClipboard");
    }
    
    public static IClipboard asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.IClipboard");
      return (iInterface != null && iInterface instanceof IClipboard) ? (IClipboard)iInterface : new Proxy(param1IBinder);
    }
    
    public static IClipboard getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 8:
          return "hasClipboardText";
        case 7:
          return "removePrimaryClipChangedListener";
        case 6:
          return "addPrimaryClipChangedListener";
        case 5:
          return "hasPrimaryClip";
        case 4:
          return "getPrimaryClipDescription";
        case 3:
          return "getPrimaryClip";
        case 2:
          return "clearPrimaryClip";
        case 1:
          break;
      } 
      return "setPrimaryClip";
    }
    
    public static boolean setDefaultImpl(IClipboard param1IClipboard) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IClipboard != null) {
          Proxy.sDefaultImpl = param1IClipboard;
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
        ClipDescription clipDescription;
        ClipData clipData1;
        ClipData clipData2;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 8:
            param1Parcel1.enforceInterface("android.content.IClipboard");
            bool = hasClipboardText(param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.content.IClipboard");
            removePrimaryClipChangedListener(IOnPrimaryClipChangedListener.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.content.IClipboard");
            addPrimaryClipChangedListener(IOnPrimaryClipChangedListener.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.content.IClipboard");
            bool = hasPrimaryClip(param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.content.IClipboard");
            clipDescription = getPrimaryClipDescription(param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            if (clipDescription != null) {
              param1Parcel2.writeInt(1);
              clipDescription.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 3:
            clipDescription.enforceInterface("android.content.IClipboard");
            clipData1 = getPrimaryClip(clipDescription.readString(), clipDescription.readInt());
            param1Parcel2.writeNoException();
            if (clipData1 != null) {
              param1Parcel2.writeInt(1);
              clipData1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 2:
            clipData1.enforceInterface("android.content.IClipboard");
            clearPrimaryClip(clipData1.readString(), clipData1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        clipData1.enforceInterface("android.content.IClipboard");
        if (clipData1.readInt() != 0) {
          clipData2 = (ClipData)ClipData.CREATOR.createFromParcel((Parcel)clipData1);
        } else {
          clipData2 = null;
        } 
        setPrimaryClip(clipData2, clipData1.readString(), clipData1.readInt());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.content.IClipboard");
      return true;
    }
    
    private static class Proxy implements IClipboard {
      public static IClipboard sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener param2IOnPrimaryClipChangedListener, String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.content.IClipboard");
          if (param2IOnPrimaryClipChangedListener != null) {
            iBinder = param2IOnPrimaryClipChangedListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
            IClipboard.Stub.getDefaultImpl().addPrimaryClipChangedListener(param2IOnPrimaryClipChangedListener, param2String, param2Int);
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
      
      public void clearPrimaryClip(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.IClipboard");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
            IClipboard.Stub.getDefaultImpl().clearPrimaryClip(param2String, param2Int);
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
        return "android.content.IClipboard";
      }
      
      public ClipData getPrimaryClip(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.IClipboard");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null)
            return IClipboard.Stub.getDefaultImpl().getPrimaryClip(param2String, param2Int); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ClipData clipData = (ClipData)ClipData.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (ClipData)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ClipDescription getPrimaryClipDescription(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.IClipboard");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null)
            return IClipboard.Stub.getDefaultImpl().getPrimaryClipDescription(param2String, param2Int); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ClipDescription clipDescription = (ClipDescription)ClipDescription.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (ClipDescription)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean hasClipboardText(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.IClipboard");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(8, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
            bool = IClipboard.Stub.getDefaultImpl().hasClipboardText(param2String, param2Int);
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
      
      public boolean hasPrimaryClip(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.IClipboard");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(5, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
            bool = IClipboard.Stub.getDefaultImpl().hasPrimaryClip(param2String, param2Int);
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
      
      public void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener param2IOnPrimaryClipChangedListener, String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.content.IClipboard");
          if (param2IOnPrimaryClipChangedListener != null) {
            iBinder = param2IOnPrimaryClipChangedListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
            IClipboard.Stub.getDefaultImpl().removePrimaryClipChangedListener(param2IOnPrimaryClipChangedListener, param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setPrimaryClip(ClipData param2ClipData, String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.IClipboard");
          if (param2ClipData != null) {
            parcel1.writeInt(1);
            param2ClipData.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
            IClipboard.Stub.getDefaultImpl().setPrimaryClip(param2ClipData, param2String, param2Int);
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
  
  private static class Proxy implements IClipboard {
    public static IClipboard sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void addPrimaryClipChangedListener(IOnPrimaryClipChangedListener param1IOnPrimaryClipChangedListener, String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.IClipboard");
        if (param1IOnPrimaryClipChangedListener != null) {
          iBinder = param1IOnPrimaryClipChangedListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          IClipboard.Stub.getDefaultImpl().addPrimaryClipChangedListener(param1IOnPrimaryClipChangedListener, param1String, param1Int);
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
    
    public void clearPrimaryClip(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          IClipboard.Stub.getDefaultImpl().clearPrimaryClip(param1String, param1Int);
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
      return "android.content.IClipboard";
    }
    
    public ClipData getPrimaryClip(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null)
          return IClipboard.Stub.getDefaultImpl().getPrimaryClip(param1String, param1Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ClipData clipData = (ClipData)ClipData.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (ClipData)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ClipDescription getPrimaryClipDescription(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null)
          return IClipboard.Stub.getDefaultImpl().getPrimaryClipDescription(param1String, param1Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ClipDescription clipDescription = (ClipDescription)ClipDescription.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (ClipDescription)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasClipboardText(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(8, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          bool = IClipboard.Stub.getDefaultImpl().hasClipboardText(param1String, param1Int);
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
    
    public boolean hasPrimaryClip(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(5, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          bool = IClipboard.Stub.getDefaultImpl().hasPrimaryClip(param1String, param1Int);
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
    
    public void removePrimaryClipChangedListener(IOnPrimaryClipChangedListener param1IOnPrimaryClipChangedListener, String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.IClipboard");
        if (param1IOnPrimaryClipChangedListener != null) {
          iBinder = param1IOnPrimaryClipChangedListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          IClipboard.Stub.getDefaultImpl().removePrimaryClipChangedListener(param1IOnPrimaryClipChangedListener, param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPrimaryClip(ClipData param1ClipData, String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.IClipboard");
        if (param1ClipData != null) {
          parcel1.writeInt(1);
          param1ClipData.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IClipboard.Stub.getDefaultImpl() != null) {
          IClipboard.Stub.getDefaultImpl().setPrimaryClip(param1ClipData, param1String, param1Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/IClipboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */