package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Insets;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import com.android.internal.R;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class GradientDrawable extends Drawable {
  private static final float DEFAULT_INNER_RADIUS_RATIO = 3.0F;
  
  private static final Orientation DEFAULT_ORIENTATION;
  
  private static final float DEFAULT_THICKNESS_RATIO = 9.0F;
  
  public static final int LINE = 2;
  
  public static final int LINEAR_GRADIENT = 0;
  
  public static final int OVAL = 1;
  
  public static final int RADIAL_GRADIENT = 1;
  
  private static final int RADIUS_TYPE_FRACTION = 1;
  
  private static final int RADIUS_TYPE_FRACTION_PARENT = 2;
  
  private static final int RADIUS_TYPE_PIXELS = 0;
  
  public static final int RECTANGLE = 0;
  
  public static final int RING = 3;
  
  public static final int SWEEP_GRADIENT = 2;
  
  public static boolean sWrapNegativeAngleMeasurements = true;
  
  private int mAlpha = 255;
  
  private BlendModeColorFilter mBlendModeColorFilter;
  
  private ColorFilter mColorFilter;
  
  private final Paint mFillPaint = new Paint(1);
  
  private boolean mGradientIsDirty;
  
  private float mGradientRadius;
  
  private GradientState mGradientState;
  
  private Paint mLayerPaint;
  
  private boolean mMutated;
  
  private Rect mPadding;
  
  private final Path mPath = new Path();
  
  private boolean mPathIsDirty = true;
  
  private final RectF mRect = new RectF();
  
  private Path mRingPath;
  
  private Paint mStrokePaint;
  
  static {
    DEFAULT_ORIENTATION = Orientation.TOP_BOTTOM;
  }
  
  public GradientDrawable() {
    this(new GradientState(DEFAULT_ORIENTATION, null), (Resources)null);
  }
  
  private GradientDrawable(GradientState paramGradientState, Resources paramResources) {
    this.mGradientState = paramGradientState;
    updateLocalState(paramResources);
  }
  
  public GradientDrawable(Orientation paramOrientation, int[] paramArrayOfint) {
    this(new GradientState(paramOrientation, paramArrayOfint), (Resources)null);
  }
  
  private void applyThemeChildElements(Resources.Theme paramTheme) {
    GradientState gradientState = this.mGradientState;
    if (gradientState.mAttrSize != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(gradientState.mAttrSize, R.styleable.GradientDrawableSize);
      updateGradientDrawableSize(typedArray);
      typedArray.recycle();
    } 
    if (gradientState.mAttrGradient != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(gradientState.mAttrGradient, R.styleable.GradientDrawableGradient);
      try {
        updateGradientDrawableGradient(paramTheme.getResources(), typedArray);
      } finally {
        typedArray.recycle();
      } 
    } 
    if (gradientState.mAttrSolid != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(gradientState.mAttrSolid, R.styleable.GradientDrawableSolid);
      updateGradientDrawableSolid(typedArray);
      typedArray.recycle();
    } 
    if (gradientState.mAttrStroke != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(gradientState.mAttrStroke, R.styleable.GradientDrawableStroke);
      updateGradientDrawableStroke(typedArray);
      typedArray.recycle();
    } 
    if (gradientState.mAttrCorners != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(gradientState.mAttrCorners, R.styleable.DrawableCorners);
      updateDrawableCorners(typedArray);
      typedArray.recycle();
    } 
    if (gradientState.mAttrPadding != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(gradientState.mAttrPadding, R.styleable.GradientDrawablePadding);
      updateGradientDrawablePadding(typedArray);
      typedArray.recycle();
    } 
  }
  
  private void buildPathIfDirty() {
    GradientState gradientState = this.mGradientState;
    if (this.mPathIsDirty) {
      ensureValidRect();
      this.mPath.reset();
      this.mPath.addRoundRect(this.mRect, gradientState.mRadiusArray, Path.Direction.CW);
      this.mPathIsDirty = false;
    } 
  }
  
  private Path buildRing(GradientState paramGradientState) {
    float f1;
    float f4;
    float f5;
    if (this.mRingPath != null && (!paramGradientState.mUseLevelForShape || !this.mPathIsDirty))
      return this.mRingPath; 
    this.mPathIsDirty = false;
    if (paramGradientState.mUseLevelForShape) {
      f1 = getLevel() * 360.0F / 10000.0F;
    } else {
      f1 = 360.0F;
    } 
    RectF rectF2 = new RectF(this.mRect);
    float f2 = rectF2.width() / 2.0F;
    float f3 = rectF2.height() / 2.0F;
    if (paramGradientState.mThickness != -1) {
      f4 = paramGradientState.mThickness;
    } else {
      f4 = rectF2.width() / paramGradientState.mThicknessRatio;
    } 
    if (paramGradientState.mInnerRadius != -1) {
      f5 = paramGradientState.mInnerRadius;
    } else {
      f5 = rectF2.width() / paramGradientState.mInnerRadiusRatio;
    } 
    RectF rectF1 = new RectF(rectF2);
    rectF1.inset(f2 - f5, f3 - f5);
    rectF2 = new RectF(rectF1);
    rectF2.inset(-f4, -f4);
    Path path = this.mRingPath;
    if (path == null) {
      this.mRingPath = new Path();
    } else {
      path.reset();
    } 
    path = this.mRingPath;
    if (f1 < 360.0F && f1 > -360.0F) {
      path.setFillType(Path.FillType.EVEN_ODD);
      path.moveTo(f2 + f5, f3);
      path.lineTo(f2 + f5 + f4, f3);
      path.arcTo(rectF2, 0.0F, f1, false);
      path.arcTo(rectF1, f1, -f1, false);
      path.close();
    } else {
      path.addOval(rectF2, Path.Direction.CW);
      path.addOval(rectF1, Path.Direction.CCW);
    } 
    return path;
  }
  
  private boolean ensureValidRect() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mGradientIsDirty : Z
    //   4: ifeq -> 1009
    //   7: aload_0
    //   8: iconst_0
    //   9: putfield mGradientIsDirty : Z
    //   12: aload_0
    //   13: invokevirtual getBounds : ()Landroid/graphics/Rect;
    //   16: astore_1
    //   17: fconst_0
    //   18: fstore_2
    //   19: aload_0
    //   20: getfield mStrokePaint : Landroid/graphics/Paint;
    //   23: astore_3
    //   24: aload_3
    //   25: ifnull -> 37
    //   28: aload_3
    //   29: invokevirtual getStrokeWidth : ()F
    //   32: ldc_w 0.5
    //   35: fmul
    //   36: fstore_2
    //   37: aload_0
    //   38: getfield mGradientState : Landroid/graphics/drawable/GradientDrawable$GradientState;
    //   41: astore #4
    //   43: aload_0
    //   44: getfield mRect : Landroid/graphics/RectF;
    //   47: aload_1
    //   48: getfield left : I
    //   51: i2f
    //   52: fload_2
    //   53: fadd
    //   54: aload_1
    //   55: getfield top : I
    //   58: i2f
    //   59: fload_2
    //   60: fadd
    //   61: aload_1
    //   62: getfield right : I
    //   65: i2f
    //   66: fload_2
    //   67: fsub
    //   68: aload_1
    //   69: getfield bottom : I
    //   72: i2f
    //   73: fload_2
    //   74: fsub
    //   75: invokevirtual set : (FFFF)V
    //   78: aload #4
    //   80: getfield mGradientColors : [I
    //   83: astore #5
    //   85: aload #5
    //   87: ifnull -> 1009
    //   90: aload_0
    //   91: getfield mRect : Landroid/graphics/RectF;
    //   94: astore_1
    //   95: aload #4
    //   97: getfield mGradient : I
    //   100: istore #6
    //   102: fconst_1
    //   103: fstore_2
    //   104: iload #6
    //   106: ifne -> 472
    //   109: aload #4
    //   111: getfield mUseLevel : Z
    //   114: ifeq -> 126
    //   117: aload_0
    //   118: invokevirtual getLevel : ()I
    //   121: i2f
    //   122: ldc 10000.0
    //   124: fdiv
    //   125: fstore_2
    //   126: getstatic android/graphics/drawable/GradientDrawable$1.$SwitchMap$android$graphics$drawable$GradientDrawable$Orientation : [I
    //   129: aload #4
    //   131: getfield mOrientation : Landroid/graphics/drawable/GradientDrawable$Orientation;
    //   134: invokevirtual ordinal : ()I
    //   137: iaload
    //   138: tableswitch default -> 180, 1 -> 410, 2 -> 376, 3 -> 346, 4 -> 312, 5 -> 278, 6 -> 244, 7 -> 214
    //   180: aload_1
    //   181: getfield left : F
    //   184: fstore #7
    //   186: aload_1
    //   187: getfield top : F
    //   190: fstore #8
    //   192: aload_1
    //   193: getfield right : F
    //   196: fload_2
    //   197: fmul
    //   198: fstore #9
    //   200: aload_1
    //   201: getfield bottom : F
    //   204: fload_2
    //   205: fmul
    //   206: fstore #10
    //   208: fload #7
    //   210: fstore_2
    //   211: goto -> 437
    //   214: aload_1
    //   215: getfield left : F
    //   218: fstore #7
    //   220: aload_1
    //   221: getfield top : F
    //   224: fstore #8
    //   226: aload_1
    //   227: getfield right : F
    //   230: fload_2
    //   231: fmul
    //   232: fstore #9
    //   234: fload #8
    //   236: fstore #10
    //   238: fload #7
    //   240: fstore_2
    //   241: goto -> 437
    //   244: aload_1
    //   245: getfield left : F
    //   248: fstore #7
    //   250: aload_1
    //   251: getfield bottom : F
    //   254: fstore #8
    //   256: aload_1
    //   257: getfield right : F
    //   260: fload_2
    //   261: fmul
    //   262: fstore #9
    //   264: aload_1
    //   265: getfield top : F
    //   268: fload_2
    //   269: fmul
    //   270: fstore #10
    //   272: fload #7
    //   274: fstore_2
    //   275: goto -> 437
    //   278: aload_1
    //   279: getfield left : F
    //   282: fstore #8
    //   284: aload_1
    //   285: getfield bottom : F
    //   288: fstore #7
    //   290: fload #8
    //   292: fstore #9
    //   294: aload_1
    //   295: getfield top : F
    //   298: fload_2
    //   299: fmul
    //   300: fstore #10
    //   302: fload #8
    //   304: fstore_2
    //   305: fload #7
    //   307: fstore #8
    //   309: goto -> 437
    //   312: aload_1
    //   313: getfield right : F
    //   316: fstore #7
    //   318: aload_1
    //   319: getfield bottom : F
    //   322: fstore #8
    //   324: aload_1
    //   325: getfield left : F
    //   328: fload_2
    //   329: fmul
    //   330: fstore #9
    //   332: aload_1
    //   333: getfield top : F
    //   336: fload_2
    //   337: fmul
    //   338: fstore #10
    //   340: fload #7
    //   342: fstore_2
    //   343: goto -> 437
    //   346: aload_1
    //   347: getfield right : F
    //   350: fstore #7
    //   352: aload_1
    //   353: getfield top : F
    //   356: fstore #8
    //   358: aload_1
    //   359: getfield left : F
    //   362: fload_2
    //   363: fmul
    //   364: fstore #9
    //   366: fload #8
    //   368: fstore #10
    //   370: fload #7
    //   372: fstore_2
    //   373: goto -> 437
    //   376: aload_1
    //   377: getfield right : F
    //   380: fstore #7
    //   382: aload_1
    //   383: getfield top : F
    //   386: fstore #8
    //   388: aload_1
    //   389: getfield left : F
    //   392: fload_2
    //   393: fmul
    //   394: fstore #9
    //   396: aload_1
    //   397: getfield bottom : F
    //   400: fload_2
    //   401: fmul
    //   402: fstore #10
    //   404: fload #7
    //   406: fstore_2
    //   407: goto -> 437
    //   410: aload_1
    //   411: getfield left : F
    //   414: fstore #7
    //   416: aload_1
    //   417: getfield top : F
    //   420: fstore #8
    //   422: fload #7
    //   424: fstore #9
    //   426: aload_1
    //   427: getfield bottom : F
    //   430: fload_2
    //   431: fmul
    //   432: fstore #10
    //   434: fload #7
    //   436: fstore_2
    //   437: aload_0
    //   438: getfield mFillPaint : Landroid/graphics/Paint;
    //   441: new android/graphics/LinearGradient
    //   444: dup
    //   445: fload_2
    //   446: fload #8
    //   448: fload #9
    //   450: fload #10
    //   452: aload #5
    //   454: aload #4
    //   456: getfield mPositions : [F
    //   459: getstatic android/graphics/Shader$TileMode.CLAMP : Landroid/graphics/Shader$TileMode;
    //   462: invokespecial <init> : (FFFF[I[FLandroid/graphics/Shader$TileMode;)V
    //   465: invokevirtual setShader : (Landroid/graphics/Shader;)Landroid/graphics/Shader;
    //   468: pop
    //   469: goto -> 991
    //   472: aload #4
    //   474: getfield mGradient : I
    //   477: iconst_1
    //   478: if_icmpne -> 731
    //   481: aload_1
    //   482: getfield left : F
    //   485: fstore #10
    //   487: aload_1
    //   488: getfield right : F
    //   491: fstore #11
    //   493: aload_1
    //   494: getfield left : F
    //   497: fstore #12
    //   499: aload #4
    //   501: getfield mCenterX : F
    //   504: fstore #13
    //   506: aload_1
    //   507: getfield top : F
    //   510: fstore #14
    //   512: aload_1
    //   513: getfield bottom : F
    //   516: fstore #15
    //   518: aload_1
    //   519: getfield top : F
    //   522: fstore #7
    //   524: aload #4
    //   526: getfield mCenterY : F
    //   529: fstore #16
    //   531: aload #4
    //   533: getfield mGradientRadius : F
    //   536: fstore #9
    //   538: aload #4
    //   540: getfield mGradientRadiusType : I
    //   543: iconst_1
    //   544: if_icmpne -> 609
    //   547: aload #4
    //   549: getfield mWidth : I
    //   552: iflt -> 565
    //   555: aload #4
    //   557: getfield mWidth : I
    //   560: i2f
    //   561: fstore_2
    //   562: goto -> 570
    //   565: aload_1
    //   566: invokevirtual width : ()F
    //   569: fstore_2
    //   570: aload #4
    //   572: getfield mHeight : I
    //   575: iflt -> 589
    //   578: aload #4
    //   580: getfield mHeight : I
    //   583: i2f
    //   584: fstore #8
    //   586: goto -> 595
    //   589: aload_1
    //   590: invokevirtual height : ()F
    //   593: fstore #8
    //   595: fload #9
    //   597: fload_2
    //   598: fload #8
    //   600: invokestatic min : (FF)F
    //   603: fmul
    //   604: fstore #8
    //   606: goto -> 641
    //   609: fload #9
    //   611: fstore #8
    //   613: aload #4
    //   615: getfield mGradientRadiusType : I
    //   618: iconst_2
    //   619: if_icmpne -> 641
    //   622: fload #9
    //   624: aload_1
    //   625: invokevirtual width : ()F
    //   628: aload_1
    //   629: invokevirtual height : ()F
    //   632: invokestatic min : (FF)F
    //   635: fmul
    //   636: fstore #8
    //   638: goto -> 641
    //   641: fload #8
    //   643: fstore_2
    //   644: aload #4
    //   646: getfield mUseLevel : Z
    //   649: ifeq -> 664
    //   652: fload #8
    //   654: aload_0
    //   655: invokevirtual getLevel : ()I
    //   658: i2f
    //   659: ldc 10000.0
    //   661: fdiv
    //   662: fmul
    //   663: fstore_2
    //   664: aload_0
    //   665: fload_2
    //   666: putfield mGradientRadius : F
    //   669: fload_2
    //   670: fstore #8
    //   672: fload_2
    //   673: fconst_0
    //   674: fcmpg
    //   675: ifgt -> 683
    //   678: ldc_w 0.001
    //   681: fstore #8
    //   683: aload_0
    //   684: getfield mFillPaint : Landroid/graphics/Paint;
    //   687: new android/graphics/RadialGradient
    //   690: dup
    //   691: fload #10
    //   693: fload #11
    //   695: fload #12
    //   697: fsub
    //   698: fload #13
    //   700: fmul
    //   701: fadd
    //   702: fload #14
    //   704: fload #15
    //   706: fload #7
    //   708: fsub
    //   709: fload #16
    //   711: fmul
    //   712: fadd
    //   713: fload #8
    //   715: aload #5
    //   717: aconst_null
    //   718: getstatic android/graphics/Shader$TileMode.CLAMP : Landroid/graphics/Shader$TileMode;
    //   721: invokespecial <init> : (FFF[I[FLandroid/graphics/Shader$TileMode;)V
    //   724: invokevirtual setShader : (Landroid/graphics/Shader;)Landroid/graphics/Shader;
    //   727: pop
    //   728: goto -> 991
    //   731: aload #4
    //   733: getfield mGradient : I
    //   736: iconst_2
    //   737: if_icmpne -> 991
    //   740: aload_1
    //   741: getfield left : F
    //   744: fstore #12
    //   746: aload_1
    //   747: getfield right : F
    //   750: fstore #15
    //   752: aload_1
    //   753: getfield left : F
    //   756: fstore #11
    //   758: aload #4
    //   760: getfield mCenterX : F
    //   763: fstore #14
    //   765: aload_1
    //   766: getfield top : F
    //   769: fstore_2
    //   770: aload_1
    //   771: getfield bottom : F
    //   774: fstore #9
    //   776: aload_1
    //   777: getfield top : F
    //   780: fstore #10
    //   782: aload #4
    //   784: getfield mCenterY : F
    //   787: fstore #16
    //   789: aload #5
    //   791: astore_1
    //   792: aconst_null
    //   793: astore_3
    //   794: aload #4
    //   796: getfield mUseLevel : Z
    //   799: ifeq -> 953
    //   802: aload #4
    //   804: getfield mTempColors : [I
    //   807: astore_3
    //   808: aload #5
    //   810: arraylength
    //   811: istore #17
    //   813: aload_3
    //   814: ifnull -> 828
    //   817: aload_3
    //   818: astore_1
    //   819: aload_3
    //   820: arraylength
    //   821: iload #17
    //   823: iconst_1
    //   824: iadd
    //   825: if_icmpeq -> 841
    //   828: iload #17
    //   830: iconst_1
    //   831: iadd
    //   832: newarray int
    //   834: astore_1
    //   835: aload #4
    //   837: aload_1
    //   838: putfield mTempColors : [I
    //   841: aload #5
    //   843: iconst_0
    //   844: aload_1
    //   845: iconst_0
    //   846: iload #17
    //   848: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   851: aload_1
    //   852: iload #17
    //   854: aload #5
    //   856: iload #17
    //   858: iconst_1
    //   859: isub
    //   860: iaload
    //   861: iastore
    //   862: aload #4
    //   864: getfield mTempPositions : [F
    //   867: astore #5
    //   869: fconst_1
    //   870: iload #17
    //   872: iconst_1
    //   873: isub
    //   874: i2f
    //   875: fdiv
    //   876: fstore #7
    //   878: aload #5
    //   880: ifnull -> 896
    //   883: aload #5
    //   885: astore_3
    //   886: aload #5
    //   888: arraylength
    //   889: iload #17
    //   891: iconst_1
    //   892: iadd
    //   893: if_icmpeq -> 909
    //   896: iload #17
    //   898: iconst_1
    //   899: iadd
    //   900: newarray float
    //   902: astore_3
    //   903: aload #4
    //   905: aload_3
    //   906: putfield mTempPositions : [F
    //   909: aload_0
    //   910: invokevirtual getLevel : ()I
    //   913: i2f
    //   914: ldc 10000.0
    //   916: fdiv
    //   917: fstore #8
    //   919: iconst_0
    //   920: istore #6
    //   922: iload #6
    //   924: iload #17
    //   926: if_icmpge -> 948
    //   929: aload_3
    //   930: iload #6
    //   932: iload #6
    //   934: i2f
    //   935: fload #7
    //   937: fmul
    //   938: fload #8
    //   940: fmul
    //   941: fastore
    //   942: iinc #6, 1
    //   945: goto -> 922
    //   948: aload_3
    //   949: iload #17
    //   951: fconst_1
    //   952: fastore
    //   953: aload_0
    //   954: getfield mFillPaint : Landroid/graphics/Paint;
    //   957: new android/graphics/SweepGradient
    //   960: dup
    //   961: fload #12
    //   963: fload #15
    //   965: fload #11
    //   967: fsub
    //   968: fload #14
    //   970: fmul
    //   971: fadd
    //   972: fload_2
    //   973: fload #9
    //   975: fload #10
    //   977: fsub
    //   978: fload #16
    //   980: fmul
    //   981: fadd
    //   982: aload_1
    //   983: aload_3
    //   984: invokespecial <init> : (FF[I[F)V
    //   987: invokevirtual setShader : (Landroid/graphics/Shader;)Landroid/graphics/Shader;
    //   990: pop
    //   991: aload #4
    //   993: getfield mSolidColors : Landroid/content/res/ColorStateList;
    //   996: ifnonnull -> 1009
    //   999: aload_0
    //   1000: getfield mFillPaint : Landroid/graphics/Paint;
    //   1003: ldc_w -16777216
    //   1006: invokevirtual setColor : (I)V
    //   1009: aload_0
    //   1010: getfield mRect : Landroid/graphics/RectF;
    //   1013: invokevirtual isEmpty : ()Z
    //   1016: iconst_1
    //   1017: ixor
    //   1018: ireturn
  }
  
  private static float getFloatOrFraction(TypedArray paramTypedArray, int paramInt, float paramFloat) {
    TypedValue typedValue = paramTypedArray.peekValue(paramInt);
    if (typedValue != null) {
      if (typedValue.type == 6) {
        paramInt = 1;
      } else {
        paramInt = 0;
      } 
      if (paramInt != 0) {
        paramFloat = typedValue.getFraction(1.0F, 1.0F);
      } else {
        paramFloat = typedValue.getFloat();
      } 
    } 
    return paramFloat;
  }
  
  private void inflateChildElements(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth() + 1;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          TypedArray typedArray;
          if (j != 2 || k > i)
            continue; 
          String str = paramXmlPullParser.getName();
          if (str.equals("size")) {
            typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientDrawableSize);
            updateGradientDrawableSize(typedArray);
            typedArray.recycle();
            continue;
          } 
          if (typedArray.equals("gradient")) {
            typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientDrawableGradient);
            updateGradientDrawableGradient(paramResources, typedArray);
            typedArray.recycle();
            continue;
          } 
          if (typedArray.equals("solid")) {
            typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientDrawableSolid);
            updateGradientDrawableSolid(typedArray);
            typedArray.recycle();
            continue;
          } 
          if (typedArray.equals("stroke")) {
            typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientDrawableStroke);
            updateGradientDrawableStroke(typedArray);
            typedArray.recycle();
            continue;
          } 
          if (typedArray.equals("corners")) {
            typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.DrawableCorners);
            updateDrawableCorners(typedArray);
            typedArray.recycle();
            continue;
          } 
          if (typedArray.equals("padding")) {
            typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientDrawablePadding);
            updateGradientDrawablePadding(typedArray);
            typedArray.recycle();
            continue;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Bad element under <shape>: ");
          stringBuilder.append((String)typedArray);
          Log.w("drawable", stringBuilder.toString());
          continue;
        } 
      } 
      break;
    } 
  }
  
  static boolean isOpaque(int paramInt) {
    boolean bool;
    if ((paramInt >> 24 & 0xFF) == 255) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isOpaqueForState() {
    if (this.mGradientState.mStrokeWidth >= 0) {
      Paint paint = this.mStrokePaint;
      if (paint != null && !isOpaque(paint.getColor()))
        return false; 
    } 
    return !(this.mGradientState.mGradientColors == null && !isOpaque(this.mFillPaint.getColor()));
  }
  
  private int modulateAlpha(int paramInt) {
    int i = this.mAlpha;
    return paramInt * (i + (i >> 7)) >> 8;
  }
  
  private void setStrokeInternal(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) {
    if (this.mStrokePaint == null) {
      Paint paint = new Paint(1);
      this.mStrokePaint = paint;
      paint.setStyle(Paint.Style.STROKE);
    } 
    this.mStrokePaint.setStrokeWidth(paramInt1);
    this.mStrokePaint.setColor(paramInt2);
    DashPathEffect dashPathEffect = null;
    if (paramFloat1 > 0.0F)
      dashPathEffect = new DashPathEffect(new float[] { paramFloat1, paramFloat2 }, 0.0F); 
    this.mStrokePaint.setPathEffect((PathEffect)dashPathEffect);
    this.mGradientIsDirty = true;
    invalidateSelf();
  }
  
  private void updateDrawableCorners(TypedArray paramTypedArray) {
    GradientState gradientState = this.mGradientState;
    gradientState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    gradientState.mAttrCorners = paramTypedArray.extractThemeAttrs();
    int i = paramTypedArray.getDimensionPixelSize(0, (int)gradientState.mRadius);
    setCornerRadius(i);
    int j = paramTypedArray.getDimensionPixelSize(1, i);
    int k = paramTypedArray.getDimensionPixelSize(2, i);
    int m = paramTypedArray.getDimensionPixelSize(3, i);
    int n = paramTypedArray.getDimensionPixelSize(4, i);
    if (j != i || k != i || m != i || n != i)
      setCornerRadii(new float[] { j, j, k, k, n, n, m, m }); 
  }
  
  private void updateGradientDrawableGradient(Resources paramResources, TypedArray paramTypedArray) {
    boolean bool1;
    GradientState gradientState = this.mGradientState;
    gradientState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    gradientState.mAttrGradient = paramTypedArray.extractThemeAttrs();
    gradientState.mCenterX = getFloatOrFraction(paramTypedArray, 5, gradientState.mCenterX);
    gradientState.mCenterY = getFloatOrFraction(paramTypedArray, 6, gradientState.mCenterY);
    gradientState.mUseLevel = paramTypedArray.getBoolean(2, gradientState.mUseLevel);
    gradientState.mGradient = paramTypedArray.getInt(4, gradientState.mGradient);
    if (gradientState.mGradientColors != null) {
      i = 1;
    } else {
      i = 0;
    } 
    boolean bool = gradientState.hasCenterColor();
    if (i) {
      bool1 = gradientState.mGradientColors[0];
    } else {
      bool1 = false;
    } 
    if (bool) {
      j = gradientState.mGradientColors[1];
    } else {
      j = 0;
    } 
    if (gradientState.hasCenterColor()) {
      i = gradientState.mGradientColors[2];
    } else if (i != 0) {
      i = gradientState.mGradientColors[1];
    } else {
      i = 0;
    } 
    int k = paramTypedArray.getColor(0, bool1);
    if (paramTypedArray.hasValue(8) || bool) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    int j = paramTypedArray.getColor(8, j);
    int i = paramTypedArray.getColor(1, i);
    if (bool1) {
      float f;
      gradientState.mGradientColors = new int[3];
      gradientState.mGradientColors[0] = k;
      gradientState.mGradientColors[1] = j;
      gradientState.mGradientColors[2] = i;
      gradientState.mPositions = new float[3];
      gradientState.mPositions[0] = 0.0F;
      float[] arrayOfFloat = gradientState.mPositions;
      if (gradientState.mCenterX != 0.5F) {
        f = gradientState.mCenterX;
      } else {
        f = gradientState.mCenterY;
      } 
      arrayOfFloat[1] = f;
      gradientState.mPositions[2] = 1.0F;
    } else {
      gradientState.mGradientColors = new int[2];
      gradientState.mGradientColors[0] = k;
      gradientState.mGradientColors[1] = i;
    } 
    i = (int)paramTypedArray.getFloat(3, gradientState.mAngle);
    if (sWrapNegativeAngleMeasurements) {
      gradientState.mAngle = (i % 360 + 360) % 360;
    } else {
      gradientState.mAngle = i % 360;
    } 
    if (gradientState.mAngle >= 0) {
      i = gradientState.mAngle;
      if (i != 0) {
        if (i != 45) {
          if (i != 90) {
            if (i != 135) {
              if (i != 180) {
                if (i != 225) {
                  if (i != 270) {
                    if (i == 315)
                      gradientState.mOrientation = Orientation.TL_BR; 
                  } else {
                    gradientState.mOrientation = Orientation.TOP_BOTTOM;
                  } 
                } else {
                  gradientState.mOrientation = Orientation.TR_BL;
                } 
              } else {
                gradientState.mOrientation = Orientation.RIGHT_LEFT;
              } 
            } else {
              gradientState.mOrientation = Orientation.BR_TL;
            } 
          } else {
            gradientState.mOrientation = Orientation.BOTTOM_TOP;
          } 
        } else {
          gradientState.mOrientation = Orientation.BL_TR;
        } 
      } else {
        gradientState.mOrientation = Orientation.LEFT_RIGHT;
      } 
    } else {
      gradientState.mOrientation = DEFAULT_ORIENTATION;
    } 
    TypedValue typedValue = paramTypedArray.peekValue(7);
    if (typedValue != null) {
      float f;
      if (typedValue.type == 6) {
        f = typedValue.getFraction(1.0F, 1.0F);
        if ((typedValue.data >> 0 & 0xF) == 1) {
          i = 2;
        } else {
          i = 1;
        } 
      } else if (typedValue.type == 5) {
        f = typedValue.getDimension(paramResources.getDisplayMetrics());
        i = 0;
      } else {
        f = typedValue.getFloat();
        i = 0;
      } 
      gradientState.mGradientRadius = f;
      gradientState.mGradientRadiusType = i;
    } 
  }
  
  private void updateGradientDrawablePadding(TypedArray paramTypedArray) {
    GradientState gradientState = this.mGradientState;
    gradientState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    gradientState.mAttrPadding = paramTypedArray.extractThemeAttrs();
    if (gradientState.mPadding == null)
      gradientState.mPadding = new Rect(); 
    Rect rect = gradientState.mPadding;
    rect.set(paramTypedArray.getDimensionPixelOffset(0, rect.left), paramTypedArray.getDimensionPixelOffset(1, rect.top), paramTypedArray.getDimensionPixelOffset(2, rect.right), paramTypedArray.getDimensionPixelOffset(3, rect.bottom));
    this.mPadding = rect;
  }
  
  private void updateGradientDrawableSize(TypedArray paramTypedArray) {
    GradientState gradientState = this.mGradientState;
    gradientState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    gradientState.mAttrSize = paramTypedArray.extractThemeAttrs();
    gradientState.mWidth = paramTypedArray.getDimensionPixelSize(1, gradientState.mWidth);
    gradientState.mHeight = paramTypedArray.getDimensionPixelSize(0, gradientState.mHeight);
  }
  
  private void updateGradientDrawableSolid(TypedArray paramTypedArray) {
    GradientState gradientState = this.mGradientState;
    gradientState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    gradientState.mAttrSolid = paramTypedArray.extractThemeAttrs();
    ColorStateList colorStateList = paramTypedArray.getColorStateList(0);
    if (colorStateList != null)
      setColor(colorStateList); 
  }
  
  private void updateGradientDrawableStroke(TypedArray paramTypedArray) {
    GradientState gradientState = this.mGradientState;
    gradientState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    gradientState.mAttrStroke = paramTypedArray.extractThemeAttrs();
    int i = paramTypedArray.getDimensionPixelSize(0, Math.max(0, gradientState.mStrokeWidth));
    float f = paramTypedArray.getDimension(2, gradientState.mStrokeDashWidth);
    ColorStateList colorStateList1 = paramTypedArray.getColorStateList(1);
    ColorStateList colorStateList2 = colorStateList1;
    if (colorStateList1 == null)
      colorStateList2 = gradientState.mStrokeColors; 
    if (f != 0.0F) {
      setStroke(i, colorStateList2, f, paramTypedArray.getDimension(3, gradientState.mStrokeDashGap));
    } else {
      setStroke(i, colorStateList2);
    } 
  }
  
  private void updateLocalState(Resources paramResources) {
    GradientState gradientState = this.mGradientState;
    if (gradientState.mSolidColors != null) {
      int[] arrayOfInt = getState();
      int i = gradientState.mSolidColors.getColorForState(arrayOfInt, 0);
      this.mFillPaint.setColor(i);
    } else if (gradientState.mGradientColors == null) {
      this.mFillPaint.setColor(0);
    } else {
      this.mFillPaint.setColor(-16777216);
    } 
    this.mPadding = gradientState.mPadding;
    if (gradientState.mStrokeWidth >= 0) {
      Paint paint = new Paint(1);
      this.mStrokePaint = paint;
      paint.setStyle(Paint.Style.STROKE);
      this.mStrokePaint.setStrokeWidth(gradientState.mStrokeWidth);
      if (gradientState.mStrokeColors != null) {
        int[] arrayOfInt = getState();
        int i = gradientState.mStrokeColors.getColorForState(arrayOfInt, 0);
        this.mStrokePaint.setColor(i);
      } 
      if (gradientState.mStrokeDashWidth != 0.0F) {
        DashPathEffect dashPathEffect = new DashPathEffect(new float[] { gradientState.mStrokeDashWidth, gradientState.mStrokeDashGap }, 0.0F);
        this.mStrokePaint.setPathEffect((PathEffect)dashPathEffect);
      } 
    } 
    this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, gradientState.mTint, gradientState.mBlendMode);
    this.mGradientIsDirty = true;
    gradientState.computeOpacity();
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    GradientState gradientState = this.mGradientState;
    gradientState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    gradientState.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    gradientState.mShape = paramTypedArray.getInt(3, gradientState.mShape);
    gradientState.mDither = paramTypedArray.getBoolean(0, gradientState.mDither);
    if (gradientState.mShape == 3) {
      gradientState.mInnerRadius = paramTypedArray.getDimensionPixelSize(7, gradientState.mInnerRadius);
      if (gradientState.mInnerRadius == -1)
        gradientState.mInnerRadiusRatio = paramTypedArray.getFloat(4, gradientState.mInnerRadiusRatio); 
      gradientState.mThickness = paramTypedArray.getDimensionPixelSize(8, gradientState.mThickness);
      if (gradientState.mThickness == -1)
        gradientState.mThicknessRatio = paramTypedArray.getFloat(5, gradientState.mThicknessRatio); 
      gradientState.mUseLevelForShape = paramTypedArray.getBoolean(6, gradientState.mUseLevelForShape);
    } 
    int i = paramTypedArray.getInt(9, -1);
    if (i != -1)
      gradientState.mBlendMode = Drawable.parseBlendMode(i, BlendMode.SRC_IN); 
    ColorStateList colorStateList = paramTypedArray.getColorStateList(1);
    if (colorStateList != null)
      gradientState.mTint = colorStateList; 
    gradientState.mOpticalInsets = Insets.of(paramTypedArray.getDimensionPixelSize(10, gradientState.mOpticalInsets.left), paramTypedArray.getDimensionPixelSize(11, gradientState.mOpticalInsets.top), paramTypedArray.getDimensionPixelSize(12, gradientState.mOpticalInsets.right), paramTypedArray.getDimensionPixelSize(13, gradientState.mOpticalInsets.bottom));
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    GradientState gradientState = this.mGradientState;
    if (gradientState == null)
      return; 
    gradientState.setDensity(Drawable.resolveDensity(paramTheme.getResources(), 0));
    if (gradientState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(gradientState.mThemeAttrs, R.styleable.GradientDrawable);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    } 
    if (gradientState.mTint != null && gradientState.mTint.canApplyTheme())
      gradientState.mTint = gradientState.mTint.obtainForTheme(paramTheme); 
    if (gradientState.mSolidColors != null && gradientState.mSolidColors.canApplyTheme())
      gradientState.mSolidColors = gradientState.mSolidColors.obtainForTheme(paramTheme); 
    if (gradientState.mStrokeColors != null && gradientState.mStrokeColors.canApplyTheme())
      gradientState.mStrokeColors = gradientState.mStrokeColors.obtainForTheme(paramTheme); 
    applyThemeChildElements(paramTheme);
    updateLocalState(paramTheme.getResources());
  }
  
  public boolean canApplyTheme() {
    boolean bool;
    GradientState gradientState = this.mGradientState;
    if ((gradientState != null && gradientState.canApplyTheme()) || super.canApplyTheme()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  public void draw(Canvas paramCanvas) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial ensureValidRect : ()Z
    //   4: ifne -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield mFillPaint : Landroid/graphics/Paint;
    //   12: invokevirtual getAlpha : ()I
    //   15: istore_2
    //   16: aload_0
    //   17: getfield mStrokePaint : Landroid/graphics/Paint;
    //   20: astore_3
    //   21: iconst_0
    //   22: istore #4
    //   24: aload_3
    //   25: ifnull -> 37
    //   28: aload_3
    //   29: invokevirtual getAlpha : ()I
    //   32: istore #5
    //   34: goto -> 40
    //   37: iconst_0
    //   38: istore #5
    //   40: aload_0
    //   41: iload_2
    //   42: invokespecial modulateAlpha : (I)I
    //   45: istore #6
    //   47: aload_0
    //   48: iload #5
    //   50: invokespecial modulateAlpha : (I)I
    //   53: istore #7
    //   55: iload #7
    //   57: ifle -> 84
    //   60: aload_0
    //   61: getfield mStrokePaint : Landroid/graphics/Paint;
    //   64: astore_3
    //   65: aload_3
    //   66: ifnull -> 84
    //   69: aload_3
    //   70: invokevirtual getStrokeWidth : ()F
    //   73: fconst_0
    //   74: fcmpl
    //   75: ifle -> 84
    //   78: iconst_1
    //   79: istore #8
    //   81: goto -> 87
    //   84: iconst_0
    //   85: istore #8
    //   87: iload #6
    //   89: ifle -> 98
    //   92: iconst_1
    //   93: istore #9
    //   95: goto -> 101
    //   98: iconst_0
    //   99: istore #9
    //   101: aload_0
    //   102: getfield mGradientState : Landroid/graphics/drawable/GradientDrawable$GradientState;
    //   105: astore #10
    //   107: aload_0
    //   108: getfield mColorFilter : Landroid/graphics/ColorFilter;
    //   111: astore_3
    //   112: aload_3
    //   113: ifnull -> 119
    //   116: goto -> 124
    //   119: aload_0
    //   120: getfield mBlendModeColorFilter : Landroid/graphics/BlendModeColorFilter;
    //   123: astore_3
    //   124: iload #4
    //   126: istore #11
    //   128: iload #8
    //   130: ifeq -> 188
    //   133: iload #4
    //   135: istore #11
    //   137: iload #9
    //   139: ifeq -> 188
    //   142: iload #4
    //   144: istore #11
    //   146: aload #10
    //   148: getfield mShape : I
    //   151: iconst_2
    //   152: if_icmpeq -> 188
    //   155: iload #4
    //   157: istore #11
    //   159: iload #7
    //   161: sipush #255
    //   164: if_icmpge -> 188
    //   167: aload_0
    //   168: getfield mAlpha : I
    //   171: sipush #255
    //   174: if_icmplt -> 185
    //   177: iload #4
    //   179: istore #11
    //   181: aload_3
    //   182: ifnull -> 188
    //   185: iconst_1
    //   186: istore #11
    //   188: iload #11
    //   190: ifeq -> 322
    //   193: aload_0
    //   194: getfield mLayerPaint : Landroid/graphics/Paint;
    //   197: ifnonnull -> 211
    //   200: aload_0
    //   201: new android/graphics/Paint
    //   204: dup
    //   205: invokespecial <init> : ()V
    //   208: putfield mLayerPaint : Landroid/graphics/Paint;
    //   211: aload_0
    //   212: getfield mLayerPaint : Landroid/graphics/Paint;
    //   215: aload #10
    //   217: getfield mDither : Z
    //   220: invokevirtual setDither : (Z)V
    //   223: aload_0
    //   224: getfield mLayerPaint : Landroid/graphics/Paint;
    //   227: aload_0
    //   228: getfield mAlpha : I
    //   231: invokevirtual setAlpha : (I)V
    //   234: aload_0
    //   235: getfield mLayerPaint : Landroid/graphics/Paint;
    //   238: aload_3
    //   239: invokevirtual setColorFilter : (Landroid/graphics/ColorFilter;)Landroid/graphics/ColorFilter;
    //   242: pop
    //   243: aload_0
    //   244: getfield mStrokePaint : Landroid/graphics/Paint;
    //   247: invokevirtual getStrokeWidth : ()F
    //   250: fstore #12
    //   252: aload_1
    //   253: aload_0
    //   254: getfield mRect : Landroid/graphics/RectF;
    //   257: getfield left : F
    //   260: fload #12
    //   262: fsub
    //   263: aload_0
    //   264: getfield mRect : Landroid/graphics/RectF;
    //   267: getfield top : F
    //   270: fload #12
    //   272: fsub
    //   273: aload_0
    //   274: getfield mRect : Landroid/graphics/RectF;
    //   277: getfield right : F
    //   280: fload #12
    //   282: fadd
    //   283: aload_0
    //   284: getfield mRect : Landroid/graphics/RectF;
    //   287: getfield bottom : F
    //   290: fload #12
    //   292: fadd
    //   293: aload_0
    //   294: getfield mLayerPaint : Landroid/graphics/Paint;
    //   297: invokevirtual saveLayer : (FFFFLandroid/graphics/Paint;)I
    //   300: pop
    //   301: aload_0
    //   302: getfield mFillPaint : Landroid/graphics/Paint;
    //   305: aconst_null
    //   306: invokevirtual setColorFilter : (Landroid/graphics/ColorFilter;)Landroid/graphics/ColorFilter;
    //   309: pop
    //   310: aload_0
    //   311: getfield mStrokePaint : Landroid/graphics/Paint;
    //   314: aconst_null
    //   315: invokevirtual setColorFilter : (Landroid/graphics/ColorFilter;)Landroid/graphics/ColorFilter;
    //   318: pop
    //   319: goto -> 423
    //   322: aload_3
    //   323: astore #13
    //   325: aload #10
    //   327: astore #14
    //   329: aload_0
    //   330: getfield mFillPaint : Landroid/graphics/Paint;
    //   333: iload #6
    //   335: invokevirtual setAlpha : (I)V
    //   338: aload_0
    //   339: getfield mFillPaint : Landroid/graphics/Paint;
    //   342: aload #14
    //   344: getfield mDither : Z
    //   347: invokevirtual setDither : (Z)V
    //   350: aload_0
    //   351: getfield mFillPaint : Landroid/graphics/Paint;
    //   354: aload #13
    //   356: invokevirtual setColorFilter : (Landroid/graphics/ColorFilter;)Landroid/graphics/ColorFilter;
    //   359: pop
    //   360: aload #13
    //   362: ifnull -> 387
    //   365: aload #14
    //   367: getfield mSolidColors : Landroid/content/res/ColorStateList;
    //   370: ifnonnull -> 387
    //   373: aload_0
    //   374: getfield mFillPaint : Landroid/graphics/Paint;
    //   377: aload_0
    //   378: getfield mAlpha : I
    //   381: bipush #24
    //   383: ishl
    //   384: invokevirtual setColor : (I)V
    //   387: iload #8
    //   389: ifeq -> 423
    //   392: aload_0
    //   393: getfield mStrokePaint : Landroid/graphics/Paint;
    //   396: iload #7
    //   398: invokevirtual setAlpha : (I)V
    //   401: aload_0
    //   402: getfield mStrokePaint : Landroid/graphics/Paint;
    //   405: aload #14
    //   407: getfield mDither : Z
    //   410: invokevirtual setDither : (Z)V
    //   413: aload_0
    //   414: getfield mStrokePaint : Landroid/graphics/Paint;
    //   417: aload #13
    //   419: invokevirtual setColorFilter : (Landroid/graphics/ColorFilter;)Landroid/graphics/ColorFilter;
    //   422: pop
    //   423: aload #10
    //   425: getfield mShape : I
    //   428: istore #9
    //   430: iload #9
    //   432: ifeq -> 563
    //   435: iload #9
    //   437: iconst_1
    //   438: if_icmpeq -> 531
    //   441: iload #9
    //   443: iconst_2
    //   444: if_icmpeq -> 489
    //   447: iload #9
    //   449: iconst_3
    //   450: if_icmpeq -> 456
    //   453: goto -> 741
    //   456: aload_0
    //   457: aload #10
    //   459: invokespecial buildRing : (Landroid/graphics/drawable/GradientDrawable$GradientState;)Landroid/graphics/Path;
    //   462: astore_3
    //   463: aload_1
    //   464: aload_3
    //   465: aload_0
    //   466: getfield mFillPaint : Landroid/graphics/Paint;
    //   469: invokevirtual drawPath : (Landroid/graphics/Path;Landroid/graphics/Paint;)V
    //   472: iload #8
    //   474: ifeq -> 741
    //   477: aload_1
    //   478: aload_3
    //   479: aload_0
    //   480: getfield mStrokePaint : Landroid/graphics/Paint;
    //   483: invokevirtual drawPath : (Landroid/graphics/Path;Landroid/graphics/Paint;)V
    //   486: goto -> 741
    //   489: aload_0
    //   490: getfield mRect : Landroid/graphics/RectF;
    //   493: astore_3
    //   494: aload_3
    //   495: invokevirtual centerY : ()F
    //   498: fstore #12
    //   500: iload #8
    //   502: ifeq -> 528
    //   505: aload_1
    //   506: aload_3
    //   507: getfield left : F
    //   510: fload #12
    //   512: aload_3
    //   513: getfield right : F
    //   516: fload #12
    //   518: aload_0
    //   519: getfield mStrokePaint : Landroid/graphics/Paint;
    //   522: invokevirtual drawLine : (FFFFLandroid/graphics/Paint;)V
    //   525: goto -> 741
    //   528: goto -> 741
    //   531: aload_1
    //   532: aload_0
    //   533: getfield mRect : Landroid/graphics/RectF;
    //   536: aload_0
    //   537: getfield mFillPaint : Landroid/graphics/Paint;
    //   540: invokevirtual drawOval : (Landroid/graphics/RectF;Landroid/graphics/Paint;)V
    //   543: iload #8
    //   545: ifeq -> 741
    //   548: aload_1
    //   549: aload_0
    //   550: getfield mRect : Landroid/graphics/RectF;
    //   553: aload_0
    //   554: getfield mStrokePaint : Landroid/graphics/Paint;
    //   557: invokevirtual drawOval : (Landroid/graphics/RectF;Landroid/graphics/Paint;)V
    //   560: goto -> 741
    //   563: aload #10
    //   565: getfield mRadiusArray : [F
    //   568: ifnull -> 607
    //   571: aload_0
    //   572: invokespecial buildPathIfDirty : ()V
    //   575: aload_1
    //   576: aload_0
    //   577: getfield mPath : Landroid/graphics/Path;
    //   580: aload_0
    //   581: getfield mFillPaint : Landroid/graphics/Paint;
    //   584: invokevirtual drawPath : (Landroid/graphics/Path;Landroid/graphics/Paint;)V
    //   587: iload #8
    //   589: ifeq -> 741
    //   592: aload_1
    //   593: aload_0
    //   594: getfield mPath : Landroid/graphics/Path;
    //   597: aload_0
    //   598: getfield mStrokePaint : Landroid/graphics/Paint;
    //   601: invokevirtual drawPath : (Landroid/graphics/Path;Landroid/graphics/Paint;)V
    //   604: goto -> 741
    //   607: aload #10
    //   609: getfield mRadius : F
    //   612: fconst_0
    //   613: fcmpl
    //   614: ifle -> 688
    //   617: aload #10
    //   619: getfield mRadius : F
    //   622: aload_0
    //   623: getfield mRect : Landroid/graphics/RectF;
    //   626: invokevirtual width : ()F
    //   629: aload_0
    //   630: getfield mRect : Landroid/graphics/RectF;
    //   633: invokevirtual height : ()F
    //   636: invokestatic min : (FF)F
    //   639: ldc_w 0.5
    //   642: fmul
    //   643: invokestatic min : (FF)F
    //   646: fstore #12
    //   648: aload_1
    //   649: aload_0
    //   650: getfield mRect : Landroid/graphics/RectF;
    //   653: fload #12
    //   655: fload #12
    //   657: aload_0
    //   658: getfield mFillPaint : Landroid/graphics/Paint;
    //   661: invokevirtual drawRoundRect : (Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V
    //   664: iload #8
    //   666: ifeq -> 685
    //   669: aload_1
    //   670: aload_0
    //   671: getfield mRect : Landroid/graphics/RectF;
    //   674: fload #12
    //   676: fload #12
    //   678: aload_0
    //   679: getfield mStrokePaint : Landroid/graphics/Paint;
    //   682: invokevirtual drawRoundRect : (Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V
    //   685: goto -> 741
    //   688: aload_0
    //   689: getfield mFillPaint : Landroid/graphics/Paint;
    //   692: invokevirtual getColor : ()I
    //   695: ifne -> 712
    //   698: aload_3
    //   699: ifnonnull -> 712
    //   702: aload_0
    //   703: getfield mFillPaint : Landroid/graphics/Paint;
    //   706: invokevirtual getShader : ()Landroid/graphics/Shader;
    //   709: ifnull -> 724
    //   712: aload_1
    //   713: aload_0
    //   714: getfield mRect : Landroid/graphics/RectF;
    //   717: aload_0
    //   718: getfield mFillPaint : Landroid/graphics/Paint;
    //   721: invokevirtual drawRect : (Landroid/graphics/RectF;Landroid/graphics/Paint;)V
    //   724: iload #8
    //   726: ifeq -> 741
    //   729: aload_1
    //   730: aload_0
    //   731: getfield mRect : Landroid/graphics/RectF;
    //   734: aload_0
    //   735: getfield mStrokePaint : Landroid/graphics/Paint;
    //   738: invokevirtual drawRect : (Landroid/graphics/RectF;Landroid/graphics/Paint;)V
    //   741: iload #11
    //   743: ifeq -> 753
    //   746: aload_1
    //   747: invokevirtual restore : ()V
    //   750: goto -> 775
    //   753: aload_0
    //   754: getfield mFillPaint : Landroid/graphics/Paint;
    //   757: iload_2
    //   758: invokevirtual setAlpha : (I)V
    //   761: iload #8
    //   763: ifeq -> 775
    //   766: aload_0
    //   767: getfield mStrokePaint : Landroid/graphics/Paint;
    //   770: iload #5
    //   772: invokevirtual setAlpha : (I)V
    //   775: return
  }
  
  public int getAlpha() {
    return this.mAlpha;
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mGradientState.getChangingConfigurations();
  }
  
  public ColorStateList getColor() {
    return this.mGradientState.mSolidColors;
  }
  
  public ColorFilter getColorFilter() {
    return this.mColorFilter;
  }
  
  public int[] getColors() {
    int[] arrayOfInt;
    if (this.mGradientState.mGradientColors == null) {
      arrayOfInt = null;
    } else {
      arrayOfInt = (int[])this.mGradientState.mGradientColors.clone();
    } 
    return arrayOfInt;
  }
  
  public Drawable.ConstantState getConstantState() {
    this.mGradientState.mChangingConfigurations = getChangingConfigurations();
    return this.mGradientState;
  }
  
  public float[] getCornerRadii() {
    return (float[])this.mGradientState.mRadiusArray.clone();
  }
  
  public float getCornerRadius() {
    return this.mGradientState.mRadius;
  }
  
  public float getGradientCenterX() {
    return this.mGradientState.mCenterX;
  }
  
  public float getGradientCenterY() {
    return this.mGradientState.mCenterY;
  }
  
  public float getGradientRadius() {
    if (this.mGradientState.mGradient != 1)
      return 0.0F; 
    ensureValidRect();
    return this.mGradientRadius;
  }
  
  public int getGradientType() {
    return this.mGradientState.mGradient;
  }
  
  public int getInnerRadius() {
    return this.mGradientState.mInnerRadius;
  }
  
  public float getInnerRadiusRatio() {
    return this.mGradientState.mInnerRadiusRatio;
  }
  
  public int getIntrinsicHeight() {
    return this.mGradientState.mHeight;
  }
  
  public int getIntrinsicWidth() {
    return this.mGradientState.mWidth;
  }
  
  public int getOpacity() {
    byte b;
    if (this.mAlpha == 255 && this.mGradientState.mOpaqueOverBounds && isOpaqueForState()) {
      b = -1;
    } else {
      b = -3;
    } 
    return b;
  }
  
  public Insets getOpticalInsets() {
    return this.mGradientState.mOpticalInsets;
  }
  
  public Orientation getOrientation() {
    return this.mGradientState.mOrientation;
  }
  
  public void getOutline(Outline paramOutline) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mGradientState : Landroid/graphics/drawable/GradientDrawable$GradientState;
    //   4: astore_2
    //   5: aload_0
    //   6: invokevirtual getBounds : ()Landroid/graphics/Rect;
    //   9: astore_3
    //   10: aload_2
    //   11: getfield mOpaqueOverShape : Z
    //   14: ifeq -> 59
    //   17: aload_0
    //   18: getfield mGradientState : Landroid/graphics/drawable/GradientDrawable$GradientState;
    //   21: getfield mStrokeWidth : I
    //   24: ifle -> 53
    //   27: aload_0
    //   28: getfield mStrokePaint : Landroid/graphics/Paint;
    //   31: astore #4
    //   33: aload #4
    //   35: ifnull -> 53
    //   38: aload #4
    //   40: invokevirtual getAlpha : ()I
    //   43: aload_0
    //   44: getfield mFillPaint : Landroid/graphics/Paint;
    //   47: invokevirtual getAlpha : ()I
    //   50: if_icmpne -> 59
    //   53: iconst_1
    //   54: istore #5
    //   56: goto -> 62
    //   59: iconst_0
    //   60: istore #5
    //   62: iload #5
    //   64: ifeq -> 88
    //   67: aload_0
    //   68: aload_0
    //   69: getfield mFillPaint : Landroid/graphics/Paint;
    //   72: invokevirtual getAlpha : ()I
    //   75: invokespecial modulateAlpha : (I)I
    //   78: i2f
    //   79: ldc_w 255.0
    //   82: fdiv
    //   83: fstore #6
    //   85: goto -> 91
    //   88: fconst_0
    //   89: fstore #6
    //   91: aload_1
    //   92: fload #6
    //   94: invokevirtual setAlpha : (F)V
    //   97: aload_2
    //   98: getfield mShape : I
    //   101: istore #5
    //   103: iload #5
    //   105: ifeq -> 205
    //   108: iload #5
    //   110: iconst_1
    //   111: if_icmpeq -> 199
    //   114: iload #5
    //   116: iconst_2
    //   117: if_icmpeq -> 121
    //   120: return
    //   121: aload_0
    //   122: getfield mStrokePaint : Landroid/graphics/Paint;
    //   125: astore #4
    //   127: aload #4
    //   129: ifnonnull -> 140
    //   132: ldc_w 1.0E-4
    //   135: fstore #6
    //   137: goto -> 151
    //   140: aload #4
    //   142: invokevirtual getStrokeWidth : ()F
    //   145: ldc_w 0.5
    //   148: fmul
    //   149: fstore #6
    //   151: aload_3
    //   152: invokevirtual centerY : ()I
    //   155: i2f
    //   156: fstore #7
    //   158: fload #7
    //   160: fload #6
    //   162: fsub
    //   163: f2d
    //   164: invokestatic floor : (D)D
    //   167: d2i
    //   168: istore #5
    //   170: fload #7
    //   172: fload #6
    //   174: fadd
    //   175: f2d
    //   176: invokestatic ceil : (D)D
    //   179: d2i
    //   180: istore #8
    //   182: aload_1
    //   183: aload_3
    //   184: getfield left : I
    //   187: iload #5
    //   189: aload_3
    //   190: getfield right : I
    //   193: iload #8
    //   195: invokevirtual setRect : (IIII)V
    //   198: return
    //   199: aload_1
    //   200: aload_3
    //   201: invokevirtual setOval : (Landroid/graphics/Rect;)V
    //   204: return
    //   205: aload_2
    //   206: getfield mRadiusArray : [F
    //   209: ifnull -> 225
    //   212: aload_0
    //   213: invokespecial buildPathIfDirty : ()V
    //   216: aload_1
    //   217: aload_0
    //   218: getfield mPath : Landroid/graphics/Path;
    //   221: invokevirtual setPath : (Landroid/graphics/Path;)V
    //   224: return
    //   225: fconst_0
    //   226: fstore #6
    //   228: aload_2
    //   229: getfield mRadius : F
    //   232: fconst_0
    //   233: fcmpl
    //   234: ifle -> 262
    //   237: aload_2
    //   238: getfield mRadius : F
    //   241: aload_3
    //   242: invokevirtual width : ()I
    //   245: aload_3
    //   246: invokevirtual height : ()I
    //   249: invokestatic min : (II)I
    //   252: i2f
    //   253: ldc_w 0.5
    //   256: fmul
    //   257: invokestatic min : (FF)F
    //   260: fstore #6
    //   262: aload_1
    //   263: aload_3
    //   264: fload #6
    //   266: invokevirtual setRoundRect : (Landroid/graphics/Rect;F)V
    //   269: return
  }
  
  public boolean getPadding(Rect paramRect) {
    Rect rect = this.mPadding;
    if (rect != null) {
      paramRect.set(rect);
      return true;
    } 
    return super.getPadding(paramRect);
  }
  
  public int getShape() {
    return this.mGradientState.mShape;
  }
  
  public int getThickness() {
    return this.mGradientState.mThickness;
  }
  
  public float getThicknessRatio() {
    return this.mGradientState.mThicknessRatio;
  }
  
  public boolean getUseLevel() {
    return this.mGradientState.mUseLevel;
  }
  
  public boolean hasFocusStateSpecified() {
    boolean bool;
    GradientState gradientState = this.mGradientState;
    if ((gradientState.mSolidColors != null && gradientState.mSolidColors.hasFocusStateSpecified()) || (gradientState.mStrokeColors != null && gradientState.mStrokeColors.hasFocusStateSpecified()) || (gradientState.mTint != null && gradientState.mTint.hasFocusStateSpecified())) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    this.mGradientState.setDensity(Drawable.resolveDensity(paramResources, 0));
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.GradientDrawable);
    updateStateFromTypedArray(typedArray);
    typedArray.recycle();
    inflateChildElements(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    updateLocalState(paramResources);
  }
  
  public boolean isStateful() {
    GradientState gradientState = this.mGradientState;
    return (super.isStateful() || (gradientState.mSolidColors != null && gradientState.mSolidColors.isStateful()) || (gradientState.mStrokeColors != null && gradientState.mStrokeColors.isStateful()) || (gradientState.mTint != null && gradientState.mTint.isStateful()));
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mGradientState = new GradientState(this.mGradientState, null);
      updateLocalState((Resources)null);
      this.mMutated = true;
    } 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    super.onBoundsChange(paramRect);
    this.mRingPath = null;
    this.mPathIsDirty = true;
    this.mGradientIsDirty = true;
  }
  
  protected boolean onLevelChange(int paramInt) {
    super.onLevelChange(paramInt);
    this.mGradientIsDirty = true;
    this.mPathIsDirty = true;
    invalidateSelf();
    return true;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    boolean bool1 = false;
    GradientState gradientState = this.mGradientState;
    ColorStateList colorStateList = gradientState.mSolidColors;
    boolean bool2 = bool1;
    if (colorStateList != null) {
      int i = colorStateList.getColorForState(paramArrayOfint, 0);
      bool2 = bool1;
      if (this.mFillPaint.getColor() != i) {
        this.mFillPaint.setColor(i);
        bool2 = true;
      } 
    } 
    Paint paint = this.mStrokePaint;
    bool1 = bool2;
    if (paint != null) {
      ColorStateList colorStateList1 = gradientState.mStrokeColors;
      bool1 = bool2;
      if (colorStateList1 != null) {
        int i = colorStateList1.getColorForState(paramArrayOfint, 0);
        bool1 = bool2;
        if (paint.getColor() != i) {
          paint.setColor(i);
          bool1 = true;
        } 
      } 
    } 
    bool2 = bool1;
    if (gradientState.mTint != null) {
      bool2 = bool1;
      if (gradientState.mBlendMode != null) {
        this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, gradientState.mTint, gradientState.mBlendMode);
        bool2 = true;
      } 
    } 
    if (bool2) {
      invalidateSelf();
      return true;
    } 
    return false;
  }
  
  public void setAlpha(int paramInt) {
    if (paramInt != this.mAlpha) {
      this.mAlpha = paramInt;
      invalidateSelf();
    } 
  }
  
  public void setAntiAlias(boolean paramBoolean) {
    this.mFillPaint.setAntiAlias(paramBoolean);
  }
  
  public void setColor(int paramInt) {
    this.mGradientState.setSolidColors(ColorStateList.valueOf(paramInt));
    this.mFillPaint.setColor(paramInt);
    invalidateSelf();
  }
  
  public void setColor(ColorStateList paramColorStateList) {
    if (paramColorStateList == null) {
      setColor(0);
    } else {
      int i = paramColorStateList.getColorForState(getState(), 0);
      this.mGradientState.setSolidColors(paramColorStateList);
      this.mFillPaint.setColor(i);
      invalidateSelf();
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    if (paramColorFilter != this.mColorFilter) {
      this.mColorFilter = paramColorFilter;
      invalidateSelf();
    } 
  }
  
  public void setColors(int[] paramArrayOfint) {
    setColors(paramArrayOfint, (float[])null);
  }
  
  public void setColors(int[] paramArrayOfint, float[] paramArrayOffloat) {
    this.mGradientState.setGradientColors(paramArrayOfint);
    this.mGradientState.mPositions = paramArrayOffloat;
    this.mGradientIsDirty = true;
    invalidateSelf();
  }
  
  public void setCornerRadii(float[] paramArrayOffloat) {
    this.mGradientState.setCornerRadii(paramArrayOffloat);
    this.mPathIsDirty = true;
    invalidateSelf();
  }
  
  public void setCornerRadius(float paramFloat) {
    this.mGradientState.setCornerRadius(paramFloat);
    this.mPathIsDirty = true;
    invalidateSelf();
  }
  
  public void setDither(boolean paramBoolean) {
    if (paramBoolean != this.mGradientState.mDither) {
      this.mGradientState.mDither = paramBoolean;
      invalidateSelf();
    } 
  }
  
  public void setGradientCenter(float paramFloat1, float paramFloat2) {
    this.mGradientState.setGradientCenter(paramFloat1, paramFloat2);
    this.mGradientIsDirty = true;
    invalidateSelf();
  }
  
  public void setGradientRadius(float paramFloat) {
    this.mGradientState.setGradientRadius(paramFloat, 0);
    this.mGradientIsDirty = true;
    invalidateSelf();
  }
  
  public void setGradientType(int paramInt) {
    this.mGradientState.setGradientType(paramInt);
    this.mGradientIsDirty = true;
    invalidateSelf();
  }
  
  public void setInnerRadius(int paramInt) {
    this.mGradientState.mInnerRadius = paramInt;
    this.mPathIsDirty = true;
    invalidateSelf();
  }
  
  public void setInnerRadiusRatio(float paramFloat) {
    if (paramFloat > 0.0F) {
      this.mGradientState.mInnerRadiusRatio = paramFloat;
      this.mPathIsDirty = true;
      invalidateSelf();
      return;
    } 
    throw new IllegalArgumentException("Ratio must be greater than zero");
  }
  
  public void setOrientation(Orientation paramOrientation) {
    this.mGradientState.mOrientation = paramOrientation;
    this.mGradientIsDirty = true;
    invalidateSelf();
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.mGradientState.mPadding == null)
      this.mGradientState.mPadding = new Rect(); 
    this.mGradientState.mPadding.set(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mPadding = this.mGradientState.mPadding;
    invalidateSelf();
  }
  
  public void setShape(int paramInt) {
    this.mRingPath = null;
    this.mPathIsDirty = true;
    this.mGradientState.setShape(paramInt);
    invalidateSelf();
  }
  
  public void setSize(int paramInt1, int paramInt2) {
    this.mGradientState.setSize(paramInt1, paramInt2);
    this.mPathIsDirty = true;
    invalidateSelf();
  }
  
  public void setStroke(int paramInt1, int paramInt2) {
    setStroke(paramInt1, paramInt2, 0.0F, 0.0F);
  }
  
  public void setStroke(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) {
    this.mGradientState.setStroke(paramInt1, ColorStateList.valueOf(paramInt2), paramFloat1, paramFloat2);
    setStrokeInternal(paramInt1, paramInt2, paramFloat1, paramFloat2);
  }
  
  public void setStroke(int paramInt, ColorStateList paramColorStateList) {
    setStroke(paramInt, paramColorStateList, 0.0F, 0.0F);
  }
  
  public void setStroke(int paramInt, ColorStateList paramColorStateList, float paramFloat1, float paramFloat2) {
    int i;
    this.mGradientState.setStroke(paramInt, paramColorStateList, paramFloat1, paramFloat2);
    if (paramColorStateList == null) {
      i = 0;
    } else {
      i = paramColorStateList.getColorForState(getState(), 0);
    } 
    setStrokeInternal(paramInt, i, paramFloat1, paramFloat2);
  }
  
  public void setThickness(int paramInt) {
    this.mGradientState.mThickness = paramInt;
    this.mPathIsDirty = true;
    invalidateSelf();
  }
  
  public void setThicknessRatio(float paramFloat) {
    if (paramFloat > 0.0F) {
      this.mGradientState.mThicknessRatio = paramFloat;
      this.mPathIsDirty = true;
      invalidateSelf();
      return;
    } 
    throw new IllegalArgumentException("Ratio must be greater than zero");
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    this.mGradientState.mBlendMode = paramBlendMode;
    this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, this.mGradientState.mTint, paramBlendMode);
    invalidateSelf();
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    this.mGradientState.mTint = paramColorStateList;
    this.mBlendModeColorFilter = updateBlendModeFilter(this.mBlendModeColorFilter, paramColorStateList, this.mGradientState.mBlendMode);
    invalidateSelf();
  }
  
  public void setUseLevel(boolean paramBoolean) {
    this.mGradientState.mUseLevel = paramBoolean;
    this.mGradientIsDirty = true;
    invalidateSelf();
  }
  
  public void setXfermode(Xfermode paramXfermode) {
    super.setXfermode(paramXfermode);
    this.mFillPaint.setXfermode(paramXfermode);
  }
  
  static final class GradientState extends Drawable.ConstantState {
    public int mAngle = 0;
    
    int[] mAttrCorners;
    
    int[] mAttrGradient;
    
    int[] mAttrPadding;
    
    int[] mAttrSize;
    
    int[] mAttrSolid;
    
    int[] mAttrStroke;
    
    BlendMode mBlendMode = Drawable.DEFAULT_BLEND_MODE;
    
    float mCenterX = 0.5F;
    
    float mCenterY = 0.5F;
    
    public int mChangingConfigurations;
    
    int mDensity = 160;
    
    public boolean mDither = false;
    
    public int mGradient = 0;
    
    public int[] mGradientColors;
    
    float mGradientRadius = 0.5F;
    
    int mGradientRadiusType = 0;
    
    public int mHeight = -1;
    
    public int mInnerRadius = -1;
    
    public float mInnerRadiusRatio = 3.0F;
    
    boolean mOpaqueOverBounds;
    
    boolean mOpaqueOverShape;
    
    public Insets mOpticalInsets = Insets.NONE;
    
    public GradientDrawable.Orientation mOrientation;
    
    public Rect mPadding = null;
    
    public float[] mPositions;
    
    public float mRadius = 0.0F;
    
    public float[] mRadiusArray = null;
    
    public int mShape = 0;
    
    public ColorStateList mSolidColors;
    
    public ColorStateList mStrokeColors;
    
    public float mStrokeDashGap = 0.0F;
    
    public float mStrokeDashWidth = 0.0F;
    
    public int mStrokeWidth = -1;
    
    public int[] mTempColors;
    
    public float[] mTempPositions;
    
    int[] mThemeAttrs;
    
    public int mThickness = -1;
    
    public float mThicknessRatio = 9.0F;
    
    ColorStateList mTint = null;
    
    boolean mUseLevel = false;
    
    boolean mUseLevelForShape = true;
    
    public int mWidth = -1;
    
    public GradientState(GradientState param1GradientState, Resources param1Resources) {
      this.mChangingConfigurations = param1GradientState.mChangingConfigurations;
      this.mShape = param1GradientState.mShape;
      this.mGradient = param1GradientState.mGradient;
      this.mAngle = param1GradientState.mAngle;
      this.mOrientation = param1GradientState.mOrientation;
      this.mSolidColors = param1GradientState.mSolidColors;
      int[] arrayOfInt = param1GradientState.mGradientColors;
      if (arrayOfInt != null)
        this.mGradientColors = (int[])arrayOfInt.clone(); 
      float[] arrayOfFloat = param1GradientState.mPositions;
      if (arrayOfFloat != null)
        this.mPositions = (float[])arrayOfFloat.clone(); 
      this.mStrokeColors = param1GradientState.mStrokeColors;
      this.mStrokeWidth = param1GradientState.mStrokeWidth;
      this.mStrokeDashWidth = param1GradientState.mStrokeDashWidth;
      this.mStrokeDashGap = param1GradientState.mStrokeDashGap;
      this.mRadius = param1GradientState.mRadius;
      arrayOfFloat = param1GradientState.mRadiusArray;
      if (arrayOfFloat != null)
        this.mRadiusArray = (float[])arrayOfFloat.clone(); 
      if (param1GradientState.mPadding != null)
        this.mPadding = new Rect(param1GradientState.mPadding); 
      this.mWidth = param1GradientState.mWidth;
      this.mHeight = param1GradientState.mHeight;
      this.mInnerRadiusRatio = param1GradientState.mInnerRadiusRatio;
      this.mThicknessRatio = param1GradientState.mThicknessRatio;
      this.mInnerRadius = param1GradientState.mInnerRadius;
      this.mThickness = param1GradientState.mThickness;
      this.mDither = param1GradientState.mDither;
      this.mOpticalInsets = param1GradientState.mOpticalInsets;
      this.mCenterX = param1GradientState.mCenterX;
      this.mCenterY = param1GradientState.mCenterY;
      this.mGradientRadius = param1GradientState.mGradientRadius;
      this.mGradientRadiusType = param1GradientState.mGradientRadiusType;
      this.mUseLevel = param1GradientState.mUseLevel;
      this.mUseLevelForShape = param1GradientState.mUseLevelForShape;
      this.mOpaqueOverBounds = param1GradientState.mOpaqueOverBounds;
      this.mOpaqueOverShape = param1GradientState.mOpaqueOverShape;
      this.mTint = param1GradientState.mTint;
      this.mBlendMode = param1GradientState.mBlendMode;
      this.mThemeAttrs = param1GradientState.mThemeAttrs;
      this.mAttrSize = param1GradientState.mAttrSize;
      this.mAttrGradient = param1GradientState.mAttrGradient;
      this.mAttrSolid = param1GradientState.mAttrSolid;
      this.mAttrStroke = param1GradientState.mAttrStroke;
      this.mAttrCorners = param1GradientState.mAttrCorners;
      this.mAttrPadding = param1GradientState.mAttrPadding;
      int i = Drawable.resolveDensity(param1Resources, param1GradientState.mDensity);
      this.mDensity = i;
      int j = param1GradientState.mDensity;
      if (j != i)
        applyDensityScaling(j, i); 
    }
    
    public GradientState(GradientDrawable.Orientation param1Orientation, int[] param1ArrayOfint) {
      this.mOrientation = param1Orientation;
      setGradientColors(param1ArrayOfint);
    }
    
    private void applyDensityScaling(int param1Int1, int param1Int2) {
      int i = this.mInnerRadius;
      if (i > 0)
        this.mInnerRadius = Drawable.scaleFromDensity(i, param1Int1, param1Int2, true); 
      i = this.mThickness;
      if (i > 0)
        this.mThickness = Drawable.scaleFromDensity(i, param1Int1, param1Int2, true); 
      if (this.mOpticalInsets != Insets.NONE)
        this.mOpticalInsets = Insets.of(Drawable.scaleFromDensity(this.mOpticalInsets.left, param1Int1, param1Int2, true), Drawable.scaleFromDensity(this.mOpticalInsets.top, param1Int1, param1Int2, true), Drawable.scaleFromDensity(this.mOpticalInsets.right, param1Int1, param1Int2, true), Drawable.scaleFromDensity(this.mOpticalInsets.bottom, param1Int1, param1Int2, true)); 
      Rect rect = this.mPadding;
      if (rect != null) {
        rect.left = Drawable.scaleFromDensity(rect.left, param1Int1, param1Int2, false);
        rect = this.mPadding;
        rect.top = Drawable.scaleFromDensity(rect.top, param1Int1, param1Int2, false);
        rect = this.mPadding;
        rect.right = Drawable.scaleFromDensity(rect.right, param1Int1, param1Int2, false);
        rect = this.mPadding;
        rect.bottom = Drawable.scaleFromDensity(rect.bottom, param1Int1, param1Int2, false);
      } 
      float f = this.mRadius;
      if (f > 0.0F)
        this.mRadius = Drawable.scaleFromDensity(f, param1Int1, param1Int2); 
      float[] arrayOfFloat = this.mRadiusArray;
      if (arrayOfFloat != null) {
        arrayOfFloat[0] = Drawable.scaleFromDensity((int)arrayOfFloat[0], param1Int1, param1Int2, true);
        arrayOfFloat = this.mRadiusArray;
        arrayOfFloat[1] = Drawable.scaleFromDensity((int)arrayOfFloat[1], param1Int1, param1Int2, true);
        arrayOfFloat = this.mRadiusArray;
        arrayOfFloat[2] = Drawable.scaleFromDensity((int)arrayOfFloat[2], param1Int1, param1Int2, true);
        arrayOfFloat = this.mRadiusArray;
        arrayOfFloat[3] = Drawable.scaleFromDensity((int)arrayOfFloat[3], param1Int1, param1Int2, true);
      } 
      i = this.mStrokeWidth;
      if (i > 0)
        this.mStrokeWidth = Drawable.scaleFromDensity(i, param1Int1, param1Int2, true); 
      if (this.mStrokeDashWidth > 0.0F)
        this.mStrokeDashWidth = Drawable.scaleFromDensity(this.mStrokeDashGap, param1Int1, param1Int2); 
      f = this.mStrokeDashGap;
      if (f > 0.0F)
        this.mStrokeDashGap = Drawable.scaleFromDensity(f, param1Int1, param1Int2); 
      if (this.mGradientRadiusType == 0)
        this.mGradientRadius = Drawable.scaleFromDensity(this.mGradientRadius, param1Int1, param1Int2); 
      i = this.mWidth;
      if (i > 0)
        this.mWidth = Drawable.scaleFromDensity(i, param1Int1, param1Int2, true); 
      i = this.mHeight;
      if (i > 0)
        this.mHeight = Drawable.scaleFromDensity(i, param1Int1, param1Int2, true); 
    }
    
    private void computeOpacity() {
      boolean bool1 = false;
      this.mOpaqueOverBounds = false;
      this.mOpaqueOverShape = false;
      if (this.mGradientColors != null) {
        byte b = 0;
        while (true) {
          int[] arrayOfInt = this.mGradientColors;
          if (b < arrayOfInt.length) {
            if (!GradientDrawable.isOpaque(arrayOfInt[b]))
              return; 
            b++;
            continue;
          } 
          break;
        } 
      } 
      if (this.mGradientColors == null && this.mSolidColors == null)
        return; 
      this.mOpaqueOverShape = true;
      boolean bool2 = bool1;
      if (this.mShape == 0) {
        bool2 = bool1;
        if (this.mRadius <= 0.0F) {
          bool2 = bool1;
          if (this.mRadiusArray == null)
            bool2 = true; 
        } 
      } 
      this.mOpaqueOverBounds = bool2;
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs == null && this.mAttrSize == null && this.mAttrGradient == null && this.mAttrSolid == null && this.mAttrStroke == null && this.mAttrCorners == null && this.mAttrPadding == null) {
        ColorStateList colorStateList = this.mTint;
        if (colorStateList == null || !colorStateList.canApplyTheme()) {
          colorStateList = this.mStrokeColors;
          if (colorStateList == null || !colorStateList.canApplyTheme()) {
            colorStateList = this.mSolidColors;
            return ((colorStateList != null && colorStateList.canApplyTheme()) || super.canApplyTheme());
          } 
        } 
      } 
      return true;
    }
    
    public int getChangingConfigurations() {
      byte b;
      boolean bool;
      int i = this.mChangingConfigurations;
      ColorStateList colorStateList = this.mStrokeColors;
      int j = 0;
      if (colorStateList != null) {
        b = colorStateList.getChangingConfigurations();
      } else {
        b = 0;
      } 
      colorStateList = this.mSolidColors;
      if (colorStateList != null) {
        bool = colorStateList.getChangingConfigurations();
      } else {
        bool = false;
      } 
      colorStateList = this.mTint;
      if (colorStateList != null)
        j = colorStateList.getChangingConfigurations(); 
      return i | b | bool | j;
    }
    
    public GradientDrawable.Orientation getOrientation() {
      return this.mOrientation;
    }
    
    public boolean hasCenterColor() {
      boolean bool;
      int[] arrayOfInt = this.mGradientColors;
      if (arrayOfInt != null && arrayOfInt.length == 3) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public Drawable newDrawable() {
      return new GradientDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      GradientState gradientState;
      if (Drawable.resolveDensity(param1Resources, this.mDensity) != this.mDensity) {
        gradientState = new GradientState(this, param1Resources);
      } else {
        gradientState = this;
      } 
      return new GradientDrawable(gradientState, param1Resources);
    }
    
    public void setCornerRadii(float[] param1ArrayOffloat) {
      this.mRadiusArray = param1ArrayOffloat;
      if (param1ArrayOffloat == null)
        this.mRadius = 0.0F; 
      computeOpacity();
    }
    
    public void setCornerRadius(float param1Float) {
      float f = param1Float;
      if (param1Float < 0.0F)
        f = 0.0F; 
      this.mRadius = f;
      this.mRadiusArray = null;
      computeOpacity();
    }
    
    public final void setDensity(int param1Int) {
      if (this.mDensity != param1Int) {
        int i = this.mDensity;
        this.mDensity = param1Int;
        applyDensityScaling(i, param1Int);
      } 
    }
    
    public void setGradientCenter(float param1Float1, float param1Float2) {
      this.mCenterX = param1Float1;
      this.mCenterY = param1Float2;
    }
    
    public void setGradientColors(int[] param1ArrayOfint) {
      this.mGradientColors = param1ArrayOfint;
      this.mSolidColors = null;
      computeOpacity();
    }
    
    public void setGradientRadius(float param1Float, int param1Int) {
      this.mGradientRadius = param1Float;
      this.mGradientRadiusType = param1Int;
    }
    
    public void setGradientType(int param1Int) {
      this.mGradient = param1Int;
    }
    
    public void setShape(int param1Int) {
      this.mShape = param1Int;
      computeOpacity();
    }
    
    public void setSize(int param1Int1, int param1Int2) {
      this.mWidth = param1Int1;
      this.mHeight = param1Int2;
    }
    
    public void setSolidColors(ColorStateList param1ColorStateList) {
      this.mGradientColors = null;
      this.mSolidColors = param1ColorStateList;
      computeOpacity();
    }
    
    public void setStroke(int param1Int, ColorStateList param1ColorStateList, float param1Float1, float param1Float2) {
      this.mStrokeWidth = param1Int;
      this.mStrokeColors = param1ColorStateList;
      this.mStrokeDashWidth = param1Float1;
      this.mStrokeDashGap = param1Float2;
      computeOpacity();
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface GradientType {}
  
  public enum Orientation {
    BL_TR, BOTTOM_TOP, BR_TL, LEFT_RIGHT, RIGHT_LEFT, TL_BR, TOP_BOTTOM, TR_BL;
    
    static {
      BR_TL = new Orientation("BR_TL", 3);
      BOTTOM_TOP = new Orientation("BOTTOM_TOP", 4);
      BL_TR = new Orientation("BL_TR", 5);
      LEFT_RIGHT = new Orientation("LEFT_RIGHT", 6);
      Orientation orientation = new Orientation("TL_BR", 7);
      TL_BR = orientation;
      $VALUES = new Orientation[] { TOP_BOTTOM, TR_BL, RIGHT_LEFT, BR_TL, BOTTOM_TOP, BL_TR, LEFT_RIGHT, orientation };
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RadiusType {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Shape {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/GradientDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */