package android.hardware.camera2.legacy;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.utils.SubmitInfo;
import java.util.ArrayDeque;
import java.util.List;

public class RequestQueue {
  public static final long INVALID_FRAME = -1L;
  
  private static final String TAG = "RequestQueue";
  
  private long mCurrentFrameNumber = 0L;
  
  private long mCurrentRepeatingFrameNumber = -1L;
  
  private int mCurrentRequestId = 0;
  
  private final List<Long> mJpegSurfaceIds;
  
  private BurstHolder mRepeatingRequest = null;
  
  private final ArrayDeque<BurstHolder> mRequestQueue = new ArrayDeque<>();
  
  public RequestQueue(List<Long> paramList) {
    this.mJpegSurfaceIds = paramList;
  }
  
  private long calculateLastFrame(int paramInt) {
    long l = this.mCurrentFrameNumber;
    for (BurstHolder burstHolder : this.mRequestQueue) {
      l += burstHolder.getNumberOfRequests();
      if (burstHolder.getRequestId() == paramInt)
        return l - 1L; 
    } 
    throw new IllegalStateException("At least one request must be in the queue to calculate frame number");
  }
  
  public RequestQueueEntry getNext() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mRequestQueue : Ljava/util/ArrayDeque;
    //   6: invokevirtual poll : ()Ljava/lang/Object;
    //   9: checkcast android/hardware/camera2/legacy/BurstHolder
    //   12: astore_1
    //   13: aload_1
    //   14: ifnull -> 32
    //   17: aload_0
    //   18: getfield mRequestQueue : Ljava/util/ArrayDeque;
    //   21: invokevirtual size : ()I
    //   24: ifne -> 32
    //   27: iconst_1
    //   28: istore_2
    //   29: goto -> 34
    //   32: iconst_0
    //   33: istore_2
    //   34: aload_1
    //   35: astore_3
    //   36: aload_1
    //   37: ifnonnull -> 68
    //   40: aload_1
    //   41: astore_3
    //   42: aload_0
    //   43: getfield mRepeatingRequest : Landroid/hardware/camera2/legacy/BurstHolder;
    //   46: ifnull -> 68
    //   49: aload_0
    //   50: getfield mRepeatingRequest : Landroid/hardware/camera2/legacy/BurstHolder;
    //   53: astore_3
    //   54: aload_0
    //   55: aload_0
    //   56: getfield mCurrentFrameNumber : J
    //   59: aload_3
    //   60: invokevirtual getNumberOfRequests : ()I
    //   63: i2l
    //   64: ladd
    //   65: putfield mCurrentRepeatingFrameNumber : J
    //   68: aload_3
    //   69: ifnonnull -> 76
    //   72: aload_0
    //   73: monitorexit
    //   74: aconst_null
    //   75: areturn
    //   76: new android/hardware/camera2/legacy/RequestQueue$RequestQueueEntry
    //   79: astore_1
    //   80: aload_1
    //   81: aload_0
    //   82: aload_3
    //   83: aload_0
    //   84: getfield mCurrentFrameNumber : J
    //   87: invokestatic valueOf : (J)Ljava/lang/Long;
    //   90: iload_2
    //   91: invokespecial <init> : (Landroid/hardware/camera2/legacy/RequestQueue;Landroid/hardware/camera2/legacy/BurstHolder;Ljava/lang/Long;Z)V
    //   94: aload_0
    //   95: aload_0
    //   96: getfield mCurrentFrameNumber : J
    //   99: aload_3
    //   100: invokevirtual getNumberOfRequests : ()I
    //   103: i2l
    //   104: ladd
    //   105: putfield mCurrentFrameNumber : J
    //   108: aload_0
    //   109: monitorexit
    //   110: aload_1
    //   111: areturn
    //   112: astore_3
    //   113: aload_0
    //   114: monitorexit
    //   115: aload_3
    //   116: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	112	finally
    //   17	27	112	finally
    //   42	68	112	finally
    //   76	108	112	finally
  }
  
  public long stopRepeating() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mRepeatingRequest : Landroid/hardware/camera2/legacy/BurstHolder;
    //   6: ifnonnull -> 23
    //   9: ldc 'RequestQueue'
    //   11: ldc 'cancel failed: no repeating request exists.'
    //   13: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   16: pop
    //   17: aload_0
    //   18: monitorexit
    //   19: ldc2_w -1
    //   22: lreturn
    //   23: aload_0
    //   24: aload_0
    //   25: getfield mRepeatingRequest : Landroid/hardware/camera2/legacy/BurstHolder;
    //   28: invokevirtual getRequestId : ()I
    //   31: invokevirtual stopRepeating : (I)J
    //   34: lstore_1
    //   35: aload_0
    //   36: monitorexit
    //   37: lload_1
    //   38: lreturn
    //   39: astore_3
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_3
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	39	finally
    //   23	35	39	finally
  }
  
  public long stopRepeating(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc2_w -1
    //   5: lstore_2
    //   6: aload_0
    //   7: getfield mRepeatingRequest : Landroid/hardware/camera2/legacy/BurstHolder;
    //   10: ifnull -> 72
    //   13: aload_0
    //   14: getfield mRepeatingRequest : Landroid/hardware/camera2/legacy/BurstHolder;
    //   17: invokevirtual getRequestId : ()I
    //   20: iload_1
    //   21: if_icmpne -> 72
    //   24: aload_0
    //   25: aconst_null
    //   26: putfield mRepeatingRequest : Landroid/hardware/camera2/legacy/BurstHolder;
    //   29: aload_0
    //   30: getfield mCurrentRepeatingFrameNumber : J
    //   33: ldc2_w -1
    //   36: lcmp
    //   37: ifne -> 47
    //   40: ldc2_w -1
    //   43: lstore_2
    //   44: goto -> 54
    //   47: aload_0
    //   48: getfield mCurrentRepeatingFrameNumber : J
    //   51: lconst_1
    //   52: lsub
    //   53: lstore_2
    //   54: aload_0
    //   55: ldc2_w -1
    //   58: putfield mCurrentRepeatingFrameNumber : J
    //   61: ldc 'RequestQueue'
    //   63: ldc 'Repeating capture request cancelled.'
    //   65: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   68: pop
    //   69: goto -> 108
    //   72: new java/lang/StringBuilder
    //   75: astore #4
    //   77: aload #4
    //   79: invokespecial <init> : ()V
    //   82: aload #4
    //   84: ldc 'cancel failed: no repeating request exists for request id: '
    //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: pop
    //   90: aload #4
    //   92: iload_1
    //   93: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: ldc 'RequestQueue'
    //   99: aload #4
    //   101: invokevirtual toString : ()Ljava/lang/String;
    //   104: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   107: pop
    //   108: aload_0
    //   109: monitorexit
    //   110: lload_2
    //   111: lreturn
    //   112: astore #4
    //   114: aload_0
    //   115: monitorexit
    //   116: aload #4
    //   118: athrow
    // Exception table:
    //   from	to	target	type
    //   6	40	112	finally
    //   47	54	112	finally
    //   54	69	112	finally
    //   72	108	112	finally
  }
  
  public SubmitInfo submit(CaptureRequest[] paramArrayOfCaptureRequest, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCurrentRequestId : I
    //   6: istore_3
    //   7: aload_0
    //   8: iload_3
    //   9: iconst_1
    //   10: iadd
    //   11: putfield mCurrentRequestId : I
    //   14: new android/hardware/camera2/legacy/BurstHolder
    //   17: astore #4
    //   19: aload #4
    //   21: iload_3
    //   22: iload_2
    //   23: aload_1
    //   24: aload_0
    //   25: getfield mJpegSurfaceIds : Ljava/util/List;
    //   28: invokespecial <init> : (IZ[Landroid/hardware/camera2/CaptureRequest;Ljava/util/Collection;)V
    //   31: ldc2_w -1
    //   34: lstore #5
    //   36: aload #4
    //   38: invokevirtual isRepeating : ()Z
    //   41: ifeq -> 102
    //   44: ldc 'RequestQueue'
    //   46: ldc 'Repeating capture request set.'
    //   48: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   51: pop
    //   52: aload_0
    //   53: getfield mRepeatingRequest : Landroid/hardware/camera2/legacy/BurstHolder;
    //   56: ifnull -> 86
    //   59: aload_0
    //   60: getfield mCurrentRepeatingFrameNumber : J
    //   63: ldc2_w -1
    //   66: lcmp
    //   67: ifne -> 78
    //   70: ldc2_w -1
    //   73: lstore #5
    //   75: goto -> 86
    //   78: aload_0
    //   79: getfield mCurrentRepeatingFrameNumber : J
    //   82: lconst_1
    //   83: lsub
    //   84: lstore #5
    //   86: aload_0
    //   87: ldc2_w -1
    //   90: putfield mCurrentRepeatingFrameNumber : J
    //   93: aload_0
    //   94: aload #4
    //   96: putfield mRepeatingRequest : Landroid/hardware/camera2/legacy/BurstHolder;
    //   99: goto -> 123
    //   102: aload_0
    //   103: getfield mRequestQueue : Ljava/util/ArrayDeque;
    //   106: aload #4
    //   108: invokevirtual offer : (Ljava/lang/Object;)Z
    //   111: pop
    //   112: aload_0
    //   113: aload #4
    //   115: invokevirtual getRequestId : ()I
    //   118: invokespecial calculateLastFrame : (I)J
    //   121: lstore #5
    //   123: new android/hardware/camera2/utils/SubmitInfo
    //   126: dup
    //   127: iload_3
    //   128: lload #5
    //   130: invokespecial <init> : (IJ)V
    //   133: astore_1
    //   134: aload_0
    //   135: monitorexit
    //   136: aload_1
    //   137: areturn
    //   138: astore_1
    //   139: aload_0
    //   140: monitorexit
    //   141: aload_1
    //   142: athrow
    // Exception table:
    //   from	to	target	type
    //   2	31	138	finally
    //   36	52	138	finally
    //   52	70	138	finally
    //   78	86	138	finally
    //   86	99	138	finally
    //   102	123	138	finally
    //   123	134	138	finally
  }
  
  public final class RequestQueueEntry {
    private final BurstHolder mBurstHolder;
    
    private final Long mFrameNumber;
    
    private final boolean mQueueEmpty;
    
    public RequestQueueEntry(BurstHolder param1BurstHolder, Long param1Long, boolean param1Boolean) {
      this.mBurstHolder = param1BurstHolder;
      this.mFrameNumber = param1Long;
      this.mQueueEmpty = param1Boolean;
    }
    
    public BurstHolder getBurstHolder() {
      return this.mBurstHolder;
    }
    
    public Long getFrameNumber() {
      return this.mFrameNumber;
    }
    
    public boolean isQueueEmpty() {
      return this.mQueueEmpty;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */