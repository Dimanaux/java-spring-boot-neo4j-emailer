const sendCreateRequest = (folderName) => {
    fetch('/inbox/folders', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        credentials: 'include',
        body: `name=${folderName}`
    });
};

const getFolderName = () => {
    return prompt('Enter new folder name');
};

const createFolder = () => {
    sendCreateRequest(getFolderName());
};
