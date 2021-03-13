package android.filterfw.io;

class ImportPackageCommand implements TextGraphReader.Command {
  private String mPackageName;
  
  public ImportPackageCommand(String paramString) {
    this.mPackageName = paramString;
  }
  
  public void execute(TextGraphReader paramTextGraphReader) throws GraphIOException {
    try {
      TextGraphReader.access$000(paramTextGraphReader).addPackage(this.mPackageName);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new GraphIOException(illegalArgumentException.getMessage());
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/io/TextGraphReader$ImportPackageCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */