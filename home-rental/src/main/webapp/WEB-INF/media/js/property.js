
/*
JS - Property
Date of creation : 26/04/2013
Creator : brnrd
target : modal, submit form
*/


(function() {
  var context, deleteHandler, modalActionHandler, modifyHandler, setContext;

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
      $('#modal-property .modal-header h3').text('Modify this property');
      $('#modal-property .modal-body').load(context.url + ' #modify');
      $('#modal-property .modal-footer #submit').html('Save');
      $('#modal-property .modal-footer #submit').removeClass('btn-danger').addClass('btn-success');
    } else {
      $('#modal-property .modal-header h3').text('Delete this property');
      $('#modal-property .modal-body').load(context.url + ' #delete');
      $('#modal-property .modal-footer #submit').html('Delete');
      $('#modal-property .modal-footer #submit').removeClass('btn-success').addClass('btn-danger');
    }
    return $('#modal-property').modal('show');
  };

  modifyHandler = function(dataToSend) {
    return $.post('/s/property/update', dataToSend, function(data) {
      $('#modal-property').modal('hide');
      return resetModal();
    });
  };

  deleteHandler = function(dataToSend) {
    return $.post('/s/property/delete', dataToSend, function(data) {
      $('#modal-property').modal('hide');
      return resetModal();
    });
  };

  $('#modify-property').on("click", function(event) {
    setContext('modify', '/home-rental/s/property/' + $('#property-id').val() + '/modal/');
    console.log(context);
    return modalActionHandler();
  });

  $('#delete-property').on("click", function(event) {
    setContext('delete', '/home-rental/s/property/' + $('#property-id').val() + '/modal/');
    return modalActionHandler();
  });

  $('#modify-form').on("submit", function(event) {
    event.preventDefault();
    return modifyHandler($(this).serialize());
  });

  $('#delete-form').on("submit", function(event) {
    event.preventDefault();
    return deleteHandler($(this).serialize());
  });

}).call(this);
