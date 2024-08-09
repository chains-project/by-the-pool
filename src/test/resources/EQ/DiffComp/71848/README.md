The diff should precisely show that the two bytecode are equivalent.
Only `String Concatenation` implementation is different and it is based on JEP 280.

Gumtree shows a lot of operations.
Diffoscope shows some spurious changes in diff.
1. Position of `static` function is different.
2. There is an unnecessary `String.valueOf` wrapper in the diff.
