###
JS - User-properties
Date of creation : 28/04/2013
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
    $('#modal-property .modal-header h3').text('Modify this property')
    $('#modal-property .modal-body').load(context.url + ' #modify')
    $('#modal-property .modal-footer #submit' ).html('Save')
    $('#modal-property .modal-footer #submit' ).attr('form','modify-form')
    $('#modal-property .modal-footer #submit' ).removeClass('btn-danger').addClass('btn-success')
  else
    $('#modal-property .modal-header h3').text('Delete this property')
    $('#modal-property .modal-body').load(context.url + ' #delete')
    $('#modal-property .modal-footer #submit' ).html('Delete')
    $('#modal-property .modal-footer #submit' ).attr('form','delete-form')
    $('#modal-property .modal-footer #submit' ).removeClass('btn-success').addClass('btn-danger')
  
  $('#modal-property').modal('show')
  
  
# Modify handler
modifyHandler = (dataToSend) ->
  console.log(dataToSend)
  $.post '/home-rental/s/property/update',
  dataToSend
  (data) ->
    $('#modal-property').modal('hide')

# Delete handler
deleteHandler = (dataToSend) ->
  console.log(dataToSend)
  $.post '/home-rental/s/property/delete',
    dataToSend
    (data) ->
      $('#modal-property').modal('hide')


# Modify user click to open modal
$('#modify-property').on "click", (event) ->
  setContext('modify', '/home-rental/s/property/' + $('#property-id').val() + '/modal/')
  console.log context
  modalActionHandler()

# Delete user clieck to open modal
$('#delete-property').on "click", (event) ->
  setContext('delete', '/home-rental/s/property/' + $('#property-id').val() + '/modal/')
  modalActionHandler()

# Modify form submit
$('#modify-property').on "submit", (event) ->
  event.preventDefault()
  modifyHandler($(this).serialize())

# Delete form submit
$('#delete-property').on "submit", (event) ->
  event.preventDefault()
  deleteHandler($(this).serialize())