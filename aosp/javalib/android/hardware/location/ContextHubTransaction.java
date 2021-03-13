package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Handler;
import android.os.HandlerExecutor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SystemApi
public class ContextHubTransaction<T> {
  public static final int RESULT_FAILED_AT_HUB = 5;
  
  public static final int RESULT_FAILED_BAD_PARAMS = 2;
  
  public static final int RESULT_FAILED_BUSY = 4;
  
  public static final int RESULT_FAILED_HAL_UNAVAILABLE = 8;
  
  public static final int RESULT_FAILED_SERVICE_INTERNAL_FAILURE = 7;
  
  public static final int RESULT_FAILED_TIMEOUT = 6;
  
  public static final int RESULT_FAILED_UNINITIALIZED = 3;
  
  public static final int RESULT_FAILED_UNKNOWN = 1;
  
  public static final int RESULT_SUCCESS = 0;
  
  private static final String TAG = "ContextHubTransaction";
  
  public static final int TYPE_DISABLE_NANOAPP = 3;
  
  public static final int TYPE_ENABLE_NANOAPP = 2;
  
  public static final int TYPE_LOAD_NANOAPP = 0;
  
  public static final int TYPE_QUERY_NANOAPPS = 4;
  
  public static final int TYPE_UNLOAD_NANOAPP = 1;
  
  private final CountDownLatch mDoneSignal = new CountDownLatch(1);
  
  private Executor mExecutor = null;
  
  private boolean mIsResponseSet = false;
  
  private OnCompleteListener<T> mListener = null;
  
  private Response<T> mResponse;
  
  private int mTransactionType;
  
  ContextHubTransaction(int paramInt) {
    this.mTransactionType = paramInt;
  }
  
  public static String typeToString(int paramInt, boolean paramBoolean) {
    String str;
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt != 4) {
              if (paramBoolean) {
                str = "Unknown";
              } else {
                str = "unknown";
              } 
              return str;
            } 
            if (paramBoolean) {
              str = "Query";
            } else {
              str = "query";
            } 
            return str;
          } 
          if (paramBoolean) {
            str = "Disable";
          } else {
            str = "disable";
          } 
          return str;
        } 
        if (paramBoolean) {
          str = "Enable";
        } else {
          str = "enable";
        } 
        return str;
      } 
      if (paramBoolean) {
        str = "Unload";
      } else {
        str = "unload";
      } 
      return str;
    } 
    if (paramBoolean) {
      str = "Load";
    } else {
      str = "load";
    } 
    return str;
  }
  
  public int getType() {
    return this.mTransactionType;
  }
  
  public void setOnCompleteListener(OnCompleteListener<T> paramOnCompleteListener) {
    setOnCompleteListener(paramOnCompleteListener, (Executor)new HandlerExecutor(Handler.getMain()));
  }
  
  public void setOnCompleteListener(OnCompleteListener<T> paramOnCompleteListener, Executor paramExecutor) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc 'OnCompleteListener cannot be null'
    //   5: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   8: pop
    //   9: aload_2
    //   10: ldc 'Executor cannot be null'
    //   12: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   15: pop
    //   16: aload_0
    //   17: getfield mListener : Landroid/hardware/location/ContextHubTransaction$OnCompleteListener;
    //   20: ifnonnull -> 69
    //   23: aload_0
    //   24: aload_1
    //   25: putfield mListener : Landroid/hardware/location/ContextHubTransaction$OnCompleteListener;
    //   28: aload_0
    //   29: aload_2
    //   30: putfield mExecutor : Ljava/util/concurrent/Executor;
    //   33: aload_0
    //   34: getfield mDoneSignal : Ljava/util/concurrent/CountDownLatch;
    //   37: invokevirtual getCount : ()J
    //   40: lconst_0
    //   41: lcmp
    //   42: ifne -> 66
    //   45: aload_0
    //   46: getfield mExecutor : Ljava/util/concurrent/Executor;
    //   49: astore_2
    //   50: new android/hardware/location/_$$Lambda$ContextHubTransaction$7a5H6DrY_dOy9M3qnYHhlmDHRNQ
    //   53: astore_1
    //   54: aload_1
    //   55: aload_0
    //   56: invokespecial <init> : (Landroid/hardware/location/ContextHubTransaction;)V
    //   59: aload_2
    //   60: aload_1
    //   61: invokeinterface execute : (Ljava/lang/Runnable;)V
    //   66: aload_0
    //   67: monitorexit
    //   68: return
    //   69: new java/lang/IllegalStateException
    //   72: astore_1
    //   73: aload_1
    //   74: ldc 'Cannot set ContextHubTransaction listener multiple times'
    //   76: invokespecial <init> : (Ljava/lang/String;)V
    //   79: aload_1
    //   80: athrow
    //   81: astore_1
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_1
    //   85: athrow
    // Exception table:
    //   from	to	target	type
    //   2	66	81	finally
    //   66	68	81	finally
    //   69	81	81	finally
    //   82	84	81	finally
  }
  
  void setResponse(Response<T> paramResponse) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc 'Response cannot be null'
    //   5: invokestatic requireNonNull : (Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
    //   8: pop
    //   9: aload_0
    //   10: getfield mIsResponseSet : Z
    //   13: ifne -> 64
    //   16: aload_0
    //   17: aload_1
    //   18: putfield mResponse : Landroid/hardware/location/ContextHubTransaction$Response;
    //   21: aload_0
    //   22: iconst_1
    //   23: putfield mIsResponseSet : Z
    //   26: aload_0
    //   27: getfield mDoneSignal : Ljava/util/concurrent/CountDownLatch;
    //   30: invokevirtual countDown : ()V
    //   33: aload_0
    //   34: getfield mListener : Landroid/hardware/location/ContextHubTransaction$OnCompleteListener;
    //   37: ifnull -> 61
    //   40: aload_0
    //   41: getfield mExecutor : Ljava/util/concurrent/Executor;
    //   44: astore_2
    //   45: new android/hardware/location/_$$Lambda$ContextHubTransaction$RNVGnle3xCUm9u68syzn6_2znnU
    //   48: astore_1
    //   49: aload_1
    //   50: aload_0
    //   51: invokespecial <init> : (Landroid/hardware/location/ContextHubTransaction;)V
    //   54: aload_2
    //   55: aload_1
    //   56: invokeinterface execute : (Ljava/lang/Runnable;)V
    //   61: aload_0
    //   62: monitorexit
    //   63: return
    //   64: new java/lang/IllegalStateException
    //   67: astore_1
    //   68: aload_1
    //   69: ldc 'Cannot set response of ContextHubTransaction multiple times'
    //   71: invokespecial <init> : (Ljava/lang/String;)V
    //   74: aload_1
    //   75: athrow
    //   76: astore_1
    //   77: aload_0
    //   78: monitorexit
    //   79: aload_1
    //   80: athrow
    // Exception table:
    //   from	to	target	type
    //   2	61	76	finally
    //   61	63	76	finally
    //   64	76	76	finally
    //   77	79	76	finally
  }
  
  public Response<T> waitForResponse(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException, TimeoutException {
    if (this.mDoneSignal.await(paramLong, paramTimeUnit))
      return this.mResponse; 
    throw new TimeoutException("Timed out while waiting for transaction");
  }
  
  @FunctionalInterface
  public static interface OnCompleteListener<L> {
    void onComplete(ContextHubTransaction<L> param1ContextHubTransaction, ContextHubTransaction.Response<L> param1Response);
  }
  
  public static class Response<R> {
    private R mContents;
    
    private int mResult;
    
    Response(int param1Int, R param1R) {
      this.mResult = param1Int;
      this.mContents = param1R;
    }
    
    public R getContents() {
      return this.mContents;
    }
    
    public int getResult() {
      return this.mResult;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Result {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Type {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubTransaction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */