const deleteRequest = (path) => {
    let message = 'Are you sure to delete this?';
    if (confirm(message)) {
        fetch(path, {method: 'delete'})
            .then(_ => alert('Deleted.'));
    }
};
