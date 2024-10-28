# Ecommerce Project

>[TR]

ECommerce projesi bir e ticaret uygulamasının sipariş, stok, kullanıcı yönetimini yapan servislerini içerir. 

### Servisler:

* **Product-App:**
Ürün bilgilerini içerir ve stok yönetimini yapar
* **Order-App:**
Sipariş yönetimi yapar
* **User-App:**
Kullanıcı bilgilerini içerir.
* **EurekaServer:**
EurekaServer uygulamasıdır.

###  Kurulum

Ana dizin altındaki ./docker klasörü altına girip 
```bash
docker-compose up -d 
```

ile gerekli olan databaseleri, kafka modüllerini, traefik yöneticisini  ayağa kaldırır.

Uygulamaların çalıştığını gördükten sonra ./product-app, ./order-app, ./user-app, ./EurekaServer dizinlerine girip her birinde;

```bash
./gradlew bootRun 
``` 
yada kullandığınız IDE'den çalıştırabilirsiniz.

### Özellikler
 Sonraki adımda ECommerce.postman_collection.json bir Postman uygulamasına Import edip istekleri deneyebilirsiniz.
 Deneyebileceğiniz servisler.
* **Product-App:**
  * _Read Product By Id (/product/read/{id})_: Ürün Id si ile ürün bilgilerini sorgular.
  * _Create Product (product/createProduct)_: Ürün yaratır
  * _Update Product Stock (/product/updateProductStock/{productId}/{newStock}): Stok günceller
* **Order-App:**
  * _Read Order By Id (/order/placeOrder)_: Sipariş sorgular
  * _Place Order (/order/placeOrder)_: Yeni bir sipariş oluşturur
* **User-App:**
  * _Read User By Id (/order/placeOrder)_: Kullanıcı sorgular
  * _Is User Exists (/order/placeOrder)_: Kullanıcı varlığına göre true/false döner.

###  Benchmark&Profiling

./benchmark klasöründe bulunan Product App Test.jmx dosyasını Jmeter programı içinde açarsanız belirtilmiş  olan testleri koşabilirsiniz.