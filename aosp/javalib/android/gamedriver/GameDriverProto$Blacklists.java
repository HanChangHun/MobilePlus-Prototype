package android.gamedriver;

import com.android.framework.protobuf.AbstractMessageLite;
import com.android.framework.protobuf.ByteString;
import com.android.framework.protobuf.CodedInputStream;
import com.android.framework.protobuf.ExtensionRegistryLite;
import com.android.framework.protobuf.GeneratedMessageLite;
import com.android.framework.protobuf.Internal;
import com.android.framework.protobuf.InvalidProtocolBufferException;
import com.android.framework.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class Blacklists extends GeneratedMessageLite<GameDriverProto.Blacklists, GameDriverProto.Blacklists.Builder> implements GameDriverProto.BlacklistsOrBuilder {
  public static final int BLACKLISTS_FIELD_NUMBER = 1;
  
  private static final Blacklists DEFAULT_INSTANCE;
  
  private static volatile Parser<Blacklists> PARSER;
  
  private Internal.ProtobufList<GameDriverProto.Blacklist> blacklists_ = emptyProtobufList();
  
  static {
    Blacklists blacklists = new Blacklists();
    DEFAULT_INSTANCE = blacklists;
    GeneratedMessageLite.registerDefaultInstance(Blacklists.class, blacklists);
  }
  
  private void addAllBlacklists(Iterable<? extends GameDriverProto.Blacklist> paramIterable) {
    ensureBlacklistsIsMutable();
    AbstractMessageLite.addAll(paramIterable, (List)this.blacklists_);
  }
  
  private void addBlacklists(int paramInt, GameDriverProto.Blacklist.Builder paramBuilder) {
    ensureBlacklistsIsMutable();
    this.blacklists_.add(paramInt, paramBuilder.build());
  }
  
  private void addBlacklists(int paramInt, GameDriverProto.Blacklist paramBlacklist) {
    if (paramBlacklist != null) {
      ensureBlacklistsIsMutable();
      this.blacklists_.add(paramInt, paramBlacklist);
      return;
    } 
    throw null;
  }
  
  private void addBlacklists(GameDriverProto.Blacklist.Builder paramBuilder) {
    ensureBlacklistsIsMutable();
    this.blacklists_.add(paramBuilder.build());
  }
  
  private void addBlacklists(GameDriverProto.Blacklist paramBlacklist) {
    if (paramBlacklist != null) {
      ensureBlacklistsIsMutable();
      this.blacklists_.add(paramBlacklist);
      return;
    } 
    throw null;
  }
  
  private void clearBlacklists() {
    this.blacklists_ = emptyProtobufList();
  }
  
  private void ensureBlacklistsIsMutable() {
    if (!this.blacklists_.isModifiable())
      this.blacklists_ = GeneratedMessageLite.mutableCopy(this.blacklists_); 
  }
  
  public static Blacklists getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  public static Builder newBuilder() {
    return (Builder)DEFAULT_INSTANCE.createBuilder();
  }
  
  public static Builder newBuilder(Blacklists paramBlacklists) {
    return (Builder)DEFAULT_INSTANCE.createBuilder(paramBlacklists);
  }
  
  public static Blacklists parseDelimitedFrom(InputStream paramInputStream) throws IOException {
    return (Blacklists)parseDelimitedFrom(DEFAULT_INSTANCE, paramInputStream);
  }
  
  public static Blacklists parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return (Blacklists)parseDelimitedFrom(DEFAULT_INSTANCE, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Blacklists parseFrom(ByteString paramByteString) throws InvalidProtocolBufferException {
    return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramByteString);
  }
  
  public static Blacklists parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramByteString, paramExtensionRegistryLite);
  }
  
  public static Blacklists parseFrom(CodedInputStream paramCodedInputStream) throws IOException {
    return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramCodedInputStream);
  }
  
  public static Blacklists parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramCodedInputStream, paramExtensionRegistryLite);
  }
  
  public static Blacklists parseFrom(InputStream paramInputStream) throws IOException {
    return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramInputStream);
  }
  
  public static Blacklists parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Blacklists parseFrom(ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramByteBuffer);
  }
  
  public static Blacklists parseFrom(ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramByteBuffer, paramExtensionRegistryLite);
  }
  
  public static Blacklists parseFrom(byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramArrayOfbyte);
  }
  
  public static Blacklists parseFrom(byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramArrayOfbyte, paramExtensionRegistryLite);
  }
  
  public static Parser<Blacklists> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
  
  private void removeBlacklists(int paramInt) {
    ensureBlacklistsIsMutable();
    this.blacklists_.remove(paramInt);
  }
  
  private void setBlacklists(int paramInt, GameDriverProto.Blacklist.Builder paramBuilder) {
    ensureBlacklistsIsMutable();
    this.blacklists_.set(paramInt, paramBuilder.build());
  }
  
  private void setBlacklists(int paramInt, GameDriverProto.Blacklist paramBlacklist) {
    if (paramBlacklist != null) {
      ensureBlacklistsIsMutable();
      this.blacklists_.set(paramInt, paramBlacklist);
      return;
    } 
    throw null;
  }
  
  protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke paramMethodToInvoke, Object<Blacklists> paramObject1, Object paramObject2) {
    // Byte code:
    //   0: getstatic android/gamedriver/GameDriverProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke : [I
    //   3: aload_1
    //   4: invokevirtual ordinal : ()I
    //   7: iaload
    //   8: tableswitch default -> 52, 1 -> 155, 2 -> 146, 3 -> 123, 4 -> 119, 5 -> 67, 6 -> 62, 7 -> 60
    //   52: new java/lang/UnsupportedOperationException
    //   55: dup
    //   56: invokespecial <init> : ()V
    //   59: athrow
    //   60: aconst_null
    //   61: areturn
    //   62: iconst_1
    //   63: invokestatic valueOf : (B)Ljava/lang/Byte;
    //   66: areturn
    //   67: getstatic android/gamedriver/GameDriverProto$Blacklists.PARSER : Lcom/android/framework/protobuf/Parser;
    //   70: astore_2
    //   71: aload_2
    //   72: astore_1
    //   73: aload_2
    //   74: ifnonnull -> 117
    //   77: ldc android/gamedriver/GameDriverProto$Blacklists
    //   79: monitorenter
    //   80: getstatic android/gamedriver/GameDriverProto$Blacklists.PARSER : Lcom/android/framework/protobuf/Parser;
    //   83: astore_2
    //   84: aload_2
    //   85: astore_1
    //   86: aload_2
    //   87: ifnonnull -> 105
    //   90: new com/android/framework/protobuf/GeneratedMessageLite$DefaultInstanceBasedParser
    //   93: astore_1
    //   94: aload_1
    //   95: getstatic android/gamedriver/GameDriverProto$Blacklists.DEFAULT_INSTANCE : Landroid/gamedriver/GameDriverProto$Blacklists;
    //   98: invokespecial <init> : (Lcom/android/framework/protobuf/GeneratedMessageLite;)V
    //   101: aload_1
    //   102: putstatic android/gamedriver/GameDriverProto$Blacklists.PARSER : Lcom/android/framework/protobuf/Parser;
    //   105: ldc android/gamedriver/GameDriverProto$Blacklists
    //   107: monitorexit
    //   108: goto -> 117
    //   111: astore_1
    //   112: ldc android/gamedriver/GameDriverProto$Blacklists
    //   114: monitorexit
    //   115: aload_1
    //   116: athrow
    //   117: aload_1
    //   118: areturn
    //   119: getstatic android/gamedriver/GameDriverProto$Blacklists.DEFAULT_INSTANCE : Landroid/gamedriver/GameDriverProto$Blacklists;
    //   122: areturn
    //   123: getstatic android/gamedriver/GameDriverProto$Blacklists.DEFAULT_INSTANCE : Landroid/gamedriver/GameDriverProto$Blacklists;
    //   126: ldc '    '
    //   128: iconst_2
    //   129: anewarray java/lang/Object
    //   132: dup
    //   133: iconst_0
    //   134: ldc 'blacklists_'
    //   136: aastore
    //   137: dup
    //   138: iconst_1
    //   139: ldc android/gamedriver/GameDriverProto$Blacklist
    //   141: aastore
    //   142: invokestatic newMessageInfo : (Lcom/android/framework/protobuf/MessageLite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
    //   145: areturn
    //   146: new android/gamedriver/GameDriverProto$Blacklists$Builder
    //   149: dup
    //   150: aconst_null
    //   151: invokespecial <init> : (Landroid/gamedriver/GameDriverProto$1;)V
    //   154: areturn
    //   155: new android/gamedriver/GameDriverProto$Blacklists
    //   158: dup
    //   159: invokespecial <init> : ()V
    //   162: areturn
    // Exception table:
    //   from	to	target	type
    //   80	84	111	finally
    //   90	101	111	finally
    //   101	105	111	finally
    //   105	108	111	finally
    //   112	115	111	finally
  }
  
  public GameDriverProto.Blacklist getBlacklists(int paramInt) {
    return (GameDriverProto.Blacklist)this.blacklists_.get(paramInt);
  }
  
  public int getBlacklistsCount() {
    return this.blacklists_.size();
  }
  
  public List<GameDriverProto.Blacklist> getBlacklistsList() {
    return (List<GameDriverProto.Blacklist>)this.blacklists_;
  }
  
  public GameDriverProto.BlacklistOrBuilder getBlacklistsOrBuilder(int paramInt) {
    return (GameDriverProto.BlacklistOrBuilder)this.blacklists_.get(paramInt);
  }
  
  public List<? extends GameDriverProto.BlacklistOrBuilder> getBlacklistsOrBuilderList() {
    return (List)this.blacklists_;
  }
  
  public static final class Builder extends GeneratedMessageLite.Builder<Blacklists, Builder> implements GameDriverProto.BlacklistsOrBuilder {
    private Builder() {
      super(GameDriverProto.Blacklists.DEFAULT_INSTANCE);
    }
    
    public Builder addAllBlacklists(Iterable<? extends GameDriverProto.Blacklist> param2Iterable) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).addAllBlacklists(param2Iterable);
      return this;
    }
    
    public Builder addBlacklists(int param2Int, GameDriverProto.Blacklist.Builder param2Builder) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).addBlacklists(param2Int, param2Builder);
      return this;
    }
    
    public Builder addBlacklists(int param2Int, GameDriverProto.Blacklist param2Blacklist) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).addBlacklists(param2Int, param2Blacklist);
      return this;
    }
    
    public Builder addBlacklists(GameDriverProto.Blacklist.Builder param2Builder) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).addBlacklists(param2Builder);
      return this;
    }
    
    public Builder addBlacklists(GameDriverProto.Blacklist param2Blacklist) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).addBlacklists(param2Blacklist);
      return this;
    }
    
    public Builder clearBlacklists() {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).clearBlacklists();
      return this;
    }
    
    public GameDriverProto.Blacklist getBlacklists(int param2Int) {
      return ((GameDriverProto.Blacklists)this.instance).getBlacklists(param2Int);
    }
    
    public int getBlacklistsCount() {
      return ((GameDriverProto.Blacklists)this.instance).getBlacklistsCount();
    }
    
    public List<GameDriverProto.Blacklist> getBlacklistsList() {
      return Collections.unmodifiableList(((GameDriverProto.Blacklists)this.instance).getBlacklistsList());
    }
    
    public Builder removeBlacklists(int param2Int) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).removeBlacklists(param2Int);
      return this;
    }
    
    public Builder setBlacklists(int param2Int, GameDriverProto.Blacklist.Builder param2Builder) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).setBlacklists(param2Int, param2Builder);
      return this;
    }
    
    public Builder setBlacklists(int param2Int, GameDriverProto.Blacklist param2Blacklist) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).setBlacklists(param2Int, param2Blacklist);
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gamedriver/GameDriverProto$Blacklists.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */