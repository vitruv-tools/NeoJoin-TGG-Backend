@import tools.vitruv.neojoin.tgg.operators.expressions.ValueExpression
@import tools.vitruv.neojoin.tgg.operators.expressions.VariableExpression
@import tools.vitruv.neojoin.tgg.operators.expressions.ConstantExpression
@param ValueExpression expression
@if(expression instanceof ConstantExpression constant)${constant.value()}
@elseif(expression instanceof VariableExpression variable)<${variable.name()}>
@endif