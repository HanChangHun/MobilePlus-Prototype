package android.bluetooth;

import android.util.Pair;
import java.util.concurrent.Executor;

class null extends IBluetoothMetadataListener.Stub {
  public void onMetadataChanged(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte) {
    synchronized (BluetoothAdapter.access$000()) {
      if (BluetoothAdapter.access$000().containsKey(paramBluetoothDevice))
        for (Pair pair : BluetoothAdapter.access$000().get(paramBluetoothDevice)) {
          BluetoothAdapter.OnMetadataChangedListener onMetadataChangedListener = (BluetoothAdapter.OnMetadataChangedListener)pair.first;
          Executor executor = (Executor)pair.second;
          _$$Lambda$BluetoothAdapter$1$I3p3FVKkxuogQU8eug7PAKoZKZc _$$Lambda$BluetoothAdapter$1$I3p3FVKkxuogQU8eug7PAKoZKZc = new _$$Lambda$BluetoothAdapter$1$I3p3FVKkxuogQU8eug7PAKoZKZc();
          this(onMetadataChangedListener, paramBluetoothDevice, paramInt, paramArrayOfbyte);
          executor.execute(_$$Lambda$BluetoothAdapter$1$I3p3FVKkxuogQU8eug7PAKoZKZc);
        }  
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAdapter$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */