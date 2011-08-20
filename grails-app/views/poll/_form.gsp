<%@ page import="com.pollworthy.Poll" %>



<div class="fieldcontain ${hasErrors(bean: pollInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="poll.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${pollInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pollInstance, field: 'questions', 'error')} ">
	<label for="questions">
		<g:message code="poll.questions.label" default="Questions" />
		
	</label>
	<g:select name="questions" from="${com.pollworthy.Question.list()}" multiple="multiple" optionKey="id" size="5" value="${pollInstance?.questions*.id}" class="many-to-many"/>
</div>

