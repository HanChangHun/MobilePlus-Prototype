package android.gamedriver;

import com.android.framework.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.List;

public final class Builder extends GeneratedMessageLite.Builder<GameDriverProto.Blacklists, GameDriverProto.Blacklists.Builder> implements GameDriverProto.BlacklistsOrBuilder {
  private Builder() {
    super(GameDriverProto.Blacklists.access$900());
  }
  
  public Builder addAllBlacklists(Iterable<? extends GameDriverProto.Blacklist> paramIterable) {
    copyOnWrite();
    GameDriverProto.Blacklists.access$1600((GameDriverProto.Blacklists)this.instance, paramIterable);
    return this;
  }
  
  public Builder addBlacklists(int paramInt, GameDriverProto.Blacklist.Builder paramBuilder) {
    copyOnWrite();
    GameDriverProto.Blacklists.access$1500((GameDriverProto.Blacklists)this.instance, paramInt, paramBuilder);
    return this;
  }
  
  public Builder addBlacklists(int paramInt, GameDriverProto.Blacklist paramBlacklist) {
    copyOnWrite();
    GameDriverProto.Blacklists.access$1300((GameDriverProto.Blacklists)this.instance, paramInt, paramBlacklist);
    return this;
  }
  
  public Builder addBlacklists(GameDriverProto.Blacklist.Builder paramBuilder) {
    copyOnWrite();
    GameDriverProto.Blacklists.access$1400((GameDriverProto.Blacklists)this.instance, paramBuilder);
    return this;
  }
  
  public Builder addBlacklists(GameDriverProto.Blacklist paramBlacklist) {
    copyOnWrite();
    GameDriverProto.Blacklists.access$1200((GameDriverProto.Blacklists)this.instance, paramBlacklist);
    return this;
  }
  
  public Builder clearBlacklists() {
    copyOnWrite();
    GameDriverProto.Blacklists.access$1700((GameDriverProto.Blacklists)this.instance);
    return this;
  }
  
  public GameDriverProto.Blacklist getBlacklists(int paramInt) {
    return ((GameDriverProto.Blacklists)this.instance).getBlacklists(paramInt);
  }
  
  public int getBlacklistsCount() {
    return ((GameDriverProto.Blacklists)this.instance).getBlacklistsCount();
  }
  
  public List<GameDriverProto.Blacklist> getBlacklistsList() {
    return Collections.unmodifiableList(((GameDriverProto.Blacklists)this.instance).getBlacklistsList());
  }
  
  public Builder removeBlacklists(int paramInt) {
    copyOnWrite();
    GameDriverProto.Blacklists.access$1800((GameDriverProto.Blacklists)this.instance, paramInt);
    return this;
  }
  
  public Builder setBlacklists(int paramInt, GameDriverProto.Blacklist.Builder paramBuilder) {
    copyOnWrite();
    GameDriverProto.Blacklists.access$1100((GameDriverProto.Blacklists)this.instance, paramInt, paramBuilder);
    return this;
  }
  
  public Builder setBlacklists(int paramInt, GameDriverProto.Blacklist paramBlacklist) {
    copyOnWrite();
    GameDriverProto.Blacklists.access$1000((GameDriverProto.Blacklists)this.instance, paramInt, paramBlacklist);
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gamedriver/GameDriverProto$Blacklists$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */