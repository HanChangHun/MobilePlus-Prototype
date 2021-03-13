package android.graphics;

import com.android.internal.util.ArrayUtils;

final class CompositePositionUpdateListener implements RenderNode.PositionUpdateListener {
  private static final RenderNode.PositionUpdateListener[] sEmpty = new RenderNode.PositionUpdateListener[0];
  
  private final RenderNode.PositionUpdateListener[] mListeners;
  
  CompositePositionUpdateListener(RenderNode.PositionUpdateListener... paramVarArgs) {
    if (paramVarArgs == null)
      paramVarArgs = sEmpty; 
    this.mListeners = paramVarArgs;
  }
  
  public void positionChanged(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    RenderNode.PositionUpdateListener[] arrayOfPositionUpdateListener = this.mListeners;
    int i = arrayOfPositionUpdateListener.length;
    for (byte b = 0; b < i; b++)
      arrayOfPositionUpdateListener[b].positionChanged(paramLong, paramInt1, paramInt2, paramInt3, paramInt4); 
  }
  
  public void positionLost(long paramLong) {
    RenderNode.PositionUpdateListener[] arrayOfPositionUpdateListener = this.mListeners;
    int i = arrayOfPositionUpdateListener.length;
    for (byte b = 0; b < i; b++)
      arrayOfPositionUpdateListener[b].positionLost(paramLong); 
  }
  
  public CompositePositionUpdateListener with(RenderNode.PositionUpdateListener paramPositionUpdateListener) {
    return new CompositePositionUpdateListener((RenderNode.PositionUpdateListener[])ArrayUtils.appendElement(RenderNode.PositionUpdateListener.class, (Object[])this.mListeners, paramPositionUpdateListener));
  }
  
  public CompositePositionUpdateListener without(RenderNode.PositionUpdateListener paramPositionUpdateListener) {
    return new CompositePositionUpdateListener((RenderNode.PositionUpdateListener[])ArrayUtils.removeElement(RenderNode.PositionUpdateListener.class, (Object[])this.mListeners, paramPositionUpdateListener));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/RenderNode$CompositePositionUpdateListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */