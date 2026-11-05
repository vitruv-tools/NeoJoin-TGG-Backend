package tools.vitruv.neojoin.tgg.transpiler.operators;

import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TripleRule;

public interface RuleGenerator {
    void extendRule(TripleRule rule);
}
