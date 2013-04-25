
/*
JS - Search
Date of creation : 23/04/2013
Creator : Romain FONCIER
target : search
comment :
*/


/* Utils methods
*/


(function() {
  var pluralize;

  pluralize = function(i, title) {
    if (i > 1) {
      return i + " " + title + "s";
    } else {
      return i + " " + title;
    }
  };

  jQuery(function() {
    var params, slider_params;
    params = $('.map-container #search-params').data('search-params');
    $('#search-bar #location-search').val(params[0]);
    $('#search-bar #checkin').val(params[1]);
    $('#search-bar #checkout').val(params[2]);
    $('#search-bar #guests-number').val(params[3]);
    $('#search-bar button.btn-dpd strong').text(pluralize(params[3], "guest"));
    slider_params = params[4];
    return $("#slider").slider({
      range: true,
      min: slider_params[0],
      max: slider_params[1],
      values: [slider_params[0], slider_params[1]],
      slide: function(event, ui) {}
    });
  });

}).call(this);
