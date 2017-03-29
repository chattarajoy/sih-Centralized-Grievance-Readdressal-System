(function() {
    var staticPanel = $('.panel--static');
    var slidingPanel = $('.panel--sliding');
    
    var signupBtn = staticPanel.find('.btn.signup');
    var loginBtn = staticPanel.find('.btn.login');
    
    var signupContent = slidingPanel.find('.panel__content.signup');
    
    var loginContent = slidingPanel.find('.panel__content.login');

    signupBtn.on('click', function() {
        loginContent.hide();
        signupContent.show();
        slidingPanel.animate({
            'left': '4%'
        }, 550, 'easeInOutBack');
    });

    loginBtn.on('click', function() {
        signupContent.hide();
        loginContent.show();
        slidingPanel.animate({
            'left': '54%'
        }, 550, 'easeInOutBack');
    });
})();