
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

  /* Call authHandler
  */


  $('#auth-modal-signup #auth-process').on("submit", function(event) {
    event.preventDefault();
    return console.log("form : " + $(this).auth_username);
  });

  $('#auth-modal-login #auth-process').on("submit", function(event) {
    event.preventDefault();
    return loginHandler($(this).serialize());
  });

}).call(this);
