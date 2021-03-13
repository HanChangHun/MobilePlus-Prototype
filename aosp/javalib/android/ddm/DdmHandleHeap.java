package android.ddm;

import android.os.Debug;
import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.Chunk;
import org.apache.harmony.dalvik.ddmc.ChunkHandler;
import org.apache.harmony.dalvik.ddmc.DdmServer;
import org.apache.harmony.dalvik.ddmc.DdmVmInternal;

public class DdmHandleHeap extends ChunkHandler {
  public static final int CHUNK_HPDS;
  
  public static final int CHUNK_HPDU;
  
  public static final int CHUNK_HPGC;
  
  public static final int CHUNK_HPIF = type("HPIF");
  
  public static final int CHUNK_HPSG = type("HPSG");
  
  public static final int CHUNK_NHSG;
  
  public static final int CHUNK_REAE;
  
  public static final int CHUNK_REAL;
  
  public static final int CHUNK_REAQ;
  
  private static DdmHandleHeap mInstance;
  
  static {
    CHUNK_HPDU = type("HPDU");
    CHUNK_HPDS = type("HPDS");
    CHUNK_NHSG = type("NHSG");
    CHUNK_HPGC = type("HPGC");
    CHUNK_REAE = type("REAE");
    CHUNK_REAQ = type("REAQ");
    CHUNK_REAL = type("REAL");
    mInstance = new DdmHandleHeap();
  }
  
  private Chunk handleHPDS(Chunk paramChunk) {
    String str;
    wrapChunk(paramChunk);
    paramChunk = null;
    try {
      Debug.dumpHprofDataDdms();
    } catch (UnsupportedOperationException unsupportedOperationException) {
      str = "hprof dumps not supported in this VM";
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Exception: ");
      stringBuilder.append(runtimeException.getMessage());
      str = stringBuilder.toString();
    } 
    if (str != null) {
      Log.w("ddm-heap", str);
      return createFailChunk(1, str);
    } 
    return null;
  }
  
  private Chunk handleHPDU(Chunk paramChunk) {
    byte b;
    ByteBuffer byteBuffer = wrapChunk(paramChunk);
    String str = getString(byteBuffer, byteBuffer.getInt());
    try {
      Debug.dumpHprofData(str);
      b = 0;
    } catch (UnsupportedOperationException unsupportedOperationException) {
      Log.w("ddm-heap", "hprof dumps not supported in this VM");
      b = -1;
    } catch (IOException iOException) {
      b = -1;
    } catch (RuntimeException runtimeException) {
      b = -1;
    } 
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte)b;
    return new Chunk(CHUNK_HPDU, arrayOfByte, 0, arrayOfByte.length);
  }
  
  private Chunk handleHPGC(Chunk paramChunk) {
    Runtime.getRuntime().gc();
    return null;
  }
  
  private Chunk handleHPIF(Chunk paramChunk) {
    return !DdmVmInternal.heapInfoNotify(wrapChunk(paramChunk).get()) ? createFailChunk(1, "Unsupported HPIF what") : null;
  }
  
  private Chunk handleHPSGNHSG(Chunk paramChunk, boolean paramBoolean) {
    ByteBuffer byteBuffer = wrapChunk(paramChunk);
    return !DdmVmInternal.heapSegmentNotify(byteBuffer.get(), byteBuffer.get(), paramBoolean) ? createFailChunk(1, "Unsupported HPSG what/when") : null;
  }
  
  private Chunk handleREAE(Chunk paramChunk) {
    boolean bool;
    if (wrapChunk(paramChunk).get() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    DdmVmInternal.enableRecentAllocations(bool);
    return null;
  }
  
  private Chunk handleREAL(Chunk paramChunk) {
    byte[] arrayOfByte = DdmVmInternal.getRecentAllocations();
    return new Chunk(CHUNK_REAL, arrayOfByte, 0, arrayOfByte.length);
  }
  
  private Chunk handleREAQ(Chunk paramChunk) {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte)DdmVmInternal.getRecentAllocationStatus();
    return new Chunk(CHUNK_REAQ, arrayOfByte, 0, arrayOfByte.length);
  }
  
  public static void register() {
    DdmServer.registerHandler(CHUNK_HPIF, mInstance);
    DdmServer.registerHandler(CHUNK_HPSG, mInstance);
    DdmServer.registerHandler(CHUNK_HPDU, mInstance);
    DdmServer.registerHandler(CHUNK_HPDS, mInstance);
    DdmServer.registerHandler(CHUNK_NHSG, mInstance);
    DdmServer.registerHandler(CHUNK_HPGC, mInstance);
    DdmServer.registerHandler(CHUNK_REAE, mInstance);
    DdmServer.registerHandler(CHUNK_REAQ, mInstance);
    DdmServer.registerHandler(CHUNK_REAL, mInstance);
  }
  
  public void connected() {}
  
  public void disconnected() {}
  
  public Chunk handleChunk(Chunk paramChunk) {
    int i = paramChunk.type;
    if (i == CHUNK_HPIF)
      return handleHPIF(paramChunk); 
    if (i == CHUNK_HPSG)
      return handleHPSGNHSG(paramChunk, false); 
    if (i == CHUNK_HPDU)
      return handleHPDU(paramChunk); 
    if (i == CHUNK_HPDS)
      return handleHPDS(paramChunk); 
    if (i == CHUNK_NHSG)
      return handleHPSGNHSG(paramChunk, true); 
    if (i == CHUNK_HPGC)
      return handleHPGC(paramChunk); 
    if (i == CHUNK_REAE)
      return handleREAE(paramChunk); 
    if (i == CHUNK_REAQ)
      return handleREAQ(paramChunk); 
    if (i == CHUNK_REAL)
      return handleREAL(paramChunk); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown packet ");
    stringBuilder.append(ChunkHandler.name(i));
    throw new RuntimeException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/ddm/DdmHandleHeap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */