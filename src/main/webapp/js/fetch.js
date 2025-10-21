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

function fetchObjectFromBucketWithRole() {
    let url = 'http://100.26.44.160:8080/demo/object-bucket-with-role';
    fetch(url)
    .then(response => response.text())
    .then((textData) => {
        const lines = textData.split('\n');
        const keys = lines.slice(2).filter(key => key.trim() !== '');

        keys.forEach(key => {
            const row = document.createElement('tr');

            const cellImage = document.createElement('td');
            const img = document.createElement('img');
            img.width = 100;
            img.height = 100;
            img.src = `http://100.26.44.160:8080/demo/object-bucket-with-role/${key}`;
            cellImage.appendChild(img);
            row.appendChild(cellImage);

            const cellDownload = document.createElement('td');
            const downloadLink = document.createElement('a');
            downloadLink.textContent = 'Download';
            downloadLink.className = 'download-button';
            downloadLink.href = `http://100.26.44.160:8080/demo/object-bucket-with-role/${key}`;
            cellDownload.appendChild(downloadLink);

            row.appendChild(cellDownload);

            tableBody.appendChild(row);
        });
    });
}
