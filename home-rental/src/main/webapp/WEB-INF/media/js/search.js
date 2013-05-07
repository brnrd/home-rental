
/*
JS - Search
Date of creation : 23/04/2013
Creator : Romain FONCIER
target : search
comment :
*/


(function() {
  var bookingHandler, calculatePrice, checkLogin, loginHandler, notifyMessage, param, params, pluralize, prepareModalForReservation, reservation_price, reservation_target, reservation_title, smax, smin, updateUserMenu;

  param = null;

  reservation_target = reservation_title = reservation_price = null;

  /* Utils methods
  */


  pluralize = function(i, title) {
    if (i > 1) {
      return i + " " + title + "s";
    } else {
      return i + " " + title;
    }
  };

  calculatePrice = function(number) {
    return Math.ceil(number / 2) * reservation_price;
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

  updateUserMenu = function(data) {
    var user_logged;
    user_logged = "<div class=\"user-logged\"><div class=\"btn-group\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\"><span class=\"username\"><strong>" + data.username + "</strong></span><span class=\"caret\"></span></a><ul class=\"dropdown-menu\" role=\"menu\" aria-labelledby=\"dropdownMenu\"><li><a tabindex=\"-1\" href=\"/home-rental/s/account/" + data.username + "\">Account</a></li><li><a tabindex=\"-1\" href=\"/home-rental/s/account/" + data.username + "/properties\">My Properties</a></li><li><a tabindex=\"-1\" href=\"/home-rental/s/account/" + data.username + "/reservations\">My Reservations</a></li><li class=\"divider\"></li><li><a tabindex=\"-1\" href=\"/home-rental/j_spring_security_logout\">Signout</a></li></ul></div></div>";
    $('.login').find('.btn-login').remove();
    return $('.login').prepend(user_logged);
  };

  prepareModalForReservation = function() {
    var checkin, checkout, guests, price;
    checkin = $('#search-bar #checkin').val();
    checkout = $('#search-bar #checkout').val();
    guests = $('#search-bar #guests-number').val();
    $('#booking-modal .booking-item input[name="date_rent_start"]').val(checkin);
    $('#booking-modal .booking-desc #b-checkin').html(checkin);
    $('#booking-modal .booking-item input[name="date_rent_stop"]').val(checkout);
    $('#booking-modal .booking-desc #b-checkout').html(checkout);
    $('#booking-modal .booking-item input[name="guests_number"]').val(guests);
    $('#booking-modal .booking-desc #b-guests').html(guests);
    $('#booking-modal .booking-item input[name="target_property"]').val(reservation_target);
    $('#booking-modal .booking-desc #b-title').html(reservation_title);
    price = calculatePrice(parseInt(guests));
    $('#booking-modal .booking-item input[name="price"]').val(price);
    $('#booking-modal .booking-desc #b-price').html(price);
    $('#booking-modal').modal('show');
    return true;
  };

  checkLogin = function() {
    if ($('.user-logged').length > 0) {
      return true;
    } else {
      $('#ajax-login-modal').modal('show');
      $('#ajax-login-modal #auth_username').focus();
      return false;
    }
  };

  loginHandler = function(dataToSend) {
    $('.ajax-loader').show();
    return $.post('/home-rental/ajax-login', dataToSend, function(data) {
      $('.ajax-loader').hide();
      data = JSON.parse(data);
      switch (data.status) {
        case 'success':
          $('#ajax-login-modal').modal('hide');
          notifyMessage("success", "You are successfully logged");
          updateUserMenu(data);
          prepareModalForReservation();
          return true;
        case 'error':
          $('#ajax-login-modal .form-error span').html("Warning ! Your username/password are incorrects !");
          $('#ajax-login-modal .field-container').each(function(item) {
            return item.addClass('error');
          });
          return false;
      }
    });
  };

  bookingHandler = function(dataToSend) {
    $('.ajax-loader').show();
    return $.post('/home-rental/booking', dataToSend, function(data) {
      $('.ajax-loader').hide();
      data = JSON.parse(data);
      switch (data.status) {
        case 'success':
          $('#booking-modal').modal('hide');
          return notifyMessage("success", "Your booking has been successfully registered");
        case 'error':
          $('#booking-modal').modal('hide');
          return notifyMessage("error", "Warning ! An error has been encountered during sending data. Please, try again !");
      }
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
    var prop;
    prop = $(this).parents('li');
    reservation_target = $(prop).data('property-id');
    reservation_title = $(prop).find('.item-content-title').text();
    reservation_price = $(prop).find('.item-action-price').text().split("$")[1];
    if (checkLogin()) {
      return console.log("OK");
    }
  });

  $('#auth-ajax-login').on("submit", function(event) {
    event.preventDefault();
    return loginHandler($(this).serialize());
  });

  $('#booking-process').on("submit", function(event) {
    event.preventDefault();
    return bookingHandler($(this).serialize());
  });

  true;

}).call(this);
