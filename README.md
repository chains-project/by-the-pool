# gumtree-jvm-bytecode-diff
> todo: cool name :P

Tool that reports fine-grained diff between two Java classfiles.
The diff is based on the [gumtree](https://github.com/GumTreeDiff/gumtree) 
algorithm, which is a tree differencing tool.
The tree representation for the bytecode is based on the [`ClassModel`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/lang/classfile/ClassModel.html)
API proposed in [JEP 457](https://openjdk.org/jeps/457).
As of writing this README, this feature is JDK 22 as first preview.

## Examples

#### With diffoscope 272
> Java 17

```diff
--- A45305b1a-3bdc-455f-b22d-96636743c129.class
+++ A4e6577e8-d1ee-4acd-b38f-1dcd68658a48.class
├── procyon -ec {}
│ @@ -1,9 +1,9 @@
│  
│  package io.dropwizard.jersey;
│  
│ -public class DropwizardResourceConfig$SpecificBindere75da6ac-3201-4073-bd4f-75682c761862 extends DropwizardResourceConfig$SpecificBinder
│ +public class DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443 extends DropwizardResourceConfig$SpecificBinder
│  {
│ -    public DropwizardResourceConfig$SpecificBindere75da6ac-3201-4073-bd4f-75682c761862(final Object o, final Class clazz) {
│ +    public DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443(final Object o, final Class clazz) {
│          super(o, clazz);
│      }
│  }
```

#### With gumtree-jvm-bytecode-diff
> Java 22

```txt
===
update-node
---
thisClass: io/dropwizard/jersey/DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443 [0,0]
replace io/dropwizard/jersey/DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443 by io/dropwizard/jersey/DropwizardResourceConfig$SpecificBindere75da6ac-3201-4073-bd4f-75682c761862

===
update-node
---
sourceFileAttribute: DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443.java [0,0]
replace DropwizardResourceConfig$SpecificBinder1d717258-a234-4322-aa8f-167ac4454443.java by DropwizardResourceConfig$SpecificBindere75da6ac-3201-4073-bd4f-75682c761862.java
```

It precisely tells you what has changed in the classfile. In this case,
`thisClass` and `sourceFileAttribute` have been updated.
