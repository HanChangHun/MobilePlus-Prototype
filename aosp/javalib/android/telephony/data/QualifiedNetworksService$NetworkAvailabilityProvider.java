package android.telephony.data;

import android.os.RemoteException;
import android.util.SparseArray;
import java.util.List;
import java.util.function.ToIntFunction;

public abstract class NetworkAvailabilityProvider implements AutoCloseable {
  private IQualifiedNetworksServiceCallback mCallback;
  
  private SparseArray<int[]> mQualifiedNetworkTypesList = new SparseArray();
  
  private final int mSlotIndex;
  
  public NetworkAvailabilityProvider(int paramInt) {
    this.mSlotIndex = paramInt;
  }
  
  private void onUpdateQualifiedNetworkTypes(int paramInt, int[] paramArrayOfint) {
    this.mQualifiedNetworkTypesList.put(paramInt, paramArrayOfint);
    IQualifiedNetworksServiceCallback iQualifiedNetworksServiceCallback = this.mCallback;
    if (iQualifiedNetworksServiceCallback != null)
      try {
        iQualifiedNetworksServiceCallback.onQualifiedNetworkTypesChanged(paramInt, paramArrayOfint);
      } catch (RemoteException remoteException) {
        QualifiedNetworksService qualifiedNetworksService = QualifiedNetworksService.this;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to call onQualifiedNetworksChanged. ");
        stringBuilder.append(remoteException);
        QualifiedNetworksService.access$100(qualifiedNetworksService, stringBuilder.toString());
      }  
  }
  
  private void registerForQualifiedNetworkTypesChanged(IQualifiedNetworksServiceCallback paramIQualifiedNetworksServiceCallback) {
    this.mCallback = paramIQualifiedNetworksServiceCallback;
    if (paramIQualifiedNetworksServiceCallback != null)
      for (byte b = 0; b < this.mQualifiedNetworkTypesList.size(); b++) {
        try {
          this.mCallback.onQualifiedNetworkTypesChanged(this.mQualifiedNetworkTypesList.keyAt(b), (int[])this.mQualifiedNetworkTypesList.valueAt(b));
        } catch (RemoteException remoteException) {
          QualifiedNetworksService qualifiedNetworksService = QualifiedNetworksService.this;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Failed to call onQualifiedNetworksChanged. ");
          stringBuilder.append(remoteException);
          QualifiedNetworksService.access$100(qualifiedNetworksService, stringBuilder.toString());
        } 
      }  
  }
  
  public abstract void close();
  
  public final int getSlotIndex() {
    return this.mSlotIndex;
  }
  
  public final void updateQualifiedNetworkTypes(int paramInt, List<Integer> paramList) {
    int[] arrayOfInt = paramList.stream().mapToInt((ToIntFunction)_$$Lambda$QualifiedNetworksService$NetworkAvailabilityProvider$sNPqwkqArvqymBmHYmxAc4rF5Es.INSTANCE).toArray();
    QualifiedNetworksService.access$200(QualifiedNetworksService.this).obtainMessage(4, this.mSlotIndex, paramInt, arrayOfInt).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/QualifiedNetworksService$NetworkAvailabilityProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */