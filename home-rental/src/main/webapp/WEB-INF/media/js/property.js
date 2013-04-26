
/*
JS - Property
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
      $('#modal-property .modal-header').load(context.url + ' #modify-header');
      $('#modal-property .modal-body').load(context.url + ' #modify-form');
      $('#modal-property .modal-footer').load(context.url + ' #modify-buttons');
    } else {
      $('#modal-property .modal-header').load(context.url + ' #delete-header');
      $('#modal-property .modal-body').load(context.url + ' #delete-body');
      $('#modal-property .modal-footer').load(context.url + ' #delete-buttons');
    }
    return $('#modal-property').modal('show');
  };

  $('#modify-property').on("click", function(event) {
    setContext('modify', '/home-rental/s/property/modal/');
    console.log(context);
    return modalActionHandler();
  });

  $('#delete-property').on("click", function(event) {
    setContext('delete', '/home-rental/s/property/modal/');
    return modalActionHandler();
  });

}).call(this);
