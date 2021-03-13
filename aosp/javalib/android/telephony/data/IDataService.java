package android.telephony.data;

import android.net.LinkProperties;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface IDataService extends IInterface {
  void createDataServiceProvider(int paramInt) throws RemoteException;
  
  void deactivateDataCall(int paramInt1, int paramInt2, int paramInt3, IDataServiceCallback paramIDataServiceCallback) throws RemoteException;
  
  void registerForDataCallListChanged(int paramInt, IDataServiceCallback paramIDataServiceCallback) throws RemoteException;
  
  void removeDataServiceProvider(int paramInt) throws RemoteException;
  
  void requestDataCallList(int paramInt, IDataServiceCallback paramIDataServiceCallback) throws RemoteException;
  
  void setDataProfile(int paramInt, List<DataProfile> paramList, boolean paramBoolean, IDataServiceCallback paramIDataServiceCallback) throws RemoteException;
  
  void setInitialAttachApn(int paramInt, DataProfile paramDataProfile, boolean paramBoolean, IDataServiceCallback paramIDataServiceCallback) throws RemoteException;
  
  void setupDataCall(int paramInt1, int paramInt2, DataProfile paramDataProfile, boolean paramBoolean1, boolean paramBoolean2, int paramInt3, LinkProperties paramLinkProperties, IDataServiceCallback paramIDataServiceCallback) throws RemoteException;
  
  void unregisterForDataCallListChanged(int paramInt, IDataServiceCallback paramIDataServiceCallback) throws RemoteException;
  
  public static class Default implements IDataService {
    public IBinder asBinder() {
      return null;
    }
    
    public void createDataServiceProvider(int param1Int) throws RemoteException {}
    
    public void deactivateDataCall(int param1Int1, int param1Int2, int param1Int3, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {}
    
    public void registerForDataCallListChanged(int param1Int, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {}
    
    public void removeDataServiceProvider(int param1Int) throws RemoteException {}
    
    public void requestDataCallList(int param1Int, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {}
    
    public void setDataProfile(int param1Int, List<DataProfile> param1List, boolean param1Boolean, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {}
    
    public void setInitialAttachApn(int param1Int, DataProfile param1DataProfile, boolean param1Boolean, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {}
    
    public void setupDataCall(int param1Int1, int param1Int2, DataProfile param1DataProfile, boolean param1Boolean1, boolean param1Boolean2, int param1Int3, LinkProperties param1LinkProperties, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {}
    
    public void unregisterForDataCallListChanged(int param1Int, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IDataService {
    private static final String DESCRIPTOR = "android.telephony.data.IDataService";
    
    static final int TRANSACTION_createDataServiceProvider = 1;
    
    static final int TRANSACTION_deactivateDataCall = 4;
    
    static final int TRANSACTION_registerForDataCallListChanged = 8;
    
    static final int TRANSACTION_removeDataServiceProvider = 2;
    
    static final int TRANSACTION_requestDataCallList = 7;
    
    static final int TRANSACTION_setDataProfile = 6;
    
    static final int TRANSACTION_setInitialAttachApn = 5;
    
    static final int TRANSACTION_setupDataCall = 3;
    
    static final int TRANSACTION_unregisterForDataCallListChanged = 9;
    
    public Stub() {
      attachInterface(this, "android.telephony.data.IDataService");
    }
    
    public static IDataService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.telephony.data.IDataService");
      return (iInterface != null && iInterface instanceof IDataService) ? (IDataService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IDataService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 9:
          return "unregisterForDataCallListChanged";
        case 8:
          return "registerForDataCallListChanged";
        case 7:
          return "requestDataCallList";
        case 6:
          return "setDataProfile";
        case 5:
          return "setInitialAttachApn";
        case 4:
          return "deactivateDataCall";
        case 3:
          return "setupDataCall";
        case 2:
          return "removeDataServiceProvider";
        case 1:
          break;
      } 
      return "createDataServiceProvider";
    }
    
    public static boolean setDefaultImpl(IDataService param1IDataService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IDataService != null) {
          Proxy.sDefaultImpl = param1IDataService;
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
      ArrayList<DataProfile> arrayList;
      if (param1Int1 != 1598968902) {
        int i;
        LinkProperties linkProperties;
        boolean bool1 = false;
        boolean bool2 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 9:
            param1Parcel1.enforceInterface("android.telephony.data.IDataService");
            unregisterForDataCallListChanged(param1Parcel1.readInt(), IDataServiceCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.telephony.data.IDataService");
            registerForDataCallListChanged(param1Parcel1.readInt(), IDataServiceCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.telephony.data.IDataService");
            requestDataCallList(param1Parcel1.readInt(), IDataServiceCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.telephony.data.IDataService");
            param1Int1 = param1Parcel1.readInt();
            arrayList = param1Parcel1.createTypedArrayList(DataProfile.CREATOR);
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            setDataProfile(param1Int1, arrayList, bool2, IDataServiceCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.telephony.data.IDataService");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              DataProfile dataProfile = (DataProfile)DataProfile.CREATOR.createFromParcel(param1Parcel1);
            } else {
              arrayList = null;
            } 
            bool2 = bool1;
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            setInitialAttachApn(param1Int1, (DataProfile)arrayList, bool2, IDataServiceCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.telephony.data.IDataService");
            deactivateDataCall(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt(), IDataServiceCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.telephony.data.IDataService");
            param1Int1 = param1Parcel1.readInt();
            i = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              DataProfile dataProfile = (DataProfile)DataProfile.CREATOR.createFromParcel(param1Parcel1);
            } else {
              arrayList = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            param1Int2 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              linkProperties = (LinkProperties)LinkProperties.CREATOR.createFromParcel(param1Parcel1);
            } else {
              linkProperties = null;
            } 
            setupDataCall(param1Int1, i, (DataProfile)arrayList, bool2, bool1, param1Int2, linkProperties, IDataServiceCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.telephony.data.IDataService");
            removeDataServiceProvider(param1Parcel1.readInt());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.telephony.data.IDataService");
        createDataServiceProvider(param1Parcel1.readInt());
        return true;
      } 
      arrayList.writeString("android.telephony.data.IDataService");
      return true;
    }
    
    private static class Proxy implements IDataService {
      public static IDataService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void createDataServiceProvider(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.telephony.data.IDataService");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
            IDataService.Stub.getDefaultImpl().createDataServiceProvider(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void deactivateDataCall(int param2Int1, int param2Int2, int param2Int3, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.telephony.data.IDataService");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          if (param2IDataServiceCallback != null) {
            iBinder = param2IDataServiceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(4, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
            IDataService.Stub.getDefaultImpl().deactivateDataCall(param2Int1, param2Int2, param2Int3, param2IDataServiceCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.telephony.data.IDataService";
      }
      
      public void registerForDataCallListChanged(int param2Int, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.telephony.data.IDataService");
          parcel.writeInt(param2Int);
          if (param2IDataServiceCallback != null) {
            iBinder = param2IDataServiceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(8, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
            IDataService.Stub.getDefaultImpl().registerForDataCallListChanged(param2Int, param2IDataServiceCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void removeDataServiceProvider(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.telephony.data.IDataService");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
            IDataService.Stub.getDefaultImpl().removeDataServiceProvider(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void requestDataCallList(int param2Int, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.telephony.data.IDataService");
          parcel.writeInt(param2Int);
          if (param2IDataServiceCallback != null) {
            iBinder = param2IDataServiceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(7, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
            IDataService.Stub.getDefaultImpl().requestDataCallList(param2Int, param2IDataServiceCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void setDataProfile(int param2Int, List<DataProfile> param2List, boolean param2Boolean, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          IBinder iBinder;
          parcel.writeInterfaceToken("android.telephony.data.IDataService");
          parcel.writeInt(param2Int);
          parcel.writeTypedList(param2List);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2IDataServiceCallback != null) {
            iBinder = param2IDataServiceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(6, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
            IDataService.Stub.getDefaultImpl().setDataProfile(param2Int, param2List, param2Boolean, param2IDataServiceCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void setInitialAttachApn(int param2Int, DataProfile param2DataProfile, boolean param2Boolean, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.telephony.data.IDataService");
          parcel.writeInt(param2Int);
          boolean bool = false;
          if (param2DataProfile != null) {
            parcel.writeInt(1);
            param2DataProfile.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2Boolean)
            bool = true; 
          parcel.writeInt(bool);
          if (param2IDataServiceCallback != null) {
            iBinder = param2IDataServiceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(5, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
            IDataService.Stub.getDefaultImpl().setInitialAttachApn(param2Int, param2DataProfile, param2Boolean, param2IDataServiceCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void setupDataCall(int param2Int1, int param2Int2, DataProfile param2DataProfile, boolean param2Boolean1, boolean param2Boolean2, int param2Int3, LinkProperties param2LinkProperties, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.telephony.data.IDataService");
          try {
            parcel.writeInt(param2Int1);
            try {
              boolean bool;
              parcel.writeInt(param2Int2);
              if (param2DataProfile != null) {
                parcel.writeInt(1);
                param2DataProfile.writeToParcel(parcel, 0);
              } else {
                parcel.writeInt(0);
              } 
              if (param2Boolean1) {
                bool = true;
              } else {
                bool = false;
              } 
              parcel.writeInt(bool);
              if (param2Boolean2) {
                bool = true;
              } else {
                bool = false;
              } 
              parcel.writeInt(bool);
              try {
                IBinder iBinder;
                parcel.writeInt(param2Int3);
                if (param2LinkProperties != null) {
                  parcel.writeInt(1);
                  param2LinkProperties.writeToParcel(parcel, 0);
                } else {
                  parcel.writeInt(0);
                } 
                if (param2IDataServiceCallback != null) {
                  iBinder = param2IDataServiceCallback.asBinder();
                } else {
                  iBinder = null;
                } 
                parcel.writeStrongBinder(iBinder);
                if (!this.mRemote.transact(3, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
                  IDataService.Stub.getDefaultImpl().setupDataCall(param2Int1, param2Int2, param2DataProfile, param2Boolean1, param2Boolean2, param2Int3, param2LinkProperties, param2IDataServiceCallback);
                  parcel.recycle();
                  return;
                } 
                parcel.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel.recycle();
        throw param2DataProfile;
      }
      
      public void unregisterForDataCallListChanged(int param2Int, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.telephony.data.IDataService");
          parcel.writeInt(param2Int);
          if (param2IDataServiceCallback != null) {
            iBinder = param2IDataServiceCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(9, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
            IDataService.Stub.getDefaultImpl().unregisterForDataCallListChanged(param2Int, param2IDataServiceCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IDataService {
    public static IDataService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void createDataServiceProvider(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().createDataServiceProvider(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void deactivateDataCall(int param1Int1, int param1Int2, int param1Int3, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (param1IDataServiceCallback != null) {
          iBinder = param1IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().deactivateDataCall(param1Int1, param1Int2, param1Int3, param1IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.telephony.data.IDataService";
    }
    
    public void registerForDataCallListChanged(int param1Int, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param1Int);
        if (param1IDataServiceCallback != null) {
          iBinder = param1IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(8, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().registerForDataCallListChanged(param1Int, param1IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void removeDataServiceProvider(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().removeDataServiceProvider(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void requestDataCallList(int param1Int, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param1Int);
        if (param1IDataServiceCallback != null) {
          iBinder = param1IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(7, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().requestDataCallList(param1Int, param1IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setDataProfile(int param1Int, List<DataProfile> param1List, boolean param1Boolean, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param1Int);
        parcel.writeTypedList(param1List);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1IDataServiceCallback != null) {
          iBinder = param1IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(6, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().setDataProfile(param1Int, param1List, param1Boolean, param1IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setInitialAttachApn(int param1Int, DataProfile param1DataProfile, boolean param1Boolean, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param1Int);
        boolean bool = false;
        if (param1DataProfile != null) {
          parcel.writeInt(1);
          param1DataProfile.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (param1IDataServiceCallback != null) {
          iBinder = param1IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().setInitialAttachApn(param1Int, param1DataProfile, param1Boolean, param1IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setupDataCall(int param1Int1, int param1Int2, DataProfile param1DataProfile, boolean param1Boolean1, boolean param1Boolean2, int param1Int3, LinkProperties param1LinkProperties, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        try {
          parcel.writeInt(param1Int1);
          try {
            boolean bool;
            parcel.writeInt(param1Int2);
            if (param1DataProfile != null) {
              parcel.writeInt(1);
              param1DataProfile.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            if (param1Boolean1) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel.writeInt(bool);
            if (param1Boolean2) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel.writeInt(bool);
            try {
              IBinder iBinder;
              parcel.writeInt(param1Int3);
              if (param1LinkProperties != null) {
                parcel.writeInt(1);
                param1LinkProperties.writeToParcel(parcel, 0);
              } else {
                parcel.writeInt(0);
              } 
              if (param1IDataServiceCallback != null) {
                iBinder = param1IDataServiceCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel.writeStrongBinder(iBinder);
              if (!this.mRemote.transact(3, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
                IDataService.Stub.getDefaultImpl().setupDataCall(param1Int1, param1Int2, param1DataProfile, param1Boolean1, param1Boolean2, param1Int3, param1LinkProperties, param1IDataServiceCallback);
                parcel.recycle();
                return;
              } 
              parcel.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param1DataProfile;
    }
    
    public void unregisterForDataCallListChanged(int param1Int, IDataServiceCallback param1IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param1Int);
        if (param1IDataServiceCallback != null) {
          iBinder = param1IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(9, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().unregisterForDataCallListChanged(param1Int, param1IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IDataService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */