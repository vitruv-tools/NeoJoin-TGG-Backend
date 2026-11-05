package tools.vitruv.neojoin.tgg.transpiler.graph;

import tools.vitruv.neojoin.tgg.operators.LogicOperator;
import tools.vitruv.neojoin.tgg.operators.expressions.ValueExpression;

public record Attribute(String name, LogicOperator operator, ValueExpression value) {
    public Attribute deepCopy() {
        return new Attribute(name, operator, value);
    }

    @Override
    public String toString() {
        return "." + name + operator.print() + value;
    }
}
