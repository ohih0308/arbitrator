$(document).ready(function () {
    $('#email-suffix').text(emailSuffix);

    initializeFormSubmission(csrfToken, emailSuffix);
    initializeBackButton();
});

function initializeFormSubmission(csrfToken, emailSuffix) {
    $('#registerForm').on('submit', async function (event) {
        event.preventDefault();
        var name = $('#name').val();
        var emailPrefix = $('#email-prefix').text();
        var email = emailPrefix + emailSuffix; // 이메일 접미사 추가
        var password = $('#register-password').val();
        var confirmPassword = $('#confirm-password').val();

        if (password !== confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        try {
            const response = await registerUser(name, email, password, csrfToken);
            if (response.success) {
                const message = await getMessage(response.responseCodes[0], csrfToken);
                alert(message);
                window.location.href = response.redirectUrl;
            } else {
                // handle errors
                handleErrors(response.responseCodes, csrfToken);
            }
        } catch (error) {
            console.log(error);
        }
    });
}

function initializeBackButton() {
    $('#backIcon').on('click', function () {
        window.history.back();
    });
}

function registerUser(name, email, password, csrfToken) {
    return $.ajax({
        url: '/perform-register',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({name: name, email: email, password: password}),
        beforeSend: function (xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
        }
    });
}

function handleErrors(responseCodes, csrfToken) {
    responseCodes.forEach(async function (code) {
        const message = await getMessage(code, csrfToken);
        alert(message);
    });
}