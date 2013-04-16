###
JS - carousel
Date of creation : 12/04/2013
Creator : Romain FONCIER
target : home
comment : 
###

# In the case where the number of slide worth 5
# CONFIGURATION STEP #
MIN = 1
MAX = 5
TIME = 6000 # 6sec
current_slide = MIN;

# Update slide and selector
change_slide = (ind) ->    
    # Update CSS Class
    $('#frame-container li#loft_'+ind).addClass('active')
    $('#frame-container li#loft_'+current_slide).removeClass('active')

check_change = (ind) ->
    if ind > MAX
      change_slide(MIN)
      current_slide = MIN
    else
      change_slide(ind)
      current_slide = ind    

# Main function
jQuery ->
    setInterval ->
        check_change(current_slide+1)
    , TIME
    true