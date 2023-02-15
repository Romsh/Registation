<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="css.jsp"%>
<title>Insert title here</title>
</head>
<body>
    <div id="wrapper">
        
    <%@ include file="topnavbar.jsp" %>
    <%@ include file="sidemenu.jsp"%>

        <div id="page-content-wrapper">
            <div class="page-content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="panel panel-danger">
                                <div class="panel-heading">
                                        Panel 1
                                </div>
                                <div class="panel-body">
                                    content body
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">                    
                            <div class="panel panel-success">
                                <div class="panel-heading">
                                        Panel 1
                                </div>
                                <div class="panel-body">
                                    content body
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="js.jsp"%>
</body>
</html>