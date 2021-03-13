package android.apex;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IApexService {
  public static IApexService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void abortStagedSession(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().abortStagedSession(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void activatePackage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().activatePackage(paramString);
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
  
  public void deactivatePackage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().deactivatePackage(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void destroyCeSnapshotsNotSpecified(int paramInt, int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeInt(paramInt);
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().destroyCeSnapshotsNotSpecified(paramInt, paramArrayOfint);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void destroyDeSnapshots(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().destroyDeSnapshots(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ApexInfo getActivePackage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null)
        return IApexService.Stub.getDefaultImpl().getActivePackage(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ApexInfo apexInfo = (ApexInfo)ApexInfo.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ApexInfo)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ApexInfo[] getActivePackages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
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
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
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
    return IApexService.Stub.access$000();
  }
  
  public ApexSessionInfo[] getSessions() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null)
        return IApexService.Stub.getDefaultImpl().getSessions(); 
      parcel2.readException();
      return (ApexSessionInfo[])parcel2.createTypedArray(ApexSessionInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ApexSessionInfo getStagedSessionInfo(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ApexSessionInfo apexSessionInfo;
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        apexSessionInfo = IApexService.Stub.getDefaultImpl().getStagedSessionInfo(paramInt);
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
  
  public void markStagedSessionReady(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().markStagedSessionReady(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void markStagedSessionSuccessful(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().markStagedSessionSuccessful(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void postinstallPackages(List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().postinstallPackages(paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void preinstallPackages(List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().preinstallPackages(paramList);
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
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
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
  
  public void restoreCeData(int paramInt1, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().restoreCeData(paramInt1, paramInt2, paramString);
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
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
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
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
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
  
  public long snapshotCeData(int paramInt1, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null)
        return IApexService.Stub.getDefaultImpl().snapshotCeData(paramInt1, paramInt2, paramString); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stagePackages(List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().stagePackages(paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void submitStagedSession(ApexSessionParams paramApexSessionParams, ApexInfoList paramApexInfoList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      if (paramApexSessionParams != null) {
        parcel1.writeInt(1);
        paramApexSessionParams.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().submitStagedSession(paramApexSessionParams, paramApexInfoList);
        return;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0)
        paramApexInfoList.readFromParcel(parcel2); 
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unstagePackages(List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken(IApexService.Stub.access$000());
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IApexService.Stub.getDefaultImpl() != null) {
        IApexService.Stub.getDefaultImpl().unstagePackages(paramList);
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


/* Location:              /home/chun/Desktop/temp/!/android/apex/IApexService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */