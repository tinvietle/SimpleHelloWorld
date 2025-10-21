const BASE_URL = '/demo';

function fetchObject(key) {
    let url = `${BASE_URL}/object/${key}`;
    fetch(url)
        .then(response => response.blob())
        .then((myBlob) => {
            const objectURL = URL.createObjectURL(myBlob);
            const img_S3 = document.getElementById('s3-image');
            img_S3.src = objectURL;
        });
}

function fetchObjectWithRole(key) {
    let url = `${BASE_URL}/object-with-role/${key}`;
    fetch(url)
        .then(response => response.blob())
        .then((myBlob) => {
            const objectURL = URL.createObjectURL(myBlob);
            const img_S3 = document.getElementById('s3-image');
            img_S3.src = objectURL;
        });
}

function fetchObjectFromBucketWithRole() {
    const url = `${BASE_URL}/object-bucket-with-role`;
    const tableBody = document.getElementById('table-body');

    fetch(url)
        .then(response => {
            if (!response.ok) throw new Error("Failed to fetch bucket list");
            return response.text();
        })
        .then(textData => {
            const keys = textData
                .split('\n')
                .map(k => k.trim())
                .filter(k => k !== '');

            keys.forEach(key => {
                const row = document.createElement('tr');

                // Object name
                const cellName = document.createElement('td');
                cellName.textContent = key;
                row.appendChild(cellName);

                // Image preview
                const cellImage = document.createElement('td');
                const img = document.createElement('img');
                img.width = 100;
                img.height = 100;
                img.src = `${BASE_URL}/object-with-role/${key}`;
                cellImage.appendChild(img);
                row.appendChild(cellImage);

                // Download link
                const cellDownload = document.createElement('td');
                const link = document.createElement('a');
                link.textContent = 'Download';
                link.href = `${BASE_URL}/download-object/${key}`;
                link.download = key;
                cellDownload.appendChild(link);
                row.appendChild(cellDownload);

                tableBody.appendChild(row);
            });
        })
        .catch(err => console.error("fetchObjectFromBucketWithRole error:", err));
}

