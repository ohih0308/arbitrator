// register.js

$(document).ready(function () {
    setupEventHandlers();
});

function setupEventHandlers() {
    $('#registerForm').on('submit', function (event) {
        event.preventDefault();
        register();
    });
}

function register() {
    var name = $('#name').val();
    var emailPrefix = $('#email-prefix').val();
    var email = emailPrefix + $('#login-email-suffix').text();
    var password = $('#register-password').val();
    var confirmPassword = $('#confirm-password').val();

    if (password !== confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }

    const {token, header} = getCsrfTokenAndHeader();

    $.ajax({
        url: '/perform-register',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({name: name, email: email, password: password}),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (response) {
            handleRegisterResponse(response);
        },
        error: function (xhr) {
            handleRegisterError(xhr);
        }
    });
}


function handleRegisterResponse(response) {
    var promises = [];

    response.responseCodes.forEach(function (code) {
        promises.push(getMessage(code));
    });

    $.when.apply($, promises).done(function () {
        var messages = Array.prototype.slice.call(arguments);

        messages.forEach(function (message) {
            alert(message[0]); // 각 메시지를 경고창으로 표시
        });

        if (response.success) {
            window.location.href = response.redirectUrl;
        }
    }).fail(function () {
        $('#error-message').text('메시지를 가져오는 데 실패했습니다.').show();
    });
}

function handleRegisterError(xhr) {
    if (xhr.status === 400) {  // Bad Request
        var response = JSON.parse(xhr.responseText);
        getMessage(response.responseCode).done(function (message) {
            $('#error-message').text(message).show();
        }).fail(function () {
            $('#error-message').text('메시지를 가져오는 데 실패했습니다.').show();
        });
    } else {
        $('#error-message').text('회원가입 실패: 서버 오류.').show();
    }
}