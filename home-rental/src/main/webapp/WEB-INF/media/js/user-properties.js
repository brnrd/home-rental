
/*
JS - User-properties
Date of creation : 28/04/2013
Creator : brnrd
target : modal, submit form
*/


(function() {
  var ckeckin, context, deleteHandler, formatDate, modalActionHandler, modifyHandler, setContext, target;

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
      $('#modal-property .modal-footer #submit').attr('form', 'modify-form');
      $('#modal-property .modal-footer #submit').removeClass('btn-danger').addClass('btn-success');
    } else {
      $('#modal-property .modal-header h3').text('Delete this property');
      $('#modal-property .modal-body').load(context.url + ' #delete');
      $('#modal-property .modal-footer #submit').html('Delete');
      $('#modal-property .modal-footer #submit').attr('form', 'delete-form');
      $('#modal-property .modal-footer #submit').removeClass('btn-success').addClass('btn-danger');
    }
    return $('#modal-property').modal('show');
  };

  modifyHandler = function(dataToSend) {
    $.post('/home-rental/s/property/update', dataToSend);
    return function(data) {
      return $('#modal-property').modal('hide');
    };
  };

  deleteHandler = function(dataToSend) {
    return $.post('/home-rental/s/property/delete', dataToSend, function(data) {
      return $('#modal-property').modal('hide');
    });
  };

  $('.modify-property').on("click", function(event) {
    var property_id;
    property_id = $(this).data("property-id");
    setContext('modify', '/home-rental/s/property/' + property_id + '/modal/');
    return modalActionHandler();
  });

  $('.delete-property').on("click", function(event) {
    var property_id;
    property_id = $(this).data("property-id");
    setContext('delete', '/home-rental/s/property/' + property_id + '/modal/');
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

  ckeckin = null;

  target = null;

  formatDate = function(date) {
    var res, t_res;
    res = date.toLocaleString().split(" ")[0];
    t_res = res.split("/");
    if (t_res[0].length === 1) {
      return '0' + t_res[0] + "/" + t_res[1] + "/" + t_res[2];
    }
    return t_res.join("/");
  };

  $('#rentStart').datepicker({
    onClose: function(selectedDate) {
      var checkin;
      console.log(selectedDate);
      checkin = new Date(selectedDate);
      console.log(checkin);
      target = new Date(checkin.getFullYear(), checkin.getMonth(), checkin.getDate() + 7);
      console.log(target);
      $('#rentStop').datepicker('option', 'minDate', selectedDate);
      return $('#rentStop').val(formatDate(target));
    }
  });

  $('#rentStop').datepicker({
    defaultDate: target ? target : '',
    onClose: function(selectedDate) {
      if (checkin) {
        return $('#rentStart').datepicker('option', 'maxDate', selectedDate);
      }
    }
  });

}).call(this);
