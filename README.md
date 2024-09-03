# by-the-pool
> todo: cool name :P

## Goal

Find a better way to diff JVM bytecode in order to prove reproducibility.

Approaches:
1. AST: diff based on tree representation of JVM bytecode.
2. Canonicalization: diff on the intermediate representation inspired by [§4.4 
   Bytecode Canonicalization](https://arxiv.org/abs/2407.00246).
