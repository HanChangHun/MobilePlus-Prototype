package android.telephony.data;

import android.net.LinkProperties;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IDataService {
  public IBinder asBinder() {
    return null;
  }
  
  public void createDataServiceProvider(int paramInt) throws RemoteException {}
  
  public void deactivateDataCall(int paramInt1, int paramInt2, int paramInt3, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {}
  
  public void registerForDataCallListChanged(int paramInt, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {}
  
  public void removeDataServiceProvider(int paramInt) throws RemoteException {}
  
  public void requestDataCallList(int paramInt, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {}
  
  public void setDataProfile(int paramInt, List<DataProfile> paramList, boolean paramBoolean, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {}
  
  public void setInitialAttachApn(int paramInt, DataProfile paramDataProfile, boolean paramBoolean, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {}
  
  public void setupDataCall(int paramInt1, int paramInt2, DataProfile paramDataProfile, boolean paramBoolean1, boolean paramBoolean2, int paramInt3, LinkProperties paramLinkProperties, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {}
  
  public void unregisterForDataCallListChanged(int paramInt, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IDataService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */