$(document).ready(function () {
    setupEventHandlers();
});

function setupEventHandlers() {
    $('#loginButton').on('click', function (event) {
        event.preventDefault();
        login();
    });
}

function login() {
    var emailPrefix = $('#email-prefix').val();
    var email = emailPrefix + $('#login-email-suffix').text();
    var password = $('#password').val();

    $.ajax({
        url: '/process-login',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({email: email, password: password}),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken); // CSRF 토큰을 헤더에 추가
        },
        success: function (response) {
            handleLoginResponse(response);
        },
        error: function (xhr) {
            handleLoginError(xhr);
        }
    });
}

function handleLoginResponse(response) {
    getMessage(response.responseCode).done(function (message) {
        alert(message);  // 메시지를 경고창으로 표시
        if (response.success) {  // 로그인 성공 시
            window.location.href = response.redirectUrl;
        }
    }).fail(function () {
        $('#error-message').text('메시지를 가져오는 데 실패했습니다.').show();
    });
}

function handleLoginError(xhr) {
    if (xhr.status === 401) {  // Unauthorized
        var response = JSON.parse(xhr.responseText);
        getMessage(response.responseCode).done(function (message) {
            $('#error-message').text(message).show();
        }).fail(function () {
            $('#error-message').text('메시지를 가져오는 데 실패했습니다.').show();
        });
    } else {
        $('#error-message').text('로그인 실패: 서버 오류.').show();
    }
}