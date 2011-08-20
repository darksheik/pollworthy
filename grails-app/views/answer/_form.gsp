<%@ page import="com.pollworthy.Answer" %>



<div class="fieldcontain ${hasErrors(bean: answerInstance, field: 'text', 'error')} ">
	<label for="text">
		<g:message code="answer.text.label" default="Text" />
		
	</label>
	<g:textField name="text" value="${answerInstance?.text}"/>
</div>

