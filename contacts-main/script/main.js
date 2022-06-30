const init = () => {
    // $.load는 html을 읽어와 화면을 조립하기 위한 ajax 함수
    // ajax에는 CORS정책이 적용된다
    //    즉 ajax를 요청하는 주소와 응답하는 주소가 일치해야 한다
    //     www.naver.com에서 ad.naver.com로 ajax를 보내면 거부한다
    $("header").load("/contacts-main/fragment/header.html");
    $("nav").load("/contacts-main/fragment/nav.html");
    $("aside").load("/contacts-main/fragment/aside.html");
    $("footer").load("/contacts-main/fragment/footer.html");
  };