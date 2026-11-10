package tools.vitruv.neojoin.tgg.operators.builder;

import tools.vitruv.neojoin.tgg.operators.FQN;
import tools.vitruv.neojoin.tgg.operators.FunctionInvocation;
import tools.vitruv.neojoin.tgg.operators.Tuple;
import tools.vitruv.neojoin.tgg.operators.selection.Pattern;

import java.util.Collection;

public class SourceSelectionBuilder {
    private final Pattern pattern;

    public SourceSelectionBuilder(FQN element) {
        this.pattern = Pattern.from(element);
    }

    private SourceSelectionBuilder(Pattern pattern) {
        this.pattern = pattern;
    }

    public SourceSelectionBuilder join(FQN element, Collection<Tuple<String, String>> properties) {
        return new SourceSelectionBuilder(pattern.join(element, properties));
    }

    public SourceSelectionBuilder join(FQN element, String originProperty, String destinationProperty) {
        return new SourceSelectionBuilder(pattern.join(element, originProperty, destinationProperty));
    }

    public SourceSelectionBuilder join(FQN element, String property) {
        return new SourceSelectionBuilder(pattern.join(element, property));
    }

    public SourceSelectionBuilder join(FQN element, FunctionInvocation functionInvocation) {
        return new SourceSelectionBuilder(pattern.join(element, functionInvocation));
    }

    public SourceSelectionBuilder ref(FQN element, String reference) {
        return new SourceSelectionBuilder(pattern.ref(element, reference));
    }

    public TargetSelectionBuilder create(FQN element) {
        return new TargetSelectionBuilder(pattern, element);
    }
}
