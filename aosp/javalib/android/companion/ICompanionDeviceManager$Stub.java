package android.companion;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements ICompanionDeviceManager {
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
  
  public static ICompanionDeviceManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.companion.ICompanionDeviceManager");
    return (iInterface != null && iInterface instanceof ICompanionDeviceManager) ? (ICompanionDeviceManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICompanionDeviceManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(ICompanionDeviceManager paramICompanionDeviceManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICompanionDeviceManager != null) {
        Proxy.sDefaultImpl = paramICompanionDeviceManager;
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
      PendingIntent pendingIntent;
      List<String> list;
      AssociationRequest associationRequest;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 7:
          paramParcel1.enforceInterface("android.companion.ICompanionDeviceManager");
          bool = isDeviceAssociatedForWifiConnection(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.companion.ICompanionDeviceManager");
          if (paramParcel1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          pendingIntent = requestNotificationAccess((ComponentName)paramParcel1);
          paramParcel2.writeNoException();
          if (pendingIntent != null) {
            paramParcel2.writeInt(1);
            pendingIntent.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
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
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 4:
          pendingIntent.enforceInterface("android.companion.ICompanionDeviceManager");
          disassociate(pendingIntent.readString(), pendingIntent.readString());
          paramParcel2.writeNoException();
          return true;
        case 3:
          pendingIntent.enforceInterface("android.companion.ICompanionDeviceManager");
          list = getAssociations(pendingIntent.readString(), pendingIntent.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeStringList(list);
          return true;
        case 2:
          list.enforceInterface("android.companion.ICompanionDeviceManager");
          if (list.readInt() != 0) {
            associationRequest = (AssociationRequest)AssociationRequest.CREATOR.createFromParcel((Parcel)list);
          } else {
            associationRequest = null;
          } 
          stopScan(associationRequest, IFindDeviceCallback.Stub.asInterface(list.readStrongBinder()), list.readString());
          paramParcel2.writeNoException();
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
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.companion.ICompanionDeviceManager");
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


/* Location:              /home/chun/Desktop/temp/!/android/companion/ICompanionDeviceManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */