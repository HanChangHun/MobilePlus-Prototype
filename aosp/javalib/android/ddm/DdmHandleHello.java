package android.ddm;

import android.os.Debug;
import android.os.Process;
import android.os.UserHandle;
import dalvik.system.VMRuntime;
import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.Chunk;
import org.apache.harmony.dalvik.ddmc.ChunkHandler;
import org.apache.harmony.dalvik.ddmc.DdmServer;

public class DdmHandleHello extends ChunkHandler {
  public static final int CHUNK_FEAT;
  
  public static final int CHUNK_HELO = type("HELO");
  
  public static final int CHUNK_WAIT = type("WAIT");
  
  private static final int CLIENT_PROTOCOL_VERSION = 1;
  
  private static final String[] FRAMEWORK_FEATURES;
  
  private static DdmHandleHello mInstance;
  
  static {
    CHUNK_FEAT = type("FEAT");
    mInstance = new DdmHandleHello();
    FRAMEWORK_FEATURES = new String[] { "opengl-tracing", "view-hierarchy" };
  }
  
  private Chunk handleFEAT(Chunk paramChunk) {
    String[] arrayOfString = Debug.getVmFeatureList();
    int i = (arrayOfString.length + FRAMEWORK_FEATURES.length) * 4 + 4;
    int j;
    for (j = arrayOfString.length - 1; j >= 0; j--)
      i += arrayOfString[j].length() * 2; 
    for (j = FRAMEWORK_FEATURES.length - 1; j >= 0; j--)
      i += FRAMEWORK_FEATURES[j].length() * 2; 
    ByteBuffer byteBuffer = ByteBuffer.allocate(i);
    byteBuffer.order(ChunkHandler.CHUNK_ORDER);
    byteBuffer.putInt(arrayOfString.length + FRAMEWORK_FEATURES.length);
    for (i = arrayOfString.length - 1; i >= 0; i--) {
      byteBuffer.putInt(arrayOfString[i].length());
      putString(byteBuffer, arrayOfString[i]);
    } 
    for (i = FRAMEWORK_FEATURES.length - 1; i >= 0; i--) {
      byteBuffer.putInt(FRAMEWORK_FEATURES[i].length());
      putString(byteBuffer, FRAMEWORK_FEATURES[i]);
    } 
    return new Chunk(CHUNK_FEAT, byteBuffer);
  }
  
  private Chunk handleHELO(Chunk paramChunk) {
    wrapChunk(paramChunk).getInt();
    String str2 = System.getProperty("java.vm.name", "?");
    String str3 = System.getProperty("java.vm.version", "?");
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(str2);
    stringBuilder1.append(" v");
    stringBuilder1.append(str3);
    String str5 = stringBuilder1.toString();
    DdmHandleAppName.Names names = DdmHandleAppName.getNames();
    String str4 = names.getAppName();
    String str6 = names.getPkgName();
    VMRuntime vMRuntime = VMRuntime.getRuntime();
    if (vMRuntime.is64Bit()) {
      str1 = "64-bit";
    } else {
      str1 = "32-bit";
    } 
    String str7 = vMRuntime.vmInstructionSet();
    str3 = str1;
    if (str7 != null) {
      str3 = str1;
      if (str7.length() > 0) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append(" (");
        stringBuilder.append(str7);
        stringBuilder.append(")");
        str3 = stringBuilder.toString();
      } 
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("CheckJNI=");
    if (vMRuntime.isCheckJniEnabled()) {
      str1 = "true";
    } else {
      str1 = "false";
    } 
    stringBuilder2.append(str1);
    String str1 = stringBuilder2.toString();
    boolean bool = vMRuntime.isNativeDebuggable();
    ByteBuffer byteBuffer = ByteBuffer.allocate(str5.length() * 2 + 32 + str4.length() * 2 + str3.length() * 2 + str1.length() * 2 + 1 + str6.length() * 2);
    byteBuffer.order(ChunkHandler.CHUNK_ORDER);
    byteBuffer.putInt(1);
    byteBuffer.putInt(Process.myPid());
    byteBuffer.putInt(str5.length());
    byteBuffer.putInt(str4.length());
    putString(byteBuffer, str5);
    putString(byteBuffer, str4);
    byteBuffer.putInt(UserHandle.myUserId());
    byteBuffer.putInt(str3.length());
    putString(byteBuffer, str3);
    byteBuffer.putInt(str1.length());
    putString(byteBuffer, str1);
    byteBuffer.put((byte)bool);
    byteBuffer.putInt(str6.length());
    putString(byteBuffer, str6);
    Chunk chunk = new Chunk(CHUNK_HELO, byteBuffer);
    if (Debug.waitingForDebugger())
      sendWAIT(0); 
    return chunk;
  }
  
  public static void register() {
    DdmServer.registerHandler(CHUNK_HELO, mInstance);
    DdmServer.registerHandler(CHUNK_FEAT, mInstance);
  }
  
  public static void sendWAIT(int paramInt) {
    byte b = (byte)paramInt;
    DdmServer.sendChunk(new Chunk(CHUNK_WAIT, new byte[] { b }, 0, 1));
  }
  
  public void connected() {}
  
  public void disconnected() {}
  
  public Chunk handleChunk(Chunk paramChunk) {
    int i = paramChunk.type;
    if (i == CHUNK_HELO)
      return handleHELO(paramChunk); 
    if (i == CHUNK_FEAT)
      return handleFEAT(paramChunk); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown packet ");
    stringBuilder.append(ChunkHandler.name(i));
    throw new RuntimeException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/ddm/DdmHandleHello.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */