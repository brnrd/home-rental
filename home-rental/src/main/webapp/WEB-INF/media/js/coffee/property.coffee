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
    $('#modal-property .modal-header h3').text('Modify this property')
    $('#modal-property .modal-body').load(context.url + ' #modify')
    $('#modal-property .modal-footer #submit' ).html('Save')
    $('#modal-property .modal-footer #submit' ).removeClass('btn-danger').addClass('btn-success')
  else
    $('#modal-property .modal-header h3').text('Delete this property')
    $('#modal-property .modal-body').load(context.url + ' #delete')
    $('#modal-property .modal-footer #submit' ).html('Delete')
    $('#modal-property .modal-footer #submit' ).removeClass('btn-success').addClass('btn-danger')
  
  $('#modal-property').modal('show')
  
  
modifyHandler = (dataToSend) ->
        $.post '/s/property/update',
                dataToSend
                (data) ->
                        $('#modal-property').modal('hide')
                        resetModal()

deleteHandler = (dataToSend) ->
        $.post '/s/property/delete',
                dataToSend
                (data) ->
                        $('#modal-property').modal('hide')
                        resetModal()
                        

# Modify property
$('#modify-property').on "click", (event) ->
  setContext('modify', '/home-rental/s/property/' + $('#property-id').val() + '/modal/')
  console.log context
  modalActionHandler()

# Delete property
$('#delete-property').on "click", (event) ->
  setContext('delete', '/home-rental/s/property/' + $('#property-id').val() + '/modal/')
  modalActionHandler()
  
# Submit modify
$('#modify-form').on "submit", (event) ->
  event.preventDefault()
  modifyHandler($(this).serialize())

# Submit delete
$('#delete-form').on "submit", (event) ->
  event.preventDefault()
  deleteHandler($(this).serialize())
