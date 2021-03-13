package android.database.sqlite;

import java.util.ArrayList;

final class Operation {
  private static final int MAX_TRACE_METHOD_NAME_LEN = 256;
  
  public ArrayList<Object> mBindArgs;
  
  public int mCookie;
  
  public long mEndTime;
  
  public Exception mException;
  
  public boolean mFinished;
  
  public String mKind;
  
  public String mPath;
  
  public long mResultLong;
  
  public String mResultString;
  
  public String mSql;
  
  public long mStartTime;
  
  public long mStartWallTime;
  
  private Operation() {}
  
  private String getStatus() {
    String str;
    if (!this.mFinished)
      return "running"; 
    if (this.mException != null) {
      str = "failed";
    } else {
      str = "succeeded";
    } 
    return str;
  }
  
  private String getTraceMethodName() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mKind);
    stringBuilder.append(" ");
    stringBuilder.append(this.mSql);
    String str = stringBuilder.toString();
    return (str.length() > 256) ? str.substring(0, 256) : str;
  }
  
  public void describe(StringBuilder paramStringBuilder, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: aload_0
    //   2: getfield mKind : Ljava/lang/String;
    //   5: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   8: pop
    //   9: aload_0
    //   10: getfield mFinished : Z
    //   13: ifeq -> 47
    //   16: aload_1
    //   17: ldc ' took '
    //   19: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: pop
    //   23: aload_1
    //   24: aload_0
    //   25: getfield mEndTime : J
    //   28: aload_0
    //   29: getfield mStartTime : J
    //   32: lsub
    //   33: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   36: pop
    //   37: aload_1
    //   38: ldc 'ms'
    //   40: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: goto -> 74
    //   47: aload_1
    //   48: ldc ' started '
    //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload_1
    //   55: invokestatic currentTimeMillis : ()J
    //   58: aload_0
    //   59: getfield mStartWallTime : J
    //   62: lsub
    //   63: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload_1
    //   68: ldc 'ms ago'
    //   70: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: pop
    //   74: aload_1
    //   75: ldc ' - '
    //   77: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: pop
    //   81: aload_1
    //   82: aload_0
    //   83: invokespecial getStatus : ()Ljava/lang/String;
    //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: pop
    //   90: aload_0
    //   91: getfield mSql : Ljava/lang/String;
    //   94: ifnull -> 123
    //   97: aload_1
    //   98: ldc ', sql="'
    //   100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: pop
    //   104: aload_1
    //   105: aload_0
    //   106: getfield mSql : Ljava/lang/String;
    //   109: invokestatic access$200 : (Ljava/lang/String;)Ljava/lang/String;
    //   112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: aload_1
    //   117: ldc '"'
    //   119: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: pop
    //   123: iload_2
    //   124: ifeq -> 155
    //   127: getstatic android/database/sqlite/SQLiteDebug$NoPreloadHolder.DEBUG_LOG_DETAILED : Z
    //   130: ifeq -> 155
    //   133: aload_0
    //   134: getfield mBindArgs : Ljava/util/ArrayList;
    //   137: astore_3
    //   138: aload_3
    //   139: ifnull -> 155
    //   142: aload_3
    //   143: invokevirtual size : ()I
    //   146: ifeq -> 155
    //   149: iconst_1
    //   150: istore #4
    //   152: goto -> 158
    //   155: iconst_0
    //   156: istore #4
    //   158: iload #4
    //   160: ifeq -> 294
    //   163: aload_1
    //   164: ldc ', bindArgs=['
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: aload_0
    //   171: getfield mBindArgs : Ljava/util/ArrayList;
    //   174: invokevirtual size : ()I
    //   177: istore #5
    //   179: iconst_0
    //   180: istore #4
    //   182: iload #4
    //   184: iload #5
    //   186: if_icmpge -> 287
    //   189: aload_0
    //   190: getfield mBindArgs : Ljava/util/ArrayList;
    //   193: iload #4
    //   195: invokevirtual get : (I)Ljava/lang/Object;
    //   198: astore_3
    //   199: iload #4
    //   201: ifeq -> 211
    //   204: aload_1
    //   205: ldc ', '
    //   207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: pop
    //   211: aload_3
    //   212: ifnonnull -> 225
    //   215: aload_1
    //   216: ldc 'null'
    //   218: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: pop
    //   222: goto -> 281
    //   225: aload_3
    //   226: instanceof [B
    //   229: ifeq -> 242
    //   232: aload_1
    //   233: ldc '<byte[]>'
    //   235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: pop
    //   239: goto -> 281
    //   242: aload_3
    //   243: instanceof java/lang/String
    //   246: ifeq -> 275
    //   249: aload_1
    //   250: ldc '"'
    //   252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: pop
    //   256: aload_1
    //   257: aload_3
    //   258: checkcast java/lang/String
    //   261: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: pop
    //   265: aload_1
    //   266: ldc '"'
    //   268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: pop
    //   272: goto -> 281
    //   275: aload_1
    //   276: aload_3
    //   277: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   280: pop
    //   281: iinc #4, 1
    //   284: goto -> 182
    //   287: aload_1
    //   288: ldc ']'
    //   290: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: pop
    //   294: aload_1
    //   295: ldc ', path='
    //   297: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: pop
    //   301: aload_1
    //   302: aload_0
    //   303: getfield mPath : Ljava/lang/String;
    //   306: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: pop
    //   310: aload_0
    //   311: getfield mException : Ljava/lang/Exception;
    //   314: ifnull -> 343
    //   317: aload_1
    //   318: ldc ', exception="'
    //   320: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: pop
    //   324: aload_1
    //   325: aload_0
    //   326: getfield mException : Ljava/lang/Exception;
    //   329: invokevirtual getMessage : ()Ljava/lang/String;
    //   332: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   335: pop
    //   336: aload_1
    //   337: ldc '"'
    //   339: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: pop
    //   343: aload_0
    //   344: getfield mResultLong : J
    //   347: ldc2_w -9223372036854775808
    //   350: lcmp
    //   351: ifeq -> 370
    //   354: aload_1
    //   355: ldc ', result='
    //   357: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   360: pop
    //   361: aload_1
    //   362: aload_0
    //   363: getfield mResultLong : J
    //   366: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   369: pop
    //   370: aload_0
    //   371: getfield mResultString : Ljava/lang/String;
    //   374: ifnull -> 400
    //   377: aload_1
    //   378: ldc ', result="'
    //   380: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: pop
    //   384: aload_1
    //   385: aload_0
    //   386: getfield mResultString : Ljava/lang/String;
    //   389: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: pop
    //   393: aload_1
    //   394: ldc '"'
    //   396: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: pop
    //   400: return
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteConnection$Operation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */