###
JS - Auth
Date of creation : 16/04/2013
Creator : Romain FONCIER
target : login, signup.
comment :
###

##############
#   HANDLERS   #
##############

$('#signup').on "click", (event) ->
    $('#auth-modal-signup').modal('show')
    
$('#login').on "click", (event) ->
    $('#auth-modal-login').modal('show')