package tools.vitruv.neojoin.tgg.operators.projections;

import tools.vitruv.neojoin.tgg.operators.FunctionInvocation;
import tools.vitruv.neojoin.tgg.operators.Projection;

public record DerivedProjection(FunctionInvocation function) implements Projection {

    @Override
    public String toString() {
        return "π(" + function + ")";
    }
}
