
/*		<tr>
                              <td> <img src="images/blanc_asperge.png" class="img-responsive"></td>
                              <td> Blanc asperge</td>
                              <td><input type="text" placeholder="quantité" class="form-control"></td>
                              <td> <span class="glyphicon glyphicon-remove"></span></td>
                            </tr>			*/
}

$('#article_view').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var nom = button.data('nom') // Extract info from data-* attributes
    var description = button.data('description') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this)
    modal.find('.nom').text(nom)
    modal.find('.description').val(description)
})

