function validar()
{
	var formulario= document.getElementsByName("formulario")[0];
	
	var nombre = formulario.name.value;
	var apellido1 = formulario.surname1.value;
	var apellido2 = formulario.surname2.value;
	var correo = formulario.email.value;
	var tlf = formulario.phone.value;

	var expreg = /^([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú]+[\s]*)+$/;
	
	var expregCorreo = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/ ;
	
	var expregTlf = /^[9|6]{1}([\d]{2}[-]*){3}[\d]{2}$/;
	
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
    
    alert("Mensaje enviado con éxito.");
    
	return true;
		
}
