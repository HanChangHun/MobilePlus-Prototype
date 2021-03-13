package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.OutputConfiguration;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.Range;
import android.view.Surface;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public class CameraDeviceCallbacks extends ICameraDeviceCallbacks.Stub {
  private void onCaptureErrorLocked(int paramInt, CaptureResultExtras paramCaptureResultExtras) {
    Iterator<Surface> iterator;
    final Executor holder;
    int i = paramCaptureResultExtras.getRequestId();
    int j = paramCaptureResultExtras.getSubsequenceId();
    final long frameNumber = paramCaptureResultExtras.getFrameNumber();
    String str = paramCaptureResultExtras.getErrorPhysicalCameraId();
    CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder)CameraOfflineSessionImpl.access$500(CameraOfflineSessionImpl.this).get(i);
    boolean bool = false;
    if (captureCallbackHolder == null) {
      Log.e("CameraOfflineSessionImpl", String.format("Receive capture error on unknown request ID %d", new Object[] { Integer.valueOf(i) }));
      return;
    } 
    final CaptureRequest request = captureCallbackHolder.getRequest(j);
    if (paramInt == 5) {
      OutputConfiguration outputConfiguration;
      if (CameraOfflineSessionImpl.access$300(CameraOfflineSessionImpl.this) == null && !CameraOfflineSessionImpl.access$000(CameraOfflineSessionImpl.this)) {
        outputConfiguration = (OutputConfiguration)CameraOfflineSessionImpl.access$1000(CameraOfflineSessionImpl.this).get(paramCaptureResultExtras.getErrorStreamId());
      } else {
        outputConfiguration = (OutputConfiguration)CameraOfflineSessionImpl.access$1100(CameraOfflineSessionImpl.this).get(paramCaptureResultExtras.getErrorStreamId());
      } 
      if (outputConfiguration == null) {
        Log.v("CameraOfflineSessionImpl", String.format("Stream %d has been removed. Skipping buffer lost callback", new Object[] { Integer.valueOf(paramCaptureResultExtras.getErrorStreamId()) }));
        return;
      } 
      iterator = outputConfiguration.getSurfaces().iterator();
      final CaptureCallbackHolder holder = captureCallbackHolder;
      while (iterator.hasNext()) {
        final Surface surface = iterator.next();
        if (!captureRequest.containsTarget(surface))
          continue; 
        executor = captureCallbackHolder1.getCallback().getExecutor();
        Runnable runnable = new Runnable() {
            public void run() {
              CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
              if (!CameraOfflineSessionImpl.access$000(CameraOfflineSessionImpl.this) && captureCallback != null)
                captureCallback.onCaptureBufferLost((CameraCaptureSession)CameraOfflineSessionImpl.this, request, surface, frameNumber); 
            }
          };
        if (executor != null) {
          long l1 = Binder.clearCallingIdentity();
          try {
            executor.execute(runnable);
          } finally {
            Binder.restoreCallingIdentity(l1);
          } 
        } 
      } 
    } else {
      if (paramInt == 4)
        bool = true; 
      final CaptureFailure failure = new CaptureFailure(captureRequest, 0, bool, i, l, (String)iterator);
      Executor executor1 = executor.getCallback().getExecutor();
      Runnable runnable = new Runnable() {
          public void run() {
            CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
            if (!CameraOfflineSessionImpl.access$000(CameraOfflineSessionImpl.this) && captureCallback != null)
              captureCallback.onCaptureFailed((CameraCaptureSession)CameraOfflineSessionImpl.this, request, failure); 
          }
        };
      CameraOfflineSessionImpl.access$800(CameraOfflineSessionImpl.this).updateTracker(l, true, captureRequest.getRequestType());
      CameraOfflineSessionImpl.access$900(CameraOfflineSessionImpl.this);
      if (executor1 != null) {
        long l1 = Binder.clearCallingIdentity();
        try {
          executor1.execute(runnable);
        } finally {
          Binder.restoreCallingIdentity(l1);
        } 
      } 
    } 
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public void onCaptureStarted(CaptureResultExtras paramCaptureResultExtras, long paramLong) {
    Object object3;
    int i = paramCaptureResultExtras.getRequestId();
    long l1 = paramCaptureResultExtras.getFrameNumber();
    long l2 = paramCaptureResultExtras.getLastCompletedRegularFrameNumber();
    long l3 = paramCaptureResultExtras.getLastCompletedReprocessFrameNumber();
    long l4 = paramCaptureResultExtras.getLastCompletedZslFrameNumber();
    Object object2 = CameraOfflineSessionImpl.this.mInterfaceLock;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    try {
      CameraOfflineSessionImpl.access$400(CameraOfflineSessionImpl.this, l2, l3, l4);
      CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder)CameraOfflineSessionImpl.access$500(CameraOfflineSessionImpl.this).get(i);
      if (captureCallbackHolder == null)
        return; 
      Executor executor = captureCallbackHolder.getCallback().getExecutor();
      if (CameraOfflineSessionImpl.access$000(CameraOfflineSessionImpl.this) || executor == null) {
        object = object2;
        return;
      } 
      l4 = Binder.clearCallingIdentity();
      try {
        Runnable runnable = new Runnable() {
            public void run() {
              CameraCaptureSession.CaptureCallback captureCallback = holder.getCallback().getSessionCallback();
              if (!CameraOfflineSessionImpl.access$000(CameraOfflineSessionImpl.this) && captureCallback != null) {
                int i = resultExtras.getSubsequenceId();
                CaptureRequest captureRequest = holder.getRequest(i);
                if (holder.hasBatchedOutputs()) {
                  Range range = (Range)captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
                  for (byte b = 0; b < holder.getRequestCount(); b++) {
                    CaptureRequest captureRequest1 = holder.getRequest(b);
                    long l1 = timestamp;
                    long l2 = (i - b) * 1000000000L / ((Integer)range.getUpper()).intValue();
                    long l3 = frameNumber;
                    long l4 = (i - b);
                    captureCallback.onCaptureStarted((CameraCaptureSession)CameraOfflineSessionImpl.this, captureRequest1, l1 - l2, l3 - l4);
                  } 
                } else {
                  captureCallback.onCaptureStarted((CameraCaptureSession)CameraOfflineSessionImpl.this, holder.getRequest(resultExtras.getSubsequenceId()), timestamp, frameNumber);
                } 
              } 
            }
          };
        object3 = object2;
        try {
          super(this, captureCallbackHolder, (CaptureResultExtras)object, paramLong, l1);
          executor.execute(runnable);
          object = object3;
          try {
            Binder.restoreCallingIdentity(l4);
            return;
          } finally {
            object3 = null;
          } 
        } finally {}
      } finally {}
      Object object = object2;
      Binder.restoreCallingIdentity(l4);
      object = object2;
      throw object3;
    } finally {
      paramCaptureResultExtras = null;
    } 
    Object object1 = object2;
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    throw object3;
  }
  
  public void onDeviceError(int paramInt, CaptureResultExtras paramCaptureResultExtras) {
    // Byte code:
    //   0: aload_0
    //   1: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   4: getfield mInterfaceLock : Ljava/lang/Object;
    //   7: astore_3
    //   8: aload_3
    //   9: monitorenter
    //   10: iload_1
    //   11: iconst_3
    //   12: if_icmpeq -> 68
    //   15: iload_1
    //   16: iconst_4
    //   17: if_icmpeq -> 68
    //   20: iload_1
    //   21: iconst_5
    //   22: if_icmpeq -> 68
    //   25: new android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$1
    //   28: astore_2
    //   29: aload_2
    //   30: aload_0
    //   31: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks;)V
    //   34: invokestatic clearCallingIdentity : ()J
    //   37: lstore #4
    //   39: aload_0
    //   40: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   43: invokestatic access$200 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Ljava/util/concurrent/Executor;
    //   46: aload_2
    //   47: invokeinterface execute : (Ljava/lang/Runnable;)V
    //   52: lload #4
    //   54: invokestatic restoreCallingIdentity : (J)V
    //   57: goto -> 74
    //   60: astore_2
    //   61: lload #4
    //   63: invokestatic restoreCallingIdentity : (J)V
    //   66: aload_2
    //   67: athrow
    //   68: aload_0
    //   69: iload_1
    //   70: aload_2
    //   71: invokespecial onCaptureErrorLocked : (ILandroid/hardware/camera2/impl/CaptureResultExtras;)V
    //   74: aload_3
    //   75: monitorexit
    //   76: return
    //   77: astore_2
    //   78: aload_3
    //   79: monitorexit
    //   80: aload_2
    //   81: athrow
    // Exception table:
    //   from	to	target	type
    //   25	39	77	finally
    //   39	52	60	finally
    //   52	57	77	finally
    //   61	66	77	finally
    //   66	68	77	finally
    //   68	74	77	finally
    //   74	76	77	finally
    //   78	80	77	finally
  }
  
  public void onDeviceIdle() {
    synchronized (CameraOfflineSessionImpl.this.mInterfaceLock) {
      if (CameraOfflineSessionImpl.access$300(CameraOfflineSessionImpl.this) == null) {
        Log.v("CameraOfflineSessionImpl", "Ignoring idle state notifications during offline switches");
        return;
      } 
      CameraOfflineSessionImpl.access$400(CameraOfflineSessionImpl.this, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE);
      null = new Runnable() {
          public void run() {
            if (!CameraOfflineSessionImpl.access$000(CameraOfflineSessionImpl.this))
              CameraOfflineSessionImpl.access$100(CameraOfflineSessionImpl.this).onIdle(CameraOfflineSessionImpl.this); 
          }
        };
      super(this);
      long l = Binder.clearCallingIdentity();
      try {
        CameraOfflineSessionImpl.access$200(CameraOfflineSessionImpl.this).execute(null);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    } 
  }
  
  public void onPrepared(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unexpected stream ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" is prepared");
    Log.e("CameraOfflineSessionImpl", stringBuilder.toString());
  }
  
  public void onRepeatingRequestError(long paramLong, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unexpected repeating request error received. Last frame number is ");
    stringBuilder.append(paramLong);
    Log.e("CameraOfflineSessionImpl", stringBuilder.toString());
  }
  
  public void onRequestQueueEmpty() {
    Log.v("CameraOfflineSessionImpl", "onRequestQueueEmpty");
  }
  
  public void onResultReceived(CameraMetadataNative paramCameraMetadataNative, CaptureResultExtras paramCaptureResultExtras, PhysicalCaptureResultInfo[] paramArrayOfPhysicalCaptureResultInfo) throws RemoteException {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual getRequestId : ()I
    //   4: istore #4
    //   6: aload_2
    //   7: invokevirtual getFrameNumber : ()J
    //   10: lstore #5
    //   12: aload_0
    //   13: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   16: getfield mInterfaceLock : Ljava/lang/Object;
    //   19: astore #7
    //   21: aload #7
    //   23: monitorenter
    //   24: aload_1
    //   25: getstatic android/hardware/camera2/CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   28: aload_0
    //   29: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   32: invokestatic access$600 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/hardware/camera2/CameraCharacteristics;
    //   35: getstatic android/hardware/camera2/CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   38: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   41: checkcast android/util/Size
    //   44: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   47: aload_0
    //   48: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   51: invokestatic access$500 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/util/SparseArray;
    //   54: iload #4
    //   56: invokevirtual get : (I)Ljava/lang/Object;
    //   59: checkcast android/hardware/camera2/impl/CaptureCallbackHolder
    //   62: astore #8
    //   64: aload #8
    //   66: aload_2
    //   67: invokevirtual getSubsequenceId : ()I
    //   70: invokevirtual getRequest : (I)Landroid/hardware/camera2/CaptureRequest;
    //   73: astore #9
    //   75: aload_2
    //   76: invokevirtual getPartialResultCount : ()I
    //   79: aload_0
    //   80: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   83: invokestatic access$700 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)I
    //   86: if_icmpge -> 95
    //   89: iconst_1
    //   90: istore #10
    //   92: goto -> 98
    //   95: iconst_0
    //   96: istore #10
    //   98: aload #9
    //   100: invokevirtual getRequestType : ()I
    //   103: istore #11
    //   105: aload #8
    //   107: ifnonnull -> 138
    //   110: aload_0
    //   111: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   114: invokestatic access$800 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   117: lload #5
    //   119: aconst_null
    //   120: iload #10
    //   122: iload #11
    //   124: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
    //   127: aload #7
    //   129: monitorexit
    //   130: return
    //   131: astore_1
    //   132: aload #7
    //   134: astore_2
    //   135: goto -> 456
    //   138: aload_0
    //   139: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   142: invokestatic access$000 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Z
    //   145: istore #12
    //   147: iload #12
    //   149: ifeq -> 173
    //   152: aload_0
    //   153: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   156: invokestatic access$800 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   159: lload #5
    //   161: aconst_null
    //   162: iload #10
    //   164: iload #11
    //   166: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
    //   169: aload #7
    //   171: monitorexit
    //   172: return
    //   173: aload #8
    //   175: invokevirtual hasBatchedOutputs : ()Z
    //   178: istore #12
    //   180: iload #12
    //   182: ifeq -> 199
    //   185: new android/hardware/camera2/impl/CameraMetadataNative
    //   188: astore #13
    //   190: aload #13
    //   192: aload_1
    //   193: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;)V
    //   196: goto -> 202
    //   199: aconst_null
    //   200: astore #13
    //   202: aload #8
    //   204: invokevirtual getCallback : ()Landroid/hardware/camera2/impl/CaptureCallback;
    //   207: invokevirtual getExecutor : ()Ljava/util/concurrent/Executor;
    //   210: astore #14
    //   212: iload #10
    //   214: ifeq -> 251
    //   217: new android/hardware/camera2/CaptureResult
    //   220: astore_3
    //   221: aload_3
    //   222: aload_1
    //   223: aload #9
    //   225: aload_2
    //   226: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/impl/CaptureResultExtras;)V
    //   229: new android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$4
    //   232: dup
    //   233: aload_0
    //   234: aload #8
    //   236: aload #13
    //   238: aload_2
    //   239: aload #9
    //   241: aload_3
    //   242: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks;Landroid/hardware/camera2/impl/CaptureCallbackHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/impl/CaptureResultExtras;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/CaptureResult;)V
    //   245: astore_1
    //   246: aload_3
    //   247: astore_2
    //   248: goto -> 357
    //   251: aload_0
    //   252: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   255: invokestatic access$800 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   258: lload #5
    //   260: invokevirtual popPartialResults : (J)Ljava/util/List;
    //   263: astore #15
    //   265: aload_1
    //   266: getstatic android/hardware/camera2/CaptureResult.SENSOR_TIMESTAMP : Landroid/hardware/camera2/CaptureResult$Key;
    //   269: invokevirtual get : (Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;
    //   272: checkcast java/lang/Long
    //   275: invokevirtual longValue : ()J
    //   278: lstore #16
    //   280: aload #9
    //   282: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   285: invokevirtual get : (Landroid/hardware/camera2/CaptureRequest$Key;)Ljava/lang/Object;
    //   288: checkcast android/util/Range
    //   291: astore #18
    //   293: aload_2
    //   294: invokevirtual getSubsequenceId : ()I
    //   297: istore #4
    //   299: new android/hardware/camera2/TotalCaptureResult
    //   302: astore #19
    //   304: aload #8
    //   306: invokevirtual getSessionId : ()I
    //   309: istore #20
    //   311: aload #19
    //   313: aload_1
    //   314: aload #9
    //   316: aload_2
    //   317: aload #15
    //   319: iload #20
    //   321: aload_3
    //   322: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/impl/CaptureResultExtras;Ljava/util/List;I[Landroid/hardware/camera2/impl/PhysicalCaptureResultInfo;)V
    //   325: aload #7
    //   327: astore_3
    //   328: new android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks$5
    //   331: dup
    //   332: aload_0
    //   333: aload #8
    //   335: aload #13
    //   337: lload #16
    //   339: iload #4
    //   341: aload #18
    //   343: aload_2
    //   344: aload #15
    //   346: aload #9
    //   348: aload #19
    //   350: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks;Landroid/hardware/camera2/impl/CaptureCallbackHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;JILandroid/util/Range;Landroid/hardware/camera2/impl/CaptureResultExtras;Ljava/util/List;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;)V
    //   353: astore_1
    //   354: aload #19
    //   356: astore_2
    //   357: aload #14
    //   359: ifnull -> 403
    //   362: aload #7
    //   364: astore_3
    //   365: invokestatic clearCallingIdentity : ()J
    //   368: lstore #16
    //   370: aload #14
    //   372: aload_1
    //   373: invokeinterface execute : (Ljava/lang/Runnable;)V
    //   378: aload #7
    //   380: astore_3
    //   381: lload #16
    //   383: invokestatic restoreCallingIdentity : (J)V
    //   386: goto -> 403
    //   389: astore_1
    //   390: aload #7
    //   392: astore_3
    //   393: lload #16
    //   395: invokestatic restoreCallingIdentity : (J)V
    //   398: aload #7
    //   400: astore_3
    //   401: aload_1
    //   402: athrow
    //   403: aload #7
    //   405: astore_3
    //   406: aload_0
    //   407: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   410: invokestatic access$800 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   413: lload #5
    //   415: aload_2
    //   416: iload #10
    //   418: iload #11
    //   420: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
    //   423: iload #10
    //   425: ifne -> 438
    //   428: aload #7
    //   430: astore_3
    //   431: aload_0
    //   432: getfield this$0 : Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   435: invokestatic access$900 : (Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;)V
    //   438: aload #7
    //   440: astore_3
    //   441: aload #7
    //   443: monitorexit
    //   444: return
    //   445: astore_1
    //   446: aload #7
    //   448: astore_2
    //   449: goto -> 456
    //   452: astore_1
    //   453: aload #7
    //   455: astore_2
    //   456: aload_2
    //   457: astore_3
    //   458: aload_2
    //   459: monitorexit
    //   460: aload_1
    //   461: athrow
    //   462: astore_1
    //   463: aload_3
    //   464: astore_2
    //   465: goto -> 456
    // Exception table:
    //   from	to	target	type
    //   24	75	452	finally
    //   75	89	452	finally
    //   98	105	452	finally
    //   110	130	131	finally
    //   138	147	452	finally
    //   152	172	131	finally
    //   173	180	452	finally
    //   185	196	131	finally
    //   202	212	452	finally
    //   217	246	131	finally
    //   251	311	452	finally
    //   311	325	445	finally
    //   328	354	462	finally
    //   365	370	462	finally
    //   370	378	389	finally
    //   381	386	462	finally
    //   393	398	462	finally
    //   401	403	462	finally
    //   406	423	462	finally
    //   431	438	462	finally
    //   441	444	462	finally
    //   458	460	462	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */