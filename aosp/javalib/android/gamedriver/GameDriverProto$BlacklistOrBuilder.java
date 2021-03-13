package android.gamedriver;

import com.android.framework.protobuf.ByteString;
import com.android.framework.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface BlacklistOrBuilder extends MessageLiteOrBuilder {
  String getPackageNames(int paramInt);
  
  ByteString getPackageNamesBytes(int paramInt);
  
  int getPackageNamesCount();
  
  List<String> getPackageNamesList();
  
  long getVersionCode();
  
  boolean hasVersionCode();
}


/* Location:              /home/chun/Desktop/temp/!/android/gamedriver/GameDriverProto$BlacklistOrBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */