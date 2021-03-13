package android.gsi;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IImageService {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean backingImageExists(String paramString) throws RemoteException {
    return false;
  }
  
  public void createBackingImage(String paramString, long paramLong, int paramInt, IProgressCallback paramIProgressCallback) throws RemoteException {}
  
  public void deleteBackingImage(String paramString) throws RemoteException {}
  
  public List<String> getAllBackingImages() throws RemoteException {
    return null;
  }
  
  public int getAvbPublicKey(String paramString, AvbPublicKey paramAvbPublicKey) throws RemoteException {
    return 0;
  }
  
  public String getMappedImageDevice(String paramString) throws RemoteException {
    return null;
  }
  
  public boolean isImageMapped(String paramString) throws RemoteException {
    return false;
  }
  
  public void mapImageDevice(String paramString, int paramInt, MappedImage paramMappedImage) throws RemoteException {}
  
  public void removeAllImages() throws RemoteException {}
  
  public void removeDisabledImages() throws RemoteException {}
  
  public void unmapImageDevice(String paramString) throws RemoteException {}
  
  public void zeroFillNewImage(String paramString, long paramLong) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IImageService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */