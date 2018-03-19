const xhr = new XMLHttpRequest()

xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {

		const res = xhr.responseText;

		const tagList = document.getElementById('tagList');

		tagList.innerHTML = res;

	}
}

const form = document.getElementById('newTagForm');

form.addEventListener('submit', function(event) {
	event.preventDefault();
	
	const tagInputBox = document.getElementById('descriptionId').value;
	if (tagInputBox == "") {
		alert("Fill it out!");
		return false;
	}
	
	const reviewTagId = document.getElementById('reviewTagId').value;
	const descriptionId = document.getElementById('descriptionId').value;
	
	xhr.open("post", "http://localhost:8080/add-tag", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("reviewId=" + reviewTagId + "&description=" + descriptionId);
	
})

const removeForm = document.getElementById('deleteTagForm');
removeForm.addEventListener('submit', function(event) {
	event.preventDefault();
	const deleteTagId = document.getElementById('deleteTagId').value;
	const deleteTagDescriptionId = document
			.getElementById('deleteTagDescriptionId').value;

	xhr.open('POST', 'http://localhost:8080/delete-tag', true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("reviewId=" + deleteTagId + "&description="
			+ deleteTagDescriptionId);

})