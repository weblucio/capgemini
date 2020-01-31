<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jquery.js" ></script>
<script type="text/javascript" src="jsoriginal.js"></script>


<title>Planilha</title>
</head>
<style>

table, td, th {  
  border: 1px solid #ddd;
  text-align: left;
  }

table {
  border-collapse: collapse;
  width: 100%;
}

th, td{
}

tr{
height:40px;}

.bt{
position:relative;
border:none;
width:40px;
height:40px;

}

input{
border:none;
height:40px;
}


.mini{

  width: 20px;
height:20px;
}

#container{
position:absolute;

border:none;

}

#container th td{
border:none;

}

#menu{
position:absolute;
left:40px;
display:none;
}

#sheet{
position:absolute;
}





</style>
<body>
<input type="hidden" id="table" value="<%= request.getParameter("table") %>" />
<button onclick='addRow()'>+</button>
<div><input type="button" value="empresa" onclick="load(this.value)"> 
</div>
<div id="sheet" style="position:absolute;">

 </div>
<div id="menu" style="position:absolute;">daffdasdfadfasdfsafdfs</div>

</body>
<script>
//load(document.getElementById("table").value);

//setTimeout(function(){load(document.getElementById("table").value);},1000);
</script>
</html>