# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: dao.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='dao.proto',
  package='com.test',
  syntax='proto3',
  serialized_options=None,
  create_key=_descriptor._internal_create_key,
  serialized_pb=b'\n\tdao.proto\x12\x08\x63om.test\"2\n\x05\x45\x63hoo\x12\n\n\x02id\x18\x01 \x01(\x05\x12\r\n\x05input\x18\x02 \x01(\t\x12\x0e\n\x06output\x18\x03 \x01(\t\"\'\n\x06\x45\x63hoos\x12\x1d\n\x04item\x18\x01 \x03(\x0b\x32\x0f.com.test.Echoob\x06proto3'
)




_ECHOO = _descriptor.Descriptor(
  name='Echoo',
  full_name='com.test.Echoo',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='id', full_name='com.test.Echoo.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='input', full_name='com.test.Echoo.input', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='output', full_name='com.test.Echoo.output', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=23,
  serialized_end=73,
)


_ECHOOS = _descriptor.Descriptor(
  name='Echoos',
  full_name='com.test.Echoos',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='item', full_name='com.test.Echoos.item', index=0,
      number=1, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=75,
  serialized_end=114,
)

_ECHOOS.fields_by_name['item'].message_type = _ECHOO
DESCRIPTOR.message_types_by_name['Echoo'] = _ECHOO
DESCRIPTOR.message_types_by_name['Echoos'] = _ECHOOS
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

Echoo = _reflection.GeneratedProtocolMessageType('Echoo', (_message.Message,), {
  'DESCRIPTOR' : _ECHOO,
  '__module__' : 'dao_pb2'
  # @@protoc_insertion_point(class_scope:com.test.Echoo)
  })
_sym_db.RegisterMessage(Echoo)

Echoos = _reflection.GeneratedProtocolMessageType('Echoos', (_message.Message,), {
  'DESCRIPTOR' : _ECHOOS,
  '__module__' : 'dao_pb2'
  # @@protoc_insertion_point(class_scope:com.test.Echoos)
  })
_sym_db.RegisterMessage(Echoos)


# @@protoc_insertion_point(module_scope)
