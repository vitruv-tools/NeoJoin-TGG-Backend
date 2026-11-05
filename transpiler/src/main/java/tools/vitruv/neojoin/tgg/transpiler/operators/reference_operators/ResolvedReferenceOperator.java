package tools.vitruv.neojoin.tgg.transpiler.operators.reference_operators;

import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TripleRulesBuilder;

public interface ResolvedReferenceOperator {
    void extendRules(TripleRulesBuilder builder);
}
