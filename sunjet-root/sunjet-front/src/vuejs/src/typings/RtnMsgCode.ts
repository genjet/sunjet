
enum RtnMsgCode {
    // success
    SUCCESS = 20000,
    //  invalid access token
    INVALID_ACCESS_TOKEN = 50001,
    // already login in other place
    ALREADY_LOGIN = 50002,
    //  access token expired
    ACCESS_TOKEN_EXPIRED = 50003,
    // invalid user (user not exist)
    INVALID_USER = 50004,
    // username or password is incorrect
    USERNAME_PASSWORD_WRONG = 50005,
    // 50008, 50012, 50014
}

export default RtnMsgCode
