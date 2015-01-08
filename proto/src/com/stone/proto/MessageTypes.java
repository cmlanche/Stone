// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MessageType.proto

package com.stone.proto;

public final class MessageTypes {
  private MessageTypes() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code MessageType}
   *
   * <pre>
   * 消息类型;
   * </pre>
   */
  public enum MessageType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>CG_PLAYER_LOGIN = 1001;</code>
     *
     * <pre>
     * 请求登陆
     * </pre>
     */
    CG_PLAYER_LOGIN(0, 1001),
    /**
     * <code>GC_PLAYER_LOGIN_RESULT = 1002;</code>
     */
    GC_PLAYER_LOGIN_RESULT(1, 1002),
    ;

    /**
     * <code>CG_PLAYER_LOGIN = 1001;</code>
     *
     * <pre>
     * 请求登陆
     * </pre>
     */
    public static final int CG_PLAYER_LOGIN_VALUE = 1001;
    /**
     * <code>GC_PLAYER_LOGIN_RESULT = 1002;</code>
     */
    public static final int GC_PLAYER_LOGIN_RESULT_VALUE = 1002;


    public final int getNumber() { return value; }

    public static MessageType valueOf(int value) {
      switch (value) {
        case 1001: return CG_PLAYER_LOGIN;
        case 1002: return GC_PLAYER_LOGIN_RESULT;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<MessageType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<MessageType>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<MessageType>() {
            public MessageType findValueByNumber(int number) {
              return MessageType.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.stone.proto.MessageTypes.getDescriptor().getEnumTypes().get(0);
    }

    private static final MessageType[] VALUES = values();

    public static MessageType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private MessageType(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:MessageType)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021MessageType.proto*@\n\013MessageType\022\024\n\017CG" +
      "_PLAYER_LOGIN\020\351\007\022\033\n\026GC_PLAYER_LOGIN_RESU" +
      "LT\020\352\007B\037\n\017com.stone.protoB\014MessageTypes"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}