package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.format.ImageFormat;
import android.graphics.Bitmap;

public class BitmapSource extends Filter {
  @GenerateFieldPort(name = "bitmap")
  private Bitmap mBitmap;
  
  private Frame mImageFrame;
  
  @GenerateFieldPort(hasDefault = true, name = "recycleBitmap")
  private boolean mRecycleBitmap = true;
  
  @GenerateFieldPort(hasDefault = true, name = "repeatFrame")
  boolean mRepeatFrame = false;
  
  private int mTarget;
  
  @GenerateFieldPort(name = "target")
  String mTargetString;
  
  public BitmapSource(String paramString) {
    super(paramString);
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (paramString.equals("bitmap") || paramString.equals("target")) {
      Frame frame = this.mImageFrame;
      if (frame != null) {
        frame.release();
        this.mImageFrame = null;
      } 
    } 
  }
  
  public void loadImage(FilterContext paramFilterContext) {
    this.mTarget = FrameFormat.readTargetString(this.mTargetString);
    MutableFrameFormat mutableFrameFormat = ImageFormat.create(this.mBitmap.getWidth(), this.mBitmap.getHeight(), 3, this.mTarget);
    Frame frame = paramFilterContext.getFrameManager().newFrame((FrameFormat)mutableFrameFormat);
    this.mImageFrame = frame;
    frame.setBitmap(this.mBitmap);
    this.mImageFrame.setTimestamp(-1L);
    if (this.mRecycleBitmap)
      this.mBitmap.recycle(); 
    this.mBitmap = null;
  }
  
  public void process(FilterContext paramFilterContext) {
    if (this.mImageFrame == null)
      loadImage(paramFilterContext); 
    pushOutput("image", this.mImageFrame);
    if (!this.mRepeatFrame)
      closeOutputPort("image"); 
  }
  
  public void setupPorts() {
    addOutputPort("image", (FrameFormat)ImageFormat.create(3, 0));
  }
  
  public void tearDown(FilterContext paramFilterContext) {
    Frame frame = this.mImageFrame;
    if (frame != null) {
      frame.release();
      this.mImageFrame = null;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/BitmapSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */