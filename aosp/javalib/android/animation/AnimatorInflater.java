package android.animation;

import android.content.Context;
import android.content.res.ConfigurationBoundResourceCache;
import android.content.res.ConstantState;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.PathParser;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import android.view.animation.AnimationUtils;
import android.view.animation.BaseInterpolator;
import android.view.animation.Interpolator;
import com.android.internal.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatorInflater {
  private static final boolean DBG_ANIMATOR_INFLATER = false;
  
  private static final int SEQUENTIALLY = 1;
  
  private static final String TAG = "AnimatorInflater";
  
  private static final int TOGETHER = 0;
  
  private static final int VALUE_TYPE_COLOR = 3;
  
  private static final int VALUE_TYPE_FLOAT = 0;
  
  private static final int VALUE_TYPE_INT = 1;
  
  private static final int VALUE_TYPE_PATH = 2;
  
  private static final int VALUE_TYPE_UNDEFINED = 4;
  
  private static final TypedValue sTmpTypedValue = new TypedValue();
  
  private static Animator createAnimatorFromXml(Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, float paramFloat) throws XmlPullParserException, IOException {
    return createAnimatorFromXml(paramResources, paramTheme, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser), null, 0, paramFloat);
  }
  
  private static Animator createAnimatorFromXml(Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, AnimatorSet paramAnimatorSet, int paramInt, float paramFloat) throws XmlPullParserException, IOException {
    AnimatorSet animatorSet;
    ArrayList<AnimatorSet> arrayList;
    ObjectAnimator objectAnimator = null;
    int i = paramXmlPullParser.getDepth();
    String str = null;
    while (true) {
      int j = paramXmlPullParser.next();
      if ((j != 3 || paramXmlPullParser.getDepth() > i) && j != 1) {
        ArrayList<AnimatorSet> arrayList1;
        if (j != 2)
          continue; 
        String str1 = paramXmlPullParser.getName();
        j = 0;
        if (str1.equals("objectAnimator")) {
          objectAnimator = loadObjectAnimator(paramResources, paramTheme, paramAttributeSet, paramFloat);
        } else if (str1.equals("animator")) {
          ValueAnimator valueAnimator = loadAnimator(paramResources, paramTheme, paramAttributeSet, null, paramFloat);
        } else {
          AnimatorSet animatorSet1;
          if (str1.equals("set")) {
            TypedArray typedArray;
            animatorSet1 = new AnimatorSet();
            if (paramTheme != null) {
              typedArray = paramTheme.obtainStyledAttributes(paramAttributeSet, R.styleable.AnimatorSet, 0, 0);
            } else {
              typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.AnimatorSet);
            } 
            animatorSet1.appendChangingConfigurations(typedArray.getChangingConfigurations());
            int k = typedArray.getInt(0, 0);
            createAnimatorFromXml(paramResources, paramTheme, paramXmlPullParser, paramAttributeSet, animatorSet1, k, paramFloat);
            typedArray.recycle();
            animatorSet = animatorSet1;
          } else if (animatorSet1.equals("propertyValuesHolder")) {
            PropertyValuesHolder[] arrayOfPropertyValuesHolder = loadValues(paramResources, paramTheme, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser));
            if (arrayOfPropertyValuesHolder != null && animatorSet != null && animatorSet instanceof ValueAnimator)
              ((ValueAnimator)animatorSet).setValues(arrayOfPropertyValuesHolder); 
            j = 1;
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown animator name: ");
            stringBuilder.append(paramXmlPullParser.getName());
            throw new RuntimeException(stringBuilder.toString());
          } 
        } 
        str1 = str;
        if (paramAnimatorSet != null) {
          str1 = str;
          if (j == 0) {
            str1 = str;
            if (str == null)
              arrayList1 = new ArrayList(); 
            arrayList1.add(animatorSet);
          } 
        } 
        arrayList = arrayList1;
        continue;
      } 
      break;
    } 
    if (paramAnimatorSet != null && arrayList != null) {
      Animator[] arrayOfAnimator = new Animator[arrayList.size()];
      byte b = 0;
      Iterator<AnimatorSet> iterator = arrayList.iterator();
      while (iterator.hasNext()) {
        arrayOfAnimator[b] = iterator.next();
        b++;
      } 
      if (paramInt == 0) {
        paramAnimatorSet.playTogether(arrayOfAnimator);
      } else {
        paramAnimatorSet.playSequentially(arrayOfAnimator);
      } 
    } 
    return animatorSet;
  }
  
  private static Keyframe createNewKeyframe(Keyframe paramKeyframe, float paramFloat) {
    if (paramKeyframe.getType() == float.class) {
      paramKeyframe = Keyframe.ofFloat(paramFloat);
    } else if (paramKeyframe.getType() == int.class) {
      paramKeyframe = Keyframe.ofInt(paramFloat);
    } else {
      paramKeyframe = Keyframe.ofObject(paramFloat);
    } 
    return paramKeyframe;
  }
  
  private static StateListAnimator createStateListAnimatorFromXml(Context paramContext, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws IOException, XmlPullParserException {
    StateListAnimator stateListAnimator = new StateListAnimator();
    while (true) {
      int i = paramXmlPullParser.next();
      if (i != 1) {
        if (i != 2) {
          if (i != 3)
            continue; 
          break;
        } 
        Animator animator = null;
        if ("item".equals(paramXmlPullParser.getName())) {
          int j = paramXmlPullParser.getAttributeCount();
          int[] arrayOfInt = new int[j];
          byte b = 0;
          for (i = 0; i < j; i++) {
            int k = paramAttributeSet.getAttributeNameResource(i);
            if (k == 16843213) {
              animator = loadAnimator(paramContext, paramAttributeSet.getAttributeResourceValue(i, 0));
            } else {
              if (!paramAttributeSet.getAttributeBooleanValue(i, false))
                k = -k; 
              arrayOfInt[b] = k;
              b++;
            } 
          } 
          Animator animator1 = animator;
          if (animator == null)
            animator1 = createAnimatorFromXml(paramContext.getResources(), paramContext.getTheme(), paramXmlPullParser, 1.0F); 
          if (animator1 != null) {
            stateListAnimator.addState(StateSet.trimStateSet(arrayOfInt, b), animator1);
            continue;
          } 
          throw new Resources.NotFoundException("animation state item must have a valid animation");
        } 
        continue;
      } 
      break;
    } 
    return stateListAnimator;
  }
  
  private static void distributeKeyframes(Keyframe[] paramArrayOfKeyframe, float paramFloat, int paramInt1, int paramInt2) {
    paramFloat /= (paramInt2 - paramInt1 + 2);
    while (paramInt1 <= paramInt2) {
      paramArrayOfKeyframe[paramInt1].setFraction(paramArrayOfKeyframe[paramInt1 - 1].getFraction() + paramFloat);
      paramInt1++;
    } 
  }
  
  private static void dumpKeyframes(Object[] paramArrayOfObject, String paramString) {
    if (paramArrayOfObject == null || paramArrayOfObject.length == 0)
      return; 
    Log.d("AnimatorInflater", paramString);
    int i = paramArrayOfObject.length;
    for (byte b = 0; b < i; b++) {
      Float float_;
      Keyframe keyframe = (Keyframe)paramArrayOfObject[b];
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Keyframe ");
      stringBuilder.append(b);
      stringBuilder.append(": fraction ");
      float f = keyframe.getFraction();
      String str = "null";
      if (f < 0.0F) {
        paramString = "null";
      } else {
        float_ = Float.valueOf(keyframe.getFraction());
      } 
      stringBuilder.append(float_);
      stringBuilder.append(", , value : ");
      Object object = str;
      if (keyframe.hasValue())
        object = keyframe.getValue(); 
      stringBuilder.append(object);
      Log.d("AnimatorInflater", stringBuilder.toString());
    } 
  }
  
  private static int getChangingConfigs(Resources paramResources, int paramInt) {
    synchronized (sTmpTypedValue) {
      paramResources.getValue(paramInt, sTmpTypedValue, true);
      paramInt = sTmpTypedValue.changingConfigurations;
      return paramInt;
    } 
  }
  
  private static PropertyValuesHolder getPVH(TypedArray paramTypedArray, int paramInt1, int paramInt2, int paramInt3, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: iload_2
    //   2: invokevirtual peekValue : (I)Landroid/util/TypedValue;
    //   5: astore #5
    //   7: aload #5
    //   9: ifnull -> 18
    //   12: iconst_1
    //   13: istore #6
    //   15: goto -> 21
    //   18: iconst_0
    //   19: istore #6
    //   21: iload #6
    //   23: ifeq -> 36
    //   26: aload #5
    //   28: getfield type : I
    //   31: istore #7
    //   33: goto -> 39
    //   36: iconst_0
    //   37: istore #7
    //   39: aload_0
    //   40: iload_3
    //   41: invokevirtual peekValue : (I)Landroid/util/TypedValue;
    //   44: astore #5
    //   46: aload #5
    //   48: ifnull -> 57
    //   51: iconst_1
    //   52: istore #8
    //   54: goto -> 60
    //   57: iconst_0
    //   58: istore #8
    //   60: iload #8
    //   62: ifeq -> 75
    //   65: aload #5
    //   67: getfield type : I
    //   70: istore #9
    //   72: goto -> 78
    //   75: iconst_0
    //   76: istore #9
    //   78: iload_1
    //   79: iconst_4
    //   80: if_icmpne -> 119
    //   83: iload #6
    //   85: ifeq -> 96
    //   88: iload #7
    //   90: invokestatic isColorType : (I)Z
    //   93: ifne -> 109
    //   96: iload #8
    //   98: ifeq -> 114
    //   101: iload #9
    //   103: invokestatic isColorType : (I)Z
    //   106: ifeq -> 114
    //   109: iconst_3
    //   110: istore_1
    //   111: goto -> 119
    //   114: iconst_0
    //   115: istore_1
    //   116: goto -> 119
    //   119: iload_1
    //   120: ifne -> 129
    //   123: iconst_1
    //   124: istore #10
    //   126: goto -> 132
    //   129: iconst_0
    //   130: istore #10
    //   132: iload_1
    //   133: iconst_2
    //   134: if_icmpne -> 368
    //   137: aload_0
    //   138: iload_2
    //   139: invokevirtual getString : (I)Ljava/lang/String;
    //   142: astore #11
    //   144: aload_0
    //   145: iload_3
    //   146: invokevirtual getString : (I)Ljava/lang/String;
    //   149: astore #12
    //   151: aload #11
    //   153: ifnonnull -> 161
    //   156: aconst_null
    //   157: astore_0
    //   158: goto -> 171
    //   161: new android/util/PathParser$PathData
    //   164: dup
    //   165: aload #11
    //   167: invokespecial <init> : (Ljava/lang/String;)V
    //   170: astore_0
    //   171: aload #12
    //   173: ifnonnull -> 182
    //   176: aconst_null
    //   177: astore #5
    //   179: goto -> 193
    //   182: new android/util/PathParser$PathData
    //   185: dup
    //   186: aload #12
    //   188: invokespecial <init> : (Ljava/lang/String;)V
    //   191: astore #5
    //   193: aload_0
    //   194: ifnonnull -> 208
    //   197: aload #5
    //   199: ifnull -> 205
    //   202: goto -> 208
    //   205: goto -> 360
    //   208: aload_0
    //   209: ifnull -> 329
    //   212: new android/animation/AnimatorInflater$PathDataEvaluator
    //   215: dup
    //   216: aconst_null
    //   217: invokespecial <init> : (Landroid/animation/AnimatorInflater$1;)V
    //   220: astore #13
    //   222: aload #5
    //   224: ifnull -> 310
    //   227: aload_0
    //   228: aload #5
    //   230: invokestatic canMorph : (Landroid/util/PathParser$PathData;Landroid/util/PathParser$PathData;)Z
    //   233: ifeq -> 260
    //   236: aload #4
    //   238: aload #13
    //   240: iconst_2
    //   241: anewarray java/lang/Object
    //   244: dup
    //   245: iconst_0
    //   246: aload_0
    //   247: aastore
    //   248: dup
    //   249: iconst_1
    //   250: aload #5
    //   252: aastore
    //   253: invokestatic ofObject : (Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/PropertyValuesHolder;
    //   256: astore_0
    //   257: goto -> 326
    //   260: new java/lang/StringBuilder
    //   263: dup
    //   264: invokespecial <init> : ()V
    //   267: astore_0
    //   268: aload_0
    //   269: ldc_w ' Can't morph from '
    //   272: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: pop
    //   276: aload_0
    //   277: aload #11
    //   279: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: pop
    //   283: aload_0
    //   284: ldc_w ' to '
    //   287: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: pop
    //   291: aload_0
    //   292: aload #12
    //   294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: new android/view/InflateException
    //   301: dup
    //   302: aload_0
    //   303: invokevirtual toString : ()Ljava/lang/String;
    //   306: invokespecial <init> : (Ljava/lang/String;)V
    //   309: athrow
    //   310: aload #4
    //   312: aload #13
    //   314: iconst_1
    //   315: anewarray java/lang/Object
    //   318: dup
    //   319: iconst_0
    //   320: aload_0
    //   321: aastore
    //   322: invokestatic ofObject : (Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/PropertyValuesHolder;
    //   325: astore_0
    //   326: goto -> 362
    //   329: aload #5
    //   331: ifnull -> 360
    //   334: aload #4
    //   336: new android/animation/AnimatorInflater$PathDataEvaluator
    //   339: dup
    //   340: aconst_null
    //   341: invokespecial <init> : (Landroid/animation/AnimatorInflater$1;)V
    //   344: iconst_1
    //   345: anewarray java/lang/Object
    //   348: dup
    //   349: iconst_0
    //   350: aload #5
    //   352: aastore
    //   353: invokestatic ofObject : (Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/PropertyValuesHolder;
    //   356: astore_0
    //   357: goto -> 362
    //   360: aconst_null
    //   361: astore_0
    //   362: aload_0
    //   363: astore #4
    //   365: goto -> 746
    //   368: aconst_null
    //   369: astore #5
    //   371: iload_1
    //   372: iconst_3
    //   373: if_icmpne -> 381
    //   376: invokestatic getInstance : ()Landroid/animation/ArgbEvaluator;
    //   379: astore #5
    //   381: iload #10
    //   383: ifeq -> 527
    //   386: iload #6
    //   388: ifeq -> 485
    //   391: iload #7
    //   393: iconst_5
    //   394: if_icmpne -> 408
    //   397: aload_0
    //   398: iload_2
    //   399: fconst_0
    //   400: invokevirtual getDimension : (IF)F
    //   403: fstore #14
    //   405: goto -> 416
    //   408: aload_0
    //   409: iload_2
    //   410: fconst_0
    //   411: invokevirtual getFloat : (IF)F
    //   414: fstore #14
    //   416: iload #8
    //   418: ifeq -> 468
    //   421: iload #9
    //   423: iconst_5
    //   424: if_icmpne -> 438
    //   427: aload_0
    //   428: iload_3
    //   429: fconst_0
    //   430: invokevirtual getDimension : (IF)F
    //   433: fstore #15
    //   435: goto -> 446
    //   438: aload_0
    //   439: iload_3
    //   440: fconst_0
    //   441: invokevirtual getFloat : (IF)F
    //   444: fstore #15
    //   446: aload #4
    //   448: iconst_2
    //   449: newarray float
    //   451: dup
    //   452: iconst_0
    //   453: fload #14
    //   455: fastore
    //   456: dup
    //   457: iconst_1
    //   458: fload #15
    //   460: fastore
    //   461: invokestatic ofFloat : (Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;
    //   464: astore_0
    //   465: goto -> 524
    //   468: aload #4
    //   470: iconst_1
    //   471: newarray float
    //   473: dup
    //   474: iconst_0
    //   475: fload #14
    //   477: fastore
    //   478: invokestatic ofFloat : (Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;
    //   481: astore_0
    //   482: goto -> 524
    //   485: iload #9
    //   487: iconst_5
    //   488: if_icmpne -> 502
    //   491: aload_0
    //   492: iload_3
    //   493: fconst_0
    //   494: invokevirtual getDimension : (IF)F
    //   497: fstore #14
    //   499: goto -> 510
    //   502: aload_0
    //   503: iload_3
    //   504: fconst_0
    //   505: invokevirtual getFloat : (IF)F
    //   508: fstore #14
    //   510: aload #4
    //   512: iconst_1
    //   513: newarray float
    //   515: dup
    //   516: iconst_0
    //   517: fload #14
    //   519: fastore
    //   520: invokestatic ofFloat : (Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;
    //   523: astore_0
    //   524: goto -> 722
    //   527: iload #6
    //   529: ifeq -> 657
    //   532: iload #7
    //   534: iconst_5
    //   535: if_icmpne -> 549
    //   538: aload_0
    //   539: iload_2
    //   540: fconst_0
    //   541: invokevirtual getDimension : (IF)F
    //   544: f2i
    //   545: istore_1
    //   546: goto -> 574
    //   549: iload #7
    //   551: invokestatic isColorType : (I)Z
    //   554: ifeq -> 567
    //   557: aload_0
    //   558: iload_2
    //   559: iconst_0
    //   560: invokevirtual getColor : (II)I
    //   563: istore_1
    //   564: goto -> 574
    //   567: aload_0
    //   568: iload_2
    //   569: iconst_0
    //   570: invokevirtual getInt : (II)I
    //   573: istore_1
    //   574: iload #8
    //   576: ifeq -> 641
    //   579: iload #9
    //   581: iconst_5
    //   582: if_icmpne -> 596
    //   585: aload_0
    //   586: iload_3
    //   587: fconst_0
    //   588: invokevirtual getDimension : (IF)F
    //   591: f2i
    //   592: istore_2
    //   593: goto -> 621
    //   596: iload #9
    //   598: invokestatic isColorType : (I)Z
    //   601: ifeq -> 614
    //   604: aload_0
    //   605: iload_3
    //   606: iconst_0
    //   607: invokevirtual getColor : (II)I
    //   610: istore_2
    //   611: goto -> 621
    //   614: aload_0
    //   615: iload_3
    //   616: iconst_0
    //   617: invokevirtual getInt : (II)I
    //   620: istore_2
    //   621: aload #4
    //   623: iconst_2
    //   624: newarray int
    //   626: dup
    //   627: iconst_0
    //   628: iload_1
    //   629: iastore
    //   630: dup
    //   631: iconst_1
    //   632: iload_2
    //   633: iastore
    //   634: invokestatic ofInt : (Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;
    //   637: astore_0
    //   638: goto -> 722
    //   641: aload #4
    //   643: iconst_1
    //   644: newarray int
    //   646: dup
    //   647: iconst_0
    //   648: iload_1
    //   649: iastore
    //   650: invokestatic ofInt : (Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;
    //   653: astore_0
    //   654: goto -> 722
    //   657: iload #8
    //   659: ifeq -> 720
    //   662: iload #9
    //   664: iconst_5
    //   665: if_icmpne -> 679
    //   668: aload_0
    //   669: iload_3
    //   670: fconst_0
    //   671: invokevirtual getDimension : (IF)F
    //   674: f2i
    //   675: istore_1
    //   676: goto -> 704
    //   679: iload #9
    //   681: invokestatic isColorType : (I)Z
    //   684: ifeq -> 697
    //   687: aload_0
    //   688: iload_3
    //   689: iconst_0
    //   690: invokevirtual getColor : (II)I
    //   693: istore_1
    //   694: goto -> 704
    //   697: aload_0
    //   698: iload_3
    //   699: iconst_0
    //   700: invokevirtual getInt : (II)I
    //   703: istore_1
    //   704: aload #4
    //   706: iconst_1
    //   707: newarray int
    //   709: dup
    //   710: iconst_0
    //   711: iload_1
    //   712: iastore
    //   713: invokestatic ofInt : (Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;
    //   716: astore_0
    //   717: goto -> 722
    //   720: aconst_null
    //   721: astore_0
    //   722: aload_0
    //   723: astore #4
    //   725: aload_0
    //   726: ifnull -> 746
    //   729: aload_0
    //   730: astore #4
    //   732: aload #5
    //   734: ifnull -> 746
    //   737: aload_0
    //   738: aload #5
    //   740: invokevirtual setEvaluator : (Landroid/animation/TypeEvaluator;)V
    //   743: aload_0
    //   744: astore #4
    //   746: aload #4
    //   748: areturn
  }
  
  private static int inferValueTypeFromValues(TypedArray paramTypedArray, int paramInt1, int paramInt2) {
    boolean bool2;
    TypedValue typedValue2 = paramTypedArray.peekValue(paramInt1);
    boolean bool1 = true;
    int i = 0;
    if (typedValue2 != null) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    } 
    if (paramInt1 != 0) {
      bool2 = typedValue2.type;
    } else {
      bool2 = false;
    } 
    TypedValue typedValue1 = paramTypedArray.peekValue(paramInt2);
    if (typedValue1 != null) {
      paramInt2 = bool1;
    } else {
      paramInt2 = 0;
    } 
    if (paramInt2 != 0)
      i = typedValue1.type; 
    if ((paramInt1 != 0 && isColorType(bool2)) || (paramInt2 != 0 && isColorType(i))) {
      paramInt1 = 3;
    } else {
      paramInt1 = 0;
    } 
    return paramInt1;
  }
  
  private static int inferValueTypeOfKeyframe(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet) {
    TypedArray typedArray;
    byte b = 0;
    if (paramTheme != null) {
      typedArray = paramTheme.obtainStyledAttributes(paramAttributeSet, R.styleable.Keyframe, 0, 0);
    } else {
      typedArray = typedArray.obtainAttributes(paramAttributeSet, R.styleable.Keyframe);
    } 
    TypedValue typedValue = typedArray.peekValue(0);
    if (typedValue != null)
      b = 1; 
    if (b && isColorType(typedValue.type)) {
      b = 3;
    } else {
      b = 0;
    } 
    typedArray.recycle();
    return b;
  }
  
  private static boolean isColorType(int paramInt) {
    boolean bool;
    if (paramInt >= 28 && paramInt <= 31) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static Animator loadAnimator(Context paramContext, int paramInt) throws Resources.NotFoundException {
    return loadAnimator(paramContext.getResources(), paramContext.getTheme(), paramInt);
  }
  
  public static Animator loadAnimator(Resources paramResources, Resources.Theme paramTheme, int paramInt) throws Resources.NotFoundException {
    return loadAnimator(paramResources, paramTheme, paramInt, 1.0F);
  }
  
  public static Animator loadAnimator(Resources paramResources, Resources.Theme paramTheme, int paramInt, float paramFloat) throws Resources.NotFoundException {
    Resources.NotFoundException notFoundException1;
    Resources.NotFoundException notFoundException2;
    ConfigurationBoundResourceCache configurationBoundResourceCache = paramResources.getAnimatorCache();
    Animator animator = (Animator)configurationBoundResourceCache.getInstance(paramInt, paramResources, paramTheme);
    if (animator != null)
      return animator; 
    XmlResourceParser xmlResourceParser1 = null;
    XmlResourceParser xmlResourceParser2 = null;
    animator = null;
    try {
      XmlResourceParser xmlResourceParser4 = paramResources.getAnimation(paramInt);
      XmlResourceParser xmlResourceParser3 = xmlResourceParser4;
      xmlResourceParser1 = xmlResourceParser4;
      xmlResourceParser2 = xmlResourceParser4;
      Animator animator2 = createAnimatorFromXml(paramResources, paramTheme, (XmlPullParser)xmlResourceParser4, paramFloat);
      Animator animator1 = animator2;
      if (animator2 != null) {
        XmlResourceParser xmlResourceParser = xmlResourceParser4;
        xmlResourceParser1 = xmlResourceParser4;
        xmlResourceParser2 = xmlResourceParser4;
        animator2.appendChangingConfigurations(getChangingConfigs(paramResources, paramInt));
        xmlResourceParser = xmlResourceParser4;
        xmlResourceParser1 = xmlResourceParser4;
        xmlResourceParser2 = xmlResourceParser4;
        ConstantState<Animator> constantState = animator2.createConstantState();
        Animator animator3 = animator2;
        if (constantState != null) {
          XmlResourceParser xmlResourceParser5 = xmlResourceParser4;
          xmlResourceParser1 = xmlResourceParser4;
          xmlResourceParser2 = xmlResourceParser4;
          configurationBoundResourceCache.put(paramInt, paramTheme, constantState);
          xmlResourceParser5 = xmlResourceParser4;
          xmlResourceParser1 = xmlResourceParser4;
          xmlResourceParser2 = xmlResourceParser4;
          Animator animator4 = (Animator)constantState.newInstance(paramResources, paramTheme);
          animator1 = animator4;
        } 
      } 
      if (xmlResourceParser4 != null)
        xmlResourceParser4.close(); 
      return animator1;
    } catch (XmlPullParserException xmlPullParserException) {
      XmlResourceParser xmlResourceParser = xmlResourceParser2;
      notFoundException2 = new Resources.NotFoundException();
      xmlResourceParser = xmlResourceParser2;
      StringBuilder stringBuilder = new StringBuilder();
      xmlResourceParser = xmlResourceParser2;
      this();
      xmlResourceParser = xmlResourceParser2;
      stringBuilder.append("Can't load animation resource ID #0x");
      xmlResourceParser = xmlResourceParser2;
      stringBuilder.append(Integer.toHexString(paramInt));
      xmlResourceParser = xmlResourceParser2;
      this(stringBuilder.toString());
      xmlResourceParser = xmlResourceParser2;
      notFoundException2.initCause((Throwable)xmlPullParserException);
      xmlResourceParser = xmlResourceParser2;
      throw notFoundException2;
    } catch (IOException iOException) {
      notFoundException1 = notFoundException2;
      Resources.NotFoundException notFoundException = new Resources.NotFoundException();
      notFoundException1 = notFoundException2;
      StringBuilder stringBuilder = new StringBuilder();
      notFoundException1 = notFoundException2;
      this();
      notFoundException1 = notFoundException2;
      stringBuilder.append("Can't load animation resource ID #0x");
      notFoundException1 = notFoundException2;
      stringBuilder.append(Integer.toHexString(paramInt));
      notFoundException1 = notFoundException2;
      this(stringBuilder.toString());
      notFoundException1 = notFoundException2;
      notFoundException.initCause(iOException);
      notFoundException1 = notFoundException2;
      throw notFoundException;
    } finally {}
    if (notFoundException1 != null)
      notFoundException1.close(); 
    throw paramResources;
  }
  
  private static ValueAnimator loadAnimator(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, ValueAnimator paramValueAnimator, float paramFloat) throws Resources.NotFoundException {
    TypedArray typedArray2;
    TypedArray typedArray1 = null;
    if (paramTheme != null) {
      typedArray2 = paramTheme.obtainStyledAttributes(paramAttributeSet, R.styleable.Animator, 0, 0);
    } else {
      typedArray2 = paramResources.obtainAttributes(paramAttributeSet, R.styleable.Animator);
    } 
    if (paramValueAnimator != null) {
      TypedArray typedArray;
      if (paramTheme != null) {
        typedArray = paramTheme.obtainStyledAttributes(paramAttributeSet, R.styleable.PropertyAnimator, 0, 0);
      } else {
        typedArray = paramResources.obtainAttributes((AttributeSet)typedArray, R.styleable.PropertyAnimator);
      } 
      paramValueAnimator.appendChangingConfigurations(typedArray.getChangingConfigurations());
      typedArray1 = typedArray;
    } 
    ValueAnimator valueAnimator = paramValueAnimator;
    if (paramValueAnimator == null)
      valueAnimator = new ValueAnimator(); 
    valueAnimator.appendChangingConfigurations(typedArray2.getChangingConfigurations());
    parseAnimatorFromTypeArray(valueAnimator, typedArray2, typedArray1, paramFloat);
    int i = typedArray2.getResourceId(0, 0);
    if (i > 0) {
      Interpolator interpolator = AnimationUtils.loadInterpolator(paramResources, paramTheme, i);
      if (interpolator instanceof BaseInterpolator)
        valueAnimator.appendChangingConfigurations(((BaseInterpolator)interpolator).getChangingConfiguration()); 
      valueAnimator.setInterpolator((TimeInterpolator)interpolator);
    } 
    typedArray2.recycle();
    if (typedArray1 != null)
      typedArray1.recycle(); 
    return valueAnimator;
  }
  
  private static Keyframe loadKeyframe(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int paramInt) throws XmlPullParserException, IOException {
    Keyframe keyframe;
    TypedArray typedArray;
    boolean bool;
    if (paramTheme != null) {
      typedArray = paramTheme.obtainStyledAttributes(paramAttributeSet, R.styleable.Keyframe, 0, 0);
    } else {
      typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.Keyframe);
    } 
    paramAttributeSet = null;
    float f = typedArray.getFloat(3, -1.0F);
    TypedValue typedValue = typedArray.peekValue(0);
    if (typedValue != null) {
      bool = true;
    } else {
      bool = false;
    } 
    int i = paramInt;
    if (paramInt == 4)
      if (bool && isColorType(typedValue.type)) {
        i = 3;
      } else {
        i = 0;
      }  
    if (bool) {
      if (i != 0) {
        if (i == 1 || i == 3)
          keyframe = Keyframe.ofInt(f, typedArray.getInt(0, 0)); 
      } else {
        keyframe = Keyframe.ofFloat(f, typedArray.getFloat(0, 0.0F));
      } 
    } else if (i == 0) {
      keyframe = Keyframe.ofFloat(f);
    } else {
      keyframe = Keyframe.ofInt(f);
    } 
    paramInt = typedArray.getResourceId(1, 0);
    if (paramInt > 0)
      keyframe.setInterpolator((TimeInterpolator)AnimationUtils.loadInterpolator(paramResources, paramTheme, paramInt)); 
    typedArray.recycle();
    return keyframe;
  }
  
  private static ObjectAnimator loadObjectAnimator(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, float paramFloat) throws Resources.NotFoundException {
    ObjectAnimator objectAnimator = new ObjectAnimator();
    loadAnimator(paramResources, paramTheme, paramAttributeSet, objectAnimator, paramFloat);
    return objectAnimator;
  }
  
  private static PropertyValuesHolder loadPvh(Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, String paramString, int paramInt) throws XmlPullParserException, IOException {
    Resources resources = null;
    ArrayList<Keyframe> arrayList = null;
    while (true) {
      int i = paramXmlPullParser.next();
      if (i != 3 && i != 1) {
        i = paramInt;
        ArrayList<Keyframe> arrayList1 = arrayList;
        if (paramXmlPullParser.getName().equals("keyframe")) {
          i = paramInt;
          if (paramInt == 4)
            i = inferValueTypeOfKeyframe(paramResources, paramTheme, Xml.asAttributeSet(paramXmlPullParser)); 
          Keyframe keyframe = loadKeyframe(paramResources, paramTheme, Xml.asAttributeSet(paramXmlPullParser), i);
          arrayList1 = arrayList;
          if (keyframe != null) {
            arrayList1 = arrayList;
            if (arrayList == null)
              arrayList1 = new ArrayList(); 
            arrayList1.add(keyframe);
          } 
          paramXmlPullParser.next();
        } 
        paramInt = i;
        arrayList = arrayList1;
        continue;
      } 
      break;
    } 
    if (arrayList != null) {
      int i = arrayList.size();
      int j = i;
      if (i > 0) {
        Keyframe keyframe2 = arrayList.get(0);
        Keyframe keyframe1 = arrayList.get(j - 1);
        float f1 = keyframe1.getFraction();
        float f2 = 0.0F;
        i = j;
        if (f1 < 1.0F)
          if (f1 < 0.0F) {
            keyframe1.setFraction(1.0F);
            i = j;
          } else {
            arrayList.add(arrayList.size(), createNewKeyframe(keyframe1, 1.0F));
            i = j + 1;
          }  
        f1 = keyframe2.getFraction();
        int k = i;
        if (f1 != 0.0F)
          if (f1 < 0.0F) {
            keyframe2.setFraction(0.0F);
            k = i;
          } else {
            arrayList.add(0, createNewKeyframe(keyframe2, 0.0F));
            k = i + 1;
          }  
        Keyframe[] arrayOfKeyframe = new Keyframe[k];
        arrayList.toArray(arrayOfKeyframe);
        for (i = 0; i < k; i++) {
          keyframe2 = arrayOfKeyframe[i];
          if (keyframe2.getFraction() < f2)
            if (i == 0) {
              keyframe2.setFraction(f2);
            } else if (i == k - 1) {
              keyframe2.setFraction(1.0F);
              f2 = 0.0F;
            } else {
              int m = i;
              for (j = i + 1; j < k - 1 && arrayOfKeyframe[j].getFraction() < 0.0F; j++)
                m = j; 
              f2 = 0.0F;
              distributeKeyframes(arrayOfKeyframe, arrayOfKeyframe[m + 1].getFraction() - arrayOfKeyframe[i - 1].getFraction(), i, m);
            }  
        } 
        PropertyValuesHolder propertyValuesHolder2 = PropertyValuesHolder.ofKeyframe(paramString, arrayOfKeyframe);
        PropertyValuesHolder propertyValuesHolder1 = propertyValuesHolder2;
        if (paramInt == 3) {
          propertyValuesHolder2.setEvaluator(ArgbEvaluator.getInstance());
          propertyValuesHolder1 = propertyValuesHolder2;
        } 
      } else {
        paramResources = resources;
      } 
    } else {
      paramResources = resources;
    } 
    return (PropertyValuesHolder)paramResources;
  }
  
  public static StateListAnimator loadStateListAnimator(Context paramContext, int paramInt) throws Resources.NotFoundException {
    Resources.NotFoundException notFoundException1;
    Resources.NotFoundException notFoundException2;
    Resources resources = paramContext.getResources();
    ConfigurationBoundResourceCache configurationBoundResourceCache = resources.getStateListAnimatorCache();
    Resources.Theme theme = paramContext.getTheme();
    StateListAnimator stateListAnimator = (StateListAnimator)configurationBoundResourceCache.getInstance(paramInt, resources, theme);
    if (stateListAnimator != null)
      return stateListAnimator; 
    XmlResourceParser xmlResourceParser1 = null;
    XmlResourceParser xmlResourceParser2 = null;
    stateListAnimator = null;
    try {
      XmlResourceParser xmlResourceParser4 = resources.getAnimation(paramInt);
      XmlResourceParser xmlResourceParser3 = xmlResourceParser4;
      xmlResourceParser1 = xmlResourceParser4;
      xmlResourceParser2 = xmlResourceParser4;
      StateListAnimator stateListAnimator2 = createStateListAnimatorFromXml(paramContext, (XmlPullParser)xmlResourceParser4, Xml.asAttributeSet((XmlPullParser)xmlResourceParser4));
      StateListAnimator stateListAnimator1 = stateListAnimator2;
      if (stateListAnimator2 != null) {
        xmlResourceParser3 = xmlResourceParser4;
        xmlResourceParser1 = xmlResourceParser4;
        xmlResourceParser2 = xmlResourceParser4;
        stateListAnimator2.appendChangingConfigurations(getChangingConfigs(resources, paramInt));
        xmlResourceParser3 = xmlResourceParser4;
        xmlResourceParser1 = xmlResourceParser4;
        xmlResourceParser2 = xmlResourceParser4;
        ConstantState<StateListAnimator> constantState = stateListAnimator2.createConstantState();
        stateListAnimator1 = stateListAnimator2;
        if (constantState != null) {
          xmlResourceParser3 = xmlResourceParser4;
          xmlResourceParser1 = xmlResourceParser4;
          xmlResourceParser2 = xmlResourceParser4;
          configurationBoundResourceCache.put(paramInt, theme, constantState);
          xmlResourceParser3 = xmlResourceParser4;
          xmlResourceParser1 = xmlResourceParser4;
          xmlResourceParser2 = xmlResourceParser4;
          stateListAnimator1 = (StateListAnimator)constantState.newInstance(resources, theme);
        } 
      } 
      if (xmlResourceParser4 != null)
        xmlResourceParser4.close(); 
      return stateListAnimator1;
    } catch (XmlPullParserException xmlPullParserException) {
      XmlResourceParser xmlResourceParser = xmlResourceParser2;
      notFoundException2 = new Resources.NotFoundException();
      xmlResourceParser = xmlResourceParser2;
      StringBuilder stringBuilder = new StringBuilder();
      xmlResourceParser = xmlResourceParser2;
      this();
      xmlResourceParser = xmlResourceParser2;
      stringBuilder.append("Can't load state list animator resource ID #0x");
      xmlResourceParser = xmlResourceParser2;
      stringBuilder.append(Integer.toHexString(paramInt));
      xmlResourceParser = xmlResourceParser2;
      this(stringBuilder.toString());
      xmlResourceParser = xmlResourceParser2;
      notFoundException2.initCause((Throwable)xmlPullParserException);
      xmlResourceParser = xmlResourceParser2;
      throw notFoundException2;
    } catch (IOException iOException) {
      notFoundException1 = notFoundException2;
      Resources.NotFoundException notFoundException = new Resources.NotFoundException();
      notFoundException1 = notFoundException2;
      StringBuilder stringBuilder = new StringBuilder();
      notFoundException1 = notFoundException2;
      this();
      notFoundException1 = notFoundException2;
      stringBuilder.append("Can't load state list animator resource ID #0x");
      notFoundException1 = notFoundException2;
      stringBuilder.append(Integer.toHexString(paramInt));
      notFoundException1 = notFoundException2;
      this(stringBuilder.toString());
      notFoundException1 = notFoundException2;
      notFoundException.initCause(iOException);
      notFoundException1 = notFoundException2;
      throw notFoundException;
    } finally {}
    if (notFoundException1 != null)
      notFoundException1.close(); 
    throw paramContext;
  }
  
  private static PropertyValuesHolder[] loadValues(Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws XmlPullParserException, IOException {
    PropertyValuesHolder[] arrayOfPropertyValuesHolder;
    ArrayList<PropertyValuesHolder> arrayList;
    PropertyValuesHolder propertyValuesHolder = null;
    while (true) {
      int i = paramXmlPullParser.getEventType();
      if (i != 3 && i != 1) {
        ArrayList<PropertyValuesHolder> arrayList1;
        if (i != 2) {
          paramXmlPullParser.next();
          continue;
        } 
        PropertyValuesHolder propertyValuesHolder1 = propertyValuesHolder;
        if (paramXmlPullParser.getName().equals("propertyValuesHolder")) {
          TypedArray typedArray;
          if (paramTheme != null) {
            typedArray = paramTheme.obtainStyledAttributes(paramAttributeSet, R.styleable.PropertyValuesHolder, 0, 0);
          } else {
            typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.PropertyValuesHolder);
          } 
          String str = typedArray.getString(3);
          i = typedArray.getInt(2, 4);
          propertyValuesHolder1 = loadPvh(paramResources, paramTheme, paramXmlPullParser, str, i);
          PropertyValuesHolder propertyValuesHolder2 = propertyValuesHolder1;
          if (propertyValuesHolder1 == null)
            propertyValuesHolder2 = getPVH(typedArray, i, 0, 1, str); 
          propertyValuesHolder1 = propertyValuesHolder;
          if (propertyValuesHolder2 != null) {
            propertyValuesHolder1 = propertyValuesHolder;
            if (propertyValuesHolder == null)
              arrayList1 = new ArrayList(); 
            arrayList1.add(propertyValuesHolder2);
          } 
          typedArray.recycle();
        } 
        paramXmlPullParser.next();
        arrayList = arrayList1;
        continue;
      } 
      break;
    } 
    paramResources = null;
    if (arrayList != null) {
      int i = arrayList.size();
      PropertyValuesHolder[] arrayOfPropertyValuesHolder1 = new PropertyValuesHolder[i];
      byte b = 0;
      while (true) {
        arrayOfPropertyValuesHolder = arrayOfPropertyValuesHolder1;
        if (b < i) {
          arrayOfPropertyValuesHolder1[b] = arrayList.get(b);
          b++;
          continue;
        } 
        break;
      } 
    } 
    return arrayOfPropertyValuesHolder;
  }
  
  private static void parseAnimatorFromTypeArray(ValueAnimator paramValueAnimator, TypedArray paramTypedArray1, TypedArray paramTypedArray2, float paramFloat) {
    long l1 = paramTypedArray1.getInt(1, 300);
    long l2 = paramTypedArray1.getInt(2, 0);
    int i = paramTypedArray1.getInt(7, 4);
    int j = i;
    if (i == 4)
      j = inferValueTypeFromValues(paramTypedArray1, 5, 6); 
    PropertyValuesHolder propertyValuesHolder = getPVH(paramTypedArray1, j, 5, 6, "");
    if (propertyValuesHolder != null)
      paramValueAnimator.setValues(new PropertyValuesHolder[] { propertyValuesHolder }); 
    paramValueAnimator.setDuration(l1);
    paramValueAnimator.setStartDelay(l2);
    if (paramTypedArray1.hasValue(3))
      paramValueAnimator.setRepeatCount(paramTypedArray1.getInt(3, 0)); 
    if (paramTypedArray1.hasValue(4))
      paramValueAnimator.setRepeatMode(paramTypedArray1.getInt(4, 1)); 
    if (paramTypedArray2 != null)
      setupObjectAnimator(paramValueAnimator, paramTypedArray2, j, paramFloat); 
  }
  
  private static TypeEvaluator setupAnimatorForPath(ValueAnimator paramValueAnimator, TypedArray paramTypedArray) {
    StringBuilder stringBuilder;
    PathDataEvaluator pathDataEvaluator;
    PathParser.PathData pathData1;
    PathParser.PathData pathData2;
    TypedArray typedArray = null;
    String str1 = paramTypedArray.getString(5);
    String str2 = paramTypedArray.getString(6);
    if (str1 == null) {
      pathData1 = null;
    } else {
      pathData1 = new PathParser.PathData(str1);
    } 
    if (str2 == null) {
      pathData2 = null;
    } else {
      pathData2 = new PathParser.PathData(str2);
    } 
    if (pathData1 != null) {
      if (pathData2 != null) {
        paramValueAnimator.setObjectValues(new Object[] { pathData1, pathData2 });
        if (!PathParser.canMorph(pathData1, pathData2)) {
          stringBuilder = new StringBuilder();
          stringBuilder.append(paramTypedArray.getPositionDescription());
          stringBuilder.append(" Can't morph from ");
          stringBuilder.append(str1);
          stringBuilder.append(" to ");
          stringBuilder.append(str2);
          throw new InflateException(stringBuilder.toString());
        } 
      } else {
        stringBuilder.setObjectValues(new Object[] { pathData1 });
      } 
      pathDataEvaluator = new PathDataEvaluator();
    } else {
      paramTypedArray = typedArray;
      if (pathData2 != null) {
        stringBuilder.setObjectValues(new Object[] { pathData2 });
        pathDataEvaluator = new PathDataEvaluator();
      } 
    } 
    return pathDataEvaluator;
  }
  
  private static void setupObjectAnimator(ValueAnimator paramValueAnimator, TypedArray paramTypedArray, int paramInt, float paramFloat) {
    // Byte code:
    //   0: iload_2
    //   1: istore #4
    //   3: aload_0
    //   4: checkcast android/animation/ObjectAnimator
    //   7: astore #5
    //   9: aload_1
    //   10: iconst_1
    //   11: invokevirtual getString : (I)Ljava/lang/String;
    //   14: astore_0
    //   15: aload_0
    //   16: ifnull -> 237
    //   19: aload_1
    //   20: iconst_2
    //   21: invokevirtual getString : (I)Ljava/lang/String;
    //   24: astore #6
    //   26: aload_1
    //   27: iconst_3
    //   28: invokevirtual getString : (I)Ljava/lang/String;
    //   31: astore #7
    //   33: iload #4
    //   35: iconst_2
    //   36: if_icmpeq -> 48
    //   39: iload #4
    //   41: istore_2
    //   42: iload #4
    //   44: iconst_4
    //   45: if_icmpne -> 50
    //   48: iconst_0
    //   49: istore_2
    //   50: aload #6
    //   52: ifnonnull -> 100
    //   55: aload #7
    //   57: ifnull -> 63
    //   60: goto -> 100
    //   63: new java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial <init> : ()V
    //   70: astore_0
    //   71: aload_0
    //   72: aload_1
    //   73: invokevirtual getPositionDescription : ()Ljava/lang/String;
    //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: aload_0
    //   81: ldc_w ' propertyXName or propertyYName is needed for PathData'
    //   84: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: new android/view/InflateException
    //   91: dup
    //   92: aload_0
    //   93: invokevirtual toString : ()Ljava/lang/String;
    //   96: invokespecial <init> : (Ljava/lang/String;)V
    //   99: athrow
    //   100: aload_0
    //   101: invokestatic createPathFromPathData : (Ljava/lang/String;)Landroid/graphics/Path;
    //   104: ldc_w 0.5
    //   107: fload_3
    //   108: fmul
    //   109: invokestatic ofPath : (Landroid/graphics/Path;F)Landroid/animation/PathKeyframes;
    //   112: astore_0
    //   113: iload_2
    //   114: ifne -> 130
    //   117: aload_0
    //   118: invokevirtual createXFloatKeyframes : ()Landroid/animation/Keyframes$FloatKeyframes;
    //   121: astore_1
    //   122: aload_0
    //   123: invokevirtual createYFloatKeyframes : ()Landroid/animation/Keyframes$FloatKeyframes;
    //   126: astore_0
    //   127: goto -> 140
    //   130: aload_0
    //   131: invokevirtual createXIntKeyframes : ()Landroid/animation/Keyframes$IntKeyframes;
    //   134: astore_1
    //   135: aload_0
    //   136: invokevirtual createYIntKeyframes : ()Landroid/animation/Keyframes$IntKeyframes;
    //   139: astore_0
    //   140: aconst_null
    //   141: astore #8
    //   143: aconst_null
    //   144: astore #9
    //   146: aload #6
    //   148: ifnull -> 159
    //   151: aload #6
    //   153: aload_1
    //   154: invokestatic ofKeyframes : (Ljava/lang/String;Landroid/animation/Keyframes;)Landroid/animation/PropertyValuesHolder;
    //   157: astore #8
    //   159: aload #9
    //   161: astore_1
    //   162: aload #7
    //   164: ifnull -> 174
    //   167: aload #7
    //   169: aload_0
    //   170: invokestatic ofKeyframes : (Ljava/lang/String;Landroid/animation/Keyframes;)Landroid/animation/PropertyValuesHolder;
    //   173: astore_1
    //   174: aload #8
    //   176: ifnonnull -> 195
    //   179: aload #5
    //   181: iconst_1
    //   182: anewarray android/animation/PropertyValuesHolder
    //   185: dup
    //   186: iconst_0
    //   187: aload_1
    //   188: aastore
    //   189: invokevirtual setValues : ([Landroid/animation/PropertyValuesHolder;)V
    //   192: goto -> 234
    //   195: aload_1
    //   196: ifnonnull -> 216
    //   199: aload #5
    //   201: iconst_1
    //   202: anewarray android/animation/PropertyValuesHolder
    //   205: dup
    //   206: iconst_0
    //   207: aload #8
    //   209: aastore
    //   210: invokevirtual setValues : ([Landroid/animation/PropertyValuesHolder;)V
    //   213: goto -> 234
    //   216: aload #5
    //   218: iconst_2
    //   219: anewarray android/animation/PropertyValuesHolder
    //   222: dup
    //   223: iconst_0
    //   224: aload #8
    //   226: aastore
    //   227: dup
    //   228: iconst_1
    //   229: aload_1
    //   230: aastore
    //   231: invokevirtual setValues : ([Landroid/animation/PropertyValuesHolder;)V
    //   234: goto -> 247
    //   237: aload #5
    //   239: aload_1
    //   240: iconst_0
    //   241: invokevirtual getString : (I)Ljava/lang/String;
    //   244: invokevirtual setPropertyName : (Ljava/lang/String;)V
    //   247: return
  }
  
  private static void setupValues(ValueAnimator paramValueAnimator, TypedArray paramTypedArray, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, boolean paramBoolean3, int paramInt2) {
    if (paramBoolean1) {
      if (paramBoolean2) {
        float f;
        if (paramInt1 == 5) {
          f = paramTypedArray.getDimension(5, 0.0F);
        } else {
          f = paramTypedArray.getFloat(5, 0.0F);
        } 
        if (paramBoolean3) {
          float f1;
          if (paramInt2 == 5) {
            f1 = paramTypedArray.getDimension(6, 0.0F);
          } else {
            f1 = paramTypedArray.getFloat(6, 0.0F);
          } 
          paramValueAnimator.setFloatValues(new float[] { f, f1 });
        } else {
          paramValueAnimator.setFloatValues(new float[] { f });
        } 
      } else {
        float f;
        if (paramInt2 == 5) {
          f = paramTypedArray.getDimension(6, 0.0F);
        } else {
          f = paramTypedArray.getFloat(6, 0.0F);
        } 
        paramValueAnimator.setFloatValues(new float[] { f });
      } 
    } else if (paramBoolean2) {
      if (paramInt1 == 5) {
        paramInt1 = (int)paramTypedArray.getDimension(5, 0.0F);
      } else if (isColorType(paramInt1)) {
        paramInt1 = paramTypedArray.getColor(5, 0);
      } else {
        paramInt1 = paramTypedArray.getInt(5, 0);
      } 
      if (paramBoolean3) {
        if (paramInt2 == 5) {
          paramInt2 = (int)paramTypedArray.getDimension(6, 0.0F);
        } else if (isColorType(paramInt2)) {
          paramInt2 = paramTypedArray.getColor(6, 0);
        } else {
          paramInt2 = paramTypedArray.getInt(6, 0);
        } 
        paramValueAnimator.setIntValues(new int[] { paramInt1, paramInt2 });
      } else {
        paramValueAnimator.setIntValues(new int[] { paramInt1 });
      } 
    } else if (paramBoolean3) {
      if (paramInt2 == 5) {
        paramInt1 = (int)paramTypedArray.getDimension(6, 0.0F);
      } else if (isColorType(paramInt2)) {
        paramInt1 = paramTypedArray.getColor(6, 0);
      } else {
        paramInt1 = paramTypedArray.getInt(6, 0);
      } 
      paramValueAnimator.setIntValues(new int[] { paramInt1 });
    } 
  }
  
  private static class PathDataEvaluator implements TypeEvaluator<PathParser.PathData> {
    private final PathParser.PathData mPathData = new PathParser.PathData();
    
    private PathDataEvaluator() {}
    
    public PathParser.PathData evaluate(float param1Float, PathParser.PathData param1PathData1, PathParser.PathData param1PathData2) {
      if (PathParser.interpolatePathData(this.mPathData, param1PathData1, param1PathData2, param1Float))
        return this.mPathData; 
      throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimatorInflater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */