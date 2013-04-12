###
JS - Base
Date of creation : 12/04/2013
Creator : Romain FONCIER
target : all (datepicker, ...)
comment : 
###

ckeckin = null
target = null

formatDate = (date) ->
    res = date.toLocaleString().split(" ")[0]
    t_res = res.split("/")
    if t_res[0].length == 1
        return '0'+t_res[0]+"/"+t_res[1]+"/"+t_res[2]
    return t_res.join("/")

$('#checkin').datepicker(
    onClose: (selectedDate) ->
        console.log selectedDate
        checkin = new Date(selectedDate)
        console.log checkin
        target = new Date(checkin.getFullYear(), checkin.getMonth(), checkin.getDate()+1)
        console.log target
        $('#checkout').datepicker( "option", "minDate", selectedDate)
        $('#checkout').val(formatDate(target))
)
        
$('#checkout').datepicker(
    defaultDate: if target then target else ''
    onClose: (selectedDate) ->
        if checkin
            $('#checkin').datepicker('option', 'maxDate', selectedDate)
)