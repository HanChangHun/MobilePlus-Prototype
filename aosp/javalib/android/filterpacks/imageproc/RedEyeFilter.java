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
import android.graphics.Canvas;
import android.graphics.Paint;

public class RedEyeFilter extends Filter {
  private static final float DEFAULT_RED_INTENSITY = 1.3F;
  
  private static final float MIN_RADIUS = 10.0F;
  
  private static final float RADIUS_RATIO = 0.06F;
  
  private final Canvas mCanvas = new Canvas();
  
  @GenerateFieldPort(name = "centers")
  private float[] mCenters;
  
  private int mHeight = 0;
  
  private final Paint mPaint = new Paint();
  
  private Program mProgram;
  
  private float mRadius;
  
  private Bitmap mRedEyeBitmap;
  
  private Frame mRedEyeFrame;
  
  private final String mRedEyeShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float intensity;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_1, v_texcoord);\n  if (mask.a > 0.0) {\n    float green_blue = color.g + color.b;\n    float red_intensity = color.r / green_blue;\n    if (red_intensity > intensity) {\n      color.r = 0.5 * green_blue;\n    }\n  }\n  gl_FragColor = color;\n}\n";
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  private int mWidth = 0;
  
  public RedEyeFilter(String paramString) {
    super(paramString);
  }
  
  private void createRedEyeFrame(FilterContext paramFilterContext) {
    int i = this.mWidth / 2;
    int j = this.mHeight / 2;
    Bitmap bitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    this.mCanvas.setBitmap(bitmap);
    this.mPaint.setColor(-1);
    this.mRadius = Math.max(10.0F, Math.min(i, j) * 0.06F);
    byte b = 0;
    while (true) {
      float[] arrayOfFloat = this.mCenters;
      if (b < arrayOfFloat.length) {
        this.mCanvas.drawCircle(arrayOfFloat[b] * i, arrayOfFloat[b + 1] * j, this.mRadius, this.mPaint);
        b += 2;
        continue;
      } 
      MutableFrameFormat mutableFrameFormat = ImageFormat.create(i, j, 3, 3);
      Frame frame = paramFilterContext.getFrameManager().newFrame((FrameFormat)mutableFrameFormat);
      this.mRedEyeFrame = frame;
      frame.setBitmap(bitmap);
      bitmap.recycle();
      return;
    } 
  }
  
  private void updateProgramParams() {
    if (this.mCenters.length % 2 != 1)
      return; 
    throw new RuntimeException("The size of center array must be even.");
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mProgram != null)
      updateProgramParams(); 
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void initProgram(FilterContext paramFilterContext, int paramInt) {
    if (paramInt == 3) {
      ShaderProgram shaderProgram = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float intensity;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_1, v_texcoord);\n  if (mask.a > 0.0) {\n    float green_blue = color.g + color.b;\n    float red_intensity = color.r / green_blue;\n    if (red_intensity > intensity) {\n      color.r = 0.5 * green_blue;\n    }\n  }\n  gl_FragColor = color;\n}\n");
      shaderProgram.setMaximumTileSize(this.mTileSize);
      this.mProgram = (Program)shaderProgram;
      shaderProgram.setHostValue("intensity", Float.valueOf(1.3F));
      this.mTarget = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Filter RedEye does not support frames of target ");
    stringBuilder.append(paramInt);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame2 = pullInput("image");
    FrameFormat frameFormat = frame2.getFormat();
    Frame frame3 = paramFilterContext.getFrameManager().newFrame(frameFormat);
    if (this.mProgram == null || frameFormat.getTarget() != this.mTarget)
      initProgram(paramFilterContext, frameFormat.getTarget()); 
    if (frameFormat.getWidth() != this.mWidth || frameFormat.getHeight() != this.mHeight) {
      this.mWidth = frameFormat.getWidth();
      this.mHeight = frameFormat.getHeight();
    } 
    createRedEyeFrame(paramFilterContext);
    Frame frame1 = this.mRedEyeFrame;
    this.mProgram.process(new Frame[] { frame2, frame1 }, frame3);
    pushOutput("image", frame3);
    frame3.release();
    this.mRedEyeFrame.release();
    this.mRedEyeFrame = null;
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/RedEyeFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */