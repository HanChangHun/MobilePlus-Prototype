package android.debug;

import java.io.File;

public abstract class AdbManagerInternal {
  public abstract File getAdbKeysFile();
  
  public abstract File getAdbTempKeysFile();
  
  public abstract boolean isAdbEnabled(byte paramByte);
  
  public abstract void registerTransport(IAdbTransport paramIAdbTransport);
  
  public abstract void startAdbdForTransport(byte paramByte);
  
  public abstract void stopAdbdForTransport(byte paramByte);
  
  public abstract void unregisterTransport(IAdbTransport paramIAdbTransport);
}


/* Location:              /home/chun/Desktop/temp/!/android/debug/AdbManagerInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */