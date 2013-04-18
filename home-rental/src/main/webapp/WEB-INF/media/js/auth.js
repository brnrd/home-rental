
/*
JS - Auth
Date of creation : 16/04/2013
Creator : Romain FONCIER
target : login, signup.
comment :
*/


/* Noty
*/


(function() {
  var authHandler, notifyMessage, setAjaxHeader, validForm;

  notifyMessage = function(type, msg) {
    return noty({
      text: msg,
      type: type,
      dismissQueue: false,
      timeout: 2000,
      layout: 'topCenter',
      theme: 'defaultTheme'
    });
  };

  setAjaxHeader = function() {
    return $.ajaxSetup({
      beforeSend: function(xhr) {
        return xhr.setRequestHeader("X-Ajax-call", "true");
      }
    });
  };

  authHandler = function(dataToSend, target) {
    setAjaxHeader();
    return $.post('/home-rental/' + target, dataToSend, function(data) {
      $('.ajax-loader').hide();
      data = JSON.parse(data);
      switch (data.status) {
        case 'success':
          return notifyMessage('success', data.msg);
        case 'error':
          return notifyMessage('error', data.msg);
      }
    });
  };

  validForm = function(array) {
    var i, valid;
    valid = true;
    i = 0;
    while (valid && i < array.length) {
      valid = array[i].value.length > 0;
    }
    return valid;
  };

  $('#signup').on("click", function(event) {
    return $('#auth-modal-signup').modal('show');
  });

  $('#login').on("click", function(event) {
    $('#auth-modal-login .modal-body').load('/home-rental/login .modal-body');
    return $('#auth-modal-login').modal('show');
  });

  /* Call authHandler
  */


  $('#auth-process-signup').on("submit", function(event) {
    event.preventDefault();
    if (validForm($(this).serializeArray())) {

    } else {
      return $('#auth-modal-signup .form-error').text("Warning ! All fields must be filled !");
    }
  });

  $('#auth-process-login').on("submit", function(event) {
    event.preventDefault();
    $('.ajax-loader').show();
    return authHandler($(this).serialize(), 'login');
  });

}).call(this);
