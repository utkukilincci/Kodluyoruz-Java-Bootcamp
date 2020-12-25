[![Work in Repl.it](https://classroom.github.com/assets/work-in-replit-14baed9a392b3a25080506f3b7b6d57f295ec2978f6f33ec97e36a161684cbe9.svg)](https://classroom.github.com/online_ide?assignment_repo_id=3838714&assignment_repo_type=AssignmentRepo)

# Warehouse Restful Api

Merhaba arkadaşlar, bu hafta Warehouse Restful API'sı oluşturacağız. Uygulamamızı **Spring Boot** kullanarak yazacağız.

# Gereksinimler

* Spring Boot 
* Spring Web
* Jpa
* H2 Database
* Lombok



# Uygulama Özellikleri

Bu API ile sistemde kayıtlı Depo'lara yine sistemde kayıtlı bulunan ürünlerin stok miktarlarını tutacağız. Bir ürün aratıldığında o ürünün hangi depoda kaç adet ürün olduğunu görebildiğimiz gibi dilediğimizde ürün çıkartma / ekleme ve ürün stoğunu güncelleme gibi işlemleri yapacağız.

# Modeller

1. Warehouse
2. Product
3. ProductWarehouse

**Warehouse**

Depomuzun özellikleri aşağıdaki gibidir.
  * Depo ID'si (ID) (Primary Key)
  * Depo Kodu (Code) 
  * Depo'nun ismi (Name)
  * Deponun statüsü ( WarehouseStatus {ACTIVE, PASSIVE, DELETED} )
  * Kayıt Tarihi (CreateDate)
  * Güncelleme Tarihi (UpdateDate)

**Product**
Ürüne ait özellikle aşağıodaki gibidir.

  * Ürün ID'si (ID) ( Primary Key )
  * Ürün Kodu (Code)
  * Ürünün ismi (Name)
  * Ürünün KDV'si (VATRate)
  * Ürünün KDV Fiyatı (VatAmount)
  * Ürünün KDV'siz Fiyatı (BasePrice)
  * Ürünün KDV Dahil fiyatı (VatIncludedPrice)
  * Ürünün durumu ( ProductStatus {ACTIVE, PASSIVE} )
  * Kayıt Tarihi (CreateDate)
  * Güncelleme Tarihi (UpdateDate)
  
**ProductWarehouse**
Bu model İlişkisel olarak depo - ürün bilgisini tutacaktır. Özellikleri aşağıdaki gibidir.

  * Ürün ID'si (ProductId)
  * Depo ID'si (WarehouseId)
  * Stok Miktarı (StockAmount)
  * İşlem zamanı (TransactionDate)
  

# Fonksiyonlar

**WarehouseController**


1. Depo Listeleme
    * **Method Name** = list
    * **HTTP Request Type** = GET
    * **End Point** = /warehouseapi/warehouses
    * **Kondisyon** = Tüm aktif depolar listelenmeli. Dilerseniz aktif olup olmama durumunu requestten alabileceğiniz gibi sadece aktif olanları da listeleyebilirsiniz.
    
2. Depo Yaratma
    * **Method Name** = create
    * **HTTP Request Type** = POST
    * **End Point** = /warehouseapi/warehouses
    * **Kondisyon** = Depo ismi kesinlikle olmalı, depo ismi girilmemişse exception fırlatılıp uyarı verilmeli. Ayrıca, aynı depo kodundan en fazla bir adet olmalı, bir depo kodu sadece bir kere kullanılmalı. Depo yaratıldığında default olarak statüsü aktif olmalı.
    
3. Depo Güncelleme
    * **Method Name** = update
    * **HTTP Request Type** = PUT
    * **End Point** = /warehouseapi/warehouses/{warehouseId}
    * **Kondisyon** = DB'de kayıtlı bir depo yok ise hata fırlatılmalı. Güncelleme işleminde depo'nun statüsü DELETED olarak seçilmişse depo içerisinde stoğu olan ürün var mı kontrol edilmeli, eğer stok var ise depo silinemememli. Önce transfer yapılmalı uyarısı verilmeli.
    
4. Depo Silme
    * **Method Name** = delete
    * **HTTP Request Type** = DELETE
    * **End Point** = /warehouseapi/warehouses/{warehouseId}
    
5. Transfer 
    * **Method Name** = transfer
    * **HTTP Request Type** = POST
    * **EndPoint** = /warehouseapi/warehouses/transfer/{fromWarehouseId}/{toWarehouseId}
    * **Kondisyon** = Her iki deponun durumu aktif olmalı, outgoing depodaki tüm ürünler incoming deposuna aktarılmalı. Bu kod bloğu transactional olmalı, herhangi bir yerde hata alırsa o ana kadar yapılan tüm işlemler geri alınabilir olmalı. 
    
Yukarıdaki end-pointlerin detaylı açıklaması şöyledir.

Kullanıcı N adet depo oluşturabilir ve oluşturduğu bu depoların tamamını listeleyebilir. Bir depo silinmek istenildiğinde ilgili deponun içerisinde **stoğu 0'dan büyük herhangi** bir ürün olmamalıdır. Depolar kayıt edildiğinde kayıt tarihi atılmalı ve bir daha bu alan asla güncellenmemeli. Depo güncellenirse, güncellenme tarihi değiştirilmeli.


**ProductController**

1. Ürün Listeleme
    * **Method Name** = list
    * **HTTP Request Type** = GET
    * **End Point** = /warehouseapi/products
    * **Kondisyon** = Tüm aktif ürünler listelenmeli. Dilerseniz aktif olup olmama durumunu requestten alabileceğiniz gibi sadece aktif olanları da listeleyebilirsiniz.
    
2. Ürün Yaratma
    * **Method Name** = create
    * **HTTP Request Type** = POST
    * **End Point** = /warehouseapi/products
    * **Kondisyon** = Ürüne ait tüm özellikler mutlaka requestten gelmeli, herhangi bir alan dolu değilse hata fırlatılmalı. Aynı ürün kodu ile birden fazla ürün olmamalı, girilen ürün kodu sistemde mevcutsa hata fırlatılmalı. Ürünün fiyatı, KDV oranı, KDV'si, KDV'li miktarı gibi alanlar sıfırdan büyük olmalı. 
    
3. Ürün Güncelleme
    * **Method Name** = update
    * **HTTP Request Type** = PUT
    * **End Point** = /warehouseapi/products/{productId}
    * **Kondisyon** = DB'de kayıtlı bir ürün yok ise hata fırlatılmalı. Aynı ürün kodu ile birden fazla ürün olmamalı, girilen ürün kodu sistemde mevcutsa hata fırlatılmalı. Ürünün fiyatı, KDV oranı, KDV'si, KDV'li miktarı gibi alanlar sıfırdan büyük olmalı.   
    
4. Ürün Silme
    * **Method Name** = delete
    * **HTTP Request Type** = DELETE
    * **End Point** = /warehouseapi/products/{productId}
     * **Kondisyon** = Ürün silinmeden önce mutlaka stok bilgisine bakılmalı. Depolar içerisinde ilgili ürüne ait stoğu 0'dan büyük bir kayıt var ise ürün silinmemeli ve hata fırlatılmalı..
     

**StockController**

1. Transfer 
    * **Method Name** = transfer
    * **HTTP Request Type** = GET
    * **EndPoint** = /warehouseapi/stocks/{productId}/transfer/{fromWarehouseId}/{toWarehouseId}
    * **Kondisyon** = Her iki deponun durumu aktif olmalı. Ürünün durumu aktif olmalı. İlgili ürünün kaydı aktarılan depodan silinip aktarılacak depoya eklenmeli. Eğer ilgili ürün aktarılacak depoda mevcutsa ürünün stok miktarı arttırılmalı. Bu kod bloğu transactional olmalı, herhangi bir yerde hata alırsa o ana kadar yapılan tüm işlemler geri alınabilir olmalı. 

2. Güncelleme
    * **Method Name** = update
    * **HTTP Request Type** = POST
    * **End Point** = /warehouseapi/stocks
    * **Kondisyon** = Request Body olarak gönderilen obje içerisinde ürün ID'si, Depo ID'si ve yeni stock miktarı girilmeli. İlgili ürün ilgili depoda bulunamazsa hata fırlatılmalı. İlgili ürün bulanamazsa hata fırlatılmalı. Ürün ve Depo'nun durumları aktif olmalı eğer değilse hata fırlatılmalı. Ürüne ait yeni stock miktarı 0'dan küçük olmamalı. 
    
3. Özet Bilgi
    * **Method Name** = summaries
    * **HTTP Request Type** = GET
    * **End Point** = /warehouseapi/stocks
    * **Kondisyon** = Bu method ile hangi depoda hangi üründen kaç adet var, bu ürünlerin KDV'li, KDV'siz toplam fiyatları ile toplam KDV bilgilerini de gönderiyor olacağız. 
    
    
# Dikkat edilmesi gereken hususlar #

Kullanacağımız veri tabanı H2 olacaktır.  H2'nun memory özelliği olduğundan uygulamamızı kapatıp açsakta bu bilgilerin local file'de kalmasını sağlayalım. ( Bunun için biraz araştırma yapmanız gerekecek ) 

Kodlarımızı yazarken MVC ( Model / View / Controller ) yapısına dikkat edelim. Data Layer'da sadece Database işlemleri yaparken Business Layer'da gerekiyorsa validasyonlarımızı ve hesaplamalarımızı yapacağız. Servis katmanı asla data katmanındaki objeyi bilmeyecektir. Aynı anda data katmanı da servis katmanındaki objeyi bilmeyerek loose coupling'i gerçekleştireceğiz. Controller katmanında herhangi bir business olmamalı.

Kodlarımızı yazarken ilgili yerlere log basmayı unutmayalım. 

Ek : Interceptor kullanarak gelen tüm isteklerinizi loglayabilirsiniz. ( **Springboot Intercepter Http Requests** for google searching :)) ) 

Ek2: Customize exception sınıflarımızı türetelim.

EK3 : Spring Controller Advice ile Exception handling yapabilirsiniz. Bu sayede business katmanında fırlatacağınız exceptionları handle ederek doğru custom responsları dönebilirsiniz. ( **How to handle exceptions in Spring Boot via Controller Advice** for google searching :)) ) 

EK4: spring'in auto-ddl özelliğinden faydalanmayı unutmayın..

#### Hepinize iyi kodlamalar arkadaşlar ####
    
 

    


