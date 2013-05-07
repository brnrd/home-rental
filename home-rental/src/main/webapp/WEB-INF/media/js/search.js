
/*
JS - Search
Date of creation : 23/04/2013
Creator : Romain FONCIER
target : search
comment :
*/


(function() {
  var authentication_success, checkLogin, loadAjaxLoginModal, loginHandler, notifyMessage, param, params, pluralize, reservation_target, smax, smin;

  param = null;

  reservation_target = null;

  authentication_success = false;

  /* Utils methods
  */


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

  loadAjaxLoginModal = function() {
    console.log("Load ajax login form");
    return $('#modal-container').load('/home-rental/ajax-login #ajax-login-modal', function() {
      return $('#ajax-login-modal').modal('show');
    });
  };

  checkLogin = function() {
    if ($('.user-logged').length > 0) {
      return true;
    } else {
      $('#ajax-login-modal').modal('show');
      return false;
    }
  };

  loginHandler = function(dataToSend) {
    $('.ajax-loader').show();
    return $.post('/ajax-login', dataToSend, function(data) {
      $('.ajax-loader').hide();
      return console.log(data);
      /*
                  switch data.status
                      when 'success'
                          notifyMessage("success", "You are successfully logged")
                          true
                      when 'error'
                          $('#ajax-login-modal .form-error span').text("Warning ! Your username/password are incorrects !")
                          $('#ajax-login-modal .field-container').each (item) ->
                              item.addClass('error')
                          false
      */

    });
  };

  /* HANDLERS
  */


  params = $('.map-wrapper #search-params').data('search-params');

  $('#search-bar #location-search').val(params[0]);

  $('#search-bar #checkin').val(params[1]);

  $('#search-bar #checkout').val(params[2]);

  $('#search-bar #guests-number').val(params[3]);

  $('#search-bar button.btn-dpd strong').text(pluralize(params[3], "guest"));

  smin = $('.map-wrapper #min_price').text();

  smax = $('.map-wrapper #max_price').text();

  $("#slider").slider({
    range: true,
    min: smin,
    max: smax,
    values: [smin, smax],
    slide: function(event, ui) {
      $("#min_price").val(ui.values[0]);
      return $("#max_price").val(ui.values[1]);
    }
  });

  $('#btn-booking').on("click", function(event) {
    reservation_target = $(this).parents('li').data('property-id');
    if (checkLogin()) {
      return console.log("OK");
    }
  });

  $('#auth-ajax-login').on("submit", function(event) {
    event.preventDefault();
    return console.log("loginHandler : " + loginHandler($(this).serialize()));
  });

  true;

}).call(this);
