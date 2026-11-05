@import tools.vitruv.neojoin.tgg.transpiler.NameResolver
@import tools.vitruv.neojoin.tgg.transpiler.graph.pattern.GraphPattern
@import tools.vitruv.neojoin.tgg.transpiler.graph.pattern.PatternNode
@param GraphPattern constraint
@param NameResolver nameResolver
constraint ${nameResolver.resolveConstraintName(constraint)} = forbid ${nameResolver.resolvePatternName(constraint)}
pattern ${nameResolver.resolvePatternName(constraint)} {
    @for(PatternNode node: constraint.getNodes())@template.pattern.PatternNode(node = node, nameResolver = nameResolver)
    @endfor
}
