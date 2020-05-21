<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content="TechnoReach IT Solutions" />
    <meta name="description" content="TechnoReach IT Solutions" />
    <meta name="author" content="TechnoReach IT Solutions" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

  <title>Payment Review | TechnoReach IT Solutions</title>
    <link rel="canonical" href="https://technoreachit.com/payment-review.jsp">
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
        .tr-tw-6 { font-weight: 600 !important; }
        .button.bt-white { 
        	background: #ddd; 
        	color: #000; 
        	box-shadow: none; 
       	}
       	.button.bt-white:hover, .button.bt-white:focus { 
       		background: #333333; 
       		color: #fff; 
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
    <div class="row">
        <div class="col-sm-12 text-center tr-mt-10">
            <img src="images/logo.png" alt="logo" style="height: 75px;">
        </div>
    </div>
    <!-- Header End -->

    <div class="main-content">
        <!-- Review Payment Start -->
        <section class="tr-mtb-0 tr-mt-0">
            <div class="container">
                <div class="row">
                	<div class="col-md-2 hidden-xs">&nbsp;</div>
                    <div class="col-md-8 col-xs-12 tr-mt-0">
                        <section class="tr-our-info overview-block-ptb">
                            <div class="row">
                                <div class="col-xs-12 text-center">
                                	<h1 style="font-size:35px;" class="tr-font-blue">Please Review Before Paying</h1>
                                </div>
                                <div class="col-xs-12">
                                	<h2 style="font-size:16px;"><strong>Transaction Details</strong></h2>
                                </div>
                                <div class="col-xs-12">
                                	<table class="table table-bordered tr-font-black tr-tw-6">
                                		<tr>
                                			<td style="width:30%;">Description</td>
                                			<td>${transaction.description}</td>
                                		</tr>
                                		<tr>
                                			<td>Total Amount in USD</td>
                                			<td>${transaction.amount.total}</td>
                                		</tr>
                                	</table>
                                </div>
                                <div class="col-xs-12">
                                	<h2 style="font-size:16px;"><strong>Payer Information</strong></h2>
                                </div>
                                <div class="col-xs-12">
                                	<table class="table table-bordered tr-font-black tr-tw-6">
                                		<tr>
                                			<td style="width:30%;">First Name</td>
                                			<td>${payer.firstName}</td>
                                		</tr>
                                		<tr>
                                			<td>Last Name</td>
                                			<td>${payer.lastName}</td>
                                		</tr>
                                		<tr>
                                			<td>Mobile</td>
                                			<td>${param.mobileNumber}</td>
                                		</tr>
                                		<tr>
                                			<td>E-mail</td>
                                			<td>${payer.email}</td>
                                		</tr>
                                	</table>
                                </div>
                                <div class="col-xs-12">
                                	<h2 style="font-size:16px;"><strong>Shipping Address</strong></h2>
                                </div>
                                <div class="col-xs-12">
                                	<table class="table table-bordered tr-font-black tr-tw-6">
                                		<tr>
                                			<td style="width:30%;">Recipient Name</td>
                                			<td>${shippingAddress.recipientName}</td>
                                		</tr>
                                		<tr>
                                			<td>Line 1</td>
                                			<td>${shippingAddress.line1}</td>
                                		</tr>
                                		<tr>
                                			<td>Line 2</td>
                                			<td>${shippingAddress.line2}</td>
                                		</tr>
                                		<tr>
                                			<td>City</td>
                                			<td>${shippingAddress.city}</td>
                                		</tr>
                                		<tr>
                                			<td>State</td>
                                			<td>${shippingAddress.state}</td>
                                		</tr>
                                		<tr>
                                			<td>Country Code</td>
                                			<td>${shippingAddress.countryCode}</td>
                                		</tr>
                                		<tr>
                                			<td>Postal Code</td>
                                			<td>${shippingAddress.postalCode}</td>
                                		</tr>
                                	</table>
                                </div>
                                <div class="col-xs-12 text-center">
                                 <form action="<%=request.getContextPath()%>/execute_payment" method="post">
                                  <input type="hidden" name="paymentId" value="${param.paymentId}" />
                                     <input type="hidden" name="PayerID" value="${param.PayerID}" />
                                     <input type="hidden" name="mobilenumber" value="${param.mobileNumber}" />
                                <button name="submit" type="submit" value="Send" class="button tr-mb-50">Pay Now</button>
                                 </form>
                                 <a href="<%=request.getContextPath()%>/our-payments.jsp" class="button bt-white tr-mb-50">Back</a>
                                </div>
                            </div>
                           
                        </section>
                    </div>
                    <div class="col-md-2 hidden-xs">&nbsp;</div>
                </div>
            </div>
        </section>
        <!-- Review Payment End -->
    </div>
    
    <div class="footer">
        <!-- Footer Start -->
        <footer class="tr-footer white-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <div class="footer-info tr-mt-0 tr-mb-0">
                            <img id="logo_img" class="img-responsive center-block tr-mb-10 wow zoomIn" data-wow-duration="1s" src="images/logo-footer.png" alt="">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="footer-copyright tr-mb-10">Copyright &copy; 2019 <span class="tr-font-blue">TechnoReach</span> All Rights Reserved </div>
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