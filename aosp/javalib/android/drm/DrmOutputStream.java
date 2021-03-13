package android.drm;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownServiceException;
import libcore.io.IoBridge;
import libcore.io.Streams;
import libcore.util.ArrayUtils;

@Deprecated
public class DrmOutputStream extends OutputStream {
  private static final String TAG = "DrmOutputStream";
  
  private final DrmManagerClient mClient;
  
  private final FileDescriptor mFd;
  
  private final ParcelFileDescriptor mPfd;
  
  private int mSessionId = -1;
  
  public DrmOutputStream(DrmManagerClient paramDrmManagerClient, ParcelFileDescriptor paramParcelFileDescriptor, String paramString) throws IOException {
    this.mClient = paramDrmManagerClient;
    this.mPfd = paramParcelFileDescriptor;
    this.mFd = paramParcelFileDescriptor.getFileDescriptor();
    int i = this.mClient.openConvertSession(paramString);
    this.mSessionId = i;
    if (i != -1)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Failed to open DRM session for ");
    stringBuilder.append(paramString);
    throw new UnknownServiceException(stringBuilder.toString());
  }
  
  public void close() throws IOException {
    if (this.mSessionId == -1)
      Log.w("DrmOutputStream", "Closing stream without finishing"); 
    this.mPfd.close();
  }
  
  public void finish() throws IOException {
    DrmConvertedStatus drmConvertedStatus = this.mClient.closeConvertSession(this.mSessionId);
    if (drmConvertedStatus.statusCode == 1) {
      try {
        Os.lseek(this.mFd, drmConvertedStatus.offset, OsConstants.SEEK_SET);
      } catch (ErrnoException errnoException) {
        errnoException.rethrowAsIOException();
      } 
      IoBridge.write(this.mFd, drmConvertedStatus.convertedData, 0, drmConvertedStatus.convertedData.length);
      this.mSessionId = -1;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unexpected DRM status: ");
    stringBuilder.append(drmConvertedStatus.statusCode);
    throw new IOException(stringBuilder.toString());
  }
  
  public void write(int paramInt) throws IOException {
    Streams.writeSingleByte(this, paramInt);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    ArrayUtils.throwsIfOutOfBounds(paramArrayOfbyte.length, paramInt1, paramInt2);
    if (paramInt2 != paramArrayOfbyte.length) {
      byte[] arrayOfByte = new byte[paramInt2];
      System.arraycopy(paramArrayOfbyte, paramInt1, arrayOfByte, 0, paramInt2);
      paramArrayOfbyte = arrayOfByte;
    } 
    DrmConvertedStatus drmConvertedStatus = this.mClient.convertData(this.mSessionId, paramArrayOfbyte);
    if (drmConvertedStatus.statusCode == 1) {
      IoBridge.write(this.mFd, drmConvertedStatus.convertedData, 0, drmConvertedStatus.convertedData.length);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unexpected DRM status: ");
    stringBuilder.append(drmConvertedStatus.statusCode);
    throw new IOException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */