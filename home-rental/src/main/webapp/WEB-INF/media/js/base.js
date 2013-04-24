
/*
JS - Base
Date of creation : 12/04/2013
Creator : Romain FONCIER
target : all (datepicker, ...)
comment :
*/


/* Utils methods
*/


(function() {
  var ckeckin, formatDate, geocoder, notifyMessage, pluralize, service, target;

  pluralize = function(i, title) {
    if (i > 1) {
      return i + " " + title + "s";
    } else {
      return i + " " + title;
    }
  };

  /* Noty
  */


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
    notifyMessage("success", "Your new account has been successfully created");
  }

  if ($('#logged_user').length > 0) {
    notifyMessage("success", "You are successfully logged");
  }

  if ($('#logout_success').length > 0) {
    notifyMessage("success", "You are successfully logged out");
  }

  /* Guests selector
  */


  $('#search-bar #guests-list li a').on("click", function(event) {
    var nb;
    event.preventDefault();
    nb = $(this).text().split(" ")[0];
    $('#search-bar button.btn-dpd strong').text(pluralize(nb, "guest"));
    return $('#search-bar #guests-number').val(nb);
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

  $('#checkin').datepicker({
    onClose: function(selectedDate) {
      var checkin;
      checkin = new Date(selectedDate);
      target = new Date(checkin.getFullYear(), checkin.getMonth(), checkin.getDate() + 1);
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
