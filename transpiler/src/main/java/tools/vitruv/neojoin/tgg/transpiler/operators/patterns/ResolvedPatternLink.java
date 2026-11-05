package tools.vitruv.neojoin.tgg.transpiler.operators.patterns;

import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGNode;
import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGSlice;

public interface ResolvedPatternLink {
    FQN element();
    TGGNode extendSlice(TGGSlice slice, TGGNode lastNode);
}
