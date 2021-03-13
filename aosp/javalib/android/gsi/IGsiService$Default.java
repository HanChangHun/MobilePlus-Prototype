package android.gsi;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;

public class Default implements IGsiService {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean cancelGsiInstall() throws RemoteException {
    return false;
  }
  
  public int closeInstall() throws RemoteException {
    return 0;
  }
  
  public boolean commitGsiChunkFromAshmem(long paramLong) throws RemoteException {
    return false;
  }
  
  public boolean commitGsiChunkFromStream(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong) throws RemoteException {
    return false;
  }
  
  public int createPartition(String paramString, long paramLong, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public boolean disableGsi() throws RemoteException {
    return false;
  }
  
  public String dumpDeviceMapperDevices() throws RemoteException {
    return null;
  }
  
  public int enableGsi(boolean paramBoolean, String paramString) throws RemoteException {
    return 0;
  }
  
  public void enableGsiAsync(boolean paramBoolean, String paramString, IGsiServiceCallback paramIGsiServiceCallback) throws RemoteException {}
  
  public String getActiveDsuSlot() throws RemoteException {
    return null;
  }
  
  public int getAvbPublicKey(AvbPublicKey paramAvbPublicKey) throws RemoteException {
    return 0;
  }
  
  public GsiProgress getInstallProgress() throws RemoteException {
    return null;
  }
  
  public List<String> getInstalledDsuSlots() throws RemoteException {
    return null;
  }
  
  public String getInstalledGsiImageDir() throws RemoteException {
    return null;
  }
  
  public boolean isGsiEnabled() throws RemoteException {
    return false;
  }
  
  public boolean isGsiInstallInProgress() throws RemoteException {
    return false;
  }
  
  public boolean isGsiInstalled() throws RemoteException {
    return false;
  }
  
  public boolean isGsiRunning() throws RemoteException {
    return false;
  }
  
  public IImageService openImageService(String paramString) throws RemoteException {
    return null;
  }
  
  public int openInstall(String paramString) throws RemoteException {
    return 0;
  }
  
  public boolean removeGsi() throws RemoteException {
    return false;
  }
  
  public void removeGsiAsync(IGsiServiceCallback paramIGsiServiceCallback) throws RemoteException {}
  
  public boolean setGsiAshmem(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong) throws RemoteException {
    return false;
  }
  
  public int zeroPartition(String paramString) throws RemoteException {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IGsiService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */