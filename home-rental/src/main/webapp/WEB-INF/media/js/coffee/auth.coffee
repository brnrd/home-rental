###
JS - Auth
Date of creation : 16/04/2013
Creator : Romain FONCIER
target : login, signup.
comment :
###

#signupHandler = (dataToSend) ->

##############
#   HANDLERS   #
##############

$('#signup').on "click", (event) ->
    $('#auth-modal-signup').modal('show')
    
$('#login').on "click", (event) ->
    $('#auth-modal-login').modal('show')
    
### Call authHandler ###
$('#auth-modal-signup #auth-process').on "submit", (event) ->
        event.preventDefault()
        console.log "form : "+$(this).auth_username
        #signupHandler($(this).serialize())

$('#auth-modal-login #auth-process').on "submit", (event) ->
        event.preventDefault()
        loginHandler($(this).serialize())