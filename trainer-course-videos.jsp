<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="java.util.ArrayList" %>
     <%@ page import="beans.*" %>
     <%@page import="daos.*"%>
    <%
    AdminDao adminDao = new AdminDao();
    AdminBean trainerDetails = new AdminBean();
    AdminBean videoBean = new AdminBean();
    ArrayList<AdminBean> videoDetails = (ArrayList<AdminBean>)request.getAttribute("videodetails");
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

    <title>Course Videos | TechnoReach IT Solutions</title>
    <link rel="canonical" href="https://technoreachit.com/trainer-course-videos.jsp">
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
var viewList = new XMLHttpRequest();
function getTrainingVideo(vid)
{
	var path = "<%=request.getContextPath()%>/GetVideoToViewFromStorage?videoid="+vid+"";
	viewList.open("post",path , true);
	viewList.send(null);
		
}
viewList.onreadystatechange = function(){
	if(viewList.readyState == 4){
		var output = viewList.responseText;
		if(output.charAt(0) === '<'){
			document.getElementById("videodiv").innerHTML = output;
		}else{
			alert(output);
			}
	}
	}
</script>

 <script language="javascript" type="text/javascript">
        var deleteList = new XMLHttpRequest();
        function deleteVideo(vid)
        {
            var path = "<%=request.getContextPath()%>/DeleteVideoService?videoid="+vid+"";
            deleteList.open("post",path , true);
            deleteList.send(null);
                
        }
        deleteList.onreadystatechange = function(){
            if(deleteList.readyState == 4){
                var output = deleteList.responseText;
                if(output.charAt(0) != '<'){
                    alert(output);
                    window.location.href = "<%=request.getContextPath()%>/TrainerCourseVideos";
                }else{
                    alert(output);
                    window.location.href = "<%=request.getContextPath()%>/TrainerCourseVideos";
                    }
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
                                <li><a href="<%=request.getContextPath()%>/index-trainer.jsp">Home</a></li>
                                <li class="active"><a href="<%=request.getContextPath()%>/TrainerCourseVideos">Course Videos</a></li>
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
                            <h1 class="title tr-tw-8 tr-font-white">Course Videos</h1>
                            <div class="divider white"></div>
                        </div>
                    <ul class="breadcrumb">
                        <li><a href="<%=request.getContextPath()%>/index-trainer.jsp"><i class="fa fa-home"></i> Home</a></li>
                        <li class="active">Course Videos</li>
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
	               <div class="col-xs-12 text-right tr-mb-20">
	               		<a class="button tr-mt-15" data-toggle="modal" data-target="#uploadvideo" style="cursor: pointer;">Upload Video</a>
	               </div>
                    <div class="col-md-3 col-xs-12 tr-mb-20 videolist">
						<div>
							<ul>
							<%
                    if(videoDetails != null && videoDetails.size() > 0){
								for(int i=0;i<videoDetails.size();i++)
								{
									 if (videoDetails.get(i) != null)
									{
										 
										 videoBean=new AdminBean();
										   	
										 videoBean = videoDetails.get(i);
										 
											 if (videoBean.getVideoid() != 0)
											 {%>
											 <li><a href="javascript:getTrainingVideo('<%=videoBean.getVideoid()%>');"><%=i+1%>. <%=videoBean.getVideoheading()%> (<%=videoBean.getBatchnumber()%>)</a><button style="margin-bottom: 5px;float:right;" class="btn btn-sm btn-danger" onclick="deleteVideo('<%=videoBean.getVideoid()%>')"><i class="fa fa-trash"></i></button></li>
									      <%  }
							            }
									 videoBean = null;
					 	          }
                         }else{%>
                          <li>Currently No Videos Available.</li>
                          <% }%>
							</ul>
						</div>
					</div>
                    <div class="col-md-9 col-xs-12">
						<div class="app-border">
							<div id="videodiv"></div>
						</div>
					</div>
                </div>
            </div>
        </section>
        <!-- Our Clients Questions End -->
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
	
	<!-- Modal Upload Video -->
	<div class="modal fade" id="uploadvideo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Upload Video</h4>
	      </div>
	      <div class="modal-body">
      		<div class="row tr-mt-20">
                <div class="contact-form">
                    <div class="">
                         <form class="form-horizontal" name="videoform" id="videoform"  method="post" action="<%=request.getContextPath()%>/CreateTrainerVideoService" enctype= "multipart/form-data">
                            <div class="section-field col-md-12 col-xs-12">
                                <input type="text" placeholder="Video Title" name="videotitle" required>
                            </div>
                            <div class="section-field col-md-12 col-xs-12">
                                <input type="file" placeholder="Video Title" name="video" required>
                            </div>
                             <div class="col-md-12 col-xs-12 section-field select">
                                    <select name="batch" required>
                                        <option value="">--Select--</option>
                                        <option>Batch 1</option>
                                        <option>Batch 2</option>
                                    </select>
                             </div>
                            <div class="section-field col-md-12 col-xs-12">
                            	<!-- <a class="button tr-mt-15" style="cursor: pointer;">Upload Video</a>  -->
                            	<button name="submit" type="submit" value="Send" class="button tr-mb-50">Submit</button>
                            </div>
                        </form>
                    </div>
                 </div>
             </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- Modal Upload Video -->
	
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