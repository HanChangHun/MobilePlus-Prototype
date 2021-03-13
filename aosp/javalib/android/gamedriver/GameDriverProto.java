package android.gamedriver;

import com.android.framework.protobuf.AbstractMessageLite;
import com.android.framework.protobuf.ByteString;
import com.android.framework.protobuf.CodedInputStream;
import com.android.framework.protobuf.ExtensionRegistryLite;
import com.android.framework.protobuf.GeneratedMessageLite;
import com.android.framework.protobuf.Internal;
import com.android.framework.protobuf.InvalidProtocolBufferException;
import com.android.framework.protobuf.MessageLiteOrBuilder;
import com.android.framework.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class GameDriverProto {
  static {
  
  }
  
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite) {}
  
  public static final class Blacklist extends GeneratedMessageLite<Blacklist, Blacklist.Builder> implements BlacklistOrBuilder {
    private static final Blacklist DEFAULT_INSTANCE;
    
    public static final int PACKAGE_NAMES_FIELD_NUMBER = 2;
    
    private static volatile Parser<Blacklist> PARSER;
    
    public static final int VERSION_CODE_FIELD_NUMBER = 1;
    
    private int bitField0_;
    
    private Internal.ProtobufList<String> packageNames_ = GeneratedMessageLite.emptyProtobufList();
    
    private long versionCode_;
    
    static {
      Blacklist blacklist = new Blacklist();
      DEFAULT_INSTANCE = blacklist;
      GeneratedMessageLite.registerDefaultInstance(Blacklist.class, blacklist);
    }
    
    private void addAllPackageNames(Iterable<String> param1Iterable) {
      ensurePackageNamesIsMutable();
      AbstractMessageLite.addAll(param1Iterable, (List)this.packageNames_);
    }
    
    private void addPackageNames(String param1String) {
      if (param1String != null) {
        ensurePackageNamesIsMutable();
        this.packageNames_.add(param1String);
        return;
      } 
      throw null;
    }
    
    private void addPackageNamesBytes(ByteString param1ByteString) {
      if (param1ByteString != null) {
        ensurePackageNamesIsMutable();
        this.packageNames_.add(param1ByteString.toStringUtf8());
        return;
      } 
      throw null;
    }
    
    private void clearPackageNames() {
      this.packageNames_ = GeneratedMessageLite.emptyProtobufList();
    }
    
    private void clearVersionCode() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.versionCode_ = 0L;
    }
    
    private void ensurePackageNamesIsMutable() {
      if (!this.packageNames_.isModifiable())
        this.packageNames_ = GeneratedMessageLite.mutableCopy(this.packageNames_); 
    }
    
    public static Blacklist getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    public static Builder newBuilder() {
      return (Builder)DEFAULT_INSTANCE.createBuilder();
    }
    
    public static Builder newBuilder(Blacklist param1Blacklist) {
      return (Builder)DEFAULT_INSTANCE.createBuilder(param1Blacklist);
    }
    
    public static Blacklist parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (Blacklist)parseDelimitedFrom(DEFAULT_INSTANCE, param1InputStream);
    }
    
    public static Blacklist parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Blacklist)parseDelimitedFrom(DEFAULT_INSTANCE, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static Blacklist parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ByteString);
    }
    
    public static Blacklist parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static Blacklist parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1CodedInputStream);
    }
    
    public static Blacklist parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static Blacklist parseFrom(InputStream param1InputStream) throws IOException {
      return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1InputStream);
    }
    
    public static Blacklist parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static Blacklist parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ByteBuffer);
    }
    
    public static Blacklist parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static Blacklist parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ArrayOfbyte);
    }
    
    public static Blacklist parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<Blacklist> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
    
    private void setPackageNames(int param1Int, String param1String) {
      if (param1String != null) {
        ensurePackageNamesIsMutable();
        this.packageNames_.set(param1Int, param1String);
        return;
      } 
      throw null;
    }
    
    private void setVersionCode(long param1Long) {
      this.bitField0_ |= 0x1;
      this.versionCode_ = param1Long;
    }
    
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke param1MethodToInvoke, Object<Blacklist> param1Object1, Object param1Object2) {
      // Byte code:
      //   0: getstatic android/gamedriver/GameDriverProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke : [I
      //   3: aload_1
      //   4: invokevirtual ordinal : ()I
      //   7: iaload
      //   8: tableswitch default -> 52, 1 -> 160, 2 -> 151, 3 -> 123, 4 -> 119, 5 -> 67, 6 -> 62, 7 -> 60
      //   52: new java/lang/UnsupportedOperationException
      //   55: dup
      //   56: invokespecial <init> : ()V
      //   59: athrow
      //   60: aconst_null
      //   61: areturn
      //   62: iconst_1
      //   63: invokestatic valueOf : (B)Ljava/lang/Byte;
      //   66: areturn
      //   67: getstatic android/gamedriver/GameDriverProto$Blacklist.PARSER : Lcom/android/framework/protobuf/Parser;
      //   70: astore_2
      //   71: aload_2
      //   72: astore_1
      //   73: aload_2
      //   74: ifnonnull -> 117
      //   77: ldc android/gamedriver/GameDriverProto$Blacklist
      //   79: monitorenter
      //   80: getstatic android/gamedriver/GameDriverProto$Blacklist.PARSER : Lcom/android/framework/protobuf/Parser;
      //   83: astore_2
      //   84: aload_2
      //   85: astore_1
      //   86: aload_2
      //   87: ifnonnull -> 105
      //   90: new com/android/framework/protobuf/GeneratedMessageLite$DefaultInstanceBasedParser
      //   93: astore_1
      //   94: aload_1
      //   95: getstatic android/gamedriver/GameDriverProto$Blacklist.DEFAULT_INSTANCE : Landroid/gamedriver/GameDriverProto$Blacklist;
      //   98: invokespecial <init> : (Lcom/android/framework/protobuf/GeneratedMessageLite;)V
      //   101: aload_1
      //   102: putstatic android/gamedriver/GameDriverProto$Blacklist.PARSER : Lcom/android/framework/protobuf/Parser;
      //   105: ldc android/gamedriver/GameDriverProto$Blacklist
      //   107: monitorexit
      //   108: goto -> 117
      //   111: astore_1
      //   112: ldc android/gamedriver/GameDriverProto$Blacklist
      //   114: monitorexit
      //   115: aload_1
      //   116: athrow
      //   117: aload_1
      //   118: areturn
      //   119: getstatic android/gamedriver/GameDriverProto$Blacklist.DEFAULT_INSTANCE : Landroid/gamedriver/GameDriverProto$Blacklist;
      //   122: areturn
      //   123: getstatic android/gamedriver/GameDriverProto$Blacklist.DEFAULT_INSTANCE : Landroid/gamedriver/GameDriverProto$Blacklist;
      //   126: ldc '    '
      //   128: iconst_3
      //   129: anewarray java/lang/Object
      //   132: dup
      //   133: iconst_0
      //   134: ldc 'bitField0_'
      //   136: aastore
      //   137: dup
      //   138: iconst_1
      //   139: ldc 'versionCode_'
      //   141: aastore
      //   142: dup
      //   143: iconst_2
      //   144: ldc 'packageNames_'
      //   146: aastore
      //   147: invokestatic newMessageInfo : (Lcom/android/framework/protobuf/MessageLite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
      //   150: areturn
      //   151: new android/gamedriver/GameDriverProto$Blacklist$Builder
      //   154: dup
      //   155: aconst_null
      //   156: invokespecial <init> : (Landroid/gamedriver/GameDriverProto$1;)V
      //   159: areturn
      //   160: new android/gamedriver/GameDriverProto$Blacklist
      //   163: dup
      //   164: invokespecial <init> : ()V
      //   167: areturn
      // Exception table:
      //   from	to	target	type
      //   80	84	111	finally
      //   90	101	111	finally
      //   101	105	111	finally
      //   105	108	111	finally
      //   112	115	111	finally
    }
    
    public String getPackageNames(int param1Int) {
      return (String)this.packageNames_.get(param1Int);
    }
    
    public ByteString getPackageNamesBytes(int param1Int) {
      return ByteString.copyFromUtf8((String)this.packageNames_.get(param1Int));
    }
    
    public int getPackageNamesCount() {
      return this.packageNames_.size();
    }
    
    public List<String> getPackageNamesList() {
      return (List<String>)this.packageNames_;
    }
    
    public long getVersionCode() {
      return this.versionCode_;
    }
    
    public boolean hasVersionCode() {
      int i = this.bitField0_;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public static final class Builder extends GeneratedMessageLite.Builder<Blacklist, Builder> implements GameDriverProto.BlacklistOrBuilder {
      private Builder() {
        super(GameDriverProto.Blacklist.DEFAULT_INSTANCE);
      }
      
      public Builder addAllPackageNames(Iterable<String> param2Iterable) {
        copyOnWrite();
        ((GameDriverProto.Blacklist)this.instance).addAllPackageNames(param2Iterable);
        return this;
      }
      
      public Builder addPackageNames(String param2String) {
        copyOnWrite();
        ((GameDriverProto.Blacklist)this.instance).addPackageNames(param2String);
        return this;
      }
      
      public Builder addPackageNamesBytes(ByteString param2ByteString) {
        copyOnWrite();
        ((GameDriverProto.Blacklist)this.instance).addPackageNamesBytes(param2ByteString);
        return this;
      }
      
      public Builder clearPackageNames() {
        copyOnWrite();
        ((GameDriverProto.Blacklist)this.instance).clearPackageNames();
        return this;
      }
      
      public Builder clearVersionCode() {
        copyOnWrite();
        ((GameDriverProto.Blacklist)this.instance).clearVersionCode();
        return this;
      }
      
      public String getPackageNames(int param2Int) {
        return ((GameDriverProto.Blacklist)this.instance).getPackageNames(param2Int);
      }
      
      public ByteString getPackageNamesBytes(int param2Int) {
        return ((GameDriverProto.Blacklist)this.instance).getPackageNamesBytes(param2Int);
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
      
      public Builder setPackageNames(int param2Int, String param2String) {
        copyOnWrite();
        ((GameDriverProto.Blacklist)this.instance).setPackageNames(param2Int, param2String);
        return this;
      }
      
      public Builder setVersionCode(long param2Long) {
        copyOnWrite();
        ((GameDriverProto.Blacklist)this.instance).setVersionCode(param2Long);
        return this;
      }
    }
  }
  
  public static final class Builder extends GeneratedMessageLite.Builder<Blacklist, Blacklist.Builder> implements BlacklistOrBuilder {
    private Builder() {
      super(GameDriverProto.Blacklist.DEFAULT_INSTANCE);
    }
    
    public Builder addAllPackageNames(Iterable<String> param1Iterable) {
      copyOnWrite();
      ((GameDriverProto.Blacklist)this.instance).addAllPackageNames(param1Iterable);
      return this;
    }
    
    public Builder addPackageNames(String param1String) {
      copyOnWrite();
      ((GameDriverProto.Blacklist)this.instance).addPackageNames(param1String);
      return this;
    }
    
    public Builder addPackageNamesBytes(ByteString param1ByteString) {
      copyOnWrite();
      ((GameDriverProto.Blacklist)this.instance).addPackageNamesBytes(param1ByteString);
      return this;
    }
    
    public Builder clearPackageNames() {
      copyOnWrite();
      ((GameDriverProto.Blacklist)this.instance).clearPackageNames();
      return this;
    }
    
    public Builder clearVersionCode() {
      copyOnWrite();
      ((GameDriverProto.Blacklist)this.instance).clearVersionCode();
      return this;
    }
    
    public String getPackageNames(int param1Int) {
      return ((GameDriverProto.Blacklist)this.instance).getPackageNames(param1Int);
    }
    
    public ByteString getPackageNamesBytes(int param1Int) {
      return ((GameDriverProto.Blacklist)this.instance).getPackageNamesBytes(param1Int);
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
    
    public Builder setPackageNames(int param1Int, String param1String) {
      copyOnWrite();
      ((GameDriverProto.Blacklist)this.instance).setPackageNames(param1Int, param1String);
      return this;
    }
    
    public Builder setVersionCode(long param1Long) {
      copyOnWrite();
      ((GameDriverProto.Blacklist)this.instance).setVersionCode(param1Long);
      return this;
    }
  }
  
  public static interface BlacklistOrBuilder extends MessageLiteOrBuilder {
    String getPackageNames(int param1Int);
    
    ByteString getPackageNamesBytes(int param1Int);
    
    int getPackageNamesCount();
    
    List<String> getPackageNamesList();
    
    long getVersionCode();
    
    boolean hasVersionCode();
  }
  
  public static final class Blacklists extends GeneratedMessageLite<Blacklists, Blacklists.Builder> implements BlacklistsOrBuilder {
    public static final int BLACKLISTS_FIELD_NUMBER = 1;
    
    private static final Blacklists DEFAULT_INSTANCE;
    
    private static volatile Parser<Blacklists> PARSER;
    
    private Internal.ProtobufList<GameDriverProto.Blacklist> blacklists_ = emptyProtobufList();
    
    static {
      Blacklists blacklists = new Blacklists();
      DEFAULT_INSTANCE = blacklists;
      GeneratedMessageLite.registerDefaultInstance(Blacklists.class, blacklists);
    }
    
    private void addAllBlacklists(Iterable<? extends GameDriverProto.Blacklist> param1Iterable) {
      ensureBlacklistsIsMutable();
      AbstractMessageLite.addAll(param1Iterable, (List)this.blacklists_);
    }
    
    private void addBlacklists(int param1Int, GameDriverProto.Blacklist.Builder param1Builder) {
      ensureBlacklistsIsMutable();
      this.blacklists_.add(param1Int, param1Builder.build());
    }
    
    private void addBlacklists(int param1Int, GameDriverProto.Blacklist param1Blacklist) {
      if (param1Blacklist != null) {
        ensureBlacklistsIsMutable();
        this.blacklists_.add(param1Int, param1Blacklist);
        return;
      } 
      throw null;
    }
    
    private void addBlacklists(GameDriverProto.Blacklist.Builder param1Builder) {
      ensureBlacklistsIsMutable();
      this.blacklists_.add(param1Builder.build());
    }
    
    private void addBlacklists(GameDriverProto.Blacklist param1Blacklist) {
      if (param1Blacklist != null) {
        ensureBlacklistsIsMutable();
        this.blacklists_.add(param1Blacklist);
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
    
    public static Builder newBuilder(Blacklists param1Blacklists) {
      return (Builder)DEFAULT_INSTANCE.createBuilder(param1Blacklists);
    }
    
    public static Blacklists parseDelimitedFrom(InputStream param1InputStream) throws IOException {
      return (Blacklists)parseDelimitedFrom(DEFAULT_INSTANCE, param1InputStream);
    }
    
    public static Blacklists parseDelimitedFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Blacklists)parseDelimitedFrom(DEFAULT_INSTANCE, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static Blacklists parseFrom(ByteString param1ByteString) throws InvalidProtocolBufferException {
      return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ByteString);
    }
    
    public static Blacklists parseFrom(ByteString param1ByteString, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ByteString, param1ExtensionRegistryLite);
    }
    
    public static Blacklists parseFrom(CodedInputStream param1CodedInputStream) throws IOException {
      return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1CodedInputStream);
    }
    
    public static Blacklists parseFrom(CodedInputStream param1CodedInputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1CodedInputStream, param1ExtensionRegistryLite);
    }
    
    public static Blacklists parseFrom(InputStream param1InputStream) throws IOException {
      return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1InputStream);
    }
    
    public static Blacklists parseFrom(InputStream param1InputStream, ExtensionRegistryLite param1ExtensionRegistryLite) throws IOException {
      return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1InputStream, param1ExtensionRegistryLite);
    }
    
    public static Blacklists parseFrom(ByteBuffer param1ByteBuffer) throws InvalidProtocolBufferException {
      return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ByteBuffer);
    }
    
    public static Blacklists parseFrom(ByteBuffer param1ByteBuffer, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ByteBuffer, param1ExtensionRegistryLite);
    }
    
    public static Blacklists parseFrom(byte[] param1ArrayOfbyte) throws InvalidProtocolBufferException {
      return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ArrayOfbyte);
    }
    
    public static Blacklists parseFrom(byte[] param1ArrayOfbyte, ExtensionRegistryLite param1ExtensionRegistryLite) throws InvalidProtocolBufferException {
      return (Blacklists)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, param1ArrayOfbyte, param1ExtensionRegistryLite);
    }
    
    public static Parser<Blacklists> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
    
    private void removeBlacklists(int param1Int) {
      ensureBlacklistsIsMutable();
      this.blacklists_.remove(param1Int);
    }
    
    private void setBlacklists(int param1Int, GameDriverProto.Blacklist.Builder param1Builder) {
      ensureBlacklistsIsMutable();
      this.blacklists_.set(param1Int, param1Builder.build());
    }
    
    private void setBlacklists(int param1Int, GameDriverProto.Blacklist param1Blacklist) {
      if (param1Blacklist != null) {
        ensureBlacklistsIsMutable();
        this.blacklists_.set(param1Int, param1Blacklist);
        return;
      } 
      throw null;
    }
    
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke param1MethodToInvoke, Object<Blacklists> param1Object1, Object param1Object2) {
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
    
    public GameDriverProto.Blacklist getBlacklists(int param1Int) {
      return (GameDriverProto.Blacklist)this.blacklists_.get(param1Int);
    }
    
    public int getBlacklistsCount() {
      return this.blacklists_.size();
    }
    
    public List<GameDriverProto.Blacklist> getBlacklistsList() {
      return (List<GameDriverProto.Blacklist>)this.blacklists_;
    }
    
    public GameDriverProto.BlacklistOrBuilder getBlacklistsOrBuilder(int param1Int) {
      return (GameDriverProto.BlacklistOrBuilder)this.blacklists_.get(param1Int);
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
  
  public static final class Builder extends GeneratedMessageLite.Builder<Blacklists, Blacklists.Builder> implements BlacklistsOrBuilder {
    private Builder() {
      super(GameDriverProto.Blacklists.DEFAULT_INSTANCE);
    }
    
    public Builder addAllBlacklists(Iterable<? extends GameDriverProto.Blacklist> param1Iterable) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).addAllBlacklists(param1Iterable);
      return this;
    }
    
    public Builder addBlacklists(int param1Int, GameDriverProto.Blacklist.Builder param1Builder) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).addBlacklists(param1Int, param1Builder);
      return this;
    }
    
    public Builder addBlacklists(int param1Int, GameDriverProto.Blacklist param1Blacklist) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).addBlacklists(param1Int, param1Blacklist);
      return this;
    }
    
    public Builder addBlacklists(GameDriverProto.Blacklist.Builder param1Builder) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).addBlacklists(param1Builder);
      return this;
    }
    
    public Builder addBlacklists(GameDriverProto.Blacklist param1Blacklist) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).addBlacklists(param1Blacklist);
      return this;
    }
    
    public Builder clearBlacklists() {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).clearBlacklists();
      return this;
    }
    
    public GameDriverProto.Blacklist getBlacklists(int param1Int) {
      return ((GameDriverProto.Blacklists)this.instance).getBlacklists(param1Int);
    }
    
    public int getBlacklistsCount() {
      return ((GameDriverProto.Blacklists)this.instance).getBlacklistsCount();
    }
    
    public List<GameDriverProto.Blacklist> getBlacklistsList() {
      return Collections.unmodifiableList(((GameDriverProto.Blacklists)this.instance).getBlacklistsList());
    }
    
    public Builder removeBlacklists(int param1Int) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).removeBlacklists(param1Int);
      return this;
    }
    
    public Builder setBlacklists(int param1Int, GameDriverProto.Blacklist.Builder param1Builder) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).setBlacklists(param1Int, param1Builder);
      return this;
    }
    
    public Builder setBlacklists(int param1Int, GameDriverProto.Blacklist param1Blacklist) {
      copyOnWrite();
      ((GameDriverProto.Blacklists)this.instance).setBlacklists(param1Int, param1Blacklist);
      return this;
    }
  }
  
  public static interface BlacklistsOrBuilder extends MessageLiteOrBuilder {
    GameDriverProto.Blacklist getBlacklists(int param1Int);
    
    int getBlacklistsCount();
    
    List<GameDriverProto.Blacklist> getBlacklistsList();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gamedriver/GameDriverProto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */