package io.github.algomaster99.bytecode.diff.visitor;

import static com.github.gumtreediff.tree.TypeSet.type;

import com.github.gumtreediff.tree.Tree;
import com.github.gumtreediff.tree.TreeContext;
import com.github.gumtreediff.tree.Type;

public class Builder {
    private final TreeContext treeContext = new TreeContext();

    public Tree getTree(byte[] bytes) {
        Type type = type("root");
        final Tree root = treeContext.createTree(type, "");
        root.setPos(0);
        root.setLength(bytes.length - 1);
        treeContext.setRoot(root);
        new ClassFileVisitor(treeContext, root, bytes).scan();
        return root;
    }

    public TreeContext getTreeContext() {
        return treeContext;
    }
}
