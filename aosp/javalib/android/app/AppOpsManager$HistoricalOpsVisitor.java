package android.app;

public interface HistoricalOpsVisitor {
  void visitHistoricalAttributionOps(AppOpsManager.AttributedHistoricalOps paramAttributedHistoricalOps);
  
  void visitHistoricalOp(AppOpsManager.HistoricalOp paramHistoricalOp);
  
  void visitHistoricalOps(AppOpsManager.HistoricalOps paramHistoricalOps);
  
  void visitHistoricalPackageOps(AppOpsManager.HistoricalPackageOps paramHistoricalPackageOps);
  
  void visitHistoricalUidOps(AppOpsManager.HistoricalUidOps paramHistoricalUidOps);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$HistoricalOpsVisitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */