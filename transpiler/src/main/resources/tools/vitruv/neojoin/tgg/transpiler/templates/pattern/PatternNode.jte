@import tools.vitruv.neojoin.tgg.transpiler.NameResolver
@import tools.vitruv.neojoin.tgg.transpiler.graph.Attribute
@import tools.vitruv.neojoin.tgg.transpiler.graph.pattern.PatternLink;import tools.vitruv.neojoin.tgg.transpiler.graph.pattern.PatternNode
@param PatternNode node
@param NameResolver nameResolver
${node.getId()}: ${nameResolver.resolveName(node.getType())} {
    @for(Attribute attr : node.attributes())@template.pattern.PatternAttribute(node = node, attribute = attr)
    @endfor

    @for(PatternLink link : node.links())@template.pattern.PatternLink(link = link)
    @endfor
}
