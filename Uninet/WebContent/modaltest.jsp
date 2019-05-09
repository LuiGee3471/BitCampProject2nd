<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* Popup Box */

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 8888; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 10vh auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 90%; /* Could be more or less, depending on screen size */
}
@media (min-width: 1366px) {
  .modal-content {
    background-color: #fefefe;
    margin: 10vh auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 30%; /* Could be more or less, depending on screen size */
    }
}

/* The Close Button */
.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

button.button {
    background:none;
  border-top:none;
  border-right:none;
  border-left:none;
  border-bottom:#02274a 1px solid;
  padding:0 0 3px 0;
  font-size:16px;
}

button.button:hover {
  border-bottom:#a99567 1px solid;
  color:#a99567;
}
</style>
</head>
<body>
<p>
  <button class="button" data-modal="modalOne">
    Contact Us
  </button>
</p>
<p>
  <button class="button" data-modal="modalTwo">
    Send Us Hate Mail
  </button>
</p>

<div id="modalOne" class="modal">
  <div class="modal-content">
    <div class="contact-form">
      <a class="close">&times;</a>
      <h2>Ask A Question</h2><br>
    </div>
  </div>
</div>

<div id="modalTwo" class="modal">
  <div class="modal-content">
    <div class="contact-form">
      <span class="close">&times;</span>
      <h2>Haters Gonna Hate</h2><br>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
var modalBtns = [...document.querySelectorAll(".button")];
modalBtns.forEach(function(btn){
  btn.onclick = function() {
    var modal = btn.getAttribute('data-modal');
    document.getElementById(modal).style.display = "block";
  }
});

var closeBtns = [...document.querySelectorAll(".close")];
closeBtns.forEach(function(btn){
  btn.onclick = function() {
    var modal = btn.closest('.modal');
    modal.style.display = "none";
  }
});

window.onclick = function(event) {
  if (event.target.className === "modal") {
    event.target.style.display = "none";
  }
}


</script>
</html>