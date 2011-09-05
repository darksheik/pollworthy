<%@ page import="com.pollworthy.Poll" %>


Enter a name for your new poll!
<div class="fieldcontain ${hasErrors(bean: pollInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="poll.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${pollInstance?.name}"/>
</div>

