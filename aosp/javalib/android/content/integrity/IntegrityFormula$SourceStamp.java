package android.content.integrity;

public final class SourceStamp {
  public static IntegrityFormula notTrusted() {
    return new AtomicFormula.BooleanAtomicFormula(6, false);
  }
  
  public static IntegrityFormula stampCertificateHashEquals(String paramString) {
    return new AtomicFormula.StringAtomicFormula(7, paramString);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/IntegrityFormula$SourceStamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */