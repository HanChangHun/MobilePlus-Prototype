package android.hardware.location;

import android.util.Log;
import java.util.List;

class null extends IContextHubTransactionCallback.Stub {
  public void onQueryResponse(int paramInt, List<NanoAppState> paramList) {
    Log.e("ContextHubManager", "Received a query callback on a non-query request");
    transaction.setResponse(new ContextHubTransaction.Response(7, null));
  }
  
  public void onTransactionComplete(int paramInt) {
    transaction.setResponse(new ContextHubTransaction.Response(paramInt, null));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */