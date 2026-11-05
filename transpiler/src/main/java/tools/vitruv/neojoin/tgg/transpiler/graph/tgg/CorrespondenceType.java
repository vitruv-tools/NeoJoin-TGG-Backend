package tools.vitruv.neojoin.tgg.transpiler.graph.tgg;

import tools.vitruv.neojoin.tgg.operators.FQN;

import java.util.Objects;

public record CorrespondenceType(FQN source, FQN target) {
    public CorrespondenceType(TGGNode source, TGGNode target) {
        this(source.getType(), target.getType());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CorrespondenceType(FQN source1, FQN target1) &&
            Objects.equals(source, source1) && Objects.equals(target, target1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }
}
