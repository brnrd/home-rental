###
JS - New
Date of creation : 17/04/2013
Creator : brnrd
target : all (typeahead, maps, ...)
###


###################
#   Google Maps   #
###################

map = null

initialize = (lat, long) ->
    map = new google.maps.Map(
      $('#map-canvas')[0], 
        zoom: 9 
        center: new google.maps.LatLng(lat, long)
        mapTypeId: google.maps.MapTypeId.ROADMAP
    )

getLocation = () -> 
  $.getJSON("http://jsonip.com?callback=?", (data) ->
    $.getJSON("http://freegeoip.net/json/" + data.ip, (fulldata) ->
      currentLat= fulldata.latitude
      currentLong = fulldata.longitude
      google.maps.event.addDomListener(window, 'load', initialize(currentLat, currentLong))
    )
  )
      
$(document).ready getLocation
##################
#   Datepicker   #
##################

ckeckin = null
target = null

formatDate = (date) ->
    res = date.toLocaleString().split(" ")[0]
    t_res = res.split("/")
    if t_res[0].length == 1
        return '0'+t_res[0]+"/"+t_res[1]+"/"+t_res[2]
    return t_res.join("/")

$('#rentStart').datepicker(
    onClose: (selectedDate) ->
        console.log selectedDate
        checkin = new Date(selectedDate)
        console.log checkin
        target = new Date(checkin.getFullYear(), checkin.getMonth(), checkin.getDate()+7)
        console.log target
        $('#rentStop').datepicker('option', 'minDate', selectedDate)
        $('#rentStop').val(formatDate(target))
)

$('#rentStop').datepicker(
    defaultDate: if target then target else ''
    onClose: (selectedDate) ->
        if checkin
            $('#rentStart').datepicker('option', 'maxDate', selectedDate)
)

#############################
#   Typeahead Google Maps   #
#############################

service = new google.maps.places.AutocompleteService()
geocoder = new google.maps.Geocoder()

$('#city-maps').typeahead(
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
                console.log results[0]
                console.log map
                map.setZoom(12)
        )
        splitCity(item)
        item
)

#############################
#   Split typeahead Maps    #
#############################

splitCity = (item) =>
  if item 
    maps_data = $.trim(item).split(",")  
#   If there is a city, a state and a country
    if maps_data.length > 2
      $('#city').val(maps_data[0] + ", " + maps_data[1])
      $('#country').val(maps_data[maps_data.length - 1])
#   If there is a city and a state/country
    else if maps_data.length == 2
#     If the length of the second elem is 2 then it's a american state
      if maps_data[1].length == 2
        $('#city').val(maps_data[0] + ", " +maps_data[1])
#       So add USA as country
        $('#country').val("USA")
#     else that's the city country pattern  
      else
        $('#city').val(maps_data[0])
        $('#country').val(maps_data[maps_data.length - 1])
#   else that's only a country, so nothing is add in the city input 
    else
      $('#country').val(maps_data[0])