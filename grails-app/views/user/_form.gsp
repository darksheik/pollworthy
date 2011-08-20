<%@ page import="com.pollworthy.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="user.comments.label" default="Comments" />
		
	</label>
	<g:select name="comments" from="${com.pollworthy.Comment.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.comments*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${userInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'polls', 'error')} ">
	<label for="polls">
		<g:message code="user.polls.label" default="Polls" />
		
	</label>
	<g:select name="polls" from="${com.pollworthy.Poll.list()}" multiple="multiple" optionKey="id" size="5" value="${userInstance?.polls*.id}" class="many-to-many"/>
</div>

