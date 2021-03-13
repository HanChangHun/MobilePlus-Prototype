package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.GenerateFieldPort;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class OutputStreamTarget extends Filter {
  @GenerateFieldPort(name = "stream")
  private OutputStream mOutputStream;
  
  public OutputStreamTarget(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    ByteBuffer byteBuffer;
    Frame frame = pullInput("data");
    if (frame.getFormat().getObjectClass() == String.class) {
      byteBuffer = ByteBuffer.wrap(((String)frame.getObjectValue()).getBytes());
    } else {
      byteBuffer = byteBuffer.getData();
    } 
    try {
      this.mOutputStream.write(byteBuffer.array(), 0, byteBuffer.limit());
      this.mOutputStream.flush();
      return;
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("OutputStreamTarget: Could not write to stream: ");
      stringBuilder.append(iOException.getMessage());
      stringBuilder.append("!");
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  public void setupPorts() {
    addInputPort("data");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/OutputStreamTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */