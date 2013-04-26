
/*
JS - carousel
Date of creation : 12/04/2013
Creator : Romain FONCIER
target : home
comment :
*/


(function() {
  var MAX, MIN, TIME, change_slide, check_change, current_slide;

  MIN = 1;

  MAX = 5;

  TIME = 6000;

  current_slide = MIN;

  change_slide = function(ind) {
    $('#frame-container li#loft_' + ind).addClass('active');
    return $('#frame-container li#loft_' + current_slide).removeClass('active');
  };

  check_change = function(ind) {
    if (ind > MAX) {
      change_slide(MIN);
      return current_slide = MIN;
    } else {
      change_slide(ind);
      return current_slide = ind;
    }
  };

  setInterval(function() {
    return check_change(current_slide + 1);
  }, TIME);

  true;

}).call(this);
