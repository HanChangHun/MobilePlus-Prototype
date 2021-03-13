package android.graphics;

import libcore.util.NativeAllocationRegistry;

class NoImagePreloadHolder {
  public static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Canvas.class.getClassLoader(), Canvas.access$000());
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Canvas$NoImagePreloadHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */