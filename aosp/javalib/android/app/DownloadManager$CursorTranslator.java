package android.app;

import android.content.ContentUris;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.provider.Downloads;
import java.io.File;

class CursorTranslator extends CursorWrapper {
  private final boolean mAccessFilename;
  
  private final Uri mBaseUri;
  
  public CursorTranslator(Cursor paramCursor, Uri paramUri, boolean paramBoolean) {
    super(paramCursor);
    this.mBaseUri = paramUri;
    this.mAccessFilename = paramBoolean;
  }
  
  private long getErrorCode(int paramInt) {
    if ((400 <= paramInt && paramInt < 488) || (500 <= paramInt && paramInt < 600))
      return paramInt; 
    if (paramInt != 198) {
      if (paramInt != 199) {
        if (paramInt != 488) {
          if (paramInt != 489) {
            if (paramInt != 497) {
              switch (paramInt) {
                default:
                  return 1000L;
                case 495:
                  return 1004L;
                case 493:
                case 494:
                  return 1002L;
                case 492:
                  break;
              } 
              return 1001L;
            } 
            return 1005L;
          } 
          return 1008L;
        } 
        return 1009L;
      } 
      return 1007L;
    } 
    return 1006L;
  }
  
  private String getLocalUri() {
    long l = getLong(getColumnIndex("destination"));
    if (l == 4L || l == 0L || l == 6L) {
      String str = super.getString(getColumnIndex("local_filename"));
      return (str == null) ? null : Uri.fromFile(new File(str)).toString();
    } 
    l = getLong(getColumnIndex("_id"));
    return ContentUris.withAppendedId(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, l).toString();
  }
  
  private long getPausedReason(int paramInt) {
    switch (paramInt) {
      default:
        return 4L;
      case 196:
        return 3L;
      case 195:
        return 2L;
      case 194:
        break;
    } 
    return 1L;
  }
  
  private long getReason(int paramInt) {
    int i = translateStatus(paramInt);
    return (i != 4) ? ((i != 16) ? 0L : getErrorCode(paramInt)) : getPausedReason(paramInt);
  }
  
  private int translateStatus(int paramInt) {
    if (paramInt != 190) {
      if (paramInt != 200) {
        switch (paramInt) {
          default:
            return 16;
          case 193:
          case 194:
          case 195:
          case 196:
            return 4;
          case 192:
            break;
        } 
        return 2;
      } 
      return 8;
    } 
    return 1;
  }
  
  public int getInt(int paramInt) {
    return (int)getLong(paramInt);
  }
  
  public long getLong(int paramInt) {
    return getColumnName(paramInt).equals("reason") ? getReason(super.getInt(getColumnIndex("status"))) : (getColumnName(paramInt).equals("status") ? translateStatus(super.getInt(getColumnIndex("status"))) : super.getLong(paramInt));
  }
  
  public String getString(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: invokevirtual getColumnName : (I)Ljava/lang/String;
    //   5: astore_2
    //   6: aload_2
    //   7: invokevirtual hashCode : ()I
    //   10: istore_3
    //   11: iload_3
    //   12: ldc -1204869480
    //   14: if_icmpeq -> 40
    //   17: iload_3
    //   18: ldc 22072411
    //   20: if_icmpeq -> 26
    //   23: goto -> 54
    //   26: aload_2
    //   27: ldc 'local_filename'
    //   29: invokevirtual equals : (Ljava/lang/Object;)Z
    //   32: ifeq -> 23
    //   35: iconst_1
    //   36: istore_3
    //   37: goto -> 56
    //   40: aload_2
    //   41: ldc 'local_uri'
    //   43: invokevirtual equals : (Ljava/lang/Object;)Z
    //   46: ifeq -> 23
    //   49: iconst_0
    //   50: istore_3
    //   51: goto -> 56
    //   54: iconst_m1
    //   55: istore_3
    //   56: iload_3
    //   57: ifeq -> 91
    //   60: iload_3
    //   61: iconst_1
    //   62: if_icmpeq -> 68
    //   65: goto -> 75
    //   68: aload_0
    //   69: getfield mAccessFilename : Z
    //   72: ifeq -> 81
    //   75: aload_0
    //   76: iload_1
    //   77: invokespecial getString : (I)Ljava/lang/String;
    //   80: areturn
    //   81: new java/lang/SecurityException
    //   84: dup
    //   85: ldc 'COLUMN_LOCAL_FILENAME is deprecated; use ContentResolver.openFileDescriptor() instead'
    //   87: invokespecial <init> : (Ljava/lang/String;)V
    //   90: athrow
    //   91: aload_0
    //   92: invokespecial getLocalUri : ()Ljava/lang/String;
    //   95: areturn
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DownloadManager$CursorTranslator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */