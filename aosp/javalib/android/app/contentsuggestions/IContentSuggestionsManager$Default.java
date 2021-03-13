package android.app.contentsuggestions;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.internal.os.IResultReceiver;

public class Default implements IContentSuggestionsManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void classifyContentSelections(int paramInt, ClassificationsRequest paramClassificationsRequest, IClassificationsCallback paramIClassificationsCallback) throws RemoteException {}
  
  public void isEnabled(int paramInt, IResultReceiver paramIResultReceiver) throws RemoteException {}
  
  public void notifyInteraction(int paramInt, String paramString, Bundle paramBundle) throws RemoteException {}
  
  public void provideContextBitmap(int paramInt, Bitmap paramBitmap, Bundle paramBundle) throws RemoteException {}
  
  public void provideContextImage(int paramInt1, int paramInt2, Bundle paramBundle) throws RemoteException {}
  
  public void suggestContentSelections(int paramInt, SelectionsRequest paramSelectionsRequest, ISelectionsCallback paramISelectionsCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/IContentSuggestionsManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */