const xhr = new XMLHttpRequest()

xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {

		const res = xhr.responseText;

		const tagList = document.getElementById('tagList');

		tagList.innerHTML = res;

	}
}

const addform = document.getElementById('addTagForm');

addform.addEventListener('submit', function(event) {
	event.preventDefault();
		
	const addTagId = document.getElementById('addTagId').value;
	const addTagDescriptionId = document.getElementById('addTagDescriptionId').value;
	if (addTagDescriptionId == "") {
	alert("Noting to add");
	return false;
	}
	
	xhr.open("post", "http://localhost:8080/add-tag", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("reviewId=" + addTagId + "&description=" + addTagDescriptionId);
	
})

const removeForm = document.getElementById('deleteTagForm');
removeForm.addEventListener('submit', function(event) {
	event.preventDefault();
	
	const deleteTagId = document.getElementById('deleteTagId').value;
	const deleteTagDescriptionId = document.getElementById('deleteTagDescriptionId').value;
	if (deleteTagDescriptionId == "") {
		alert("Noting to delete");
		return false;
		}

	xhr.open('POST', 'http://localhost:8080/delete-tag', true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("reviewId=" + deleteTagId + "&description="
			+ deleteTagDescriptionId);

})

//Many thanks to KC