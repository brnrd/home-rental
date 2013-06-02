###
JS - User
Date of creation : 26/04/2013
Creator : brnrd
target : modal, submit form
###

context = type: null, url: null
# Modal handler
setContext = (type, url, username) ->
        context.type = type
        context.url = url
        
# Modal handler
modalActionHandler = () ->
  if context.type is "modify"
    $('#modal-user .modal-header h3').text('Modify your account')
    $('#modal-user .modal-body').load(context.url + ' #modify')
    $('#modal-user .modal-footer #submit').html('Save')
    $('#modal-user .modal-footer #submit').attr('form','modify-form')
    $('#modal-user .modal-footer #submit').removeClass('btn-danger').addClass('btn-success')
  else
    $('#modal-user .modal-header h3').text('Delete your account')
    $('#modal-user .modal-body').load(context.url + ' #delete')
    $('#modal-user .modal-footer #submit').html('Delete')
    $('#modal-user .modal-footer #submit').attr('form','delete-form')
    $('#modal-user .modal-footer #submit').removeClass('btn-success').addClass('btn-danger')
  
  $('#modal-user').modal('show')
  
# Modify handler
modifyHandler = (dataToSend) ->
  console.log(dataToSend)
  $.post '/home-rental/s/' + $('#page-header p').text() + '/update',
  dataToSend
  (data) ->
    $('#modal-user').modal('hide')

# Delete handler
deleteHandler = (dataToSend) ->
  console.log(dataToSend)
  $.post '/home-rental/s/' + $('#page-header p').text() + '/delete',
    dataToSend
    (data) ->
      $('#modal-user').modal('hide')


# Modify user click to open modal
$('#modify-user').on "click", (event) ->
  setContext('modify', '/home-rental/s/' + $('#page-header p').text() + '/modal')
  console.log context
  modalActionHandler()

# Delete user clieck to open modal
$('#delete-user').on "click", (event) ->
  setContext('delete', '/home-rental/s/' + $('#page-header p').text() + '/modal')
  modalActionHandler()

# Modify form submit
$('#modify-form').on "submit", (event) ->
  event.preventDefault()
  modifyHandler($(this).serialize())

# Delete form submit
$('#delete-form').on "submit", (event) ->
  event.preventDefault()
  deleteHandler($(this).serialize())