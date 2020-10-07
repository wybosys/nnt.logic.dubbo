# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: dubbo/test.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()


from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2
from google.protobuf import wrappers_pb2 as google_dot_protobuf_dot_wrappers__pb2
import dao_pb2 as dao__pb2


DESCRIPTOR = _descriptor.FileDescriptor(
  name='dubbo/test.proto',
  package='com.test.dubbo',
  syntax='proto3',
  serialized_pb=_b('\n\x10\x64ubbo/test.proto\x12\x0e\x63om.test.dubbo\x1a\x1bgoogle/protobuf/empty.proto\x1a\x1egoogle/protobuf/wrappers.proto\x1a\tdao.proto\"\x1c\n\tTestReply\x12\x0f\n\x07message\x18\x01 \x01(\t2\x83\x02\n\x04Test\x12<\n\x05hello\x12\x16.google.protobuf.Empty\x1a\x19.com.test.dubbo.TestReply\"\x00\x12\x38\n\x05\x65\x63hoo\x12\x1c.google.protobuf.StringValue\x1a\x0f.com.test.Echoo\"\x00\x12\x44\n\x0b\x63lear_echoo\x12\x16.google.protobuf.Empty\x1a\x1b.google.protobuf.Int32Value\"\x00\x12=\n\x0cupdate_echoo\x12\x0f.com.test.Echoo\x1a\x1a.google.protobuf.BoolValue\"\x00\x62\x06proto3')
  ,
  dependencies=[google_dot_protobuf_dot_empty__pb2.DESCRIPTOR,google_dot_protobuf_dot_wrappers__pb2.DESCRIPTOR,dao__pb2.DESCRIPTOR,])




_TESTREPLY = _descriptor.Descriptor(
  name='TestReply',
  full_name='com.test.dubbo.TestReply',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='message', full_name='com.test.dubbo.TestReply.message', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=108,
  serialized_end=136,
)

DESCRIPTOR.message_types_by_name['TestReply'] = _TESTREPLY
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

TestReply = _reflection.GeneratedProtocolMessageType('TestReply', (_message.Message,), dict(
  DESCRIPTOR = _TESTREPLY,
  __module__ = 'dubbo.test_pb2'
  # @@protoc_insertion_point(class_scope:com.test.dubbo.TestReply)
  ))
_sym_db.RegisterMessage(TestReply)



_TEST = _descriptor.ServiceDescriptor(
  name='Test',
  full_name='com.test.dubbo.Test',
  file=DESCRIPTOR,
  index=0,
  options=None,
  serialized_start=139,
  serialized_end=398,
  methods=[
  _descriptor.MethodDescriptor(
    name='hello',
    full_name='com.test.dubbo.Test.hello',
    index=0,
    containing_service=None,
    input_type=google_dot_protobuf_dot_empty__pb2._EMPTY,
    output_type=_TESTREPLY,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='echoo',
    full_name='com.test.dubbo.Test.echoo',
    index=1,
    containing_service=None,
    input_type=google_dot_protobuf_dot_wrappers__pb2._STRINGVALUE,
    output_type=dao__pb2._ECHOO,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='clear_echoo',
    full_name='com.test.dubbo.Test.clear_echoo',
    index=2,
    containing_service=None,
    input_type=google_dot_protobuf_dot_empty__pb2._EMPTY,
    output_type=google_dot_protobuf_dot_wrappers__pb2._INT32VALUE,
    options=None,
  ),
  _descriptor.MethodDescriptor(
    name='update_echoo',
    full_name='com.test.dubbo.Test.update_echoo',
    index=3,
    containing_service=None,
    input_type=dao__pb2._ECHOO,
    output_type=google_dot_protobuf_dot_wrappers__pb2._BOOLVALUE,
    options=None,
  ),
])
_sym_db.RegisterServiceDescriptor(_TEST)

DESCRIPTOR.services_by_name['Test'] = _TEST

# @@protoc_insertion_point(module_scope)
