function changeQuestionStatus(id){
    if (confirm("Are you sure you want to change the status of this question?")){
        var form = document.getElementById("questionUpdateFormId")
        form.action = "/projects/" + projectId + "/questions/" + questionId + "/changeStatus";
        form.submit();
    }
}