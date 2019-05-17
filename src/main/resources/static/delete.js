const deleteRequest = (path) => {
    let message = 'Are you sure to delete this?';
    if (confirm(message)) {
        fetch(path, {method: 'delete'})
            .then(_ => alert('Deleted.'));
    }
};

const moveMessageToFolder = (messageId, folderName) => {
    fetch(`/inbox/folders/${folderName}`, {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        credentials: 'include',
        body: `messageId=${messageId}`
    });
};
