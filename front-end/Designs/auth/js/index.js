var working = false;
$('.otp').hide();
$('.login_2').on('submit', function(e) {
  e.preventDefault();
  if (working) return;
  working = true;
  var $this = $(this),
    $state = $this.find('button > .state');
  $this.addClass('loading');
  $state.html('Authenticating');
  setTimeout(function() {
    $this.addClass('ok');
    $state.html('OTP Sent !');
    setTimeout(function() {
      $state.html('Enter OTP');
      $this.removeClass('ok loading');
      $('.login_2').hide();
      $('.otp').show();
      working = false;
    }, 4000);
  }, 3000);
});