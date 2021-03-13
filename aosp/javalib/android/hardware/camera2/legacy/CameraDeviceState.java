package android.hardware.camera2.legacy;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.os.Handler;
import android.util.Log;

public class CameraDeviceState {
  private static final boolean DEBUG = false;
  
  public static final int NO_CAPTURE_ERROR = -1;
  
  private static final int STATE_CAPTURING = 4;
  
  private static final int STATE_CONFIGURING = 2;
  
  private static final int STATE_ERROR = 0;
  
  private static final int STATE_IDLE = 3;
  
  private static final int STATE_UNCONFIGURED = 1;
  
  private static final String TAG = "CameraDeviceState";
  
  private static final String[] sStateNames = new String[] { "ERROR", "UNCONFIGURED", "CONFIGURING", "IDLE", "CAPTURING" };
  
  private int mCurrentError = -1;
  
  private Handler mCurrentHandler = null;
  
  private CameraDeviceStateListener mCurrentListener = null;
  
  private RequestHolder mCurrentRequest = null;
  
  private int mCurrentState = 1;
  
  private void doStateTransition(int paramInt) {
    doStateTransition(paramInt, 0L, -1);
  }
  
  private void doStateTransition(int paramInt1, final long timestamp, final int error) {
    if (paramInt1 != this.mCurrentState) {
      String str1 = "UNKNOWN";
      String str2 = str1;
      if (paramInt1 >= 0) {
        String[] arrayOfString = sStateNames;
        str2 = str1;
        if (paramInt1 < arrayOfString.length)
          str2 = arrayOfString[paramInt1]; 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Legacy camera service transitioning to state ");
      stringBuilder.append(str2);
      Log.i("CameraDeviceState", stringBuilder.toString());
    } 
    if (paramInt1 != 0 && paramInt1 != 3 && this.mCurrentState != paramInt1) {
      Handler handler = this.mCurrentHandler;
      if (handler != null && this.mCurrentListener != null)
        handler.post(new Runnable() {
              public void run() {
                CameraDeviceState.this.mCurrentListener.onBusy();
              }
            }); 
    } 
    if (paramInt1 != 0) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 == 4) {
            paramInt1 = this.mCurrentState;
            if (paramInt1 != 3 && paramInt1 != 4) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Cannot call capture while in state: ");
              stringBuilder.append(this.mCurrentState);
              Log.e("CameraDeviceState", stringBuilder.toString());
              this.mCurrentError = 1;
              doStateTransition(0);
            } else {
              Handler handler = this.mCurrentHandler;
              if (handler != null && this.mCurrentListener != null)
                if (error != -1) {
                  handler.post(new Runnable() {
                        public void run() {
                          CameraDeviceState.this.mCurrentListener.onError(error, null, CameraDeviceState.this.mCurrentRequest);
                        }
                      });
                } else {
                  handler.post(new Runnable() {
                        public void run() {
                          CameraDeviceState.this.mCurrentListener.onCaptureStarted(CameraDeviceState.this.mCurrentRequest, timestamp);
                        }
                      });
                }  
              this.mCurrentState = 4;
            } 
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Transition to unknown state: ");
            stringBuilder.append(paramInt1);
            throw new IllegalStateException(stringBuilder.toString());
          } 
        } else {
          paramInt1 = this.mCurrentState;
          if (paramInt1 != 3)
            if (paramInt1 != 2 && paramInt1 != 4) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Cannot call idle while in state: ");
              stringBuilder.append(this.mCurrentState);
              Log.e("CameraDeviceState", stringBuilder.toString());
              this.mCurrentError = 1;
              doStateTransition(0);
            } else {
              if (this.mCurrentState != 3) {
                Handler handler = this.mCurrentHandler;
                if (handler != null && this.mCurrentListener != null)
                  handler.post(new Runnable() {
                        public void run() {
                          CameraDeviceState.this.mCurrentListener.onIdle();
                        }
                      }); 
              } 
              this.mCurrentState = 3;
            }  
        } 
      } else {
        paramInt1 = this.mCurrentState;
        if (paramInt1 != 1 && paramInt1 != 3) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Cannot call configure while in state: ");
          stringBuilder.append(this.mCurrentState);
          Log.e("CameraDeviceState", stringBuilder.toString());
          this.mCurrentError = 1;
          doStateTransition(0);
        } else {
          if (this.mCurrentState != 2) {
            Handler handler = this.mCurrentHandler;
            if (handler != null && this.mCurrentListener != null)
              handler.post(new Runnable() {
                    public void run() {
                      CameraDeviceState.this.mCurrentListener.onConfiguring();
                    }
                  }); 
          } 
          this.mCurrentState = 2;
        } 
      } 
    } else {
      if (this.mCurrentState != 0) {
        Handler handler = this.mCurrentHandler;
        if (handler != null && this.mCurrentListener != null)
          handler.post(new Runnable() {
                public void run() {
                  CameraDeviceState.this.mCurrentListener.onError(CameraDeviceState.this.mCurrentError, null, CameraDeviceState.this.mCurrentRequest);
                }
              }); 
      } 
      this.mCurrentState = 0;
    } 
  }
  
  public void setCameraDeviceCallbacks(Handler paramHandler, CameraDeviceStateListener paramCameraDeviceStateListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield mCurrentHandler : Landroid/os/Handler;
    //   7: aload_0
    //   8: aload_2
    //   9: putfield mCurrentListener : Landroid/hardware/camera2/legacy/CameraDeviceState$CameraDeviceStateListener;
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	15	finally
  }
  
  public boolean setCaptureResult(RequestHolder paramRequestHolder, CameraMetadataNative paramCameraMetadataNative) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: iconst_m1
    //   6: aconst_null
    //   7: invokevirtual setCaptureResult : (Landroid/hardware/camera2/legacy/RequestHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;ILjava/lang/Object;)Z
    //   10: istore_3
    //   11: aload_0
    //   12: monitorexit
    //   13: iload_3
    //   14: ireturn
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	15	finally
  }
  
  public boolean setCaptureResult(RequestHolder paramRequestHolder, CameraMetadataNative paramCameraMetadataNative, int paramInt, Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCurrentState : I
    //   6: istore #5
    //   8: iconst_1
    //   9: istore #6
    //   11: iconst_1
    //   12: istore #7
    //   14: iload #5
    //   16: iconst_4
    //   17: if_icmpeq -> 85
    //   20: new java/lang/StringBuilder
    //   23: astore_1
    //   24: aload_1
    //   25: invokespecial <init> : ()V
    //   28: aload_1
    //   29: ldc 'Cannot receive result while in state: '
    //   31: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload_1
    //   36: aload_0
    //   37: getfield mCurrentState : I
    //   40: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: ldc 'CameraDeviceState'
    //   46: aload_1
    //   47: invokevirtual toString : ()Ljava/lang/String;
    //   50: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   53: pop
    //   54: aload_0
    //   55: iconst_1
    //   56: putfield mCurrentError : I
    //   59: aload_0
    //   60: iconst_0
    //   61: invokespecial doStateTransition : (I)V
    //   64: aload_0
    //   65: getfield mCurrentError : I
    //   68: istore_3
    //   69: iload_3
    //   70: iconst_m1
    //   71: if_icmpne -> 77
    //   74: goto -> 80
    //   77: iconst_0
    //   78: istore #7
    //   80: aload_0
    //   81: monitorexit
    //   82: iload #7
    //   84: ireturn
    //   85: aload_0
    //   86: getfield mCurrentHandler : Landroid/os/Handler;
    //   89: ifnull -> 161
    //   92: aload_0
    //   93: getfield mCurrentListener : Landroid/hardware/camera2/legacy/CameraDeviceState$CameraDeviceStateListener;
    //   96: ifnull -> 161
    //   99: iload_3
    //   100: iconst_m1
    //   101: if_icmpeq -> 134
    //   104: aload_0
    //   105: getfield mCurrentHandler : Landroid/os/Handler;
    //   108: astore_2
    //   109: new android/hardware/camera2/legacy/CameraDeviceState$1
    //   112: astore #8
    //   114: aload #8
    //   116: aload_0
    //   117: iload_3
    //   118: aload #4
    //   120: aload_1
    //   121: invokespecial <init> : (Landroid/hardware/camera2/legacy/CameraDeviceState;ILjava/lang/Object;Landroid/hardware/camera2/legacy/RequestHolder;)V
    //   124: aload_2
    //   125: aload #8
    //   127: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   130: pop
    //   131: goto -> 161
    //   134: aload_0
    //   135: getfield mCurrentHandler : Landroid/os/Handler;
    //   138: astore #8
    //   140: new android/hardware/camera2/legacy/CameraDeviceState$2
    //   143: astore #4
    //   145: aload #4
    //   147: aload_0
    //   148: aload_2
    //   149: aload_1
    //   150: invokespecial <init> : (Landroid/hardware/camera2/legacy/CameraDeviceState;Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/legacy/RequestHolder;)V
    //   153: aload #8
    //   155: aload #4
    //   157: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   160: pop
    //   161: aload_0
    //   162: getfield mCurrentError : I
    //   165: istore_3
    //   166: iload_3
    //   167: iconst_m1
    //   168: if_icmpne -> 178
    //   171: iload #6
    //   173: istore #7
    //   175: goto -> 181
    //   178: iconst_0
    //   179: istore #7
    //   181: aload_0
    //   182: monitorexit
    //   183: iload #7
    //   185: ireturn
    //   186: astore_1
    //   187: aload_0
    //   188: monitorexit
    //   189: aload_1
    //   190: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	186	finally
    //   20	69	186	finally
    //   85	99	186	finally
    //   104	131	186	finally
    //   134	161	186	finally
    //   161	166	186	finally
  }
  
  public boolean setCaptureStart(RequestHolder paramRequestHolder, long paramLong, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield mCurrentRequest : Landroid/hardware/camera2/legacy/RequestHolder;
    //   7: aload_0
    //   8: iconst_4
    //   9: lload_2
    //   10: iload #4
    //   12: invokespecial doStateTransition : (IJI)V
    //   15: aload_0
    //   16: getfield mCurrentError : I
    //   19: istore #4
    //   21: iload #4
    //   23: iconst_m1
    //   24: if_icmpne -> 33
    //   27: iconst_1
    //   28: istore #5
    //   30: goto -> 36
    //   33: iconst_0
    //   34: istore #5
    //   36: aload_0
    //   37: monitorexit
    //   38: iload #5
    //   40: ireturn
    //   41: astore_1
    //   42: aload_0
    //   43: monitorexit
    //   44: aload_1
    //   45: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	41	finally
  }
  
  public boolean setConfiguring() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_2
    //   4: invokespecial doStateTransition : (I)V
    //   7: aload_0
    //   8: getfield mCurrentError : I
    //   11: istore_1
    //   12: iload_1
    //   13: iconst_m1
    //   14: if_icmpne -> 22
    //   17: iconst_1
    //   18: istore_2
    //   19: goto -> 24
    //   22: iconst_0
    //   23: istore_2
    //   24: aload_0
    //   25: monitorexit
    //   26: iload_2
    //   27: ireturn
    //   28: astore_3
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_3
    //   32: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	28	finally
  }
  
  public void setError(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield mCurrentError : I
    //   7: aload_0
    //   8: iconst_0
    //   9: invokespecial doStateTransition : (I)V
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: astore_2
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_2
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	15	finally
  }
  
  public boolean setIdle() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_3
    //   4: invokespecial doStateTransition : (I)V
    //   7: aload_0
    //   8: getfield mCurrentError : I
    //   11: istore_1
    //   12: iload_1
    //   13: iconst_m1
    //   14: if_icmpne -> 22
    //   17: iconst_1
    //   18: istore_2
    //   19: goto -> 24
    //   22: iconst_0
    //   23: istore_2
    //   24: aload_0
    //   25: monitorexit
    //   26: iload_2
    //   27: ireturn
    //   28: astore_3
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_3
    //   32: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	28	finally
  }
  
  public void setRepeatingRequestError(long paramLong, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCurrentHandler : Landroid/os/Handler;
    //   6: astore #4
    //   8: new android/hardware/camera2/legacy/CameraDeviceState$3
    //   11: astore #5
    //   13: aload #5
    //   15: aload_0
    //   16: lload_1
    //   17: iload_3
    //   18: invokespecial <init> : (Landroid/hardware/camera2/legacy/CameraDeviceState;JI)V
    //   21: aload #4
    //   23: aload #5
    //   25: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   28: pop
    //   29: aload_0
    //   30: monitorexit
    //   31: return
    //   32: astore #4
    //   34: aload_0
    //   35: monitorexit
    //   36: aload #4
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	32	finally
  }
  
  public void setRequestQueueEmpty() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mCurrentHandler : Landroid/os/Handler;
    //   6: astore_1
    //   7: new android/hardware/camera2/legacy/CameraDeviceState$4
    //   10: astore_2
    //   11: aload_2
    //   12: aload_0
    //   13: invokespecial <init> : (Landroid/hardware/camera2/legacy/CameraDeviceState;)V
    //   16: aload_1
    //   17: aload_2
    //   18: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   21: pop
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	25	finally
  }
  
  public static interface CameraDeviceStateListener {
    void onBusy();
    
    void onCaptureResult(CameraMetadataNative param1CameraMetadataNative, RequestHolder param1RequestHolder);
    
    void onCaptureStarted(RequestHolder param1RequestHolder, long param1Long);
    
    void onConfiguring();
    
    void onError(int param1Int, Object param1Object, RequestHolder param1RequestHolder);
    
    void onIdle();
    
    void onRepeatingRequestError(long param1Long, int param1Int);
    
    void onRequestQueueEmpty();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/CameraDeviceState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */