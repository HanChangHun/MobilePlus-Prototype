package android.hardware.location;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IContextHubService extends IInterface {
  IContextHubClient createClient(int paramInt, IContextHubClientCallback paramIContextHubClientCallback) throws RemoteException;
  
  IContextHubClient createPendingIntentClient(int paramInt, PendingIntent paramPendingIntent, long paramLong) throws RemoteException;
  
  void disableNanoApp(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, long paramLong) throws RemoteException;
  
  void enableNanoApp(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, long paramLong) throws RemoteException;
  
  int[] findNanoAppOnHub(int paramInt, NanoAppFilter paramNanoAppFilter) throws RemoteException;
  
  int[] getContextHubHandles() throws RemoteException;
  
  ContextHubInfo getContextHubInfo(int paramInt) throws RemoteException;
  
  List<ContextHubInfo> getContextHubs() throws RemoteException;
  
  NanoAppInstanceInfo getNanoAppInstanceInfo(int paramInt) throws RemoteException;
  
  int loadNanoApp(int paramInt, NanoApp paramNanoApp) throws RemoteException;
  
  void loadNanoAppOnHub(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, NanoAppBinary paramNanoAppBinary) throws RemoteException;
  
  void queryNanoApps(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback) throws RemoteException;
  
  int registerCallback(IContextHubCallback paramIContextHubCallback) throws RemoteException;
  
  int sendMessage(int paramInt1, int paramInt2, ContextHubMessage paramContextHubMessage) throws RemoteException;
  
  int unloadNanoApp(int paramInt) throws RemoteException;
  
  void unloadNanoAppFromHub(int paramInt, IContextHubTransactionCallback paramIContextHubTransactionCallback, long paramLong) throws RemoteException;
  
  public static class Default implements IContextHubService {
    public IBinder asBinder() {
      return null;
    }
    
    public IContextHubClient createClient(int param1Int, IContextHubClientCallback param1IContextHubClientCallback) throws RemoteException {
      return null;
    }
    
    public IContextHubClient createPendingIntentClient(int param1Int, PendingIntent param1PendingIntent, long param1Long) throws RemoteException {
      return null;
    }
    
    public void disableNanoApp(int param1Int, IContextHubTransactionCallback param1IContextHubTransactionCallback, long param1Long) throws RemoteException {}
    
    public void enableNanoApp(int param1Int, IContextHubTransactionCallback param1IContextHubTransactionCallback, long param1Long) throws RemoteException {}
    
    public int[] findNanoAppOnHub(int param1Int, NanoAppFilter param1NanoAppFilter) throws RemoteException {
      return null;
    }
    
    public int[] getContextHubHandles() throws RemoteException {
      return null;
    }
    
    public ContextHubInfo getContextHubInfo(int param1Int) throws RemoteException {
      return null;
    }
    
    public List<ContextHubInfo> getContextHubs() throws RemoteException {
      return null;
    }
    
    public NanoAppInstanceInfo getNanoAppInstanceInfo(int param1Int) throws RemoteException {
      return null;
    }
    
    public int loadNanoApp(int param1Int, NanoApp param1NanoApp) throws RemoteException {
      return 0;
    }
    
    public void loadNanoAppOnHub(int param1Int, IContextHubTransactionCallback param1IContextHubTransactionCallback, NanoAppBinary param1NanoAppBinary) throws RemoteException {}
    
    public void queryNanoApps(int param1Int, IContextHubTransactionCallback param1IContextHubTransactionCallback) throws RemoteException {}
    
    public int registerCallback(IContextHubCallback param1IContextHubCallback) throws RemoteException {
      return 0;
    }
    
    public int sendMessage(int param1Int1, int param1Int2, ContextHubMessage param1ContextHubMessage) throws RemoteException {
      return 0;
    }
    
    public int unloadNanoApp(int param1Int) throws RemoteException {
      return 0;
    }
    
    public void unloadNanoAppFromHub(int param1Int, IContextHubTransactionCallback param1IContextHubTransactionCallback, long param1Long) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IContextHubService {
    private static final String DESCRIPTOR = "android.hardware.location.IContextHubService";
    
    static final int TRANSACTION_createClient = 9;
    
    static final int TRANSACTION_createPendingIntentClient = 10;
    
    static final int TRANSACTION_disableNanoApp = 15;
    
    static final int TRANSACTION_enableNanoApp = 14;
    
    static final int TRANSACTION_findNanoAppOnHub = 7;
    
    static final int TRANSACTION_getContextHubHandles = 2;
    
    static final int TRANSACTION_getContextHubInfo = 3;
    
    static final int TRANSACTION_getContextHubs = 11;
    
    static final int TRANSACTION_getNanoAppInstanceInfo = 6;
    
    static final int TRANSACTION_loadNanoApp = 4;
    
    static final int TRANSACTION_loadNanoAppOnHub = 12;
    
    static final int TRANSACTION_queryNanoApps = 16;
    
    static final int TRANSACTION_registerCallback = 1;
    
    static final int TRANSACTION_sendMessage = 8;
    
    static final int TRANSACTION_unloadNanoApp = 5;
    
    static final int TRANSACTION_unloadNanoAppFromHub = 13;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IContextHubService");
    }
    
    public static IContextHubService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IContextHubService");
      return (iInterface != null && iInterface instanceof IContextHubService) ? (IContextHubService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IContextHubService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 16:
          return "queryNanoApps";
        case 15:
          return "disableNanoApp";
        case 14:
          return "enableNanoApp";
        case 13:
          return "unloadNanoAppFromHub";
        case 12:
          return "loadNanoAppOnHub";
        case 11:
          return "getContextHubs";
        case 10:
          return "createPendingIntentClient";
        case 9:
          return "createClient";
        case 8:
          return "sendMessage";
        case 7:
          return "findNanoAppOnHub";
        case 6:
          return "getNanoAppInstanceInfo";
        case 5:
          return "unloadNanoApp";
        case 4:
          return "loadNanoApp";
        case 3:
          return "getContextHubInfo";
        case 2:
          return "getContextHubHandles";
        case 1:
          break;
      } 
      return "registerCallback";
    }
    
    public static boolean setDefaultImpl(IContextHubService param1IContextHubService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IContextHubService != null) {
          Proxy.sDefaultImpl = param1IContextHubService;
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
        List<ContextHubInfo> list;
        IBinder iBinder2;
        IContextHubClient iContextHubClient1;
        IBinder iBinder1;
        int[] arrayOfInt2;
        NanoAppInstanceInfo nanoAppInstanceInfo;
        ContextHubInfo contextHubInfo;
        int[] arrayOfInt1;
        IContextHubClient iContextHubClient2;
        IContextHubClient iContextHubClient3;
        IContextHubTransactionCallback iContextHubTransactionCallback = null;
        List list1 = null;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 16:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubService");
            queryNanoApps(param1Parcel1.readInt(), IContextHubTransactionCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 15:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubService");
            disableNanoApp(param1Parcel1.readInt(), IContextHubTransactionCallback.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readLong());
            param1Parcel2.writeNoException();
            return true;
          case 14:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubService");
            enableNanoApp(param1Parcel1.readInt(), IContextHubTransactionCallback.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readLong());
            param1Parcel2.writeNoException();
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubService");
            unloadNanoAppFromHub(param1Parcel1.readInt(), IContextHubTransactionCallback.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readLong());
            param1Parcel2.writeNoException();
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubService");
            param1Int1 = param1Parcel1.readInt();
            iContextHubTransactionCallback = IContextHubTransactionCallback.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              NanoAppBinary nanoAppBinary = (NanoAppBinary)NanoAppBinary.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            loadNanoAppOnHub(param1Int1, iContextHubTransactionCallback, (NanoAppBinary)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 11:
            param1Parcel1.enforceInterface("android.hardware.location.IContextHubService");
            list = getContextHubs();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 10:
            list.enforceInterface("android.hardware.location.IContextHubService");
            param1Int1 = list.readInt();
            if (list.readInt() != 0) {
              PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)list);
            } else {
              iContextHubTransactionCallback = null;
            } 
            iContextHubClient2 = createPendingIntentClient(param1Int1, (PendingIntent)iContextHubTransactionCallback, list.readLong());
            param1Parcel2.writeNoException();
            list = list1;
            if (iContextHubClient2 != null)
              iBinder2 = iContextHubClient2.asBinder(); 
            param1Parcel2.writeStrongBinder(iBinder2);
            return true;
          case 9:
            iBinder2.enforceInterface("android.hardware.location.IContextHubService");
            iContextHubClient3 = createClient(iBinder2.readInt(), IContextHubClientCallback.Stub.asInterface(iBinder2.readStrongBinder()));
            param1Parcel2.writeNoException();
            iContextHubClient1 = iContextHubClient2;
            if (iContextHubClient3 != null)
              iBinder1 = iContextHubClient3.asBinder(); 
            param1Parcel2.writeStrongBinder(iBinder1);
            return true;
          case 8:
            iBinder1.enforceInterface("android.hardware.location.IContextHubService");
            param1Int2 = iBinder1.readInt();
            param1Int1 = iBinder1.readInt();
            if (iBinder1.readInt() != 0) {
              ContextHubMessage contextHubMessage = (ContextHubMessage)ContextHubMessage.CREATOR.createFromParcel((Parcel)iBinder1);
            } else {
              iBinder1 = null;
            } 
            param1Int1 = sendMessage(param1Int2, param1Int1, (ContextHubMessage)iBinder1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 7:
            iBinder1.enforceInterface("android.hardware.location.IContextHubService");
            param1Int1 = iBinder1.readInt();
            if (iBinder1.readInt() != 0) {
              NanoAppFilter nanoAppFilter = (NanoAppFilter)NanoAppFilter.CREATOR.createFromParcel((Parcel)iBinder1);
            } else {
              iBinder1 = null;
            } 
            arrayOfInt2 = findNanoAppOnHub(param1Int1, (NanoAppFilter)iBinder1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeIntArray(arrayOfInt2);
            return true;
          case 6:
            arrayOfInt2.enforceInterface("android.hardware.location.IContextHubService");
            nanoAppInstanceInfo = getNanoAppInstanceInfo(arrayOfInt2.readInt());
            param1Parcel2.writeNoException();
            if (nanoAppInstanceInfo != null) {
              param1Parcel2.writeInt(1);
              nanoAppInstanceInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 5:
            nanoAppInstanceInfo.enforceInterface("android.hardware.location.IContextHubService");
            param1Int1 = unloadNanoApp(nanoAppInstanceInfo.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 4:
            nanoAppInstanceInfo.enforceInterface("android.hardware.location.IContextHubService");
            param1Int1 = nanoAppInstanceInfo.readInt();
            if (nanoAppInstanceInfo.readInt() != 0) {
              NanoApp nanoApp = (NanoApp)NanoApp.CREATOR.createFromParcel((Parcel)nanoAppInstanceInfo);
            } else {
              nanoAppInstanceInfo = null;
            } 
            param1Int1 = loadNanoApp(param1Int1, (NanoApp)nanoAppInstanceInfo);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 3:
            nanoAppInstanceInfo.enforceInterface("android.hardware.location.IContextHubService");
            contextHubInfo = getContextHubInfo(nanoAppInstanceInfo.readInt());
            param1Parcel2.writeNoException();
            if (contextHubInfo != null) {
              param1Parcel2.writeInt(1);
              contextHubInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 2:
            contextHubInfo.enforceInterface("android.hardware.location.IContextHubService");
            arrayOfInt1 = getContextHubHandles();
            param1Parcel2.writeNoException();
            param1Parcel2.writeIntArray(arrayOfInt1);
            return true;
          case 1:
            break;
        } 
        arrayOfInt1.enforceInterface("android.hardware.location.IContextHubService");
        param1Int1 = registerCallback(IContextHubCallback.Stub.asInterface(arrayOfInt1.readStrongBinder()));
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(param1Int1);
        return true;
      } 
      param1Parcel2.writeString("android.hardware.location.IContextHubService");
      return true;
    }
    
    private static class Proxy implements IContextHubService {
      public static IContextHubService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public IContextHubClient createClient(int param2Int, IContextHubClientCallback param2IContextHubClientCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (param2IContextHubClientCallback != null) {
            iBinder = param2IContextHubClientCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
            return IContextHubService.Stub.getDefaultImpl().createClient(param2Int, param2IContextHubClientCallback); 
          parcel2.readException();
          return IContextHubClient.Stub.asInterface(parcel2.readStrongBinder());
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IContextHubClient createPendingIntentClient(int param2Int, PendingIntent param2PendingIntent, long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (param2PendingIntent != null) {
            parcel1.writeInt(1);
            param2PendingIntent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeLong(param2Long);
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
            return IContextHubService.Stub.getDefaultImpl().createPendingIntentClient(param2Int, param2PendingIntent, param2Long); 
          parcel2.readException();
          return IContextHubClient.Stub.asInterface(parcel2.readStrongBinder());
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void disableNanoApp(int param2Int, IContextHubTransactionCallback param2IContextHubTransactionCallback, long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (param2IContextHubTransactionCallback != null) {
            iBinder = param2IContextHubTransactionCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeLong(param2Long);
          if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
            IContextHubService.Stub.getDefaultImpl().disableNanoApp(param2Int, param2IContextHubTransactionCallback, param2Long);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void enableNanoApp(int param2Int, IContextHubTransactionCallback param2IContextHubTransactionCallback, long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (param2IContextHubTransactionCallback != null) {
            iBinder = param2IContextHubTransactionCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeLong(param2Long);
          if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
            IContextHubService.Stub.getDefaultImpl().enableNanoApp(param2Int, param2IContextHubTransactionCallback, param2Long);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int[] findNanoAppOnHub(int param2Int, NanoAppFilter param2NanoAppFilter) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (param2NanoAppFilter != null) {
            parcel1.writeInt(1);
            param2NanoAppFilter.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
            return IContextHubService.Stub.getDefaultImpl().findNanoAppOnHub(param2Int, param2NanoAppFilter); 
          parcel2.readException();
          return parcel2.createIntArray();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int[] getContextHubHandles() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
            return IContextHubService.Stub.getDefaultImpl().getContextHubHandles(); 
          parcel2.readException();
          return parcel2.createIntArray();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ContextHubInfo getContextHubInfo(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ContextHubInfo contextHubInfo;
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
            contextHubInfo = IContextHubService.Stub.getDefaultImpl().getContextHubInfo(param2Int);
            return contextHubInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            contextHubInfo = (ContextHubInfo)ContextHubInfo.CREATOR.createFromParcel(parcel2);
          } else {
            contextHubInfo = null;
          } 
          return contextHubInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ContextHubInfo> getContextHubs() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
            return IContextHubService.Stub.getDefaultImpl().getContextHubs(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ContextHubInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.location.IContextHubService";
      }
      
      public NanoAppInstanceInfo getNanoAppInstanceInfo(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          NanoAppInstanceInfo nanoAppInstanceInfo;
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
            nanoAppInstanceInfo = IContextHubService.Stub.getDefaultImpl().getNanoAppInstanceInfo(param2Int);
            return nanoAppInstanceInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            nanoAppInstanceInfo = (NanoAppInstanceInfo)NanoAppInstanceInfo.CREATOR.createFromParcel(parcel2);
          } else {
            nanoAppInstanceInfo = null;
          } 
          return nanoAppInstanceInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int loadNanoApp(int param2Int, NanoApp param2NanoApp) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (param2NanoApp != null) {
            parcel1.writeInt(1);
            param2NanoApp.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
            param2Int = IContextHubService.Stub.getDefaultImpl().loadNanoApp(param2Int, param2NanoApp);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void loadNanoAppOnHub(int param2Int, IContextHubTransactionCallback param2IContextHubTransactionCallback, NanoAppBinary param2NanoAppBinary) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (param2IContextHubTransactionCallback != null) {
            iBinder = param2IContextHubTransactionCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2NanoAppBinary != null) {
            parcel1.writeInt(1);
            param2NanoAppBinary.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
            IContextHubService.Stub.getDefaultImpl().loadNanoAppOnHub(param2Int, param2IContextHubTransactionCallback, param2NanoAppBinary);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void queryNanoApps(int param2Int, IContextHubTransactionCallback param2IContextHubTransactionCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (param2IContextHubTransactionCallback != null) {
            iBinder = param2IContextHubTransactionCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
            IContextHubService.Stub.getDefaultImpl().queryNanoApps(param2Int, param2IContextHubTransactionCallback);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int registerCallback(IContextHubCallback param2IContextHubCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          if (param2IContextHubCallback != null) {
            iBinder = param2IContextHubCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
            return IContextHubService.Stub.getDefaultImpl().registerCallback(param2IContextHubCallback); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int sendMessage(int param2Int1, int param2Int2, ContextHubMessage param2ContextHubMessage) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2ContextHubMessage != null) {
            parcel1.writeInt(1);
            param2ContextHubMessage.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
            param2Int1 = IContextHubService.Stub.getDefaultImpl().sendMessage(param2Int1, param2Int2, param2ContextHubMessage);
            return param2Int1;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          return param2Int1;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int unloadNanoApp(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
            param2Int = IContextHubService.Stub.getDefaultImpl().unloadNanoApp(param2Int);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unloadNanoAppFromHub(int param2Int, IContextHubTransactionCallback param2IContextHubTransactionCallback, long param2Long) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
          parcel1.writeInt(param2Int);
          if (param2IContextHubTransactionCallback != null) {
            iBinder = param2IContextHubTransactionCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeLong(param2Long);
          if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
            IContextHubService.Stub.getDefaultImpl().unloadNanoAppFromHub(param2Int, param2IContextHubTransactionCallback, param2Long);
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
  
  private static class Proxy implements IContextHubService {
    public static IContextHubService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public IContextHubClient createClient(int param1Int, IContextHubClientCallback param1IContextHubClientCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (param1IContextHubClientCallback != null) {
          iBinder = param1IContextHubClientCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
          return IContextHubService.Stub.getDefaultImpl().createClient(param1Int, param1IContextHubClientCallback); 
        parcel2.readException();
        return IContextHubClient.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IContextHubClient createPendingIntentClient(int param1Int, PendingIntent param1PendingIntent, long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (param1PendingIntent != null) {
          parcel1.writeInt(1);
          param1PendingIntent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeLong(param1Long);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
          return IContextHubService.Stub.getDefaultImpl().createPendingIntentClient(param1Int, param1PendingIntent, param1Long); 
        parcel2.readException();
        return IContextHubClient.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void disableNanoApp(int param1Int, IContextHubTransactionCallback param1IContextHubTransactionCallback, long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (param1IContextHubTransactionCallback != null) {
          iBinder = param1IContextHubTransactionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeLong(param1Long);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
          IContextHubService.Stub.getDefaultImpl().disableNanoApp(param1Int, param1IContextHubTransactionCallback, param1Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enableNanoApp(int param1Int, IContextHubTransactionCallback param1IContextHubTransactionCallback, long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (param1IContextHubTransactionCallback != null) {
          iBinder = param1IContextHubTransactionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeLong(param1Long);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
          IContextHubService.Stub.getDefaultImpl().enableNanoApp(param1Int, param1IContextHubTransactionCallback, param1Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int[] findNanoAppOnHub(int param1Int, NanoAppFilter param1NanoAppFilter) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (param1NanoAppFilter != null) {
          parcel1.writeInt(1);
          param1NanoAppFilter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
          return IContextHubService.Stub.getDefaultImpl().findNanoAppOnHub(param1Int, param1NanoAppFilter); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int[] getContextHubHandles() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
          return IContextHubService.Stub.getDefaultImpl().getContextHubHandles(); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ContextHubInfo getContextHubInfo(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ContextHubInfo contextHubInfo;
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
          contextHubInfo = IContextHubService.Stub.getDefaultImpl().getContextHubInfo(param1Int);
          return contextHubInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          contextHubInfo = (ContextHubInfo)ContextHubInfo.CREATOR.createFromParcel(parcel2);
        } else {
          contextHubInfo = null;
        } 
        return contextHubInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ContextHubInfo> getContextHubs() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
          return IContextHubService.Stub.getDefaultImpl().getContextHubs(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ContextHubInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IContextHubService";
    }
    
    public NanoAppInstanceInfo getNanoAppInstanceInfo(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        NanoAppInstanceInfo nanoAppInstanceInfo;
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
          nanoAppInstanceInfo = IContextHubService.Stub.getDefaultImpl().getNanoAppInstanceInfo(param1Int);
          return nanoAppInstanceInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          nanoAppInstanceInfo = (NanoAppInstanceInfo)NanoAppInstanceInfo.CREATOR.createFromParcel(parcel2);
        } else {
          nanoAppInstanceInfo = null;
        } 
        return nanoAppInstanceInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int loadNanoApp(int param1Int, NanoApp param1NanoApp) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (param1NanoApp != null) {
          parcel1.writeInt(1);
          param1NanoApp.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
          param1Int = IContextHubService.Stub.getDefaultImpl().loadNanoApp(param1Int, param1NanoApp);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void loadNanoAppOnHub(int param1Int, IContextHubTransactionCallback param1IContextHubTransactionCallback, NanoAppBinary param1NanoAppBinary) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (param1IContextHubTransactionCallback != null) {
          iBinder = param1IContextHubTransactionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1NanoAppBinary != null) {
          parcel1.writeInt(1);
          param1NanoAppBinary.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
          IContextHubService.Stub.getDefaultImpl().loadNanoAppOnHub(param1Int, param1IContextHubTransactionCallback, param1NanoAppBinary);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void queryNanoApps(int param1Int, IContextHubTransactionCallback param1IContextHubTransactionCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (param1IContextHubTransactionCallback != null) {
          iBinder = param1IContextHubTransactionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
          IContextHubService.Stub.getDefaultImpl().queryNanoApps(param1Int, param1IContextHubTransactionCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int registerCallback(IContextHubCallback param1IContextHubCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        if (param1IContextHubCallback != null) {
          iBinder = param1IContextHubCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null)
          return IContextHubService.Stub.getDefaultImpl().registerCallback(param1IContextHubCallback); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int sendMessage(int param1Int1, int param1Int2, ContextHubMessage param1ContextHubMessage) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1ContextHubMessage != null) {
          parcel1.writeInt(1);
          param1ContextHubMessage.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
          param1Int1 = IContextHubService.Stub.getDefaultImpl().sendMessage(param1Int1, param1Int2, param1ContextHubMessage);
          return param1Int1;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        return param1Int1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int unloadNanoApp(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
          param1Int = IContextHubService.Stub.getDefaultImpl().unloadNanoApp(param1Int);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unloadNanoAppFromHub(int param1Int, IContextHubTransactionCallback param1IContextHubTransactionCallback, long param1Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.location.IContextHubService");
        parcel1.writeInt(param1Int);
        if (param1IContextHubTransactionCallback != null) {
          iBinder = param1IContextHubTransactionCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeLong(param1Long);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IContextHubService.Stub.getDefaultImpl() != null) {
          IContextHubService.Stub.getDefaultImpl().unloadNanoAppFromHub(param1Int, param1IContextHubTransactionCallback, param1Long);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */