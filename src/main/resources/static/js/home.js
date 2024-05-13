document.getElementById('projectSelect').addEventListener('change', function() {
        var selectedOption = this.options[this.selectedIndex];
        var projectFullName = selectedOption.getAttribute('data-full-name');
        var projectId = selectedOption.value;
        document.getElementById('projectFullNameDisplay').textContent = projectFullName;
        document.getElementById('addQuestionsButton').href = '/project/' + projectId + '/questions';
        document.getElementById('addParticipantsButton').href = '/project/' + projectId + '/participants';
    });

    var buttons = document.querySelectorAll('#addQuestionsButton, #addParticipantsButton');

    buttons.forEach(function(button) {
        button.addEventListener('click', function(e) {
            var projectSelect = document.getElementById('projectSelect');
            if (projectSelect.selectedIndex === -1 || projectSelect.value === '') {
                e.preventDefault();
                alert('Please select a project first.');
            }
        });
    });
