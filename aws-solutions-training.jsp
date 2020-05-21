<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="java.util.ArrayList" %>
     <%@ page import="beans.*" %>
     <%@page import="daos.*"%>
    <%
    AdminDao adminDao = new AdminDao();
    AdminBean adminBean = new AdminBean();
	AdminBean contentBean = new AdminBean();
	    contentBean = adminDao.getTrainingContentDetailsByTrainingname("AWS Solutions Architect");
	    adminBean = adminDao.getTrainingDetailsById(contentBean.getTrainingid());
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AWS Solutions Architect Training | AWS certification training</title>
    <meta property="og:title" content="AWS Solutions Architect Training | AWS certification training"/>
	<meta property="og:url" content="https://www.technoreachit.com/aws-solutions-training.jsp"/>
	<meta property="og:description" content="Master AWS Solutions Architect Certification Course with live projects. Contact us for more details."/>
    <meta name="keywords" content="TechnoReach IT Solutions" />
    <meta name="description" content="TechnoReach IT Solutions" />
    <meta name="author" content="TechnoReach IT Solutions" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

    <link rel="canonical" href="https://technoreachit.com/aws-solutions-training.jsp"> 
    <!-- Favicon -->
    <link rel="shortcut icon" href="../images/favicon.ico" />
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
    <link rel="stylesheet" href="../css/style.css">
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
        .tr-blog-meta .col-md-6{
        	padding-left:0px;
        	padding-right:0px;
        }
    </style>
    <!-- Google Tag Manager -->
    <script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
        new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
        j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
        'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
        })(window,document,'script','dataLayer','GTM-NK6BZ3D');</script>
    <!-- End Google Tag Manager -->
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
                                <img src="../images/logo-white.png" alt="logo">
                            </a>
                        </div>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                              <ul class="nav navbar-nav navbar-right" id="top-menu">
                               <li><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
                                <li><a href="<%=request.getContextPath()%>/about-us.jsp">About Us</a></li>
                                <li><a href="<%=request.getContextPath()%>/our-services.jsp">Our Services</a></li>
                                <li><a href="<%=request.getContextPath()%>/our-clients.jsp">Our Clients</a></li>
                                 <li class="tr-widget-menu dropdown dropbtn">
                                    <a href="<%=request.getContextPath()%>/our-trainings.jsp">Our Trainings</a>
                                    <div class="dropdown-content">
                                        <a href="<%=request.getContextPath()%>/studentForm/student-form.jsp">classroom/workshop training</a>
                                    </div>
                                </li>
                                <li><a href="<%=request.getContextPath()%>/our-team.jsp">Our Team</a></li>
                                <li><a href="<%=request.getContextPath()%>/careers.jsp">Careers</a></li>
                                <li><a href="<%=request.getContextPath()%>/contact-us.jsp">Contact Us</a></li>
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
    <section class="tr-breadcrumb tr-breadcrumb-course text-center overview-block-pt tr-bg tr-bg-fixed tr-over-black-90 tr-box-shadow">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="heading-title tr-breadcrumb-title tr-mtb-50">
                        <div class="row">
                            <div class="col-md-8 text-left">
                                <div class="row">
                                    <div class="col-md-12 maincourse-wrap">
                                        <h1 class="tr-font-white"><%=adminBean.getCoursename()%>&nbsp;Training</h1>
                                       <!-- <h6 class="tr-font-white tr-tw-5">AWS training and certification course masters you in AWS Cloud.</h6> -->
                                        <ul class="social-icons list-items">
                                            <li>
                                                <span class="stars" data-rating="5" data-num-stars="5"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span> (5.0)
                                            </li>
                                            <li>
                                                <i class="fa fa-google"></i> 4.1/5                                
                                            </li>
                                            <li>
                                                <i class="fa fa-facebook"></i> 4.1/5                                
                                            </li>
                                            <li>
                                                <i class="fa fa-star trust-pilot"></i> 4.3/5                                
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="row tr-mt-20 tr-coursedetails">
                                    <div class="col-md-3 col-xs-6 tr-mb-20 tr-course-border">
                                        <h6 class="tr-font-white maintext">Course Duration</h6>
                                        <h6 class="tr-font-white subtext"><%=adminBean.getDuration()%> Hours</h6>
                                    </div>
                                    <div class="col-md-2 col-xs-6 tr-mb-20 tr-course-border">
                                        <h6 class="tr-font-white maintext">Live Projects</h6>
                                        <h6 class="tr-font-white subtext">02</h6>
                                    </div>
                                    <div class="col-md-3 col-xs-6 tr-mb-20 tr-course-border">
                                        <h6 class="tr-font-white maintext">Certification Pass</h6>
                                        <h6 class="tr-font-white subtext">Guaranteed</h6>
                                    </div>
                                    <div class="col-md-4 col-xs-6 tr-mb-20 tr-course-border">
                                        <h6 class="tr-font-white maintext">Training Format</h6>
                                        <h6 class="tr-font-white subtext">online, Classroom & Corporate Training</h6>
                                    </div>
                                     <%if(adminBean.getLink() != null && !adminBean.getLink().equalsIgnoreCase("")){%>
                                     <div class="col-xs-12 tr-mb-20">
                                        <a href="<%=adminBean.getLink()%>" target="_blank" class="button button-icon" style="padding: 5px 20px; text-transform: none;">View Demo</a>
                                    </div>
                                   <%}%>
                                </div>
                                
                            </div>
                            <div class="col-md-4 maincourse-wrap">
                                <img src="../images/courses/aws.jpg" />
                            </div>
                        </div>
                    </div>
                    <ul class="breadcrumb">
                        <li><a href="<%=request.getContextPath()%>/index.jsp"><i class="ion-home"></i> Home</a></li>
                        <li><a href="<%=request.getContextPath()%>/our-trainings.jsp"><i class="ion-home"></i> Courses</a></li>
                        <li class="active"><%=adminBean.getCoursename()%></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
    <!-- Intro End -->
    <div class="main-content">
         <!-- Our Trainings Start -->
         <section class="overview-block-ptb white-bg">
            <div class="container">
                <div class="row course-overview">
                    <div class="col-xs-12">
                        <h2 class="section-heading"><%=adminBean.getCoursename()%> Training Content</h2>
                        <h2 class="readmore small_desc_heading">
                          <pre><%=contentBean.getTrainingcontent()%></pre>
                        </h2>
                    </div>
                </div>
                <div class="row course-overview">
                    <div class="col-md-12">
                        <h2 class="section-heading">Key Features</h2>
                    </div>
                    <div class="row">
                        <div class="col-md-4 text-center keyfeatures-wrap tr-border-rightkey tr-mt-20 tr-mb-20">
                            <i class="fa fa-clock-o tr-font-light-blue"></i>
                            <h3><%=adminBean.getDuration()%> Hours of Learning</h3>
                            <p>online | Classroom | Corporate<br />Learning.</p>
                        </div>
                        <div class="col-md-4 text-center keyfeatures-wrap tr-border-rightkey tr-mt-20 tr-mb-20">
                            <i class="fa fa-graduation-cap tr-font-light-blue"></i>
                            <h3>Job Placement Assistance</h3>
                            <p>Based on performance of candidate in training batch, will be placed in our company projects.</p>
                        </div>
                        <div class="col-md-4 text-center keyfeatures-wrap tr-mt-20 tr-mb-20">
                            <i class="fa fa-book tr-font-light-blue"></i>
                            <h3>2+ Practical Hands-On Projects</h3>
                            <p>After course completion sample projects will be shared by trainers.</p>
                        </div>
                    </div>
                    <div class="col-md-12 hidden-xs"><hr /></div>
                    <div class="row">
                        <div class="col-md-2 text-center keyfeatures-wrap tr-mt-20 tr-mb-20 hidden-xs">&nbsp;</div>
                        <div class="col-md-4 text-center keyfeatures-wrap tr-border-rightkey tr-mt-20 tr-mb-20">
                            <i class="fa fa-calendar tr-font-light-blue"></i>
                            <h3>Flexible Schedules</h3>
                            <p>Weekdays, Weekend, Classroom batches only in hyderabad location are available.</p>
                        </div>
                        <div class="col-md-4 text-center keyfeatures-wrap tr-mt-20 tr-mb-20">
                            <i class="fa fa-phone tr-font-light-blue"></i>
                            <h3>Support</h3>
                            <p>Job support will be provided until student will work independently. Can be added in technical what sapp groups.</p>
                        </div>
                        <div class="col-md-2 text-center keyfeatures-wrap tr-mt-20 tr-mb-20 hidden-xs">&nbsp;</div>
                    </div>
                </div>
                <div class="row course-overview">
                    <div class="col-md-12">
                        <h2 class="section-heading">Modes of Training</h2>
                    </div>
                    <div class="col-md-4 tr-mb-20">
                        <div class="modeoftrain-wrap">
                            <h6 class="text-center">CLASS ROOM TRAINING</h6>
                            <ul class="list-info">
                                <li>Links / Blog link will be shared</li>
                                <li>Real time scenarios/ use cases will be covered in training</li>
                            </ul>
                            <div class="text-center tr-mt-30"><a href="<%=request.getContextPath()%>/contact-us.jsp" class="button" style="padding: 4px 10px; text-transform: none;">Contact Us</a></div>
                        </div>
                    </div>
                    <div class="col-md-4 tr-mb-20">
                        <div class="modeoftrain-wrap">
                            <h6 class="text-center">ONLINE TRAINING</h6>
                            <ul class="list-info">
                                <li>Daily assignment will be given</li>
                                <li>Daily session recording videos access will be given for 90 days</li>
                            </ul>
                            <div class="text-center tr-mt-30"><a href="<%=request.getContextPath()%>/contact-us.jsp" class="button" style="padding: 4px 10px; text-transform: none;">Contact Us</a></div>
                        </div>
                    </div>
                    <div class="col-md-4 tr-mb-20">
                        <div class="modeoftrain-wrap">
                            <h6 class="text-center">CORPORATE TRAINING</h6>
                            <ul class="list-info">
                                <li>Experienced corporate faculty is available</li>
                                <li>Interview questions, material will be provided in training</li>
                            </ul>
                            <div class="text-center tr-mt-30"><a href="<%=request.getContextPath()%>/contact-us.jsp" class="button" style="padding: 4px 10px; text-transform: none;">Contact Us</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Our Trainings Questions End -->
    </div>
    
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
    <script type="text/javascript">
        $(document).ready(function() {
            var showChar = 80; 
            var showtext = 200; 
            var ellipsestext = "...";
            var moretext = "Read more";
            var lesstext = "Read less";
    
            $('.more').each(function() {
                var content = $(this).html();
                if(content.length > showChar) {
                    var c = content.substr(0, showChar);
                    var h = content.substr(showChar, content.length - showChar);
                    var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span><span class="morecontent"><span>' + h + '</span> <a href="javascript:void(0);" class="morelink">' + moretext + '</a> </span>';
                    $(this).html(html);
                }
            });
    
            $('.readmore').each(function() {
                var content = $(this).html();
                if(content.length > showtext) {
                    var c = content.substr(0, showtext);
                    var h = content.substr(showtext, content.length - showtext);
                    var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span><span class="morecontent"><span>' + h + '</span> <a href="" class="morelink">' + moretext + '</a> </span>';
                    $(this).html(html);
                }
            });
    
            $(".morelink").click(function(){
                if($(this).hasClass("less")) {
                    $(this).removeClass("less");
                    $(this).html(moretext);
                } else {
                    $(this).addClass("less");
                    $(this).html(lesstext);
                }
                $(this).parent().prev().toggle();
                $(this).prev().toggle();
                return false;
            });
        });
    </script>
</body>


</html>