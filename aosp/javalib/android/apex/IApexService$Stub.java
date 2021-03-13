package android.apex;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IApexService {
  private static final String DESCRIPTOR = "android$apex$IApexService".replace('$', '.');
  
  static final int TRANSACTION_abortStagedSession = 8;
  
  static final int TRANSACTION_activatePackage = 16;
  
  static final int TRANSACTION_deactivatePackage = 17;
  
  static final int TRANSACTION_destroyCeSnapshotsNotSpecified = 13;
  
  static final int TRANSACTION_destroyDeSnapshots = 12;
  
  static final int TRANSACTION_getActivePackage = 15;
  
  static final int TRANSACTION_getActivePackages = 6;
  
  static final int TRANSACTION_getAllPackages = 7;
  
  static final int TRANSACTION_getSessions = 4;
  
  static final int TRANSACTION_getStagedSessionInfo = 5;
  
  static final int TRANSACTION_markStagedSessionReady = 2;
  
  static final int TRANSACTION_markStagedSessionSuccessful = 3;
  
  static final int TRANSACTION_postinstallPackages = 19;
  
  static final int TRANSACTION_preinstallPackages = 18;
  
  static final int TRANSACTION_remountPackages = 22;
  
  static final int TRANSACTION_restoreCeData = 11;
  
  static final int TRANSACTION_resumeRevertIfNeeded = 21;
  
  static final int TRANSACTION_revertActiveSessions = 9;
  
  static final int TRANSACTION_snapshotCeData = 10;
  
  static final int TRANSACTION_stagePackages = 20;
  
  static final int TRANSACTION_submitStagedSession = 1;
  
  static final int TRANSACTION_unstagePackages = 14;
  
  public Stub() {
    attachInterface(this, DESCRIPTOR);
  }
  
  public static IApexService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface(DESCRIPTOR);
    return (iInterface != null && iInterface instanceof IApexService) ? (IApexService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IApexService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static boolean setDefaultImpl(IApexService paramIApexService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIApexService != null) {
        Proxy.sDefaultImpl = paramIApexService;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    ApexInfoList apexInfoList;
    String str = DESCRIPTOR;
    if (paramInt1 != 1598968902) {
      ApexInfo apexInfo;
      ApexInfo[] arrayOfApexInfo;
      ApexSessionInfo apexSessionInfo;
      ApexSessionInfo[] arrayOfApexSessionInfo;
      long l;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 22:
          paramParcel1.enforceInterface(str);
          remountPackages();
          paramParcel2.writeNoException();
          return true;
        case 21:
          paramParcel1.enforceInterface(str);
          resumeRevertIfNeeded();
          paramParcel2.writeNoException();
          return true;
        case 20:
          paramParcel1.enforceInterface(str);
          stagePackages(paramParcel1.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 19:
          paramParcel1.enforceInterface(str);
          postinstallPackages(paramParcel1.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 18:
          paramParcel1.enforceInterface(str);
          preinstallPackages(paramParcel1.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 17:
          paramParcel1.enforceInterface(str);
          deactivatePackage(paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 16:
          paramParcel1.enforceInterface(str);
          activatePackage(paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 15:
          paramParcel1.enforceInterface(str);
          apexInfo = getActivePackage(paramParcel1.readString());
          paramParcel2.writeNoException();
          if (apexInfo != null) {
            paramParcel2.writeInt(1);
            apexInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 14:
          apexInfo.enforceInterface(str);
          unstagePackages(apexInfo.createStringArrayList());
          paramParcel2.writeNoException();
          return true;
        case 13:
          apexInfo.enforceInterface(str);
          destroyCeSnapshotsNotSpecified(apexInfo.readInt(), apexInfo.createIntArray());
          paramParcel2.writeNoException();
          return true;
        case 12:
          apexInfo.enforceInterface(str);
          destroyDeSnapshots(apexInfo.readInt());
          paramParcel2.writeNoException();
          return true;
        case 11:
          apexInfo.enforceInterface(str);
          restoreCeData(apexInfo.readInt(), apexInfo.readInt(), apexInfo.readString());
          paramParcel2.writeNoException();
          return true;
        case 10:
          apexInfo.enforceInterface(str);
          l = snapshotCeData(apexInfo.readInt(), apexInfo.readInt(), apexInfo.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 9:
          apexInfo.enforceInterface(str);
          revertActiveSessions();
          paramParcel2.writeNoException();
          return true;
        case 8:
          apexInfo.enforceInterface(str);
          abortStagedSession(apexInfo.readInt());
          paramParcel2.writeNoException();
          return true;
        case 7:
          apexInfo.enforceInterface(str);
          arrayOfApexInfo = getAllPackages();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfApexInfo, 1);
          return true;
        case 6:
          arrayOfApexInfo.enforceInterface(str);
          arrayOfApexInfo = getActivePackages();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfApexInfo, 1);
          return true;
        case 5:
          arrayOfApexInfo.enforceInterface(str);
          apexSessionInfo = getStagedSessionInfo(arrayOfApexInfo.readInt());
          paramParcel2.writeNoException();
          if (apexSessionInfo != null) {
            paramParcel2.writeInt(1);
            apexSessionInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 4:
          apexSessionInfo.enforceInterface(str);
          arrayOfApexSessionInfo = getSessions();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfApexSessionInfo, 1);
          return true;
        case 3:
          arrayOfApexSessionInfo.enforceInterface(str);
          markStagedSessionSuccessful(arrayOfApexSessionInfo.readInt());
          paramParcel2.writeNoException();
          return true;
        case 2:
          arrayOfApexSessionInfo.enforceInterface(str);
          markStagedSessionReady(arrayOfApexSessionInfo.readInt());
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      arrayOfApexSessionInfo.enforceInterface(str);
      if (arrayOfApexSessionInfo.readInt() != 0) {
        ApexSessionParams apexSessionParams = (ApexSessionParams)ApexSessionParams.CREATOR.createFromParcel((Parcel)arrayOfApexSessionInfo);
      } else {
        arrayOfApexSessionInfo = null;
      } 
      apexInfoList = new ApexInfoList();
      submitStagedSession((ApexSessionParams)arrayOfApexSessionInfo, apexInfoList);
      paramParcel2.writeNoException();
      paramParcel2.writeInt(1);
      apexInfoList.writeToParcel(paramParcel2, 1);
      return true;
    } 
    paramParcel2.writeString((String)apexInfoList);
    return true;
  }
  
  private static class Proxy implements IApexService {
    public static IApexService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void abortStagedSession(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().abortStagedSession(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void activatePackage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().activatePackage(param2String);
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
    
    public void deactivatePackage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().deactivatePackage(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void destroyCeSnapshotsNotSpecified(int param2Int, int[] param2ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeInt(param2Int);
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().destroyCeSnapshotsNotSpecified(param2Int, param2ArrayOfint);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void destroyDeSnapshots(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().destroyDeSnapshots(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ApexInfo getActivePackage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null)
          return IApexService.Stub.getDefaultImpl().getActivePackage(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ApexInfo apexInfo = (ApexInfo)ApexInfo.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ApexInfo)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ApexInfo[] getActivePackages() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null)
          return IApexService.Stub.getDefaultImpl().getActivePackages(); 
        parcel2.readException();
        return (ApexInfo[])parcel2.createTypedArray(ApexInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ApexInfo[] getAllPackages() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null)
          return IApexService.Stub.getDefaultImpl().getAllPackages(); 
        parcel2.readException();
        return (ApexInfo[])parcel2.createTypedArray(ApexInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return IApexService.Stub.DESCRIPTOR;
    }
    
    public ApexSessionInfo[] getSessions() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null)
          return IApexService.Stub.getDefaultImpl().getSessions(); 
        parcel2.readException();
        return (ApexSessionInfo[])parcel2.createTypedArray(ApexSessionInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ApexSessionInfo getStagedSessionInfo(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ApexSessionInfo apexSessionInfo;
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          apexSessionInfo = IApexService.Stub.getDefaultImpl().getStagedSessionInfo(param2Int);
          return apexSessionInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          apexSessionInfo = (ApexSessionInfo)ApexSessionInfo.CREATOR.createFromParcel(parcel2);
        } else {
          apexSessionInfo = null;
        } 
        return apexSessionInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void markStagedSessionReady(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().markStagedSessionReady(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void markStagedSessionSuccessful(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().markStagedSessionSuccessful(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void postinstallPackages(List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().postinstallPackages(param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void preinstallPackages(List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().preinstallPackages(param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void remountPackages() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().remountPackages();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void restoreCeData(int param2Int1, int param2Int2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().restoreCeData(param2Int1, param2Int2, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resumeRevertIfNeeded() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().resumeRevertIfNeeded();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void revertActiveSessions() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().revertActiveSessions();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long snapshotCeData(int param2Int1, int param2Int2, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null)
          return IApexService.Stub.getDefaultImpl().snapshotCeData(param2Int1, param2Int2, param2String); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stagePackages(List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().stagePackages(param2List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void submitStagedSession(ApexSessionParams param2ApexSessionParams, ApexInfoList param2ApexInfoList) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        if (param2ApexSessionParams != null) {
          parcel1.writeInt(1);
          param2ApexSessionParams.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().submitStagedSession(param2ApexSessionParams, param2ApexInfoList);
          return;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0)
          param2ApexInfoList.readFromParcel(parcel2); 
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unstagePackages(List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken(IApexService.Stub.DESCRIPTOR);
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
          IApexService.Stub.getDefaultImpl().unstagePackages(param2List);
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


/* Location:              /home/chun/Desktop/temp/!/android/apex/IApexService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */