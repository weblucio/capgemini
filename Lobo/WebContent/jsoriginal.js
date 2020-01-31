var protocol={operation:"",table:"",value:"",target:""};

var complemento="<table  id='container' style='border:none;' ><tr><td><button onclick='addRow()'>+</button></td><td><button id='excluir' value='default' onclick='deleteRow(this.value)'>-</button></td></tr></table>";
var exclude={};
var mode="";
var data=[];

last="";
function setTo(para){
	
//if(document.getElementById("status_"+para).style.backgroundColor=="green"){
	
	sendingconfig["to"]=para;
	sendingprocess["to"]=para;
	document.getElementById("f").disabled=false;
	document.getElementById("for").innerHTML="Send to "+para;
//}else{
	
	//alert("User offline");
//}
}





//
//const url="ws://"+window.location.hostname+":8080/Lobo/control";
const url="ws://localhost:8080/Lobo/actions";
var socket;

var slices;
function open(){

	
	socket=new WebSocket(url);
//	socket.binaryType="blob";
console.log("Abriu");
console.log("Estado=>"+socket.readyState);	
//onopen
socket.onopen=function(event){
	console.log("Conectou");
	error=0;
//socket.send(JSON.stringify(login));
	load(document.getElementById("table").value);

}
//onclose
socket.onclose=function(event){
	console.log("desconectou");
	console.log("Estado=>"+socket.readyState);
	open();
}

socket.onerror=function(e){
	console.log("Reconectando no erro"+e);
		
//	error++;
	
	
	open();
	//socket.send(JSON.stringify(login));

	
}


socket.onmessage=function(event){

	

	object=JSON.parse(event.data);
if(mode=="delete"){

	window.location.reload();
	
}else{
	writeOnScreen(object);
}}

}
open();

//_____________________


function writeOnScreen(dados){

	
	//table="<html><table><tr><td></td><td>Nome</td><td>Cnpj</td><td>Responsaveis</td><td>Telefone</td><td>Contato</td><td>Telefone</td><td>Contato</td><td>Telefone</td><td>Contato</td></tr>";
table="<table>";
	contador=1;
	data=dados;
	id="";
	data.forEach(function(item){
	//data.push(item);

		if(contador==1){
table+="<tr><td></td>";
		item.forEach(function(value){
	table+="<td>"+value.ColumnName+"</td>";
	
		});
		
		table+="</tr>";
		
		}

		table+="<tr>";
		internal=1;
		item.forEach(function(value){

			if(internal==1){
	id=value.ValueColumn;
			table+="<td><input type='button' class='bt' id='open_"+id+"' name='"+id+"' value='"+contador+"' onclick='showMenu(this.id,this.name)'/></td>";
}
internal++;
			
			table+="<td><input type='text' id ='"+value.ColumnName+"_"+id+"' value='"+value.ValueColumn+"' </td>";


				});

		table+="</tr>";
	






//		table+="<tr><td><input type='button' class='bt' id='open_"+item.id+"' name='"+item.id+"' value='"+contador+"' onclick='showMenu(this.id,this.name)'/></td><td><input type='text' id ='nome_"+item.id+"' value='"+item.nome+"' /></td><td><input type='text' id ='cnpj_"+item.id+"' value='"+item.cnpj+"' /></td><td><input type='text' id ='responsaveis_"+item.id+"' value='"+item.responsaveis+"' /></td><td><input type='text' id ='telefone_"+item.id+"' value='"+item.telefone+"' /></td><td><input type='text' id ='contato_"+item.id+"' value='"+item.contato+"' /></td><td><input type='text' id ='telefone1_"+item.id+"' value='"+item.telefone1+"' /></td><td><input type='text' id ='contato1_"+item.id+"' value='"+item.contato1+"' /></td><td><input type='text' id ='telefone2_"+item.id+"' value='"+item.telefone2+"' /></td><td><input type='text' id ='contato2_"+item.id+"' value='"+item.contato2+"' /></td></tr>";

	
contador++;



		});

		
	table+="</table></html>";

	document.getElementById("sheet").innerHTML=table;
//$("#sheet").load(table);
}

function load(sheet){

	
	protocol["operation"]="load";
	protocol["table"]=document.getElementById("table").value;
socket.send(JSON.stringify(protocol));
	}




																																																																																																																																																																																																																				function filter(){}
function save(){}
function deleteRow(row){
	
	mode="delete";
	protocol["operation"]="deleteRow";
	protocol["table"]=document.getElementById("table").value;
	protocol["value"]=row;

	socket.send(JSON.stringify(protocol));
	document.getElementById("menu").style.display="none";
	
}
function deleteCol(){
	
}

function addRow(){
	
	protocol["operation"]="addRow";
	protocol["table"]=document.getElementById("table").value;
socket.send(JSON.stringify(protocol));

	
}

function addCol(){}

function erase(num){
	

	color="white";
	document.getElementById("nome_"+num).style.backgroundColor=color;

	document.getElementById("cnpj_"+num).style.backgroundColor=color;

	document.getElementById("responsaveis_"+num).style.backgroundColor=color;

	document.getElementById("telefone_"+num).style.backgroundColor=color;

	document.getElementById("contato_"+num).style.backgroundColor=color;

	document.getElementById("telefone1_"+num).style.backgroundColor=color;

	document.getElementById("contato1_"+num).style.backgroundColor=color;

	document.getElementById("telefone2_"+num).style.backgroundColor=color;

	document.getElementById("contato2_"+num).style.backgroundColor=color;

	
}


function showMenu(id,num){
//	alert(document.getElementById(id).offsetTop);
if(last!=""){
	erase(last);
}
	color="#f28c07";
	document.getElementById("nome_"+num).style.backgroundColor=color;

	document.getElementById("cnpj_"+num).style.backgroundColor=color;

	document.getElementById("responsaveis_"+num).style.backgroundColor=color;

	document.getElementById("telefone_"+num).style.backgroundColor=color;

	document.getElementById("contato_"+num).style.backgroundColor=color;

	document.getElementById("telefone1_"+num).style.backgroundColor=color;

	document.getElementById("contato1_"+num).style.backgroundColor=color;

	document.getElementById("telefone2_"+num).style.backgroundColor=color;

	document.getElementById("contato2_"+num).style.backgroundColor=color;
last=num;
	document.getElementById("menu").innerHTML=complemento;
	document.getElementById("menu").style.top=document.getElementById(id).offsetTop+40+"px";
	document.getElementById("menu").style.left=document.getElementById(id).offsetLeft+40+"px";
	document.getElementById("menu").style.display="block";
	document.getElementById("excluir").value=num;
	//alert(document.getElementById("excluir").value);
	
}
