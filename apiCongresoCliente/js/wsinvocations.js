function postInscripcion(nom, s1, s2, dni, email, tel, days, prensa) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/attendees/",
        contentType: "application/json",
        dataType: "text",
        data: JSON.stringify({
            "name": nom,
            "surname1": s1,
            "surname2": s2,
			      "dni": dni,
      			"email": email,
      			"phone": tel,
      			"days": days,
      			"prensa": prensa
        }),
        success: function(data) {
            $("#resul").html(data);
        },
        error: function(res) {
            alert("ERROR " + res.statusText);
        }
    });
}

function getInscripciones(){
 $.ajax({
      type: "GET",
      url: "http://localhost:8080/attendees/",
      success: function(data){
        var attendees = data;
        var HTMLText  = "<ul>";

        for( var i = 0 ; i < attendees.length ; i ++){
          var attendeeAux = attendees[i];
          HTMLText += "<li> Nombre: " + attendeeAux.name +
                            "<br>Apellido 1: " + attendeeAux.surname1 +
                            "<br>Apellido 2: " + attendeeAux.surname2 +
                            "<br>DNI: " + attendeeAux.dni +
                            "<br>Email: " + attendeeAux.email +
                            "<br>Teléfono: " + attendeeAux.phone +
                            "<br>Días de asistencia: " + attendeeAux.days +
                            "<br>Prensa: " + attendeeAux.prensa +
                      "</li><br><br>";
        }
        HTMLText +="</ul>";
        $("#resul").html(HTMLText);
      /*      $("#resul").html(JSON.stringify(data));  */
     },
      error:function(res){
              alert("ERROR: "+ res.statusText);  }
  });
}

function getInscripcion(id){
 $.ajax({
      type: "GET",
      url: "http://localhost:8080/attendees/" + id,
      success: function(data){
          var attendee = data;
          var HTMLText =    "<p>Nombre: " + attendee[0].name +
                            "<br>Apellido 1: " + attendee[0].surname1 +
                            "<br>Apellido 2: " + attendee[0].surname2 +
                            "<br>DNI: " + attendee[0].dni +
                            "<br>Email: " + attendee[0].email +
                            "<br>Teléfono: " + attendee[0].phone +
                            "<br>Días de asistencia: " + attendee[0].days +
                            "<br>Prensa: " + attendee[0].prensa; + "</p>"
            $("#resul").html(HTMLText);
      },
      error:function(res){
              alert("ERROR: "+ res.statusText);  }
  });
}

//function putInscripcion(id, nom, a1, a2, dni, mail, tel, n, d1, d2, p){
function putInscripcion(id, nom, surname1, surname2, dni, email, phone, dias, prensa){
 $.ajax({
      type: "PUT",
      url: "http://localhost:8080/attendees/" + id,
      data:{
            "name": nom,
            "surname1": surname1,
            "surname2": surname2,
            "dni": dni,
            "email": email,
            "phone": phone,
            "days": dias,
            "prensa": prensa
        },
      success: function(data){
            var HTMLText = "Asistente actualizado con éxito.";
              $("#resul").html(HTMLText);      },
      error:function(res){
              alert("ERROR: "+ res.statusText);  }
  });
}

function deleteInscripcion(id){
 $.ajax({
      type: "DELETE",
      url: "http://localhost:8080/attendees/" + id,
      success: function(data){
          var HTMLText = "Asistente eliminado con éxito";
              $("#resul").html(HTMLText);
      },
      error:function(res){
              alert("ERROR: "+ res.statusText);  }
  });
}

function deleteInscripciones(){
 $.ajax({
      type: "DELETE",
      url: "http://localhost:8080/attendees/",
      success: function(data){
              var HTMLText = "Los asistentes fueron eliminados con éxito.";
              $("#resul").html(HTMLText);      },
      error:function(res){
              alert("ERROR: "+ res.statusText);  }
  });
}
