moonTypeOptions =
    getTileUrl: (coord, zoom) ->
        normalizedCoord = getNormalizedCoord(coord, zoom)
        if not normalizedCoord
            return null
        bound = Math.pow(2, zoom)
        return 'http://mw1.google.com/mw-planetary/lunar/lunarmaps_v1/clem_bw' +
            '/' + zoom + '/' + normalizedCoord.x + '/' + (bound - normalizedCoord.y - 1) + '.jpg'
    tileSize: new google.maps.Size(256, 256)
    maxZoom: 9
    minZoom: 0
    radius: 1738000
    name: 'Moon'

moonMapType = new google.maps.ImageMapType(moonTypeOptions)

initialize = () ->
    myLatlng = new google.maps.LatLng(8, 7);
    mapOptions =
        center: myLatlng
        zoom: 3
        streetViewControl: false
        mapTypeControlOptions:
            mapTypeIds: ['moon']

    map = new google.maps.Map(document.getElementById('lunar-map'),mapOptions)
    map.mapTypes.set('moon', moonMapType);
    map.setMapTypeId('moon');

# Normalizes the coords that tiles repeat across the x axis (horizontally)
# like the standard Google map tiles.
getNormalizedCoord = (coord, zoom) ->
    y = coord.y
    x = coord.x

    # tile range in one direction range is dependent on zoom level
    # 0 = 1 tile, 1 = 2 tiles, 2 = 4 tiles, 3 = 8 tiles, etc
    tileRange = 1 << zoom

    # don't repeat across y-axis (vertically)
    if y < 0 || y >= tileRange
        return null

    # repeat across x-axis
    if x < 0 || x >= tileRange
        x = (x % tileRange + tileRange) % tileRange

    return {x: x, y: y}

google.maps.event.addDomListener(window, 'load', initialize)