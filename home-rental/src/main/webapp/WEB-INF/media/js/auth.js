
/*
JS - Auth
Date of creation : 16/04/2013
Creator : Romain FONCIER
target : login, signup.
comment :
*/


(function() {

  $('#signup').on("click", function(event) {
    return $('#auth-modal-signup').modal('show');
  });

  $('#login').on("click", function(event) {
    return $('#auth-modal-login').modal('show');
  });

}).call(this);
