package android.filterpacks.videosrc;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GLFrame;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.GenerateFinalPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.opengl.Matrix;
import android.util.Log;

public class MediaSource extends Filter {
  private static final int NEWFRAME_TIMEOUT = 100;
  
  private static final int NEWFRAME_TIMEOUT_REPEAT = 10;
  
  private static final int PREP_TIMEOUT = 100;
  
  private static final int PREP_TIMEOUT_REPEAT = 100;
  
  private static final String TAG = "MediaSource";
  
  private static final float[] mSourceCoords_0 = new float[] { 
      1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 
      0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F };
  
  private static final float[] mSourceCoords_180;
  
  private static final float[] mSourceCoords_270 = new float[] { 
      0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 
      0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F };
  
  private static final float[] mSourceCoords_90;
  
  private boolean mCompleted;
  
  @GenerateFieldPort(hasDefault = true, name = "context")
  private Context mContext = null;
  
  private ShaderProgram mFrameExtractor;
  
  private final String mFrameShader = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n";
  
  private boolean mGotSize;
  
  private int mHeight;
  
  private final boolean mLogVerbose = Log.isLoggable("MediaSource", 2);
  
  @GenerateFieldPort(hasDefault = true, name = "loop")
  private boolean mLooping = true;
  
  private GLFrame mMediaFrame;
  
  private MediaPlayer mMediaPlayer;
  
  private boolean mNewFrameAvailable = false;
  
  @GenerateFieldPort(hasDefault = true, name = "orientation")
  private int mOrientation = 0;
  
  private boolean mOrientationUpdated;
  
  private MutableFrameFormat mOutputFormat;
  
  private boolean mPaused;
  
  private boolean mPlaying;
  
  private boolean mPrepared;
  
  @GenerateFieldPort(hasDefault = true, name = "sourceIsUrl")
  private boolean mSelectedIsUrl = false;
  
  @GenerateFieldPort(hasDefault = true, name = "sourceAsset")
  private AssetFileDescriptor mSourceAsset = null;
  
  @GenerateFieldPort(hasDefault = true, name = "sourceUrl")
  private String mSourceUrl = "";
  
  private SurfaceTexture mSurfaceTexture;
  
  @GenerateFieldPort(hasDefault = true, name = "volume")
  private float mVolume = 0.0F;
  
  @GenerateFinalPort(hasDefault = true, name = "waitForNewFrame")
  private boolean mWaitForNewFrame = true;
  
  private int mWidth;
  
  private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
      public void onCompletion(MediaPlayer param1MediaPlayer) {
        if (MediaSource.this.mLogVerbose)
          Log.v("MediaSource", "MediaPlayer has completed playback"); 
        synchronized (MediaSource.this) {
          MediaSource.access$702(MediaSource.this, true);
          return;
        } 
      }
    };
  
  private SurfaceTexture.OnFrameAvailableListener onMediaFrameAvailableListener = new SurfaceTexture.OnFrameAvailableListener() {
      public void onFrameAvailable(SurfaceTexture param1SurfaceTexture) {
        if (MediaSource.this.mLogVerbose)
          Log.v("MediaSource", "New frame from media player"); 
        synchronized (MediaSource.this) {
          if (MediaSource.this.mLogVerbose)
            Log.v("MediaSource", "New frame: notify"); 
          MediaSource.access$802(MediaSource.this, true);
          MediaSource.this.notify();
          if (MediaSource.this.mLogVerbose)
            Log.v("MediaSource", "New frame: notify done"); 
          return;
        } 
      }
    };
  
  private MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {
      public void onPrepared(MediaPlayer param1MediaPlayer) {
        if (MediaSource.this.mLogVerbose)
          Log.v("MediaSource", "MediaPlayer is prepared"); 
        synchronized (MediaSource.this) {
          MediaSource.access$602(MediaSource.this, true);
          MediaSource.this.notify();
          return;
        } 
      }
    };
  
  private MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() {
      public void onVideoSizeChanged(MediaPlayer param1MediaPlayer, int param1Int1, int param1Int2) {
        if (MediaSource.this.mLogVerbose) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("MediaPlayer sent dimensions: ");
          stringBuilder.append(param1Int1);
          stringBuilder.append(" x ");
          stringBuilder.append(param1Int2);
          Log.v("MediaSource", stringBuilder.toString());
        } 
        if (!MediaSource.this.mGotSize) {
          if (MediaSource.this.mOrientation == 0 || MediaSource.this.mOrientation == 180) {
            MediaSource.this.mOutputFormat.setDimensions(param1Int1, param1Int2);
          } else {
            MediaSource.this.mOutputFormat.setDimensions(param1Int2, param1Int1);
          } 
          MediaSource.access$402(MediaSource.this, param1Int1);
          MediaSource.access$502(MediaSource.this, param1Int2);
        } else if (MediaSource.this.mOutputFormat.getWidth() != param1Int1 || MediaSource.this.mOutputFormat.getHeight() != param1Int2) {
          Log.e("MediaSource", "Multiple video size change events received!");
        } 
        synchronized (MediaSource.this) {
          MediaSource.access$102(MediaSource.this, true);
          MediaSource.this.notify();
          return;
        } 
      }
    };
  
  static {
    mSourceCoords_180 = new float[] { 
        0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 
        0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F };
    mSourceCoords_90 = new float[] { 
        1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 
        0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F };
  }
  
  public MediaSource(String paramString) {
    super(paramString);
  }
  
  private void createFormats() {
    this.mOutputFormat = ImageFormat.create(3, 3);
  }
  
  private boolean setupMediaPlayer(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield mPrepared : Z
    //   7: aload_0
    //   8: iconst_0
    //   9: putfield mGotSize : Z
    //   12: aload_0
    //   13: iconst_0
    //   14: putfield mPlaying : Z
    //   17: aload_0
    //   18: iconst_0
    //   19: putfield mPaused : Z
    //   22: aload_0
    //   23: iconst_0
    //   24: putfield mCompleted : Z
    //   27: aload_0
    //   28: iconst_0
    //   29: putfield mNewFrameAvailable : Z
    //   32: aload_0
    //   33: getfield mLogVerbose : Z
    //   36: ifeq -> 47
    //   39: ldc 'MediaSource'
    //   41: ldc 'Setting up playback.'
    //   43: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   46: pop
    //   47: aload_0
    //   48: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   51: ifnull -> 79
    //   54: aload_0
    //   55: getfield mLogVerbose : Z
    //   58: ifeq -> 69
    //   61: ldc 'MediaSource'
    //   63: ldc 'Resetting existing MediaPlayer.'
    //   65: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   68: pop
    //   69: aload_0
    //   70: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   73: invokevirtual reset : ()V
    //   76: goto -> 107
    //   79: aload_0
    //   80: getfield mLogVerbose : Z
    //   83: ifeq -> 94
    //   86: ldc 'MediaSource'
    //   88: ldc 'Creating new MediaPlayer.'
    //   90: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   93: pop
    //   94: new android/media/MediaPlayer
    //   97: astore_2
    //   98: aload_2
    //   99: invokespecial <init> : ()V
    //   102: aload_0
    //   103: aload_2
    //   104: putfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   107: aload_0
    //   108: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   111: astore_2
    //   112: aload_2
    //   113: ifnull -> 542
    //   116: iload_1
    //   117: ifeq -> 206
    //   120: aload_0
    //   121: getfield mLogVerbose : Z
    //   124: ifeq -> 161
    //   127: new java/lang/StringBuilder
    //   130: astore_2
    //   131: aload_2
    //   132: invokespecial <init> : ()V
    //   135: aload_2
    //   136: ldc 'Setting MediaPlayer source to URI '
    //   138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: pop
    //   142: aload_2
    //   143: aload_0
    //   144: getfield mSourceUrl : Ljava/lang/String;
    //   147: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: pop
    //   151: ldc 'MediaSource'
    //   153: aload_2
    //   154: invokevirtual toString : ()Ljava/lang/String;
    //   157: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   160: pop
    //   161: aload_0
    //   162: getfield mContext : Landroid/content/Context;
    //   165: ifnonnull -> 182
    //   168: aload_0
    //   169: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   172: aload_0
    //   173: getfield mSourceUrl : Ljava/lang/String;
    //   176: invokevirtual setDataSource : (Ljava/lang/String;)V
    //   179: goto -> 275
    //   182: aload_0
    //   183: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   186: aload_0
    //   187: getfield mContext : Landroid/content/Context;
    //   190: aload_0
    //   191: getfield mSourceUrl : Ljava/lang/String;
    //   194: invokevirtual toString : ()Ljava/lang/String;
    //   197: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   200: invokevirtual setDataSource : (Landroid/content/Context;Landroid/net/Uri;)V
    //   203: goto -> 275
    //   206: aload_0
    //   207: getfield mLogVerbose : Z
    //   210: ifeq -> 247
    //   213: new java/lang/StringBuilder
    //   216: astore_2
    //   217: aload_2
    //   218: invokespecial <init> : ()V
    //   221: aload_2
    //   222: ldc 'Setting MediaPlayer source to asset '
    //   224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: pop
    //   228: aload_2
    //   229: aload_0
    //   230: getfield mSourceAsset : Landroid/content/res/AssetFileDescriptor;
    //   233: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   236: pop
    //   237: ldc 'MediaSource'
    //   239: aload_2
    //   240: invokevirtual toString : ()Ljava/lang/String;
    //   243: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   246: pop
    //   247: aload_0
    //   248: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   251: aload_0
    //   252: getfield mSourceAsset : Landroid/content/res/AssetFileDescriptor;
    //   255: invokevirtual getFileDescriptor : ()Ljava/io/FileDescriptor;
    //   258: aload_0
    //   259: getfield mSourceAsset : Landroid/content/res/AssetFileDescriptor;
    //   262: invokevirtual getStartOffset : ()J
    //   265: aload_0
    //   266: getfield mSourceAsset : Landroid/content/res/AssetFileDescriptor;
    //   269: invokevirtual getLength : ()J
    //   272: invokevirtual setDataSource : (Ljava/io/FileDescriptor;JJ)V
    //   275: aload_0
    //   276: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   279: aload_0
    //   280: getfield mLooping : Z
    //   283: invokevirtual setLooping : (Z)V
    //   286: aload_0
    //   287: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   290: aload_0
    //   291: getfield mVolume : F
    //   294: aload_0
    //   295: getfield mVolume : F
    //   298: invokevirtual setVolume : (FF)V
    //   301: new android/view/Surface
    //   304: astore_2
    //   305: aload_2
    //   306: aload_0
    //   307: getfield mSurfaceTexture : Landroid/graphics/SurfaceTexture;
    //   310: invokespecial <init> : (Landroid/graphics/SurfaceTexture;)V
    //   313: aload_0
    //   314: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   317: aload_2
    //   318: invokevirtual setSurface : (Landroid/view/Surface;)V
    //   321: aload_2
    //   322: invokevirtual release : ()V
    //   325: aload_0
    //   326: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   329: aload_0
    //   330: getfield onVideoSizeChangedListener : Landroid/media/MediaPlayer$OnVideoSizeChangedListener;
    //   333: invokevirtual setOnVideoSizeChangedListener : (Landroid/media/MediaPlayer$OnVideoSizeChangedListener;)V
    //   336: aload_0
    //   337: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   340: aload_0
    //   341: getfield onPreparedListener : Landroid/media/MediaPlayer$OnPreparedListener;
    //   344: invokevirtual setOnPreparedListener : (Landroid/media/MediaPlayer$OnPreparedListener;)V
    //   347: aload_0
    //   348: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   351: aload_0
    //   352: getfield onCompletionListener : Landroid/media/MediaPlayer$OnCompletionListener;
    //   355: invokevirtual setOnCompletionListener : (Landroid/media/MediaPlayer$OnCompletionListener;)V
    //   358: aload_0
    //   359: getfield mSurfaceTexture : Landroid/graphics/SurfaceTexture;
    //   362: aload_0
    //   363: getfield onMediaFrameAvailableListener : Landroid/graphics/SurfaceTexture$OnFrameAvailableListener;
    //   366: invokevirtual setOnFrameAvailableListener : (Landroid/graphics/SurfaceTexture$OnFrameAvailableListener;)V
    //   369: aload_0
    //   370: getfield mLogVerbose : Z
    //   373: ifeq -> 385
    //   376: ldc 'MediaSource'
    //   378: ldc_w 'Preparing MediaPlayer.'
    //   381: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   384: pop
    //   385: aload_0
    //   386: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   389: invokevirtual prepareAsync : ()V
    //   392: aload_0
    //   393: monitorexit
    //   394: iconst_1
    //   395: ireturn
    //   396: astore_2
    //   397: aload_0
    //   398: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   401: invokevirtual release : ()V
    //   404: aload_0
    //   405: aconst_null
    //   406: putfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   409: iload_1
    //   410: ifeq -> 441
    //   413: new java/lang/RuntimeException
    //   416: astore_3
    //   417: aload_3
    //   418: ldc_w 'Unable to set MediaPlayer to URL %s!'
    //   421: iconst_1
    //   422: anewarray java/lang/Object
    //   425: dup
    //   426: iconst_0
    //   427: aload_0
    //   428: getfield mSourceUrl : Ljava/lang/String;
    //   431: aastore
    //   432: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   435: aload_2
    //   436: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   439: aload_3
    //   440: athrow
    //   441: new java/lang/RuntimeException
    //   444: astore_3
    //   445: aload_3
    //   446: ldc_w 'Unable to set MediaPlayer to asset %s!'
    //   449: iconst_1
    //   450: anewarray java/lang/Object
    //   453: dup
    //   454: iconst_0
    //   455: aload_0
    //   456: getfield mSourceAsset : Landroid/content/res/AssetFileDescriptor;
    //   459: aastore
    //   460: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   463: aload_2
    //   464: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   467: aload_3
    //   468: athrow
    //   469: astore_2
    //   470: aload_0
    //   471: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   474: invokevirtual release : ()V
    //   477: aload_0
    //   478: aconst_null
    //   479: putfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   482: iload_1
    //   483: ifeq -> 514
    //   486: new java/lang/RuntimeException
    //   489: astore_3
    //   490: aload_3
    //   491: ldc_w 'Unable to set MediaPlayer to URL %s!'
    //   494: iconst_1
    //   495: anewarray java/lang/Object
    //   498: dup
    //   499: iconst_0
    //   500: aload_0
    //   501: getfield mSourceUrl : Ljava/lang/String;
    //   504: aastore
    //   505: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   508: aload_2
    //   509: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   512: aload_3
    //   513: athrow
    //   514: new java/lang/RuntimeException
    //   517: astore_3
    //   518: aload_3
    //   519: ldc_w 'Unable to set MediaPlayer to asset %s!'
    //   522: iconst_1
    //   523: anewarray java/lang/Object
    //   526: dup
    //   527: iconst_0
    //   528: aload_0
    //   529: getfield mSourceAsset : Landroid/content/res/AssetFileDescriptor;
    //   532: aastore
    //   533: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   536: aload_2
    //   537: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   540: aload_3
    //   541: athrow
    //   542: new java/lang/RuntimeException
    //   545: astore_2
    //   546: aload_2
    //   547: ldc_w 'Unable to create a MediaPlayer!'
    //   550: invokespecial <init> : (Ljava/lang/String;)V
    //   553: aload_2
    //   554: athrow
    //   555: astore_2
    //   556: aload_0
    //   557: monitorexit
    //   558: aload_2
    //   559: athrow
    // Exception table:
    //   from	to	target	type
    //   2	47	555	finally
    //   47	69	555	finally
    //   69	76	555	finally
    //   79	94	555	finally
    //   94	107	555	finally
    //   107	112	555	finally
    //   120	161	469	java/io/IOException
    //   120	161	396	java/lang/IllegalArgumentException
    //   120	161	555	finally
    //   161	179	469	java/io/IOException
    //   161	179	396	java/lang/IllegalArgumentException
    //   161	179	555	finally
    //   182	203	469	java/io/IOException
    //   182	203	396	java/lang/IllegalArgumentException
    //   182	203	555	finally
    //   206	247	469	java/io/IOException
    //   206	247	396	java/lang/IllegalArgumentException
    //   206	247	555	finally
    //   247	275	469	java/io/IOException
    //   247	275	396	java/lang/IllegalArgumentException
    //   247	275	555	finally
    //   275	385	555	finally
    //   385	392	555	finally
    //   397	409	555	finally
    //   413	441	555	finally
    //   441	469	555	finally
    //   470	482	555	finally
    //   486	514	555	finally
    //   514	542	555	finally
    //   542	555	555	finally
  }
  
  public void close(FilterContext paramFilterContext) {
    if (this.mMediaPlayer.isPlaying())
      this.mMediaPlayer.stop(); 
    this.mPrepared = false;
    this.mGotSize = false;
    this.mPlaying = false;
    this.mPaused = false;
    this.mCompleted = false;
    this.mNewFrameAvailable = false;
    this.mMediaPlayer.release();
    this.mMediaPlayer = null;
    this.mSurfaceTexture.release();
    this.mSurfaceTexture = null;
    if (this.mLogVerbose)
      Log.v("MediaSource", "MediaSource closed"); 
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("MediaSource", "Parameter update"); 
    if (paramString.equals("sourceUrl")) {
      if (isOpen()) {
        if (this.mLogVerbose)
          Log.v("MediaSource", "Opening new source URL"); 
        boolean bool = this.mSelectedIsUrl;
        if (bool)
          setupMediaPlayer(bool); 
      } 
    } else if (paramString.equals("sourceAsset")) {
      if (isOpen()) {
        if (this.mLogVerbose)
          Log.v("MediaSource", "Opening new source FD"); 
        boolean bool = this.mSelectedIsUrl;
        if (!bool)
          setupMediaPlayer(bool); 
      } 
    } else if (paramString.equals("loop")) {
      if (isOpen())
        this.mMediaPlayer.setLooping(this.mLooping); 
    } else if (paramString.equals("sourceIsUrl")) {
      if (isOpen()) {
        if (this.mSelectedIsUrl) {
          if (this.mLogVerbose)
            Log.v("MediaSource", "Opening new source URL"); 
        } else if (this.mLogVerbose) {
          Log.v("MediaSource", "Opening new source Asset");
        } 
        setupMediaPlayer(this.mSelectedIsUrl);
      } 
    } else {
      MediaPlayer mediaPlayer;
      if (paramString.equals("volume")) {
        if (isOpen()) {
          mediaPlayer = this.mMediaPlayer;
          float f = this.mVolume;
          mediaPlayer.setVolume(f, f);
        } 
      } else if (mediaPlayer.equals("orientation") && this.mGotSize) {
        int i = this.mOrientation;
        if (i == 0 || i == 180) {
          this.mOutputFormat.setDimensions(this.mWidth, this.mHeight);
        } else {
          this.mOutputFormat.setDimensions(this.mHeight, this.mWidth);
        } 
        this.mOrientationUpdated = true;
      } 
    } 
  }
  
  public void open(FilterContext paramFilterContext) {
    if (this.mLogVerbose) {
      Log.v("MediaSource", "Opening MediaSource");
      if (this.mSelectedIsUrl) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Current URL is ");
        stringBuilder.append(this.mSourceUrl);
        Log.v("MediaSource", stringBuilder.toString());
      } else {
        Log.v("MediaSource", "Current source is Asset!");
      } 
    } 
    GLFrame gLFrame = (GLFrame)paramFilterContext.getFrameManager().newBoundFrame((FrameFormat)this.mOutputFormat, 104, 0L);
    this.mMediaFrame = gLFrame;
    this.mSurfaceTexture = new SurfaceTexture(gLFrame.getTextureId());
    if (setupMediaPlayer(this.mSelectedIsUrl))
      return; 
    throw new RuntimeException("Error setting up MediaPlayer!");
  }
  
  public void pauseVideo(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual isOpen : ()Z
    //   6: ifeq -> 48
    //   9: iload_1
    //   10: ifeq -> 30
    //   13: aload_0
    //   14: getfield mPaused : Z
    //   17: ifne -> 30
    //   20: aload_0
    //   21: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   24: invokevirtual pause : ()V
    //   27: goto -> 48
    //   30: iload_1
    //   31: ifne -> 48
    //   34: aload_0
    //   35: getfield mPaused : Z
    //   38: ifeq -> 48
    //   41: aload_0
    //   42: getfield mMediaPlayer : Landroid/media/MediaPlayer;
    //   45: invokevirtual start : ()V
    //   48: aload_0
    //   49: iload_1
    //   50: putfield mPaused : Z
    //   53: aload_0
    //   54: monitorexit
    //   55: return
    //   56: astore_2
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_2
    //   60: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	56	finally
    //   13	27	56	finally
    //   34	48	56	finally
    //   48	53	56	finally
  }
  
  protected void prepare(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("MediaSource", "Preparing MediaSource"); 
    ShaderProgram shaderProgram = new ShaderProgram(paramFilterContext, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n}\n");
    this.mFrameExtractor = shaderProgram;
    shaderProgram.setSourceRect(0.0F, 1.0F, 1.0F, -1.0F);
    createFormats();
  }
  
  public void process(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("MediaSource", "Processing new frame"); 
    if (this.mMediaPlayer != null) {
      if (this.mCompleted) {
        closeOutputPort("video");
        return;
      } 
      if (!this.mPlaying) {
        if (this.mLogVerbose)
          Log.v("MediaSource", "Waiting for preparation to complete"); 
        byte b = 0;
        while (true) {
          if (!this.mGotSize || !this.mPrepared) {
            try {
              wait(100L);
            } catch (InterruptedException interruptedException) {}
            if (this.mCompleted) {
              closeOutputPort("video");
              return;
            } 
            if (++b != 100)
              continue; 
            this.mMediaPlayer.release();
            throw new RuntimeException("MediaPlayer timed out while preparing!");
          } 
          if (this.mLogVerbose)
            Log.v("MediaSource", "Starting playback"); 
          this.mMediaPlayer.start();
          break;
        } 
      } 
      if (!this.mPaused || !this.mPlaying) {
        if (this.mWaitForNewFrame) {
          if (this.mLogVerbose)
            Log.v("MediaSource", "Waiting for new frame"); 
          for (byte b = 0; !this.mNewFrameAvailable; b++) {
            if (b == 10) {
              if (this.mCompleted) {
                closeOutputPort("video");
                return;
              } 
              throw new RuntimeException("Timeout waiting for new frame!");
            } 
            try {
              wait(100L);
            } catch (InterruptedException interruptedException) {
              if (this.mLogVerbose)
                Log.v("MediaSource", "interrupted"); 
            } 
          } 
          this.mNewFrameAvailable = false;
          if (this.mLogVerbose)
            Log.v("MediaSource", "Got new frame"); 
        } 
        this.mSurfaceTexture.updateTexImage();
        this.mOrientationUpdated = true;
      } 
      if (this.mOrientationUpdated) {
        float[] arrayOfFloat2 = new float[16];
        this.mSurfaceTexture.getTransformMatrix(arrayOfFloat2);
        float[] arrayOfFloat1 = new float[16];
        int i = this.mOrientation;
        if (i != 90) {
          if (i != 180) {
            if (i != 270) {
              Matrix.multiplyMM(arrayOfFloat1, 0, arrayOfFloat2, 0, mSourceCoords_0, 0);
            } else {
              Matrix.multiplyMM(arrayOfFloat1, 0, arrayOfFloat2, 0, mSourceCoords_270, 0);
            } 
          } else {
            Matrix.multiplyMM(arrayOfFloat1, 0, arrayOfFloat2, 0, mSourceCoords_180, 0);
          } 
        } else {
          Matrix.multiplyMM(arrayOfFloat1, 0, arrayOfFloat2, 0, mSourceCoords_90, 0);
        } 
        if (this.mLogVerbose) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("OrientationHint = ");
          stringBuilder.append(this.mOrientation);
          Log.v("MediaSource", stringBuilder.toString());
          Log.v("MediaSource", String.format("SetSourceRegion: %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f", new Object[] { Float.valueOf(arrayOfFloat1[4]), Float.valueOf(arrayOfFloat1[5]), Float.valueOf(arrayOfFloat1[0]), Float.valueOf(arrayOfFloat1[1]), Float.valueOf(arrayOfFloat1[12]), Float.valueOf(arrayOfFloat1[13]), Float.valueOf(arrayOfFloat1[8]), Float.valueOf(arrayOfFloat1[9]) }));
        } 
        this.mFrameExtractor.setSourceRegion(arrayOfFloat1[4], arrayOfFloat1[5], arrayOfFloat1[0], arrayOfFloat1[1], arrayOfFloat1[12], arrayOfFloat1[13], arrayOfFloat1[8], arrayOfFloat1[9]);
        this.mOrientationUpdated = false;
      } 
      Frame frame = paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mOutputFormat);
      this.mFrameExtractor.process((Frame)this.mMediaFrame, frame);
      long l = this.mSurfaceTexture.getTimestamp();
      if (this.mLogVerbose) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Timestamp: ");
        stringBuilder.append(l / 1.0E9D);
        stringBuilder.append(" s");
        Log.v("MediaSource", stringBuilder.toString());
      } 
      frame.setTimestamp(l);
      pushOutput("video", frame);
      frame.release();
      this.mPlaying = true;
      return;
    } 
    throw new NullPointerException("Unexpected null media player!");
  }
  
  public void setupPorts() {
    addOutputPort("video", (FrameFormat)ImageFormat.create(3, 3));
  }
  
  public void tearDown(FilterContext paramFilterContext) {
    GLFrame gLFrame = this.mMediaFrame;
    if (gLFrame != null)
      gLFrame.release(); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videosrc/MediaSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */