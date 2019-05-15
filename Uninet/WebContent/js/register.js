function match(string, regexp) {
  return string.match(regexp);
}

function validate(input, regexp, warning) {
  input.keyup(function() {
    const val = input.val();
    const tip = input.siblings('.tip');

    if (!match(val, regexp)) {
      tip.removeClass('unseen');
      tip.addClass('warning');
      tip.text(warning);
    } else {
      tip.addClass('unseen');
    }
  });
}

$(function() {
  const emailRegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{3,}))$/;
  const pwdRegExp = /^.{4,}$/;
  const idRegExp = /^[0-9a-zA-Z]{6,}$/;

  const nameRegExp = /^[가-힣]{2,5}$/;
  const phoneRegExp = /^010\d{4}\d{4}$/;

  validate($('#pwd'), pwdRegExp, '4자 이상을 입력해주세요');
  validate($('#email'), emailRegExp, '올바른 이메일을 입력해주세요');

  validate($('#name'), nameRegExp, '2자 이상 5자 이하의 한글 이름');
  validate($('#phoneNumber'), phoneRegExp, '올바른 번호를 숫자로 입력');
});

$("#id").keyup(function() {
  const idRegExp = /^[0-9a-zA-Z]{6,}$/;
  const tip = $(this).siblings('.tip');
  const id = $(this).val();
  
  if (!match(id, idRegExp)) {
	  tip.removeClass("unseen");
    tip.addClass("warning");
    tip.text('올바른 아이디를 입력하세요');
  } else {
    $.ajax({
      url: "idcheck",
      method: "post",
      dataType: "html",
      data: {
        id: id
      },
      success: function(data) {
        if (data === "exists") {
          tip.text("이미 존재하는 아이디입니다");
        } else {
          tip.removeClass("warning");
          tip.text("사용 가능한 아이디입니다");
        }
      }
    })
  }
});

$('#pwdcheck').keyup(function() {
  const tip = $('#pwdcheck').siblings('.tip');
  if ($('#pwd').val() !== $('#pwdcheck').val()) {
    tip.removeClass('unseen');
    tip.addClass('warning');
    tip.text('비밀번호가 다릅니다');
  } else {
    tip.addClass('unseen');
  }
});

$('#registerForm').keypress(event => {
  if (event.keyCode === 13) {
    event.preventDefault();
    return false;
  }
});

$('#dept').change(() => {
  const second = $('#second-div');
  second.removeClass('unseen');
  second.addClass('seen');
});

$('#second-div input').blur(function() {
  const emailRegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{3,}))$/;
  const pwdRegExp = /^.{4,}$/;
  const idRegExp = /^[0-9a-zA-Z]{6,}$/;

  const id = $('#id').val();
  const pwd = $('#pwd').val();
  const pwdcheck = $("#pwdcheck").val();
  const email = $('#email').val();
  
  const idExists = $(".id").children("span").text();

  const check =
    idExists === "사용 가능한 아이디입니다" && match(pwd, pwdRegExp) && match(email, emailRegExp) && pwd === pwdcheck;

  if (check) {
	$('#third-div').removeClass('unseen');
	$('#third-div').addClass('seen');
    $('#second-div input').prop('readonly', true);
  }
});

$('#third-div input').blur(() => {
  const nameRegExp = /^[가-힣]{2,5}$/;
  const phoneRegExp = /^010\d{4}\d{4}$/;

  const name = $('#name').val();
  const phoneNumber = $('#phoneNumber').val();

  const check = match(name, nameRegExp) && match(phoneNumber, phoneRegExp);

  if (check) {
	$('#fourth-div').removeClass('unseen');
	$('#fourth-div').addClass('seen');
    $('#third-div input').prop('readonly', true);
  }
});

$("#birthday").keypress(function() {
  event.preventDefault();
});

$('#agree').change(() => {
  const submit = $('#btn');
  const agree = $('#agree');

  if (agree.is(':checked')) {
    submit.removeClass('unseen');
    submit.addClass('seen');
  } else {
    submit.removeClass('seen');
    submit.addClass('unseen');
  }
});

$("#btn").click(function() {
  if ($("#dept").val() === "") {
    event.preventDefault();
    alert('부서를 올바르게 선택해주세요.');
  }
})