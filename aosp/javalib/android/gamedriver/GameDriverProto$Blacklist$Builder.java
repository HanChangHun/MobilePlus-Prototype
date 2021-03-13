package android.gamedriver;

import com.android.framework.protobuf.ByteString;
import com.android.framework.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.List;

public final class Builder extends GeneratedMessageLite.Builder<GameDriverProto.Blacklist, GameDriverProto.Blacklist.Builder> implements GameDriverProto.BlacklistOrBuilder {
  private Builder() {
    super(GameDriverProto.Blacklist.access$000());
  }
  
  public Builder addAllPackageNames(Iterable<String> paramIterable) {
    copyOnWrite();
    GameDriverProto.Blacklist.access$500((GameDriverProto.Blacklist)this.instance, paramIterable);
    return this;
  }
  
  public Builder addPackageNames(String paramString) {
    copyOnWrite();
    GameDriverProto.Blacklist.access$400((GameDriverProto.Blacklist)this.instance, paramString);
    return this;
  }
  
  public Builder addPackageNamesBytes(ByteString paramByteString) {
    copyOnWrite();
    GameDriverProto.Blacklist.access$700((GameDriverProto.Blacklist)this.instance, paramByteString);
    return this;
  }
  
  public Builder clearPackageNames() {
    copyOnWrite();
    GameDriverProto.Blacklist.access$600((GameDriverProto.Blacklist)this.instance);
    return this;
  }
  
  public Builder clearVersionCode() {
    copyOnWrite();
    GameDriverProto.Blacklist.access$200((GameDriverProto.Blacklist)this.instance);
    return this;
  }
  
  public String getPackageNames(int paramInt) {
    return ((GameDriverProto.Blacklist)this.instance).getPackageNames(paramInt);
  }
  
  public ByteString getPackageNamesBytes(int paramInt) {
    return ((GameDriverProto.Blacklist)this.instance).getPackageNamesBytes(paramInt);
  }
  
  public int getPackageNamesCount() {
    return ((GameDriverProto.Blacklist)this.instance).getPackageNamesCount();
  }
  
  public List<String> getPackageNamesList() {
    return Collections.unmodifiableList(((GameDriverProto.Blacklist)this.instance).getPackageNamesList());
  }
  
  public long getVersionCode() {
    return ((GameDriverProto.Blacklist)this.instance).getVersionCode();
  }
  
  public boolean hasVersionCode() {
    return ((GameDriverProto.Blacklist)this.instance).hasVersionCode();
  }
  
  public Builder setPackageNames(int paramInt, String paramString) {
    copyOnWrite();
    GameDriverProto.Blacklist.access$300((GameDriverProto.Blacklist)this.instance, paramInt, paramString);
    return this;
  }
  
  public Builder setVersionCode(long paramLong) {
    copyOnWrite();
    GameDriverProto.Blacklist.access$100((GameDriverProto.Blacklist)this.instance, paramLong);
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gamedriver/GameDriverProto$Blacklist$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */