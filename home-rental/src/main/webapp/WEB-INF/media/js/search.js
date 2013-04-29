
/*
JS - Search
Date of creation : 23/04/2013
Creator : Romain FONCIER
target : search
comment :
*/


(function() {
  var checkLogin, loadAjaxLoginModal, loginHandler, notifyMessage, param, pluralize, reservation_target;

  param = null;

  reservation_target = null;

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
    $('#ajax-login-modal .modal-header h3').text("Home Rental Login");
    $('#ajax-login-modal #btn-ajax-login').val('Login');
    $('#ajax-login-modal .modal-body').load('/home-rental/login-ajax .auth-body');
    return $('#ajax-login-modal').modal('show');
  };

  checkLogin = function() {
    if ($('.user-logged').length > 0) {
      return true;
    } else {
      loadAjaxLoginModal();
      return false;
    }
  };

  loginHandler = function(dataToSend) {
    $('.ajax-loader').show();
    return $.post('login-ajax', dataToSendj, function(data) {
      $('.ajax-loader').hide();
      switch (data.status) {
        case 'success':
          notifyMessage("success", "You are successfully logged");
          return true;
        case 'error':
          $('#ajax-login-modal .form-error span').text("Warning ! Your username/password are incorrects !");
          $('#ajax-login-modal .field-container').each(function(item) {
            return item.addClass('error');
          });
          return false;
      }
    });
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
        return prepareModalForReservation();
      }
    });
    return $('#auth-process-ajax-login').on("submit", function(event) {
      event.preventDefault();
      if (loginHandler($(this).serialize())) {
        return prepareModalForReservation();
      }
    });
  });

}).call(this);
