###
JS - Search
Date of creation : 23/04/2013
Creator : Romain FONCIER
target : search
comment :
###

### Utils methods ###
pluralize = (i, title) ->
    if i > 1 then i+" "+title+"s" else i+" "+title

jQuery ->
    # Filled the search bar with search parameters #
    params = $('.map-container #search-params').data('search-params')
    $('#search-bar #location-search').val(params[0])
    $('#search-bar #checkin').val(params[1])
    $('#search-bar #checkout').val(params[2])
    $('#search-bar #guests-number').val(params[3])
    $('#search-bar button.btn-dpd strong').text(pluralize(params[3], "guest"))
    
    # Init slider price range
    slider_params = params[4]
    $( "#slider" ).slider(
        range: true
        min: slider_params[0]
        max: slider_params[1]
        values: [slider_params[0], slider_params[1]],
        slide: ( event, ui ) ->
            #$( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
    )