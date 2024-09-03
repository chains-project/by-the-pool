Tool that reports fine-grained diff between two Java classfiles.
The diff is based on the [gumtree](https://github.com/GumTreeDiff/gumtree)
algorithm, which is a tree differencing tool.
The tree representation for the bytecode is based on the [`ClassModel`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/lang/classfile/ClassModel.html)
API proposed in [JEP 457](https://openjdk.org/jeps/457).
As of writing this README, this feature is JDK 22 as first preview.