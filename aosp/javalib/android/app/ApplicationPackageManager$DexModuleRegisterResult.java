package android.app;

class DexModuleRegisterResult {
  final String dexModulePath;
  
  final String message;
  
  final boolean success;
  
  private DexModuleRegisterResult(String paramString1, boolean paramBoolean, String paramString2) {
    this.dexModulePath = paramString1;
    this.success = paramBoolean;
    this.message = paramString2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationPackageManager$DexModuleRegisterResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */