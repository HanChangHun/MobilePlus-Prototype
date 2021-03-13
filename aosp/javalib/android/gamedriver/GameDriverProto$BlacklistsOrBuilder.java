package android.gamedriver;

import com.android.framework.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface BlacklistsOrBuilder extends MessageLiteOrBuilder {
  GameDriverProto.Blacklist getBlacklists(int paramInt);
  
  int getBlacklistsCount();
  
  List<GameDriverProto.Blacklist> getBlacklistsList();
}


/* Location:              /home/chun/Desktop/temp/!/android/gamedriver/GameDriverProto$BlacklistsOrBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */