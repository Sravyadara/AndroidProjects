jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        if(username == '') {
           $('#dis').slideDown().html("<span>Please type Username</span>");
return false;
        }
        {
$('#dis').slideDown().html('<span >Please type Password</span>');
return false;
}
   });

    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });

});