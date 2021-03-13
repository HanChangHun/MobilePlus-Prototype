package android.content.pm;

import android.content.res.TypedArray;

class ParsePackageItemArgs {
  final int bannerRes;
  
  final int iconRes;
  
  final int labelRes;
  
  final int logoRes;
  
  final int nameRes;
  
  final String[] outError;
  
  final PackageParser.Package owner;
  
  final int roundIconRes;
  
  TypedArray sa;
  
  String tag;
  
  ParsePackageItemArgs(PackageParser.Package paramPackage, String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    this.owner = paramPackage;
    this.outError = paramArrayOfString;
    this.nameRes = paramInt1;
    this.labelRes = paramInt2;
    this.iconRes = paramInt3;
    this.logoRes = paramInt5;
    this.bannerRes = paramInt6;
    this.roundIconRes = paramInt4;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$ParsePackageItemArgs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */