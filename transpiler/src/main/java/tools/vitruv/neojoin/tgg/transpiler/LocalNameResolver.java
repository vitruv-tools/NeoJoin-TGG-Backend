package tools.vitruv.neojoin.tgg.transpiler;

import tools.vitruv.neojoin.tgg.operators.FQN;

public class LocalNameResolver implements NameResolver {
    @Override
    public String resolveName(FQN fqn) {
        return fqn.localName();
    }
}
