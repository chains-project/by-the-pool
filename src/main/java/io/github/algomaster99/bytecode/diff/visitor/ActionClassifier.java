package io.github.algomaster99.bytecode.diff.visitor;

import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.actions.model.Delete;
import com.github.gumtreediff.actions.model.Insert;
import com.github.gumtreediff.actions.model.Move;
import com.github.gumtreediff.actions.model.Update;
import com.github.gumtreediff.matchers.MappingStore;
import com.github.gumtreediff.tree.Tree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActionClassifier {
    private List<Tree> srcUpdTrees = new ArrayList<>();
    private List<Tree> dstUpdTrees = new ArrayList<>();
    private List<Tree> srcMvTrees = new ArrayList<>();
    private List<Tree> dstMvTrees = new ArrayList<>();
    private List<Tree> srcDelTrees = new ArrayList<>();
    private List<Tree> dstAddTrees = new ArrayList<>();
    private Map<Tree, Action> originalActionsSrc = new HashMap<>();
    private Map<Tree, Action> originalActionsDst = new HashMap<>();

    public ActionClassifier(MappingStore mappings, List<Action> actions) {
        clean();

        for (Action action : actions) {
            final Tree original = action.getNode();
            if (action instanceof Delete) {
                srcDelTrees.add(original);
                originalActionsSrc.put(original, action);
            } else if (action instanceof Insert) {
                dstAddTrees.add(original);
                originalActionsDst.put(original, action);
            } else if (action instanceof Update) {
                Tree dest = mappings.getDstForSrc(original);
                srcUpdTrees.add(original);
                dstUpdTrees.add(dest);
                originalActionsSrc.put(original, action);
            } else if (action instanceof Move) {
                Tree dest = mappings.getDstForSrc(original);
                srcMvTrees.add(original);
                dstMvTrees.add(dest);
                originalActionsDst.put(dest, action);
            }
        }
    }

    /**
     * This method retrieves ONLY the ROOT actions
     */
    public List<Action> getRootActions() {
        final List<Action> rootActions =
                srcUpdTrees.stream().map(t -> originalActionsSrc.get(t)).collect(Collectors.toList());

        rootActions.addAll(srcDelTrees.stream() //
                .filter(t -> !srcDelTrees.contains(t.getParent()) && !srcUpdTrees.contains(t.getParent())) //
                .map(t -> originalActionsSrc.get(t)) //
                .collect(Collectors.toList()));

        rootActions.addAll(dstAddTrees.stream() //
                .filter(t -> !dstAddTrees.contains(t.getParent()) && !dstUpdTrees.contains(t.getParent())) //
                .map(t -> originalActionsDst.get(t)) //
                .collect(Collectors.toList()));

        rootActions.addAll(dstMvTrees.stream() //
                .filter(t -> !dstMvTrees.contains(t.getParent())) //
                .map(t -> originalActionsDst.get(t)) //
                .collect(Collectors.toList()));

        rootActions.removeAll(Collections.singleton(null));
        return rootActions;
    }

    private void clean() {
        srcUpdTrees.clear();
        dstUpdTrees.clear();
        srcMvTrees.clear();
        dstMvTrees.clear();
        srcDelTrees.clear();
        dstAddTrees.clear();
        originalActionsSrc.clear();
        originalActionsDst.clear();
    }
}
