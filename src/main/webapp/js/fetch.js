function fetchObject(key) {
    let url = `http://100.26.44.160:8080/demo/object/${key}`;
    fetch(url)
    .then(response => response.blob())
    .then((myBlob) => {
        const objectURL = URL.createObjectURL(myBlob);
        const img_S3 = document.getElementById('s3-image');
        img_S3.src = objectURL;
    });
}
function fetchObjectWithRole(key) {
    let url = `http://100.26.44.160:8080/demo/object-with-role/${key}`;
    fetch(url)
    .then(response => response.blob())
    .then((myBlob) => {
        const objectURL = URL.createObjectURL(myBlob);
        const img_S3 = document.getElementById('s3-image');
        img_S3.src = objectURL;
    });
}