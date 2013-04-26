###
JS - User
Date of creation : 26/04/2013
Creator : brnrd
target : modal, submit form
###

context = type: null, url: null

# Modal handler


setContext = (type, url) ->
        context.type = type
        context.url = url

modalActionHandler = () ->
  if context.type is "modify"
    $('#modal-user .modal-header').load(context.url + ' #modify-header')
    $('#modal-user .modal-body').load(context.url + ' #modify-form')
    $('#modal-user .modal-footer').load(context.url + ' #modify-buttons')
  else
    $('#modal-user .modal-header').load(context.url + ' #delete-header')
    $('#modal-user .modal-body').load(context.url + ' #delete-body')
    $('#modal-user .modal-footer').load(context.url + ' #delete-buttons')
  
  $('#modal-user').modal('show')

# Modify user
$('#modify-user').on "click", (event) ->
  setContext('modify', '/home-rental/s/account/modal/')
  console.log context
  modalActionHandler()

# Delete user
$('#delete-user').on "click", (event) ->
  setContext('delete', '/home-rental/s/account/modal/')
  modalActionHandler()
  
