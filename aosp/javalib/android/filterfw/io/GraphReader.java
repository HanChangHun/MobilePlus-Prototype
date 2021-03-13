package android.filterfw.io;

import android.content.Context;
import android.filterfw.core.FilterGraph;
import android.filterfw.core.KeyValueMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

public abstract class GraphReader {
  protected KeyValueMap mReferences = new KeyValueMap();
  
  public void addReference(String paramString, Object paramObject) {
    this.mReferences.put(paramString, paramObject);
  }
  
  public void addReferencesByKeysAndValues(Object... paramVarArgs) {
    this.mReferences.setKeyValues(paramVarArgs);
  }
  
  public void addReferencesByMap(KeyValueMap paramKeyValueMap) {
    this.mReferences.putAll((Map)paramKeyValueMap);
  }
  
  public FilterGraph readGraphResource(Context paramContext, int paramInt) throws GraphIOException {
    InputStreamReader inputStreamReader = new InputStreamReader(paramContext.getResources().openRawResource(paramInt));
    StringWriter stringWriter = new StringWriter();
    char[] arrayOfChar = new char[1024];
    try {
      while (true) {
        paramInt = inputStreamReader.read(arrayOfChar, 0, 1024);
        if (paramInt > 0) {
          stringWriter.write(arrayOfChar, 0, paramInt);
          continue;
        } 
        return readGraphString(stringWriter.toString());
      } 
    } catch (IOException iOException) {
      throw new RuntimeException("Could not read specified resource file!");
    } 
  }
  
  public abstract FilterGraph readGraphString(String paramString) throws GraphIOException;
  
  public abstract KeyValueMap readKeyValueAssignments(String paramString) throws GraphIOException;
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/io/GraphReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */