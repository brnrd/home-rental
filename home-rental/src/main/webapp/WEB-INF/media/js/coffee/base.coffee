###
JS - Base
Date of creation : 12/04/2013
Creator : Romain FONCIER
target : all (datepicker, ...)
comment :
###

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

if $('#new_user').length > 0
  notifyMessage("success", "Your new account has been successfully created")
  
if $('#logged_user').length > 0
  notifyMessage("success", "You are successfully logged")
  
if $('#logout_success').length > 0
  notifyMessage("success", "You are successfully logged out")

### Guests selector ###
$('#search-bar #guests-list li a').on "click", (event) ->
    event.preventDefault()
    nb = $(this).text().split(" ")[0]
    
    # Update button and input hidden
    $('#search-bar button.btn-dpd strong').text(pluralize(nb, "guest"))
    $('#search-bar #guests-number').val(nb)

##############
#   Datepicker   #
##############

ckeckin = null
target = null

formatDate = (date) ->
    res = date.toLocaleString().split(" ")[0]
    t_res = res.split("/")
    if t_res[0].length == 1
        return '0'+t_res[0]+"/"+t_res[1]+"/"+t_res[2]
    return t_res.join("/")

$('#checkin').datepicker(
    onClose: (selectedDate) ->
        checkin = new Date(selectedDate)
        target = new Date(checkin.getFullYear(), checkin.getMonth(), checkin.getDate()+1)
        $('#checkout').datepicker( "option", "minDate", selectedDate)
        $('#checkout').val(formatDate(target))
)

$('#checkout').datepicker(
    defaultDate: if target then target else ''
    onClose: (selectedDate) ->
        if checkin
            $('#checkin').datepicker('option', 'maxDate', selectedDate)
)

#############################
#   Typeahead Google Maps   #
#############################

getCoordinates = (location) ->
    $.getJSON 'http://maps.googleapis.com/maps/api/geocode/json?address='+location.replace(' ', '+')+'&sensor=false',
        (data) ->
            coord = data.results[0].geometry.location
            coord.lat+','+coord.lng

service = new google.maps.places.AutocompleteService()
geocoder = new google.maps.Geocoder()

$('#location-search').typeahead(
    source: (query, process) ->
        service.getPlacePredictions(
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
        $('#search-bar #latlong').val(getCoordinates(item))
        item
)