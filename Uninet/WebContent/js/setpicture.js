$(function() {
	$("#upload").change(function(e) {
		event.preventDefault();

		var file = e.target.files[0];
		var url = URL.createObjectURL(file);
		$("#previewImage").attr("src", url);
	});
});