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
import java.lang.classfile.instruction.FieldInstruction;
import java.lang.classfile.instruction.LoadInstruction;
import java.lang.reflect.Field;
import java.util.Stack;

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
        setPositions(node, fieldModel, fieldModel.getClass(), "startPos", "endPos");
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
        setPositions(node, methodModel, methodModel.getClass(), "startPos", "endPos");
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
            setPositions(codeModelNode, codeModel, codeModel.getClass(), "codeStart", "codeEnd");
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
}
