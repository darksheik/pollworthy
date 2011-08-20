<%@ page import="com.pollworthy.Comment" %>



<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="comment.content.label" default="Content" />
		
	</label>
	<g:textField name="content" value="${commentInstance?.content}"/>
</div>

