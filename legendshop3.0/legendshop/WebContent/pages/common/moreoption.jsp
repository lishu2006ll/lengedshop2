<%@ page language="java" isErrorPage="true" contentType="text/html;charset=UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<%@include file='/pages/common/taglib.jsp'%>
<%@include file='localeBundle.jsp'%>
<table cellSpacing="0" cellPadding="0" width="955px" align="center">
	<tr>
		<td>
			<BLOCKQUOTE>
				<OL>
					<UL type="square">
						<c:forEach items="${User_Messages.callBackList}" var="callback">	
							<li>
							<c:choose>
								<c:when test="${callback.callBackHref == null}">
									${callback.callBackTitle}
								</c:when>
								<c:otherwise><a href="${pageContext.request.contextPath}/${callback.callBackHref}"><b>${callback.callBackTitle}</b></a></c:otherwise>
							</c:choose>
								 ${callback.callBackDesc}
							</li>
						</c:forEach>
						<br></br>
						<LI>
							<A href="${pageContext.request.contextPath}/all${applicationScope.WEB_SUFFIX}"><b><fmt:message key="advance.search"/></b></A> - <fmt:message key="search.all.hint"/>
						</LI>
						<LI>
							<A href="${pageContext.request.contextPath}/index${applicationScope.WEB_SUFFIX}"><b><fmt:message key="shop.index"/></b></A> - <fmt:message key="shop.index.desc"/>
						</LI>
						<LI>
							<A href="${pageContext.request.contextPath}/reg${applicationScope.WEB_SUFFIX}"><b><fmt:message key="regFree"/></b></A> - <fmt:message key="regFree.hint"/>
						</LI>
						<LI>
							<A href="${pageContext.request.contextPath}/login${applicationScope.WEB_SUFFIX}"><b><fmt:message key="login"/></b></A> - <fmt:message key="logon.hint"/>
						</LI>
						<LI>
							<A href="${applicationScope.LEGENDSHOP_DOMAIN_NAME}/club"><b><fmt:message key="bbs"/></b></A> - <fmt:message key="bbs.desc"/>
						</LI>
					</UL>
				</OL>
				<P></P>
			</BLOCKQUOTE>
		</td>
	</tr>
</table>