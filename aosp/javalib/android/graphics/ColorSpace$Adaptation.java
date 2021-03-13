package android.graphics;

public enum Adaptation {
  BRADFORD(new float[] { 0.8951F, -0.7502F, 0.0389F, 0.2664F, 1.7135F, -0.0685F, -0.1614F, 0.0367F, 1.0296F }),
  CIECAT02(new float[] { 0.8951F, -0.7502F, 0.0389F, 0.2664F, 1.7135F, -0.0685F, -0.1614F, 0.0367F, 1.0296F }),
  VON_KRIES(new float[] { 0.40024F, -0.2263F, 0.0F, 0.7076F, 1.16532F, 0.0F, -0.08081F, 0.0457F, 0.91822F });
  
  final float[] mTransform;
  
  static {
    Adaptation adaptation = new Adaptation("CIECAT02", 2, new float[] { 0.7328F, -0.7036F, 0.003F, 0.4296F, 1.6975F, 0.0136F, -0.1624F, 0.0061F, 0.9834F });
    CIECAT02 = adaptation;
    $VALUES = new Adaptation[] { BRADFORD, VON_KRIES, adaptation };
  }
  
  Adaptation(float[] paramArrayOffloat) {
    this.mTransform = paramArrayOffloat;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Adaptation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */