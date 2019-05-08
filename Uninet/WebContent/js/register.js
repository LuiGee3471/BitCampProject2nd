const dept = $('#dept');
const second = $('#second-div');
const third = $('#third-div');
const fourth = $('#fourth-div');
const submit = $('#btn');
const email = $('#email');
const phoneNumber = $('#phoneNumber');
const agree = $('#agree');
const id = $('#id');
const pwd = $('#pwd');
const pwdCheck = $('#pwdcheck');

const emailRegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{3,}))$/;
const phoneRegExp = /^010([0-9]{4})([0-9]{4})$/;
const pwdRegExp = /^.{4,}$/;
const idRegExp = /^[0-9a-zA-Z]{6,}$/;

function match(string, regexp) {
  return string.match(regexp);
}

$('#registerForm').keypress((event) => {
  if (event.keyCode == 13) {
    event.preventDefault();
    return false;
  }
});

dept.change(() => {
  second.removeClass('unseen');
  second.addClass('seen');
});

$('#second-div input').keyup(() => {
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

phoneNumber.keyup(() => {
  if (phoneNumber.val().match(phoneRegExp)) {
    fourth.removeClass('unseen');
    fourth.addClass('seen');
  }
});

agree.change(() => {
  if (agree.is(':checked')) {
    submit.removeClass('unseen');
    submit.addClass('seen');
  } else {
    submit.removeClass('seen');
    submit.addClass('unseen');
  }
});
