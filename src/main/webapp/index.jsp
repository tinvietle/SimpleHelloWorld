<html>

<body>
    <h2>Hello World$$$</h2>
    <img src="https://cloud-public-mpg.s3.us-east-1.amazonaws.com/picture.png" alt="Picture of multiple people" />
    <img id="s3-image" alt="Fetched from S3" />
    <!-- <button onclick="fetchObject('cat.jpeg')">Load cat.jpeg</button> -->
    <table id="objects-table">
        <thead>
            <tr>
                <th>Object Name (Key)</th>
                <th>Preview</th>
                <th>Download</th>
            </tr>
        </thead>
        <tbody id="table-body"></tbody>
    </table>
    <script src="js/fetch.js"></script>
    <script>fetchObjectWithRole('cat.jpeg')</script>
    <script>fetchObjectFromBucketWithRole()</script>
</body>

</html>