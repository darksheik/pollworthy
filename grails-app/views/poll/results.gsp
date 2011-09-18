
<%@ page import="com.pollworthy.Poll" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'poll.label', default: 'Poll')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-poll" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-poll" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form>
			<ol class="property-list poll">
			
				<g:if test="${pollInstance?.name}">
				<li class="fieldcontain">
						Poll # ${pollInstance?.id}: <B><I><g:fieldValue bean="${pollInstance}" field="name"/></span></I></B> 
                                        &nbsp;&nbsp;(by <g:link controller="user" action="show" id="${pollInstance.user.id}">
                                        <g:if test="${pollInstance?.user.id == session.user.id}">you!</g:if>
                                        <g:else>${pollInstance?.user.name}</g:else></g:link>)
					
				</li>
				</g:if>
			
				<g:if test="${pollInstance?.questions}">
				<li class="fieldcontain">
					<ol class="questions-poll">
						<g:each in="${pollInstance.questions.sort{it.id}}" var="q">
						   <li class="questioninpoll">${q.text}</li>

                                                   <g:if test="${q?.answers}">
                                                     <table>
                                                     <g:each in="${q?.answers.sort{it.id}}" var="a">
                                                       <tr><td>
                                                       ${a.text}
                                                       </td>
                                                       <td>
                                                       ${a?.responses.size()} of ${q?.answers.responses.flatten().size()}, <g:formatNumber number="${((a?.responses.size() / q?.answers.responses.flatten().size()) *100)}" type="number" maxFractionDigits="2" />%
                                                       </td>
                                                     </g:each>
                                                     </table>
                                                   </g:if>
						</g:each>
                                        </ol>
					
				</li>
				</g:if>
			
			</ol>

				<fieldset class="buttons">
					<g:hiddenField name="id" value="${pollInstance?.id}" />
                                <g:if test="${pollInstance?.user.id == session.user.id}">
					<g:link class="edit" action="edit" id="${pollInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</g:if>
		<g:actionSubmit class="saveanswers" action="saveanswers" value="${message(code: 'default.button.save.label', default: 'Save Answers!')}"  />
			<g:link controller="question" action="create" params="[pollid: pollInstance.id]">New Question</g:link>
                                </fieldset>
			</g:form>
		</div>
	</body>
</html>
