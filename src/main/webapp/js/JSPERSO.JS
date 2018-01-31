$(document).ready(function(){

 $('.show-password').click(function() {
    if($(this).prev('input').prop('type') == 'password') {
       //Si c'est un input type password
       $(this).prev('input').prop('type','text');
       $(this).removeClass('glyphicon glyphicon-eye-open');
       $(this).addClass('glyphicon glyphicon-eye-close');
    } else {
       //Sinon
       $(this).prev('input').prop('type','password');
       $(this).removeClass('glyphicon glyphicon-eye-close');
       $(this).addClass('glyphicon glyphicon-eye-open');
    }
 });

});
