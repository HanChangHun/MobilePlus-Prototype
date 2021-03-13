package android.hardware.display;

public enum ColorComponent {
  CHANNEL0, CHANNEL1, CHANNEL2, CHANNEL3;
  
  static {
    ColorComponent colorComponent = new ColorComponent("CHANNEL3", 3);
    CHANNEL3 = colorComponent;
    $VALUES = new ColorComponent[] { CHANNEL0, CHANNEL1, CHANNEL2, colorComponent };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayedContentSample$ColorComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */