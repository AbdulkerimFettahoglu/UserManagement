## User Management API
Bu uygulama ile veritabanına kullanıcılar ekleyebilir, düzenleyebilir ve silebilirsiniz.

### MySql Bilgileri

Lokal bilgisayarınızdaki Mysql bilgileri aşağıdaki gibi olmalıdır. MySql server' ınız başka bir konteynerde kurulu ise uygulamanızın konteyneri ile aynı network' de olduklarından emin olun.

```
jdbc:mysql://localhost:3306/my_database
Database Name:my_database
MySql Port:3306
User Name :root
Password :
(No password)
```

### Docker

Aşağıdaki komutları kullanarak imajınızı oluşturup çalıştırabilirsiniz.

```
docker build --tag=user-management:latest .
docker run -p8080:8080 user-management:latest
```

### Swagger UI

Uygulamada Swagger implementasyonu vardır. Aşağıdaki linkten ulaşabilirsiniz.

```
http://localhost:8080/swagger-ui.html#/
```

## Create User (JWT olmadan ulaşılabilir)
Kullanıcı oluşturmak için aşağıdaki modeli kullanın

```
(POST)http://localhost:8080/user
{
    "email": "kerim",
    "firstname": "kerim",
    "lastname": "fett",
    "password": "password"
}
```

## Login User (JWT olmadan ulaşılabilir)
Halihazırdaki kullanıcıyı kullanarak JWT almak için kullanabilirsiniz. Cevapta bulunan "Header" lar arasından "access_token" değerini alabilirsiniz. İstek tipi "x-www-form-urlencoded" olmalıdır.

```
(POST)http://localhost:8080/login
{
    "username": "kerim",
    "password": "password"
}
```

## Update User
Kullanıcı güncellemek için aşağıdaki modeli kullanın

```
(PUT)http://localhost:8080/user/update
{
    "id": 1,
    "email": "kerimm",
    "firstname": "kerim",
    "lastname": "fett",
    "password": "password"
}
```

## Get User
Veritabanında kullanıcı getirmek için aşağıdaki URI adresini kullanın ve id değeri verin

```
(GET)http://localhost:8080/user/find?id=1
```

## Get User
Veritabanındaki tüm kullanıcıları getirmek için aşağıdaki URI adresini kullanın

```
(GET)http://localhost:8080/user/all
```

## Delete User
Kullanıcı silmek için aşağıdaki URI adresini kullanın ve id değeri verin

```
(DELETE)http://localhost:8080/user/delete?id=1
```

##Örnek İstekler
Örnek istekler için "UserManagement.postman_collection.json" dosyasını indirip Postman uygulaması ile kullanabilirsiniz.