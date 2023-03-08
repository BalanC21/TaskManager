const BASE_URL_ENV = process.env.REACT_APP_BASE_URL;

const genericFetch = (url, method = "GET", payload = null) => {
    let options = {
        method,
        mode: "cors",
        headers: {"Content-Type": "application/json"},
    };
    if (payload) {
        options.body = JSON.stringify(payload);
    }

    return fetch(url, options)
        .then((response) => response.json())
        .then((response) => response)
        .catch((error) => {
            alert(`${method} went wrong!`);
            console.error(error);
        });
};

const getData = (url) => genericFetch(BASE_URL_ENV + url, "GET");

const postData = (url, payload) =>
    genericFetch(BASE_URL_ENV + url, "POST", payload);

const patchData = (url) => genericFetch(BASE_URL_ENV + url, "PATCH");

const deleteData = (url) => genericFetch(BASE_URL_ENV + url, "DELETE");

export {postData, getData, patchData, deleteData};
