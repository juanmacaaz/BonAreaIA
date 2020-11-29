const express = require('express');
const fileUpload = require('express-fileupload');
const cors = require('cors');
const bodyParser = require('body-parser');
const morgan = require('morgan');
const _ = require('lodash');
const fs = require('fs');

const app = express();

app.use(fileUpload({
    createParentPath: true
}));

app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(morgan('dev'));

const port = process.env.PORT || 3000;

app.listen(port, () => 
  console.log(`App is listening on port ${port}.`)
);

app.post('/update', async (req, res) => {
    try {
        let data = JSON.stringify(req.body);
        fs.writeFileSync('data.json', data);
        console.log(req.body);
        res.status(200).send('OK');
    } catch (err) {
        res.status(500).send(err);
    }
});

app.get('/data', async (req, res) => {
    try {
        let rawdata = fs.readFileSync('data.json');
        let student = JSON.parse(rawdata);
        res.status(200).send(student);
    } catch (err) {
        res.status(500).send(err);
    }
});