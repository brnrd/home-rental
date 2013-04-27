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