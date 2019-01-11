<jsp:useBean id="manageCgu" scope="session" class="fr.paris.lutece.plugins.cgu.web.CguJspBean" />
<% String strContent = manageCgu.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
