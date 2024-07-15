// common.js

// CSRF 토큰과 헤더 이름을 가져오는 함수
function getCsrfTokenAndHeader() {
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
    return {token: csrfToken, header: csrfHeader};
}

// 서버에서 메시지를 가져오는 함수
function getMessage(responseCode) {
    console.log("responseCode: " + responseCode)
    const {token, header} = getCsrfTokenAndHeader();
    return $.ajax({
        url: '/get-message',
        method: 'GET',
        data: {responseCode: responseCode},
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        }
    });
}