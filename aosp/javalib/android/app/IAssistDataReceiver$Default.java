package android.app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IAssistDataReceiver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onHandleAssistData(Bundle paramBundle) throws RemoteException {}
  
  public void onHandleAssistScreenshot(Bitmap paramBitmap) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAssistDataReceiver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */