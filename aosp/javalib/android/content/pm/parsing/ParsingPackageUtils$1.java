package android.content.pm.parsing;

import android.content.res.TypedArray;

class null implements ParsingPackageUtils.Callback {
  public boolean hasFeature(String paramString) {
    return false;
  }
  
  public ParsingPackage startParsingPackage(String paramString1, String paramString2, String paramString3, TypedArray paramTypedArray, boolean paramBoolean) {
    return new ParsingPackageImpl(paramString1, paramString2, paramString3, paramTypedArray);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/ParsingPackageUtils$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */