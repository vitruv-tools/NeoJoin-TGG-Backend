@import tools.vitruv.neojoin.tgg.operators.LogicOperator
@import tools.vitruv.neojoin.tgg.transpiler.graph.tgg.TGGNode
@param LogicOperator operator
@param TGGNode node
@if(operator == LogicOperator.Equals && node.isGreen()):=
@elseif(operator == LogicOperator.Equals && !node.isGreen()):
@elseif(operator == LogicOperator.NotEquals)!=
@elseif(operator == LogicOperator.LessThan)<
@elseif(operator == LogicOperator.LessEquals)<=
@elseif(operator == LogicOperator.MoreThan)>
@elseif(operator == LogicOperator.MoreEquals)>=
@endif