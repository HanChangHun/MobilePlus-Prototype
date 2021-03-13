package android.filterfw.io;

import android.filterfw.core.FilterFactory;

class AddLibraryCommand implements TextGraphReader.Command {
  private String mLibraryName;
  
  public AddLibraryCommand(String paramString) {
    this.mLibraryName = paramString;
  }
  
  public void execute(TextGraphReader paramTextGraphReader) {
    TextGraphReader.access$000(paramTextGraphReader);
    FilterFactory.addFilterLibrary(this.mLibraryName);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/io/TextGraphReader$AddLibraryCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */