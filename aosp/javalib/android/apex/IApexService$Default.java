package android.apex;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IApexService {
  public void abortStagedSession(int paramInt) throws RemoteException {}
  
  public void activatePackage(String paramString) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void deactivatePackage(String paramString) throws RemoteException {}
  
  public void destroyCeSnapshotsNotSpecified(int paramInt, int[] paramArrayOfint) throws RemoteException {}
  
  public void destroyDeSnapshots(int paramInt) throws RemoteException {}
  
  public ApexInfo getActivePackage(String paramString) throws RemoteException {
    return null;
  }
  
  public ApexInfo[] getActivePackages() throws RemoteException {
    return null;
  }
  
  public ApexInfo[] getAllPackages() throws RemoteException {
    return null;
  }
  
  public ApexSessionInfo[] getSessions() throws RemoteException {
    return null;
  }
  
  public ApexSessionInfo getStagedSessionInfo(int paramInt) throws RemoteException {
    return null;
  }
  
  public void markStagedSessionReady(int paramInt) throws RemoteException {}
  
  public void markStagedSessionSuccessful(int paramInt) throws RemoteException {}
  
  public void postinstallPackages(List<String> paramList) throws RemoteException {}
  
  public void preinstallPackages(List<String> paramList) throws RemoteException {}
  
  public void remountPackages() throws RemoteException {}
  
  public void restoreCeData(int paramInt1, int paramInt2, String paramString) throws RemoteException {}
  
  public void resumeRevertIfNeeded() throws RemoteException {}
  
  public void revertActiveSessions() throws RemoteException {}
  
  public long snapshotCeData(int paramInt1, int paramInt2, String paramString) throws RemoteException {
    return 0L;
  }
  
  public void stagePackages(List<String> paramList) throws RemoteException {}
  
  public void submitStagedSession(ApexSessionParams paramApexSessionParams, ApexInfoList paramApexInfoList) throws RemoteException {}
  
  public void unstagePackages(List<String> paramList) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/apex/IApexService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */