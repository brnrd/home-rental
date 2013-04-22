###
JS - Base
Date of creation : 12/04/2013
Creator : Romain FONCIER
target : all (datepicker, ...)
comment :
###

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
        console.log selectedDate
        checkin = new Date(selectedDate)
        console.log checkin
        target = new Date(checkin.getFullYear(), checkin.getMonth(), checkin.getDate()+1)
        console.log target
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
        geocoder.geocode(
            address: item,
            (results, status) ->
                if status != google.maps.GeocoderStatus.OK
                    alert('Cannot find address')
                    return
                map.setCenter(results[0].geometry.location)
                map.setZoom(12)
        )
        item
)