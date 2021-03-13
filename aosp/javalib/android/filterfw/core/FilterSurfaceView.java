package android.filterfw.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FilterSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
  private static int STATE_ALLOCATED = 0;
  
  private static int STATE_CREATED = 1;
  
  private static int STATE_INITIALIZED = 2;
  
  private int mFormat;
  
  private GLEnvironment mGLEnv;
  
  private int mHeight;
  
  private SurfaceHolder.Callback mListener;
  
  private int mState = STATE_ALLOCATED;
  
  private int mSurfaceId = -1;
  
  private int mWidth;
  
  public FilterSurfaceView(Context paramContext) {
    super(paramContext);
    getHolder().addCallback(this);
  }
  
  public FilterSurfaceView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    getHolder().addCallback(this);
  }
  
  private void registerSurface() {
    int i = this.mGLEnv.registerSurface(getHolder().getSurface());
    this.mSurfaceId = i;
    if (i >= 0)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not register Surface: ");
    stringBuilder.append(getHolder().getSurface());
    stringBuilder.append(" in FilterSurfaceView!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  private void unregisterSurface() {
    GLEnvironment gLEnvironment = this.mGLEnv;
    if (gLEnvironment != null) {
      int i = this.mSurfaceId;
      if (i > 0)
        gLEnvironment.unregisterSurfaceId(i); 
    } 
  }
  
  public void bindToListener(SurfaceHolder.Callback paramCallback, GLEnvironment paramGLEnvironment) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull -> 187
    //   6: aload_0
    //   7: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   10: ifnull -> 82
    //   13: aload_0
    //   14: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   17: aload_1
    //   18: if_acmpne -> 24
    //   21: goto -> 82
    //   24: new java/lang/RuntimeException
    //   27: astore_2
    //   28: new java/lang/StringBuilder
    //   31: astore_3
    //   32: aload_3
    //   33: invokespecial <init> : ()V
    //   36: aload_3
    //   37: ldc 'Attempting to bind filter '
    //   39: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload_3
    //   44: aload_1
    //   45: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   48: pop
    //   49: aload_3
    //   50: ldc ' to SurfaceView with another open filter '
    //   52: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: pop
    //   56: aload_3
    //   57: aload_0
    //   58: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   61: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   64: pop
    //   65: aload_3
    //   66: ldc ' attached already!'
    //   68: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: aload_2
    //   73: aload_3
    //   74: invokevirtual toString : ()Ljava/lang/String;
    //   77: invokespecial <init> : (Ljava/lang/String;)V
    //   80: aload_2
    //   81: athrow
    //   82: aload_0
    //   83: aload_1
    //   84: putfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   87: aload_0
    //   88: getfield mGLEnv : Landroid/filterfw/core/GLEnvironment;
    //   91: ifnull -> 113
    //   94: aload_0
    //   95: getfield mGLEnv : Landroid/filterfw/core/GLEnvironment;
    //   98: aload_2
    //   99: if_acmpeq -> 113
    //   102: aload_0
    //   103: getfield mGLEnv : Landroid/filterfw/core/GLEnvironment;
    //   106: aload_0
    //   107: getfield mSurfaceId : I
    //   110: invokevirtual unregisterSurfaceId : (I)V
    //   113: aload_0
    //   114: aload_2
    //   115: putfield mGLEnv : Landroid/filterfw/core/GLEnvironment;
    //   118: aload_0
    //   119: getfield mState : I
    //   122: getstatic android/filterfw/core/FilterSurfaceView.STATE_CREATED : I
    //   125: if_icmplt -> 180
    //   128: aload_0
    //   129: invokespecial registerSurface : ()V
    //   132: aload_0
    //   133: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   136: aload_0
    //   137: invokevirtual getHolder : ()Landroid/view/SurfaceHolder;
    //   140: invokeinterface surfaceCreated : (Landroid/view/SurfaceHolder;)V
    //   145: aload_0
    //   146: getfield mState : I
    //   149: getstatic android/filterfw/core/FilterSurfaceView.STATE_INITIALIZED : I
    //   152: if_icmpne -> 180
    //   155: aload_0
    //   156: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   159: aload_0
    //   160: invokevirtual getHolder : ()Landroid/view/SurfaceHolder;
    //   163: aload_0
    //   164: getfield mFormat : I
    //   167: aload_0
    //   168: getfield mWidth : I
    //   171: aload_0
    //   172: getfield mHeight : I
    //   175: invokeinterface surfaceChanged : (Landroid/view/SurfaceHolder;III)V
    //   180: aload_0
    //   181: monitorexit
    //   182: return
    //   183: astore_1
    //   184: goto -> 199
    //   187: new java/lang/NullPointerException
    //   190: astore_1
    //   191: aload_1
    //   192: ldc 'Attempting to bind null filter to SurfaceView!'
    //   194: invokespecial <init> : (Ljava/lang/String;)V
    //   197: aload_1
    //   198: athrow
    //   199: aload_0
    //   200: monitorexit
    //   201: aload_1
    //   202: athrow
    // Exception table:
    //   from	to	target	type
    //   6	21	183	finally
    //   24	82	183	finally
    //   82	113	183	finally
    //   113	180	183	finally
    //   187	199	183	finally
  }
  
  public GLEnvironment getGLEnv() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mGLEnv : Landroid/filterfw/core/GLEnvironment;
    //   6: astore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: aload_1
    //   10: areturn
    //   11: astore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_1
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public int getSurfaceId() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mSurfaceId : I
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_2
    //   4: putfield mFormat : I
    //   7: aload_0
    //   8: iload_3
    //   9: putfield mWidth : I
    //   12: aload_0
    //   13: iload #4
    //   15: putfield mHeight : I
    //   18: aload_0
    //   19: getstatic android/filterfw/core/FilterSurfaceView.STATE_INITIALIZED : I
    //   22: putfield mState : I
    //   25: aload_0
    //   26: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   29: ifnull -> 46
    //   32: aload_0
    //   33: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   36: aload_1
    //   37: iload_2
    //   38: iload_3
    //   39: iload #4
    //   41: invokeinterface surfaceChanged : (Landroid/view/SurfaceHolder;III)V
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	46	49	finally
  }
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getstatic android/filterfw/core/FilterSurfaceView.STATE_CREATED : I
    //   6: putfield mState : I
    //   9: aload_0
    //   10: getfield mGLEnv : Landroid/filterfw/core/GLEnvironment;
    //   13: ifnull -> 20
    //   16: aload_0
    //   17: invokespecial registerSurface : ()V
    //   20: aload_0
    //   21: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   24: ifnull -> 37
    //   27: aload_0
    //   28: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   31: aload_1
    //   32: invokeinterface surfaceCreated : (Landroid/view/SurfaceHolder;)V
    //   37: aload_0
    //   38: monitorexit
    //   39: return
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	40	finally
    //   20	37	40	finally
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getstatic android/filterfw/core/FilterSurfaceView.STATE_ALLOCATED : I
    //   6: putfield mState : I
    //   9: aload_0
    //   10: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   13: ifnull -> 26
    //   16: aload_0
    //   17: getfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   20: aload_1
    //   21: invokeinterface surfaceDestroyed : (Landroid/view/SurfaceHolder;)V
    //   26: aload_0
    //   27: invokespecial unregisterSurface : ()V
    //   30: aload_0
    //   31: monitorexit
    //   32: return
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: athrow
    // Exception table:
    //   from	to	target	type
    //   2	26	33	finally
    //   26	30	33	finally
  }
  
  public void unbind() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aconst_null
    //   4: putfield mListener : Landroid/view/SurfaceHolder$Callback;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/FilterSurfaceView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */