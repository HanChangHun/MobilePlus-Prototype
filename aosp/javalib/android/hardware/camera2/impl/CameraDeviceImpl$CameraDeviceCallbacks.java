package android.hardware.camera2.impl;

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
import java.util.List;
import java.util.concurrent.Executor;

public class CameraDeviceCallbacks extends ICameraDeviceCallbacks.Stub {
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public void onCaptureStarted(CaptureResultExtras paramCaptureResultExtras, long paramLong) {
    int i = paramCaptureResultExtras.getRequestId();
    long l1 = paramCaptureResultExtras.getFrameNumber();
    long l2 = paramCaptureResultExtras.getLastCompletedRegularFrameNumber();
    long l3 = paramCaptureResultExtras.getLastCompletedReprocessFrameNumber();
    long l4 = paramCaptureResultExtras.getLastCompletedZslFrameNumber();
    Object object2 = CameraDeviceImpl.this.mInterfaceLock;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    try {
      if (CameraDeviceImpl.access$000(CameraDeviceImpl.this) == null) {
        /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
        return;
      } 
      CameraOfflineSessionImpl cameraOfflineSessionImpl = CameraDeviceImpl.access$300(CameraDeviceImpl.this);
      if (cameraOfflineSessionImpl != null) {
        try {
          CameraOfflineSessionImpl.CameraDeviceCallbacks cameraDeviceCallbacks = CameraDeviceImpl.access$300(CameraDeviceImpl.this).getCallbacks();
          cameraDeviceCallbacks.onCaptureStarted(paramCaptureResultExtras, paramLong);
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
          return;
        } finally {}
      } else {
        CameraDeviceImpl.access$800(CameraDeviceImpl.this, l2, l3, l4);
        CaptureCallbackHolder captureCallbackHolder = (CaptureCallbackHolder)CameraDeviceImpl.access$900(CameraDeviceImpl.this).get(i);
        if (captureCallbackHolder == null) {
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
          return;
        } 
        if (CameraDeviceImpl.access$400(CameraDeviceImpl.this)) {
          /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
          return;
        } 
        l2 = Binder.clearCallingIdentity();
        try {
          Executor executor = captureCallbackHolder.getExecutor();
          Runnable runnable = new Runnable() {
              public void run() {
                if (!CameraDeviceImpl.access$400(CameraDeviceImpl.this)) {
                  int i = resultExtras.getSubsequenceId();
                  CaptureRequest captureRequest = holder.getRequest(i);
                  if (holder.hasBatchedOutputs()) {
                    Range range = (Range)captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
                    for (byte b = 0; b < holder.getRequestCount(); b++)
                      holder.getCallback().onCaptureStarted(CameraDeviceImpl.this, holder.getRequest(b), timestamp - (i - b) * 1000000000L / ((Integer)range.getUpper()).intValue(), frameNumber - (i - b)); 
                  } else {
                    holder.getCallback().onCaptureStarted(CameraDeviceImpl.this, holder.getRequest(resultExtras.getSubsequenceId()), timestamp, frameNumber);
                  } 
                } 
              }
            };
          Object object3 = object2;
          try {
            super(this, paramCaptureResultExtras, captureCallbackHolder, paramLong, l1);
            executor.execute(runnable);
            Object object4 = object3;
            try {
              Binder.restoreCallingIdentity(l2);
              object4 = object3;
              return;
            } finally {
              object3 = null;
            } 
          } finally {}
        } finally {}
        Object object = object2;
        Binder.restoreCallingIdentity(l2);
        object = object2;
        throw cameraOfflineSessionImpl;
      } 
    } finally {}
    CaptureResultExtras captureResultExtras = paramCaptureResultExtras;
    Object object1 = object2;
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    throw captureResultExtras;
  }
  
  public void onDeviceError(int paramInt, CaptureResultExtras paramCaptureResultExtras) {
    CameraDeviceImpl.this.onDeviceError(paramInt, paramCaptureResultExtras);
  }
  
  public void onDeviceIdle() {
    CameraDeviceImpl.this.onDeviceIdle();
  }
  
  public void onPrepared(int paramInt) {
    synchronized (CameraDeviceImpl.this.mInterfaceLock) {
      if (CameraDeviceImpl.access$300(CameraDeviceImpl.this) != null) {
        CameraDeviceImpl.access$300(CameraDeviceImpl.this).getCallbacks().onPrepared(paramInt);
        return;
      } 
      OutputConfiguration outputConfiguration = (OutputConfiguration)CameraDeviceImpl.access$1400(CameraDeviceImpl.this).get(paramInt);
      CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.access$100(CameraDeviceImpl.this);
      if (stateCallbackKK == null)
        return; 
      if (outputConfiguration == null) {
        Log.w(CameraDeviceImpl.access$1500(CameraDeviceImpl.this), "onPrepared invoked for unknown output Surface");
        return;
      } 
      null = outputConfiguration.getSurfaces().iterator();
      while (null.hasNext())
        stateCallbackKK.onSurfacePrepared(null.next()); 
      return;
    } 
  }
  
  public void onRepeatingRequestError(long paramLong, int paramInt) {
    synchronized (CameraDeviceImpl.this.mInterfaceLock) {
      if (CameraDeviceImpl.access$000(CameraDeviceImpl.this) == null || CameraDeviceImpl.access$500(CameraDeviceImpl.this) == -1)
        return; 
      if (CameraDeviceImpl.access$300(CameraDeviceImpl.this) != null) {
        CameraDeviceImpl.access$300(CameraDeviceImpl.this).getCallbacks().onRepeatingRequestError(paramLong, paramInt);
        return;
      } 
      CameraDeviceImpl.access$700(CameraDeviceImpl.this, CameraDeviceImpl.access$500(CameraDeviceImpl.this), paramLong, CameraDeviceImpl.access$600(CameraDeviceImpl.this));
      if (CameraDeviceImpl.access$500(CameraDeviceImpl.this) == paramInt) {
        CameraDeviceImpl.access$502(CameraDeviceImpl.this, -1);
        CameraDeviceImpl.access$602(CameraDeviceImpl.this, null);
      } 
      return;
    } 
  }
  
  public void onRequestQueueEmpty() {
    synchronized (CameraDeviceImpl.this.mInterfaceLock) {
      if (CameraDeviceImpl.access$300(CameraDeviceImpl.this) != null) {
        CameraDeviceImpl.access$300(CameraDeviceImpl.this).getCallbacks().onRequestQueueEmpty();
        return;
      } 
      CameraDeviceImpl.StateCallbackKK stateCallbackKK = CameraDeviceImpl.access$100(CameraDeviceImpl.this);
      if (stateCallbackKK == null)
        return; 
      stateCallbackKK.onRequestQueueEmpty();
      return;
    } 
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
    //   13: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   16: getfield mInterfaceLock : Ljava/lang/Object;
    //   19: astore #7
    //   21: aload #7
    //   23: monitorenter
    //   24: aload_0
    //   25: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   28: invokestatic access$000 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/ICameraDeviceUserWrapper;
    //   31: astore #8
    //   33: aload #8
    //   35: ifnonnull -> 49
    //   38: aload #7
    //   40: monitorexit
    //   41: return
    //   42: astore_1
    //   43: aload #7
    //   45: astore_2
    //   46: goto -> 535
    //   49: aload_0
    //   50: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   53: invokestatic access$300 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   56: astore #8
    //   58: aload #8
    //   60: ifnull -> 94
    //   63: aload_0
    //   64: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   67: invokestatic access$300 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/CameraOfflineSessionImpl;
    //   70: invokevirtual getCallbacks : ()Landroid/hardware/camera2/impl/CameraOfflineSessionImpl$CameraDeviceCallbacks;
    //   73: astore #8
    //   75: aload #8
    //   77: aload_1
    //   78: aload_2
    //   79: aload_3
    //   80: invokevirtual onResultReceived : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/impl/CaptureResultExtras;[Landroid/hardware/camera2/impl/PhysicalCaptureResultInfo;)V
    //   83: aload #7
    //   85: monitorexit
    //   86: return
    //   87: astore_1
    //   88: aload #7
    //   90: astore_2
    //   91: goto -> 535
    //   94: aload_1
    //   95: getstatic android/hardware/camera2/CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   98: aload_0
    //   99: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   102: invokestatic access$1000 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/CameraCharacteristics;
    //   105: getstatic android/hardware/camera2/CameraCharacteristics.LENS_INFO_SHADING_MAP_SIZE : Landroid/hardware/camera2/CameraCharacteristics$Key;
    //   108: invokevirtual get : (Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;
    //   111: checkcast android/util/Size
    //   114: invokevirtual set : (Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)V
    //   117: aload_0
    //   118: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   121: invokestatic access$900 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/util/SparseArray;
    //   124: iload #4
    //   126: invokevirtual get : (I)Ljava/lang/Object;
    //   129: checkcast android/hardware/camera2/impl/CaptureCallbackHolder
    //   132: astore #9
    //   134: aload #9
    //   136: aload_2
    //   137: invokevirtual getSubsequenceId : ()I
    //   140: invokevirtual getRequest : (I)Landroid/hardware/camera2/CaptureRequest;
    //   143: astore #10
    //   145: aload_2
    //   146: invokevirtual getPartialResultCount : ()I
    //   149: aload_0
    //   150: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   153: invokestatic access$1100 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)I
    //   156: if_icmpge -> 165
    //   159: iconst_1
    //   160: istore #11
    //   162: goto -> 168
    //   165: iconst_0
    //   166: istore #11
    //   168: aload #10
    //   170: invokevirtual getRequestType : ()I
    //   173: istore #4
    //   175: aload #9
    //   177: ifnonnull -> 201
    //   180: aload_0
    //   181: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   184: invokestatic access$1200 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   187: lload #5
    //   189: aconst_null
    //   190: iload #11
    //   192: iload #4
    //   194: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
    //   197: aload #7
    //   199: monitorexit
    //   200: return
    //   201: aload_0
    //   202: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   205: invokestatic access$400 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Z
    //   208: istore #12
    //   210: iload #12
    //   212: ifeq -> 245
    //   215: aload_0
    //   216: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   219: invokestatic access$1200 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   222: astore_1
    //   223: aload_1
    //   224: lload #5
    //   226: aconst_null
    //   227: iload #11
    //   229: iload #4
    //   231: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
    //   234: aload #7
    //   236: monitorexit
    //   237: return
    //   238: astore_1
    //   239: aload #7
    //   241: astore_2
    //   242: goto -> 535
    //   245: aload #9
    //   247: invokevirtual hasBatchedOutputs : ()Z
    //   250: istore #12
    //   252: iload #12
    //   254: ifeq -> 278
    //   257: new android/hardware/camera2/impl/CameraMetadataNative
    //   260: astore #8
    //   262: aload #8
    //   264: aload_1
    //   265: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;)V
    //   268: goto -> 281
    //   271: astore_1
    //   272: aload #7
    //   274: astore_2
    //   275: goto -> 535
    //   278: aconst_null
    //   279: astore #8
    //   281: iload #11
    //   283: ifeq -> 320
    //   286: new android/hardware/camera2/CaptureResult
    //   289: astore_3
    //   290: aload_3
    //   291: aload_1
    //   292: aload #10
    //   294: aload_2
    //   295: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/impl/CaptureResultExtras;)V
    //   298: new android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks$2
    //   301: dup
    //   302: aload_0
    //   303: aload #9
    //   305: aload #8
    //   307: aload_2
    //   308: aload #10
    //   310: aload_3
    //   311: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks;Landroid/hardware/camera2/impl/CaptureCallbackHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/impl/CaptureResultExtras;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/CaptureResult;)V
    //   314: astore_1
    //   315: aload_3
    //   316: astore_2
    //   317: goto -> 430
    //   320: aload #9
    //   322: astore #13
    //   324: aload_0
    //   325: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   328: invokestatic access$1200 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   331: lload #5
    //   333: invokevirtual popPartialResults : (J)Ljava/util/List;
    //   336: astore #14
    //   338: aload_1
    //   339: getstatic android/hardware/camera2/CaptureResult.SENSOR_TIMESTAMP : Landroid/hardware/camera2/CaptureResult$Key;
    //   342: invokevirtual get : (Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;
    //   345: checkcast java/lang/Long
    //   348: invokevirtual longValue : ()J
    //   351: lstore #15
    //   353: aload #10
    //   355: getstatic android/hardware/camera2/CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE : Landroid/hardware/camera2/CaptureRequest$Key;
    //   358: invokevirtual get : (Landroid/hardware/camera2/CaptureRequest$Key;)Ljava/lang/Object;
    //   361: checkcast android/util/Range
    //   364: astore #17
    //   366: aload_2
    //   367: invokevirtual getSubsequenceId : ()I
    //   370: istore #18
    //   372: new android/hardware/camera2/TotalCaptureResult
    //   375: astore #19
    //   377: aload #13
    //   379: invokevirtual getSessionId : ()I
    //   382: istore #20
    //   384: aload #19
    //   386: aload_1
    //   387: aload #10
    //   389: aload_2
    //   390: aload #14
    //   392: iload #20
    //   394: aload_3
    //   395: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraMetadataNative;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/impl/CaptureResultExtras;Ljava/util/List;I[Landroid/hardware/camera2/impl/PhysicalCaptureResultInfo;)V
    //   398: aload #7
    //   400: astore_3
    //   401: new android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks$3
    //   404: dup
    //   405: aload_0
    //   406: aload #13
    //   408: aload #8
    //   410: lload #15
    //   412: iload #18
    //   414: aload #17
    //   416: aload_2
    //   417: aload #14
    //   419: aload #10
    //   421: aload #19
    //   423: invokespecial <init> : (Landroid/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks;Landroid/hardware/camera2/impl/CaptureCallbackHolder;Landroid/hardware/camera2/impl/CameraMetadataNative;JILandroid/util/Range;Landroid/hardware/camera2/impl/CaptureResultExtras;Ljava/util/List;Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;)V
    //   426: astore_1
    //   427: aload #19
    //   429: astore_2
    //   430: aload #7
    //   432: astore_3
    //   433: invokestatic clearCallingIdentity : ()J
    //   436: lstore #15
    //   438: aload #9
    //   440: invokevirtual getExecutor : ()Ljava/util/concurrent/Executor;
    //   443: aload_1
    //   444: invokeinterface execute : (Ljava/lang/Runnable;)V
    //   449: aload #7
    //   451: astore_3
    //   452: lload #15
    //   454: invokestatic restoreCallingIdentity : (J)V
    //   457: aload #7
    //   459: astore_3
    //   460: aload_0
    //   461: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   464: invokestatic access$1200 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)Landroid/hardware/camera2/impl/FrameNumberTracker;
    //   467: lload #5
    //   469: aload_2
    //   470: iload #11
    //   472: iload #4
    //   474: invokevirtual updateTracker : (JLandroid/hardware/camera2/CaptureResult;ZI)V
    //   477: iload #11
    //   479: ifne -> 492
    //   482: aload #7
    //   484: astore_3
    //   485: aload_0
    //   486: getfield this$0 : Landroid/hardware/camera2/impl/CameraDeviceImpl;
    //   489: invokestatic access$1300 : (Landroid/hardware/camera2/impl/CameraDeviceImpl;)V
    //   492: aload #7
    //   494: astore_3
    //   495: aload #7
    //   497: monitorexit
    //   498: return
    //   499: astore_1
    //   500: aload #7
    //   502: astore_3
    //   503: lload #15
    //   505: invokestatic restoreCallingIdentity : (J)V
    //   508: aload #7
    //   510: astore_3
    //   511: aload_1
    //   512: athrow
    //   513: astore_1
    //   514: goto -> 518
    //   517: astore_1
    //   518: aload #7
    //   520: astore_2
    //   521: goto -> 535
    //   524: astore_1
    //   525: aload #7
    //   527: astore_2
    //   528: goto -> 535
    //   531: astore_1
    //   532: aload #7
    //   534: astore_2
    //   535: aload_2
    //   536: astore_3
    //   537: aload_2
    //   538: monitorexit
    //   539: aload_1
    //   540: athrow
    //   541: astore_1
    //   542: aload_3
    //   543: astore_2
    //   544: goto -> 535
    // Exception table:
    //   from	to	target	type
    //   24	33	531	finally
    //   38	41	42	finally
    //   49	58	531	finally
    //   63	75	87	finally
    //   75	86	42	finally
    //   94	145	531	finally
    //   145	159	531	finally
    //   168	175	531	finally
    //   180	200	42	finally
    //   201	210	531	finally
    //   215	223	238	finally
    //   223	237	271	finally
    //   245	252	524	finally
    //   257	268	271	finally
    //   286	315	271	finally
    //   324	384	524	finally
    //   384	398	517	finally
    //   401	427	541	finally
    //   433	438	541	finally
    //   438	449	499	finally
    //   452	457	541	finally
    //   460	477	541	finally
    //   485	492	541	finally
    //   495	498	541	finally
    //   503	508	541	finally
    //   511	513	541	finally
    //   537	539	541	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$CameraDeviceCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */