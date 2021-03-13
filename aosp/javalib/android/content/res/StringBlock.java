package android.content.res;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Annotation;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.LineHeightSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.SparseArray;
import java.io.Closeable;

public final class StringBlock implements Closeable {
  private static final String TAG = "AssetManager";
  
  private static final boolean localLOGV = false;
  
  private final long mNative;
  
  private boolean mOpen = true;
  
  private final boolean mOwnsNative;
  
  private SparseArray<CharSequence> mSparseStrings;
  
  private CharSequence[] mStrings;
  
  StyleIDs mStyleIDs = null;
  
  private final boolean mUseSparse;
  
  public StringBlock(long paramLong, boolean paramBoolean) {
    this.mNative = paramLong;
    this.mUseSparse = paramBoolean;
    this.mOwnsNative = false;
  }
  
  public StringBlock(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    this.mNative = nativeCreate(paramArrayOfbyte, paramInt1, paramInt2);
    this.mUseSparse = paramBoolean;
    this.mOwnsNative = true;
  }
  
  public StringBlock(byte[] paramArrayOfbyte, boolean paramBoolean) {
    this.mNative = nativeCreate(paramArrayOfbyte, 0, paramArrayOfbyte.length);
    this.mUseSparse = paramBoolean;
    this.mOwnsNative = true;
  }
  
  private static void addParagraphSpan(Spannable paramSpannable, Object paramObject, int paramInt1, int paramInt2) {
    int i = paramSpannable.length();
    int j = paramInt1;
    if (paramInt1 != 0) {
      j = paramInt1;
      if (paramInt1 != i) {
        j = paramInt1;
        if (paramSpannable.charAt(paramInt1 - 1) != '\n') {
          j = paramInt1;
          while (true) {
            paramInt1 = j - 1;
            j = paramInt1;
            if (paramInt1 > 0) {
              j = paramInt1;
              if (paramSpannable.charAt(paramInt1 - 1) == '\n') {
                j = paramInt1;
                break;
              } 
              continue;
            } 
            break;
          } 
        } 
      } 
    } 
    paramInt1 = paramInt2;
    if (paramInt2 != 0) {
      paramInt1 = paramInt2;
      if (paramInt2 != i) {
        paramInt1 = paramInt2;
        if (paramSpannable.charAt(paramInt2 - 1) != '\n') {
          paramInt1 = paramInt2;
          while (true) {
            paramInt2 = paramInt1 + 1;
            paramInt1 = paramInt2;
            if (paramInt2 < i) {
              paramInt1 = paramInt2;
              if (paramSpannable.charAt(paramInt2 - 1) == '\n') {
                paramInt1 = paramInt2;
                break;
              } 
              continue;
            } 
            break;
          } 
        } 
      } 
    } 
    paramSpannable.setSpan(paramObject, j, paramInt1, 51);
  }
  
  private CharSequence applyStyles(String paramString, int[] paramArrayOfint, StyleIDs paramStyleIDs) {
    if (paramArrayOfint.length == 0)
      return paramString; 
    SpannableString spannableString = new SpannableString(paramString);
    for (byte b = 0; b < paramArrayOfint.length; b += 3) {
      int i = paramArrayOfint[b];
      if (i == paramStyleIDs.boldId) {
        spannableString.setSpan(new StyleSpan(1), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33);
      } else if (i == paramStyleIDs.italicId) {
        spannableString.setSpan(new StyleSpan(2), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33);
      } else if (i == paramStyleIDs.underlineId) {
        spannableString.setSpan(new UnderlineSpan(), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33);
      } else if (i == paramStyleIDs.ttId) {
        spannableString.setSpan(new TypefaceSpan("monospace"), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33);
      } else if (i == paramStyleIDs.bigId) {
        spannableString.setSpan(new RelativeSizeSpan(1.25F), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33);
      } else if (i == paramStyleIDs.smallId) {
        spannableString.setSpan(new RelativeSizeSpan(0.8F), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33);
      } else if (i == paramStyleIDs.subId) {
        spannableString.setSpan(new SubscriptSpan(), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33);
      } else if (i == paramStyleIDs.supId) {
        spannableString.setSpan(new SuperscriptSpan(), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33);
      } else if (i == paramStyleIDs.strikeId) {
        spannableString.setSpan(new StrikethroughSpan(), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33);
      } else if (i == paramStyleIDs.listItemId) {
        addParagraphSpan((Spannable)spannableString, new BulletSpan(10), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1);
      } else if (i == paramStyleIDs.marqueeId) {
        spannableString.setSpan(TextUtils.TruncateAt.MARQUEE, paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 18);
      } else {
        String str = nativeGetString(this.mNative, i);
        if (str.startsWith("font;")) {
          String str1 = subtag(str, ";height=");
          if (str1 != null)
            addParagraphSpan((Spannable)spannableString, new Height(Integer.parseInt(str1)), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1); 
          str1 = subtag(str, ";size=");
          if (str1 != null)
            spannableString.setSpan(new AbsoluteSizeSpan(Integer.parseInt(str1), true), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33); 
          str1 = subtag(str, ";fgcolor=");
          if (str1 != null)
            spannableString.setSpan(getColor(str1, true), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33); 
          str1 = subtag(str, ";color=");
          if (str1 != null)
            spannableString.setSpan(getColor(str1, true), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33); 
          str1 = subtag(str, ";bgcolor=");
          if (str1 != null)
            spannableString.setSpan(getColor(str1, false), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33); 
          str = subtag(str, ";face=");
          if (str != null)
            spannableString.setSpan(new TypefaceSpan(str), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33); 
        } else if (str.startsWith("a;")) {
          str = subtag(str, ";href=");
          if (str != null)
            spannableString.setSpan(new URLSpan(str), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33); 
        } else if (str.startsWith("annotation;")) {
          int j = str.length();
          int k;
          for (k = str.indexOf(';'); k < j; k = i) {
            int m = str.indexOf('=', k);
            if (m < 0)
              break; 
            int n = str.indexOf(';', m);
            i = n;
            if (n < 0)
              i = j; 
            spannableString.setSpan(new Annotation(str.substring(k + 1, m), str.substring(m + 1, i)), paramArrayOfint[b + 1], paramArrayOfint[b + 2] + 1, 33);
          } 
        } 
      } 
    } 
    return (CharSequence)new SpannedString((CharSequence)spannableString);
  }
  
  private static CharacterStyle getColor(String paramString, boolean paramBoolean) {
    int i = -16777216;
    int j = i;
    if (!TextUtils.isEmpty(paramString)) {
      ColorStateList colorStateList;
      if (paramString.startsWith("@")) {
        Resources resources = Resources.getSystem();
        int k = resources.getIdentifier(paramString.substring(1), "color", "android");
        j = i;
        if (k != 0) {
          colorStateList = resources.getColorStateList(k, null);
          if (paramBoolean)
            return (CharacterStyle)new TextAppearanceSpan(null, 0, 0, colorStateList, null); 
          j = colorStateList.getDefaultColor();
        } 
      } else {
        try {
          j = Color.parseColor((String)colorStateList);
        } catch (IllegalArgumentException illegalArgumentException) {
          j = -16777216;
        } 
      } 
    } 
    return (CharacterStyle)(paramBoolean ? new ForegroundColorSpan(j) : new BackgroundColorSpan(j));
  }
  
  private static native long nativeCreate(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  private static native void nativeDestroy(long paramLong);
  
  private static native int nativeGetSize(long paramLong);
  
  private static native String nativeGetString(long paramLong, int paramInt);
  
  private static native int[] nativeGetStyle(long paramLong, int paramInt);
  
  private static String subtag(String paramString1, String paramString2) {
    int i = paramString1.indexOf(paramString2);
    if (i < 0)
      return null; 
    i += paramString2.length();
    int j = paramString1.indexOf(';', i);
    return (j < 0) ? paramString1.substring(i) : paramString1.substring(i, j);
  }
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mOpen : Z
    //   6: ifeq -> 28
    //   9: aload_0
    //   10: iconst_0
    //   11: putfield mOpen : Z
    //   14: aload_0
    //   15: getfield mOwnsNative : Z
    //   18: ifeq -> 28
    //   21: aload_0
    //   22: getfield mNative : J
    //   25: invokestatic nativeDestroy : (J)V
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   2	28	31	finally
    //   28	30	31	finally
    //   32	34	31	finally
  }
  
  protected void finalize() throws Throwable {
    try {
      super.finalize();
      return;
    } finally {
      close();
    } 
  }
  
  public CharSequence get(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mStrings : [Ljava/lang/CharSequence;
    //   6: ifnull -> 27
    //   9: aload_0
    //   10: getfield mStrings : [Ljava/lang/CharSequence;
    //   13: iload_1
    //   14: aaload
    //   15: astore_2
    //   16: aload_2
    //   17: ifnull -> 24
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: areturn
    //   24: goto -> 103
    //   27: aload_0
    //   28: getfield mSparseStrings : Landroid/util/SparseArray;
    //   31: ifnull -> 57
    //   34: aload_0
    //   35: getfield mSparseStrings : Landroid/util/SparseArray;
    //   38: iload_1
    //   39: invokevirtual get : (I)Ljava/lang/Object;
    //   42: checkcast java/lang/CharSequence
    //   45: astore_2
    //   46: aload_2
    //   47: ifnull -> 54
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_2
    //   53: areturn
    //   54: goto -> 103
    //   57: aload_0
    //   58: getfield mNative : J
    //   61: invokestatic nativeGetSize : (J)I
    //   64: istore_3
    //   65: aload_0
    //   66: getfield mUseSparse : Z
    //   69: ifeq -> 95
    //   72: iload_3
    //   73: sipush #250
    //   76: if_icmple -> 95
    //   79: new android/util/SparseArray
    //   82: astore_2
    //   83: aload_2
    //   84: invokespecial <init> : ()V
    //   87: aload_0
    //   88: aload_2
    //   89: putfield mSparseStrings : Landroid/util/SparseArray;
    //   92: goto -> 103
    //   95: aload_0
    //   96: iload_3
    //   97: anewarray java/lang/CharSequence
    //   100: putfield mStrings : [Ljava/lang/CharSequence;
    //   103: aload_0
    //   104: getfield mNative : J
    //   107: iload_1
    //   108: invokestatic nativeGetString : (JI)Ljava/lang/String;
    //   111: astore #4
    //   113: aload #4
    //   115: astore_2
    //   116: aload_0
    //   117: getfield mNative : J
    //   120: iload_1
    //   121: invokestatic nativeGetStyle : (JI)[I
    //   124: astore #5
    //   126: aload #5
    //   128: ifnull -> 580
    //   131: aload_0
    //   132: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   135: ifnonnull -> 151
    //   138: new android/content/res/StringBlock$StyleIDs
    //   141: astore_2
    //   142: aload_2
    //   143: invokespecial <init> : ()V
    //   146: aload_0
    //   147: aload_2
    //   148: putfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   151: iconst_0
    //   152: istore_3
    //   153: iload_3
    //   154: aload #5
    //   156: arraylength
    //   157: if_icmpge -> 567
    //   160: aload #5
    //   162: iload_3
    //   163: iaload
    //   164: istore #6
    //   166: iload #6
    //   168: aload_0
    //   169: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   172: invokestatic access$000 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   175: if_icmpeq -> 561
    //   178: iload #6
    //   180: aload_0
    //   181: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   184: invokestatic access$100 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   187: if_icmpeq -> 561
    //   190: iload #6
    //   192: aload_0
    //   193: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   196: invokestatic access$200 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   199: if_icmpeq -> 561
    //   202: iload #6
    //   204: aload_0
    //   205: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   208: invokestatic access$300 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   211: if_icmpeq -> 561
    //   214: iload #6
    //   216: aload_0
    //   217: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   220: invokestatic access$400 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   223: if_icmpeq -> 561
    //   226: iload #6
    //   228: aload_0
    //   229: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   232: invokestatic access$500 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   235: if_icmpeq -> 561
    //   238: iload #6
    //   240: aload_0
    //   241: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   244: invokestatic access$600 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   247: if_icmpeq -> 561
    //   250: iload #6
    //   252: aload_0
    //   253: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   256: invokestatic access$700 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   259: if_icmpeq -> 561
    //   262: iload #6
    //   264: aload_0
    //   265: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   268: invokestatic access$800 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   271: if_icmpeq -> 561
    //   274: iload #6
    //   276: aload_0
    //   277: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   280: invokestatic access$900 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   283: if_icmpeq -> 561
    //   286: iload #6
    //   288: aload_0
    //   289: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   292: invokestatic access$1000 : (Landroid/content/res/StringBlock$StyleIDs;)I
    //   295: if_icmpne -> 301
    //   298: goto -> 561
    //   301: aload_0
    //   302: getfield mNative : J
    //   305: iload #6
    //   307: invokestatic nativeGetString : (JI)Ljava/lang/String;
    //   310: astore_2
    //   311: aload_2
    //   312: ldc_w 'b'
    //   315: invokevirtual equals : (Ljava/lang/Object;)Z
    //   318: ifeq -> 334
    //   321: aload_0
    //   322: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   325: iload #6
    //   327: invokestatic access$002 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   330: pop
    //   331: goto -> 561
    //   334: aload_2
    //   335: ldc_w 'i'
    //   338: invokevirtual equals : (Ljava/lang/Object;)Z
    //   341: ifeq -> 357
    //   344: aload_0
    //   345: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   348: iload #6
    //   350: invokestatic access$102 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   353: pop
    //   354: goto -> 561
    //   357: aload_2
    //   358: ldc_w 'u'
    //   361: invokevirtual equals : (Ljava/lang/Object;)Z
    //   364: ifeq -> 380
    //   367: aload_0
    //   368: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   371: iload #6
    //   373: invokestatic access$202 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   376: pop
    //   377: goto -> 561
    //   380: aload_2
    //   381: ldc_w 'tt'
    //   384: invokevirtual equals : (Ljava/lang/Object;)Z
    //   387: ifeq -> 403
    //   390: aload_0
    //   391: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   394: iload #6
    //   396: invokestatic access$302 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   399: pop
    //   400: goto -> 561
    //   403: aload_2
    //   404: ldc_w 'big'
    //   407: invokevirtual equals : (Ljava/lang/Object;)Z
    //   410: ifeq -> 426
    //   413: aload_0
    //   414: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   417: iload #6
    //   419: invokestatic access$402 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   422: pop
    //   423: goto -> 561
    //   426: aload_2
    //   427: ldc_w 'small'
    //   430: invokevirtual equals : (Ljava/lang/Object;)Z
    //   433: ifeq -> 449
    //   436: aload_0
    //   437: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   440: iload #6
    //   442: invokestatic access$502 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   445: pop
    //   446: goto -> 561
    //   449: aload_2
    //   450: ldc_w 'sup'
    //   453: invokevirtual equals : (Ljava/lang/Object;)Z
    //   456: ifeq -> 472
    //   459: aload_0
    //   460: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   463: iload #6
    //   465: invokestatic access$702 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   468: pop
    //   469: goto -> 561
    //   472: aload_2
    //   473: ldc_w 'sub'
    //   476: invokevirtual equals : (Ljava/lang/Object;)Z
    //   479: ifeq -> 495
    //   482: aload_0
    //   483: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   486: iload #6
    //   488: invokestatic access$602 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   491: pop
    //   492: goto -> 561
    //   495: aload_2
    //   496: ldc_w 'strike'
    //   499: invokevirtual equals : (Ljava/lang/Object;)Z
    //   502: ifeq -> 518
    //   505: aload_0
    //   506: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   509: iload #6
    //   511: invokestatic access$802 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   514: pop
    //   515: goto -> 561
    //   518: aload_2
    //   519: ldc_w 'li'
    //   522: invokevirtual equals : (Ljava/lang/Object;)Z
    //   525: ifeq -> 541
    //   528: aload_0
    //   529: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   532: iload #6
    //   534: invokestatic access$902 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   537: pop
    //   538: goto -> 561
    //   541: aload_2
    //   542: ldc_w 'marquee'
    //   545: invokevirtual equals : (Ljava/lang/Object;)Z
    //   548: ifeq -> 561
    //   551: aload_0
    //   552: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   555: iload #6
    //   557: invokestatic access$1002 : (Landroid/content/res/StringBlock$StyleIDs;I)I
    //   560: pop
    //   561: iinc #3, 3
    //   564: goto -> 153
    //   567: aload_0
    //   568: aload #4
    //   570: aload #5
    //   572: aload_0
    //   573: getfield mStyleIDs : Landroid/content/res/StringBlock$StyleIDs;
    //   576: invokespecial applyStyles : (Ljava/lang/String;[ILandroid/content/res/StringBlock$StyleIDs;)Ljava/lang/CharSequence;
    //   579: astore_2
    //   580: aload_0
    //   581: getfield mStrings : [Ljava/lang/CharSequence;
    //   584: ifnull -> 597
    //   587: aload_0
    //   588: getfield mStrings : [Ljava/lang/CharSequence;
    //   591: iload_1
    //   592: aload_2
    //   593: aastore
    //   594: goto -> 606
    //   597: aload_0
    //   598: getfield mSparseStrings : Landroid/util/SparseArray;
    //   601: iload_1
    //   602: aload_2
    //   603: invokevirtual put : (ILjava/lang/Object;)V
    //   606: aload_0
    //   607: monitorexit
    //   608: aload_2
    //   609: areturn
    //   610: astore_2
    //   611: aload_0
    //   612: monitorexit
    //   613: aload_2
    //   614: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	610	finally
    //   20	22	610	finally
    //   27	46	610	finally
    //   50	52	610	finally
    //   57	72	610	finally
    //   79	92	610	finally
    //   95	103	610	finally
    //   103	113	610	finally
    //   116	126	610	finally
    //   131	151	610	finally
    //   153	160	610	finally
    //   166	298	610	finally
    //   301	331	610	finally
    //   334	354	610	finally
    //   357	377	610	finally
    //   380	400	610	finally
    //   403	423	610	finally
    //   426	446	610	finally
    //   449	469	610	finally
    //   472	492	610	finally
    //   495	515	610	finally
    //   518	538	610	finally
    //   541	561	610	finally
    //   567	580	610	finally
    //   580	594	610	finally
    //   597	606	610	finally
    //   606	608	610	finally
    //   611	613	610	finally
  }
  
  private static class Height implements LineHeightSpan.WithDensity {
    private static float sProportion = 0.0F;
    
    private int mSize;
    
    public Height(int param1Int) {
      this.mSize = param1Int;
    }
    
    public void chooseHeight(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3, int param1Int4, Paint.FontMetricsInt param1FontMetricsInt) {
      chooseHeight(param1CharSequence, param1Int1, param1Int2, param1Int3, param1Int4, param1FontMetricsInt, null);
    }
    
    public void chooseHeight(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3, int param1Int4, Paint.FontMetricsInt param1FontMetricsInt, TextPaint param1TextPaint) {
      param1Int2 = this.mSize;
      param1Int1 = param1Int2;
      if (param1TextPaint != null)
        param1Int1 = (int)(param1Int2 * param1TextPaint.density); 
      if (param1FontMetricsInt.bottom - param1FontMetricsInt.top < param1Int1) {
        param1FontMetricsInt.top = param1FontMetricsInt.bottom - param1Int1;
        param1FontMetricsInt.ascent -= param1Int1;
      } else {
        if (sProportion == 0.0F) {
          Paint paint = new Paint();
          paint.setTextSize(100.0F);
          Rect rect = new Rect();
          paint.getTextBounds("ABCDEFG", 0, 7, rect);
          sProportion = rect.top / paint.ascent();
        } 
        param1Int2 = (int)Math.ceil((-param1FontMetricsInt.top * sProportion));
        if (param1Int1 - param1FontMetricsInt.descent >= param1Int2) {
          param1FontMetricsInt.top = param1FontMetricsInt.bottom - param1Int1;
          param1FontMetricsInt.ascent = param1FontMetricsInt.descent - param1Int1;
        } else if (param1Int1 >= param1Int2) {
          param1Int2 = -param1Int2;
          param1FontMetricsInt.ascent = param1Int2;
          param1FontMetricsInt.top = param1Int2;
          param1Int1 = param1FontMetricsInt.top + param1Int1;
          param1FontMetricsInt.descent = param1Int1;
          param1FontMetricsInt.bottom = param1Int1;
        } else {
          param1Int1 = -param1Int1;
          param1FontMetricsInt.ascent = param1Int1;
          param1FontMetricsInt.top = param1Int1;
          param1FontMetricsInt.descent = 0;
          param1FontMetricsInt.bottom = 0;
        } 
      } 
    }
  }
  
  static final class StyleIDs {
    private int bigId = -1;
    
    private int boldId = -1;
    
    private int italicId = -1;
    
    private int listItemId = -1;
    
    private int marqueeId = -1;
    
    private int smallId = -1;
    
    private int strikeId = -1;
    
    private int subId = -1;
    
    private int supId = -1;
    
    private int ttId = -1;
    
    private int underlineId = -1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/StringBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */