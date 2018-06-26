<!DOCTYPE html>
<html>
	<head>
		<title>User Form</title>
		<style>
			h1{
			text-align:center;
			color:black;
			padding:20px;
			font-family:verdana;
			background-color:black;
			}
			td{
			color:white;
			}
		</style>
	</head>
	<%
		
	%>
	
	<body>
		<form action="update" method="post" >
			<h1 style="color:darkorange;text-decoration:underline"><i>User Details</i></h1>
			<hr>
			<table>
			<tr>
			<td>FirstName:</td>
			<td><input type="text" name="fname" value=<%= %> autofocus required></td><!--fname is field name;all field names should be different-->
			<td></td><td></td>
			</tr>
			<tr>
			<td>LastName:</td>
			<td><input type="text" name="lname" required></td>
			<td></td><td></td>
			<tr>
			<td>Gender:</td><td><input type="radio" name="gender" value="male" required>Male</input>
			<input type="radio" name="gender" value="female">Female
			<input type="radio" name="gender" value="other">Other</td>
			<td></td><td></td>
			</tr>
			<tr>
			<td>Date Of Birth:</td>
			<td><input type="date" name="da" required></td>
			<td><input type="button" onclick="alert('Date of birth field is made compulsory to distinguish two students on DOB basis with all other personal nformation credentials same!!')" value="Why?"></td><td></td>
			</tr>
			<tr>
			<td>Mobile:</td><td><input type="tel" name="teli" required></td>
			<td></td><td></td>
			</tr>
			<tr>
			<td>Course:</td>
			<td><input list="course" name="course" required>
				<datalist id="course">
					<option value="B.Tech">
					<option value="B.E.">
					<option value="B.Sc.">
					<option value="B.A.">
					<option value="B.S.">
				</datalist>
			</td>
			<td>Semester:</td>
			<td><input type="number" name="sem" min="1" max="8"></td>
			</tr>
			<tr>
			<td>GitHub Link:</td>
			<td><input type="url" name="gitID"></td>
			<td></td><td></td>
			</tr>
			<tr>
			<td>Address:</td><td><input type="text" name="aname" required></td>
			<td>City:</td><td><select name="city">
			<option value="Lucknow">Lucknow</option>
			<option value="kanpur">Kanpur</option>
			<option value="gaziabad">Gaziabad</option>
			<option value="albd">Allahabad</option>
			<option value="banaras">Banaras</option>
			<option value="agra">Agra</option>
			<option value="dehradun">Dehradun</option>
			<option value="kolkata">Kolkata</option>
			<option value="chennai">Chennai</option>
			<option value="delhi">Delhi</option>
			<option value="mumbai">Mumbai</option>
			<option value="bangalore">Bangalore</option>
			<option value="hdbd">Hyderabad</option>
			</select></td>
			</tr>
			<tr>
			<td>State:</td><td><select name="state">
			<option value="up">Uttar Pradesh</option>
			<option value="manali">Himachal Pradesh</option>
			<option value="srinagar">Jammu and Kashmir</option>
			<option value="assam">Assam</option>
			<option value="megh">Meghalay</option>
			<option value="guj">Gujarat</option>
			<option value="dehradun">Uttarakhand</option>
			<option value="kolkata">West Bengal</option>
			<option value="chennai">Tamil Nadu</option>
			<option value="delhi">Delhi</option>
			<option value="mumbai">Maharashtra</option>
			<option value="bangalore">Karnataka</option>
			<option value="hdbd">Andhra Pradesh</option>
			</select></td>
			<td>Pin Code:</td><td><input type="text" name="pinCode" required></td>
			</tr>
			<tr><td></td><td></td><td></td><td></td></tr>
			</table>
			<br>
			<hr>
			<br><p align="center">
			<input type="submit" value="next"><hr>
		</form>
	</body>
</html>