package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.GenerateFinalPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.format.PrimitiveFormat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class InputStreamSource extends Filter {
  @GenerateFieldPort(name = "stream")
  private InputStream mInputStream;
  
  @GenerateFinalPort(hasDefault = true, name = "format")
  private MutableFrameFormat mOutputFormat = null;
  
  @GenerateFinalPort(name = "target")
  private String mTarget;
  
  public InputStreamSource(String paramString) {
    super(paramString);
  }
  
  public void process(FilterContext paramFilterContext) {
    int i = 0;
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      byte[] arrayOfByte = new byte[1024];
      while (true) {
        int j = this.mInputStream.read(arrayOfByte);
        if (j > 0) {
          byteArrayOutputStream.write(arrayOfByte, 0, j);
          i += j;
          continue;
        } 
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        this.mOutputFormat.setDimensions(i);
        Frame frame = paramFilterContext.getFrameManager().newFrame((FrameFormat)this.mOutputFormat);
        frame.setData(byteBuffer);
        pushOutput("data", frame);
        frame.release();
        closeOutputPort("data");
        return;
      } 
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("InputStreamSource: Could not read stream: ");
      stringBuilder.append(iOException.getMessage());
      stringBuilder.append("!");
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  public void setupPorts() {
    int i = FrameFormat.readTargetString(this.mTarget);
    if (this.mOutputFormat == null)
      this.mOutputFormat = PrimitiveFormat.createByteFormat(i); 
    addOutputPort("data", (FrameFormat)this.mOutputFormat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/InputStreamSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */