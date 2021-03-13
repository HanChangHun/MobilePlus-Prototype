package android.ddm;

import android.os.Debug;
import android.util.Log;
import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.Chunk;
import org.apache.harmony.dalvik.ddmc.ChunkHandler;
import org.apache.harmony.dalvik.ddmc.DdmServer;

public class DdmHandleProfiling extends ChunkHandler {
  public static final int CHUNK_MPRE;
  
  public static final int CHUNK_MPRQ;
  
  public static final int CHUNK_MPRS = type("MPRS");
  
  public static final int CHUNK_MPSE;
  
  public static final int CHUNK_MPSS;
  
  public static final int CHUNK_SPSE;
  
  public static final int CHUNK_SPSS;
  
  private static final boolean DEBUG = false;
  
  private static DdmHandleProfiling mInstance;
  
  static {
    CHUNK_MPRE = type("MPRE");
    CHUNK_MPSS = type("MPSS");
    CHUNK_MPSE = type("MPSE");
    CHUNK_MPRQ = type("MPRQ");
    CHUNK_SPSS = type("SPSS");
    CHUNK_SPSE = type("SPSE");
    mInstance = new DdmHandleProfiling();
  }
  
  private Chunk handleMPRE(Chunk paramChunk) {
    boolean bool;
    try {
      Debug.stopMethodTracing();
      bool = false;
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Method profiling end failed: ");
      stringBuilder.append(runtimeException.getMessage());
      Log.w("ddm-heap", stringBuilder.toString());
      bool = true;
    } 
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte)bool;
    return new Chunk(CHUNK_MPRE, arrayOfByte, 0, arrayOfByte.length);
  }
  
  private Chunk handleMPRQ(Chunk paramChunk) {
    int i = Debug.getMethodTracingMode();
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte)(byte)i;
    return new Chunk(CHUNK_MPRQ, arrayOfByte, 0, arrayOfByte.length);
  }
  
  private Chunk handleMPRS(Chunk paramChunk) {
    ByteBuffer byteBuffer = wrapChunk(paramChunk);
    int i = byteBuffer.getInt();
    int j = byteBuffer.getInt();
    String str = getString(byteBuffer, byteBuffer.getInt());
    try {
      Debug.startMethodTracing(str, i, j);
      return null;
    } catch (RuntimeException runtimeException) {
      return createFailChunk(1, runtimeException.getMessage());
    } 
  }
  
  private Chunk handleMPSEOrSPSE(Chunk paramChunk, String paramString) {
    try {
      Debug.stopMethodTracing();
      return null;
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(" prof stream end failed: ");
      stringBuilder.append(runtimeException.getMessage());
      Log.w("ddm-heap", stringBuilder.toString());
      return createFailChunk(1, runtimeException.getMessage());
    } 
  }
  
  private Chunk handleMPSS(Chunk paramChunk) {
    ByteBuffer byteBuffer = wrapChunk(paramChunk);
    int i = byteBuffer.getInt();
    int j = byteBuffer.getInt();
    try {
      Debug.startMethodTracingDdms(i, j, false, 0);
      return null;
    } catch (RuntimeException runtimeException) {
      return createFailChunk(1, runtimeException.getMessage());
    } 
  }
  
  private Chunk handleSPSS(Chunk paramChunk) {
    ByteBuffer byteBuffer = wrapChunk(paramChunk);
    int i = byteBuffer.getInt();
    int j = byteBuffer.getInt();
    int k = byteBuffer.getInt();
    try {
      Debug.startMethodTracingDdms(i, j, true, k);
      return null;
    } catch (RuntimeException runtimeException) {
      return createFailChunk(1, runtimeException.getMessage());
    } 
  }
  
  public static void register() {
    DdmServer.registerHandler(CHUNK_MPRS, mInstance);
    DdmServer.registerHandler(CHUNK_MPRE, mInstance);
    DdmServer.registerHandler(CHUNK_MPSS, mInstance);
    DdmServer.registerHandler(CHUNK_MPSE, mInstance);
    DdmServer.registerHandler(CHUNK_MPRQ, mInstance);
    DdmServer.registerHandler(CHUNK_SPSS, mInstance);
    DdmServer.registerHandler(CHUNK_SPSE, mInstance);
  }
  
  public void connected() {}
  
  public void disconnected() {}
  
  public Chunk handleChunk(Chunk paramChunk) {
    int i = paramChunk.type;
    if (i == CHUNK_MPRS)
      return handleMPRS(paramChunk); 
    if (i == CHUNK_MPRE)
      return handleMPRE(paramChunk); 
    if (i == CHUNK_MPSS)
      return handleMPSS(paramChunk); 
    if (i == CHUNK_MPSE)
      return handleMPSEOrSPSE(paramChunk, "Method"); 
    if (i == CHUNK_MPRQ)
      return handleMPRQ(paramChunk); 
    if (i == CHUNK_SPSS)
      return handleSPSS(paramChunk); 
    if (i == CHUNK_SPSE)
      return handleMPSEOrSPSE(paramChunk, "Sample"); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown packet ");
    stringBuilder.append(ChunkHandler.name(i));
    throw new RuntimeException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/ddm/DdmHandleProfiling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */