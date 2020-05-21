<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title></head>
<!-- custom-theme -->
<link rel="shortcut icon" href="../images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700&amp;Raleway:300,400,500,600,700,800,900">
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">   
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="canonical" href="https://technoreachit.com/studentForm/student-form.jsp">
</head>
<body>
<div class="studentform-btn">
	<a class="studebt-back-btn" href="<%=request.getContextPath()%>/our-trainings.jsp">Back To Home</a>
</div>
<!-- section -->
<section class="register">
	<div class="register-full">
		<div class="register-left">
			<div class="register">
				<div class="logo">
					<img src="images/logo.png" style="width:250px;" />
				</div>
				<p>
					<span style="color: #e4ff00;" class="address-header"><i class="fa fa-map-marker"></i> Classroom trainings location</span><br />
					TechnoReach IT Solutions Pvt Ltd, 6-2-663/4 , Opp to shadan college, 
					Chintal basti main road, Khairtabad , Hyderabad.
					<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3807.0438095598593!2d78.45749761487669!3d17.40968508806524!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zMTfCsDI0JzM0LjkiTiA3OMKwMjcnMzQuOSJF!5e0!3m2!1sen!2sin!4v1584090099755!5m2!1sen!2sin" width="100%" height="250" frameborder="0" style="border:1px solid #fff;margin-top:0.5em;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
				</p>
				<p>
					<span class="address-header"><i class="fa fa-phone"></i> Lets Talk</span><br />
					 040-6161 6626
				</p>
				<p>
					<span class="address-header"><i class="fa fa-envelope"></i> E-mail ID</span><br />
					info@technoreachit.com
				</p>
			</div>
		</div>
		<div class="register-right">
			<div class="register-in">
				<h2><span class="fa fa-pencil"></span> Register Here</h2>
				<div class="register-form">
					<form action="<%=request.getContextPath()%>/StudentFormService" method="post">
						<div class="fields-grid">
							<div class="styled-input agile-styled-input-top">
								<input type="text" name="name" required pattern="[a-zA-Z ]*$" title="Name should be alphabets"> 
								<label>Student Name*</label>
								<span></span>
							</div>
							<div class="styled-input agile-styled-input-top">
								<input type="text" name="lastname" required pattern="[a-zA-Z ]*$" title="Name should be alphabets"> 
								<label>Last Name*</label>
								<span></span>
							</div>
							<div class="styled-input">
								<input type="tel" name="mobilenumber" required pattern="[0-9]{10}" title="Should be 10 digits">
								<label>Mobile*</label>
								<span></span>
							</div>
							<div class="styled-input">
								<input type="email" name="email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="Please enter valid emailid" >
								<label>Email*</label>
								<span></span>
							</div>
							<div class="styled-input">
								<input type="text" name="college" required  title="Please enter College Name" >
								<label>College/University Name*</label>
								<span></span>
							</div>
							<div class="styled-input">
								<input type="text" name="qualification" required  title="Please enter valid Qualification" >
								<label>Qualification*</label>
								<span></span>
							</div>
							<div class="styled-input agile-styled-input-top">
								<select name="interestedin" required>
								    <option value="">Interested In*</option>
									<option value="Information Technology/Software Job">Information Technology/Software Job</option>
									<option value="M.Tech">M.Tech</option>
									<option value="MBA">MBA</option>
									<option value="Overseas Education">Overseas Education</option>
								</select>
								<span></span>
							</div>
							<div class="styled-input">
								<textarea name="goal" required></textarea>
								<label style="color: #fff;">Your Future Goal</label>
								<span></span>
							</div>
							<div class="clear"> </div>
						</div>
						<div style="text-align: center;">
							<input type="submit" value="SUBMIT">
						</div>
					</form>
				</div>
			</div>
			<div class="clear"> </div>
		</div>
	<div class="clear"> </div>
	</div>
</section>
</body>	

</html>