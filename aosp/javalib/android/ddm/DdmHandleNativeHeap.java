package android.ddm;

import android.util.Log;
import org.apache.harmony.dalvik.ddmc.Chunk;
import org.apache.harmony.dalvik.ddmc.ChunkHandler;
import org.apache.harmony.dalvik.ddmc.DdmServer;

public class DdmHandleNativeHeap extends ChunkHandler {
  public static final int CHUNK_NHGT = type("NHGT");
  
  private static DdmHandleNativeHeap mInstance = new DdmHandleNativeHeap();
  
  private native byte[] getLeakInfo();
  
  private Chunk handleNHGT(Chunk paramChunk) {
    byte[] arrayOfByte = getLeakInfo();
    if (arrayOfByte != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Sending ");
      stringBuilder.append(arrayOfByte.length);
      stringBuilder.append(" bytes");
      Log.i("ddm-nativeheap", stringBuilder.toString());
      return new Chunk(ChunkHandler.type("NHGT"), arrayOfByte, 0, arrayOfByte.length);
    } 
    return createFailChunk(1, "Something went wrong");
  }
  
  public static void register() {
    DdmServer.registerHandler(CHUNK_NHGT, mInstance);
  }
  
  public void connected() {}
  
  public void disconnected() {}
  
  public Chunk handleChunk(Chunk paramChunk) {
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Handling ");
    stringBuilder2.append(name(paramChunk.type));
    stringBuilder2.append(" chunk");
    Log.i("ddm-nativeheap", stringBuilder2.toString());
    int i = paramChunk.type;
    if (i == CHUNK_NHGT)
      return handleNHGT(paramChunk); 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Unknown packet ");
    stringBuilder1.append(ChunkHandler.name(i));
    throw new RuntimeException(stringBuilder1.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/ddm/DdmHandleNativeHeap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */