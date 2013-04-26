
/*
JS - User
Date of creation : 26/04/2013
Creator : brnrd
target : modal, submit form
*/


(function() {
  var context, modalActionHandler, setContext;

  context = {
    type: null,
    url: null
  };

  setContext = function(type, url) {
    context.type = type;
    return context.url = url;
  };

  modalActionHandler = function() {
    if (context.type === "modify") {
      $('#modal-user .modal-header').load(context.url + ' #modify-header');
      $('#modal-user .modal-body').load(context.url + ' #modify-form');
      $('#modal-user .modal-footer').load(context.url + ' #modify-buttons');
    } else {
      $('#modal-user .modal-header').load(context.url + ' #delete-header');
      $('#modal-user .modal-body').load(context.url + ' #delete-body');
      $('#modal-user .modal-footer').load(context.url + ' #delete-buttons');
    }
    return $('#modal-user').modal('show');
  };

  $('#modify-user').on("click", function(event) {
    setContext('modify', '/home-rental/s/account/modal/');
    console.log(context);
    return modalActionHandler();
  });

  $('#delete-user').on("click", function(event) {
    setContext('delete', '/home-rental/s/account/modal/');
    return modalActionHandler();
  });

}).call(this);
