package android.hardware;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

public final class ConsumerIrManager {
  private static final String TAG = "ConsumerIr";
  
  private final String mPackageName;
  
  private final IConsumerIrService mService;
  
  public ConsumerIrManager(Context paramContext) throws ServiceManager.ServiceNotFoundException {
    this.mPackageName = paramContext.getPackageName();
    this.mService = IConsumerIrService.Stub.asInterface(ServiceManager.getServiceOrThrow("consumer_ir"));
  }
  
  public CarrierFrequencyRange[] getCarrierFrequencies() {
    IConsumerIrService iConsumerIrService = this.mService;
    if (iConsumerIrService == null) {
      Log.w("ConsumerIr", "no consumer ir service.");
      return null;
    } 
    try {
      int[] arrayOfInt = iConsumerIrService.getCarrierFrequencies();
      if (arrayOfInt.length % 2 != 0) {
        Log.w("ConsumerIr", "consumer ir service returned an uneven number of frequencies.");
        return null;
      } 
      CarrierFrequencyRange[] arrayOfCarrierFrequencyRange = new CarrierFrequencyRange[arrayOfInt.length / 2];
      for (byte b = 0; b < arrayOfInt.length; b += 2)
        arrayOfCarrierFrequencyRange[b / 2] = new CarrierFrequencyRange(arrayOfInt[b], arrayOfInt[b + 1]); 
      return arrayOfCarrierFrequencyRange;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean hasIrEmitter() {
    IConsumerIrService iConsumerIrService = this.mService;
    if (iConsumerIrService == null) {
      Log.w("ConsumerIr", "no consumer ir service.");
      return false;
    } 
    try {
      return iConsumerIrService.hasIrEmitter();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void transmit(int paramInt, int[] paramArrayOfint) {
    IConsumerIrService iConsumerIrService = this.mService;
    if (iConsumerIrService == null) {
      Log.w("ConsumerIr", "failed to transmit; no consumer ir service.");
      return;
    } 
    try {
      iConsumerIrService.transmit(this.mPackageName, paramInt, paramArrayOfint);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public final class CarrierFrequencyRange {
    private final int mMaxFrequency;
    
    private final int mMinFrequency;
    
    public CarrierFrequencyRange(int param1Int1, int param1Int2) {
      this.mMinFrequency = param1Int1;
      this.mMaxFrequency = param1Int2;
    }
    
    public int getMaxFrequency() {
      return this.mMaxFrequency;
    }
    
    public int getMinFrequency() {
      return this.mMinFrequency;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ConsumerIrManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */