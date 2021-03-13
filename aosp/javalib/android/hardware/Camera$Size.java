package android.hardware;

@Deprecated
public class Size {
  public int height;
  
  public int width;
  
  public Size(int paramInt1, int paramInt2) {
    this.width = paramInt1;
    this.height = paramInt2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof Size;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (this.width == ((Size)paramObject).width) {
      bool = bool1;
      if (this.height == ((Size)paramObject).height)
        bool = true; 
    } 
    return bool;
  }
  
  public int hashCode() {
    return this.width * 32713 + this.height;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/Camera$Size.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */