package io.github.algomaster99.bytecode.diff.visitor;

import com.github.gumtreediff.tree.Tree;
import com.github.gumtreediff.tree.TreeContext;
import com.github.gumtreediff.tree.Type;
import com.github.gumtreediff.tree.TypeSet;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassFileElement;
import java.lang.classfile.ClassFileVersion;
import java.lang.classfile.ClassModel;
import java.lang.classfile.CodeModel;
import java.lang.classfile.FieldModel;
import java.lang.classfile.Instruction;
import java.lang.classfile.MethodModel;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.classfile.constantpool.ClassEntry;
import java.lang.classfile.constantpool.Utf8Entry;
import java.lang.classfile.instruction.BranchInstruction;
import java.lang.classfile.instruction.ConstantInstruction;
import java.lang.classfile.instruction.FieldInstruction;
import java.lang.classfile.instruction.InvokeDynamicInstruction;
import java.lang.classfile.instruction.InvokeInstruction;
import java.lang.classfile.instruction.LoadInstruction;
import java.lang.classfile.instruction.NewObjectInstruction;
import java.lang.classfile.instruction.ReturnInstruction;
import java.lang.classfile.instruction.StackInstruction;
import java.lang.classfile.instruction.StoreInstruction;
import java.lang.classfile.instruction.ThrowInstruction;
import java.lang.classfile.instruction.TypeCheckInstruction;
import java.lang.reflect.Field;
import java.util.Stack;
import jdk.internal.classfile.impl.LabelImpl;

public class ClassFileVisitor {
    public static final String NOTYPE = "<notype>";
    private final TreeContext treeContext;
    private final Stack<Tree> nodes = new Stack<>();
    private final Tree root;
    private final byte[] bytecode;
    private ClassModel classModel;

    public ClassFileVisitor(TreeContext treeContext, Tree root, byte[] bytes) {
        this.treeContext = treeContext;
        this.root = root;
        this.bytecode = bytes;
        classModel = ClassFile.of().parse(bytes);
        nodes.push(root);
    }

    public void scan() {
        visit(classModel.thisClass());
        classModel.elementStream().forEach(classElement -> {
            switch (classElement) {
                case ClassFileVersion version -> {
                    visit(version);
                }
                case SourceFileAttribute sourceFileAttribute -> {
                    visit(sourceFileAttribute);
                }
                case FieldModel fieldModel -> {
                    visit(fieldModel);
                }
                case MethodModel methodModel -> {
                    visit(methodModel);
                }
                default -> {
                    // todo: to be implemented
                }
            }
        });
    }

    public void visit(ClassEntry element) {
        Type thisClassEntry = TypeSet.type(DiffTypes.DIFF_IN_CLASS_NAME);
        Tree nameIndexNode = treeContext.createTree(
                thisClassEntry, String.valueOf(element.name().stringValue()));
        pushNodeToTree(nameIndexNode);
        // remove class entry node
        nodes.pop();
    }

    public void visit(ClassFileVersion element) {
        String type = "ClassFileVersion";
        Type rootNode = TypeSet.type(type);
        Tree node = treeContext.createTree(rootNode);
        node.setPos(4);
        node.setLength(4);
        pushNodeToTree(node);
        Type majorVersion = TypeSet.type(DiffTypes.DIFF_IN_MAJOR_VERSION);
        Type minorVersion = TypeSet.type("minorVersion");
        Tree majorNode = treeContext.createTree(majorVersion, String.valueOf(element.majorVersion()));
        Tree minorNode = treeContext.createTree(minorVersion, String.valueOf(element.minorVersion()));
        minorNode.setPos(4);
        minorNode.setLength(2);
        majorNode.setPos(6);
        majorNode.setLength(2);
        addLeafNode(majorNode);
        addLeafNode(minorNode);
        // remove classfileversion node
        nodes.pop();
    }

    public void visit(SourceFileAttribute element) {
        Type type = TypeSet.type(DiffTypes.DIFF_IN_SOURCE_FILE_ATTRIBUTE);
        Tree node = treeContext.createTree(type, element.sourceFile().stringValue());
        pushNodeToTree(node);
        // remove source file attribute
        nodes.pop();
    }

    public void visit(FieldModel fieldModel) {
        Type rootType = TypeSet.type(FieldModel.class.getName());
        Tree node = treeContext.createTree(rootType);
        pushNodeToTree(node);

        Type fieldName = TypeSet.type("FIELD_NAME");
        Tree fieldNameNode =
                treeContext.createTree(fieldName, fieldModel.fieldName().stringValue());
        fieldNameNode.setPos(node.getPos());
        fieldNameNode.setLength(2);
        addLeafNode(fieldNameNode);
        Type fieldType = TypeSet.type(Utf8Entry.class.getName());
        Tree fieldTypeNode =
                treeContext.createTree(fieldType, fieldModel.fieldType().stringValue());
        fieldTypeNode.setPos(node.getPos() + 2);
        fieldTypeNode.setLength(2);
        addLeafNode(fieldTypeNode);
        // remove field node
        nodes.pop();
    }

    public void visit(MethodModel methodModel) {
        Type rootType = TypeSet.type(MethodModel.class.getName());
        Tree node = treeContext.createTree(rootType);
        pushNodeToTree(node);

        Type methodName = TypeSet.type("METHOD_NAME");
        Tree methodNameNode =
                treeContext.createTree(methodName, methodModel.methodName().stringValue());
        methodNameNode.setPos(node.getPos() + 2);
        methodNameNode.setLength(2);
        addLeafNode(methodNameNode);

        Type returnType = TypeSet.type("RETURN_TYPE");
        Tree returnTypeNode =
                treeContext.createTree(returnType, methodModel.methodType().stringValue());
        returnTypeNode.setPos(node.getPos() + 4);
        returnTypeNode.setLength(2);
        addLeafNode(returnTypeNode);

        if (methodModel.code().isPresent()) {
            CodeModel codeModel = methodModel.code().get();
            Type codeModelType = TypeSet.type(CodeModel.class.getName());
            Tree codeModelNode = treeContext.createTree(codeModelType, "CodeModel");
            pushNodeToTree(codeModelNode);

            codeModel.elementList().forEach(element -> {
                switch (element) {
                    case Instruction instruction -> {
                        addInstructionNode(instruction);
                    }
                    default -> {
                        // todo: to be implemented, mainly attributes are ignored
                    }
                }
            });
            // remove code model node
            nodes.pop();
        }
        // remove method node
        nodes.pop();
    }

    private void addInstructionNode(Instruction instruction) {
        switch (instruction) {
            case LoadInstruction loadInstruction -> {
                Type type = TypeSet.type(loadInstruction.opcode().name());
                Tree node = treeContext.createTree(
                        type, loadInstruction.opcode().primaryTypeKind().name());
                addLeafNode(node);
            }
            case StoreInstruction storeInstruction -> {
                Type type = TypeSet.type(storeInstruction.opcode().name());
                Tree node = treeContext.createTree(
                        type, storeInstruction.opcode().primaryTypeKind().name());
                addLeafNode(node);
            }
            case NewObjectInstruction newObjectInstruction -> {
                Type type = TypeSet.type(newObjectInstruction.opcode().name());
                Tree node = treeContext.createTree(type);
                pushNodeToTree(node);

                Type newObjectType = TypeSet.type("CLASS_NAME");
                Tree newObjectNode = treeContext.createTree(
                        newObjectType, newObjectInstruction.className().asInternalName());
                addLeafNode(newObjectNode);
                // remove new object instruction node
                nodes.pop();
            }
            case TypeCheckInstruction typeCheckInstruction -> {
                Type type = TypeSet.type(typeCheckInstruction.opcode().name());
                Tree node = treeContext.createTree(type);
                pushNodeToTree(node);

                Type typeCheckType = TypeSet.type("TYPE_CHECK");
                Tree typeCheckNode = treeContext.createTree(
                        typeCheckType, typeCheckInstruction.type().asInternalName());
                addLeafNode(typeCheckNode);
                // remove type check instruction node
                nodes.pop();
            }
            case BranchInstruction branchInstruction -> {
                Type type = TypeSet.type(branchInstruction.opcode().name());
                Tree node = treeContext.createTree(type);
                pushNodeToTree(node);

                Type branchType = TypeSet.type("BRANCH_NUMBER");
                Tree branchNode = treeContext.createTree(
                        branchType, String.valueOf(((LabelImpl) branchInstruction.target()).getBCI()));
                addLeafNode(branchNode);
                // remove branch instruction node
                nodes.pop();
            }
            case StackInstruction stackInstruction -> {
                Type type = TypeSet.type(stackInstruction.opcode().name());
                Tree node = treeContext.createTree(type);
                addLeafNode(node);
            }
            case FieldInstruction fieldInstruction -> {
                Type type = TypeSet.type(fieldInstruction.opcode().name());
                Tree fieldNode = treeContext.createTree(type);
                pushNodeToTree(fieldNode);
                Type fieldType = TypeSet.type(DiffTypes.DIFF_IN_FIELD_TYPE);
                Tree fieldTypeNode = treeContext.createTree(
                        fieldType, fieldInstruction.field().type().stringValue());
                addLeafNode(fieldTypeNode);
                Type fieldName = TypeSet.type(DiffTypes.DIFF_IN_FIELD_NAME);
                Tree fieldNameNode = treeContext.createTree(
                        fieldName, fieldInstruction.field().name().stringValue());
                addLeafNode(fieldNameNode);
                // remove field instruction node
                nodes.pop();
            }
            case ConstantInstruction constantInstruction -> {
                Type type = TypeSet.type(constantInstruction.opcode().name());
                Tree node = treeContext.createTree(type);
                pushNodeToTree(node);

                Type constantType = TypeSet.type(constantInstruction.typeKind().name());
                Tree constantNode = treeContext.createTree(
                        constantType, constantInstruction.constantValue().toString());
                addLeafNode(constantNode);
                // remove constant instruction node
                nodes.pop();
            }
            case InvokeInstruction invokeInstruction -> {
                Type type = TypeSet.type(invokeInstruction.opcode().name());
                Tree node = treeContext.createTree(type);
                pushNodeToTree(node);

                Type methodOwnerType = TypeSet.type("METHOD_OWNER");
                Tree methodOwnerNode = treeContext.createTree(
                        methodOwnerType, invokeInstruction.method().owner().asInternalName());
                addLeafNode(methodOwnerNode);

                Type methodInvokedName = TypeSet.type("METHOD_INVOKED_NAME");
                Tree methodNode = treeContext.createTree(
                        methodInvokedName, invokeInstruction.method().name().stringValue());
                addLeafNode(methodNode);

                Type methodInvokedType = TypeSet.type("METHOD_INVOKED_RETURN_TYPE");
                Tree methodTypeNode = treeContext.createTree(
                        methodInvokedType, invokeInstruction.method().type().stringValue());
                addLeafNode(methodTypeNode);

                // remove invoke instruction node
                nodes.pop();
            }

            case InvokeDynamicInstruction invokeDynamicInstruction -> {
                Type type = TypeSet.type(invokeDynamicInstruction.opcode().name());
                Tree node = treeContext.createTree(type);
                pushNodeToTree(node);

                Type bootstrapMethodName = TypeSet.type("BOOTSTRAP_METHOD_NAME");
                Tree methodName = treeContext.createTree(
                        bootstrapMethodName, invokeDynamicInstruction.name().stringValue());
                addLeafNode(methodName);

                Type bootstrapMethodType = TypeSet.type("BOOTSTRAP_METHOD_TYPE");
                Tree methodTypeNode = treeContext.createTree(
                        bootstrapMethodType, invokeDynamicInstruction.type().stringValue());
                addLeafNode(methodTypeNode);

                Type bootstrapMethodOwner = TypeSet.type("BOOTSTRAP_METHOD_OWNER");
                Tree methodOwnerNode = treeContext.createTree(
                        bootstrapMethodOwner,
                        invokeDynamicInstruction.bootstrapMethod().owner().descriptorString());
                addLeafNode(methodOwnerNode);

                // remove invoke dynamic instruction node
                nodes.pop();
            }
            case ReturnInstruction returnInstruction -> {
                Type type = TypeSet.type(returnInstruction.opcode().name());
                Tree node = treeContext.createTree(type);
                addLeafNode(node);
            }
            case ThrowInstruction throwInstruction -> {
                Type type = TypeSet.type(throwInstruction.opcode().name());
                Tree node = treeContext.createTree(type);
                addLeafNode(node);
            }
            default -> {
                // todo: to be implemented
            }
        }
    }

    private void pushNodeToTree(Tree node) {
        Tree parent = nodes.peek();
        if (parent != null) { // happens when nodes.push(null)
            parent.addChild(node);
        }
        nodes.push(node);
    }

    private void addLeafNode(Tree node) {
        Tree parent = nodes.peek();
        if (parent != null) { // happens when nodes.push(null)
            parent.addChild(node);
        }
    }

    private static void setPositions(
            Tree node, ClassFileElement cfe, Class<?> whereFieldLies, String startField, String endField) {
        try {
            Field startPos = whereFieldLies.getDeclaredField(startField);
            startPos.setAccessible(true);
            Field endPos = whereFieldLies.getDeclaredField(endField);
            endPos.setAccessible(true);
            int startPosValue = (int) startPos.get(cfe);
            int endPosValue = (int) endPos.get(cfe);
            node.setPos(startPosValue);
            node.setLength(endPosValue - startPosValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Position information not available for this element
            if (cfe.getClass().getSuperclass() != null) {
                setPositions(node, cfe, whereFieldLies.getSuperclass(), startField, endField);
            } else {
                throw new RuntimeException("Position information not available for this element");
            }
        }
    }

    private static void setPositions(
            Tree node, ClassFileElement cfe, Class<?> whereFieldLies, String startField, int length) {
        try {
            Field startPos = whereFieldLies.getDeclaredField(startField);
            startPos.setAccessible(true);
            int startPosValue = (int) startPos.get(cfe);
            node.setPos(startPosValue);
            node.setLength(length);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Position information not available for this element
            if (cfe.getClass().getSuperclass() != null) {
                setPositions(node, cfe, whereFieldLies.getSuperclass(), startField, length);
            } else {
                throw new RuntimeException("Position information not available for this element");
            }
        }
    }
}
