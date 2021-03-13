package android.content;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import java.util.Arrays;

class Logger implements AutoCloseable {
  private final StringBuilder sb = new StringBuilder();
  
  public Logger(String paramString, Object... paramVarArgs) {
    int i = paramVarArgs.length;
    for (byte b = 0; b < i; b++) {
      Object object = paramVarArgs[b];
      if (object instanceof Bundle)
        ((Bundle)object).size(); 
    } 
    StringBuilder stringBuilder = this.sb;
    stringBuilder.append("callingUid=");
    stringBuilder.append(Binder.getCallingUid());
    stringBuilder.append(' ');
    this.sb.append(paramString);
    stringBuilder = this.sb;
    stringBuilder.append('(');
    stringBuilder.append(deepToString(paramVarArgs));
    stringBuilder.append(')');
  }
  
  private String deepToString(Object paramObject) {
    return (paramObject != null && paramObject.getClass().isArray()) ? Arrays.deepToString((Object[])paramObject) : String.valueOf(paramObject);
  }
  
  public void close() {
    Log.v(LoggingContentInterface.access$000(LoggingContentInterface.this), this.sb.toString());
  }
  
  public <T> T setResult(T paramT) {
    if (paramT instanceof Cursor) {
      this.sb.append('\n');
      DatabaseUtils.dumpCursor((Cursor)paramT, this.sb);
    } else {
      StringBuilder stringBuilder = this.sb;
      stringBuilder.append(" = ");
      stringBuilder.append(deepToString(paramT));
    } 
    return paramT;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/LoggingContentInterface$Logger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */