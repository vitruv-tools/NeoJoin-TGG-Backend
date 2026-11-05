package tools.vitruv.neojoin.tgg.operators.selection;

import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.operators.FunctionInvocation;
import tools.vitruv.neojoin.tgg.operators.expressions.ConstantExpression;

public class ThetaJoin implements PatternLink {
    private final FQN element;
    private final FunctionInvocation function;

    public ThetaJoin(FQN element, FunctionInvocation function) {
        this.element = element;
        this.function = function;
    }

    public ThetaJoin(FQN element, String function) {
        this(element, new FunctionInvocation(function));
    }

    public void setConstantArgument(String parameter, ConstantExpression value) {
        function.setConstantArgument(parameter, value.value());
    }

    public void setConstrainedArgument(String parameter, FQN element, String property) {
        function.setConstrainedArgument(parameter, element, property);
    }

    @Override
    public FQN element() {
        return element;
    }

    public FunctionInvocation function() {
        return function;
    }

    @Override
    public String toString() {
        return " ⨝(" + function + ") " + element.fqn();
    }
}
