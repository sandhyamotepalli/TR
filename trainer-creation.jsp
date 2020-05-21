<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="java.util.ArrayList" %>
     <%@ page import="beans.*" %>
     <%@page import="daos.*"%>
    <%
    AdminDao adminDao = new AdminDao();
	AdminBean adminBean = new AdminBean();
	AdminBean trainingsBean = new AdminBean();
	ArrayList<AdminBean> trainerDetails = adminDao.getAllTrainersByStatus();
	ArrayList<AdminBean> trainingDetails = adminDao.getAllTrainings();

    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content="TechnoReach IT Solutions" />
    <meta name="description" content="TechnoReach IT Solutions" />
    <meta name="author" content="TechnoReach IT Solutions" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

    <title>Trainer Creation | TechnoReach IT Solutions</title>
    <link rel="canonical" href="https://technoreachit.com/trainer-creation.jsp">
    <!-- Favicon -->
    <link rel="shortcut icon" href="images/favicon.ico" />
    <!-- Google Fonts -->
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700&amp;Raleway:300,400,500,600,700,800,900">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- owl-carousel -->
    <link rel="stylesheet" type="text/css" href="css/owl-carousel/owl.carousel.css" />
    <!-- Font Awesome -->
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
    <!-- Magnific Popup -->
    <link rel="stylesheet" type="text/css" href="css/magnific-popup/magnific-popup.css" />
    <!-- Animate -->
    <link rel="stylesheet" type="text/css" href="css/animate.css" />
    <!-- Style -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Responsive -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Style customizer (Remove these two lines please) -->
    <link rel="stylesheet" href="javascript:void(0)" data-style="styles">
    <link rel="stylesheet" href="css/style-customizer.css" />
    <!-- custom style -->
    <link rel="stylesheet" href="css/custom.css" />
    <style>
        .info-share {
            margin: 0px 0 0;
            padding: 0px 0 0;
            border-top: 0px solid #ededed;
            text-align: center;
        }
        .footer-copyright {
            border-top: 0px solid #ededed;
        }
        .contact-form .textarea textarea {
		    padding: 15px 0 20px 10px;
		    margin-bottom: 20px;
		}
		.contact-form .select select {
		    padding: 10px 0 20px 20px;
		    margin-bottom: 20px;
		}
    </style>
    <!-- Google Tag Manager -->
    <script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
        new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
        j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
        'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
        })(window,document,'script','dataLayer','GTM-NK6BZ3D');</script>
    <!-- End Google Tag Manager -->
<script language="javascript" type="text/javascript">
function changeStatus(tid,status,email)
{
	if(confirm("Are you sure to delete this trainer, click ok or cancel to continue.")){
		document.getElementById("tid").value = tid;
    	document.getElementById("tstatus").value = status;
    	document.getElementById("temail").value = email;
    	document.delform.method="post";
    	document.delform.submit();
	}else{
		var rcp = '<%=request.getContextPath()%>';
        window.open(rcp+"/trainer-creation.jsp","_self");
	}
}
</script>
</head>

<body>
    <!-- Google Tag Manager (noscript) -->
    <noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-NK6BZ3D"
        height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
        <!-- End Google Tag Manager (noscript) -->
        
    <!-- Loading Start -->
    <div id="loading" class="green-bg">
        <div id="loading-center">
            <div class="boxLoading"></div>
        </div>
    </div>
    <!-- Loading End -->

    <!-- Header Start -->
    <header id="header-wrap" data-spy="affix" data-offset-top="55">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <nav class="navbar navbar-default">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="javascript:void(0)">
                                <img src="images/logo.png" alt="logo">
                            </a>
                        </div>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav navbar-right" id="top-menu">
                                <li><a href="<%=request.getContextPath()%>/index-admin.jsp">Home</a></li>
                                <li><a href="<%=request.getContextPath()%>/our-trainings-admin.jsp">Trainings</a></li>
                                <li><a href="<%=request.getContextPath()%>/careers-admin.jsp">Careers</a></li>
                                <li class="active"><a href="<%=request.getContextPath()%>/trainer-creation.jsp">Trainer Creation</a></li>
                                <li><a href="<%=request.getContextPath()%>/student-creation.jsp">Student Creation</a></li>
                                 <li><a href="<%=request.getContextPath()%>/trainings-content-admin.jsp">Training Content</a></li>
                                <li><a href="<%=request.getContextPath()%>/course-videos-admin.jsp">Course Videos</a></li>
                                <li><a href="<%=request.getContextPath()%>/download-excel.jsp">Workshop Users</a></li>
                                <li><a href="<%=request.getContextPath()%>/change-password.jsp">Change Password</a></li>
                                <li><a href="<%=request.getContextPath()%>/Logout">Logout</a></li>
                            </ul>
                        </div>
                        <!-- /.navbar-collapse -->
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- Header End -->

    <!-- Intro Start -->
    <section class="tr-breadcrumb overview-block-pt text-center tr-bg tr-bg-fixed tr-over-black-90 tr-box-shadow" style="background: url(images/screens-images/ourtrainings-bg.jpg);" >
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="heading-title tr-breadcrumb-title tr-mtb-100">
                            <h1 class="title tr-tw-8 tr-font-white">Trainer Creation</h1>
                            <div class="divider white"></div>
                        </div>
                    <ul class="breadcrumb">
                        <li><a href="<%=request.getContextPath()%>/index-admin.jsp"><i class="fa fa-home"></i> Home</a></li>
                        <li class="active">Trainer Creation</li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
    <!-- Intro End -->

    <div class="main-content">
        <!-- Our Clients Start -->
        <section class="overview-block-ptb white-bg">
            <div class="container">
               <div class="row tr-mt-20">
                    <div class="contact-form">
                        <div class="">
                             <form name="trainerform" id="trainerform" class="form-horizontal"  method="post" action="<%=request.getContextPath()%>/CreateTrainerService">
                                <div class="section-field col-md-4 col-xs-12">
                                    <input type="text" placeholder="Name" name="name" required>
                                </div>
                                <div class="section-field col-md-4 col-xs-12">
                                    <input type="text" placeholder="E-Mail" name="email" required>
                                </div>
                                <div class="section-field select col-md-4 col-xs-12">
                                        <select name="technology" required>
                                        <option value="">--Select--</option>
                                        <%
                    if(trainingDetails != null && trainingDetails.size() > 0){
								for(int i=0;i<trainingDetails.size();i++)
								{
									 if (trainingDetails.get(i) != null)
									{
										 
										 trainingsBean=new AdminBean();
										   	
										 trainingsBean = trainingDetails.get(i);
										 
											 if (trainingsBean.getTrainingid() != 0)
											 {%>
	            		                         <option value="<%=trainingsBean.getTrainingid()%>"><%=trainingsBean.getCoursename()%></option>
									      <%  }
							            }
									 trainingsBean = null;
					 	          }
                        	 }
						  %>
                                    </select>
                                </div>
                                <div class="section-field col-md-12 col-xs-12">
                                	<button name="submit" type="submit" value="SEnd" class="button pull-right tr-mb-20">Submit</button>
                                </div>
                            </form>
                        </div>
                        <div class="section-field col-md-12 col-xs-12 creationtable">
                        	<h5 class="tr-font-blue tr-mb-10"><i class="fa fa-list"></i> Trainers List</h5>
                            <table class="table table-bordered table-responsive">
                            	<thead>
                            		<tr>
                            			<th>S.No</th>
                            			<th>Name</th>
                            			<th>E-mail</th>
                            			<th>Technologies</th>
                            			<th>Actions</th>
                            		</tr>
                            	</thead>
                            	<tbody>
                            	  <%
                              if(trainerDetails != null && trainerDetails.size() > 0){
								for(int i=0;i<trainerDetails.size();i++)
								{
									 if (trainerDetails.get(i) != null)
									{
										 
										 adminBean=new AdminBean();
										   	
										 adminBean = trainerDetails.get(i);
										 
											 if (adminBean.getTrainerid() != 0)
											 {%>
                            		<tr>
                            			<td><%=i+1%></td>
                            			<td><%=adminBean.getTrainername()%></td>
                            			<td><%=adminBean.getTraineremail()%></td>
                            			<td><%=adminBean.getTechnology()%></td>
                            			<td>
                            			<button style="margin-bottom: 5px;" class="btn btn-sm btn-danger" onclick="changeStatus('<%=adminBean.getTrainerid()%>','deactive','<%=adminBean.getTraineremail()%>')">Delete</button>
                            			</td>
                            		</tr>
                            			 
									      <%  }
							            }
									 adminBean = null;
					 	          }
                         }else{%>
                                  <tr>
                            			<td>No Records found</td>
                            		</tr>
                           <% }
						   %>
                            	</tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Our Clients Questions End -->
    </div>
    <form name="delform" id="delform" action="<%=request.getContextPath()%>/ChangeTrainerStatusService">
     <input type="hidden" name="trainerid" id="tid">
     <input type="hidden" name="status" id="tstatus">
     <input type="hidden" name="email" id="temail">
    </form>
   <div class="footer">
        <!-- Footer Start -->
        <footer class="tr-footer white-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <div class="footer-info tr-mt-50 tr-mb-30">
                            <img id="logo_img" class="img-responsive center-block tr-mb-10 wow zoomIn" data-wow-duration="1s" src="images/logo-footer.png" alt="">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <ul class="info-share">
                            <li><a href="http://linkedin.com/in/technoreach-it-solutions-511716141" target="_blank"><i class="fa fa-linkedin"></i></a></li>
                            <li><a href="https://www.facebook.com/TechnoReach-It-Solutions-Pvt-Ltd-1188702097835525/?ref=bookmarks" target="_blank"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="https://twitter.com/@technoreachit" target="_blank"><i class="fa fa-twitter"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="footer-copyright tr-ptb-20">Copyright &copy; 2019 <span id="copyright"><a href="http://technoreachit.com" class="text-green">TechnoReach.</a> All Rights Reserved </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer End -->
    </div>

    <!-- back-to-top -->
     <div id="back-to-top">
        <a class="top" id="top" href="#top"> <i class="fa fa-arrow-up"></i> </a>
    </div>
    <!-- back-to-top End -->

    <!-- jQuery -->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <!-- bootstrap -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <!-- owl-carousel -->
    <script type="text/javascript" src="js/owl-carousel/owl.carousel.min.js"></script>
    <!-- Counter -->
    <script type="text/javascript" src="js/counter/jquery.countTo.js"></script>
    <!-- Jquery Appear -->
    <script type="text/javascript" src="js/jquery.appear.js"></script>
    <!-- Magnific Popup --> 
    <script type="text/javascript" src="js/magnific-popup/jquery.magnific-popup.min.js"></script>
    <!-- Retina -->
    <script type="text/javascript" src="js/retina.min.js"></script>
    <!-- wow -->
    <script type="text/javascript" src="js/wow.min.js"></script>
    <!-- Countdown -->
    <script type="text/javascript" src="js/jquery.countdown.min.js"></script>
    <!-- Custom -->
    <script type="text/javascript" src="js/custom.js"></script>
    <!-- Style Customizer --> 
    <script type="text/javascript" src="js/style-customizer.js"></script> 
</body>

</html>