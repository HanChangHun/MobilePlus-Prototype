package android.content.pm.parsing;

import android.content.res.TypedArray;

public interface Callback {
  boolean hasFeature(String paramString);
  
  ParsingPackage startParsingPackage(String paramString1, String paramString2, String paramString3, TypedArray paramTypedArray, boolean paramBoolean);
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/ParsingPackageUtils$Callback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */