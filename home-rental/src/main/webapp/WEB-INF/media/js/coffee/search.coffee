###
JS - Search
Date of creation : 23/04/2013
Creator : Romain FONCIER
target : search
comment :
###

param = null
reservation_target = null
authentication_success = false

### Utils methods ###
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

checkLogin = () ->
    if $('.user-logged').length > 0
        true
    else
        $('#ajax-login-modal').modal('show')
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
                    authentication_success = true
                when 'error'
                    $('#ajax-login-modal .form-error span').html("Warning ! Your username/password are incorrects !")
                    $('#ajax-login-modal .field-container').each (item) ->
                        item.addClass('error')
                    false
                    
################
### HANDLERS ###
################

# Filled the search bar with search parameters #
params = $('.map-wrapper #search-params').data('search-params')
$('#search-bar #location-search').val(params[0])
$('#search-bar #checkin').val(params[1])
$('#search-bar #checkout').val(params[2])
$('#search-bar #guests-number').val(params[3])
$('#search-bar button.btn-dpd strong').text(pluralize(params[3], "guest"))

# Init slider price range
smin = $('.map-wrapper #min_price').text()
smax = $('.map-wrapper #max_price').text()
$( "#slider" ).slider(
    range: true
    min: smin
    max: smax
    values: [smin, smax],
    slide: ( event, ui ) ->
        $( "#min_price" ).val( ui.values[ 0 ] )
        $( "#max_price" ).val( ui.values[ 1 ] )
)

# Handle ajax login and booking
$('#btn-booking').on "click", (event) ->
    reservation_target = $(this).parents('li').data('property-id')
    if checkLogin()
        console.log "OK"
        #prepareModalForReservation()

$('#auth-ajax-login').on "submit", (event) ->
    event.preventDefault()
    loginHandler($(this).serialize())
    
    if authentication_success
        console.log "prepareModalForReservation"
    #    prepareModalForReservation()
true