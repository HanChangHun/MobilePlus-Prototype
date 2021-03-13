package android.content.pm;

public class ParseComponentArgs extends PackageParser.ParsePackageItemArgs {
  final int descriptionRes;
  
  final int enabledRes;
  
  int flags;
  
  final int processRes;
  
  final String[] sepProcesses;
  
  public ParseComponentArgs(PackageParser.Package paramPackage, String[] paramArrayOfString1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, String[] paramArrayOfString2, int paramInt7, int paramInt8, int paramInt9) {
    super(paramPackage, paramArrayOfString1, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
    this.sepProcesses = paramArrayOfString2;
    this.processRes = paramInt7;
    this.descriptionRes = paramInt8;
    this.enabledRes = paramInt9;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$ParseComponentArgs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */