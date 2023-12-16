const express = require('express');
const dotenv = require('dotenv');
const path = require('path');
const envPath = path.resolve(__dirname, '..', '.env');
dotenv.config({ path: envPath });

const app = express();

app.use(express.json());

const productList = [
  { id: 1, name: 'Iphone 11', price: 19.99 , image: 'https://cdn.tgdd.vn/Products/Images/42/213031/iphone-12-xanh-duong-new-600x600-600x600.jpg'},
    { id: 2, name: 'Iphone 12', price: 29.99 , image: 'https://cdn.tgdd.vn/Products/Images/42/213031/iphone-12-xanh-duong-new-600x600-600x600.jpg'},
    { id: 3, name: 'Iphone 13', price: 39.99 , image: 'https://cdn.tgdd.vn/Products/Images/42/213031/iphone-12-xanh-duong-new-600x600-600x600.jpg'},
];
app.get('/api/products', (req, res) => {
  res.json(productList);
});

app.listen(process.env.PORT, '0.0.0.0', () => {
  console.log(`Server is running on port ${process.env.PORT}`);
});
