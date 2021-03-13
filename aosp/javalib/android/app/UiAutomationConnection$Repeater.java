package android.app;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import libcore.io.IoUtils;

public class Repeater implements Runnable {
  private final InputStream readFrom;
  
  private final OutputStream writeTo;
  
  public Repeater(InputStream paramInputStream, OutputStream paramOutputStream) {
    this.readFrom = paramInputStream;
    this.writeTo = paramOutputStream;
  }
  
  public void run() {
    try {
      byte[] arrayOfByte = new byte[8192];
      while (true) {
        int i = this.readFrom.read(arrayOfByte);
        if (i < 0)
          break; 
        this.writeTo.write(arrayOfByte, 0, i);
        this.writeTo.flush();
      } 
    } catch (IOException iOException) {
      Log.w("UiAutomationConnection", "Error while reading/writing to streams");
    } finally {
      Exception exception;
    } 
    IoUtils.closeQuietly(this.readFrom);
    IoUtils.closeQuietly(this.writeTo);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/UiAutomationConnection$Repeater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */