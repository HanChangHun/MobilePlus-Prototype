package android.hardware.location;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IContextHubService {
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
  
  public static IContextHubService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IContextHubService");
    return (iInterface != null && iInterface instanceof IContextHubService) ? (IContextHubService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IContextHubService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(IContextHubService paramIContextHubService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIContextHubService != null) {
        Proxy.sDefaultImpl = paramIContextHubService;
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
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 16:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubService");
          queryNanoApps(paramParcel1.readInt(), IContextHubTransactionCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 15:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubService");
          disableNanoApp(paramParcel1.readInt(), IContextHubTransactionCallback.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        case 14:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubService");
          enableNanoApp(paramParcel1.readInt(), IContextHubTransactionCallback.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        case 13:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubService");
          unloadNanoAppFromHub(paramParcel1.readInt(), IContextHubTransactionCallback.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        case 12:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubService");
          paramInt1 = paramParcel1.readInt();
          iContextHubTransactionCallback = IContextHubTransactionCallback.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            NanoAppBinary nanoAppBinary = (NanoAppBinary)NanoAppBinary.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          loadNanoAppOnHub(paramInt1, iContextHubTransactionCallback, (NanoAppBinary)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 11:
          paramParcel1.enforceInterface("android.hardware.location.IContextHubService");
          list = getContextHubs();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 10:
          list.enforceInterface("android.hardware.location.IContextHubService");
          paramInt1 = list.readInt();
          if (list.readInt() != 0) {
            PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)list);
          } else {
            iContextHubTransactionCallback = null;
          } 
          iContextHubClient2 = createPendingIntentClient(paramInt1, (PendingIntent)iContextHubTransactionCallback, list.readLong());
          paramParcel2.writeNoException();
          list = list1;
          if (iContextHubClient2 != null)
            iBinder2 = iContextHubClient2.asBinder(); 
          paramParcel2.writeStrongBinder(iBinder2);
          return true;
        case 9:
          iBinder2.enforceInterface("android.hardware.location.IContextHubService");
          iContextHubClient3 = createClient(iBinder2.readInt(), IContextHubClientCallback.Stub.asInterface(iBinder2.readStrongBinder()));
          paramParcel2.writeNoException();
          iContextHubClient1 = iContextHubClient2;
          if (iContextHubClient3 != null)
            iBinder1 = iContextHubClient3.asBinder(); 
          paramParcel2.writeStrongBinder(iBinder1);
          return true;
        case 8:
          iBinder1.enforceInterface("android.hardware.location.IContextHubService");
          paramInt2 = iBinder1.readInt();
          paramInt1 = iBinder1.readInt();
          if (iBinder1.readInt() != 0) {
            ContextHubMessage contextHubMessage = (ContextHubMessage)ContextHubMessage.CREATOR.createFromParcel((Parcel)iBinder1);
          } else {
            iBinder1 = null;
          } 
          paramInt1 = sendMessage(paramInt2, paramInt1, (ContextHubMessage)iBinder1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 7:
          iBinder1.enforceInterface("android.hardware.location.IContextHubService");
          paramInt1 = iBinder1.readInt();
          if (iBinder1.readInt() != 0) {
            NanoAppFilter nanoAppFilter = (NanoAppFilter)NanoAppFilter.CREATOR.createFromParcel((Parcel)iBinder1);
          } else {
            iBinder1 = null;
          } 
          arrayOfInt2 = findNanoAppOnHub(paramInt1, (NanoAppFilter)iBinder1);
          paramParcel2.writeNoException();
          paramParcel2.writeIntArray(arrayOfInt2);
          return true;
        case 6:
          arrayOfInt2.enforceInterface("android.hardware.location.IContextHubService");
          nanoAppInstanceInfo = getNanoAppInstanceInfo(arrayOfInt2.readInt());
          paramParcel2.writeNoException();
          if (nanoAppInstanceInfo != null) {
            paramParcel2.writeInt(1);
            nanoAppInstanceInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 5:
          nanoAppInstanceInfo.enforceInterface("android.hardware.location.IContextHubService");
          paramInt1 = unloadNanoApp(nanoAppInstanceInfo.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 4:
          nanoAppInstanceInfo.enforceInterface("android.hardware.location.IContextHubService");
          paramInt1 = nanoAppInstanceInfo.readInt();
          if (nanoAppInstanceInfo.readInt() != 0) {
            NanoApp nanoApp = (NanoApp)NanoApp.CREATOR.createFromParcel((Parcel)nanoAppInstanceInfo);
          } else {
            nanoAppInstanceInfo = null;
          } 
          paramInt1 = loadNanoApp(paramInt1, (NanoApp)nanoAppInstanceInfo);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 3:
          nanoAppInstanceInfo.enforceInterface("android.hardware.location.IContextHubService");
          contextHubInfo = getContextHubInfo(nanoAppInstanceInfo.readInt());
          paramParcel2.writeNoException();
          if (contextHubInfo != null) {
            paramParcel2.writeInt(1);
            contextHubInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 2:
          contextHubInfo.enforceInterface("android.hardware.location.IContextHubService");
          arrayOfInt1 = getContextHubHandles();
          paramParcel2.writeNoException();
          paramParcel2.writeIntArray(arrayOfInt1);
          return true;
        case 1:
          break;
      } 
      arrayOfInt1.enforceInterface("android.hardware.location.IContextHubService");
      paramInt1 = registerCallback(IContextHubCallback.Stub.asInterface(arrayOfInt1.readStrongBinder()));
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
      return true;
    } 
    paramParcel2.writeString("android.hardware.location.IContextHubService");
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */