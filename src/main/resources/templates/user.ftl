<html>
<head>
    <#include "userstyle.ftl">
    <title>Welcome to Usertown!</title>
</head>
<body>
<h1>Welcome ${user.username} with the email address of ${user.email}!</h1>
<#if user.password??>
    <p>Your password is ${user.password}, which is kind of shit</p>
<#else>
    <p>You don't have a password set.</p>
</#if>

<p>Your account was created on ${user.created}, so you're like a second old</p>
<button id="coolButton">This is a button</button>
</body>
<#include "userscript.ftl">
</html>