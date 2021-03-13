package android.content;

import android.os.Bundle;
import android.os.RemoteCallback;

abstract class ResultListener<T> implements RemoteCallback.OnResultListener {
  public boolean done;
  
  public RuntimeException exception;
  
  public T result;
  
  private ResultListener() {}
  
  protected abstract T getResultFromBundle(Bundle paramBundle);
  
  public void onResult(Bundle paramBundle) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc 'error'
    //   5: invokevirtual getParcelable : (Ljava/lang/String;)Landroid/os/Parcelable;
    //   8: checkcast android/os/ParcelableException
    //   11: astore_2
    //   12: aload_2
    //   13: ifnull -> 56
    //   16: aload_2
    //   17: invokevirtual getCause : ()Ljava/lang/Throwable;
    //   20: astore_1
    //   21: aload_1
    //   22: instanceof java/lang/RuntimeException
    //   25: ifeq -> 39
    //   28: aload_0
    //   29: aload_1
    //   30: checkcast java/lang/RuntimeException
    //   33: putfield exception : Ljava/lang/RuntimeException;
    //   36: goto -> 53
    //   39: new java/lang/RuntimeException
    //   42: astore_2
    //   43: aload_2
    //   44: aload_1
    //   45: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   48: aload_0
    //   49: aload_2
    //   50: putfield exception : Ljava/lang/RuntimeException;
    //   53: goto -> 65
    //   56: aload_0
    //   57: aload_0
    //   58: aload_1
    //   59: invokevirtual getResultFromBundle : (Landroid/os/Bundle;)Ljava/lang/Object;
    //   62: putfield result : Ljava/lang/Object;
    //   65: aload_0
    //   66: iconst_1
    //   67: putfield done : Z
    //   70: aload_0
    //   71: invokevirtual notifyAll : ()V
    //   74: aload_0
    //   75: monitorexit
    //   76: return
    //   77: astore_1
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_1
    //   81: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	77	finally
    //   16	36	77	finally
    //   39	53	77	finally
    //   56	65	77	finally
    //   65	76	77	finally
    //   78	80	77	finally
  }
  
  public void waitForResult(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield done : Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifne -> 21
    //   11: aload_0
    //   12: lload_1
    //   13: invokevirtual wait : (J)V
    //   16: goto -> 21
    //   19: astore #4
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: astore #4
    //   26: aload_0
    //   27: monitorexit
    //   28: aload #4
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	24	finally
    //   11	16	19	java/lang/InterruptedException
    //   11	16	24	finally
    //   21	23	24	finally
    //   26	28	24	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentResolver$ResultListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */