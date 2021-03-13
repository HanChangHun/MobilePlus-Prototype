package android.filterpacks.videosrc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GLFrame;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.GenerateFinalPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.ShaderProgram;
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;
import android.graphics.SurfaceTexture;
import android.util.Log;

public class SurfaceTextureTarget extends Filter {
  private static final String TAG = "SurfaceTextureTarget";
  
  private final int RENDERMODE_CUSTOMIZE = 3;
  
  private final int RENDERMODE_FILL_CROP = 2;
  
  private final int RENDERMODE_FIT = 1;
  
  private final int RENDERMODE_STRETCH = 0;
  
  private float mAspectRatio = 1.0F;
  
  private boolean mLogVerbose = Log.isLoggable("SurfaceTextureTarget", 2);
  
  private ShaderProgram mProgram;
  
  private int mRenderMode = 1;
  
  @GenerateFieldPort(hasDefault = true, name = "renderMode")
  private String mRenderModeString;
  
  private GLFrame mScreen;
  
  @GenerateFinalPort(name = "height")
  private int mScreenHeight;
  
  @GenerateFinalPort(name = "width")
  private int mScreenWidth;
  
  @GenerateFieldPort(hasDefault = true, name = "sourceQuad")
  private Quad mSourceQuad = new Quad(new Point(0.0F, 1.0F), new Point(1.0F, 1.0F), new Point(0.0F, 0.0F), new Point(1.0F, 0.0F));
  
  private int mSurfaceId;
  
  @GenerateFinalPort(name = "surfaceTexture")
  private SurfaceTexture mSurfaceTexture;
  
  @GenerateFieldPort(hasDefault = true, name = "targetQuad")
  private Quad mTargetQuad = new Quad(new Point(0.0F, 0.0F), new Point(1.0F, 0.0F), new Point(0.0F, 1.0F), new Point(1.0F, 1.0F));
  
  public SurfaceTextureTarget(String paramString) {
    super(paramString);
  }
  
  private void updateTargetRect() {
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("updateTargetRect. Thread: ");
      stringBuilder.append(Thread.currentThread());
      Log.v("SurfaceTextureTarget", stringBuilder.toString());
    } 
    int i = this.mScreenWidth;
    if (i > 0) {
      int j = this.mScreenHeight;
      if (j > 0 && this.mProgram != null) {
        float f1 = i / j;
        float f2 = f1 / this.mAspectRatio;
        if (this.mLogVerbose) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("UTR. screen w = ");
          stringBuilder.append(this.mScreenWidth);
          stringBuilder.append(" x screen h = ");
          stringBuilder.append(this.mScreenHeight);
          stringBuilder.append(" Screen AR: ");
          stringBuilder.append(f1);
          stringBuilder.append(", frame AR: ");
          stringBuilder.append(this.mAspectRatio);
          stringBuilder.append(", relative AR: ");
          stringBuilder.append(f2);
          Log.v("SurfaceTextureTarget", stringBuilder.toString());
        } 
        if (f2 == 1.0F && this.mRenderMode != 3) {
          this.mProgram.setTargetRect(0.0F, 0.0F, 1.0F, 1.0F);
          this.mProgram.setClearsOutput(false);
        } else {
          i = this.mRenderMode;
          if (i != 0) {
            if (i != 1) {
              if (i != 2) {
                if (i == 3)
                  this.mProgram.setSourceRegion(this.mSourceQuad); 
              } else {
                if (f2 > 1.0F) {
                  this.mTargetQuad.p0.set(0.0F, 0.5F - f2 * 0.5F);
                  this.mTargetQuad.p1.set(1.0F, 0.5F - f2 * 0.5F);
                  this.mTargetQuad.p2.set(0.0F, f2 * 0.5F + 0.5F);
                  this.mTargetQuad.p3.set(1.0F, f2 * 0.5F + 0.5F);
                } else {
                  this.mTargetQuad.p0.set(0.5F - 0.5F / f2, 0.0F);
                  this.mTargetQuad.p1.set(0.5F / f2 + 0.5F, 0.0F);
                  this.mTargetQuad.p2.set(0.5F - 0.5F / f2, 1.0F);
                  this.mTargetQuad.p3.set(0.5F / f2 + 0.5F, 1.0F);
                } 
                this.mProgram.setClearsOutput(true);
              } 
            } else {
              if (f2 > 1.0F) {
                this.mTargetQuad.p0.set(0.5F - 0.5F / f2, 0.0F);
                this.mTargetQuad.p1.set(0.5F / f2 + 0.5F, 0.0F);
                this.mTargetQuad.p2.set(0.5F - 0.5F / f2, 1.0F);
                this.mTargetQuad.p3.set(0.5F / f2 + 0.5F, 1.0F);
              } else {
                this.mTargetQuad.p0.set(0.0F, 0.5F - f2 * 0.5F);
                this.mTargetQuad.p1.set(1.0F, 0.5F - f2 * 0.5F);
                this.mTargetQuad.p2.set(0.0F, f2 * 0.5F + 0.5F);
                this.mTargetQuad.p3.set(1.0F, f2 * 0.5F + 0.5F);
              } 
              this.mProgram.setClearsOutput(true);
            } 
          } else {
            this.mTargetQuad.p0.set(0.0F, 0.0F);
            this.mTargetQuad.p1.set(1.0F, 0.0F);
            this.mTargetQuad.p2.set(0.0F, 1.0F);
            this.mTargetQuad.p3.set(1.0F, 1.0F);
            this.mProgram.setClearsOutput(false);
          } 
          if (this.mLogVerbose) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UTR. quad: ");
            stringBuilder.append(this.mTargetQuad);
            Log.v("SurfaceTextureTarget", stringBuilder.toString());
          } 
          this.mProgram.setTargetRegion(this.mTargetQuad);
        } 
      } 
    } 
  }
  
  public void close(FilterContext paramFilterContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mSurfaceId : I
    //   6: ifle -> 25
    //   9: aload_1
    //   10: invokevirtual getGLEnvironment : ()Landroid/filterfw/core/GLEnvironment;
    //   13: aload_0
    //   14: getfield mSurfaceId : I
    //   17: invokevirtual unregisterSurfaceId : (I)V
    //   20: aload_0
    //   21: iconst_m1
    //   22: putfield mSurfaceId : I
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: astore_1
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_1
    //   32: athrow
    // Exception table:
    //   from	to	target	type
    //   2	25	28	finally
  }
  
  public void disconnect(FilterContext paramFilterContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mLogVerbose : Z
    //   6: ifeq -> 17
    //   9: ldc 'SurfaceTextureTarget'
    //   11: ldc 'disconnect'
    //   13: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   16: pop
    //   17: aload_0
    //   18: getfield mSurfaceTexture : Landroid/graphics/SurfaceTexture;
    //   21: ifnonnull -> 35
    //   24: ldc 'SurfaceTextureTarget'
    //   26: ldc 'SurfaceTexture is already null. Nothing to disconnect.'
    //   28: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   31: pop
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield mSurfaceTexture : Landroid/graphics/SurfaceTexture;
    //   40: aload_0
    //   41: getfield mSurfaceId : I
    //   44: ifle -> 63
    //   47: aload_1
    //   48: invokevirtual getGLEnvironment : ()Landroid/filterfw/core/GLEnvironment;
    //   51: aload_0
    //   52: getfield mSurfaceId : I
    //   55: invokevirtual unregisterSurfaceId : (I)V
    //   58: aload_0
    //   59: iconst_m1
    //   60: putfield mSurfaceId : I
    //   63: aload_0
    //   64: monitorexit
    //   65: return
    //   66: astore_1
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_1
    //   70: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	66	finally
    //   17	32	66	finally
    //   35	63	66	finally
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("FPVU. Thread: ");
      stringBuilder.append(Thread.currentThread());
      Log.v("SurfaceTextureTarget", stringBuilder.toString());
    } 
    updateRenderMode();
  }
  
  public void open(FilterContext paramFilterContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mSurfaceTexture : Landroid/graphics/SurfaceTexture;
    //   6: ifnull -> 79
    //   9: aload_1
    //   10: invokevirtual getGLEnvironment : ()Landroid/filterfw/core/GLEnvironment;
    //   13: aload_0
    //   14: getfield mSurfaceTexture : Landroid/graphics/SurfaceTexture;
    //   17: aload_0
    //   18: getfield mScreenWidth : I
    //   21: aload_0
    //   22: getfield mScreenHeight : I
    //   25: invokevirtual registerSurfaceTexture : (Landroid/graphics/SurfaceTexture;II)I
    //   28: istore_2
    //   29: aload_0
    //   30: iload_2
    //   31: putfield mSurfaceId : I
    //   34: iload_2
    //   35: ifle -> 41
    //   38: aload_0
    //   39: monitorexit
    //   40: return
    //   41: new java/lang/RuntimeException
    //   44: astore_1
    //   45: new java/lang/StringBuilder
    //   48: astore_3
    //   49: aload_3
    //   50: invokespecial <init> : ()V
    //   53: aload_3
    //   54: ldc 'Could not register SurfaceTexture: '
    //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: aload_3
    //   61: aload_0
    //   62: getfield mSurfaceTexture : Landroid/graphics/SurfaceTexture;
    //   65: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload_1
    //   70: aload_3
    //   71: invokevirtual toString : ()Ljava/lang/String;
    //   74: invokespecial <init> : (Ljava/lang/String;)V
    //   77: aload_1
    //   78: athrow
    //   79: ldc 'SurfaceTextureTarget'
    //   81: ldc 'SurfaceTexture is null!!'
    //   83: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   86: pop
    //   87: new java/lang/RuntimeException
    //   90: astore_1
    //   91: new java/lang/StringBuilder
    //   94: astore_3
    //   95: aload_3
    //   96: invokespecial <init> : ()V
    //   99: aload_3
    //   100: ldc 'Could not register SurfaceTexture: '
    //   102: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: pop
    //   106: aload_3
    //   107: aload_0
    //   108: getfield mSurfaceTexture : Landroid/graphics/SurfaceTexture;
    //   111: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   114: pop
    //   115: aload_1
    //   116: aload_3
    //   117: invokevirtual toString : ()Ljava/lang/String;
    //   120: invokespecial <init> : (Ljava/lang/String;)V
    //   123: aload_1
    //   124: athrow
    //   125: astore_1
    //   126: aload_0
    //   127: monitorexit
    //   128: aload_1
    //   129: athrow
    // Exception table:
    //   from	to	target	type
    //   2	34	125	finally
    //   41	79	125	finally
    //   79	125	125	finally
  }
  
  public void prepare(FilterContext paramFilterContext) {
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Prepare. Thread: ");
      stringBuilder.append(Thread.currentThread());
      Log.v("SurfaceTextureTarget", stringBuilder.toString());
    } 
    ShaderProgram shaderProgram = ShaderProgram.createIdentity(paramFilterContext);
    this.mProgram = shaderProgram;
    shaderProgram.setSourceRect(0.0F, 1.0F, 1.0F, -1.0F);
    this.mProgram.setClearColor(0.0F, 0.0F, 0.0F);
    updateRenderMode();
    MutableFrameFormat mutableFrameFormat = new MutableFrameFormat(2, 3);
    mutableFrameFormat.setBytesPerSample(4);
    mutableFrameFormat.setDimensions(this.mScreenWidth, this.mScreenHeight);
    this.mScreen = (GLFrame)paramFilterContext.getFrameManager().newBoundFrame((FrameFormat)mutableFrameFormat, 101, 0L);
  }
  
  public void process(FilterContext paramFilterContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mSurfaceId : I
    //   6: istore_2
    //   7: iload_2
    //   8: ifgt -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_1
    //   15: invokevirtual getGLEnvironment : ()Landroid/filterfw/core/GLEnvironment;
    //   18: astore_3
    //   19: aload_0
    //   20: ldc 'frame'
    //   22: invokevirtual pullInput : (Ljava/lang/String;)Landroid/filterfw/core/Frame;
    //   25: astore #4
    //   27: iconst_0
    //   28: istore_2
    //   29: aload #4
    //   31: invokevirtual getFormat : ()Landroid/filterfw/core/FrameFormat;
    //   34: invokevirtual getWidth : ()I
    //   37: i2f
    //   38: aload #4
    //   40: invokevirtual getFormat : ()Landroid/filterfw/core/FrameFormat;
    //   43: invokevirtual getHeight : ()I
    //   46: i2f
    //   47: fdiv
    //   48: fstore #5
    //   50: fload #5
    //   52: aload_0
    //   53: getfield mAspectRatio : F
    //   56: fcmpl
    //   57: ifeq -> 152
    //   60: aload_0
    //   61: getfield mLogVerbose : Z
    //   64: ifeq -> 142
    //   67: new java/lang/StringBuilder
    //   70: astore #6
    //   72: aload #6
    //   74: invokespecial <init> : ()V
    //   77: aload #6
    //   79: ldc_w 'Process. New aspect ratio: '
    //   82: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: aload #6
    //   88: fload #5
    //   90: invokevirtual append : (F)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload #6
    //   96: ldc_w ', previously: '
    //   99: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: pop
    //   103: aload #6
    //   105: aload_0
    //   106: getfield mAspectRatio : F
    //   109: invokevirtual append : (F)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: aload #6
    //   115: ldc_w '. Thread: '
    //   118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: pop
    //   122: aload #6
    //   124: invokestatic currentThread : ()Ljava/lang/Thread;
    //   127: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   130: pop
    //   131: ldc 'SurfaceTextureTarget'
    //   133: aload #6
    //   135: invokevirtual toString : ()Ljava/lang/String;
    //   138: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   141: pop
    //   142: aload_0
    //   143: fload #5
    //   145: putfield mAspectRatio : F
    //   148: aload_0
    //   149: invokespecial updateTargetRect : ()V
    //   152: aload #4
    //   154: invokevirtual getFormat : ()Landroid/filterfw/core/FrameFormat;
    //   157: invokevirtual getTarget : ()I
    //   160: iconst_3
    //   161: if_icmpeq -> 180
    //   164: aload_1
    //   165: invokevirtual getFrameManager : ()Landroid/filterfw/core/FrameManager;
    //   168: aload #4
    //   170: iconst_3
    //   171: invokevirtual duplicateFrameToTarget : (Landroid/filterfw/core/Frame;I)Landroid/filterfw/core/Frame;
    //   174: astore_1
    //   175: iconst_1
    //   176: istore_2
    //   177: goto -> 183
    //   180: aload #4
    //   182: astore_1
    //   183: aload_3
    //   184: aload_0
    //   185: getfield mSurfaceId : I
    //   188: invokevirtual activateSurfaceWithId : (I)V
    //   191: aload_0
    //   192: getfield mProgram : Landroid/filterfw/core/ShaderProgram;
    //   195: aload_1
    //   196: aload_0
    //   197: getfield mScreen : Landroid/filterfw/core/GLFrame;
    //   200: invokevirtual process : (Landroid/filterfw/core/Frame;Landroid/filterfw/core/Frame;)V
    //   203: aload_3
    //   204: aload #4
    //   206: invokevirtual getTimestamp : ()J
    //   209: invokevirtual setSurfaceTimestamp : (J)V
    //   212: aload_3
    //   213: invokevirtual swapBuffers : ()V
    //   216: iload_2
    //   217: ifeq -> 225
    //   220: aload_1
    //   221: invokevirtual release : ()Landroid/filterfw/core/Frame;
    //   224: pop
    //   225: aload_0
    //   226: monitorexit
    //   227: return
    //   228: astore_1
    //   229: aload_0
    //   230: monitorexit
    //   231: aload_1
    //   232: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	228	finally
    //   14	27	228	finally
    //   29	142	228	finally
    //   142	152	228	finally
    //   152	175	228	finally
    //   183	216	228	finally
    //   220	225	228	finally
  }
  
  public void setupPorts() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mSurfaceTexture : Landroid/graphics/SurfaceTexture;
    //   6: ifnull -> 22
    //   9: aload_0
    //   10: ldc 'frame'
    //   12: iconst_3
    //   13: invokestatic create : (I)Landroid/filterfw/core/MutableFrameFormat;
    //   16: invokevirtual addMaskedInputPort : (Ljava/lang/String;Landroid/filterfw/core/FrameFormat;)V
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: new java/lang/RuntimeException
    //   25: astore_1
    //   26: aload_1
    //   27: ldc_w 'Null SurfaceTexture passed to SurfaceTextureTarget'
    //   30: invokespecial <init> : (Ljava/lang/String;)V
    //   33: aload_1
    //   34: athrow
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	35	finally
    //   22	35	35	finally
  }
  
  public void tearDown(FilterContext paramFilterContext) {
    GLFrame gLFrame = this.mScreen;
    if (gLFrame != null)
      gLFrame.release(); 
  }
  
  public void updateRenderMode() {
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("updateRenderMode. Thread: ");
      stringBuilder.append(Thread.currentThread());
      Log.v("SurfaceTextureTarget", stringBuilder.toString());
    } 
    String str = this.mRenderModeString;
    if (str != null)
      if (str.equals("stretch")) {
        this.mRenderMode = 0;
      } else if (this.mRenderModeString.equals("fit")) {
        this.mRenderMode = 1;
      } else if (this.mRenderModeString.equals("fill_crop")) {
        this.mRenderMode = 2;
      } else if (this.mRenderModeString.equals("customize")) {
        this.mRenderMode = 3;
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown render mode '");
        stringBuilder.append(this.mRenderModeString);
        stringBuilder.append("'!");
        throw new RuntimeException(stringBuilder.toString());
      }  
    updateTargetRect();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosrc/SurfaceTextureTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */