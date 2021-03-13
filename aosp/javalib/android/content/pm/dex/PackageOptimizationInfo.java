package android.content.pm.dex;

public class PackageOptimizationInfo {
  private final int mCompilationFilter;
  
  private final int mCompilationReason;
  
  public PackageOptimizationInfo(int paramInt1, int paramInt2) {
    this.mCompilationReason = paramInt2;
    this.mCompilationFilter = paramInt1;
  }
  
  public static PackageOptimizationInfo createWithNoInfo() {
    return new PackageOptimizationInfo(-1, -1);
  }
  
  public int getCompilationFilter() {
    return this.mCompilationFilter;
  }
  
  public int getCompilationReason() {
    return this.mCompilationReason;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/PackageOptimizationInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */