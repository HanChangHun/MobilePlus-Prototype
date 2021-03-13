package android.telephony.data;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IDataServiceCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onDataCallListChanged(List<DataCallResponse> paramList) throws RemoteException {}
  
  public void onDeactivateDataCallComplete(int paramInt) throws RemoteException {}
  
  public void onRequestDataCallListComplete(int paramInt, List<DataCallResponse> paramList) throws RemoteException {}
  
  public void onSetDataProfileComplete(int paramInt) throws RemoteException {}
  
  public void onSetInitialAttachApnComplete(int paramInt) throws RemoteException {}
  
  public void onSetupDataCallComplete(int paramInt, DataCallResponse paramDataCallResponse) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IDataServiceCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */