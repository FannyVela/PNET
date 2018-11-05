

function fecha(dentrada, dsalida)
{
			var fechae = dentrada.split("/");
			var fechas = dsalida.split("/");
			var daye = fechae[0];
			var days = fechas[0];
			var monthe = fechae[1];
			var months = fechas[1];
			var yeare = fechae[2];
			var years = fechas[2];
			
			
	if(yeare != 2019 || years != 2019 || monthe != 5 || months != 5 || daye < 7 || daye > 9 || days < 7 || days > 9 || daye > days )
	{

		alert("Fecha de entrada y salida invalidas");
		return false;
	}
	
	return true;
			
}

function validar()
{
	var formulario= document.getElementsByName("formulario")[0];
	
	var nombre = formulario.name.value;
	var apellido1 = formulario.surname1.value;
	var apellido2 = formulario.surname2.value;
	var correo = formulario.email.value;
	var tlf = formulario.phone.value;
	var dni = formulario.dni.value;
	var dentrada = formulario.diaentrada;
	var dsalida = formulario.diasalida;
	
			

	

	var expreg = /^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\s]*)+$/;
	
	var expregCorreo = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/ ;
	
	var expregTlf = /^[9|6]{1}([\d]{2}[-]*){3}[\d]{2}$/;
	
	var expregDni = /^\d{8}[a-zA-Z]$/;
	
	var expregNie = /^[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]$/i;
	
	if(!expreg.test(nombre))
	{
			alert("Nombre inválido.");
			return false;
	} 
	
	if(!expreg.test(apellido1))
	{
			alert("1º Apellido inválido.");
			return false;
	} 

	if(!expreg.test(apellido2) && apellido2 != "")
	{
		alert("2º Apellido inválido.");
		return false;
	}
	
	if(!expregCorreo.test(correo))
	{
		alert("Correo inválido");
		return false;
	}
	
	if(!expregTlf.test(tlf) && tlf != "")
	{
		alert("Teléfono inválido.");
		return false;
	}
	
	if(!expregDni.test(dni) && !expregNie.test(dni))
	{
		alert("DNI/NIE inválido.");
		return false;
	}
	

	if(!fecha(dentrada, dsalida))
	{
		return false;
	}
	
	
    alert("Mensaje enviado con éxito.");
		
	return true;
		
}