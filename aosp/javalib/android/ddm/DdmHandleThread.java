package android.ddm;

import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.Chunk;
import org.apache.harmony.dalvik.ddmc.ChunkHandler;
import org.apache.harmony.dalvik.ddmc.DdmServer;
import org.apache.harmony.dalvik.ddmc.DdmVmInternal;

public class DdmHandleThread extends ChunkHandler {
  public static final int CHUNK_STKL;
  
  public static final int CHUNK_THCR;
  
  public static final int CHUNK_THDE;
  
  public static final int CHUNK_THEN = type("THEN");
  
  public static final int CHUNK_THST;
  
  private static DdmHandleThread mInstance;
  
  static {
    CHUNK_THCR = type("THCR");
    CHUNK_THDE = type("THDE");
    CHUNK_THST = type("THST");
    CHUNK_STKL = type("STKL");
    mInstance = new DdmHandleThread();
  }
  
  private Chunk createStackChunk(StackTraceElement[] paramArrayOfStackTraceElement, int paramInt) {
    int i = 0 + 4 + 4 + 4;
    int j = paramArrayOfStackTraceElement.length;
    int k;
    for (k = 0; k < j; k++) {
      StackTraceElement stackTraceElement = paramArrayOfStackTraceElement[k];
      int m = i + stackTraceElement.getClassName().length() * 2 + 4 + stackTraceElement.getMethodName().length() * 2 + 4 + 4;
      i = m;
      if (stackTraceElement.getFileName() != null)
        i = m + stackTraceElement.getFileName().length() * 2; 
      i += 4;
    } 
    ByteBuffer byteBuffer = ByteBuffer.allocate(i);
    byteBuffer.putInt(0);
    byteBuffer.putInt(paramInt);
    byteBuffer.putInt(paramArrayOfStackTraceElement.length);
    k = paramArrayOfStackTraceElement.length;
    for (paramInt = 0; paramInt < k; paramInt++) {
      StackTraceElement stackTraceElement = paramArrayOfStackTraceElement[paramInt];
      byteBuffer.putInt(stackTraceElement.getClassName().length());
      putString(byteBuffer, stackTraceElement.getClassName());
      byteBuffer.putInt(stackTraceElement.getMethodName().length());
      putString(byteBuffer, stackTraceElement.getMethodName());
      if (stackTraceElement.getFileName() != null) {
        byteBuffer.putInt(stackTraceElement.getFileName().length());
        putString(byteBuffer, stackTraceElement.getFileName());
      } else {
        byteBuffer.putInt(0);
      } 
      byteBuffer.putInt(stackTraceElement.getLineNumber());
    } 
    return new Chunk(CHUNK_STKL, byteBuffer);
  }
  
  private Chunk handleSTKL(Chunk paramChunk) {
    int i = wrapChunk(paramChunk).getInt();
    StackTraceElement[] arrayOfStackTraceElement = DdmVmInternal.getStackTraceById(i);
    return (arrayOfStackTraceElement == null) ? createFailChunk(1, "Stack trace unavailable") : createStackChunk(arrayOfStackTraceElement, i);
  }
  
  private Chunk handleTHEN(Chunk paramChunk) {
    boolean bool;
    if (wrapChunk(paramChunk).get() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    DdmVmInternal.threadNotify(bool);
    return null;
  }
  
  private Chunk handleTHST(Chunk paramChunk) {
    wrapChunk(paramChunk);
    byte[] arrayOfByte = DdmVmInternal.getThreadStats();
    return (arrayOfByte != null) ? new Chunk(CHUNK_THST, arrayOfByte, 0, arrayOfByte.length) : createFailChunk(1, "Can't build THST chunk");
  }
  
  public static void register() {
    DdmServer.registerHandler(CHUNK_THEN, mInstance);
    DdmServer.registerHandler(CHUNK_THST, mInstance);
    DdmServer.registerHandler(CHUNK_STKL, mInstance);
  }
  
  public void connected() {}
  
  public void disconnected() {}
  
  public Chunk handleChunk(Chunk paramChunk) {
    int i = paramChunk.type;
    if (i == CHUNK_THEN)
      return handleTHEN(paramChunk); 
    if (i == CHUNK_THST)
      return handleTHST(paramChunk); 
    if (i == CHUNK_STKL)
      return handleSTKL(paramChunk); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown packet ");
    stringBuilder.append(ChunkHandler.name(i));
    throw new RuntimeException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/ddm/DdmHandleThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */