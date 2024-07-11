function getMessage(responseCode) {
    var csrf = getCsrfToken();
    return $.ajax({
        url: '/get-message',
        method: 'GET',
        data: { responseCode: responseCode },
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrf.header, csrf.token); // CSRF 토큰을 헤더에 추가
        }
    });
}

function getCsrfToken() {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
    return { token: csrfToken, header: csrfHeader };
}