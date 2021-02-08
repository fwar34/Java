<html>
<head>
    <title>Hello world - JSP</title>
</head>

<body>
    <%-- JSP Comment --%>
    <h1>Hello world!</h1>
    <p>
    <%
        out.println("Your IP address is ");
    %>
    <span style="color:red">
        <%= request.getRemoteAddr() %>
    </span>
    </p>
</body>
</html>