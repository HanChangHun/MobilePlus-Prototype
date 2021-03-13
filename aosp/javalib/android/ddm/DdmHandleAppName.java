package android.ddm;

import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.Chunk;
import org.apache.harmony.dalvik.ddmc.ChunkHandler;
import org.apache.harmony.dalvik.ddmc.DdmServer;

public class DdmHandleAppName extends ChunkHandler {
  public static final int CHUNK_APNM = type("APNM");
  
  private static DdmHandleAppName mInstance;
  
  private static volatile Names sNames = new Names("", "");
  
  static {
    mInstance = new DdmHandleAppName();
  }
  
  public static Names getNames() {
    return sNames;
  }
  
  public static void register() {}
  
  private static void sendAPNM(String paramString1, String paramString2, int paramInt) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(paramString1.length() * 2 + 4 + 4 + 4 + paramString2.length() * 2);
    byteBuffer.order(ChunkHandler.CHUNK_ORDER);
    byteBuffer.putInt(paramString1.length());
    putString(byteBuffer, paramString1);
    byteBuffer.putInt(paramInt);
    byteBuffer.putInt(paramString2.length());
    putString(byteBuffer, paramString2);
    DdmServer.sendChunk(new Chunk(CHUNK_APNM, byteBuffer));
  }
  
  public static void setAppName(String paramString, int paramInt) {
    setAppName(paramString, paramString, paramInt);
  }
  
  public static void setAppName(String paramString1, String paramString2, int paramInt) {
    if (paramString1 == null || paramString1.isEmpty() || paramString2 == null || paramString2.isEmpty())
      return; 
    sNames = new Names(paramString1, paramString2);
    sendAPNM(paramString1, paramString2, paramInt);
  }
  
  public void connected() {}
  
  public void disconnected() {}
  
  public Chunk handleChunk(Chunk paramChunk) {
    return null;
  }
  
  static final class Names {
    private final String mAppName;
    
    private final String mPkgName;
    
    private Names(String param1String1, String param1String2) {
      this.mAppName = param1String1;
      this.mPkgName = param1String2;
    }
    
    public String getAppName() {
      return this.mAppName;
    }
    
    public String getPkgName() {
      return this.mPkgName;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/ddm/DdmHandleAppName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */