function validar()
{
	var formulario= document.getElementsByName("formulario")[0];
	
	var nombre = formulario.name.value;
	var apellido1 = formulario.surname1.value;
	var apellido2 = formulario.surname2.value;
	var correo = formulario.email.value;
	var tlf = formulario.phone.value;
	var dni = formulario.dni.value;
	
	
	
	tengo que poner aqui la fecha y utilizar esta funcion para mirar si esta entre los dias bien
	function existeFecha(fecha){
      var fechaf = fecha.split("/");
      var day = fechaf[0];
      var month = fechaf[1];
      var year = fechaf[2];
      var date = new Date(year,month,'0');
      if((day-0)>(date.getDate()-0)){
            return false;
      }
      return true;
}

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
    
    alert("Mensaje enviado con éxito.");
    
	return true;
		
}
