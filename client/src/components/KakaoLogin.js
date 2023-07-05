const SocialKakao = () => {
  const Rest_api_key = 'f713b6d2bf71a965369f45b6f9867eea'; //REST API KEY
  const redirect_uri = 'http://localhost:3000/users/sign-up'; //Redirect URI
  // oauth 요청 URL
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`;
  const handleLogin = () => {
    window.location.href = kakaoURL;
  };
  return (
    <>
      <button onClick={handleLogin}>카카오 로그인</button>
    </>
  );
};
export default SocialKakao;
