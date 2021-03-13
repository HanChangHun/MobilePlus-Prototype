package android.content;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import java.util.Objects;

public class ContentProviderOperation implements Parcelable {
  public static final Parcelable.Creator<ContentProviderOperation> CREATOR = new Parcelable.Creator<ContentProviderOperation>() {
      public ContentProviderOperation createFromParcel(Parcel param1Parcel) {
        return new ContentProviderOperation(param1Parcel);
      }
      
      public ContentProviderOperation[] newArray(int param1Int) {
        return new ContentProviderOperation[param1Int];
      }
    };
  
  private static final String TAG = "ContentProviderOperation";
  
  public static final int TYPE_ASSERT = 4;
  
  public static final int TYPE_CALL = 5;
  
  public static final int TYPE_DELETE = 3;
  
  public static final int TYPE_INSERT = 1;
  
  public static final int TYPE_UPDATE = 2;
  
  private final String mArg;
  
  private final boolean mExceptionAllowed;
  
  private final Integer mExpectedCount;
  
  private final ArrayMap<String, Object> mExtras;
  
  private final String mMethod;
  
  private final String mSelection;
  
  private final SparseArray<Object> mSelectionArgs;
  
  private final int mType;
  
  private final Uri mUri;
  
  private final ArrayMap<String, Object> mValues;
  
  private final boolean mYieldAllowed;
  
  private ContentProviderOperation(Builder paramBuilder) {
    this.mType = paramBuilder.mType;
    this.mUri = paramBuilder.mUri;
    this.mMethod = paramBuilder.mMethod;
    this.mArg = paramBuilder.mArg;
    this.mValues = paramBuilder.mValues;
    this.mExtras = paramBuilder.mExtras;
    this.mSelection = paramBuilder.mSelection;
    this.mSelectionArgs = paramBuilder.mSelectionArgs;
    this.mExpectedCount = paramBuilder.mExpectedCount;
    this.mYieldAllowed = paramBuilder.mYieldAllowed;
    this.mExceptionAllowed = paramBuilder.mExceptionAllowed;
  }
  
  public ContentProviderOperation(ContentProviderOperation paramContentProviderOperation, Uri paramUri) {
    this.mType = paramContentProviderOperation.mType;
    this.mUri = paramUri;
    this.mMethod = paramContentProviderOperation.mMethod;
    this.mArg = paramContentProviderOperation.mArg;
    this.mValues = paramContentProviderOperation.mValues;
    this.mExtras = paramContentProviderOperation.mExtras;
    this.mSelection = paramContentProviderOperation.mSelection;
    this.mSelectionArgs = paramContentProviderOperation.mSelectionArgs;
    this.mExpectedCount = paramContentProviderOperation.mExpectedCount;
    this.mYieldAllowed = paramContentProviderOperation.mYieldAllowed;
    this.mExceptionAllowed = paramContentProviderOperation.mExceptionAllowed;
  }
  
  private ContentProviderOperation(Parcel paramParcel) {
    Integer integer;
    boolean bool2;
    this.mType = paramParcel.readInt();
    this.mUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel);
    int i = paramParcel.readInt();
    String str1 = null;
    if (i != 0) {
      str2 = paramParcel.readString8();
    } else {
      str2 = null;
    } 
    this.mMethod = str2;
    if (paramParcel.readInt() != 0) {
      str2 = paramParcel.readString8();
    } else {
      str2 = null;
    } 
    this.mArg = str2;
    i = paramParcel.readInt();
    if (i != -1) {
      ArrayMap<String, Object> arrayMap = new ArrayMap(i);
      this.mValues = arrayMap;
      paramParcel.readArrayMap(arrayMap, null);
    } else {
      this.mValues = null;
    } 
    i = paramParcel.readInt();
    if (i != -1) {
      ArrayMap<String, Object> arrayMap = new ArrayMap(i);
      this.mExtras = arrayMap;
      paramParcel.readArrayMap(arrayMap, null);
    } else {
      this.mExtras = null;
    } 
    if (paramParcel.readInt() != 0) {
      str2 = paramParcel.readString8();
    } else {
      str2 = null;
    } 
    this.mSelection = str2;
    this.mSelectionArgs = paramParcel.readSparseArray(null);
    String str2 = str1;
    if (paramParcel.readInt() != 0)
      integer = Integer.valueOf(paramParcel.readInt()); 
    this.mExpectedCount = integer;
    i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mYieldAllowed = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mExceptionAllowed = bool2;
  }
  
  private ContentProviderResult applyInternal(ContentProvider paramContentProvider, ContentProviderResult[] paramArrayOfContentProviderResult, int paramInt) throws OperationApplicationException {
    // Byte code:
    //   0: aload_0
    //   1: aload_2
    //   2: iload_3
    //   3: invokevirtual resolveValueBackReferences : ([Landroid/content/ContentProviderResult;I)Landroid/content/ContentValues;
    //   6: astore #4
    //   8: aload_0
    //   9: aload_2
    //   10: iload_3
    //   11: invokevirtual resolveExtrasBackReferences : ([Landroid/content/ContentProviderResult;I)Landroid/os/Bundle;
    //   14: astore #5
    //   16: aload #5
    //   18: astore #6
    //   20: aload_0
    //   21: getfield mSelection : Ljava/lang/String;
    //   24: ifnull -> 59
    //   27: aload #5
    //   29: ifnull -> 39
    //   32: aload #5
    //   34: astore #6
    //   36: goto -> 48
    //   39: new android/os/Bundle
    //   42: dup
    //   43: invokespecial <init> : ()V
    //   46: astore #6
    //   48: aload #6
    //   50: ldc 'android:query-arg-sql-selection'
    //   52: aload_0
    //   53: getfield mSelection : Ljava/lang/String;
    //   56: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload #6
    //   61: astore #5
    //   63: aload_0
    //   64: getfield mSelectionArgs : Landroid/util/SparseArray;
    //   67: ifnull -> 104
    //   70: aload #6
    //   72: ifnull -> 78
    //   75: goto -> 87
    //   78: new android/os/Bundle
    //   81: dup
    //   82: invokespecial <init> : ()V
    //   85: astore #6
    //   87: aload #6
    //   89: ldc 'android:query-arg-sql-selection-args'
    //   91: aload_0
    //   92: aload_2
    //   93: iload_3
    //   94: invokevirtual resolveSelectionArgsBackReferences : ([Landroid/content/ContentProviderResult;I)[Ljava/lang/String;
    //   97: invokevirtual putStringArray : (Ljava/lang/String;[Ljava/lang/String;)V
    //   100: aload #6
    //   102: astore #5
    //   104: aload_0
    //   105: getfield mType : I
    //   108: istore_3
    //   109: iload_3
    //   110: iconst_1
    //   111: if_icmpne -> 183
    //   114: aload_1
    //   115: aload_0
    //   116: getfield mUri : Landroid/net/Uri;
    //   119: aload #4
    //   121: aload #5
    //   123: invokevirtual insert : (Landroid/net/Uri;Landroid/content/ContentValues;Landroid/os/Bundle;)Landroid/net/Uri;
    //   126: astore_1
    //   127: aload_1
    //   128: ifnull -> 140
    //   131: new android/content/ContentProviderResult
    //   134: dup
    //   135: aload_1
    //   136: invokespecial <init> : (Landroid/net/Uri;)V
    //   139: areturn
    //   140: new java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial <init> : ()V
    //   147: astore_1
    //   148: aload_1
    //   149: ldc 'Insert into '
    //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: pop
    //   155: aload_1
    //   156: aload_0
    //   157: getfield mUri : Landroid/net/Uri;
    //   160: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload_1
    //   165: ldc ' returned no result'
    //   167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: pop
    //   171: new android/content/OperationApplicationException
    //   174: dup
    //   175: aload_1
    //   176: invokevirtual toString : ()Ljava/lang/String;
    //   179: invokespecial <init> : (Ljava/lang/String;)V
    //   182: athrow
    //   183: iload_3
    //   184: iconst_5
    //   185: if_icmpne -> 217
    //   188: new android/content/ContentProviderResult
    //   191: dup
    //   192: aload_1
    //   193: aload_0
    //   194: getfield mUri : Landroid/net/Uri;
    //   197: invokevirtual getAuthority : ()Ljava/lang/String;
    //   200: aload_0
    //   201: getfield mMethod : Ljava/lang/String;
    //   204: aload_0
    //   205: getfield mArg : Ljava/lang/String;
    //   208: aload #5
    //   210: invokevirtual call : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Landroid/os/Bundle;
    //   213: invokespecial <init> : (Landroid/os/Bundle;)V
    //   216: areturn
    //   217: iload_3
    //   218: iconst_3
    //   219: if_icmpne -> 236
    //   222: aload_1
    //   223: aload_0
    //   224: getfield mUri : Landroid/net/Uri;
    //   227: aload #5
    //   229: invokevirtual delete : (Landroid/net/Uri;Landroid/os/Bundle;)I
    //   232: istore_3
    //   233: goto -> 510
    //   236: iload_3
    //   237: iconst_2
    //   238: if_icmpne -> 257
    //   241: aload_1
    //   242: aload_0
    //   243: getfield mUri : Landroid/net/Uri;
    //   246: aload #4
    //   248: aload #5
    //   250: invokevirtual update : (Landroid/net/Uri;Landroid/content/ContentValues;Landroid/os/Bundle;)I
    //   253: istore_3
    //   254: goto -> 510
    //   257: iload_3
    //   258: iconst_4
    //   259: if_icmpne -> 599
    //   262: aconst_null
    //   263: astore_2
    //   264: aload #4
    //   266: ifnull -> 341
    //   269: new java/util/ArrayList
    //   272: dup
    //   273: invokespecial <init> : ()V
    //   276: astore #6
    //   278: aload #4
    //   280: invokevirtual valueSet : ()Ljava/util/Set;
    //   283: invokeinterface iterator : ()Ljava/util/Iterator;
    //   288: astore_2
    //   289: aload_2
    //   290: invokeinterface hasNext : ()Z
    //   295: ifeq -> 324
    //   298: aload #6
    //   300: aload_2
    //   301: invokeinterface next : ()Ljava/lang/Object;
    //   306: checkcast java/util/Map$Entry
    //   309: invokeinterface getKey : ()Ljava/lang/Object;
    //   314: checkcast java/lang/String
    //   317: invokevirtual add : (Ljava/lang/Object;)Z
    //   320: pop
    //   321: goto -> 289
    //   324: aload #6
    //   326: aload #6
    //   328: invokevirtual size : ()I
    //   331: anewarray java/lang/String
    //   334: invokevirtual toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   337: checkcast [Ljava/lang/String;
    //   340: astore_2
    //   341: aload_1
    //   342: aload_0
    //   343: getfield mUri : Landroid/net/Uri;
    //   346: aload_2
    //   347: aload #5
    //   349: aconst_null
    //   350: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Landroid/os/Bundle;Landroid/os/CancellationSignal;)Landroid/database/Cursor;
    //   353: astore_1
    //   354: aload_1
    //   355: invokeinterface getCount : ()I
    //   360: istore #7
    //   362: aload_2
    //   363: ifnull -> 501
    //   366: aload_1
    //   367: invokeinterface moveToNext : ()Z
    //   372: ifeq -> 501
    //   375: iconst_0
    //   376: istore_3
    //   377: iload_3
    //   378: aload_2
    //   379: arraylength
    //   380: if_icmpge -> 498
    //   383: aload_1
    //   384: iload_3
    //   385: invokeinterface getString : (I)Ljava/lang/String;
    //   390: astore #5
    //   392: aload #4
    //   394: aload_2
    //   395: iload_3
    //   396: aaload
    //   397: invokevirtual getAsString : (Ljava/lang/String;)Ljava/lang/String;
    //   400: astore #6
    //   402: aload #5
    //   404: aload #6
    //   406: invokestatic equals : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   409: ifeq -> 418
    //   412: iinc #3, 1
    //   415: goto -> 377
    //   418: new android/content/OperationApplicationException
    //   421: astore #4
    //   423: new java/lang/StringBuilder
    //   426: astore #8
    //   428: aload #8
    //   430: invokespecial <init> : ()V
    //   433: aload #8
    //   435: ldc_w 'Found value '
    //   438: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: pop
    //   442: aload #8
    //   444: aload #5
    //   446: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: pop
    //   450: aload #8
    //   452: ldc_w ' when expected '
    //   455: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   458: pop
    //   459: aload #8
    //   461: aload #6
    //   463: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   466: pop
    //   467: aload #8
    //   469: ldc_w ' for column '
    //   472: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   475: pop
    //   476: aload #8
    //   478: aload_2
    //   479: iload_3
    //   480: aaload
    //   481: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   484: pop
    //   485: aload #4
    //   487: aload #8
    //   489: invokevirtual toString : ()Ljava/lang/String;
    //   492: invokespecial <init> : (Ljava/lang/String;)V
    //   495: aload #4
    //   497: athrow
    //   498: goto -> 366
    //   501: aload_1
    //   502: invokeinterface close : ()V
    //   507: iload #7
    //   509: istore_3
    //   510: aload_0
    //   511: getfield mExpectedCount : Ljava/lang/Integer;
    //   514: astore_1
    //   515: aload_1
    //   516: ifnull -> 581
    //   519: aload_1
    //   520: invokevirtual intValue : ()I
    //   523: iload_3
    //   524: if_icmpne -> 530
    //   527: goto -> 581
    //   530: new java/lang/StringBuilder
    //   533: dup
    //   534: invokespecial <init> : ()V
    //   537: astore_1
    //   538: aload_1
    //   539: ldc_w 'Expected '
    //   542: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: pop
    //   546: aload_1
    //   547: aload_0
    //   548: getfield mExpectedCount : Ljava/lang/Integer;
    //   551: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   554: pop
    //   555: aload_1
    //   556: ldc_w ' rows but actual '
    //   559: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   562: pop
    //   563: aload_1
    //   564: iload_3
    //   565: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   568: pop
    //   569: new android/content/OperationApplicationException
    //   572: dup
    //   573: aload_1
    //   574: invokevirtual toString : ()Ljava/lang/String;
    //   577: invokespecial <init> : (Ljava/lang/String;)V
    //   580: athrow
    //   581: new android/content/ContentProviderResult
    //   584: dup
    //   585: iload_3
    //   586: invokespecial <init> : (I)V
    //   589: areturn
    //   590: astore_2
    //   591: aload_1
    //   592: invokeinterface close : ()V
    //   597: aload_2
    //   598: athrow
    //   599: new java/lang/StringBuilder
    //   602: dup
    //   603: invokespecial <init> : ()V
    //   606: astore_1
    //   607: aload_1
    //   608: ldc_w 'bad type, '
    //   611: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   614: pop
    //   615: aload_1
    //   616: aload_0
    //   617: getfield mType : I
    //   620: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   623: pop
    //   624: new java/lang/IllegalStateException
    //   627: dup
    //   628: aload_1
    //   629: invokevirtual toString : ()Ljava/lang/String;
    //   632: invokespecial <init> : (Ljava/lang/String;)V
    //   635: athrow
    // Exception table:
    //   from	to	target	type
    //   354	362	590	finally
    //   366	375	590	finally
    //   377	412	590	finally
    //   418	498	590	finally
  }
  
  public static Builder newAssertQuery(Uri paramUri) {
    return new Builder(4, paramUri);
  }
  
  public static Builder newCall(Uri paramUri, String paramString1, String paramString2) {
    return new Builder(5, paramUri, paramString1, paramString2);
  }
  
  public static Builder newDelete(Uri paramUri) {
    return new Builder(3, paramUri);
  }
  
  public static Builder newInsert(Uri paramUri) {
    return new Builder(1, paramUri);
  }
  
  public static Builder newUpdate(Uri paramUri) {
    return new Builder(2, paramUri);
  }
  
  public static String typeToString(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? Integer.toString(paramInt) : "call") : "assert") : "delete") : "update") : "insert";
  }
  
  public ContentProviderResult apply(ContentProvider paramContentProvider, ContentProviderResult[] paramArrayOfContentProviderResult, int paramInt) throws OperationApplicationException {
    if (this.mExceptionAllowed)
      try {
        return applyInternal(paramContentProvider, paramArrayOfContentProviderResult, paramInt);
      } catch (Exception exception) {
        return new ContentProviderResult(exception);
      }  
    return applyInternal((ContentProvider)exception, paramArrayOfContentProviderResult, paramInt);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getType() {
    return this.mType;
  }
  
  public Uri getUri() {
    return this.mUri;
  }
  
  public boolean isAssertQuery() {
    boolean bool;
    if (this.mType == 4) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isCall() {
    boolean bool;
    if (this.mType == 5) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isDelete() {
    boolean bool;
    if (this.mType == 3) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isExceptionAllowed() {
    return this.mExceptionAllowed;
  }
  
  public boolean isInsert() {
    int i = this.mType;
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public boolean isReadOperation() {
    boolean bool;
    if (this.mType == 4) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isUpdate() {
    boolean bool;
    if (this.mType == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isWriteOperation() {
    int i = this.mType;
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (i != 3) {
      bool2 = bool1;
      if (i != 1)
        if (i == 2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
    } 
    return bool2;
  }
  
  public boolean isYieldAllowed() {
    return this.mYieldAllowed;
  }
  
  public Bundle resolveExtrasBackReferences(ContentProviderResult[] paramArrayOfContentProviderResult, int paramInt) {
    if (this.mExtras != null) {
      Bundle bundle = new Bundle();
      for (byte b = 0; b < this.mExtras.size(); b++) {
        Object object = this.mExtras.valueAt(b);
        if (object instanceof BackReference)
          object = ((BackReference)object).resolve(paramArrayOfContentProviderResult, paramInt); 
        bundle.putObject((String)this.mExtras.keyAt(b), object);
      } 
      return bundle;
    } 
    return null;
  }
  
  public String[] resolveSelectionArgsBackReferences(ContentProviderResult[] paramArrayOfContentProviderResult, int paramInt) {
    if (this.mSelectionArgs != null) {
      int i = -1;
      byte b;
      for (b = 0; b < this.mSelectionArgs.size(); b++)
        i = Math.max(i, this.mSelectionArgs.keyAt(b)); 
      String[] arrayOfString = new String[i + 1];
      for (b = 0; b < this.mSelectionArgs.size(); b++) {
        Object object = this.mSelectionArgs.valueAt(b);
        if (object instanceof BackReference)
          object = ((BackReference)object).resolve(paramArrayOfContentProviderResult, paramInt); 
        arrayOfString[this.mSelectionArgs.keyAt(b)] = String.valueOf(object);
      } 
      return arrayOfString;
    } 
    return null;
  }
  
  public ContentValues resolveValueBackReferences(ContentProviderResult[] paramArrayOfContentProviderResult, int paramInt) {
    if (this.mValues != null) {
      ContentValues contentValues = new ContentValues();
      for (byte b = 0; b < this.mValues.size(); b++) {
        Object object = this.mValues.valueAt(b);
        if (object instanceof BackReference)
          object = ((BackReference)object).resolve(paramArrayOfContentProviderResult, paramInt); 
        contentValues.putObject((String)this.mValues.keyAt(b), object);
      } 
      return contentValues;
    } 
    return null;
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder("ContentProviderOperation(");
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("type=");
    stringBuilder2.append(typeToString(this.mType));
    stringBuilder2.append(" ");
    stringBuilder1.append(stringBuilder2.toString());
    if (this.mUri != null) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("uri=");
      stringBuilder2.append(this.mUri);
      stringBuilder2.append(" ");
      stringBuilder1.append(stringBuilder2.toString());
    } 
    if (this.mValues != null) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("values=");
      stringBuilder2.append(this.mValues);
      stringBuilder2.append(" ");
      stringBuilder1.append(stringBuilder2.toString());
    } 
    if (this.mSelection != null) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("selection=");
      stringBuilder2.append(this.mSelection);
      stringBuilder2.append(" ");
      stringBuilder1.append(stringBuilder2.toString());
    } 
    if (this.mSelectionArgs != null) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("selectionArgs=");
      stringBuilder2.append(this.mSelectionArgs);
      stringBuilder2.append(" ");
      stringBuilder1.append(stringBuilder2.toString());
    } 
    if (this.mExpectedCount != null) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("expectedCount=");
      stringBuilder2.append(this.mExpectedCount);
      stringBuilder2.append(" ");
      stringBuilder1.append(stringBuilder2.toString());
    } 
    if (this.mYieldAllowed)
      stringBuilder1.append("yieldAllowed "); 
    if (this.mExceptionAllowed)
      stringBuilder1.append("exceptionAllowed "); 
    stringBuilder1.deleteCharAt(stringBuilder1.length() - 1);
    stringBuilder1.append(")");
    return stringBuilder1.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mType);
    Uri.writeToParcel(paramParcel, this.mUri);
    if (this.mMethod != null) {
      paramParcel.writeInt(1);
      paramParcel.writeString8(this.mMethod);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mArg != null) {
      paramParcel.writeInt(1);
      paramParcel.writeString8(this.mArg);
    } else {
      paramParcel.writeInt(0);
    } 
    ArrayMap<String, Object> arrayMap = this.mValues;
    if (arrayMap != null) {
      paramParcel.writeInt(arrayMap.size());
      paramParcel.writeArrayMap(this.mValues);
    } else {
      paramParcel.writeInt(-1);
    } 
    arrayMap = this.mExtras;
    if (arrayMap != null) {
      paramParcel.writeInt(arrayMap.size());
      paramParcel.writeArrayMap(this.mExtras);
    } else {
      paramParcel.writeInt(-1);
    } 
    if (this.mSelection != null) {
      paramParcel.writeInt(1);
      paramParcel.writeString8(this.mSelection);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeSparseArray(this.mSelectionArgs);
    if (this.mExpectedCount != null) {
      paramParcel.writeInt(1);
      paramParcel.writeInt(this.mExpectedCount.intValue());
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.mYieldAllowed);
    paramParcel.writeInt(this.mExceptionAllowed);
  }
  
  public static class BackReference implements Parcelable {
    public static final Parcelable.Creator<BackReference> CREATOR = new Parcelable.Creator<BackReference>() {
        public ContentProviderOperation.BackReference createFromParcel(Parcel param2Parcel) {
          return new ContentProviderOperation.BackReference(param2Parcel);
        }
        
        public ContentProviderOperation.BackReference[] newArray(int param2Int) {
          return new ContentProviderOperation.BackReference[param2Int];
        }
      };
    
    private final int fromIndex;
    
    private final String fromKey;
    
    private BackReference(int param1Int, String param1String) {
      this.fromIndex = param1Int;
      this.fromKey = param1String;
    }
    
    public BackReference(Parcel param1Parcel) {
      this.fromIndex = param1Parcel.readInt();
      if (param1Parcel.readInt() != 0) {
        this.fromKey = param1Parcel.readString8();
      } else {
        this.fromKey = null;
      } 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public Object resolve(ContentProviderResult[] param1ArrayOfContentProviderResult, int param1Int) {
      int i = this.fromIndex;
      if (i < param1Int) {
        Object object = param1ArrayOfContentProviderResult[i];
        if (((ContentProviderResult)object).extras != null) {
          object = ((ContentProviderResult)object).extras.get(this.fromKey);
        } else if (((ContentProviderResult)object).uri != null) {
          object = Long.valueOf(ContentUris.parseId(((ContentProviderResult)object).uri));
        } else {
          object = Long.valueOf(((ContentProviderResult)object).count.intValue());
        } 
        return object;
      } 
      Log.e("ContentProviderOperation", toString());
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("asked for back ref ");
      stringBuilder.append(this.fromIndex);
      stringBuilder.append(" but there are only ");
      stringBuilder.append(param1Int);
      stringBuilder.append(" back refs");
      throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.fromIndex);
      if (this.fromKey != null) {
        param1Parcel.writeInt(1);
        param1Parcel.writeString8(this.fromKey);
      } else {
        param1Parcel.writeInt(0);
      } 
    }
  }
  
  class null implements Parcelable.Creator<BackReference> {
    public ContentProviderOperation.BackReference createFromParcel(Parcel param1Parcel) {
      return new ContentProviderOperation.BackReference(param1Parcel);
    }
    
    public ContentProviderOperation.BackReference[] newArray(int param1Int) {
      return new ContentProviderOperation.BackReference[param1Int];
    }
  }
  
  public static class Builder {
    private final String mArg;
    
    private boolean mExceptionAllowed;
    
    private Integer mExpectedCount;
    
    private ArrayMap<String, Object> mExtras;
    
    private final String mMethod;
    
    private String mSelection;
    
    private SparseArray<Object> mSelectionArgs;
    
    private final int mType;
    
    private final Uri mUri;
    
    private ArrayMap<String, Object> mValues;
    
    private boolean mYieldAllowed;
    
    private Builder(int param1Int, Uri param1Uri) {
      this(param1Int, param1Uri, null, null);
    }
    
    private Builder(int param1Int, Uri param1Uri, String param1String1, String param1String2) {
      this.mType = param1Int;
      Objects.requireNonNull(param1Uri);
      this.mUri = param1Uri;
      this.mMethod = param1String1;
      this.mArg = param1String2;
    }
    
    private void assertExtrasAllowed() {
      int i = this.mType;
      if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5)
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Extras not supported for ");
      stringBuilder.append(ContentProviderOperation.typeToString(this.mType));
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    private void assertSelectionAllowed() {
      int i = this.mType;
      if (i == 2 || i == 3 || i == 4)
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Selection not supported for ");
      stringBuilder.append(ContentProviderOperation.typeToString(this.mType));
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    private void assertValuesAllowed() {
      int i = this.mType;
      if (i == 1 || i == 2 || i == 4)
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Values not supported for ");
      stringBuilder.append(ContentProviderOperation.typeToString(this.mType));
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    private void ensureExtras() {
      if (this.mExtras == null)
        this.mExtras = new ArrayMap(); 
    }
    
    private void ensureSelectionArgs() {
      if (this.mSelectionArgs == null)
        this.mSelectionArgs = new SparseArray(); 
    }
    
    private void ensureValues() {
      if (this.mValues == null)
        this.mValues = new ArrayMap(); 
    }
    
    private void setExtra(String param1String, Object param1Object) {
      ensureExtras();
      boolean bool1 = this.mExtras.get(param1String) instanceof ContentProviderOperation.BackReference;
      boolean bool2 = param1Object instanceof ContentProviderOperation.BackReference;
      if (!bool1 || bool2)
        this.mExtras.put(param1String, param1Object); 
    }
    
    private void setSelectionArg(int param1Int, Object param1Object) {
      ensureSelectionArgs();
      boolean bool1 = this.mSelectionArgs.get(param1Int) instanceof ContentProviderOperation.BackReference;
      boolean bool2 = param1Object instanceof ContentProviderOperation.BackReference;
      if (!bool1 || bool2)
        this.mSelectionArgs.put(param1Int, param1Object); 
    }
    
    private void setValue(String param1String, Object param1Object) {
      ensureValues();
      boolean bool1 = this.mValues.get(param1String) instanceof ContentProviderOperation.BackReference;
      boolean bool2 = param1Object instanceof ContentProviderOperation.BackReference;
      if (!bool1 || bool2)
        this.mValues.put(param1String, param1Object); 
    }
    
    public ContentProviderOperation build() {
      if (this.mType == 2) {
        ArrayMap<String, Object> arrayMap = this.mValues;
        if (arrayMap == null || arrayMap.isEmpty())
          throw new IllegalArgumentException("Empty values"); 
      } 
      if (this.mType == 4) {
        ArrayMap<String, Object> arrayMap = this.mValues;
        if ((arrayMap == null || arrayMap.isEmpty()) && this.mExpectedCount == null)
          throw new IllegalArgumentException("Empty values"); 
      } 
      return new ContentProviderOperation(this);
    }
    
    public Builder withExceptionAllowed(boolean param1Boolean) {
      this.mExceptionAllowed = param1Boolean;
      return this;
    }
    
    public Builder withExpectedCount(int param1Int) {
      int i = this.mType;
      if (i == 2 || i == 3 || i == 4) {
        this.mExpectedCount = Integer.valueOf(param1Int);
        return this;
      } 
      throw new IllegalArgumentException("only updates, deletes, and asserts can have expected counts");
    }
    
    public Builder withExtra(String param1String, Object param1Object) {
      assertExtrasAllowed();
      setExtra(param1String, param1Object);
      return this;
    }
    
    public Builder withExtraBackReference(String param1String, int param1Int) {
      assertExtrasAllowed();
      setExtra(param1String, new ContentProviderOperation.BackReference(param1Int, null));
      return this;
    }
    
    public Builder withExtraBackReference(String param1String1, int param1Int, String param1String2) {
      assertExtrasAllowed();
      setExtra(param1String1, new ContentProviderOperation.BackReference(param1Int, param1String2));
      return this;
    }
    
    public Builder withExtras(Bundle param1Bundle) {
      assertExtrasAllowed();
      ensureExtras();
      for (String str : param1Bundle.keySet())
        setExtra(str, param1Bundle.get(str)); 
      return this;
    }
    
    public Builder withFailureAllowed(boolean param1Boolean) {
      return withExceptionAllowed(param1Boolean);
    }
    
    public Builder withSelection(String param1String, String[] param1ArrayOfString) {
      assertSelectionAllowed();
      this.mSelection = param1String;
      if (param1ArrayOfString != null) {
        ensureSelectionArgs();
        for (byte b = 0; b < param1ArrayOfString.length; b++)
          setSelectionArg(b, param1ArrayOfString[b]); 
      } 
      return this;
    }
    
    public Builder withSelectionBackReference(int param1Int1, int param1Int2) {
      assertSelectionAllowed();
      setSelectionArg(param1Int1, new ContentProviderOperation.BackReference(param1Int2, null));
      return this;
    }
    
    public Builder withSelectionBackReference(int param1Int1, int param1Int2, String param1String) {
      assertSelectionAllowed();
      setSelectionArg(param1Int1, new ContentProviderOperation.BackReference(param1Int2, param1String));
      return this;
    }
    
    public Builder withValue(String param1String, Object param1Object) {
      assertValuesAllowed();
      if (ContentValues.isSupportedValue(param1Object)) {
        setValue(param1String, param1Object);
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("bad value type: ");
      stringBuilder.append(param1Object.getClass().getName());
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder withValueBackReference(String param1String, int param1Int) {
      assertValuesAllowed();
      setValue(param1String, new ContentProviderOperation.BackReference(param1Int, null));
      return this;
    }
    
    public Builder withValueBackReference(String param1String1, int param1Int, String param1String2) {
      assertValuesAllowed();
      setValue(param1String1, new ContentProviderOperation.BackReference(param1Int, param1String2));
      return this;
    }
    
    public Builder withValueBackReferences(ContentValues param1ContentValues) {
      assertValuesAllowed();
      ArrayMap<String, Object> arrayMap = param1ContentValues.getValues();
      for (byte b = 0; b < arrayMap.size(); b++)
        setValue((String)arrayMap.keyAt(b), new ContentProviderOperation.BackReference(((Integer)arrayMap.valueAt(b)).intValue(), null)); 
      return this;
    }
    
    public Builder withValues(ContentValues param1ContentValues) {
      assertValuesAllowed();
      ensureValues();
      ArrayMap<String, Object> arrayMap = param1ContentValues.getValues();
      for (byte b = 0; b < arrayMap.size(); b++)
        setValue((String)arrayMap.keyAt(b), arrayMap.valueAt(b)); 
      return this;
    }
    
    public Builder withYieldAllowed(boolean param1Boolean) {
      this.mYieldAllowed = param1Boolean;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */