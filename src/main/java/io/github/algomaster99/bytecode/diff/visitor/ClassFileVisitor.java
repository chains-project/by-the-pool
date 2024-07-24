package io.github.algomaster99.bytecode.diff.visitor;

import com.github.gumtreediff.tree.Tree;
import com.github.gumtreediff.tree.TreeContext;
import com.github.gumtreediff.tree.Type;
import com.github.gumtreediff.tree.TypeSet;
import java.lang.classfile.ClassFileVersion;
import java.lang.classfile.ClassModel;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.classfile.constantpool.ClassEntry;
import java.util.Stack;

public class ClassFileVisitor {
    public static final String NOTYPE = "<notype>";
    private final TreeContext treeContext;
    private final Stack<Tree> nodes = new Stack<>();
    private final Tree root;

    public ClassFileVisitor(TreeContext treeContext, Tree root) {
        this.treeContext = treeContext;
        this.root = root;
        nodes.push(root);
    }

    public void scan(ClassModel classModel) {
        visit(classModel.thisClass());
        classModel.elementStream().forEach(classElement -> {
            switch (classElement) {
                case ClassFileVersion version -> {
                    visit(version);
                }
                case SourceFileAttribute sourceFileAttribute -> {
                    visit(sourceFileAttribute);
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
        pushNodeToTree(node);
        Type majorVersion = TypeSet.type(DiffTypes.DIFF_IN_MAJOR_VERSION);
        Type minorVersion = TypeSet.type("minorVersion");
        Tree majorNode = treeContext.createTree(majorVersion, String.valueOf(element.majorVersion()));
        Tree minorNode = treeContext.createTree(minorVersion, String.valueOf(element.minorVersion()));
        addLeafNode(majorNode);
        addLeafNode(minorNode);
        // remove classfileversion node
        nodes.pop();
    }

    public void visit(SourceFileAttribute element) {
        Type rootNode = TypeSet.type(DiffTypes.DIFF_IN_SOURCE_FILE_ATTRIBUTE);
        Tree node = treeContext.createTree(rootNode, element.sourceFile().stringValue());
        pushNodeToTree(node);
        // remove source file attribute
        nodes.pop();
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
}
