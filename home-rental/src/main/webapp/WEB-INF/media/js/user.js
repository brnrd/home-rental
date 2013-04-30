
/*
JS - User
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
      $('#modal-user .modal-header h3').text('Modify your account');
      $('#modal-user .modal-body').load(context.url + ' #modify');
      $('#modal-user .modal-footer #submit').html('Save');
      $('#modal-user .modal-footer #submit').removeClass('btn-danger').addClass('btn-success');
    } else {
      $('#modal-user .modal-header h3').text('Delete your account');
      $('#modal-user .modal-body').load(context.url + ' #delete');
      $('#modal-user .modal-footer #submit').html('Delete');
      $('#modal-user .modal-footer #submit').removeClass('btn-success').addClass('btn-danger');
    }
    return $('#modal-user').modal('show');
  };

  modifyHandler = function(dataToSend) {
    $.post('/s/account/update', dataToSend);
    console.log('Data to send ' + dataToSend);
    return function(data) {
      return $('#modal-user').modal('hide');
    };
  };

  deleteHandler = function(dataToSend) {
    return $.post('/s/account/delete', dataToSend, console.log('Data to send ' + dataToSend), function(data) {
      return $('#modal-user').modal('hide');
    });
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

  $('#submit').on("click", function(event) {
    if ($('#submit').hasClass('btn-success')) {
      return $('#modify-form').submit();
    } else {
      return $('#delete-form').submit();
    }
  });

  $('#modify-form').on("submit", function(event) {
    console.log('MODIFY FORM SUBMIT');
    event.preventDefault();
    return modifyHandler($(this).serialize());
  });

  $('#delete-form').on("submit", function(event) {
    console.log('DELETE FORM SUBMIT');
    event.preventDefault();
    return deleteHandler($(this).serialize());
  });

}).call(this);
