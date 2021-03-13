package android.content;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import libcore.io.IoUtils;

public class Item {
  final String mHtmlText;
  
  final Intent mIntent;
  
  final CharSequence mText;
  
  Uri mUri;
  
  public Item(Item paramItem) {
    this.mText = paramItem.mText;
    this.mHtmlText = paramItem.mHtmlText;
    this.mIntent = paramItem.mIntent;
    this.mUri = paramItem.mUri;
  }
  
  public Item(Intent paramIntent) {
    this.mText = null;
    this.mHtmlText = null;
    this.mIntent = paramIntent;
    this.mUri = null;
  }
  
  public Item(Uri paramUri) {
    this.mText = null;
    this.mHtmlText = null;
    this.mIntent = null;
    this.mUri = paramUri;
  }
  
  public Item(CharSequence paramCharSequence) {
    this.mText = paramCharSequence;
    this.mHtmlText = null;
    this.mIntent = null;
    this.mUri = null;
  }
  
  public Item(CharSequence paramCharSequence, Intent paramIntent, Uri paramUri) {
    this.mText = paramCharSequence;
    this.mHtmlText = null;
    this.mIntent = paramIntent;
    this.mUri = paramUri;
  }
  
  public Item(CharSequence paramCharSequence, String paramString) {
    this.mText = paramCharSequence;
    this.mHtmlText = paramString;
    this.mIntent = null;
    this.mUri = null;
  }
  
  public Item(CharSequence paramCharSequence, String paramString, Intent paramIntent, Uri paramUri) {
    if (paramString == null || paramCharSequence != null) {
      this.mText = paramCharSequence;
      this.mHtmlText = paramString;
      this.mIntent = paramIntent;
      this.mUri = paramUri;
      return;
    } 
    throw new IllegalArgumentException("Plain text must be supplied if HTML text is supplied");
  }
  
  private CharSequence coerceToHtmlOrStyledText(Context paramContext, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mUri : Landroid/net/Uri;
    //   4: ifnull -> 716
    //   7: aconst_null
    //   8: astore_3
    //   9: aload_1
    //   10: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   13: aload_0
    //   14: getfield mUri : Landroid/net/Uri;
    //   17: ldc 'text/*'
    //   19: invokevirtual getStreamTypes : (Landroid/net/Uri;Ljava/lang/String;)[Ljava/lang/String;
    //   22: astore #4
    //   24: aload #4
    //   26: astore_3
    //   27: goto -> 32
    //   30: astore #4
    //   32: iconst_0
    //   33: istore #5
    //   35: iconst_0
    //   36: istore #6
    //   38: iconst_0
    //   39: istore #7
    //   41: iconst_0
    //   42: istore #8
    //   44: ldc 'text/html'
    //   46: astore #9
    //   48: aload_3
    //   49: ifnull -> 127
    //   52: aload_3
    //   53: arraylength
    //   54: istore #10
    //   56: iconst_0
    //   57: istore #11
    //   59: iload #6
    //   61: istore #5
    //   63: iload #8
    //   65: istore #7
    //   67: iload #11
    //   69: iload #10
    //   71: if_icmpge -> 127
    //   74: aload_3
    //   75: iload #11
    //   77: aaload
    //   78: astore #4
    //   80: ldc 'text/html'
    //   82: aload #4
    //   84: invokevirtual equals : (Ljava/lang/Object;)Z
    //   87: ifeq -> 96
    //   90: iconst_1
    //   91: istore #5
    //   93: goto -> 117
    //   96: iload #6
    //   98: istore #5
    //   100: aload #4
    //   102: ldc 'text/'
    //   104: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   107: ifeq -> 117
    //   110: iconst_1
    //   111: istore #8
    //   113: iload #6
    //   115: istore #5
    //   117: iinc #11, 1
    //   120: iload #5
    //   122: istore #6
    //   124: goto -> 59
    //   127: iload #5
    //   129: ifne -> 137
    //   132: iload #7
    //   134: ifeq -> 633
    //   137: aconst_null
    //   138: astore #12
    //   140: aconst_null
    //   141: astore #13
    //   143: aconst_null
    //   144: astore #14
    //   146: aconst_null
    //   147: astore #15
    //   149: aload #15
    //   151: astore_3
    //   152: aload #12
    //   154: astore #4
    //   156: aload #13
    //   158: astore #16
    //   160: aload #14
    //   162: astore #17
    //   164: aload_1
    //   165: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   168: astore #18
    //   170: aload #15
    //   172: astore_3
    //   173: aload #12
    //   175: astore #4
    //   177: aload #13
    //   179: astore #16
    //   181: aload #14
    //   183: astore #17
    //   185: aload_0
    //   186: getfield mUri : Landroid/net/Uri;
    //   189: astore #19
    //   191: iload #5
    //   193: ifeq -> 202
    //   196: aload #9
    //   198: astore_1
    //   199: goto -> 205
    //   202: ldc 'text/plain'
    //   204: astore_1
    //   205: aload #15
    //   207: astore_3
    //   208: aload #12
    //   210: astore #4
    //   212: aload #13
    //   214: astore #16
    //   216: aload #14
    //   218: astore #17
    //   220: aload #18
    //   222: aload #19
    //   224: aload_1
    //   225: aconst_null
    //   226: invokevirtual openTypedAssetFileDescriptor : (Landroid/net/Uri;Ljava/lang/String;Landroid/os/Bundle;)Landroid/content/res/AssetFileDescriptor;
    //   229: invokevirtual createInputStream : ()Ljava/io/FileInputStream;
    //   232: astore_1
    //   233: aload_1
    //   234: astore_3
    //   235: aload_1
    //   236: astore #4
    //   238: aload_1
    //   239: astore #16
    //   241: aload_1
    //   242: astore #17
    //   244: new java/io/InputStreamReader
    //   247: astore #15
    //   249: aload_1
    //   250: astore_3
    //   251: aload_1
    //   252: astore #4
    //   254: aload_1
    //   255: astore #16
    //   257: aload_1
    //   258: astore #17
    //   260: aload #15
    //   262: aload_1
    //   263: ldc 'UTF-8'
    //   265: invokespecial <init> : (Ljava/io/InputStream;Ljava/lang/String;)V
    //   268: aload_1
    //   269: astore_3
    //   270: aload_1
    //   271: astore #4
    //   273: aload_1
    //   274: astore #16
    //   276: aload_1
    //   277: astore #17
    //   279: new java/lang/StringBuilder
    //   282: astore #12
    //   284: aload_1
    //   285: astore_3
    //   286: aload_1
    //   287: astore #4
    //   289: aload_1
    //   290: astore #16
    //   292: aload_1
    //   293: astore #17
    //   295: aload #12
    //   297: sipush #128
    //   300: invokespecial <init> : (I)V
    //   303: aload_1
    //   304: astore_3
    //   305: aload_1
    //   306: astore #4
    //   308: aload_1
    //   309: astore #16
    //   311: aload_1
    //   312: astore #17
    //   314: sipush #8192
    //   317: newarray char
    //   319: astore #9
    //   321: aload_1
    //   322: astore_3
    //   323: aload_1
    //   324: astore #4
    //   326: aload_1
    //   327: astore #16
    //   329: aload_1
    //   330: astore #17
    //   332: aload #15
    //   334: aload #9
    //   336: invokevirtual read : ([C)I
    //   339: istore #6
    //   341: iload #6
    //   343: ifle -> 371
    //   346: aload_1
    //   347: astore_3
    //   348: aload_1
    //   349: astore #4
    //   351: aload_1
    //   352: astore #16
    //   354: aload_1
    //   355: astore #17
    //   357: aload #12
    //   359: aload #9
    //   361: iconst_0
    //   362: iload #6
    //   364: invokevirtual append : ([CII)Ljava/lang/StringBuilder;
    //   367: pop
    //   368: goto -> 321
    //   371: aload_1
    //   372: astore_3
    //   373: aload_1
    //   374: astore #4
    //   376: aload_1
    //   377: astore #16
    //   379: aload_1
    //   380: astore #17
    //   382: aload #12
    //   384: invokevirtual toString : ()Ljava/lang/String;
    //   387: astore #15
    //   389: iload #5
    //   391: ifeq -> 493
    //   394: iload_2
    //   395: ifeq -> 460
    //   398: aload_1
    //   399: astore_3
    //   400: aload_1
    //   401: astore #4
    //   403: aload_1
    //   404: astore #16
    //   406: aload_1
    //   407: astore #17
    //   409: aload #15
    //   411: invokestatic fromHtml : (Ljava/lang/String;)Landroid/text/Spanned;
    //   414: astore #9
    //   416: aload #9
    //   418: ifnull -> 427
    //   421: aload #9
    //   423: astore_3
    //   424: goto -> 430
    //   427: aload #15
    //   429: astore_3
    //   430: aload_1
    //   431: ifnull -> 442
    //   434: aload_1
    //   435: invokevirtual close : ()V
    //   438: goto -> 442
    //   441: astore_1
    //   442: aload_3
    //   443: areturn
    //   444: astore_3
    //   445: aload_1
    //   446: ifnull -> 457
    //   449: aload_1
    //   450: invokevirtual close : ()V
    //   453: goto -> 457
    //   456: astore_1
    //   457: aload #15
    //   459: areturn
    //   460: aload_1
    //   461: astore_3
    //   462: aload_1
    //   463: astore #4
    //   465: aload_1
    //   466: astore #16
    //   468: aload_1
    //   469: astore #17
    //   471: aload #15
    //   473: invokevirtual toString : ()Ljava/lang/String;
    //   476: astore #15
    //   478: aload_1
    //   479: ifnull -> 490
    //   482: aload_1
    //   483: invokevirtual close : ()V
    //   486: goto -> 490
    //   489: astore_1
    //   490: aload #15
    //   492: areturn
    //   493: iload_2
    //   494: ifeq -> 512
    //   497: aload_1
    //   498: ifnull -> 509
    //   501: aload_1
    //   502: invokevirtual close : ()V
    //   505: goto -> 509
    //   508: astore_1
    //   509: aload #15
    //   511: areturn
    //   512: aload_1
    //   513: astore_3
    //   514: aload_1
    //   515: astore #4
    //   517: aload_1
    //   518: astore #16
    //   520: aload_1
    //   521: astore #17
    //   523: aload #15
    //   525: invokestatic escapeHtml : (Ljava/lang/CharSequence;)Ljava/lang/String;
    //   528: astore #15
    //   530: aload_1
    //   531: ifnull -> 542
    //   534: aload_1
    //   535: invokevirtual close : ()V
    //   538: goto -> 542
    //   541: astore_1
    //   542: aload #15
    //   544: areturn
    //   545: astore_1
    //   546: goto -> 702
    //   549: astore_1
    //   550: aload #4
    //   552: astore_3
    //   553: ldc 'ClipData'
    //   555: ldc 'Failure loading text'
    //   557: aload_1
    //   558: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   561: pop
    //   562: aload #4
    //   564: astore_3
    //   565: aload_1
    //   566: invokevirtual toString : ()Ljava/lang/String;
    //   569: invokestatic escapeHtml : (Ljava/lang/CharSequence;)Ljava/lang/String;
    //   572: astore_1
    //   573: aload #4
    //   575: ifnull -> 587
    //   578: aload #4
    //   580: invokevirtual close : ()V
    //   583: goto -> 587
    //   586: astore_3
    //   587: aload_1
    //   588: areturn
    //   589: astore_1
    //   590: aload #16
    //   592: ifnull -> 633
    //   595: aload #16
    //   597: invokevirtual close : ()V
    //   600: goto -> 633
    //   603: astore_1
    //   604: goto -> 600
    //   607: astore_1
    //   608: aload #17
    //   610: astore_3
    //   611: ldc 'ClipData'
    //   613: ldc 'Failure opening stream'
    //   615: aload_1
    //   616: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   619: pop
    //   620: aload #17
    //   622: ifnull -> 633
    //   625: aload #17
    //   627: invokevirtual close : ()V
    //   630: goto -> 600
    //   633: aload_0
    //   634: getfield mUri : Landroid/net/Uri;
    //   637: invokevirtual getScheme : ()Ljava/lang/String;
    //   640: astore_1
    //   641: ldc 'content'
    //   643: aload_1
    //   644: invokevirtual equals : (Ljava/lang/Object;)Z
    //   647: ifne -> 699
    //   650: ldc 'android.resource'
    //   652: aload_1
    //   653: invokevirtual equals : (Ljava/lang/Object;)Z
    //   656: ifne -> 699
    //   659: ldc 'file'
    //   661: aload_1
    //   662: invokevirtual equals : (Ljava/lang/Object;)Z
    //   665: ifeq -> 671
    //   668: goto -> 699
    //   671: iload_2
    //   672: ifeq -> 687
    //   675: aload_0
    //   676: aload_0
    //   677: getfield mUri : Landroid/net/Uri;
    //   680: invokevirtual toString : ()Ljava/lang/String;
    //   683: invokespecial uriToStyledText : (Ljava/lang/String;)Ljava/lang/CharSequence;
    //   686: areturn
    //   687: aload_0
    //   688: aload_0
    //   689: getfield mUri : Landroid/net/Uri;
    //   692: invokevirtual toString : ()Ljava/lang/String;
    //   695: invokespecial uriToHtml : (Ljava/lang/String;)Ljava/lang/String;
    //   698: areturn
    //   699: ldc ''
    //   701: areturn
    //   702: aload_3
    //   703: ifnull -> 714
    //   706: aload_3
    //   707: invokevirtual close : ()V
    //   710: goto -> 714
    //   713: astore_3
    //   714: aload_1
    //   715: athrow
    //   716: aload_0
    //   717: getfield mIntent : Landroid/content/Intent;
    //   720: astore_1
    //   721: aload_1
    //   722: ifnull -> 749
    //   725: iload_2
    //   726: ifeq -> 739
    //   729: aload_0
    //   730: aload_1
    //   731: iconst_1
    //   732: invokevirtual toUri : (I)Ljava/lang/String;
    //   735: invokespecial uriToStyledText : (Ljava/lang/String;)Ljava/lang/CharSequence;
    //   738: areturn
    //   739: aload_0
    //   740: aload_1
    //   741: iconst_1
    //   742: invokevirtual toUri : (I)Ljava/lang/String;
    //   745: invokespecial uriToHtml : (Ljava/lang/String;)Ljava/lang/String;
    //   748: areturn
    //   749: ldc ''
    //   751: areturn
    // Exception table:
    //   from	to	target	type
    //   9	24	30	java/lang/SecurityException
    //   164	170	607	java/lang/SecurityException
    //   164	170	589	java/io/FileNotFoundException
    //   164	170	549	java/io/IOException
    //   164	170	545	finally
    //   185	191	607	java/lang/SecurityException
    //   185	191	589	java/io/FileNotFoundException
    //   185	191	549	java/io/IOException
    //   185	191	545	finally
    //   220	233	607	java/lang/SecurityException
    //   220	233	589	java/io/FileNotFoundException
    //   220	233	549	java/io/IOException
    //   220	233	545	finally
    //   244	249	607	java/lang/SecurityException
    //   244	249	589	java/io/FileNotFoundException
    //   244	249	549	java/io/IOException
    //   244	249	545	finally
    //   260	268	607	java/lang/SecurityException
    //   260	268	589	java/io/FileNotFoundException
    //   260	268	549	java/io/IOException
    //   260	268	545	finally
    //   279	284	607	java/lang/SecurityException
    //   279	284	589	java/io/FileNotFoundException
    //   279	284	549	java/io/IOException
    //   279	284	545	finally
    //   295	303	607	java/lang/SecurityException
    //   295	303	589	java/io/FileNotFoundException
    //   295	303	549	java/io/IOException
    //   295	303	545	finally
    //   314	321	607	java/lang/SecurityException
    //   314	321	589	java/io/FileNotFoundException
    //   314	321	549	java/io/IOException
    //   314	321	545	finally
    //   332	341	607	java/lang/SecurityException
    //   332	341	589	java/io/FileNotFoundException
    //   332	341	549	java/io/IOException
    //   332	341	545	finally
    //   357	368	607	java/lang/SecurityException
    //   357	368	589	java/io/FileNotFoundException
    //   357	368	549	java/io/IOException
    //   357	368	545	finally
    //   382	389	607	java/lang/SecurityException
    //   382	389	589	java/io/FileNotFoundException
    //   382	389	549	java/io/IOException
    //   382	389	545	finally
    //   409	416	444	java/lang/RuntimeException
    //   409	416	607	java/lang/SecurityException
    //   409	416	589	java/io/FileNotFoundException
    //   409	416	549	java/io/IOException
    //   409	416	545	finally
    //   434	438	441	java/io/IOException
    //   449	453	456	java/io/IOException
    //   471	478	607	java/lang/SecurityException
    //   471	478	589	java/io/FileNotFoundException
    //   471	478	549	java/io/IOException
    //   471	478	545	finally
    //   482	486	489	java/io/IOException
    //   501	505	508	java/io/IOException
    //   523	530	607	java/lang/SecurityException
    //   523	530	589	java/io/FileNotFoundException
    //   523	530	549	java/io/IOException
    //   523	530	545	finally
    //   534	538	541	java/io/IOException
    //   553	562	545	finally
    //   565	573	545	finally
    //   578	583	586	java/io/IOException
    //   595	600	603	java/io/IOException
    //   611	620	545	finally
    //   625	630	603	java/io/IOException
    //   706	710	713	java/io/IOException
  }
  
  private String uriToHtml(String paramString) {
    StringBuilder stringBuilder = new StringBuilder(256);
    stringBuilder.append("<a href=\"");
    stringBuilder.append(Html.escapeHtml(paramString));
    stringBuilder.append("\">");
    stringBuilder.append(Html.escapeHtml(paramString));
    stringBuilder.append("</a>");
    return stringBuilder.toString();
  }
  
  private CharSequence uriToStyledText(String paramString) {
    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
    spannableStringBuilder.append(paramString);
    spannableStringBuilder.setSpan(new URLSpan(paramString), 0, spannableStringBuilder.length(), 33);
    return (CharSequence)spannableStringBuilder;
  }
  
  public String coerceToHtmlText(Context paramContext) {
    String str = getHtmlText();
    if (str != null)
      return str; 
    CharSequence charSequence2 = getText();
    if (charSequence2 != null)
      return (charSequence2 instanceof Spanned) ? Html.toHtml((Spanned)charSequence2) : Html.escapeHtml(charSequence2); 
    CharSequence charSequence1 = coerceToHtmlOrStyledText(paramContext, false);
    if (charSequence1 != null) {
      charSequence1 = charSequence1.toString();
    } else {
      charSequence1 = null;
    } 
    return (String)charSequence1;
  }
  
  public CharSequence coerceToStyledText(Context paramContext) {
    CharSequence charSequence = getText();
    if (charSequence instanceof Spanned)
      return charSequence; 
    String str = getHtmlText();
    if (str != null)
      try {
        Spanned spanned = Html.fromHtml(str);
        if (spanned != null)
          return (CharSequence)spanned; 
      } catch (RuntimeException runtimeException) {} 
    return (charSequence != null) ? charSequence : coerceToHtmlOrStyledText(paramContext, true);
  }
  
  public CharSequence coerceToText(Context paramContext) {
    CharSequence charSequence = getText();
    if (charSequence != null)
      return charSequence; 
    Uri uri = getUri();
    if (uri != null) {
      ContentResolver contentResolver = paramContext.getContentResolver();
      AssetFileDescriptor assetFileDescriptor1 = null;
      AssetFileDescriptor assetFileDescriptor2 = null;
      FileInputStream fileInputStream = null;
      CharSequence charSequence1 = null;
      CharSequence charSequence2 = null;
      Context context = null;
      AssetFileDescriptor assetFileDescriptor3 = assetFileDescriptor2;
      charSequence = charSequence1;
      paramContext = context;
      try {
        AssetFileDescriptor assetFileDescriptor = contentResolver.openTypedAssetFileDescriptor(uri, "text/*", null);
        assetFileDescriptor1 = assetFileDescriptor;
      } catch (SecurityException securityException) {
        assetFileDescriptor3 = assetFileDescriptor2;
        charSequence = charSequence1;
        paramContext = context;
        Log.w("ClipData", "Failure opening stream", securityException);
      } catch (FileNotFoundException|RuntimeException fileNotFoundException) {
      
      } finally {}
      if (assetFileDescriptor1 != null) {
        InputStreamReader inputStreamReader;
        assetFileDescriptor3 = assetFileDescriptor1;
        charSequence = charSequence1;
        paramContext = context;
        charSequence1 = charSequence2;
        try {
          FileInputStream fileInputStream2 = assetFileDescriptor1.createInputStream();
          assetFileDescriptor3 = assetFileDescriptor1;
          FileInputStream fileInputStream1 = fileInputStream2;
          paramContext = context;
          fileInputStream = fileInputStream2;
          charSequence1 = charSequence2;
          InputStreamReader inputStreamReader2 = new InputStreamReader();
          assetFileDescriptor3 = assetFileDescriptor1;
          fileInputStream1 = fileInputStream2;
          paramContext = context;
          fileInputStream = fileInputStream2;
          charSequence1 = charSequence2;
          this(fileInputStream2, "UTF-8");
          InputStreamReader inputStreamReader3 = inputStreamReader2;
          assetFileDescriptor3 = assetFileDescriptor1;
          fileInputStream1 = fileInputStream2;
          InputStreamReader inputStreamReader1 = inputStreamReader3;
          fileInputStream = fileInputStream2;
          inputStreamReader = inputStreamReader3;
          charSequence2 = new StringBuilder();
          assetFileDescriptor3 = assetFileDescriptor1;
          fileInputStream1 = fileInputStream2;
          inputStreamReader1 = inputStreamReader3;
          fileInputStream = fileInputStream2;
          inputStreamReader = inputStreamReader3;
          super(128);
          assetFileDescriptor3 = assetFileDescriptor1;
          fileInputStream1 = fileInputStream2;
          inputStreamReader1 = inputStreamReader3;
          fileInputStream = fileInputStream2;
          inputStreamReader = inputStreamReader3;
          char[] arrayOfChar = new char[8192];
          while (true) {
            assetFileDescriptor3 = assetFileDescriptor1;
            fileInputStream1 = fileInputStream2;
            inputStreamReader1 = inputStreamReader3;
            fileInputStream = fileInputStream2;
            inputStreamReader = inputStreamReader3;
            int i = inputStreamReader3.read(arrayOfChar);
            if (i > 0) {
              assetFileDescriptor3 = assetFileDescriptor1;
              fileInputStream1 = fileInputStream2;
              inputStreamReader1 = inputStreamReader3;
              fileInputStream = fileInputStream2;
              inputStreamReader = inputStreamReader3;
              charSequence2.append(arrayOfChar, 0, i);
              continue;
            } 
            assetFileDescriptor3 = assetFileDescriptor1;
            fileInputStream1 = fileInputStream2;
            inputStreamReader1 = inputStreamReader3;
            fileInputStream = fileInputStream2;
            inputStreamReader = inputStreamReader3;
            charSequence2 = charSequence2.toString();
            IoUtils.closeQuietly((AutoCloseable)assetFileDescriptor1);
            IoUtils.closeQuietly(fileInputStream2);
            IoUtils.closeQuietly(inputStreamReader3);
            return charSequence2;
          } 
        } catch (IOException iOException) {
          assetFileDescriptor3 = assetFileDescriptor1;
          FileInputStream fileInputStream1 = fileInputStream;
          InputStreamReader inputStreamReader1 = inputStreamReader;
          Log.w("ClipData", "Failure loading text", iOException);
          assetFileDescriptor3 = assetFileDescriptor1;
          fileInputStream1 = fileInputStream;
          inputStreamReader1 = inputStreamReader;
          String str1 = iOException.toString();
          IoUtils.closeQuietly((AutoCloseable)assetFileDescriptor1);
          IoUtils.closeQuietly(fileInputStream);
          IoUtils.closeQuietly(inputStreamReader);
          return str1;
        } 
      } 
      IoUtils.closeQuietly((AutoCloseable)assetFileDescriptor1);
      IoUtils.closeQuietly(null);
      IoUtils.closeQuietly(null);
      String str = uri.getScheme();
      return ("content".equals(str) || "android.resource".equals(str) || "file".equals(str)) ? "" : uri.toString();
    } 
    Intent intent = getIntent();
    return (intent != null) ? intent.toUri(1) : "";
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    String str = this.mHtmlText;
    if (str != null) {
      paramProtoOutputStream.write(1138166333441L, str);
    } else {
      CharSequence charSequence = this.mText;
      if (charSequence != null) {
        paramProtoOutputStream.write(1138166333442L, charSequence.toString());
      } else {
        Uri uri = this.mUri;
        if (uri != null) {
          paramProtoOutputStream.write(1138166333443L, uri.toString());
        } else {
          Intent intent = this.mIntent;
          if (intent != null) {
            intent.dumpDebug(paramProtoOutputStream, 1146756268036L, true, true, true, true);
          } else {
            paramProtoOutputStream.write(1133871366149L, true);
          } 
        } 
      } 
    } 
    paramProtoOutputStream.end(paramLong);
  }
  
  public String getHtmlText() {
    return this.mHtmlText;
  }
  
  public Intent getIntent() {
    return this.mIntent;
  }
  
  public CharSequence getText() {
    return this.mText;
  }
  
  public Uri getUri() {
    return this.mUri;
  }
  
  public void toShortString(StringBuilder paramStringBuilder) {
    if (this.mHtmlText != null) {
      paramStringBuilder.append("H:");
      paramStringBuilder.append(this.mHtmlText);
    } else if (this.mText != null) {
      paramStringBuilder.append("T:");
      paramStringBuilder.append(this.mText);
    } else if (this.mUri != null) {
      paramStringBuilder.append("U:");
      paramStringBuilder.append(this.mUri);
    } else if (this.mIntent != null) {
      paramStringBuilder.append("I:");
      this.mIntent.toShortString(paramStringBuilder, true, true, true, true);
    } else {
      paramStringBuilder.append("NULL");
    } 
  }
  
  public void toShortSummaryString(StringBuilder paramStringBuilder) {
    if (this.mHtmlText != null) {
      paramStringBuilder.append("HTML");
    } else if (this.mText != null) {
      paramStringBuilder.append("TEXT");
    } else if (this.mUri != null) {
      paramStringBuilder.append("U:");
      paramStringBuilder.append(this.mUri);
    } else if (this.mIntent != null) {
      paramStringBuilder.append("I:");
      this.mIntent.toShortString(paramStringBuilder, true, true, true, true);
    } else {
      paramStringBuilder.append("NULL");
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("ClipData.Item { ");
    toShortString(stringBuilder);
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ClipData$Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */