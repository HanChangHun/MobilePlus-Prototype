package android.filterfw.io;

import android.filterfw.core.Filter;

class AllocateFilterCommand implements TextGraphReader.Command {
  private String mClassName;
  
  private String mFilterName;
  
  public AllocateFilterCommand(String paramString1, String paramString2) {
    this.mClassName = paramString1;
    this.mFilterName = paramString2;
  }
  
  public void execute(TextGraphReader paramTextGraphReader) throws GraphIOException {
    try {
      Filter filter = TextGraphReader.access$000(paramTextGraphReader).createFilterByClassName(this.mClassName, this.mFilterName);
      TextGraphReader.access$102(paramTextGraphReader, filter);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new GraphIOException(illegalArgumentException.getMessage());
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/io/TextGraphReader$AllocateFilterCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */