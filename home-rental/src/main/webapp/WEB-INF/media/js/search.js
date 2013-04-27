
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
    var params, smax, smin;
    params = $('.map-wrapper #search-params').data('search-params');
    $('#search-bar #location-search').val(params[0]);
    $('#search-bar #checkin').val(params[1]);
    $('#search-bar #checkout').val(params[2]);
    $('#search-bar #guests-number').val(params[3]);
    $('#search-bar button.btn-dpd strong').text(pluralize(params[3], "guest"));
    smin = $('.map-wrapper #min_price').text();
    smax = $('.map-wrapper #max_price').text();
    console.log(smin + " - " + smax);
    return $("#slider").slider({
      range: true,
      min: smin,
      max: smax,
      values: [smin, smax],
      slide: function(event, ui) {
        $("#min_price").val(ui.values[0]);
        return $("#max_price").val(ui.values[1]);
      }
    });
  });

}).call(this);
