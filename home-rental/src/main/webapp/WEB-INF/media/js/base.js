
/*
JS - Base
Date of creation : 12/04/2013
Creator : Romain FONCIER
target : all (datepicker, ...)
comment :
*/


(function() {
  var ckeckin, formatDate, target;

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

  $('#checkin').datepicker({
    onClose: function(selectedDate) {
      var checkin;
      console.log(selectedDate);
      checkin = new Date(selectedDate);
      console.log(checkin);
      target = new Date(checkin.getFullYear(), checkin.getMonth(), checkin.getDate() + 1);
      console.log(target);
      $('#checkout').datepicker("option", "minDate", selectedDate);
      return $('#checkout').val(formatDate(target));
    }
  });

  $('#checkout').datepicker({
    defaultDate: target ? target : '',
    onClose: function(selectedDate) {
      if (checkin) {
        return $('#checkin').datepicker('option', 'maxDate', selectedDate);
      }
    }
  });

}).call(this);
