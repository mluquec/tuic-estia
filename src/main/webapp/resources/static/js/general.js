function CheckPassword(inputtxt)   
{   
	var passw = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$/;   
	if(!inputtxt.value.match(passw))   
	{   
		alert('Wrong...!')  
 	}  
} 

function addListOptions(typeInfo)
{
	var option= $("#typeInfo").val();
    if( option =="LIST")
    	{
    	$("#list").show();
    	}
    else
    	{
    	$("#list").hide();
    	}
	
}

$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});


function noSpaceRedirect(that, word)
{
	
	$(that).attr('href', "#"+word.replace(/ /g, ''))
}

function noSpacePanel(that, word)
{
	
	$(that).attr('id', word.replace(/ /g, ''))
}
function myFunction()
{
	return a;
}

function sortList(list)
{
list.sort();
}



