package tools.vitruv.neojoin.tgg.operators.filters;

import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.operators.FunctionInvocation;
import tools.vitruv.neojoin.tgg.operators.expressions.ConstantExpression;
import tools.vitruv.neojoin.tgg.operators.Filter;

public record FunctionFilter(FunctionInvocation function) implements Filter {

    public FunctionFilter(String function) {
        this(new FunctionInvocation(function));
    }

    public void setConstantArgument(String parameter, ConstantExpression value) {
        function.setConstantArgument(parameter, value.value());
    }

    public void setConstrainedArgument(String parameter, FQN element, String property) {
        function.setConstrainedArgument(parameter, element, property);
    }

    @Override
    public String toString() {
        return "φ(" + function + ")";
    }
}
