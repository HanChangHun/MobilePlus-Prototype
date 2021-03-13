package android.filterfw.core;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class KeyValueMap extends HashMap<String, Object> {
  public static KeyValueMap fromKeyValues(Object... paramVarArgs) {
    KeyValueMap keyValueMap = new KeyValueMap();
    keyValueMap.setKeyValues(paramVarArgs);
    return keyValueMap;
  }
  
  public float getFloat(String paramString) {
    Object object = get(paramString);
    if (object != null) {
      object = object;
    } else {
      object = null;
    } 
    return object.floatValue();
  }
  
  public int getInt(String paramString) {
    Object object = get(paramString);
    if (object != null) {
      object = object;
    } else {
      object = null;
    } 
    return object.intValue();
  }
  
  public String getString(String paramString) {
    Object object = get(paramString);
    if (object != null) {
      object = object;
    } else {
      object = null;
    } 
    return (String)object;
  }
  
  public void setKeyValues(Object... paramVarArgs) {
    if (paramVarArgs.length % 2 == 0) {
      byte b = 0;
      while (b < paramVarArgs.length) {
        if (paramVarArgs[b] instanceof String) {
          put((String)paramVarArgs[b], paramVarArgs[b + 1]);
          b += 2;
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Key-value argument ");
        stringBuilder.append(b);
        stringBuilder.append(" must be a key of type String, but found an object of type ");
        stringBuilder.append(paramVarArgs[b].getClass());
        stringBuilder.append("!");
        throw new RuntimeException(stringBuilder.toString());
      } 
      return;
    } 
    throw new RuntimeException("Key-Value arguments passed into setKeyValues must be an alternating list of keys and values!");
  }
  
  public String toString() {
    StringWriter stringWriter = new StringWriter();
    for (Map.Entry<String, Object> entry : entrySet()) {
      String str;
      Object object = entry.getValue();
      if (object instanceof String) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\"");
        stringBuilder.append(object);
        stringBuilder.append("\"");
        str = stringBuilder.toString();
      } else {
        str = object.toString();
      } 
      object = new StringBuilder();
      object.append((String)entry.getKey());
      object.append(" = ");
      object.append(str);
      object.append(";\n");
      stringWriter.write(object.toString());
    } 
    return stringWriter.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/KeyValueMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */