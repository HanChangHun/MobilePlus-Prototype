package android.graphics.drawable;

public enum Orientation {
  BL_TR, BOTTOM_TOP, BR_TL, LEFT_RIGHT, RIGHT_LEFT, TL_BR, TOP_BOTTOM, TR_BL;
  
  static {
    RIGHT_LEFT = new Orientation("RIGHT_LEFT", 2);
    BR_TL = new Orientation("BR_TL", 3);
    BOTTOM_TOP = new Orientation("BOTTOM_TOP", 4);
    BL_TR = new Orientation("BL_TR", 5);
    LEFT_RIGHT = new Orientation("LEFT_RIGHT", 6);
    Orientation orientation = new Orientation("TL_BR", 7);
    TL_BR = orientation;
    $VALUES = new Orientation[] { TOP_BOTTOM, TR_BL, RIGHT_LEFT, BR_TL, BOTTOM_TOP, BL_TR, LEFT_RIGHT, orientation };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/GradientDrawable$Orientation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */