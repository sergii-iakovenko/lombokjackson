---
Lombok-Jackson serialization example
---

This is an example Jackson serialization with classes, that use Lombok features (like `@Value`).

It avoids `@JsonCreator` constructor deserialization with requiring messy `@JsonProperty` on each its argument.

All code generation happens in compile time, without any runtime hacks.