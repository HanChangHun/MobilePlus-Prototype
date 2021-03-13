package android.hardware;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.io.IOException;

public class SerialManager {
  private static final String TAG = "SerialManager";
  
  private final Context mContext;
  
  private final ISerialManager mService;
  
  public SerialManager(Context paramContext, ISerialManager paramISerialManager) {
    this.mContext = paramContext;
    this.mService = paramISerialManager;
  }
  
  public String[] getSerialPorts() {
    try {
      return this.mService.getSerialPorts();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public SerialPort openSerialPort(String paramString, int paramInt) throws IOException {
    try {
      ParcelFileDescriptor parcelFileDescriptor = this.mService.openSerialPort(paramString);
      if (parcelFileDescriptor != null) {
        SerialPort serialPort = new SerialPort();
        this(paramString);
        serialPort.open(parcelFileDescriptor, paramInt);
        return serialPort;
      } 
      IOException iOException = new IOException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Could not open serial port ");
      stringBuilder.append(paramString);
      this(stringBuilder.toString());
      throw iOException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SerialManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */