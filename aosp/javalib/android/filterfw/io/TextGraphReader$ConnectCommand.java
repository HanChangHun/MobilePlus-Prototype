package android.filterfw.io;

class ConnectCommand implements TextGraphReader.Command {
  private String mSourceFilter;
  
  private String mSourcePort;
  
  private String mTargetFilter;
  
  private String mTargetName;
  
  public ConnectCommand(String paramString1, String paramString2, String paramString3, String paramString4) {
    this.mSourceFilter = paramString1;
    this.mSourcePort = paramString2;
    this.mTargetFilter = paramString3;
    this.mTargetName = paramString4;
  }
  
  public void execute(TextGraphReader paramTextGraphReader) {
    TextGraphReader.access$200(paramTextGraphReader).connect(this.mSourceFilter, this.mSourcePort, this.mTargetFilter, this.mTargetName);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/io/TextGraphReader$ConnectCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */