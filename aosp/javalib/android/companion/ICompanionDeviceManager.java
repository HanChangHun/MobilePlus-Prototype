package android.companion;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface ICompanionDeviceManager extends IInterface {
  void associate(AssociationRequest paramAssociationRequest, IFindDeviceCallback paramIFindDeviceCallback, String paramString) throws RemoteException;
  
  void disassociate(String paramString1, String paramString2) throws RemoteException;
  
  List<String> getAssociations(String paramString, int paramInt) throws RemoteException;
  
  boolean hasNotificationAccess(ComponentName paramComponentName) throws RemoteException;
  
  boolean isDeviceAssociatedForWifiConnection(String paramString1, String paramString2, int paramInt) throws RemoteException;
  
  PendingIntent requestNotificationAccess(ComponentName paramComponentName) throws RemoteException;
  
  void stopScan(AssociationRequest paramAssociationRequest, IFindDeviceCallback paramIFindDeviceCallback, String paramString) throws RemoteException;
  
  public static class Default implements ICompanionDeviceManager {
    public IBinder asBinder() {
      return null;
    }
    
    public void associate(AssociationRequest param1AssociationRequest, IFindDeviceCallback param1IFindDeviceCallback, String param1String) throws RemoteException {}
    
    public void disassociate(String param1String1, String param1String2) throws RemoteException {}
    
    public List<String> getAssociations(String param1String, int param1Int) throws RemoteException {
      return null;
    }
    
    public boolean hasNotificationAccess(ComponentName param1ComponentName) throws RemoteException {
      return false;
    }
    
    public boolean isDeviceAssociatedForWifiConnection(String param1String1, String param1String2, int param1Int) throws RemoteException {
      return false;
    }
    
    public PendingIntent requestNotificationAccess(ComponentName param1ComponentName) throws RemoteException {
      return null;
    }
    
    public void stopScan(AssociationRequest param1AssociationRequest, IFindDeviceCallback param1IFindDeviceCallback, String param1String) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICompanionDeviceManager {
    private static final String DESCRIPTOR = "android.companion.ICompanionDeviceManager";
    
    static final int TRANSACTION_associate = 1;
    
    static final int TRANSACTION_disassociate = 4;
    
    static final int TRANSACTION_getAssociations = 3;
    
    static final int TRANSACTION_hasNotificationAccess = 5;
    
    static final int TRANSACTION_isDeviceAssociatedForWifiConnection = 7;
    
    static final int TRANSACTION_requestNotificationAccess = 6;
    
    static final int TRANSACTION_stopScan = 2;
    
    public Stub() {
      attachInterface(this, "android.companion.ICompanionDeviceManager");
    }
    
    public static ICompanionDeviceManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.companion.ICompanionDeviceManager");
      return (iInterface != null && iInterface instanceof ICompanionDeviceManager) ? (ICompanionDeviceManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICompanionDeviceManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 7:
          return "isDeviceAssociatedForWifiConnection";
        case 6:
          return "requestNotificationAccess";
        case 5:
          return "hasNotificationAccess";
        case 4:
          return "disassociate";
        case 3:
          return "getAssociations";
        case 2:
          return "stopScan";
        case 1:
          break;
      } 
      return "associate";
    }
    
    public static boolean setDefaultImpl(ICompanionDeviceManager param1ICompanionDeviceManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICompanionDeviceManager != null) {
          Proxy.sDefaultImpl = param1ICompanionDeviceManager;
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
        PendingIntent pendingIntent;
        List<String> list;
        AssociationRequest associationRequest;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 7:
            param1Parcel1.enforceInterface("android.companion.ICompanionDeviceManager");
            bool = isDeviceAssociatedForWifiConnection(param1Parcel1.readString(), param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.companion.ICompanionDeviceManager");
            if (param1Parcel1.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            pendingIntent = requestNotificationAccess((ComponentName)param1Parcel1);
            param1Parcel2.writeNoException();
            if (pendingIntent != null) {
              param1Parcel2.writeInt(1);
              pendingIntent.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 5:
            pendingIntent.enforceInterface("android.companion.ICompanionDeviceManager");
            if (pendingIntent.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)pendingIntent);
            } else {
              pendingIntent = null;
            } 
            bool = hasNotificationAccess((ComponentName)pendingIntent);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool);
            return true;
          case 4:
            pendingIntent.enforceInterface("android.companion.ICompanionDeviceManager");
            disassociate(pendingIntent.readString(), pendingIntent.readString());
            param1Parcel2.writeNoException();
            return true;
          case 3:
            pendingIntent.enforceInterface("android.companion.ICompanionDeviceManager");
            list = getAssociations(pendingIntent.readString(), pendingIntent.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringList(list);
            return true;
          case 2:
            list.enforceInterface("android.companion.ICompanionDeviceManager");
            if (list.readInt() != 0) {
              associationRequest = (AssociationRequest)AssociationRequest.CREATOR.createFromParcel((Parcel)list);
            } else {
              associationRequest = null;
            } 
            stopScan(associationRequest, IFindDeviceCallback.Stub.asInterface(list.readStrongBinder()), list.readString());
            param1Parcel2.writeNoException();
            return true;
          case 1:
            break;
        } 
        list.enforceInterface("android.companion.ICompanionDeviceManager");
        if (list.readInt() != 0) {
          associationRequest = (AssociationRequest)AssociationRequest.CREATOR.createFromParcel((Parcel)list);
        } else {
          associationRequest = null;
        } 
        associate(associationRequest, IFindDeviceCallback.Stub.asInterface(list.readStrongBinder()), list.readString());
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.companion.ICompanionDeviceManager");
      return true;
    }
    
    private static class Proxy implements ICompanionDeviceManager {
      public static ICompanionDeviceManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void associate(AssociationRequest param2AssociationRequest, IFindDeviceCallback param2IFindDeviceCallback, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
          if (param2AssociationRequest != null) {
            parcel1.writeInt(1);
            param2AssociationRequest.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2IFindDeviceCallback != null) {
            iBinder = param2IFindDeviceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
            ICompanionDeviceManager.Stub.getDefaultImpl().associate(param2AssociationRequest, param2IFindDeviceCallback, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void disassociate(String param2String1, String param2String2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
            ICompanionDeviceManager.Stub.getDefaultImpl().disassociate(param2String1, param2String2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<String> getAssociations(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null)
            return ICompanionDeviceManager.Stub.getDefaultImpl().getAssociations(param2String, param2Int); 
          parcel2.readException();
          return parcel2.createStringArrayList();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.companion.ICompanionDeviceManager";
      }
      
      public boolean hasNotificationAccess(ComponentName param2ComponentName) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
          boolean bool = true;
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
            bool = ICompanionDeviceManager.Stub.getDefaultImpl().hasNotificationAccess(param2ComponentName);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isDeviceAssociatedForWifiConnection(String param2String1, String param2String2, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(7, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
            bool = ICompanionDeviceManager.Stub.getDefaultImpl().isDeviceAssociatedForWifiConnection(param2String1, param2String2, param2Int);
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
      
      public PendingIntent requestNotificationAccess(ComponentName param2ComponentName) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null)
            return ICompanionDeviceManager.Stub.getDefaultImpl().requestNotificationAccess(param2ComponentName); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel2);
          } else {
            param2ComponentName = null;
          } 
          return (PendingIntent)param2ComponentName;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void stopScan(AssociationRequest param2AssociationRequest, IFindDeviceCallback param2IFindDeviceCallback, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
          if (param2AssociationRequest != null) {
            parcel1.writeInt(1);
            param2AssociationRequest.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2IFindDeviceCallback != null) {
            iBinder = param2IFindDeviceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
            ICompanionDeviceManager.Stub.getDefaultImpl().stopScan(param2AssociationRequest, param2IFindDeviceCallback, param2String);
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
  
  private static class Proxy implements ICompanionDeviceManager {
    public static ICompanionDeviceManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void associate(AssociationRequest param1AssociationRequest, IFindDeviceCallback param1IFindDeviceCallback, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
        if (param1AssociationRequest != null) {
          parcel1.writeInt(1);
          param1AssociationRequest.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IFindDeviceCallback != null) {
          iBinder = param1IFindDeviceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
          ICompanionDeviceManager.Stub.getDefaultImpl().associate(param1AssociationRequest, param1IFindDeviceCallback, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void disassociate(String param1String1, String param1String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
          ICompanionDeviceManager.Stub.getDefaultImpl().disassociate(param1String1, param1String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<String> getAssociations(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null)
          return ICompanionDeviceManager.Stub.getDefaultImpl().getAssociations(param1String, param1Int); 
        parcel2.readException();
        return parcel2.createStringArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.companion.ICompanionDeviceManager";
    }
    
    public boolean hasNotificationAccess(ComponentName param1ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
        boolean bool = true;
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
          bool = ICompanionDeviceManager.Stub.getDefaultImpl().hasNotificationAccess(param1ComponentName);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isDeviceAssociatedForWifiConnection(String param1String1, String param1String2, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(7, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
          bool = ICompanionDeviceManager.Stub.getDefaultImpl().isDeviceAssociatedForWifiConnection(param1String1, param1String2, param1Int);
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
    
    public PendingIntent requestNotificationAccess(ComponentName param1ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null)
          return ICompanionDeviceManager.Stub.getDefaultImpl().requestNotificationAccess(param1ComponentName); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel2);
        } else {
          param1ComponentName = null;
        } 
        return (PendingIntent)param1ComponentName;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopScan(AssociationRequest param1AssociationRequest, IFindDeviceCallback param1IFindDeviceCallback, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.companion.ICompanionDeviceManager");
        if (param1AssociationRequest != null) {
          parcel1.writeInt(1);
          param1AssociationRequest.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IFindDeviceCallback != null) {
          iBinder = param1IFindDeviceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICompanionDeviceManager.Stub.getDefaultImpl() != null) {
          ICompanionDeviceManager.Stub.getDefaultImpl().stopScan(param1AssociationRequest, param1IFindDeviceCallback, param1String);
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


/* Location:              /home/chun/Desktop/temp/!/android/companion/ICompanionDeviceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */