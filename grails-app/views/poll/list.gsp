
<%@ page import="com.pollworthy.Poll" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'poll.label', default: 'Poll')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-poll" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-poll" class="content scaffold-list" role="main">
			<h1>Your Polls</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'poll.name.label', default: 'Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pollInstanceList}" status="i" var="pollInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pollInstance.id}">${fieldValue(bean: pollInstance, field: "name")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pollInstanceTotal}" />
			</div>
		</div>

		<div id="list-poll2" class="content scaffold-list" role="main">
			<h1>Other Users' Polls</h1>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'poll.name.label', default: 'Name')}" />
						<g:sortableColumn property="user" title="${message(code: 'poll.user.name', default: 'User')}" />
						
					</tr>
				</thead>
				<tbody>
				<g:each in="${pollNonUserList}" status="i" var="pollInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pollInstance.id}">${fieldValue(bean: pollInstance, field: "name")}</g:link></td>
					        <td><g:link controller="user" action="show" id="${pollInstance.user.id}">${pollInstance.user.name}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pollInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
