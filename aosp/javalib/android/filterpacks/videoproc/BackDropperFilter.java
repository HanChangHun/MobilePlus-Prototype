package android.filterpacks.videoproc;

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
import android.opengl.GLES20;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import java.util.Arrays;

public class BackDropperFilter extends Filter {
  private static final float DEFAULT_ACCEPT_STDDEV = 0.85F;
  
  private static final float DEFAULT_ADAPT_RATE_BG = 0.0F;
  
  private static final float DEFAULT_ADAPT_RATE_FG = 0.0F;
  
  private static final String DEFAULT_AUTO_WB_SCALE = "0.25";
  
  private static final float[] DEFAULT_BG_FIT_TRANSFORM = new float[] { 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F };
  
  private static final float DEFAULT_EXPOSURE_CHANGE = 1.0F;
  
  private static final int DEFAULT_HIER_LRG_EXPONENT = 3;
  
  private static final float DEFAULT_HIER_LRG_SCALE = 0.7F;
  
  private static final int DEFAULT_HIER_MID_EXPONENT = 2;
  
  private static final float DEFAULT_HIER_MID_SCALE = 0.6F;
  
  private static final int DEFAULT_HIER_SML_EXPONENT = 0;
  
  private static final float DEFAULT_HIER_SML_SCALE = 0.5F;
  
  private static final float DEFAULT_LEARNING_ADAPT_RATE = 0.2F;
  
  private static final int DEFAULT_LEARNING_DONE_THRESHOLD = 20;
  
  private static final int DEFAULT_LEARNING_DURATION = 40;
  
  private static final int DEFAULT_LEARNING_VERIFY_DURATION = 10;
  
  private static final float DEFAULT_MASK_BLEND_BG = 0.65F;
  
  private static final float DEFAULT_MASK_BLEND_FG = 0.95F;
  
  private static final int DEFAULT_MASK_HEIGHT_EXPONENT = 8;
  
  private static final float DEFAULT_MASK_VERIFY_RATE = 0.25F;
  
  private static final int DEFAULT_MASK_WIDTH_EXPONENT = 8;
  
  private static final float DEFAULT_UV_SCALE_FACTOR = 1.35F;
  
  private static final float DEFAULT_WHITE_BALANCE_BLUE_CHANGE = 0.0F;
  
  private static final float DEFAULT_WHITE_BALANCE_RED_CHANGE = 0.0F;
  
  private static final int DEFAULT_WHITE_BALANCE_TOGGLE = 0;
  
  private static final float DEFAULT_Y_SCALE_FACTOR = 0.4F;
  
  private static final String DISTANCE_STORAGE_SCALE = "0.6";
  
  private static final String MASK_SMOOTH_EXPONENT = "2.0";
  
  private static final String MIN_VARIANCE = "3.0";
  
  private static final String RGB_TO_YUV_MATRIX = "0.299, -0.168736,  0.5,      0.000, 0.587, -0.331264, -0.418688, 0.000, 0.114,  0.5,      -0.081312, 0.000, 0.000,  0.5,       0.5,      1.000 ";
  
  private static final String TAG = "BackDropperFilter";
  
  private static final String VARIANCE_STORAGE_SCALE = "5.0";
  
  private static final String mAutomaticWhiteBalance = "uniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float pyramid_depth;\nuniform bool autowb_toggle;\nvarying vec2 v_texcoord;\nvoid main() {\n   vec4 mean_video = texture2D(tex_sampler_0, v_texcoord, pyramid_depth);\n   vec4 mean_bg = texture2D(tex_sampler_1, v_texcoord, pyramid_depth);\n   float green_normalizer = mean_video.g / mean_bg.g;\n   vec4 adjusted_value = vec4(mean_bg.r / mean_video.r * green_normalizer, 1., \n                         mean_bg.b / mean_video.b * green_normalizer, 1.) * auto_wb_scale; \n   gl_FragColor = autowb_toggle ? adjusted_value : vec4(auto_wb_scale);\n}\n";
  
  private static final String mBgDistanceShader = "uniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform sampler2D tex_sampler_2;\nuniform float subsample_level;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 fg_rgb = texture2D(tex_sampler_0, v_texcoord, subsample_level);\n  vec4 fg = coeff_yuv * vec4(fg_rgb.rgb, 1.);\n  vec4 mean = texture2D(tex_sampler_1, v_texcoord);\n  vec4 variance = inv_var_scale * texture2D(tex_sampler_2, v_texcoord);\n\n  float dist_y = gauss_dist_y(fg.r, mean.r, variance.r);\n  float dist_uv = gauss_dist_uv(fg.gb, mean.gb, variance.gb);\n  gl_FragColor = vec4(0.5*fg.rg, dist_scale*dist_y, dist_scale*dist_uv);\n}\n";
  
  private static final String mBgMaskShader = "uniform sampler2D tex_sampler_0;\nuniform float accept_variance;\nuniform vec2 yuv_weights;\nuniform float scale_lrg;\nuniform float scale_mid;\nuniform float scale_sml;\nuniform float exp_lrg;\nuniform float exp_mid;\nuniform float exp_sml;\nvarying vec2 v_texcoord;\nbool is_fg(vec2 dist_yc, float accept_variance) {\n  return ( dot(yuv_weights, dist_yc) >= accept_variance );\n}\nvoid main() {\n  vec4 dist_lrg_sc = texture2D(tex_sampler_0, v_texcoord, exp_lrg);\n  vec4 dist_mid_sc = texture2D(tex_sampler_0, v_texcoord, exp_mid);\n  vec4 dist_sml_sc = texture2D(tex_sampler_0, v_texcoord, exp_sml);\n  vec2 dist_lrg = inv_dist_scale * dist_lrg_sc.ba;\n  vec2 dist_mid = inv_dist_scale * dist_mid_sc.ba;\n  vec2 dist_sml = inv_dist_scale * dist_sml_sc.ba;\n  vec2 norm_dist = 0.75 * dist_sml / accept_variance;\n  bool is_fg_lrg = is_fg(dist_lrg, accept_variance * scale_lrg);\n  bool is_fg_mid = is_fg_lrg || is_fg(dist_mid, accept_variance * scale_mid);\n  float is_fg_sml =\n      float(is_fg_mid || is_fg(dist_sml, accept_variance * scale_sml));\n  float alpha = 0.5 * is_fg_sml + 0.3 * float(is_fg_mid) + 0.2 * float(is_fg_lrg);\n  gl_FragColor = vec4(alpha, norm_dist, is_fg_sml);\n}\n";
  
  private static final String mBgSubtractForceShader = "  vec4 ghost_rgb = (fg_adjusted * 0.7 + vec4(0.3,0.3,0.4,0.))*0.65 + \n                   0.35*bg_rgb;\n  float glow_start = 0.75 * mask_blend_bg; \n  float glow_max   = mask_blend_bg; \n  gl_FragColor = mask.a < glow_start ? bg_rgb : \n                 mask.a < glow_max ? mix(bg_rgb, vec4(0.9,0.9,1.0,1.0), \n                                     (mask.a - glow_start) / (glow_max - glow_start) ) : \n                 mask.a < mask_blend_fg ? mix(vec4(0.9,0.9,1.0,1.0), ghost_rgb, \n                                    (mask.a - glow_max) / (mask_blend_fg - glow_max) ) : \n                 ghost_rgb;\n}\n";
  
  private static final String mBgSubtractShader = "uniform mat3 bg_fit_transform;\nuniform float mask_blend_bg;\nuniform float mask_blend_fg;\nuniform float exposure_change;\nuniform float whitebalancered_change;\nuniform float whitebalanceblue_change;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform sampler2D tex_sampler_2;\nuniform sampler2D tex_sampler_3;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec2 bg_texcoord = (bg_fit_transform * vec3(v_texcoord, 1.)).xy;\n  vec4 bg_rgb = texture2D(tex_sampler_1, bg_texcoord);\n  vec4 wb_auto_scale = texture2D(tex_sampler_3, v_texcoord) * exposure_change / auto_wb_scale;\n  vec4 wb_manual_scale = vec4(1. + whitebalancered_change, 1., 1. + whitebalanceblue_change, 1.);\n  vec4 fg_rgb = texture2D(tex_sampler_0, v_texcoord);\n  vec4 fg_adjusted = fg_rgb * wb_manual_scale * wb_auto_scale;\n  vec4 mask = texture2D(tex_sampler_2, v_texcoord, \n                      2.0);\n  float alpha = smoothstep(mask_blend_bg, mask_blend_fg, mask.a);\n  gl_FragColor = mix(bg_rgb, fg_adjusted, alpha);\n";
  
  private static final String[] mDebugOutputNames;
  
  private static final String[] mInputNames = new String[] { "video", "background" };
  
  private static final String mMaskVerifyShader = "uniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float verify_rate;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 lastmask = texture2D(tex_sampler_0, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_1, v_texcoord);\n  float newmask = mix(lastmask.a, mask.a, verify_rate);\n  gl_FragColor = vec4(0., 0., 0., newmask);\n}\n";
  
  private static final String[] mOutputNames = new String[] { "video" };
  
  private static String mSharedUtilShader;
  
  private static final String mUpdateBgModelMeanShader = "uniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform sampler2D tex_sampler_2;\nuniform float subsample_level;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 fg_rgb = texture2D(tex_sampler_0, v_texcoord, subsample_level);\n  vec4 fg = coeff_yuv * vec4(fg_rgb.rgb, 1.);\n  vec4 mean = texture2D(tex_sampler_1, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_2, v_texcoord, \n                      2.0);\n\n  float alpha = local_adapt_rate(mask.a);\n  vec4 new_mean = mix(mean, fg, alpha);\n  gl_FragColor = new_mean;\n}\n";
  
  private static final String mUpdateBgModelVarianceShader = "uniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform sampler2D tex_sampler_2;\nuniform sampler2D tex_sampler_3;\nuniform float subsample_level;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 fg_rgb = texture2D(tex_sampler_0, v_texcoord, subsample_level);\n  vec4 fg = coeff_yuv * vec4(fg_rgb.rgb, 1.);\n  vec4 mean = texture2D(tex_sampler_1, v_texcoord);\n  vec4 variance = inv_var_scale * texture2D(tex_sampler_2, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_3, v_texcoord, \n                      2.0);\n\n  float alpha = local_adapt_rate(mask.a);\n  vec4 cur_variance = (fg-mean)*(fg-mean);\n  vec4 new_variance = mix(variance, cur_variance, alpha);\n  new_variance = max(new_variance, vec4(min_variance));\n  gl_FragColor = var_scale * new_variance;\n}\n";
  
  private final int BACKGROUND_FILL_CROP = 2;
  
  private final int BACKGROUND_FIT = 1;
  
  private final int BACKGROUND_STRETCH = 0;
  
  private ShaderProgram copyShaderProgram;
  
  private boolean isOpen;
  
  @GenerateFieldPort(hasDefault = true, name = "acceptStddev")
  private float mAcceptStddev = 0.85F;
  
  @GenerateFieldPort(hasDefault = true, name = "adaptRateBg")
  private float mAdaptRateBg = 0.0F;
  
  @GenerateFieldPort(hasDefault = true, name = "adaptRateFg")
  private float mAdaptRateFg = 0.0F;
  
  @GenerateFieldPort(hasDefault = true, name = "learningAdaptRate")
  private float mAdaptRateLearning = 0.2F;
  
  private GLFrame mAutoWB;
  
  @GenerateFieldPort(hasDefault = true, name = "autowbToggle")
  private int mAutoWBToggle = 0;
  
  private ShaderProgram mAutomaticWhiteBalanceProgram;
  
  private MutableFrameFormat mAverageFormat;
  
  @GenerateFieldPort(hasDefault = true, name = "backgroundFitMode")
  private int mBackgroundFitMode = 2;
  
  private boolean mBackgroundFitModeChanged;
  
  private ShaderProgram mBgDistProgram;
  
  private GLFrame mBgInput;
  
  private ShaderProgram mBgMaskProgram;
  
  private GLFrame[] mBgMean;
  
  private ShaderProgram mBgSubtractProgram;
  
  private ShaderProgram mBgUpdateMeanProgram;
  
  private ShaderProgram mBgUpdateVarianceProgram;
  
  private GLFrame[] mBgVariance;
  
  @GenerateFieldPort(hasDefault = true, name = "chromaScale")
  private float mChromaScale = 1.35F;
  
  private ShaderProgram mCopyOutProgram;
  
  private GLFrame mDistance;
  
  @GenerateFieldPort(hasDefault = true, name = "exposureChange")
  private float mExposureChange = 1.0F;
  
  private int mFrameCount;
  
  @GenerateFieldPort(hasDefault = true, name = "hierLrgExp")
  private int mHierarchyLrgExp = 3;
  
  @GenerateFieldPort(hasDefault = true, name = "hierLrgScale")
  private float mHierarchyLrgScale = 0.7F;
  
  @GenerateFieldPort(hasDefault = true, name = "hierMidExp")
  private int mHierarchyMidExp = 2;
  
  @GenerateFieldPort(hasDefault = true, name = "hierMidScale")
  private float mHierarchyMidScale = 0.6F;
  
  @GenerateFieldPort(hasDefault = true, name = "hierSmlExp")
  private int mHierarchySmlExp = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "hierSmlScale")
  private float mHierarchySmlScale = 0.5F;
  
  @GenerateFieldPort(hasDefault = true, name = "learningDoneListener")
  private LearningDoneListener mLearningDoneListener = null;
  
  @GenerateFieldPort(hasDefault = true, name = "learningDuration")
  private int mLearningDuration = 40;
  
  @GenerateFieldPort(hasDefault = true, name = "learningVerifyDuration")
  private int mLearningVerifyDuration = 10;
  
  private final boolean mLogVerbose = Log.isLoggable("BackDropperFilter", 2);
  
  @GenerateFieldPort(hasDefault = true, name = "lumScale")
  private float mLumScale = 0.4F;
  
  private GLFrame mMask;
  
  private GLFrame mMaskAverage;
  
  @GenerateFieldPort(hasDefault = true, name = "maskBg")
  private float mMaskBg = 0.65F;
  
  @GenerateFieldPort(hasDefault = true, name = "maskFg")
  private float mMaskFg = 0.95F;
  
  private MutableFrameFormat mMaskFormat;
  
  @GenerateFieldPort(hasDefault = true, name = "maskHeightExp")
  private int mMaskHeightExp = 8;
  
  private GLFrame[] mMaskVerify;
  
  private ShaderProgram mMaskVerifyProgram;
  
  @GenerateFieldPort(hasDefault = true, name = "maskWidthExp")
  private int mMaskWidthExp = 8;
  
  private MutableFrameFormat mMemoryFormat;
  
  @GenerateFieldPort(hasDefault = true, name = "mirrorBg")
  private boolean mMirrorBg = false;
  
  @GenerateFieldPort(hasDefault = true, name = "orientation")
  private int mOrientation = 0;
  
  private FrameFormat mOutputFormat;
  
  private boolean mPingPong;
  
  @GenerateFinalPort(hasDefault = true, name = "provideDebugOutputs")
  private boolean mProvideDebugOutputs = false;
  
  private int mPyramidDepth;
  
  private float mRelativeAspect;
  
  private boolean mStartLearning;
  
  private int mSubsampleLevel;
  
  @GenerateFieldPort(hasDefault = true, name = "useTheForce")
  private boolean mUseTheForce = false;
  
  @GenerateFieldPort(hasDefault = true, name = "maskVerifyRate")
  private float mVerifyRate = 0.25F;
  
  private GLFrame mVideoInput;
  
  @GenerateFieldPort(hasDefault = true, name = "whitebalanceblueChange")
  private float mWhiteBalanceBlueChange = 0.0F;
  
  @GenerateFieldPort(hasDefault = true, name = "whitebalanceredChange")
  private float mWhiteBalanceRedChange = 0.0F;
  
  private long startTime = -1L;
  
  static {
    mDebugOutputNames = new String[] { "debug1", "debug2" };
    mSharedUtilShader = "precision mediump float;\nuniform float fg_adapt_rate;\nuniform float bg_adapt_rate;\nconst mat4 coeff_yuv = mat4(0.299, -0.168736,  0.5,      0.000, 0.587, -0.331264, -0.418688, 0.000, 0.114,  0.5,      -0.081312, 0.000, 0.000,  0.5,       0.5,      1.000 );\nconst float dist_scale = 0.6;\nconst float inv_dist_scale = 1. / dist_scale;\nconst float var_scale=5.0;\nconst float inv_var_scale = 1. / var_scale;\nconst float min_variance = inv_var_scale *3.0/ 256.;\nconst float auto_wb_scale = 0.25;\n\nfloat gauss_dist_y(float y, float mean, float variance) {\n  float dist = (y - mean) * (y - mean) / variance;\n  return dist;\n}\nfloat gauss_dist_uv(vec2 uv, vec2 mean, vec2 variance) {\n  vec2 dist = (uv - mean) * (uv - mean) / variance;\n  return dist.r + dist.g;\n}\nfloat local_adapt_rate(float alpha) {\n  return mix(bg_adapt_rate, fg_adapt_rate, alpha);\n}\n\n";
  }
  
  public BackDropperFilter(String paramString) {
    super(paramString);
    paramString = SystemProperties.get("ro.media.effect.bgdropper.adj");
    if (paramString.length() > 0)
      try {
        this.mAcceptStddev += Float.parseFloat(paramString);
        if (this.mLogVerbose) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Adjusting accept threshold by ");
          stringBuilder.append(paramString);
          stringBuilder.append(", now ");
          stringBuilder.append(this.mAcceptStddev);
          Log.v("BackDropperFilter", stringBuilder.toString());
        } 
      } catch (NumberFormatException numberFormatException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Badly formatted property ro.media.effect.bgdropper.adj: ");
        stringBuilder.append(paramString);
        Log.e("BackDropperFilter", stringBuilder.toString());
      }  
  }
  
  private void allocateFrames(FrameFormat paramFrameFormat, FilterContext paramFilterContext) {
    if (!createMemoryFormat(paramFrameFormat))
      return; 
    if (this.mLogVerbose)
      Log.v("BackDropperFilter", "Allocating BackDropperFilter frames"); 
    int i = this.mMaskFormat.getSize();
    byte[] arrayOfByte1 = new byte[i];
    byte[] arrayOfByte2 = new byte[i];
    byte[] arrayOfByte3 = new byte[i];
    byte b;
    for (b = 0; b < i; b++) {
      arrayOfByte1[b] = (byte)Byte.MIN_VALUE;
      arrayOfByte2[b] = (byte)10;
      arrayOfByte3[b] = (byte)0;
    } 
    for (b = 0; b < 2; b++) {
      this.mBgMean[b] = (GLFrame)paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mMaskFormat);
      this.mBgMean[b].setData(arrayOfByte1, 0, i);
      this.mBgVariance[b] = (GLFrame)paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mMaskFormat);
      this.mBgVariance[b].setData(arrayOfByte2, 0, i);
      this.mMaskVerify[b] = (GLFrame)paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mMaskFormat);
      this.mMaskVerify[b].setData(arrayOfByte3, 0, i);
    } 
    if (this.mLogVerbose)
      Log.v("BackDropperFilter", "Done allocating texture for Mean and Variance objects!"); 
    this.mDistance = (GLFrame)paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mMaskFormat);
    this.mMask = (GLFrame)paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mMaskFormat);
    this.mAutoWB = (GLFrame)paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mAverageFormat);
    this.mVideoInput = (GLFrame)paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mMemoryFormat);
    this.mBgInput = (GLFrame)paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mMemoryFormat);
    this.mMaskAverage = (GLFrame)paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mAverageFormat);
    StringBuilder stringBuilder6 = new StringBuilder();
    stringBuilder6.append(mSharedUtilShader);
    stringBuilder6.append("uniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform sampler2D tex_sampler_2;\nuniform float subsample_level;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 fg_rgb = texture2D(tex_sampler_0, v_texcoord, subsample_level);\n  vec4 fg = coeff_yuv * vec4(fg_rgb.rgb, 1.);\n  vec4 mean = texture2D(tex_sampler_1, v_texcoord);\n  vec4 variance = inv_var_scale * texture2D(tex_sampler_2, v_texcoord);\n\n  float dist_y = gauss_dist_y(fg.r, mean.r, variance.r);\n  float dist_uv = gauss_dist_uv(fg.gb, mean.gb, variance.gb);\n  gl_FragColor = vec4(0.5*fg.rg, dist_scale*dist_y, dist_scale*dist_uv);\n}\n");
    ShaderProgram shaderProgram6 = new ShaderProgram(paramFilterContext, stringBuilder6.toString());
    this.mBgDistProgram = shaderProgram6;
    shaderProgram6.setHostValue("subsample_level", Float.valueOf(this.mSubsampleLevel));
    StringBuilder stringBuilder5 = new StringBuilder();
    stringBuilder5.append(mSharedUtilShader);
    stringBuilder5.append("uniform sampler2D tex_sampler_0;\nuniform float accept_variance;\nuniform vec2 yuv_weights;\nuniform float scale_lrg;\nuniform float scale_mid;\nuniform float scale_sml;\nuniform float exp_lrg;\nuniform float exp_mid;\nuniform float exp_sml;\nvarying vec2 v_texcoord;\nbool is_fg(vec2 dist_yc, float accept_variance) {\n  return ( dot(yuv_weights, dist_yc) >= accept_variance );\n}\nvoid main() {\n  vec4 dist_lrg_sc = texture2D(tex_sampler_0, v_texcoord, exp_lrg);\n  vec4 dist_mid_sc = texture2D(tex_sampler_0, v_texcoord, exp_mid);\n  vec4 dist_sml_sc = texture2D(tex_sampler_0, v_texcoord, exp_sml);\n  vec2 dist_lrg = inv_dist_scale * dist_lrg_sc.ba;\n  vec2 dist_mid = inv_dist_scale * dist_mid_sc.ba;\n  vec2 dist_sml = inv_dist_scale * dist_sml_sc.ba;\n  vec2 norm_dist = 0.75 * dist_sml / accept_variance;\n  bool is_fg_lrg = is_fg(dist_lrg, accept_variance * scale_lrg);\n  bool is_fg_mid = is_fg_lrg || is_fg(dist_mid, accept_variance * scale_mid);\n  float is_fg_sml =\n      float(is_fg_mid || is_fg(dist_sml, accept_variance * scale_sml));\n  float alpha = 0.5 * is_fg_sml + 0.3 * float(is_fg_mid) + 0.2 * float(is_fg_lrg);\n  gl_FragColor = vec4(alpha, norm_dist, is_fg_sml);\n}\n");
    ShaderProgram shaderProgram5 = new ShaderProgram(paramFilterContext, stringBuilder5.toString());
    this.mBgMaskProgram = shaderProgram5;
    float f1 = this.mAcceptStddev;
    shaderProgram5.setHostValue("accept_variance", Float.valueOf(f1 * f1));
    float f2 = this.mLumScale;
    f1 = this.mChromaScale;
    this.mBgMaskProgram.setHostValue("yuv_weights", new float[] { f2, f1 });
    this.mBgMaskProgram.setHostValue("scale_lrg", Float.valueOf(this.mHierarchyLrgScale));
    this.mBgMaskProgram.setHostValue("scale_mid", Float.valueOf(this.mHierarchyMidScale));
    this.mBgMaskProgram.setHostValue("scale_sml", Float.valueOf(this.mHierarchySmlScale));
    this.mBgMaskProgram.setHostValue("exp_lrg", Float.valueOf((this.mSubsampleLevel + this.mHierarchyLrgExp)));
    this.mBgMaskProgram.setHostValue("exp_mid", Float.valueOf((this.mSubsampleLevel + this.mHierarchyMidExp)));
    this.mBgMaskProgram.setHostValue("exp_sml", Float.valueOf((this.mSubsampleLevel + this.mHierarchySmlExp)));
    if (this.mUseTheForce) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(mSharedUtilShader);
      stringBuilder.append("uniform mat3 bg_fit_transform;\nuniform float mask_blend_bg;\nuniform float mask_blend_fg;\nuniform float exposure_change;\nuniform float whitebalancered_change;\nuniform float whitebalanceblue_change;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform sampler2D tex_sampler_2;\nuniform sampler2D tex_sampler_3;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec2 bg_texcoord = (bg_fit_transform * vec3(v_texcoord, 1.)).xy;\n  vec4 bg_rgb = texture2D(tex_sampler_1, bg_texcoord);\n  vec4 wb_auto_scale = texture2D(tex_sampler_3, v_texcoord) * exposure_change / auto_wb_scale;\n  vec4 wb_manual_scale = vec4(1. + whitebalancered_change, 1., 1. + whitebalanceblue_change, 1.);\n  vec4 fg_rgb = texture2D(tex_sampler_0, v_texcoord);\n  vec4 fg_adjusted = fg_rgb * wb_manual_scale * wb_auto_scale;\n  vec4 mask = texture2D(tex_sampler_2, v_texcoord, \n                      2.0);\n  float alpha = smoothstep(mask_blend_bg, mask_blend_fg, mask.a);\n  gl_FragColor = mix(bg_rgb, fg_adjusted, alpha);\n");
      stringBuilder.append("  vec4 ghost_rgb = (fg_adjusted * 0.7 + vec4(0.3,0.3,0.4,0.))*0.65 + \n                   0.35*bg_rgb;\n  float glow_start = 0.75 * mask_blend_bg; \n  float glow_max   = mask_blend_bg; \n  gl_FragColor = mask.a < glow_start ? bg_rgb : \n                 mask.a < glow_max ? mix(bg_rgb, vec4(0.9,0.9,1.0,1.0), \n                                     (mask.a - glow_start) / (glow_max - glow_start) ) : \n                 mask.a < mask_blend_fg ? mix(vec4(0.9,0.9,1.0,1.0), ghost_rgb, \n                                    (mask.a - glow_max) / (mask_blend_fg - glow_max) ) : \n                 ghost_rgb;\n}\n");
      this.mBgSubtractProgram = new ShaderProgram(paramFilterContext, stringBuilder.toString());
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(mSharedUtilShader);
      stringBuilder.append("uniform mat3 bg_fit_transform;\nuniform float mask_blend_bg;\nuniform float mask_blend_fg;\nuniform float exposure_change;\nuniform float whitebalancered_change;\nuniform float whitebalanceblue_change;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform sampler2D tex_sampler_2;\nuniform sampler2D tex_sampler_3;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec2 bg_texcoord = (bg_fit_transform * vec3(v_texcoord, 1.)).xy;\n  vec4 bg_rgb = texture2D(tex_sampler_1, bg_texcoord);\n  vec4 wb_auto_scale = texture2D(tex_sampler_3, v_texcoord) * exposure_change / auto_wb_scale;\n  vec4 wb_manual_scale = vec4(1. + whitebalancered_change, 1., 1. + whitebalanceblue_change, 1.);\n  vec4 fg_rgb = texture2D(tex_sampler_0, v_texcoord);\n  vec4 fg_adjusted = fg_rgb * wb_manual_scale * wb_auto_scale;\n  vec4 mask = texture2D(tex_sampler_2, v_texcoord, \n                      2.0);\n  float alpha = smoothstep(mask_blend_bg, mask_blend_fg, mask.a);\n  gl_FragColor = mix(bg_rgb, fg_adjusted, alpha);\n");
      stringBuilder.append("}\n");
      this.mBgSubtractProgram = new ShaderProgram(paramFilterContext, stringBuilder.toString());
    } 
    this.mBgSubtractProgram.setHostValue("bg_fit_transform", DEFAULT_BG_FIT_TRANSFORM);
    this.mBgSubtractProgram.setHostValue("mask_blend_bg", Float.valueOf(this.mMaskBg));
    this.mBgSubtractProgram.setHostValue("mask_blend_fg", Float.valueOf(this.mMaskFg));
    this.mBgSubtractProgram.setHostValue("exposure_change", Float.valueOf(this.mExposureChange));
    this.mBgSubtractProgram.setHostValue("whitebalanceblue_change", Float.valueOf(this.mWhiteBalanceBlueChange));
    this.mBgSubtractProgram.setHostValue("whitebalancered_change", Float.valueOf(this.mWhiteBalanceRedChange));
    StringBuilder stringBuilder4 = new StringBuilder();
    stringBuilder4.append(mSharedUtilShader);
    stringBuilder4.append("uniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform sampler2D tex_sampler_2;\nuniform float subsample_level;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 fg_rgb = texture2D(tex_sampler_0, v_texcoord, subsample_level);\n  vec4 fg = coeff_yuv * vec4(fg_rgb.rgb, 1.);\n  vec4 mean = texture2D(tex_sampler_1, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_2, v_texcoord, \n                      2.0);\n\n  float alpha = local_adapt_rate(mask.a);\n  vec4 new_mean = mix(mean, fg, alpha);\n  gl_FragColor = new_mean;\n}\n");
    ShaderProgram shaderProgram4 = new ShaderProgram(paramFilterContext, stringBuilder4.toString());
    this.mBgUpdateMeanProgram = shaderProgram4;
    shaderProgram4.setHostValue("subsample_level", Float.valueOf(this.mSubsampleLevel));
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(mSharedUtilShader);
    stringBuilder3.append("uniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform sampler2D tex_sampler_2;\nuniform sampler2D tex_sampler_3;\nuniform float subsample_level;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 fg_rgb = texture2D(tex_sampler_0, v_texcoord, subsample_level);\n  vec4 fg = coeff_yuv * vec4(fg_rgb.rgb, 1.);\n  vec4 mean = texture2D(tex_sampler_1, v_texcoord);\n  vec4 variance = inv_var_scale * texture2D(tex_sampler_2, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_3, v_texcoord, \n                      2.0);\n\n  float alpha = local_adapt_rate(mask.a);\n  vec4 cur_variance = (fg-mean)*(fg-mean);\n  vec4 new_variance = mix(variance, cur_variance, alpha);\n  new_variance = max(new_variance, vec4(min_variance));\n  gl_FragColor = var_scale * new_variance;\n}\n");
    ShaderProgram shaderProgram3 = new ShaderProgram(paramFilterContext, stringBuilder3.toString());
    this.mBgUpdateVarianceProgram = shaderProgram3;
    shaderProgram3.setHostValue("subsample_level", Float.valueOf(this.mSubsampleLevel));
    this.mCopyOutProgram = ShaderProgram.createIdentity(paramFilterContext);
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(mSharedUtilShader);
    stringBuilder2.append("uniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float pyramid_depth;\nuniform bool autowb_toggle;\nvarying vec2 v_texcoord;\nvoid main() {\n   vec4 mean_video = texture2D(tex_sampler_0, v_texcoord, pyramid_depth);\n   vec4 mean_bg = texture2D(tex_sampler_1, v_texcoord, pyramid_depth);\n   float green_normalizer = mean_video.g / mean_bg.g;\n   vec4 adjusted_value = vec4(mean_bg.r / mean_video.r * green_normalizer, 1., \n                         mean_bg.b / mean_video.b * green_normalizer, 1.) * auto_wb_scale; \n   gl_FragColor = autowb_toggle ? adjusted_value : vec4(auto_wb_scale);\n}\n");
    ShaderProgram shaderProgram2 = new ShaderProgram(paramFilterContext, stringBuilder2.toString());
    this.mAutomaticWhiteBalanceProgram = shaderProgram2;
    shaderProgram2.setHostValue("pyramid_depth", Float.valueOf(this.mPyramidDepth));
    this.mAutomaticWhiteBalanceProgram.setHostValue("autowb_toggle", Integer.valueOf(this.mAutoWBToggle));
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(mSharedUtilShader);
    stringBuilder1.append("uniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float verify_rate;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 lastmask = texture2D(tex_sampler_0, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_1, v_texcoord);\n  float newmask = mix(lastmask.a, mask.a, verify_rate);\n  gl_FragColor = vec4(0., 0., 0., newmask);\n}\n");
    ShaderProgram shaderProgram1 = new ShaderProgram(paramFilterContext, stringBuilder1.toString());
    this.mMaskVerifyProgram = shaderProgram1;
    shaderProgram1.setHostValue("verify_rate", Float.valueOf(this.mVerifyRate));
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Shader width set to ");
      stringBuilder.append(this.mMemoryFormat.getWidth());
      Log.v("BackDropperFilter", stringBuilder.toString());
    } 
    this.mRelativeAspect = 1.0F;
    this.mFrameCount = 0;
    this.mStartLearning = true;
  }
  
  private boolean createMemoryFormat(FrameFormat paramFrameFormat) {
    if (this.mMemoryFormat != null)
      return false; 
    if (paramFrameFormat.getWidth() != 0 && paramFrameFormat.getHeight() != 0) {
      this.mMaskFormat = paramFrameFormat.mutableCopy();
      int i = (int)Math.pow(2.0D, this.mMaskWidthExp);
      int j = (int)Math.pow(2.0D, this.mMaskHeightExp);
      this.mMaskFormat.setDimensions(i, j);
      this.mPyramidDepth = Math.max(this.mMaskWidthExp, this.mMaskHeightExp);
      this.mMemoryFormat = this.mMaskFormat.mutableCopy();
      int k = Math.max(this.mMaskWidthExp, pyramidLevel(paramFrameFormat.getWidth()));
      int m = Math.max(this.mMaskHeightExp, pyramidLevel(paramFrameFormat.getHeight()));
      this.mPyramidDepth = Math.max(k, m);
      int n = Math.max(i, (int)Math.pow(2.0D, k));
      int i1 = Math.max(j, (int)Math.pow(2.0D, m));
      this.mMemoryFormat.setDimensions(n, i1);
      this.mSubsampleLevel = this.mPyramidDepth - Math.max(this.mMaskWidthExp, this.mMaskHeightExp);
      if (this.mLogVerbose) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Mask frames size ");
        stringBuilder.append(i);
        stringBuilder.append(" x ");
        stringBuilder.append(j);
        Log.v("BackDropperFilter", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("Pyramid levels ");
        stringBuilder.append(k);
        stringBuilder.append(" x ");
        stringBuilder.append(m);
        Log.v("BackDropperFilter", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("Memory frames size ");
        stringBuilder.append(n);
        stringBuilder.append(" x ");
        stringBuilder.append(i1);
        Log.v("BackDropperFilter", stringBuilder.toString());
      } 
      MutableFrameFormat mutableFrameFormat = paramFrameFormat.mutableCopy();
      this.mAverageFormat = mutableFrameFormat;
      mutableFrameFormat.setDimensions(1, 1);
      return true;
    } 
    throw new RuntimeException("Attempting to process input frame with unknown size");
  }
  
  private int pyramidLevel(int paramInt) {
    return (int)Math.floor(Math.log10(paramInt) / Math.log10(2.0D)) - 1;
  }
  
  private void updateBgScaling(Frame paramFrame1, Frame paramFrame2, boolean paramBoolean) {
    float f = paramFrame1.getFormat().getWidth() / paramFrame1.getFormat().getHeight() / paramFrame2.getFormat().getWidth() / paramFrame2.getFormat().getHeight();
    if (f != this.mRelativeAspect || paramBoolean) {
      this.mRelativeAspect = f;
      float f1 = 0.0F;
      float f2 = 1.0F;
      float f3 = 0.0F;
      float f4 = 1.0F;
      int i = this.mBackgroundFitMode;
      if (i != 1) {
        if (i == 2)
          if (f > 1.0F) {
            f3 = 0.5F - 0.5F / f;
            f4 = 1.0F / f;
          } else {
            f1 = 0.5F - f * 0.5F;
            f2 = this.mRelativeAspect;
          }  
      } else if (f > 1.0F) {
        f1 = 0.5F - f * 0.5F;
        f2 = f * 1.0F;
      } else {
        f3 = 0.5F - 0.5F / f;
        f4 = 1.0F / f;
      } 
      float f5 = f1;
      float f6 = f2;
      f = f3;
      float f7 = f4;
      if (this.mMirrorBg) {
        if (this.mLogVerbose)
          Log.v("BackDropperFilter", "Mirroring the background!"); 
        i = this.mOrientation;
        if (i == 0 || i == 180) {
          f6 = -f2;
          f5 = 1.0F - f1;
          f7 = f4;
          f = f3;
        } else {
          f7 = -f4;
          f = 1.0F - f3;
          f5 = f1;
          f6 = f2;
        } 
      } 
      if (this.mLogVerbose) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bgTransform: xMin, yMin, xWidth, yWidth : ");
        stringBuilder.append(f5);
        stringBuilder.append(", ");
        stringBuilder.append(f);
        stringBuilder.append(", ");
        stringBuilder.append(f6);
        stringBuilder.append(", ");
        stringBuilder.append(f7);
        stringBuilder.append(", mRelAspRatio = ");
        stringBuilder.append(this.mRelativeAspect);
        Log.v("BackDropperFilter", stringBuilder.toString());
      } 
      this.mBgSubtractProgram.setHostValue("bg_fit_transform", new float[] { f6, 0.0F, 0.0F, 0.0F, f7, 0.0F, f5, f, 1.0F });
    } 
  }
  
  public void close(FilterContext paramFilterContext) {
    if (this.mMemoryFormat == null)
      return; 
    if (this.mLogVerbose)
      Log.v("BackDropperFilter", "Filter Closing!"); 
    for (byte b = 0; b < 2; b++) {
      this.mBgMean[b].release();
      this.mBgVariance[b].release();
      this.mMaskVerify[b].release();
    } 
    this.mDistance.release();
    this.mMask.release();
    this.mAutoWB.release();
    this.mVideoInput.release();
    this.mBgInput.release();
    this.mMaskAverage.release();
    this.mMemoryFormat = null;
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (paramString.equals("backgroundFitMode")) {
      this.mBackgroundFitModeChanged = true;
    } else {
      ShaderProgram shaderProgram;
      if (paramString.equals("acceptStddev")) {
        shaderProgram = this.mBgMaskProgram;
        float f = this.mAcceptStddev;
        shaderProgram.setHostValue("accept_variance", Float.valueOf(f * f));
      } else if (shaderProgram.equals("hierLrgScale")) {
        this.mBgMaskProgram.setHostValue("scale_lrg", Float.valueOf(this.mHierarchyLrgScale));
      } else if (shaderProgram.equals("hierMidScale")) {
        this.mBgMaskProgram.setHostValue("scale_mid", Float.valueOf(this.mHierarchyMidScale));
      } else if (shaderProgram.equals("hierSmlScale")) {
        this.mBgMaskProgram.setHostValue("scale_sml", Float.valueOf(this.mHierarchySmlScale));
      } else if (shaderProgram.equals("hierLrgExp")) {
        this.mBgMaskProgram.setHostValue("exp_lrg", Float.valueOf((this.mSubsampleLevel + this.mHierarchyLrgExp)));
      } else if (shaderProgram.equals("hierMidExp")) {
        this.mBgMaskProgram.setHostValue("exp_mid", Float.valueOf((this.mSubsampleLevel + this.mHierarchyMidExp)));
      } else if (shaderProgram.equals("hierSmlExp")) {
        this.mBgMaskProgram.setHostValue("exp_sml", Float.valueOf((this.mSubsampleLevel + this.mHierarchySmlExp)));
      } else {
        if (shaderProgram.equals("lumScale") || shaderProgram.equals("chromaScale")) {
          float f1 = this.mLumScale;
          float f2 = this.mChromaScale;
          this.mBgMaskProgram.setHostValue("yuv_weights", new float[] { f1, f2 });
          return;
        } 
        if (shaderProgram.equals("maskBg")) {
          this.mBgSubtractProgram.setHostValue("mask_blend_bg", Float.valueOf(this.mMaskBg));
        } else if (shaderProgram.equals("maskFg")) {
          this.mBgSubtractProgram.setHostValue("mask_blend_fg", Float.valueOf(this.mMaskFg));
        } else if (shaderProgram.equals("exposureChange")) {
          this.mBgSubtractProgram.setHostValue("exposure_change", Float.valueOf(this.mExposureChange));
        } else if (shaderProgram.equals("whitebalanceredChange")) {
          this.mBgSubtractProgram.setHostValue("whitebalancered_change", Float.valueOf(this.mWhiteBalanceRedChange));
        } else if (shaderProgram.equals("whitebalanceblueChange")) {
          this.mBgSubtractProgram.setHostValue("whitebalanceblue_change", Float.valueOf(this.mWhiteBalanceBlueChange));
        } else if (shaderProgram.equals("autowbToggle")) {
          this.mAutomaticWhiteBalanceProgram.setHostValue("autowb_toggle", Integer.valueOf(this.mAutoWBToggle));
        } 
      } 
    } 
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    MutableFrameFormat mutableFrameFormat = paramFrameFormat.mutableCopy();
    if (!Arrays.<String>asList(mOutputNames).contains(paramString))
      mutableFrameFormat.setDimensions(0, 0); 
    return (FrameFormat)mutableFrameFormat;
  }
  
  public void prepare(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v("BackDropperFilter", "Preparing BackDropperFilter!"); 
    this.mBgMean = new GLFrame[2];
    this.mBgVariance = new GLFrame[2];
    this.mMaskVerify = new GLFrame[2];
    this.copyShaderProgram = ShaderProgram.createIdentity(paramFilterContext);
  }
  
  public void process(FilterContext paramFilterContext) {
    LearningDoneListener learningDoneListener;
    Frame frame1 = pullInput("video");
    Frame frame2 = pullInput("background");
    allocateFrames(frame1.getFormat(), paramFilterContext);
    if (this.mStartLearning) {
      if (this.mLogVerbose)
        Log.v("BackDropperFilter", "Starting learning"); 
      this.mBgUpdateMeanProgram.setHostValue("bg_adapt_rate", Float.valueOf(this.mAdaptRateLearning));
      this.mBgUpdateMeanProgram.setHostValue("fg_adapt_rate", Float.valueOf(this.mAdaptRateLearning));
      this.mBgUpdateVarianceProgram.setHostValue("bg_adapt_rate", Float.valueOf(this.mAdaptRateLearning));
      this.mBgUpdateVarianceProgram.setHostValue("fg_adapt_rate", Float.valueOf(this.mAdaptRateLearning));
      this.mFrameCount = 0;
    } 
    boolean bool = this.mPingPong;
    int j = bool ^ true;
    this.mPingPong = bool ^ true;
    updateBgScaling(frame1, frame2, this.mBackgroundFitModeChanged);
    this.mBackgroundFitModeChanged = false;
    this.copyShaderProgram.process(frame1, (Frame)this.mVideoInput);
    this.copyShaderProgram.process(frame2, (Frame)this.mBgInput);
    this.mVideoInput.generateMipMap();
    this.mVideoInput.setTextureParameter(10241, 9985);
    this.mBgInput.generateMipMap();
    this.mBgInput.setTextureParameter(10241, 9985);
    if (this.mStartLearning) {
      this.copyShaderProgram.process((Frame)this.mVideoInput, (Frame)this.mBgMean[j]);
      this.mStartLearning = false;
    } 
    GLFrame gLFrame1 = this.mVideoInput;
    GLFrame gLFrame2 = this.mBgMean[j];
    GLFrame gLFrame3 = this.mBgVariance[j];
    ShaderProgram shaderProgram2 = this.mBgDistProgram;
    GLFrame gLFrame5 = this.mDistance;
    shaderProgram2.process(new Frame[] { (Frame)gLFrame1, (Frame)gLFrame2, (Frame)gLFrame3 }, (Frame)gLFrame5);
    this.mDistance.generateMipMap();
    this.mDistance.setTextureParameter(10241, 9985);
    this.mBgMaskProgram.process((Frame)this.mDistance, (Frame)this.mMask);
    this.mMask.generateMipMap();
    this.mMask.setTextureParameter(10241, 9985);
    GLFrame gLFrame4 = this.mVideoInput;
    gLFrame5 = this.mBgInput;
    ShaderProgram shaderProgram1 = this.mAutomaticWhiteBalanceProgram;
    gLFrame3 = this.mAutoWB;
    shaderProgram1.process(new Frame[] { (Frame)gLFrame4, (Frame)gLFrame5 }, (Frame)gLFrame3);
    if (this.mFrameCount <= this.mLearningDuration) {
      pushOutput("video", frame1);
      int k = this.mFrameCount;
      int m = this.mLearningDuration;
      int n = this.mLearningVerifyDuration;
      if (k == m - n) {
        this.copyShaderProgram.process((Frame)this.mMask, (Frame)this.mMaskVerify[bool]);
        this.mBgUpdateMeanProgram.setHostValue("bg_adapt_rate", Float.valueOf(this.mAdaptRateBg));
        this.mBgUpdateMeanProgram.setHostValue("fg_adapt_rate", Float.valueOf(this.mAdaptRateFg));
        this.mBgUpdateVarianceProgram.setHostValue("bg_adapt_rate", Float.valueOf(this.mAdaptRateBg));
        this.mBgUpdateVarianceProgram.setHostValue("fg_adapt_rate", Float.valueOf(this.mAdaptRateFg));
      } else if (k > m - n) {
        GLFrame[] arrayOfGLFrame = this.mMaskVerify;
        gLFrame4 = arrayOfGLFrame[j];
        gLFrame3 = this.mMask;
        ShaderProgram shaderProgram = this.mMaskVerifyProgram;
        GLFrame gLFrame = arrayOfGLFrame[bool];
        shaderProgram.process(new Frame[] { (Frame)gLFrame4, (Frame)gLFrame3 }, (Frame)gLFrame);
        this.mMaskVerify[bool].generateMipMap();
        this.mMaskVerify[bool].setTextureParameter(10241, 9985);
      } 
      if (this.mFrameCount == this.mLearningDuration) {
        this.copyShaderProgram.process((Frame)this.mMaskVerify[bool], (Frame)this.mMaskAverage);
        k = this.mMaskAverage.getData().array()[3] & 0xFF;
        if (this.mLogVerbose)
          Log.v("BackDropperFilter", String.format("Mask_average is %d, threshold is %d", new Object[] { Integer.valueOf(k), Integer.valueOf(20) })); 
        if (k >= 20) {
          this.mStartLearning = true;
        } else {
          if (this.mLogVerbose)
            Log.v("BackDropperFilter", "Learning done"); 
          learningDoneListener = this.mLearningDoneListener;
          if (learningDoneListener != null)
            learningDoneListener.onLearningDone(this); 
        } 
      } 
    } else {
      Frame frame = paramFilterContext.getFrameManager().newFrame(frame1.getFormat());
      gLFrame4 = this.mMask;
      gLFrame3 = this.mAutoWB;
      this.mBgSubtractProgram.process(new Frame[] { frame1, (Frame)learningDoneListener, (Frame)gLFrame4, (Frame)gLFrame3 }, frame);
      pushOutput("video", frame);
      frame.release();
    } 
    if (this.mFrameCount < this.mLearningDuration - this.mLearningVerifyDuration || this.mAdaptRateBg > 0.0D || this.mAdaptRateFg > 0.0D) {
      GLFrame gLFrame7 = this.mVideoInput;
      GLFrame[] arrayOfGLFrame2 = this.mBgMean;
      gLFrame3 = arrayOfGLFrame2[j];
      GLFrame gLFrame6 = this.mMask;
      ShaderProgram shaderProgram4 = this.mBgUpdateMeanProgram;
      GLFrame gLFrame10 = arrayOfGLFrame2[bool];
      shaderProgram4.process(new Frame[] { (Frame)gLFrame7, (Frame)gLFrame3, (Frame)gLFrame6 }, (Frame)gLFrame10);
      this.mBgMean[bool].generateMipMap();
      this.mBgMean[bool].setTextureParameter(10241, 9985);
      gLFrame6 = this.mVideoInput;
      GLFrame gLFrame9 = this.mBgMean[j];
      GLFrame[] arrayOfGLFrame1 = this.mBgVariance;
      gLFrame10 = arrayOfGLFrame1[j];
      gLFrame3 = this.mMask;
      ShaderProgram shaderProgram3 = this.mBgUpdateVarianceProgram;
      GLFrame gLFrame8 = arrayOfGLFrame1[bool];
      shaderProgram3.process(new Frame[] { (Frame)gLFrame6, (Frame)gLFrame9, (Frame)gLFrame10, (Frame)gLFrame3 }, (Frame)gLFrame8);
      this.mBgVariance[bool].generateMipMap();
      this.mBgVariance[bool].setTextureParameter(10241, 9985);
    } 
    if (this.mProvideDebugOutputs) {
      Frame frame = paramFilterContext.getFrameManager().newFrame(frame1.getFormat());
      this.mCopyOutProgram.process(frame1, frame);
      pushOutput("debug1", frame);
      frame.release();
      frame1 = paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mMemoryFormat);
      this.mCopyOutProgram.process((Frame)this.mMask, frame1);
      pushOutput("debug2", frame1);
      frame1.release();
    } 
    int i = this.mFrameCount + 1;
    this.mFrameCount = i;
    if (this.mLogVerbose && i % 30 == 0)
      if (this.startTime == -1L) {
        paramFilterContext.getGLEnvironment().activate();
        GLES20.glFinish();
        this.startTime = SystemClock.elapsedRealtime();
      } else {
        paramFilterContext.getGLEnvironment().activate();
        GLES20.glFinish();
        long l = SystemClock.elapsedRealtime();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Avg. frame duration: ");
        stringBuilder.append(String.format("%.2f", new Object[] { Double.valueOf((l - this.startTime) / 30.0D) }));
        stringBuilder.append(" ms. Avg. fps: ");
        stringBuilder.append(String.format("%.2f", new Object[] { Double.valueOf(1000.0D / (l - this.startTime) / 30.0D) }));
        Log.v("BackDropperFilter", stringBuilder.toString());
        this.startTime = l;
      }  
  }
  
  public void relearn() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield mStartLearning : Z
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
  
  public void setupPorts() {
    boolean bool = false;
    MutableFrameFormat mutableFrameFormat = ImageFormat.create(3, 0);
    String[] arrayOfString = mInputNames;
    int i = arrayOfString.length;
    byte b;
    for (b = 0; b < i; b++)
      addMaskedInputPort(arrayOfString[b], (FrameFormat)mutableFrameFormat); 
    arrayOfString = mOutputNames;
    i = arrayOfString.length;
    for (b = 0; b < i; b++)
      addOutputBasedOnInput(arrayOfString[b], "video"); 
    if (this.mProvideDebugOutputs) {
      arrayOfString = mDebugOutputNames;
      i = arrayOfString.length;
      for (b = bool; b < i; b++)
        addOutputBasedOnInput(arrayOfString[b], "video"); 
    } 
  }
  
  public static interface LearningDoneListener {
    void onLearningDone(BackDropperFilter param1BackDropperFilter);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/videoproc/BackDropperFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */