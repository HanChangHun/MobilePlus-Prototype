package android.content.integrity;

public final class Application {
  public static IntegrityFormula certificatesContain(String paramString) {
    return new AtomicFormula.StringAtomicFormula(1, paramString);
  }
  
  public static IntegrityFormula isPreInstalled() {
    return new AtomicFormula.BooleanAtomicFormula(5, true);
  }
  
  public static IntegrityFormula packageNameEquals(String paramString) {
    return new AtomicFormula.StringAtomicFormula(0, paramString);
  }
  
  public static IntegrityFormula versionCodeEquals(long paramLong) {
    return new AtomicFormula.LongAtomicFormula(4, 0, paramLong);
  }
  
  public static IntegrityFormula versionCodeGreaterThan(long paramLong) {
    return new AtomicFormula.LongAtomicFormula(4, 1, paramLong);
  }
  
  public static IntegrityFormula versionCodeGreaterThanOrEqualTo(long paramLong) {
    return new AtomicFormula.LongAtomicFormula(4, 2, paramLong);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/IntegrityFormula$Application.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */