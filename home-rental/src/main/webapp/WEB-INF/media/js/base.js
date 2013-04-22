
/*
JS - Base
Date of creation : 12/04/2013
Creator : Romain FONCIER
target : all (datepicker, ...)
comment :
*/


/* Noty
*/


(function() {
  var ckeckin, formatDate, geocoder, notifyMessage, service, target;

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

  if ($('#new_user').length > 0) {
    console.log("new user");
    notifyMessage("success", "Your new account has been successfully created");
  }

  if ($('#logged_user').length > 0) {
    console.log("logged user");
    notifyMessage("success", "You are successfully logged");
  }

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

  service = new google.maps.places.AutocompleteService();

  geocoder = new google.maps.Geocoder();

  $('#location-search').typeahead({
    source: function(query, process) {
      return service.getPlacePredictions({
        input: query
      }, function(predictions, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          return process($.map(predictions, function(prediction) {
            return prediction.description;
          }));
        }
      });
    },
    updater: function(item) {
      geocoder.geocode({
        address: item
      }, function(results, status) {
        if (status !== google.maps.GeocoderStatus.OK) {
          alert('Cannot find address');
          return;
        }
        map.setCenter(results[0].geometry.location);
        return map.setZoom(12);
      });
      return item;
    }
  });

}).call(this);
