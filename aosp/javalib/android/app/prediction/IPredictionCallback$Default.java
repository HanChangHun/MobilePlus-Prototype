package android.app.prediction;

import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPredictionCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onResult(ParceledListSlice paramParceledListSlice) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/IPredictionCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */