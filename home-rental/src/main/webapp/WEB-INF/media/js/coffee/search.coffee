###
JS - Search
Date of creation : 23/04/2013
Creator : Romain FONCIER
target : search
comment :
###

param = null
reservation_target = reservation_title = reservation_price = null

### Utils methods ###
calculatePrice = (number) ->
    # the rental prices are indicated for two persons
   Math.ceil(number / 2) * reservation_price

pluralize = (i, title) ->
    if i > 1 then i+" "+title+"s" else i+" "+title

### Noty ###
notifyMessage = (type, msg) ->
    noty (
      text: msg
      type: type
      dismissQueue: false
      timeout: 2000
      layout: 'topCenter'
      theme: 'defaultTheme'
    )
    
updateUserMenu = (data) ->
    # Create the ".user-logged" div
    user_logged = "<div class=\"user-logged\"><div class=\"btn-group\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\"><span class=\"username\"><strong>" + data.username + "</strong></span><span class=\"caret\"></span></a><ul class=\"dropdown-menu\" role=\"menu\" aria-labelledby=\"dropdownMenu\"><li><a tabindex=\"-1\" href=\"/home-rental/s/account/" + data.username + "\">Account</a></li><li><a tabindex=\"-1\" href=\"/home-rental/s/account/" + data.username + "/properties\">My Properties</a></li><li><a tabindex=\"-1\" href=\"/home-rental/s/account/" + data.username + "/reservations\">My Reservations</a></li><li class=\"divider\"></li><li><a tabindex=\"-1\" href=\"/home-rental/j_spring_security_logout\">Signout</a></li></ul></div></div>"
    
    # Remove btn loggin and add new user-logged div
    $('.login').find('.btn-login').remove()
    $('.login').prepend(user_logged)

prepareModalForReservation = () ->
    # Get data from search bar and fill in modal
    checkin = $('#search-bar #checkin').val()
    checkout = $('#search-bar #checkout').val()
    guests = $('#search-bar #guests-number').val()
    
    $('#booking-modal .booking-item input[name="date_rent_start"]').val(checkin)
    $('#booking-modal .booking-desc #b-checkin').html(checkin)
    $('#booking-modal .booking-item input[name="date_rent_stop"]').val(checkout)
    $('#booking-modal .booking-desc #b-checkout').html(checkout)
    $('#booking-modal .booking-item input[name="guests_number"]').val(guests)
    $('#booking-modal .booking-desc #b-guests').html(guests)
    
    # Get property title and calculate price
    $('#booking-modal .booking-item input[name="target_property"]').val(reservation_target)
    $('#booking-modal .booking-desc #b-title').html(reservation_title)
    price = calculatePrice(parseInt(guests))
    $('#booking-modal .booking-item input[name="price"]').val(price)
    $('#booking-modal .booking-desc #b-price').html(price)
    
    # Display modal
    $('#booking-modal').modal('show')
    true

checkLogin = () ->
    if $('.user-logged').length > 0
        true
    else
        $('#ajax-login-modal').modal('show')
        $('#ajax-login-modal #auth_username').focus()
        false

loginHandler = (dataToSend) ->
    $('.ajax-loader').show()
    $.post '/home-rental/ajax-login',
        dataToSend
        (data) ->
            $('.ajax-loader').hide()
            data = JSON.parse data
            switch data.status
                when 'success'
                    $('#ajax-login-modal').modal('hide')
                    notifyMessage("success", "You are successfully logged")
                    updateUserMenu(data)
                    prepareModalForReservation()
                    true
                when 'error'
                    $('#ajax-login-modal .form-error span').html("Warning ! Your username/password are incorrects !")
                    $('#ajax-login-modal .field-container').each (item) ->
                        item.addClass('error')
                    false
 
 bookingHandler = (dataToSend) ->
    $('.ajax-loader').show()
    $.post '/home-rental/s/booking',
        dataToSend,
        (data) ->
            $('.ajax-loader').hide()
            data = JSON.parse data
            switch (data.status)
                when 'success'
                    $('#booking-modal').modal('hide')
                    notifyMessage("success", "Your booking has been successfully registered")
                when 'error'
                    $('#booking-modal').modal('hide')
                    notifyMessage("error", "Warning ! An error has been encountered during sending data. Please, try again !")

sendSearchRequest = () ->
  # Get form
  dataToSend = $('#hr-search_form').serialize()
  console.log dataToSend
  
  # Display modal
  $('#ajax-search-modal').modal('show')

################
### HANDLERS ###
################

# Typeahead Google Maps
gService = new google.maps.places.AutocompleteService()

$('#hr-location-search').typeahead(
    source: (query, process) ->
        gService.getPlacePredictions(
            input: query,
            (predictions, status) ->
                if status == google.maps.places.PlacesServiceStatus.OK
                    process(
                        $.map(
                            predictions,
                            (prediction) ->
                                prediction.description
                                
                        )
                    )
        )
    ,
    updater: (item) ->
        # Get the lat/long coordinates and save them in hidden
        location = item
        address = 'http://maps.googleapis.com/maps/api/geocode/json?address='+location.replace(', ', '+').replace(' ', '+')+'&sensor=false'
        $.getJSON address,
            (data) ->
                coord = data.results[0].geometry.location
                console.log coord
                $('.searcher-bar #hr-latlong').val(coord.lat+","+coord.lng)
                
                # Call to sendSearchRequest
                sendSearchRequest()
        item
)

# Datepicker
hr_checkin = null
hr_target = null

formatDate = (date) ->
    res = date.toLocaleString().split(" ")[0]
    t_res = res.split("/")
    if t_res[0].length == 1
        return '0'+t_res[0]+"/"+t_res[1]+"/"+t_res[2]
    return t_res.join("/")

$('#hr-checkin').datepicker(
    onClose: (selectedDate) ->
        hr_checkin = new Date(selectedDate)
        if not $('#hr-checkout').val()?
            hr_target = new Date(hr_checkin.getFullYear(), hr_checkin.getMonth(), hr_checkin.getDate()+1)
            $('#hr-checkout').datepicker( "option", "minDate", selectedDate)
            $('#hr-checkout').val(formatDate(hr_target))
        
        # Call to sendSearchRequest
        sendSearchRequest()
)

$('#hr-checkout').datepicker(
    onClose: (selectedDate) ->
        if hr_checkin?
            $('#hr-checkin').datepicker('option', 'maxDate', selectedDate)
            
        # Call to sendSearchRequest
        sendSearchRequest()
)

# Guests selector
$('.searcher-bar #hr-guests-list li a').on "click", (event) ->
    event.preventDefault()
    nb = $(this).text().split(" ")[0]
    
    # Update button and input hidden
    $('#hr-search-bar button.btn-dpd strong').text(pluralize(nb, "guest"))
    $('#hr-search-bar #hr-guests-number').val(nb)
    
    # Call to sendSearchRequest
    sendSearchRequest()

# Types & Options selectors
$('#hr-search_form input:checkbox').on "change", (event) ->
    # Call to sendSearchRequest
    sendSearchRequest()

# Init slider price range
smin = parseInt($('.map-wrapper #min_price').text())
smax = parseInt($('.map-wrapper #max_price').text())
$( "#slider" ).slider(
    range: true
    min: smin
    max: smax
    values: [smin, smax]
    slide: (event, ui) ->
        $( "#min_price" ).html( ui.values[ 0 ] )
        $( "#max_price" ).html( ui.values[ 1 ] )
    stop: (event, ui) ->
        $( "#hr-min_price" ).val( ui.values[ 0 ] )
        $( "#hr-max_price" ).val( ui.values[ 1 ] )
        
        # Call to sendSearchRequest
        sendSearchRequest()
)

# Handle ajax login and booking
$('#btn-booking').on "click", (event) ->
    prop = $(this).parents('li')
    reservation_target = $(prop).data('property-id')
    reservation_title = $(prop).find('.item-content-title').text()
    reservation_price = $(prop).find('.item-action-price').text().split("$")[1]
    if checkLogin()
        prepareModalForReservation()

$('#auth-ajax-login').on "submit", (event) ->
    event.preventDefault()
    loginHandler($(this).serialize())
    
$('#booking-process').on "submit", (event) ->
    event.preventDefault()
    bookingHandler($(this).serialize())
    
true