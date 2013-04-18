###
JS - Auth
Date of creation : 16/04/2013
Creator : Romain FONCIER
target : login, signup.
comment :
###

### Noty ###
notifyMessage = (type, msg) ->
    noty(
        text: msg
        type: type
        dismissQueue: false
        timeout: 2000
        layout: 'topCenter'
        theme: 'defaultTheme'
     )

authHandler = (dataToSend, target) ->
    $.post '/home-rental/'+target,
        dataToSend,
        (data) ->
            $('.ajax-loader').hide()
            data = JSON.parse data
            switch data.status
                when 'success'
                   #updateUserMenu()
                    notifyMessage('success', data.msg)
                when 'error' then notifyMessage('error', data.msg)

validForm = (array) ->
    valid = true
    i =0
    while valid and i < array.length
        valid = array[i].value.length > 0
    valid

##############
#   HANDLERS   #
##############

$('#signup').on "click", (event) ->
    $('#auth-modal-signup').modal('show')
    
$('#login').on "click", (event) ->
    $('#auth-modal-login .modal-body').load('/home-rental/login .modal-body')
    $('#auth-modal-login').modal('show')
    
### Call authHandler ###
$('#auth-process-signup').on "submit", (event) ->
    event.preventDefault()
    if validForm($(this).serializeArray())
        #authHandler($(this).serialize())
    else
        $('#auth-modal-signup .form-error').text("Warning ! All fields must be filled !")

$('#auth-process-login').on "submit", (event) ->
    event.preventDefault()
    $('.ajax-loader').show()
    authHandler($(this).serialize(), 'login')