@import tools.vitruv.neojoin.tgg.transpiler.graph.Attribute
@import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGNode
@param TGGNode node
@param Attribute attribute
.${attribute.name()} @template.tgg.TGGLogicOperator(operator = attribute.operator(), node = node) @template.ValueExpression(expression = attribute.value())
