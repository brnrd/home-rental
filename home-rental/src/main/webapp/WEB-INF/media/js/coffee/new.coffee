###
JS - New
Date of creation : 17/04/2013
Creator : brnrd
target : all (typeahead, maps, ...)
###


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
                map.setZoom(12)
        )
        item
)

#############################
#   Split typeahead Maps    #
#############################


temp = $('#city-maps').split(",")
$('#city').text(temp[0])
$('#country').text(temp[temp.length - 1])

#############################
#   Format Datetime         #
#############################

$('#rentPeriodStart').text($('#rentStart') + " 00:00:00")
$('#rentPeriodStop').text($('#rentStop') + " 00:00:00")