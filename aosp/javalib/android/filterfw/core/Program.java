package android.filterfw.core;

public abstract class Program {
  public abstract Object getHostValue(String paramString);
  
  public void process(Frame paramFrame1, Frame paramFrame2) {
    process(new Frame[] { paramFrame1 }, paramFrame2);
  }
  
  public abstract void process(Frame[] paramArrayOfFrame, Frame paramFrame);
  
  public void reset() {}
  
  public abstract void setHostValue(String paramString, Object paramObject);
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/Program.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */