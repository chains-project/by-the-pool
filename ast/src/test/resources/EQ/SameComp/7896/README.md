The bytecode differs because String Concatenation is reimplemented using 
`invokedynamic` in Java 9.

Diffoscope shows the difference but is polluted with the constant pool ordering.

Gumtree shows the difference but there are a lot of operations to process 
for the user.
