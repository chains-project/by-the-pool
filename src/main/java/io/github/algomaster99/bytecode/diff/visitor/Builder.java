package io.github.algomaster99.bytecode.diff.visitor;

import static com.github.gumtreediff.tree.TypeSet.type;

import com.github.gumtreediff.tree.Tree;
import com.github.gumtreediff.tree.TreeContext;
import com.github.gumtreediff.tree.Type;
import java.lang.classfile.ClassModel;

public class Builder {
    private final TreeContext treeContext = new TreeContext();

    public Tree getTree(ClassModel classModel) {
        Type type = type("root");
        final Tree root = treeContext.createTree(type, "");
        new ClassFileVisitor(treeContext, root).scan(classModel);
        return root;
    }

    public TreeContext getTreeContext() {
        return treeContext;
    }
}
