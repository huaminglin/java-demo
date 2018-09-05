Hello world: 
<%
out.println(request.getUserPrincipal());
out.println(request.getAuthType());
out.println(request.getRemoteUser());
out.println(request.isUserInRole("admin"));
out.println(request.isUserInRole("admin-role"));
%>

