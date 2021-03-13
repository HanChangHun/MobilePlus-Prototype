package android.content.integrity;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IAppIntegrityManager {
  public IBinder asBinder() {
    return null;
  }
  
  public String getCurrentRuleSetProvider() throws RemoteException {
    return null;
  }
  
  public String getCurrentRuleSetVersion() throws RemoteException {
    return null;
  }
  
  public ParceledListSlice<Rule> getCurrentRules() throws RemoteException {
    return null;
  }
  
  public List<String> getWhitelistedRuleProviders() throws RemoteException {
    return null;
  }
  
  public void updateRuleSet(String paramString, ParceledListSlice<Rule> paramParceledListSlice, IntentSender paramIntentSender) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/IAppIntegrityManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */