
/*
JS - New
Date of creation : 17/04/2013
Creator : brnrd
target : all (typeahead, maps, ...)
*/


(function() {
  var ckeckin, formatDate, geocoder, service, target;

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

  $('#rentPeriodStart').datepicker({
    onClose: function(selectedDate) {
      var checkin;
      console.log(selectedDate);
      checkin = new Date(selectedDate);
      console.log(checkin);
      target = new Date(checkin.getFullYear(), checkin.getMonth(), checkin.getDate() + 7);
      console.log(target);
      $('#rentPeriodStop').datepicker('option', 'minDate', selectedDate);
      return $('#rentPeriodStop').val(formatDate(target));
    }
  });

  $('#rentPeriodStop').datepicker({
    defaultDate: target ? target : '',
    onClose: function(selectedDate) {
      if (checkin) {
        return $('#rentPeriodStart').datepicker('option', 'maxDate', selectedDate);
      }
    }
  });

  service = new google.maps.places.AutocompleteService();

  geocoder = new google.maps.Geocoder();

  $('#city-maps').typeahead({
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
