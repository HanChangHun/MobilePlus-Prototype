package android.content;

import android.net.Uri;
import java.util.ArrayList;

public class UriMatcher {
  private static final int EXACT = 0;
  
  public static final int NO_MATCH = -1;
  
  private static final int NUMBER = 1;
  
  private static final int TEXT = 2;
  
  private ArrayList<UriMatcher> mChildren;
  
  private int mCode;
  
  private final String mText;
  
  private final int mWhich;
  
  public UriMatcher(int paramInt) {
    this.mCode = paramInt;
    this.mWhich = -1;
    this.mChildren = new ArrayList<>();
    this.mText = null;
  }
  
  private UriMatcher(int paramInt, String paramString) {
    this.mCode = -1;
    this.mWhich = paramInt;
    this.mChildren = new ArrayList<>();
    this.mText = paramString;
  }
  
  private static UriMatcher createChild(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual hashCode : ()I
    //   4: istore_1
    //   5: iload_1
    //   6: bipush #35
    //   8: if_icmpeq -> 34
    //   11: iload_1
    //   12: bipush #42
    //   14: if_icmpeq -> 20
    //   17: goto -> 48
    //   20: aload_0
    //   21: ldc '*'
    //   23: invokevirtual equals : (Ljava/lang/Object;)Z
    //   26: ifeq -> 17
    //   29: iconst_1
    //   30: istore_1
    //   31: goto -> 50
    //   34: aload_0
    //   35: ldc '#'
    //   37: invokevirtual equals : (Ljava/lang/Object;)Z
    //   40: ifeq -> 17
    //   43: iconst_0
    //   44: istore_1
    //   45: goto -> 50
    //   48: iconst_m1
    //   49: istore_1
    //   50: iload_1
    //   51: ifeq -> 80
    //   54: iload_1
    //   55: iconst_1
    //   56: if_icmpeq -> 69
    //   59: new android/content/UriMatcher
    //   62: dup
    //   63: iconst_0
    //   64: aload_0
    //   65: invokespecial <init> : (ILjava/lang/String;)V
    //   68: areturn
    //   69: new android/content/UriMatcher
    //   72: dup
    //   73: iconst_2
    //   74: ldc '*'
    //   76: invokespecial <init> : (ILjava/lang/String;)V
    //   79: areturn
    //   80: new android/content/UriMatcher
    //   83: dup
    //   84: iconst_1
    //   85: ldc '#'
    //   87: invokespecial <init> : (ILjava/lang/String;)V
    //   90: areturn
  }
  
  public void addURI(String paramString1, String paramString2, int paramInt) {
    if (paramInt >= 0) {
      String[] arrayOfString;
      String str = null;
      int i = 0;
      if (paramString2 != null) {
        str = paramString2;
        String str1 = str;
        if (paramString2.length() > 1) {
          str1 = str;
          if (paramString2.charAt(0) == '/')
            str1 = paramString2.substring(1); 
        } 
        arrayOfString = str1.split("/");
      } 
      if (arrayOfString != null)
        i = arrayOfString.length; 
      UriMatcher uriMatcher = this;
      for (byte b = -1; b < i; b++) {
        UriMatcher uriMatcher1;
        String str1;
        if (b < 0) {
          str1 = paramString1;
        } else {
          str1 = arrayOfString[b];
        } 
        ArrayList<UriMatcher> arrayList = uriMatcher.mChildren;
        int j = arrayList.size();
        byte b1 = 0;
        while (true) {
          uriMatcher1 = uriMatcher;
          if (b1 < j) {
            uriMatcher1 = arrayList.get(b1);
            if (str1.equals(uriMatcher1.mText))
              break; 
            b1++;
            continue;
          } 
          break;
        } 
        uriMatcher = uriMatcher1;
        if (b1 == j) {
          uriMatcher = createChild(str1);
          uriMatcher1.mChildren.add(uriMatcher);
        } 
      } 
      uriMatcher.mCode = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("code ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" is invalid: it must be positive");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public int match(Uri paramUri) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getPathSegments : ()Ljava/util/List;
    //   4: astore_2
    //   5: aload_2
    //   6: invokeinterface size : ()I
    //   11: istore_3
    //   12: aload_0
    //   13: astore #4
    //   15: iload_3
    //   16: ifne -> 31
    //   19: aload_1
    //   20: invokevirtual getAuthority : ()Ljava/lang/String;
    //   23: ifnonnull -> 31
    //   26: aload_0
    //   27: getfield mCode : I
    //   30: ireturn
    //   31: iconst_m1
    //   32: istore #5
    //   34: iload #5
    //   36: iload_3
    //   37: if_icmpge -> 272
    //   40: iload #5
    //   42: ifge -> 54
    //   45: aload_1
    //   46: invokevirtual getAuthority : ()Ljava/lang/String;
    //   49: astore #6
    //   51: goto -> 67
    //   54: aload_2
    //   55: iload #5
    //   57: invokeinterface get : (I)Ljava/lang/Object;
    //   62: checkcast java/lang/String
    //   65: astore #6
    //   67: aload #4
    //   69: getfield mChildren : Ljava/util/ArrayList;
    //   72: astore #7
    //   74: aload #7
    //   76: ifnonnull -> 82
    //   79: goto -> 272
    //   82: aconst_null
    //   83: astore #8
    //   85: aload #7
    //   87: invokevirtual size : ()I
    //   90: istore #9
    //   92: iconst_0
    //   93: istore #10
    //   95: aload #8
    //   97: astore #4
    //   99: iload #10
    //   101: iload #9
    //   103: if_icmpge -> 259
    //   106: aload #7
    //   108: iload #10
    //   110: invokevirtual get : (I)Ljava/lang/Object;
    //   113: checkcast android/content/UriMatcher
    //   116: astore #11
    //   118: aload #11
    //   120: getfield mWhich : I
    //   123: istore #12
    //   125: iload #12
    //   127: ifeq -> 220
    //   130: iload #12
    //   132: iconst_1
    //   133: if_icmpeq -> 156
    //   136: iload #12
    //   138: iconst_2
    //   139: if_icmpeq -> 149
    //   142: aload #8
    //   144: astore #4
    //   146: goto -> 241
    //   149: aload #11
    //   151: astore #4
    //   153: goto -> 241
    //   156: aload #6
    //   158: invokevirtual length : ()I
    //   161: istore #13
    //   163: iconst_0
    //   164: istore #12
    //   166: iload #12
    //   168: iload #13
    //   170: if_icmpge -> 213
    //   173: aload #6
    //   175: iload #12
    //   177: invokevirtual charAt : (I)C
    //   180: istore #14
    //   182: aload #8
    //   184: astore #4
    //   186: iload #14
    //   188: bipush #48
    //   190: if_icmplt -> 241
    //   193: iload #14
    //   195: bipush #57
    //   197: if_icmple -> 207
    //   200: aload #8
    //   202: astore #4
    //   204: goto -> 241
    //   207: iinc #12, 1
    //   210: goto -> 166
    //   213: aload #11
    //   215: astore #4
    //   217: goto -> 241
    //   220: aload #8
    //   222: astore #4
    //   224: aload #11
    //   226: getfield mText : Ljava/lang/String;
    //   229: aload #6
    //   231: invokevirtual equals : (Ljava/lang/Object;)Z
    //   234: ifeq -> 241
    //   237: aload #11
    //   239: astore #4
    //   241: aload #4
    //   243: ifnull -> 249
    //   246: goto -> 259
    //   249: iinc #10, 1
    //   252: aload #4
    //   254: astore #8
    //   256: goto -> 95
    //   259: aload #4
    //   261: ifnonnull -> 266
    //   264: iconst_m1
    //   265: ireturn
    //   266: iinc #5, 1
    //   269: goto -> 34
    //   272: aload #4
    //   274: getfield mCode : I
    //   277: ireturn
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/UriMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */