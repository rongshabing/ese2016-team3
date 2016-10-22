<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<script>
	// Validate the email field
	$(document).ready(function() {
		$("#field-email").focusout(function() {
			var text = $(this).val();
			$.post("/signup/doesEmailExist", {email: text}, function(data){
				if(data){
					alert("This username is taken. Please choose another one!");
					$("#field-email").val("");
				}
			});
		});
	});
</script>

<pre>
	<a href="/">Home</a>   &gt;   Sign up</pre>

<h1>Sign up</h1>
<form:form id="signupForm" method="post" modelAttribute="signupForm"
	action="signup">
	<fieldset>
		<legend>Enter Your Information</legend>

		<table>

			<tr>
				<td class="signupDescription"><label for="field-firstName">First Name:</label></td>
				<td><form:input path="firstName" id="field-firstName" /> <form:errors
						path="firstName" cssClass="validationErrorText" /></td>
			</tr>

			<tr>
				<td class="signupDescription"><label for="field-lastName">Last Name:</label></td>
				<td><form:input path="lastName" id="field-lastName" /> <form:errors
						path="lastName" cssClass="validationErrorText" /></td>
			</tr>

			<tr>
				<td class="signupDescription"><label for="field-password">Password:</label></td>
				<td><form:input path="password" id="field-password"
						type="password" /> <form:errors path="password"
						cssClass="validationErrorText" /></td>
			</tr>

			<tr>
				<td class="signupDescription"><label for="field-email">Email:</label></td>
				<td><form:input path="email" id="field-email" /> <form:errors
						path="email" cssClass="validationErrorText" /></td>
			</tr>

			<tr>
				<td class="signupDescription"><label for="field-gender">Gender:</label></td>
				<td><form:select path="gender">
						<form:option value="FEMALE" label="Female" />
						<form:option value="MALE" label="Male" />
						<form:option value="OTHER" label="Other"/>
					</form:select></td>
			</tr>					
		</table>
		<br />
		<input type="checkbox" id="premiumUser">Do you want to sign up as a Premium User for only 5$ per month?
		<br>
		<table id=creditcardForm>
			<tr class="creditcardInfo">
				<td style="display:none;">
				<td class="signupDescription"><label for="field-creditcardNumber">Credit card number:</label></td>
				<td><form:input path="creditCard" id="field-creditcardNumber" /> <form:errors
						path="creditCard" cssClass="validationErrorText" /></td>
			</tr>
		</table>
		<br />		
		<button type="submit">Sign up</button>	
	</fieldset>
</form:form>

<script>
$("#premiumUser").change(function(){
	var self = this;
	$("#creditcardForm tr.creditcardInfo").toggle(self.checked);
}).change();
</script>

<c:import url="template/footer.jsp" />