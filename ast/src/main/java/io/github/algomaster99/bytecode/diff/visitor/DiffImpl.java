package io.github.algomaster99.bytecode.diff.visitor;

import com.github.gumtreediff.actions.ChawatheScriptGenerator;
import com.github.gumtreediff.actions.EditScript;
import com.github.gumtreediff.actions.EditScriptGenerator;
import com.github.gumtreediff.actions.SimplifiedChawatheScriptGenerator;
import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.matchers.CompositeMatchers;
import com.github.gumtreediff.matchers.GumtreeProperties;
import com.github.gumtreediff.matchers.MappingStore;
import com.github.gumtreediff.matchers.Matcher;
import com.github.gumtreediff.tree.Tree;
import com.github.gumtreediff.tree.TreeContext;
import java.util.List;

public class DiffImpl {
    private static final Matcher matcher = new CompositeMatchers.SimpleGumtree();
    private static final GumtreeProperties properties = new GumtreeProperties();
    private final TreeContext context;
    private final Tree rootBytecodeLeft;
    private final Tree rootBytecodeRight;

    private EditScript allOperations;
    private EditScript simplifiedOperations;
    private List<Action> rootOperations;

    public DiffImpl(TreeContext context, Tree rootBytecodeLeft, Tree rootBytecodeRight) {
        this.context = context;
        this.rootBytecodeLeft = rootBytecodeLeft;
        this.rootBytecodeRight = rootBytecodeRight;
    }

    public void computeDiff() {
        if (context == null) {
            throw new IllegalArgumentException();
        }
        final MappingStore mappingsComp = new MappingStore(rootBytecodeLeft, rootBytecodeRight);

        matcher.configure(properties);

        MappingStore mappings = matcher.match(rootBytecodeLeft, rootBytecodeRight, mappingsComp);

        // all actions

        EditScriptGenerator actionGenerator = new ChawatheScriptGenerator();
        this.allOperations = actionGenerator.computeActions(mappings);

        // simplified actions

        EditScriptGenerator actionGeneratorSimplified = new SimplifiedChawatheScriptGenerator();
        this.simplifiedOperations = actionGeneratorSimplified.computeActions(mappings);

        // root operations
        ActionClassifier actionClassifier = new ActionClassifier(mappings, this.allOperations.asList());
        this.rootOperations = actionClassifier.getRootActions();
    }

    public EditScript getAllOperations() {
        return allOperations;
    }

    public EditScript getSimplifiedOperations() {
        return simplifiedOperations;
    }

    public List<Action> getRootOperations() {
        return rootOperations;
    }
}
