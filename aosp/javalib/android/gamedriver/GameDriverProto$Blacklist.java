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

public final class Blacklist extends GeneratedMessageLite<GameDriverProto.Blacklist, GameDriverProto.Blacklist.Builder> implements GameDriverProto.BlacklistOrBuilder {
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
  
  private void addAllPackageNames(Iterable<String> paramIterable) {
    ensurePackageNamesIsMutable();
    AbstractMessageLite.addAll(paramIterable, (List)this.packageNames_);
  }
  
  private void addPackageNames(String paramString) {
    if (paramString != null) {
      ensurePackageNamesIsMutable();
      this.packageNames_.add(paramString);
      return;
    } 
    throw null;
  }
  
  private void addPackageNamesBytes(ByteString paramByteString) {
    if (paramByteString != null) {
      ensurePackageNamesIsMutable();
      this.packageNames_.add(paramByteString.toStringUtf8());
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
  
  public static Builder newBuilder(Blacklist paramBlacklist) {
    return (Builder)DEFAULT_INSTANCE.createBuilder(paramBlacklist);
  }
  
  public static Blacklist parseDelimitedFrom(InputStream paramInputStream) throws IOException {
    return (Blacklist)parseDelimitedFrom(DEFAULT_INSTANCE, paramInputStream);
  }
  
  public static Blacklist parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return (Blacklist)parseDelimitedFrom(DEFAULT_INSTANCE, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Blacklist parseFrom(ByteString paramByteString) throws InvalidProtocolBufferException {
    return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramByteString);
  }
  
  public static Blacklist parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramByteString, paramExtensionRegistryLite);
  }
  
  public static Blacklist parseFrom(CodedInputStream paramCodedInputStream) throws IOException {
    return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramCodedInputStream);
  }
  
  public static Blacklist parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramCodedInputStream, paramExtensionRegistryLite);
  }
  
  public static Blacklist parseFrom(InputStream paramInputStream) throws IOException {
    return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramInputStream);
  }
  
  public static Blacklist parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException {
    return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramInputStream, paramExtensionRegistryLite);
  }
  
  public static Blacklist parseFrom(ByteBuffer paramByteBuffer) throws InvalidProtocolBufferException {
    return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramByteBuffer);
  }
  
  public static Blacklist parseFrom(ByteBuffer paramByteBuffer, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramByteBuffer, paramExtensionRegistryLite);
  }
  
  public static Blacklist parseFrom(byte[] paramArrayOfbyte) throws InvalidProtocolBufferException {
    return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramArrayOfbyte);
  }
  
  public static Blacklist parseFrom(byte[] paramArrayOfbyte, ExtensionRegistryLite paramExtensionRegistryLite) throws InvalidProtocolBufferException {
    return (Blacklist)GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, paramArrayOfbyte, paramExtensionRegistryLite);
  }
  
  public static Parser<Blacklist> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
  
  private void setPackageNames(int paramInt, String paramString) {
    if (paramString != null) {
      ensurePackageNamesIsMutable();
      this.packageNames_.set(paramInt, paramString);
      return;
    } 
    throw null;
  }
  
  private void setVersionCode(long paramLong) {
    this.bitField0_ |= 0x1;
    this.versionCode_ = paramLong;
  }
  
  protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke paramMethodToInvoke, Object<Blacklist> paramObject1, Object paramObject2) {
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
  
  public String getPackageNames(int paramInt) {
    return (String)this.packageNames_.get(paramInt);
  }
  
  public ByteString getPackageNamesBytes(int paramInt) {
    return ByteString.copyFromUtf8((String)this.packageNames_.get(paramInt));
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


/* Location:              /home/chun/Desktop/temp/!/android/gamedriver/GameDriverProto$Blacklist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */