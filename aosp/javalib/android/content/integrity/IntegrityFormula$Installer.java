package android.content.integrity;

public final class Installer {
  public static IntegrityFormula certificatesContain(String paramString) {
    return new AtomicFormula.StringAtomicFormula(3, paramString);
  }
  
  public static IntegrityFormula notAllowedByManifest() {
    return IntegrityFormula.not(new InstallerAllowedByManifestFormula());
  }
  
  public static IntegrityFormula packageNameEquals(String paramString) {
    return new AtomicFormula.StringAtomicFormula(2, paramString);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/IntegrityFormula$Installer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */