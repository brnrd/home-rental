###
JS - Property
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
    $('#modal-property .modal-header').load(context.url + ' #modify-header')
    $('#modal-property .modal-body').load(context.url + ' #modify-form')
    $('#modal-property .modal-footer').load(context.url + ' #modify-buttons')
  else
    $('#modal-property .modal-header').load(context.url + ' #delete-header')
    $('#modal-property .modal-body').load(context.url + ' #delete-body')
    $('#modal-property .modal-footer').load(context.url + ' #delete-buttons')
  
  $('#modal-property').modal('show')

# Modify user
$('#modify-property').on "click", (event) ->
  setContext('modify', '/home-rental/s/property/' + $('#property-id').val() + '/modal/')
  console.log context
  modalActionHandler()

# Delete user
$('#delete-property').on "click", (event) ->
  setContext('delete', '/home-rental/s/property/' + $('#property-id').val() + '/modal/')
  modalActionHandler()
  
