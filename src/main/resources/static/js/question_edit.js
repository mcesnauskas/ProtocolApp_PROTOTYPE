document.getElementById('commentForm').addEventListener('submit', function(event) {
        var comment = document.getElementById('commentAreaId').value;
        if (comment == null || comment.trim() === '') {
            event.preventDefault();
            alert('Comment cannot be empty');
        }
    });