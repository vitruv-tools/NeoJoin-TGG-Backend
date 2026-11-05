package tools.vitruv.neojoin.tgg.transpiler.graph.tgg;

import tools.vitruv.neojoin.tgg.transpiler.graph.GraphNodeDeepCopyHelper;
import tools.vitruv.neojoin.tgg.transpiler.graph.NameRepository;

public class TGGNodeDeepCopyHelper extends GraphNodeDeepCopyHelper<TGGNode> {
    public TGGNodeDeepCopyHelper(NameRepository copiedNameRepository) {
        super(copiedNameRepository);
    }
}
