package android.filterfw.io;

import android.filterfw.core.Filter;
import android.filterfw.core.KeyValueMap;
import android.filterfw.core.ProtocolException;

class InitFilterCommand implements TextGraphReader.Command {
  private KeyValueMap mParams;
  
  public InitFilterCommand(KeyValueMap paramKeyValueMap) {
    this.mParams = paramKeyValueMap;
  }
  
  public void execute(TextGraphReader paramTextGraphReader) throws GraphIOException {
    Filter filter = TextGraphReader.access$100(paramTextGraphReader);
    try {
      filter.initWithValueMap(this.mParams);
      TextGraphReader.access$200(paramTextGraphReader).addFilter(TextGraphReader.access$100(TextGraphReader.this));
      return;
    } catch (ProtocolException protocolException) {
      throw new GraphIOException(protocolException.getMessage());
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/io/TextGraphReader$InitFilterCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */