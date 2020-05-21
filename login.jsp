<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Techno Reach IT Solutions</title>
     <link rel="canonical" href="https://technoreachit.com/login.jsp">
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
    <link href="css/login.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700&amp;Raleway:300,400,500,600,700,800,900">
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
        
    <div class="row" style="height:100%;">
        <div class="col-md-6 col-xs-12" style="background:#42e695;height:100%;">
            <!--<span>US Admissions</span>-->
        </div>
        <div class="col-md-6 loginbtnclass" style="background:#3bb2b8;height:100%;">
            <!--<button class="btn btn-info signupbtnpos">Sign up</button>-->
        </div>
    </div>
    <div class="row">
        <div class="loginbg">
            <div class="row loginbodybg loginbtnclass">
               <form class="form-horizontal"  method="post" action="<%=request.getContextPath()%>/LoginService">
                <div class="col-xs-12 top-five bottom-fourty"><span class="loginheadtext"><img src="images/logo.png" style="width: 100%;" /></span></div>
                <div class="col-xs-12 top-five bottom-twenty"><input type="text" class="form-control" placeholder="User Name" name="username" required></div>
                <div class="col-xs-12 bottom-twenty"><input type="password"  class="form-control" placeholder="Password" name="password" required></div>
                <div class="col-xs-12 bottom-fourty"><button class="button">Sign In</button></div>
                </form>
                <div class="col-xs-12 text-center bottom-ten signsubtext text-left"><a href="<%=request.getContextPath()%>/index.jsp"><i class="fa fa-hand-o-left"></i>Go Back Home</a></div>
                <!-- <div class="col-md-5 col-sm-5 col-xs-12 bottom-twenty scoialbtn widleyright-five fbbgclr"><span>Log in with</span><i class="fa fa-facebook" style="margin-left:5px;font-size:1em;"></i></div>
                <div class="col-md-2 col-sm-2">&nbsp;</div>
                <div class="col-md-5 col-sm-5 col-xs-12 bottom-twenty scoialbtn widleyleft-five googlebgclr"><span>Log in with</span><i class="fa fa-google" style="margin-left:5px;font-size:1em;"></i></div> -->
            </div>
        </div>
    </div>
    <!--Popup Code-->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
</body>
</html>	