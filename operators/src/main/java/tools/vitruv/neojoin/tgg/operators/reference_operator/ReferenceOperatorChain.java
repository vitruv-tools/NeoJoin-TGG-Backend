package tools.vitruv.neojoin.tgg.operators.reference_operator;

import tools.vitruv.neojoin.expression_parser.model.ReferenceOperator;
import tools.vitruv.neojoin.tgg.operators.FQN;

public record ReferenceOperatorChain(
        String sourceNamespace,
        FQN targetRoot,
        FQN targetLeaf,
        String targetField,
        ReferenceOperator referenceOperator) {
    @Override
    public String toString() {
        return "π(" + referenceOperator.toString() + ")";
    }
}
