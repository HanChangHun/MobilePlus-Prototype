package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;
import android.graphics.Bitmap;

public class BitmapOverlayFilter extends Filter {
  @GenerateFieldPort(name = "bitmap")
  private Bitmap mBitmap;
  
  private Frame mFrame;
  
  private final String mOverlayShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 original = texture2D(tex_sampler_0, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_1, v_texcoord);\n  gl_FragColor = vec4(original.rgb * (1.0 - mask.a) + mask.rgb, 1.0);\n}\n";
  
  private Program mProgram;
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  public BitmapOverlayFilter(String paramString) {
    super(paramString);
  }
  
  private Frame createBitmapFrame(FilterContext paramFilterContext) {
    MutableFrameFormat mutableFrameFormat = ImageFormat.create(this.mBitmap.getWidth(), this.mBitmap.getHeight(), 3, 3);
    Frame frame = paramFilterContext.getFrameManager().newFrame((FrameFormat)mutableFrameFormat);
    frame.setBitmap(this.mBitmap);
    this.mBitmap.recycle();
    this.mBitmap = null;
    return frame;
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void initProgram(FilterContext paramFilterContext, int paramInt) {
    if (paramInt == 3) {
      ShaderProgram shaderProgram = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 original = texture2D(tex_sampler_0, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_1, v_texcoord);\n  gl_FragColor = vec4(original.rgb * (1.0 - mask.a) + mask.rgb, 1.0);\n}\n");
      shaderProgram.setMaximumTileSize(this.mTileSize);
      this.mProgram = (Program)shaderProgram;
      this.mTarget = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Filter FisheyeFilter does not support frames of target ");
    stringBuilder.append(paramInt);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame1 = pullInput("image");
    FrameFormat frameFormat = frame1.getFormat();
    Frame frame2 = paramFilterContext.getFrameManager().newFrame(frameFormat);
    if (this.mProgram == null || frameFormat.getTarget() != this.mTarget)
      initProgram(paramFilterContext, frameFormat.getTarget()); 
    if (this.mBitmap != null) {
      Frame frame = createBitmapFrame(paramFilterContext);
      this.mProgram.process(new Frame[] { frame1, frame }, frame2);
      frame.release();
    } else {
      frame2.setDataFromFrame(frame1);
    } 
    pushOutput("image", frame2);
    frame2.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
  
  public void tearDown(FilterContext paramFilterContext) {
    Frame frame = this.mFrame;
    if (frame != null) {
      frame.release();
      this.mFrame = null;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/BitmapOverlayFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */