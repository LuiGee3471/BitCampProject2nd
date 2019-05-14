function match(string, regexp) {
  return string.match(regexp);
}

$('#registerForm').keypress((event) => {
  if (event.keyCode === 13) {
    event.preventDefault();
    return false;
  }
});

$("#dept").change(() => {
  const second = $('#second-div');
  second.removeClass('unseen');
  second.addClass('seen');
});

$('#second-div input').keyup(() => {
  const emailRegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{3,}))$/;
  const pwdRegExp = /^.{4,}$/;
  const idRegExp = /^[0-9a-zA-Z]{6,}$/;

  const email = $('#email');
  const id = $('#id');
  const pwd = $('#pwd');
  const pwdCheck = $('#pwdcheck');

  const third = $('#third-div');
  if (
    match(id.val(), idRegExp)
    && pwd.val() === pwdCheck.val()
    && match(pwd.val(), pwdRegExp)
    && match(email.val(), emailRegExp)
  ) {
    third.removeClass('unseen');
    third.addClass('seen');
  }
});

$('#third-div input').keyup(() => {
  const fourth = $('#fourth-div');

  const nameRegExp = /^[가-힣]{2,5}$/;
  const phoneRegExp = /^010\d{4}\d{4}$/;

  const name = $("#name");
  const phoneNumber = $('#phoneNumber');
  if (phoneNumber.val().match(phoneRegExp)) {
    fourth.removeClass('unseen');
    fourth.addClass('seen');
  }
});

agree.change(() => {
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