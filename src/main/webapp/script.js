function fn_submit() {
   var fn = document.frm;

   //유효성 체크
   if (fn.custname.value == "") {   //입력창 custname부분이 null이면
      alert("회원성명이 입력되지 않았습니다.");
      fn.cstname.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.phone.value == "") {
      alert("회원전화가 입력되지 않았습니다.");
      fn.phone.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.address.value == "") {
      alert("회원주소가 입력되지 않았습니다.");
      fn.address.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.joindate.value == "") {
      alert("가입일자가 입력되지 않았습니다.");
      fn.joindate.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.grade.value == "") {
      alert("고객등급이 입력되지 않았습니다.");
      fn.grade.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.city.value == "") {
      alert("도시코드가 입력되지 않았습니다.");
      fn.city.focus();   //포커싱
      return false;   //함수 끝
   }
   fn.submit();   //
}