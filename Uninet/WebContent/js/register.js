const dept = $('#dept');
const second = $('#second-div');
const third = $('#third-div');
const fourth = $('#fourth-div');
const submit = $('#btn');
const email = $('#email');
const phoneNumber = $('#phoneNumber');
const agree = $('#agree');

const emailRegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{3,}))$/;
const phoneRegExp = /^010([0-9]{4})([0-9]{4})$/;

$("#registerForm").keypress(function(event) {
	if (event.keyCode == 13) {
		event.preventDefault();
		return false;
	}
});

dept.change(() => {
  second.removeClass('unseen');
});

email.keyup(() => {
  if (email.val().match(emailRegExp)) {
    third.removeClass('unseen');
  } else {
    console.log('틀린 이메일');
  }
});

phoneNumber.keyup(() => {
  if (phoneNumber.val().match(phoneRegExp)) {
    fourth.removeClass('unseen');
  }
});

agree.change(() => {
  if (agree.is(':checked')) {
    submit.removeClass('unseen');
  } else {
    submit.addClass('unseen');
  }
});
