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
import android.filterfw.format.ObjectFormat;
import android.filterfw.geometry.Quad;

public class CropFilter extends Filter {
  @GenerateFieldPort(name = "fillblack")
  private boolean mFillBlack = false;
  
  private final String mFragShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  const vec2 lo = vec2(0.0, 0.0);\n  const vec2 hi = vec2(1.0, 1.0);\n  const vec4 black = vec4(0.0, 0.0, 0.0, 1.0);\n  bool out_of_bounds =\n    any(lessThan(v_texcoord, lo)) ||\n    any(greaterThan(v_texcoord, hi));\n  if (out_of_bounds) {\n    gl_FragColor = black;\n  } else {\n    gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n  }\n}\n";
  
  private FrameFormat mLastFormat = null;
  
  @GenerateFieldPort(name = "oheight")
  private int mOutputHeight = -1;
  
  @GenerateFieldPort(name = "owidth")
  private int mOutputWidth = -1;
  
  private Program mProgram;
  
  public CropFilter(String paramString) {
    super(paramString);
  }
  
  protected void createProgram(FilterContext paramFilterContext, FrameFormat paramFrameFormat) {
    FrameFormat frameFormat = this.mLastFormat;
    if (frameFormat != null && frameFormat.getTarget() == paramFrameFormat.getTarget())
      return; 
    this.mLastFormat = paramFrameFormat;
    this.mProgram = null;
    if (paramFrameFormat.getTarget() == 3)
      if (this.mFillBlack) {
        this.mProgram = (Program)new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  const vec2 lo = vec2(0.0, 0.0);\n  const vec2 hi = vec2(1.0, 1.0);\n  const vec4 black = vec4(0.0, 0.0, 0.0, 1.0);\n  bool out_of_bounds =\n    any(lessThan(v_texcoord, lo)) ||\n    any(greaterThan(v_texcoord, hi));\n  if (out_of_bounds) {\n    gl_FragColor = black;\n  } else {\n    gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n  }\n}\n");
      } else {
        this.mProgram = (Program)ShaderProgram.createIdentity(paramFilterContext);
      }  
    if (this.mProgram != null)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not create a program for crop filter ");
    stringBuilder.append(this);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    MutableFrameFormat mutableFrameFormat = paramFrameFormat.mutableCopy();
    mutableFrameFormat.setDimensions(0, 0);
    return (FrameFormat)mutableFrameFormat;
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame2 = pullInput("image");
    Frame frame3 = pullInput("box");
    createProgram(paramFilterContext, frame2.getFormat());
    Quad quad = (Quad)frame3.getObjectValue();
    MutableFrameFormat mutableFrameFormat = frame2.getFormat().mutableCopy();
    int i = this.mOutputWidth;
    int j = i;
    if (i == -1)
      j = mutableFrameFormat.getWidth(); 
    int k = this.mOutputHeight;
    i = k;
    if (k == -1)
      i = mutableFrameFormat.getHeight(); 
    mutableFrameFormat.setDimensions(j, i);
    Frame frame1 = paramFilterContext.getFrameManager().newFrame((FrameFormat)mutableFrameFormat);
    Program program = this.mProgram;
    if (program instanceof ShaderProgram)
      ((ShaderProgram)program).setSourceRegion(quad); 
    this.mProgram.process(frame2, frame1);
    pushOutput("image", frame1);
    frame1.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addMaskedInputPort("box", (FrameFormat)ObjectFormat.fromClass(Quad.class, 1));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/CropFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */