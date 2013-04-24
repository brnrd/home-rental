
/*
JS - New
Date of creation : 17/04/2013
Creator : brnrd
target : all (typeahead, maps, ...)
*/


(function() {
  var ckeckin, formatDate, geocoder, service, splitCity, target,
    _this = this;

  $(document).ready(function() {
    var map, mapOptions;
    mapOptions = {
      zoom: 8,
      center: new google.maps.LatLng(-34.397, 150.644),
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    return map = new google.maps.Map($('#map-canvas').get(0), mapOptions);
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
      splitCity(item);
      return item;
    }
  });

  splitCity = function(item) {
    var maps_data;
    if (item) {
      maps_data = $.trim(item).split(",");
      if (maps_data.length > 2) {
        $('#city').val(maps_data[0] + ", " + maps_data[1]);
        return $('#country').val(maps_data[maps_data.length - 1]);
      } else {
        $('#city').val(maps_data[0] + ", " + maps_data[1]);
        return $('#country').val("USA");
      }
    }
  };

}).call(this);
