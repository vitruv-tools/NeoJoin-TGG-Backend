package tools.vitruv.neojoin.tgg.transpiler.graph.tgg;

import tools.vitruv.neojoin.tgg.operators.expressions.ValueExpression;

public record Parameter(String attribute, ValueExpression value) {
    public Parameter deepCopy() {
        return new Parameter(attribute, value.deepCopy());
    }
}
