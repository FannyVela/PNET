function postInscripcion(nom, a1, a2, dni, mail, tel, n, d1, d2, p) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/attendees/",
        contentType: "application/json",
        dataType: "text",
        data: JSON.stringify({
            "name": nom,
            "name1": a1,
            "name2": a2,
			"dni": dni,
			"mail": mail,
			"phone": tel,
			"days": n,
			"day1": d1,
			"day2": d2,
			"press": p
        }),
        success: function(data) {
            $("#resGetHello").html(data);
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
              $("#resGetHello").html(JSON.stringify(data));      },
      error:function(res){
              alert("ERROR: "+ res.statusText);  }
  });
}

function getInscripcion(id){
 $.ajax({ 
      type: "GET",
      url: "http://localhost:8080/attendees/" + id,
      success: function(data){        
              $("#resGetHello").html(JSON.stringify(data));      },
      error:function(res){
              alert("ERROR: "+ res.statusText);  }
  });
}

function putInscripcion(id, nom, a1, a2, dni, mail, tel, n, d1, d2, p){
 $.ajax({ 
      type: "PUT",
      url: "http://localhost:8080/attendees/" + id,
      data:{
            "name": nom,
            "name1": a1,
            "name2": a2,
			"dni": dni,
			"mail": mail,
			"phone": tel,
			"days": n,
			"day1": d1,
			"day2": d2,
			"press": p
        },
      success: function(data){        
              $("#resGetHello").html(JSON.stringify(data));      },
      error:function(res){
              alert("ERROR: "+ res.statusText);  }
  });
}

function deleteInscripcion(id){
 $.ajax({ 
      type: "DELETE",
      url: "http://localhost:8080/attendees/" + id,
      success: function(data){        
              $("#resGetHello").html(JSON.stringify(data));      },
      error:function(res){
              alert("ERROR: "+ res.statusText);  }
  });
}

function deleteInscripciones(){
 $.ajax({ 
      type: "DELETE",
      url: "http://localhost:8080/attendees/",
      success: function(data){        
              $("#resGetHello").html(JSON.stringify(data));      },
      error:function(res){
              alert("ERROR: "+ res.statusText);  }
  });
}

