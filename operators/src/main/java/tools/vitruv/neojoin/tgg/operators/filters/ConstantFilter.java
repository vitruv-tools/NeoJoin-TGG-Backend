package tools.vitruv.neojoin.tgg.operators.filters;

import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.operators.LogicOperator;
import tools.vitruv.neojoin.tgg.operators.expressions.ConstantExpression;
import tools.vitruv.neojoin.tgg.operators.Filter;

public record ConstantFilter(FQN element,
                             String property,
                             LogicOperator operator,
                             ConstantExpression value) implements Filter {

    @Override
    public String toString() {
        return "φ(" + element.fqn() + "::" + property + operator.print() + value.value() + ")";
    }
}
